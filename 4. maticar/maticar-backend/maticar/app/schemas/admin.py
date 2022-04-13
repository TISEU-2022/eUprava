from pydantic import BaseModel, Field


class WorkerAddSchema(BaseModel):
    identity_number: str = Field(...)
    password: str = Field(...)
    first_name: str = Field(...)
    last_name: str = Field(...)
    username: str = Field(...)
