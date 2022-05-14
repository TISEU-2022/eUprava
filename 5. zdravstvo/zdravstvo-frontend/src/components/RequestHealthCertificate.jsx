import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import Swal from "sweetalert2";
import { CertificateService } from "../services/CertificateService";

const RequestHealthCertificate = () => {
  const [info, setInfo] = useState({
    identificationNumber: "",
    firstName: "",
    lastName: "",
    purpose: "",
  });

  const handleFormInputChange = (name) => (event) => {
    const val = event.target.value;
    setInfo({ ...info, [name]: val });
  };

  async function sendCertificateRequest() {
    await CertificateService.requestCertificate(info);
    Swal.fire({
      icon: "success",
      title: "Uspešno poslat zahtev za lekarsko uverenje!",
    });
    setInfo({
      jmbg: "",
      firstName: "",
      lastName: "",
      gender: "",
      purpose: "",
    });
  }

  return (
    <>
      <div className="request-certificate">
        <h4>Popunjavanje forme za izdavanje lekarskog uverenja</h4>
        <Form action="./upload?${_csrf.parameterName}=${_csrf.token}">
          <Form.Group>
            <Form.Label>JMBG:</Form.Label>
            <Form.Control
              onChange={handleFormInputChange("jmbg")}
              name="jmbg"
              value={info.jmbg}
              as="input"
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>Ime:</Form.Label>
            <Form.Control
              onChange={handleFormInputChange("firstName")}
              name="firstName"
              value={info.firstName}
              as="input"
            />
          </Form.Group>
          <Form.Group>
            <Form.Label>Prezime:</Form.Label>
            <Form.Control
              onChange={handleFormInputChange("lastName")}
              name="lastName"
              value={info.lastName}
              as="input"
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Svrha:</Form.Label>
            <Form.Control
              onChange={handleFormInputChange("purpose")}
              name="purpose"
              value={info.purpose}
              as="input"
            />
          </Form.Group>

          <br />
          <br />

          <Button
            className="btn btn-primary"
            onClick={() => sendCertificateRequest()}
          >
            Pošalji zahtev
          </Button>
        </Form>
      </div>
    </>
  );
};

export default RequestHealthCertificate;
