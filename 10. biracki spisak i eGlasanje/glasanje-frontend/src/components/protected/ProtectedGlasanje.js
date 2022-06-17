import React from 'react';
import {Navigate} from "react-router-dom";
import {AuthenticationService} from "../../services/AuthenticationService";
import Glasanje from "../Glasanje";

const  ProtectedGlasanje = () => {
    const autentikovan = AuthenticationService.getRole() === "KORISNIK" || AuthenticationService.getRole() === "SLUZBENIK";
    return autentikovan ? <Glasanje /> : <Navigate to="/" />;
}

export default ProtectedGlasanje;
