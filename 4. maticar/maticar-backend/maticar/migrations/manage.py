#!/usr/bin/env python
import os
from migrate.versioning.shell import main

if __name__ == '__main__':
    url = 'mysql+pymysql://{}:{}@{}:{}/{}'.format(
        os.getenv("MYSQL_USER"),
        os.getenv("MYSQL_PASSWORD"),
        os.getenv("MYSQL_HOST"),
        os.getenv("MYSQL_PORT"),
        os.getenv("MYSQL_DB"),
    )
    main(repository='./maticar/migrations', url=url, debug='True')
