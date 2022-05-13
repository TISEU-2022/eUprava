import axios from "axios";

const baseURL = "http://localhost:8080/api/users";

async function addBirthCertificate(birthCertificateRequest) {
  const response = await axios.post(baseURL, birthCertificateRequest);
  return response.data;
}

async function recordDeceasedCitizen(jmbg) {
  const response = await axios.put(baseURL + "/" + jmbg);
  return response.data;
}

export const UserService = {
  addBirthCertificate,
  recordDeceasedCitizen,
};
