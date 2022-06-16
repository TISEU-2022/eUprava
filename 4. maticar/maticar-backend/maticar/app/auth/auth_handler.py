import logging
import requests
import os

logger = logging.getLogger(__name__)


async def decodeJWT(token: str) -> dict:
    try:
        response = requests.get(os.getenv("AUTH_SERVER_API", "api") + "/auth/verify_token/" + token)
        return response.json()
    except Exception as e:
        logger.error("Error occured durring JWT token decoding. Error {}".format(str(e)))
        return {}
