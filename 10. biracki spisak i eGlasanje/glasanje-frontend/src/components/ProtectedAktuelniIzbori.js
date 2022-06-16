import React from 'react';
import GlasanjeAxiosClient from "./../services/clients/GlasanjeAxiosClient";
import {Card, Col, Container, Row} from "react-bootstrap";
import axios from "axios";
import {Navigate} from "react-router-dom";
import {AuthenticationService} from "../services/AuthenticationService";
import AktuelniIzbori from "./AktuelniIzbori";

const  ProtectedAktuelniIzbori = () => {
    console.log(AuthenticationService.getRole());
    const autentikovan = AuthenticationService.getRole() === "KORISNIK" || AuthenticationService.getRole() === "SLUZBENIK";

    return autentikovan ? <AktuelniIzbori /> : <Navigate to="/" />;
}

export default ProtectedAktuelniIzbori;
