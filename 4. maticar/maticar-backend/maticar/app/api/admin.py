from fastapi import (
    APIRouter,
    Depends,
    Request,
    Body,
    Response
)
from maticar.app import models as m_api
from maticar.app import managers as mm_mng
from sqlalchemy.orm import Session
from maticar.app.auth import (
    JWTBearer,
)
from maticar.app.schemas import WorkerAddSchema, WorkerUpdateSchema
import logging

logger = logging.getLogger(__name__)

admin_router = APIRouter(
    prefix="/admin",
    tags=["admin"],
    responses={404: {"msg": "Not Found"}}
)


@admin_router.get("/workers", status_code=200, dependencies=[Depends(JWTBearer())])
async def get_workers(
    request: Request,
    db: Session = Depends(m_api.get_db)
):
    try:
        result = await mm_mng.AdminManager().get_workers()
        return Response(status_code=result.status_code, content=result.content)
    except Exception as e:
        logger.error(f"Error occured getting workers API. Error {str(e)}")
    return None


@admin_router.post("/workers", status_code=201, dependencies=[Depends(JWTBearer())])
async def add_worker(
    worker: WorkerAddSchema = Body(...),
    db: Session = Depends(m_api.get_db)
):
    try:
        result = await mm_mng.AdminManager().add_worker(worker)
        print(result.content)
        return Response(status_code=result.status_code, content=result.content)
    except Exception as e:
        logger.error(f"Error occured deleting workers API. Error {str(e)}")


@admin_router.put("/workers/{worker_id:str}", status_code=201, dependencies=[Depends(JWTBearer())])
async def edit_worker(
    worker_id: str,
    worker: WorkerUpdateSchema = Body(...),
    db: Session = Depends(m_api.get_db)
):
    try:
        result = await mm_mng.AdminManager().update_worker(worker_id, worker)
        return Response(status_code=result.status_code, content=result.content)
    except Exception as e:
        logger.error(f"Error occured deleting workers API. Error {str(e)}")
