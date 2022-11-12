FROM python:3.8

RUN apt-get update

WORKDIR /ingest
RUN pwd

COPY . .
RUN pip install --no-cache-dir -r requirements.txt
RUN rm requirements.txt

ENV FLASK_APP=main