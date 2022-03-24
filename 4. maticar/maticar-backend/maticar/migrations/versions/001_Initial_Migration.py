from migrate import *  # noqa
from sqlalchemy import MetaData, Column, String, Table, DateTime, ForeignKey
from sqlalchemy.dialects.mysql import BIGINT
import uuid

meta = MetaData()


def generate_uuid():
    return str(uuid.uuid4())


birth_register = Table(
    'birth_register',
    meta,
    Column('id', BIGINT(unsigned=True), primary_key=True),
    Column('identification_number', String(13), nullable=False),
    Column('first_name', String(50), nullable=False),
    Column('last_name', String(50), nullable=False),
    Column('gender', String(10), nullable=False),
    Column('date_of_birth', DateTime(), nullable=False),
    Column('deceased_at', DateTime(), nullable=True),
)

marriage = Table(
    'user_marriage',
    meta,
    Column('id', BIGINT(unsigned=True), primary_key=True),
    Column('created_at', DateTime(), nullable=False),
    Column('divorced_at', DateTime(), nullable=True),
    Column('party_1_id', BIGINT(unsigned=True), ForeignKey("birth_register.id", ondelete="CASCADE"), nullable=False),
    Column('party_2_id', BIGINT(unsigned=True), ForeignKey("birth_register.id", ondelete="CASCADE"), nullable=False),
)

user_relations = Table(
    'user_relation',
    meta,
    Column('id', BIGINT(unsigned=True), primary_key=True),
    Column('user_relation_parent_id_foreign', BIGINT(unsigned=True), ForeignKey('birth_register.id', ondelete="CASCADE"), primary_key=True, unique=False, index=True),
    Column('user_relation_child_id_foreign', BIGINT(unsigned=True), ForeignKey('birth_register.id', ondelete="CASCADE"), primary_key=True, unique=False, index=True)
    )


def upgrade(migrate_engine):
    # Upgrade operations go here. Don't create your own engine; bind
    # migrate_engine to your metadata
    meta.bind = migrate_engine
    birth_register.create()
    marriage.create()
    user_relations.create()
    pass


def downgrade(migrate_engine):
    # Operations to reverse the above upgrade go here.
    meta.bind = migrate_engine
    user_relations.drop()
    marriage.drop()
    birth_register.drop()
