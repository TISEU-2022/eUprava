from sqlalchemy import (
    Column,
    DateTime,
    String,
    ForeignKey,
)
from sqlalchemy.dialects.mysql import BIGINT
from sqlalchemy.orm import relationship
from .base import Base


class UserBirthRegister(Base):
    __tablename__ = "birth_register"
    id = Column(BIGINT(unsigned=True), primary_key=True, index=True)
    identification_number = Column(String(13), nullable=False, index=True)
    first_name = Column(String(50), nullable=False)
    last_name = Column(String(50), nullable=False)
    gender = Column(String(10), nullable=False)
    date_of_birth = Column(DateTime(), nullable=False)
    deceased_at = Column(DateTime(), nullable=True)
    country_of_birth = Column(String(50), nullable=True)
    citizenship = Column(String(50), nullable=True)
    parents = relationship(
        "UserBirthRegister",
        secondary='user_relation',
        foreign_keys='UserRelation.parent_id'
    )
    children = relationship(
        "UserBirthRegister",
        secondary='user_relation',
        foreign_keys='UserRelation.child_id'
    )


class UserMarriageRegister(Base):
    __tablename__ = "user_marriage"
    id = Column(BIGINT(unsigned=True), primary_key=True, index=True)
    created_at = Column(DateTime(), nullable=False)
    party_1_id = Column(String(13), ForeignKey('birth_register.identification_number'), unique=False, nullable=False)
    party_2_id = Column(String(13), ForeignKey('birth_register.identification_number'), unique=False, nullable=False)
    witness_1_id = Column(String(13), ForeignKey('birth_register.identification_number'), unique=False, nullable=False)
    witness_2_id = Column(String(13), ForeignKey('birth_register.identification_number'), unique=False, nullable=False)
    divorced_at = Column(DateTime(), nullable=True)


class UserRelation(Base):
    __tablename__ = 'user_relation'
    id = Column(BIGINT(unsigned=True), primary_key=True, index=True)
    parent_id = Column('user_relation_parent_id_foreign', String(13), ForeignKey('birth_register.identification_number', ondelete="CASCADE"), primary_key=True, unique=False, index=True)
    child_id = Column('user_relation_child_id_foreign', String(13), ForeignKey('birth_register.identification_number', ondelete="CASCADE"), primary_key=True, unique=False, index=True)
