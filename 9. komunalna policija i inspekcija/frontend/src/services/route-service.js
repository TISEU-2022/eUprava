import React from "react";
import authService from "./auth-service";
import {Redirect} from "react-router-dom";
import Login from "../components/Auth/Login";
import WelcomePage from "../pages/WelcomePage";
import VrstePredstavkiFormPage from "../pages/VrstePredstavkiFormPage";
import VrstePredstavkiDetailsPage from "../pages/VrstePredstavkiDetailsPage";
import VrstePredstavkiPage from "../pages/VrstePredstavkiPage";
import VrsteKomunalnihProblemaFormPage from "../pages/VrsteKomunalnihProblemaFormPage";
import VrsteKomunalnihProblemaDetailsPage from "../pages/VrsteKomunalnihProblemaDetailsPage";
import VrsteKomunalnihProblemaPage from "../pages/VrsteKomunalnihProblemaPage";
import PredstavkeFormPage from "../pages/PredstavkeFormPage";
import PredstavkeDetailsPage from "../pages/PredstavkeDetailsPage";
import PredstavkePage from "../pages/PredstavkePage";
import KomunalniProblemiFormPage from "../pages/KomunalniProblemiFormPage";
import KomunalniProblemiDetailsPage from "../pages/KomunalniProblemiDetailsPage";
import KomunalniProblemiPage from "../pages/KomunalniProblemiPage";
import Logout from "../components/Auth/Logout";


class RouteService {

    authRoutes = [
        { path: "/dobro-dosli", component: WelcomePage },
        { path: "/login", component: Login },
    ]

    sluzbenikRoutes = [
        { path: "/vrste-predstavki/form/:id", render: <VrstePredstavkiFormPage edit/>},
        { path: "/vrste-predstavki/form", component: VrstePredstavkiFormPage },
        { path: "/vrste-predstavki/:id", component: VrstePredstavkiDetailsPage },
        { path: "/vrste-predstavki", component: VrstePredstavkiPage },
        { path: "/predstavke/:id", component: PredstavkeDetailsPage },
        { path: "/predstavke", component: PredstavkePage },

        { path: "/vrste-komunalnih-problema/form/:id", render: <VrsteKomunalnihProblemaFormPage edit/>},
        { path: "/vrste-komunalnih-problema/form", component: VrsteKomunalnihProblemaFormPage },
        { path: "/vrste-komunalnih-problema/:id", component: VrsteKomunalnihProblemaDetailsPage },
        { path: "/vrste-komunalnih-problema", component: VrsteKomunalnihProblemaPage },
        { path: "/komunalni-problemi/form", component: KomunalniProblemiPage },
        { path: "/komunalni-problemi/:id", component: KomunalniProblemiDetailsPage },
        { path: "/komunalni-problemi", component: KomunalniProblemiPage },

        { path: "/logout", component: Logout },
    ]

    podnosilacRoutes = [
        { path: "/predstavke/form", component: PredstavkeFormPage },
        { path: "/predstavke/:id", component: PredstavkeDetailsPage },
        { path: "/predstavke", component: PredstavkePage },

        { path: "/komunalni-problemi/form", component: KomunalniProblemiFormPage },
        { path: "/komunalni-problemi/:id", component: KomunalniProblemiDetailsPage },
        { path: "/komunalni-problemi", component: KomunalniProblemiPage },

        { path: "/logout", component: Logout },
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
            return <Redirect to='/komunalni-problemi'/>;

        if(role === "ROLE_PODNOSILAC")
            return <Redirect to='/komunalni-problemi'/>;

        return <Redirect to='/dobro-dosli'/>
    }
}

export default new RouteService();