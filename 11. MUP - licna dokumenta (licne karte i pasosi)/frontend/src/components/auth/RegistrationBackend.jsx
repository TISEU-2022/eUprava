import { useEffect, useState } from "react"
import Header from "../navbar/Header"
import axios from 'axios';
import { useJwt } from "react-jwt";
import { useNavigate } from 'react-router-dom';
import Modal from 'react-bootstrap/Modal'
import ModalHeader from 'react-bootstrap/ModalHeader'
import ModalTitle from 'react-bootstrap/ModalTitle'
import ModalBody from 'react-bootstrap/ModalBody'
import ModalFooter from 'react-bootstrap/ModalFooter'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'

export const RegistrationBackend = ({ showModal }) => {

    const navigate = useNavigate();
    const usertoken = localStorage.getItem("token")
    const { decodedToken, isExpired } = useJwt(usertoken);

    const [jmbg, setJmbg] = useState("")
    const [name, setName] = useState("")
    const [lastname, setLastname] = useState("")
    const [dateOfBirth, setDateOfBirth] = useState("")
    const [countryOfBirth, setCountryOfBirth] = useState("")
    const [citizenship, setCitizenship] = useState("")
    const [gender, setGender] = useState("Male")
    const [municipality, setMunicipality] = useState("")

    const [show, setShow] = useState(showModal);
    const [showWarningModal, setShowWarningModal] = useState(false);
    const handleClose = () => setShowWarningModal(false);

    const handleSubmit = () => {
        axios.post("http://localhost:11001/api/appointment/userData", {
            username: decodedToken.username,
            jmbg: jmbg,
            name: name,
            lastname: lastname,
            dateOfBirth: dateOfBirth,
            countryOfBirth: countryOfBirth,
            citizenship: citizenship,
            gender: gender,
            municipality: municipality
        })
            .then((res) => {
                console.log(res)
                if (res.status == 200) {
                    setShow(false)
                }
            })
            .catch((err) => {
                console.log(err)
                if (err.status != 200) {
                    setShowWarningModal(true)
                }
            })
    }


    return (
        <>
            <Modal centered show={showWarningModal} onHide={handleClose}>
                <Modal.Header>
                    <Modal.Title>Error message!</Modal.Title>
                </Modal.Header>
                <Modal.Body>Enter all required information, try again!</Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>

            <Modal show={show}>
                <Modal.Header >
                    <Modal.Title>Fill out the form to continue!</Modal.Title>
                </Modal.Header>
                <Modal.Body>The user with the given username does not exist!</Modal.Body>
                <Modal.Body>
                    <Form.Group className="mb-3" controlId="formJmbg">
                        <Form.Label>JMBG</Form.Label>
                        <Form.Control type="text" placeholder="Enter jmbg" onChange={(e) => setJmbg(e.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formName">
                        <Form.Label>Name</Form.Label>
                        <Form.Control type="text" placeholder="Enter name" onChange={(e) => setName(e.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formLastname">
                        <Form.Label>Lastname</Form.Label>
                        <Form.Control type="text" placeholder="Enter lastname" onChange={(e) => setLastname(e.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formDateOfBirth">
                        <Form.Label>Date of birth</Form.Label>
                        <Form.Control type="date" placeholder="Enter date" onChange={(e) => setDateOfBirth(e.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formCountryOfBirth">
                        <Form.Label>Country of birth</Form.Label>
                        <Form.Control type="text" placeholder="Enter country of birth" onChange={(e) => setCountryOfBirth(e.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formCitizenship">
                        <Form.Label>Citizenship</Form.Label>
                        <Form.Control type="text" placeholder="Enter citizenship" onChange={(e) => setCitizenship(e.target.value)} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formGender">
                        <Form.Label>Gender</Form.Label>
                        <Form.Select aria-label="genderSelect" onChange={(e) => setGender(e.target.value)}>
                            <option value="null">-</option>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                            <option value="Other">Other</option>
                        </Form.Select>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formMunicipality">
                        <Form.Label>Municipality</Form.Label>
                        <Form.Select aria-label="municipalitySelect" onChange={(e) => setMunicipality(e.target.value)}>
                            <option value="null">-</option>
                            <option value="ALEKSANDROVAC">Aleksandrovac</option>
                            <option value="APATIN">Apatin</option>
                            <option value="ARILJE">Arilje</option>
                            <option value="ALEKSINAC">Aleksinac</option>
                            <option value="BABUSNICA">Babusnica</option>
                            <option value="BATOCINA">Batocina</option>
                            <option value="BOGATIC">Bogatic</option>
                            <option value="BUJANOVAC">Bujanovac</option>
                        </Form.Select>
                    </Form.Group>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={handleSubmit}>
                        Submit
                    </Button>
                </Modal.Footer>
            </Modal>

        </>
    )
}

export default RegistrationBackend