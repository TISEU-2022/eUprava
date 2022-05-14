import axios from "axios";

const baseURL = "http://localhost:5001/api/medical-certificates";

async function requestCertificate(certificateRequest) {
  const response = await axios.post(baseURL, certificateRequest);
  return response.data;
}

async function checkIfUserHasCertificate(jmbg) {
  const response = await axios.put(baseURL + "/" + jmbg);
  return response.data;
}

export const CertificateService = {
  requestCertificate,
  checkIfUserHasCertificate,
};
