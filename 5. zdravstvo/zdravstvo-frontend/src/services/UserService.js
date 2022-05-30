import axios from "axios";

const baseURL = "http://localhost:5001/api/users";

async function addBirthCertificate(birthCertificateRequest) {
  const response = await axios.post(baseURL, birthCertificateRequest);
  return response.data;
}

async function recordDeceasedCitizen(jmbg) {
  const response = await axios.put(baseURL + "/" + jmbg);
  return response.data;
}

async function addParents(req) {
  const response = await axios.post(baseURL, {
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
