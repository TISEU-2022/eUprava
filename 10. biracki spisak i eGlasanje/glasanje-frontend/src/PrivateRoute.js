import React from "react";
import { Navigate, Route } from "react-router-dom";
import {AuthenticationService} from "./services/AuthenticationService";

export const PrivateRoute = ({ component: Component, roles, ...rest }) => (
    // Instanciraj rutu sa svim njenim elementima (...rest) ali uz dodatnu proveru autorizacije

    <Route
        {...rest}
        render={(props) => {

            const role = AuthenticationService.getRole();
            if (!role) {
                // Korisnik nije ulogovan a pokušava da pristup zaštićenoj stranici - vrati ga na login
                return <Navigate to={{ pathname: "/prijava" }} />;
            }

            if (roles && !roles.includes(role)) {
                // Ako je korisnik ulogovan ali nema dozvolu pristupa zaštićenoj stranici - vrati ga na glavnu stranicu
                if (role === 'ROLE_GRADJANIN') {
                    return <Navigate to={{ pathname: "/aktuelniIzbori" }} />;
                }
            }

            // Vrati stranicu koja se traži
            return <Component {...props} />;
        }}
    />
);
