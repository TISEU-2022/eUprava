import React from "react";
import authService from "./auth-service";
import {Redirect} from "react-router-dom";
import Auth from "../components/Auth/Auth";
import Page from "../components/Page";


class RouteService {

    authRoutes = [
        { path: "/dobro-dosli", render: <Auth/> },
        { path: "/auth", render: <Auth/> }
    ]

    sluzbenikRoutes = [
        { path: "/dobro-dosli", component: Page}
    ]

    podnosilacRoutes = [
        { path: "/dobro-dosli", component: Page}
    ]


    getAllowedRoutes = () => {
        let role = authService.getRoleFromJwt();

        if(role === "ROLE_SLUZBENIK")
            return this.sluzbenikRoutes;

        if(role === "ROLE_PODNOSILAC")
            return this.podnosilacRoutes;

        return this.authRoutes;
    }

    getRedirect = () => {
        const role = authService.getRoleFromJwt();

        if(role === "ROLE_SLUZBENIK")
            return <Redirect to='/dobro-dosli'/>;

        if(role === "ROLE_PODNOSILAC")
            return <Redirect to='/dobro-dosli'/>;

        return <Redirect to='/dobro-dosli'/>
    }
}

export default new RouteService();