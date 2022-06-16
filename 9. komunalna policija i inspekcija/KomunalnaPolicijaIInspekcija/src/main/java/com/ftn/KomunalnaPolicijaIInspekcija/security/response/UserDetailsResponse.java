package com.ftn.KomunalnaPolicijaIInspekcija.security.response;

public class UserDetailsResponse {

    private UserData user;
    private ClaimsData claims;

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public ClaimsData getClaims() {
        return claims;
    }

    public void setClaims(ClaimsData claims) {
        this.claims = claims;
    }
}
