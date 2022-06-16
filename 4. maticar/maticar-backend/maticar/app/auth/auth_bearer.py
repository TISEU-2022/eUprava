from fastapi import Request, Response, HTTPException
from fastapi.security import HTTPBearer
import logging

from .auth_handler import decodeJWT

logger = logging.getLogger(__name__)


class JWTBearer(HTTPBearer):

    payload = None

    permission_routes = {
        "maticar_administrator": [
            {"level": 2, "path": "admin"},
        ],
        "maticar_user": [
            {"level": 2, "path": "user"},
        ],
        "maticar_worker": [
            {"level": 2, "path": "worker"},
        ]
    }

    def __init__(self, auto_error: bool = True):
        super(JWTBearer, self).__init__(auto_error=auto_error)

    async def __call__(self, request: Request, response: Response):
        try:
            credentials = {"user": None, "token": None}
            token = request.headers['Authorization'][7:]
            if token:
                response = await decodeJWT(token)
                if response.get("status") != 401:
                    credentials['user'] = response['user']
                    credentials['token'] = token
                    if not self.check_permissions(credentials['user']['roles'], request.url.path):
                        logger.warning("Trying to access {} without premisssions. Permissions {}".format(request.url.path, credentials['user']['roles']))
                        raise HTTPException(status_code=403, detail="Not authorized to access this section.")

                    request.credentials = credentials
                    return credentials
            raise HTTPException(status_code=401, detail="Invalid token or expired token.")
        except Exception:
            raise HTTPException(status_code=403, detail="Invalid token.")

    def check_permissions(self, premissions, path):
        required = self.get_required_permission(path.split("/"))
        if not required or required in premissions:
            return True
        return False

    def get_required_permission(self, split_url):
        for perm, routes in self.permission_routes.items():
            for route in routes:
                if route['path'] == split_url[route['level']]:
                    if route.get('more', None):
                        for sub_route in route['more']:
                            if len(split_url) > sub_route['level'] and sub_route['path'] == split_url[sub_route['level']]:
                                return perm
                    else:
                        return perm
        return None
