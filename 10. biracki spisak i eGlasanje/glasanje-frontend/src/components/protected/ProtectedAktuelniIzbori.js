import React from 'react';
import {Navigate} from "react-router-dom";
import {AuthenticationService} from "../../services/AuthenticationService";
import AktuelniIzbori from "../AktuelniIzbori";

const  ProtectedAktuelniIzbori = () => {
    const autentikovan = AuthenticationService.getRole() === "KORISNIK" || AuthenticationService.getRole() === "SLUZBENIK";
    return autentikovan ? <AktuelniIzbori /> : <Navigate to="/" />;
}

export default ProtectedAktuelniIzbori;
