# coding=utf-8

from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from sqlalchemy.pool import NullPool
import os
import logging

logger = logging.getLogger(__name__)


db_url = 'mysql+pymysql://{}:{}@{}:{}/{}'.format(
    os.getenv("MYSQL_USER"),
    os.getenv("MYSQL_PASSWORD"),
    os.getenv("MYSQL_HOST"),
    os.getenv("MYSQL_PORT"),
    os.getenv("MYSQL_DB"),
)


def recreate_test_db(url):
    my_engine = create_engine(url, echo=True, pool_pre_ping=True)  # connect to server
    my_engine.execution_options(isolation_level="AUTOCOMMIT").execute("DROP DATABASE IF EXISTS test_db;")  # create db
    my_engine.execution_options(isolation_level="AUTOCOMMIT").execute("CREATE DATABASE test_db;")  # create db


try:
    if os.getenv("TEST_DB", None):
        url = 'mysql+pymysql://{}:{}@{}:{}/'.format(
            os.getenv("MYSQL_USER"),
            os.getenv("MYSQL_PASSWORD"),
            os.getenv("MYSQL_HOST"),
            os.getenv("MYSQL_PORT"),
        )
        db_url = 'mysql+pymysql://{}:{}@{}:{}/{}'.format(
            os.getenv("MYSQL_USER"),
            os.getenv("MYSQL_PASSWORD"),
            os.getenv("MYSQL_HOST"),
            os.getenv("MYSQL_PORT"),
            "test_db",
        )
        recreate_test_db(url)
except Exception as e:
    logger.debug("Error creating test db. Exception: {}".format(str(e)))


def metadata_dump(sql, *multiparams, **params):
    # print or write to log or file etc
    print(sql.compile(dialect=engine.dialect))


engine = create_engine(db_url, pool_pre_ping=True, poolclass=NullPool)
# engine = create_engine(db_url, strategy='mock', executor=metadata_dump)
Session = sessionmaker(autocommit=False, autoflush=True, bind=engine)

Base = declarative_base()


def get_db():
    try:
        db = Session()
        yield db
    finally:
        db.close()
