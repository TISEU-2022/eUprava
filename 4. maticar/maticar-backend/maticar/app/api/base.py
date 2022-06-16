from fastapi import APIRouter
import logging

logger = logging.getLogger(__name__)

base_router = APIRouter()


@base_router.get("/", tags=["root"])
async def read_root() -> dict:
    return {
        "msg": "Welcome to DJ ubre's work!",
        "status": "OK"
    }


@base_router.get("/health", tags=["health"])
async def health() -> dict:
    return {
        "msg": "API is working correctly",
        "status": "OK"
    }
