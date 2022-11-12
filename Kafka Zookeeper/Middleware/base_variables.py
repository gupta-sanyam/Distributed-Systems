import logging
import configparser
import os
import datetime
import pymongo

#paths of config files which are used at different stages of execution
setting_config_path = os.path.join(os.getcwd(), 'config/', 'settings.ini')

config = configparser.ConfigParser()
config.read(setting_config_path)

logging.basicConfig(filename=config.get('LOG','logfile_path'),
                    format='%(asctime)s %(message)s',
                    level=logging.INFO)
logger=logging.getLogger() 

time_now = lambda: datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
date_now = lambda : datetime.datetime.now().strftime("%Y-%m-%d")

id = os.environ['ID']
topics = config[id]['TOPICS'].split(", ")
neighbours = config[id]['NEIGHBOURS'].split(", ")
db_config = config['Mongo']
database = config[id]['DATABASE']
mng_client = pymongo.MongoClient(db_config['CONNECTION_STRING'])  # establishing the Mongo Connection
mng_db = mng_client[database]  # mongo db name
publisher_collection = db_config['PUBLISHER_COLLECTION']
suscriber_collection = db_config['SUSCRIBER_COLLECTION']
