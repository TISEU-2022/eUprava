from pydantic import BaseModel, Field
import logging
from typing import Optional, Generic, Sequence, TypeVar, List
from fastapi_pagination.bases import AbstractParams, BasePage
from pydantic import conint
from fastapi_pagination import Params
import datetime as dt

logger = logging.getLogger(__name__)

T = TypeVar("T")


class RelationsSchema(BaseModel):
    children: Optional[List[str]] = Field(None)


class ParentSchema(BaseModel):
    parent_1_iden_number: str = Field(...)
    parent_2_iden_number: str = Field(...)


class BirthCertificateSchema(BaseModel):
    identification_number: str = Field(...)
    first_name: str = Field(...)
    last_name: str = Field(...)
    gender: str = Field(...)
    date_of_birth: dt.date = Field(...)
    country_of_birth: str = Field(...)
    citizenship: str = Field(...)


class UserGetSchema(BaseModel):
    birth_certificate: BirthCertificateSchema = Field(None)
    parents: List[str] = Field([])
    children: List[str] = Field([])


class CheckName(BaseModel):
    id: Optional[int] = Field(None)
    name: str = Field(...)


class ErrorSchemaResp(BaseModel):
    error: str = Field(...)


class PaginationPage(BasePage[T], Generic[T]):
    page: conint(ge=0)  # type: ignore
    size: conint(gt=0)  # type: ignore

    __params_type__ = Params

    @classmethod
    def create(
        cls,
        items: Sequence[T],
        total: int,
        params: AbstractParams,
    ) -> BasePage[T]:
        if not isinstance(params, Params):
            raise ValueError("Page should be used with Params")

        return cls(
            total=total,
            items=items,
            page=params.page,
            size=params.size
        )

    class Config:
        json_encoders = {
            dt.datetime: lambda d: "{:%d %B %Y, %H:%M}".format(d),
            dt.date: lambda d: "{:%b %d, %Y}".format(d),
            float: lambda f: "{:.2f}".format(f)
        }
