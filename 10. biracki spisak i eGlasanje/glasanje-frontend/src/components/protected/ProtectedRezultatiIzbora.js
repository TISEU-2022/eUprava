import React from 'react';
import {Navigate} from "react-router-dom";
import {AuthenticationService} from "../../services/AuthenticationService";
import AktuelniIzbori from "../AktuelniIzbori";
import ZavrseniIzbori from "../ZavrseniIzbori";
import RezultatiIzbora from "../RezultatiIzbora";

const  ProtectedRezultatiIzbora = () => {
    const autentikovan = AuthenticationService.getRole() === "KORISNIK" || AuthenticationService.getRole() === "SLUZBENIK";
    return autentikovan ? <RezultatiIzbora /> : <Navigate to="/" />;
}

export default ProtectedRezultatiIzbora;
