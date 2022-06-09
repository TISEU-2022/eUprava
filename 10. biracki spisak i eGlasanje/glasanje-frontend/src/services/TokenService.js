import jwtDecode from "jwt-decode";

export const TokenService = {
    getToken,
    setToken,
    removeToken,
    decodeToken,
    didTokenExpire,
};

function getToken() {
    return localStorage.getItem("token");
}

function setToken(value) {
    localStorage.setItem("token", value);
}

function removeToken() {
    localStorage.removeItem("token");
}

function decodeToken(token) {
    try {
        return jwtDecode(token);
    } catch (error) {
        return null;
    }
}

function didTokenExpire() {
    const token = getToken();
    const decodedToken = token ? decodeToken(token) : null;
    return decodedToken ? decodedToken.exp_date < Date.now() : null;
}
