import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import React from "react";
import Swal from "sweetalert2";
import { UserService } from "../../services/UserService";

const AddBirthCertificate = () => {
  const [info, setInfo] = useState({
    identificationNumber: "",
    firstName: "",
    lastName: "",
    gender: "",
    dateOfBirth: new Date(),
    countryOfBirth: "",
    citizenship: "",
    parent1Id: "",
    parent2Id: "",
  });

  const [birthResponse, setBirthResponse] = useState("");
  const [parentsResponse, setParentsResponse] = useState("");

  const handleFormInputChange = (name) => (event) => {
    const val = event.target.value;
    setInfo({ ...info, [name]: val });
  };

  const handleDateOfBirthChange = (e) => {
    setInfo({ ...info, dateOfBirth: e.target.value });
  };

  const handleGenderChange = (value) => {
    setInfo({ ...info, gender: value });
  };

  async function sendBirthCertificateRequest() {
    if (infoValid()) {
      await UserService.addBirthCertificate(info)
        .then((response) => {
          setBirthResponse(response.message);
          Swal.fire({
            icon: "success",
            title: "Uspešno sačuvane informacije!",
          });
        })
        .catch((error) => {
          Swal.fire({
            icon: "error",
            title:
              "Neispravno unete informacije! Proverite podatke i pokušajte opet",
          });
        });
    }
  }

  const infoValid = () => {
    if (
      info.identificationNumber === "" ||
      info.firstName === "" ||
      info.lastName === "" ||
      info.gender === "" ||
      info.countryOfBirth === "" ||
      info.citizenship === ""
    ) {
      console.log(info.identificationNumber);
      Swal.fire({
        icon: "error",
        title: "Sva polja sa informacijama o detetu su obavezna!",
      });
      return false;
    } else if (info.identificationNumber.length !== 13) {
      Swal.fire({
        icon: "error",
        title: "JMBG se mora sastojati od 13 cifara!",
      });
      return false;
    }
    return true;
  };

  const parentJmbgValid = () => {
    if (
      (info.parent1Id !== "" && info.parent1Id.length !== 13) ||
      (info.parent2Id !== "" && info.parent2Id.length !== 13)
    ) {
      Swal.fire({
        icon: "error",
        title: "JMBG se mora sastojati od 13 cifara!",
      });
      return false;
    }
    return true;
  };

  async function sendParents() {
    if (parentJmbgValid()) {
      await UserService.addParents(info)
        .then((response) => {
          setParentsResponse(response.message);
          Swal.fire({
            icon: "success",
            title: "Uspešno sačuvane informacije o roditelju!",
          });
          setInfo({
            identificationNumber: "",
            firstName: "",
            lastName: "",
            gender: "",
            dateOfBirth: new Date(),
            countryOfBirth: "",
            citizenship: "",
            parent1Id: "",
            parent2Id: "",
          });
        })
        .catch((error) => {
          Swal.fire({
            icon: "error",
            title: "Neispravno uneti JMBG! Proverite podatke i pokušajte opet",
          });
        });
    }
  }

  return (
    <>
      <div className="add-birth-certificate">
        <h4>Unos informacija o novorođenčetu</h4>
        <Form action="./upload?${_csrf.parameterName}=${_csrf.token}">
          <Form.Group>
            <Form.Label>JMBG:</Form.Label>
            <Form.Control
              onChange={handleFormInputChange("identificationNumber")}
              name="identificationNumber"
              value={info.identificationNumber}
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
            <Form.Label>Prezime</Form.Label>
            <Form.Control
              onChange={handleFormInputChange("lastName")}
              name="lastName"
              value={info.lastName}
              as="input"
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Pol</Form.Label>
            <Form.Select aria-label="Default select example">
              <option>Izaberite pol</option>
              <option value="male" onClick={() => handleGenderChange("male")}>
                muški
              </option>
              <option
                value="female"
                onClick={() => handleGenderChange("female")}
              >
                ženski
              </option>
            </Form.Select>
          </Form.Group>

          <Form.Group>
            <Form.Label>Datum rođenja</Form.Label>
            <Form.Control
              type="date"
              name="dateOfBirth"
              placeholder="Datum rođenja"
              value={info.dateOfBirth}
              onChange={handleDateOfBirthChange}
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Mesto rođenja</Form.Label>
            <Form.Control
              onChange={handleFormInputChange("countryOfBirth")}
              name="countryOfBirth"
              value={info.countryOfBirth}
              as="input"
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>Državljanstvo</Form.Label>
            <Form.Control
              onChange={handleFormInputChange("citizenship")}
              name="citizenship"
              value={info.citizenship}
              as="input"
            />
          </Form.Group>
          <br />
          <Button
            className="btn btn-primary"
            onClick={() => sendBirthCertificateRequest()}
          >
            Dodaj novorođeno lice
          </Button>
          <br />
          <br />

          <Form.Group>
            <Form.Label>JMBG majke</Form.Label>
            <Form.Control
              onChange={handleFormInputChange("parent1Id")}
              name="parent1Id"
              value={info.parent1Id}
              as="input"
            />
          </Form.Group>

          <Form.Group>
            <Form.Label>JMBG oca</Form.Label>
            <Form.Control
              onChange={handleFormInputChange("parent2Id")}
              name="parent2Id"
              value={info.parent2Id}
              as="input"
            />
          </Form.Group>

          <br />
          <Button className="btn btn-primary" onClick={() => sendParents()}>
            Dodaj JMBG roditelja
          </Button>
        </Form>
      </div>
    </>
  );
};

export default AddBirthCertificate;
