import jwt_decode from "jwt-decode";

class AuthService {
    
    getRoleFromJwt = () => {
        let decodedJwt = this.getDecodedJwt();
        if(decodedJwt == null)
            return null;

        return decodedJwt.roles[0];
    }
    
    getDecodedJwt = () => {
        let token = localStorage.getItem("token");
        if(token == null)
            return null;
    
        return jwt_decode(token);
    }

    isSluzbenik = () => {
        return this.getRoleFromJwt() === "ROLE_SLUZBENIK";
    }

    isPodnosilac = () => {
        return this.getRoleFromJwt() === "ROLE_PODNOSILAC";
    }

    isLoggedIn = () => {
        return this.getDecodedJwt() !== null;
    }

    login = (token) => {
        localStorage.setItem("token", token);
        window.location.replace("/dobro-dosli");
    }

    logout = () => {
        localStorage.clear();
        window.location.replace("/dobro-dosli");
    }
}

export const authServiceLogin = "http://localhost:4101/auth/login?successUrl=http://localhost:9001/login";
export const authServiceLogout = "http://localhost:4101/auth/logout?successUrl=http://localhost:9001/logout";
export default new AuthService();