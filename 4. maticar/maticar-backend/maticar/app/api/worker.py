from fastapi import (
    APIRouter,
    Depends,
    Request,
    Body,
    Response
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


@worker_router.post("/marriage", status_code=200)
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


@worker_router.put("/marriage", status_code=200)
async def divorce_marriage(
    marriage_id: str,
    db: Session = Depends(m_api.get_db)
):
    try:
        result = await mm_mng.WorkerManager().divorce_marriage(marriage_id)
        return result
    except Exception as e:
        logger.error(f"Error occured getting creating marriage. Error {str(e)}")
    return None


@worker_router.get("/marriage", response_model=List[MarriageGetSchema], status_code=200)
async def get_marriages(
    db: Session = Depends(m_api.get_db)
):
    try:
        result = await mm_mng.WorkerManager().get_marriages()
        return result
    except Exception as e:
        logger.error(f"Error occured getting marriages. Error {str(e)}")
    return None
