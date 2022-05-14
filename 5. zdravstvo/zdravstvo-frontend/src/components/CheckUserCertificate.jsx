import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import Swal from "sweetalert2";
import { CertificateService } from "../services/CertificateService";

const CheckUserCertificate = () => {
  const [jmbg, setJmbg] = useState("");

  const handleJmbgChange = (e) => {
    setJmbg(e.target.value);
  };

  async function checkIfUserHasCertificate() {
    await CertificateService.checkIfUserHasCertificate(jmbg)
      .then((response) => {
        if (response.userHasCertificate) {
          Swal.fire({
            icon: "success",
            title: "Izabrani korisnik ima lekarsko uverenje",
          });
        } else {
          Swal.fire({
            icon: "error",
            title: "Izabrani korisnik nema lekarsko uverenje",
          });
        }
        setJmbg("");
      })
      .catch((error) => {
        Swal.fire({
          icon: "error",
          title: "Neispravan JMBG! Pokušajte ponovo",
        });
      });
  }

  return (
    <>
      <div className="request-certificate">
        <h4>Provera lekarskog uverenja za građanina</h4>
        <Form action="./upload?${_csrf.parameterName}=${_csrf.token}">
          <Form.Group>
            <Form.Label>JMBG:</Form.Label>
            <Form.Control
              onChange={handleJmbgChange}
              name="identificationNumber"
              value={jmbg}
              as="input"
            />
          </Form.Group>

          <br />

          <Button
            className="btn btn-primary"
            onClick={() => checkIfUserHasCertificate()}
          >
            Unesi
          </Button>
        </Form>
      </div>
    </>
  );
};

export default CheckUserCertificate;
