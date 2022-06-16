import {TokenService} from "./TokenService";
import GlasanjeAxiosClient from "./clients/GlasanjeAxiosClient";
import Swal from "sweetalert2";
import axios from "axios";

export const AuthenticationService = {
    login,
    logout,
    getRole
};

async function login(userCredentials) {
    try {
        const response = await GlasanjeAxiosClient.post(
            "http://localhost:10002/korisnici/prijava",
            userCredentials
        );

        if (response.status === 200) {
            localStorage.setItem("id", response.data.id);
            window.location.assign("/AktuelniIzbori");
        } else {
            await Swal.fire({
                icon: 'error',
                title: 'Ох не 😩',
                text: 'Негде сте погрешили 😕',
            })
        }

        const decoded_token = TokenService.decodeToken(response.data);
        if (decoded_token) {
            TokenService.setToken(response.data);

            window.location.assign("/AktuelniIzbori");
        } else {
            console.error("Invalid token");
        }
    } catch (error) {
        await Swal.fire({
            icon: 'error',
            title: 'Ох не 😩',
            text: 'Негде сте погрешили 😕',
        })
    }
}

function logout() {
    TokenService.removeToken();
    localStorage.removeItem("id");
    window.location.assign("/prijava");
}

function getRole() {
    const token = TokenService.getToken();
    const decoded_token = token ? TokenService.decodeToken(token) : null;
    if (decoded_token) {
        return decoded_token.role.authority;
    } else {
        return null;
    }
}