#!/usr/bin/env bash

python maticar/migrations/manage.py version_control
python maticar/migrations/manage.py upgrade
maticar
