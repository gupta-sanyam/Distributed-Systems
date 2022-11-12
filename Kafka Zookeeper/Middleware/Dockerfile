FROM python:3.8

#install unrar
RUN apt-get update

WORKDIR /ingest
RUN pwd
# for installing ms sql sever driver

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
RUN rm requirements.txt
ENV FLASK_APP=main
ENV FLASK_ENV=development