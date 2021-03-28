# Https Nginx

## Docker Nginx Https 적용하기

1. 인증용 nginx server 실행
2. certbot 인증
3. 인증서를 이용한 nginx server 구축

### 1. 인증용 nginx server 실행

nginx 실행

```bash
$ docker run --name ssltest -it -d -p 80:80 -p 443:443 -p 8080:8080 -p 7946:7946 -v /var/www/a202:/var/www/a202 nginx:alpine
```

nginx bash 진입

```bash
$ docker exec -it ssltest sh
```

nginx 설정 추가

```bash
$ vi /etc/nginx/conf.d/default.conf
```

http 내 location / {} 밑에 붙여넣어준다.

```nginx
...
	location ^~ /.well-known/acme-challenge/ {
                default_type "text/plain";
                root /var/www/a202;
    }
...
```

nginx test 및 재실행 후 터미널을 나온다.

```bash
$ nginx -T
$ nginx -s reload
$ exit
```



### 2. certbot 인증

certbot 실행을 통해 인증 진행

```bash
$ sudo docker run -it --rm --name certbot \
            -v "/etc/letsencrypt:/etc/letsencrypt" \
            -v "/var/lib/letsencrypt:/var/lib/letsencrypt" \
            -v "/var/www/a202:/var/www/a202" \
            certbot/certbot certonly --webroot -w /var/www/a202 -d j4a202.p.ssafy.io
```

인증용 nginx 및 certbot을 삭제한다.

```bash
$ docker stop `docker ps -a`
$ docker rm `docker ps -a`
```



### 3. 인증서를 이용한 nginx server 구축

```nginx
server {
    listen       80;
    listen  [::]:80;
    server_name  j4a202.p.ssafy.io;
    
    return 301 https://$host$request_uri;
}

server {
    listen 443 ssl;
    server_name j4a202.p.ssafy.io;

    ssl_certificate /etc/letsencrypt/archive/j4a202.p.ssafy.io/fullchain1.pem;
    ssl_certificate_key /etc/letsencrypt/archive/j4a202.p.ssafy.io/privkey1.pem;

    ssl_session_timeout 5m;
    ssl_protocols SSLv2 SSLv3 TLSv1 TLSv1.1 TLSv1.2 TLSv1.3;
    ssl_ciphers HIGH:!aNULL:!MD5;
    ssl_prefer_server_ciphers on;

    location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }
}
```

```bash
$ docker run --name ssltest -it -d -p 80:80 -p 443:443 -v /etc/letsencrypt:/etc/letsencrypt -v ~/default.conf:/etc/nginx/conf.d/default.conf nginx
```



### `docker-compose.yml`

```dockerfile
version: '3'

services:
  nginx:
    image: nginx
    ports:
      - "80:80"
      - "443:443"
    volumes:
      - /etc/letsencrypt:/etc/letsencrypt
      - ./default.conf:/etc/nginx/conf.d/default.conf
```



### `default.conf`

```nginx
server {
    listen       80;
    listen  [::]:80;
    server_name  example.com;
    
    return 301 https://$host$request_uri;
}

server {
    listen 443 ssl;
    server_name example.com;

    ssl_certificate /etc/letsencrypt/archive/{site-url}/fullchain1.pem;
    ssl_certificate_key /etc/letsencrypt/archive/{site-url}/privkey1.pem;

    ssl_session_timeout 5m;
    ssl_protocols SSLv2 SSLv3 TLSv1 TLSv1.1 TLSv1.2 TLSv1.3;
    ssl_ciphers HIGH:!aNULL:!MD5;
    ssl_prefer_server_ciphers on;

    location / {
        proxy_pass http://server:8080;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header Host $http_host;
    }
}
```

