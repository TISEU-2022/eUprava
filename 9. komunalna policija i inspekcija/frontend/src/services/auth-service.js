import jwt_decode from "jwt-decode";

class AuthService {
    
    getRolesFromJwt = () => {
        let decodedJwt = this.getDecodedJwt();
        if(decodedJwt == null)
            return null;
        
        return decodedJwt.roles.map(role => role.authority);
    }
    
    getEmailFromJwt = () => {
        let decodedJwt = this.getDecodedJwt();
        if(decodedJwt == null)
            return null;
        
        return decodedJwt.sub
    }
    
    getDecodedJwt = () => {
        let token = localStorage.getItem("accessToken");
        if(token == null)
            return null;
    
        return jwt_decode(token);
    }
    
    setCurrentRoleFromToken = () => {
        console.log("USAO")
        let roles = this.getRolesFromJwt();
        if(roles.includes("ROLE_DOCTOR")) {
            this.setCurrentRole("ROLE_DOCTOR");
            //TODO REPLACE URL TO DOCTOR'S HOME PAGE
            window.location.replace("/clinic/work-calendar");
        }
        else if(roles.includes("ROLE_NURSE")) {
            this.setCurrentRole("ROLE_NURSE");
            //TODO REPLACE URL TO NURSE'S HOME PAGE
            window.location.replace("/clinic/recipes");
        }
        else if(roles.includes("ROLE_CLINIC_CENTRE_ADMINISTRATOR")) {
            this.setCurrentRole("ROLE_CLINIC_CENTRE_ADMINISTRATOR");
            //TODO REPLACE URL TO CA'S HOME PAGE
            window.location.replace("/clinic/users/requests");
        }
        else if(roles.includes("ROLE_CLINIC_ADMINISTRATOR")) {
            this.setCurrentRole("ROLE_CLINIC_ADMINISTRATOR");
            //TODO REPLACE URL TO CCA'S HOME PAGE
            window.location.replace("/clinic/clinic-reports");
        }
        else if(roles.includes("ROLE_PATIENT")) {
            this.setCurrentRole("ROLE_PATIENT");
            //TODO REPLACE URL TO PATIENT'S HOME PAGE
            window.location.replace("/clinic/clinics");
        }
        else {
            console.log("NEMA ROLA")
            localStorage.clear();
            window.location.replace("/login");
        }
    }
    
    getCurrentRole = () => {
        return localStorage.getItem("currentRole");
    }
    
    setCurrentRole = role => {
        localStorage.setItem("currentRole", role);
    }

    logout = () => {
        localStorage.clear();
        window.location.replace("/login");
    }
}

export default new AuthService();