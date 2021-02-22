# Nginx

## Conf

```nginx
server {
    listen 80;
    location / {
        root /usr/share/nginx/html/;
        try_files $uri $uri/ /;        
    }
    location /api/ {
        proxy_pass http://backend:8000/;
    }
    location /static/ {
        alias /static/;
    }
}
```



# Django

## Start
```bash
$ cd backend
$ python -m venv venv
$ source venv/Scripts/activate
$ pip install -r requirements.txt
$ python manage.py runserver
```



## Secret variables

```python
# settings.py

# ...

from django.core.exceptions import ImproperlyConfigured

with open("./project_django/secrets.json") as f:
    secrets = json.loads(f.read())

def get_secret_value(secret_variable):
    try:
      	return secrets[secret_variable]
    except KeyError:
        error_msg = f'Set the {secret_variable} environment variable'
        raise ImproperlyConfigured(error_msg)

# ...
```



## Database setting

```python
# settings.py

# ...

import psycopg2.extensions

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.postgresql',
        'NAME': get_secret_value('DJANGO_DB_NAME'), # e.g. 'djangodb'
        'USER': get_secret_value('DJANGO_DB_USER'), # e.g. 'django'
        'PASSWORD': get_secret_value('DJANGO_DB_PASSWORD'), # e.g. 'psqlpassword'
        'HOST': get_secret_value('DJANGO_DB_HOST'), # e.g. 'localhost'
        'PORT': get_secret_value('DJANGO_DB_PORT'), # e.g. '5432'
    },
    'OPTIONS': {
        'isolation_level': psycopg2.extensions.ISOLATION_LEVEL_SERIALIZABLE,
    },
}

# ...
```

