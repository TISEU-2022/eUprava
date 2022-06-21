import React from 'react';
import {Navigate} from "react-router-dom";
import {AuthenticationService} from "../../services/AuthenticationService";
import AktuelniIzbori from "../AktuelniIzbori";
import ZavrseniIzbori from "../ZavrseniIzbori";

const  ProtectedZavrseniIzbori = () => {
    const autentikovan = AuthenticationService.getRole() === "KORISNIK" || AuthenticationService.getRole() === "SLUZBENIK";
    return autentikovan ? <ZavrseniIzbori /> : <Navigate to="/" />;
}

export default ProtectedZavrseniIzbori;
