from fastapi import (
    APIRouter,
    Depends,
    HTTPException,
    Body
)
from maticar.app import models as m_api
from maticar.app import managers as mm_mng
from sqlalchemy.orm import Session
from maticar.app.schemas import BirthCertificateSchema, ParentSchema
import logging

logger = logging.getLogger(__name__)

user_router = APIRouter(
    prefix="/user",
    tags=["user"],
    responses={404: {"msg": "Not Found"}}
)


@user_router.post("/", status_code=201)
async def add_birth_certificate(
    birth_certificate: BirthCertificateSchema = Body(...),
    db: Session = Depends(m_api.get_db)
):
    try:
        error = []
        result = await mm_mng.UserManager().add_birth_certificate(birth_certificate)
        if result:
            return {"status": "Added"}
        else:
            error.append(
                {
                    "detail": [
                        {
                            "loc": [
                                "body",
                                "identification_number"
                            ],
                            "msg": "Birth certificate already exists!",
                            "type": "value_error.identification_number"
                        }
                    ]
                }
            )
    except Exception as e:
        logger.error(f"Error occured getting creating marriage. Error {str(e)}")
    raise HTTPException(status_code=422, detail=error)


@user_router.post("/parents/{identification_number:str}", status_code=201)
async def add_parents(
    identification_number: str,
    parents: ParentSchema = Body(...),
    db: Session = Depends(m_api.get_db)
):
    try:
        result = await mm_mng.UserManager().add_parents(parents, identification_number)
        if result is True:
            return {"status": "Added"}
    except Exception as e:
        logger.error(f"Error occured getting creating marriage. Error {str(e)}")
    raise HTTPException(status_code=422, detail=result)
