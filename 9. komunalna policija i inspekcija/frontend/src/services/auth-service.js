import jwt_decode from "jwt-decode";

class AuthService {
    
    getRoleFromJwt = () => {
        let decodedJwt = this.getDecodedJwt();
        if(decodedJwt == null)
            return null;
        
        return decodedJwt.roles[0].authority;
    }
    
    getDecodedJwt = () => {
        let token = localStorage.getItem("token");
        if(token == null)
            return null;
    
        return jwt_decode(token);
    }

    logout = () => {
        localStorage.clear();
        window.location.replace("/dobro-dosli");
    }
}

export default new AuthService();