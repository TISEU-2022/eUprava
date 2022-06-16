from pydantic import BaseModel, Field
from typing import Optional
import logging
import datetime as dt

logger = logging.getLogger(__name__)


class MarriageAddSchema(BaseModel):
    party_1_id: str = Field(...)
    party_2_id: str = Field(...)
    witness_1_id: str = Field(...)
    witness_2_id: str = Field(...)


class MarriageGetSchema(BaseModel):
    id: str = Field(...)
    party_1_id: str = Field(...)
    party_2_id: str = Field(...)
    witness_1_id: str = Field(...)
    witness_2_id: str = Field(...)
    divorced_at: Optional[dt.datetime] = Field(None)
    created_at: dt.datetime = Field(...)

    class Config:
        ignore_extra = True
        allow_extra = True
        json_encoders = {
            dt.datetime: lambda d: "{:%b %d, %Y}".format(d),
        }
