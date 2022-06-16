import redis
import os
import json
import logging
import random

logger = logging.getLogger(__name__)


class RedisBase(object):
    """Data structure store

       Redis is used as database, for saving and reading messages
       received from other services.
    """
    _key = ""
    # set default timeout to 1 day
    _cache_timeout = 60*60*24

    def __init__(self):
        self.server = redis.Redis(os.getenv("REDIS_HOST"))

    def _setData(self, key, data):
        if self._cache_timeout:
            self.server.set(key, data, self._cache_timeout)
        else:
            self.server.set(key, data)

    def _getData(self, key):
        return self.server.get(key)

    def _delData(self, key):
        return self.server.delete(key)

    def get(self, key):
        data = self._getData("{}_{}".format(self._key, key))
        try:
            if data:
                data = json.loads(data)
        except Exception as e:
            logger.warn("Stored data is not dictionary. Exception: {}".format(str(e)))
            pass
        return data

    def set(self, key, data):
        if isinstance(data, dict):
            data = json.dumps(data)

        self._setData("{}_{}".format(self._key, key), data)

    def delete(self, key):
        self._delData("{}_{}".format(self._key, key))


class ShortCode(RedisBase):
    _key = "u_short_code"
    _cache_timeout = 60 * 30

    def generate_random_number(self):
        return random.randint(100000, 999999)

    def create(self, data):
        try:
            unique = False
            while not unique:
                short_code = str(self.generate_random_number())
                if not self.get(short_code):
                    unique = True
                    self.set(short_code, data)
            return short_code
        except Exception as e:
            logger.error("Issue creating short code for 2fa. Error {}".format(str(e)))
        return None

    def retrive(self, short_code):
        try:
            data = self.get(short_code)
            if data:
                self.delete(short_code)
            return data
        except Exception as e:
            logger.error("Issuer retriving cache for 2fa. Error {}".format(str(e)))
        return None
