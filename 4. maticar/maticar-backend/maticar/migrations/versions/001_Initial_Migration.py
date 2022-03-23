from migrate import *  # noqa
from sqlalchemy import MetaData, Column, String, Table, DateTime
from sqlalchemy.dialects.mysql import BIGINT
import uuid

meta = MetaData()


def generate_uuid():
    return str(uuid.uuid4())


user = Table(
    'user',
    meta,
    Column('id', BIGINT(), primary_key=True),
    Column('first_name', String(255), nullable=False),
    Column('last_name', String(255), nullable=False),
    Column('identification_number', String(50), nullable=False),
    Column('gender', String(255), nullable=True),
    Column('city_of_birth', String(255), nullable=True),
    Column('date_of_birth', DateTime(), nullable=True),
)


def upgrade(migrate_engine):
    # Upgrade operations go here. Don't create your own engine; bind
    # migrate_engine to your metadata
    meta.bind = migrate_engine
    user.create()
    pass


def downgrade(migrate_engine):
    # Operations to reverse the above upgrade go here.
    meta.bind = migrate_engine
    user.drop()
