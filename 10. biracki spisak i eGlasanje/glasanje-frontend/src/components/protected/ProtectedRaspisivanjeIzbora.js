import React from 'react';
import {Navigate} from "react-router-dom";
import {AuthenticationService} from "../../services/AuthenticationService";
import RaspisivanjeIzbora from "../RaspisivanjeIzbora";

const  ProtectedRaspisivanjeIzbora = () => {
    const autentikovan = AuthenticationService.getRole() === "KORISNIK" || AuthenticationService.getRole() === "SLUZBENIK";
    return autentikovan ? <RaspisivanjeIzbora /> : <Navigate to="/" />;
}

export default ProtectedRaspisivanjeIzbora;
