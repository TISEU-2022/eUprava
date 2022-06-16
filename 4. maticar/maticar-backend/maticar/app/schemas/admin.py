from pydantic import BaseModel, Field
import logging
from typing import Optional

logger = logging.getLogger(__name__)


class WorkerAddSchema(BaseModel):
    identity_number: str = Field(...)
    password: str = Field(...)
    first_name: str = Field(...)
    last_name: str = Field(...)
    username: str = Field(...)


class WorkerUpdateSchema(BaseModel):
    identity_number: str = Field(...)
    password: Optional[str] = Field(None)
    first_name: str = Field(...)
    last_name: str = Field(...)
    username: str = Field(...)
