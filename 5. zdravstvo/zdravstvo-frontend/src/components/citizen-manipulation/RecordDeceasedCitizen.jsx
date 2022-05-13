import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import Swal from "sweetalert2";
import { UserService } from "../../services/UserService";

const RecordDeceasedCitizen = () => {
  const [jmbg, setJmbg] = useState("");

  const handleJmbgChange = (e) => {
    setJmbg(e.target.value);
  };

  async function sendDeceasedCitizenRequest() {
    await UserService.recordDeceasedCitizen(jmbg);
    setJmbg("");
    Swal.fire({
      icon: "success",
      title: "Uspešno sačuvane informacije!",
    });
  }

  return (
    <>
      <div className="record-deceased-citizen">
        <h4>Unos informacija o smrtnom slučaju</h4>
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
            onClick={() => sendDeceasedCitizenRequest()}
          >
            Unesi
          </Button>
        </Form>
      </div>
    </>
  );
};

export default RecordDeceasedCitizen;
