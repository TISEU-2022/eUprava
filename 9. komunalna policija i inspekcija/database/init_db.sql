-- MariaDB dump 10.19  Distrib 10.6.5-MariaDB, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: euprava-komunalna
-- ------------------------------------------------------
-- Server version	10.6.5-MariaDB

CREATE DATABASE IF NOT EXISTS eupravakomunalna;
USE eupravakomunalna;

create table if not exists eupravakomunalna.podnosilac
(
    id               bigint auto_increment
        primary key,
    adresa        varchar(255) null,
    broj_telefona varchar(255) null,
    email         varchar(255) null,
    ime           varchar(255) null,
    jmbg          varchar(255) null,
    mesto         varchar(255) null,
    prezime       varchar(255) null,
    ptt_broj      int          not null,
    constraint UK_6kof1amxuhh2l6ftvg3qx8oof
        unique (email),
    constraint UK_7d5p69c7frwd5mnocytcsdb1h
        unique (broj_telefona),
    constraint UK_er6ejbb2vgx56kasx6vdo7quf
        unique (jmbg)
);

create table if not exists eupravakomunalna.sluzbenik
(
    id               bigint auto_increment
        primary key,
    email   varchar(255) not null,
    ime     varchar(255) null,
    jmbg    varchar(255) not null,
    prezime varchar(255) null
);

create table if not exists eupravakomunalna.izvestaj
(
    id               bigint auto_increment
        primary key,
    izvestaj         varchar(255) null,
    prihvaceno       bit          null,
    vreme_podnosenja datetime     null,
    sluzbenik_id     bigint  null,
    constraint FKd561hwgawukmij5nqvv5juxx4
        foreign key (sluzbenik_id) references eupravakomunalna.sluzbenik (id)
);

create table if not exists eupravakomunalna.vrsta_komunalnog_problema
(
    id    bigint auto_increment
        primary key,
    naziv varchar(255) null
);

create table if not exists eupravakomunalna.vrsta_predstavke
(
    id    bigint auto_increment
        primary key,
    naziv varchar(255) not null
);

create table if not exists eupravakomunalna.komunalni_problem
(
    id                           bigint auto_increment
        primary key,
    adresa_dogadjaja             varchar(255) null,
    datum_dogadjaja              datetime     null,
    datum_podnosenja             datetime     null,
    mesto_dogadjaja              varchar(255) null,
    opis                         varchar(255) null,
    izvestaj_id                  bigint       null,
    podnosilac_id                bigint  null,
    vrsta_komunalnog_problema_id bigint       null,
    constraint FK2ny8nubq6hn7p6jdsv7is5x57
        foreign key (izvestaj_id) references eupravakomunalna.izvestaj (id),
    constraint FK834mjsfnea43dugupt1muyfje
        foreign key (vrsta_komunalnog_problema_id) references eupravakomunalna.vrsta_komunalnog_problema (id),
    constraint FKkjyx2si1wqp2gg95bw3aka1ek
        foreign key (podnosilac_id) references eupravakomunalna.podnosilac (id)
);

create table if not exists eupravakomunalna.komunalni_problem_putanje_do_datoteka
(
    komunalni_problem_id bigint       not null,
    putanje_do_datoteka  varchar(255) null,
    constraint FKgyyooabekvmrqurepr69b6cjw
        foreign key (komunalni_problem_id) references eupravakomunalna.komunalni_problem (id)
);

create table if not exists eupravakomunalna.predstavka
(
    id                  bigint auto_increment
        primary key,
    adresa_dogadjaja    varchar(255) not null,
    datum_dogadjaja     datetime     null,
    mesto_dogadjaja     varchar(255) not null,
    naslov              varchar(255) not null,
    opis                varchar(255) not null,
    vreme_podnosenja    datetime     not null,
    izvestaj_id         bigint       null,
    vrsta_predstavke_id bigint       null,
    constraint FK22mvqcsamu7ennccrwvv17d8y
        foreign key (izvestaj_id) references eupravakomunalna.izvestaj (id),
    constraint FKrf2g67frhv50imiyobfik65ql
        foreign key (vrsta_predstavke_id) references eupravakomunalna.vrsta_predstavke (id)
);

create table if not exists eupravakomunalna.predstavka_putanje_do_datoteka
(
    predstavka_id       bigint       not null,
    putanje_do_datoteka varchar(255) null,
    constraint FKr9lmlmuajxrd04vfbq5xl6moe
        foreign key (predstavka_id) references eupravakomunalna.predstavka (id)
);

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-20 16:07:29
