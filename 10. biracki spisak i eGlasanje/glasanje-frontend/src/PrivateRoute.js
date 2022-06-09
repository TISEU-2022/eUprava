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
                return <Navigate to={{ pathname: "/login" }} />;
            }

            if (roles && !roles.includes(role)) {
                // Ako je korisnik ulogovan ali nema dozvolu pristupa zaštićenoj stranici - vrati ga na glavnu stranicu
                if(role === 'ROLE_PACIJENT'){
                    return <Navigate to={{ pathname: "/pacijent" }} />;
                }
                else if(role === 'ROLE_MEDICINSKA_SESTRA'){
                    return <Navigate to={{ pathname: "/medicinska-sestra" }} />;
                }
                else if(role === 'ROLE_LEKAR'){
                    return <Navigate to={{ pathname: "/lekar" }} />;
                }
                else if(role === 'ROLE_ADMINISTRATOR_KLINIKE'){
                    return <Navigate to={{ pathname: "/administrator-klinike" }} />;
                }
                else if(role === 'ROLE_ADMINISTRATOR_CENTRA'){
                    return <Navigate to={{ pathname: "/administrator-centra" }} />;
                }
            }

            // Vrati stranicu koja se traži
            return <Component {...props} />;
        }}
    />
);
