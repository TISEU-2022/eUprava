from sqlalchemy import (
    Column,
    Table,
    DateTime,
    String,
    ForeignKey
)
from sqlalchemy.dialects.mysql import BIGINT
from sqlalchemy.orm import relationship

from .base import Base

parent_child = Table(
    "parent_child",
    Base.metadata,
    Column("parent_id", BIGINT(), ForeignKey("parent.id"), primary_key=True),
    Column("child_id", BIGINT(), ForeignKey("child.id"), primary_key=True)
)


class User(Base):
    __tablename__ = "user"
    id = Column(BIGINT(), primary_key=True)
    first_name = Column(String(255), nullable=False)
    last_name = Column(String(255), nullable=False)
    gender = Column(String(255), nullable=True, index=True)
    city_of_birth = Column(String(255), nullable=False)
    date_of_birth = Column(DateTime(), nullable=False)
    children = relationship(
        "User",
        secondary="parent_child",
        primaryjoin="User.id==parent_child.c.parent_id",
        secondaryjoin="User.id==parent_child.c.child_id",
        backref="parents"
    )
