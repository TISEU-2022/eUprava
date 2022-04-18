from fastapi import (
    APIRouter,
    Depends,
    Response,
    HTTPException
)
from maticar.app import models as m_api
from typing import List
from maticar.app import managers as mm_mng
from sqlalchemy.orm import Session
from maticar.app.auth import (
    JWTBearer,
)
from maticar.app.schemas import MarriageAddSchema, MarriageGetSchema
import logging

logger = logging.getLogger(__name__)

worker_router = APIRouter(
    prefix="/worker",
    tags=["worker"],
    responses={404: {"msg": "Not Found"}}
)


@worker_router.post("/marriage", status_code=200, dependencies=[Depends(JWTBearer())])
async def add_marriage(
    marriage: MarriageAddSchema,
    db: Session = Depends(m_api.get_db)
):
    try:
        result = await mm_mng.WorkerManager().add_marriage(marriage)
        return result
    except Exception as e:
        logger.error(f"Error occured getting creating marriage. Error {str(e)}")
    return None


@worker_router.get("/users", status_code=200, dependencies=[Depends(JWTBearer())])
async def get_users(
    db: Session = Depends(m_api.get_db)
):
    try:
        result = await mm_mng.WorkerManager().get_users()
        return result
    except Exception as e:
        logger.error(f"Error occured getting users. Error {str(e)}")
    return None


@worker_router.put("/marriage/{marriage_id:str}", status_code=200, dependencies=[Depends(JWTBearer())])
async def divorce_marriage(
    marriage_id: str,
    db: Session = Depends(m_api.get_db)
):
    try:
        result = await mm_mng.WorkerManager().divorce_marriage(marriage_id)
        if result:
            return Response(status_code=201)
    except Exception as e:
        logger.error(f"Error occured getting divorcing marriage. Error {str(e)}")
    raise HTTPException(status_code=400, detail="Marriage not found or already divorced.")


@worker_router.get("/marriage", response_model=List[MarriageGetSchema], status_code=200, dependencies=[Depends(JWTBearer())])
async def get_marriages(
    db: Session = Depends(m_api.get_db)
):
    try:
        result = await mm_mng.WorkerManager().get_marriages()
        return result
    except Exception as e:
        logger.error(f"Error occured getting marriages. Error {str(e)}")
    return None
