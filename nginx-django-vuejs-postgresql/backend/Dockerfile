FROM python:3.9
RUN mkdir /django
WORKDIR /django
RUN pip install -U pip
COPY requirements.txt .
RUN pip install -r requirements.txt
COPY . .
