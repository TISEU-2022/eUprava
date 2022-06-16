# Maticar fastAPI application

## Setup instructions

- [Python 3.8](python.org/downloads/)
- [Virtualenv Wrapper](https://virtualenvwrapper.readthedocs.io/en/latest/)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Svi API endpointi koji u swaggeru nemaju katanac sa desne strane su auth endpointi koji su predvidjeni da se gadjaju sa drugih servisa(ne zahtevaju JWT token)

## DODAVANJE U KNJIGU RODJENIH
1. Prvo upisati na api endpoint /api/user/ osnovne podatke iz knjige rodjenih(videti semu u docs-swagger)
2. ZATIM roditelje dodati na /api/user/parents/{id_usera_koji_je_dodat}

This project is using docker for databases and it needs to be installed, also you need docker compose to be able to start docker containers easily.

Here are list of instructions to get you started:

1. Setup python environment:

    Create virtualenv with virtualenv wrapper:

    ```mkvirtualenv <env name> --python=/path/to/python/executable/python3.8.*```

    Install packages needed for the project:

    ```pip install -r requirements/common.txt```

2. Start docker containers:

    For now you only need databases so use this command:
    ```docker-compose build```

    ```docker-compose up -d```

3. Start the project

    ```make migrate```

    Every other time you only need this command:

    ```make run```

    Tests can be run with:
    ```make test```
