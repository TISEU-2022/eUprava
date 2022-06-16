"""A setuptools based setup module.
Authoritative references:
https://packaging.python.org/en/latest/distributing.html
https://github.com/pypa/sampleproject
"""

from setuptools import setup, find_packages
from os import path, getenv

# Get the long description from the README file
here = path.abspath(path.dirname(__file__))
with open(path.join(here, "README.md")) as f:
    long_description = f.read()

setup(
    name="Maticar",
    version=getenv("APP_VERSION", "0.0.0"),

    description="Maticar backend",
    long_description=long_description,
    url="",

    packages=find_packages(exclude=["doc"]),

    # source code layout
    namespace_packages=["maticar"],

    # Generating the command-line tool
    entry_points={
        "console_scripts": [
            "maticar=maticar.run:prod"
        ]
    },

    # author and license
    author="Ivan Djuraki",
    author_email="ivandjuraki@protonmail.com",
    # license="",

    # dependencies, a list of rules
    install_requires=["overrides>=1.8"],
    # add links to repositories if modules are not on pypi
    dependency_links=[
    ],

    #  PyTest integration
    setup_requires=["pytest-runner"],
    tests_require=["pytest", "mock"]
)
