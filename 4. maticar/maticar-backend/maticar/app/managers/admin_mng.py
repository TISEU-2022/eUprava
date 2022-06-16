from maticar.app import models as m_mng
import requests
import os
import logging

logger = logging.getLogger(__name__)


class AdminManager(object):
    def __init__(self):
        self.db = next(m_mng.get_db())

    async def get_workers(self):
        try:
            return requests.get(os.getenv('AUTH_SERVER_API') + "/user/find/role/maticar_worker")
        except Exception as e:
            logger.error("Error ocured getting workers. Error {}".format(str(e)))
        return None

    async def add_worker(self, worker):
        try:
            return requests.post(
                os.getenv('AUTH_SERVER_API') + "/user",
                data={
                    "username": worker.username,
                    "firstName": worker.first_name,
                    "lastName": worker.last_name,
                    "password": worker.password,
                    "identityNumber": worker.identity_number,
                    "roles": ["maticar_worker"]
                })
        except Exception as e:
            logger.error("Error ocured getting workers. Error {}".format(str(e)))
        return None

    async def update_worker(self, worker_id, worker):
        try:
            data = {
                    "username": worker.username,
                    "firstName": worker.first_name,
                    "lastName": worker.last_name,
                    "identityNumber": worker.identity_number,
                    "roles": ["maticar_worker"]
                }

            if worker.password:
                data["password"] = worker.password

            return requests.put(
                os.getenv('AUTH_SERVER_API') + "/user/" + worker_id,
                data=data)
        except Exception as e:
            logger.error("Error ocured getting workers. Error {}".format(str(e)))
        return None

    async def delete_worker(self, worker_id):
        try:
            return requests.delete(os.getenv('AUTH_SERVER_API') + "/user/" + worker_id)
        except Exception as e:
            logger.error("Error ocured getting workers. Error {}".format(str(e)))
        return None
