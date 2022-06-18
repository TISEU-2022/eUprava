import { TokenService } from "./TokenService";
import axios from 'axios'
export const AuthenticationService = {
  logout,
  getRole,
  getUsername,
};


function logout() {
  TokenService.removeToken();
  window.location.assign("");
}

function getRole() {
  const token = TokenService.getToken();
  const decoded_token = token ? TokenService.decodeToken(token) : null;
  if (decoded_token) {
    return decoded_token.roles[0];
  } else {
    return null;
  }
}
function getUsername() {
  const token = TokenService.getToken();
  const decoded_token = token ? TokenService.decodeToken(token) : null;
  if (decoded_token) {
    return decoded_token.username
  } else {
    return null;
  }
}

