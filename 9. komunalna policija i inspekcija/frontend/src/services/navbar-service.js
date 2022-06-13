import authService from "./auth-service";

class NavbarService {

    sluzbenkLinks = [
        { url: "/predstavke", text: "Predstavke"},
        { url: "/vrste-predstavki", text: "Vrste predstavki"},
        { url: "/komunalni-problemi", text: "Komunalni problemi"},
        { url: "/vrste-komunalnih-problema", text: "Vrste komunalnih problema"}
    ]

    podnosilacLinks = [
        { url: "/predstavke", text: "Predstavke"},
        { url: "/komunalni-problemi", text: "Komunalni problemi"},
    ]

    getAllowedNavbarLinks = () => {
        const role = authService.getRoleFromJwt();
        if(role === "ROLE_SLUZBENIK")
            return this.sluzbenkLinks;
        else if(role === "ROLE_PODNOSILAC")
            return this.podnosilacLinks;
        return [];
    }
}

export default new NavbarService();