from uvicorn import run
import os
import logging
import logging.config


log_level = os.getenv("LOG_LEVEL", "DEBUG")
develop = bool('true' == str(os.getenv("DEVELOP", 'false')).lower())

logging_config = {
    'version': 1,
    'disable_exiting_loggers': False,
    'formatters': {
        'default': {
            'format': '[%(asctime)s] %(levelname)s in %(module)s - %(name)s: %(message)s',
        }
    },
    'handlers': {
        'console': {
            'level': log_level,
            'class': 'logging.StreamHandler',
            'formatter': 'default',
        }
    },
    'loggers': {
        "multipart": {
            'level': "ERROR",
            'handlers': ['console'],
            'propagate': False
        },
        'main': {
            'level': log_level,
            'handlers': ['console']
        },
        'maticar': {
            'level': log_level,
            'handlers': ['console']
        },
        'sqlalchemy.engine': {
            'level': "ERROR",
            'handlers': ['console'],
            "propagate": False
        },
        'sqlalchemy': {
            'level': "ERROR",
            'handlers': ['console'],
            "propagate": False
        },
        "uvicorn": {
            'handlers': ["console"],
            'level': "ERROR",
            "propagate": False
        },
        "uvicorn.error":
        {
            'handlers': ["console"],
            'level': "ERROR",
            "propagate": False
        },
        "uvicorn.access": {
            'handlers': ["console"],
            'level': "ERROR",
            "propagate": False
        },
    }
}
logger = logging.getLogger("main")


def dev():
    run("maticar.app.run:app", host="0.0.0.0", port=4002, reload=True, log_level=log_level.lower(), log_config=logging_config, debug=True)


def prod():
    run(
        "maticar.app.run:app",
        host="0.0.0.0",
        port=4002,
        log_level=log_level.lower(),
        log_config=logging_config,
        debug=develop,
        forwarded_allow_ips="*"
    )


if __name__ == "__main__":
    dev()
