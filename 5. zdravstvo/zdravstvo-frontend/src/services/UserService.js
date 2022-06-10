import axios from "axios";
import AxiosClient from "./AxiosClient";

const baseURL = "http://localhost:5001/api/users";

async function addBirthCertificate(birthCertificateRequest) {
  const response = await AxiosClient.post(baseURL, birthCertificateRequest);
  return response.data;
}

async function recordDeceasedCitizen(jmbg) {
  return await AxiosClient.put(baseURL + "/" + jmbg);
}

async function addParents(req) {
  const response = await AxiosClient.post(baseURL, {
    parent1Id: req.parent1Id,
    parent2Id: req.parent2Id,
  });
  return response.data;
}
export const UserService = {
  addBirthCertificate,
  recordDeceasedCitizen,
  addParents,
};
