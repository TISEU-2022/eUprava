
import { useEffect, useState } from "react"
import { useJwt } from "react-jwt";
import { useNavigate } from 'react-router-dom';
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import axios from 'axios';
import moment from 'moment';
import Modal from 'react-bootstrap/Modal'
import ModalHeader from 'react-bootstrap/ModalHeader'
import ModalTitle from 'react-bootstrap/ModalTitle'
import ModalBody from 'react-bootstrap/ModalBody'
import ModalFooter from 'react-bootstrap/ModalFooter'

export const ZakazivanjeForma = ({ docType, minor }) => {

    const navigate = useNavigate();
    const usertoken = localStorage.getItem("token")
    const { decodedToken, isExpired } = useJwt(usertoken);

    const [appForMinor, setAppForMinor] = useState(false)
    const [appDateTime, setAppDateTimeMinor] = useState(false)
    const [firstNameMinor, setFirstNameMinor] = useState("")
    const [surnameMinor, setSurnameMinor] = useState("")
    const [dateOfBirthMinor, setDateOfBirthMinor] = useState("")
    const [countryOfBirthMinor, setCountryOfBirthMinor] = useState("")
    const [citizenshipMinor, setCitizenshipMinor] = useState("")
    const [genderMinor, setGenderMinor] = useState("")
    const [jmbgMinor, setJMGBMinor] = useState("")

    const [show, setShow] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const handleClose = () => setShowModal(false);

    useEffect(() => {
    }, [])

    const handleSubmit = () => {

        if (minor == false) {
            const format = "YYYY-MM-DD HH:mm"
            axios.post("http://localhost:11001/api/appointment/newAppointment", {
                documentType: docType,
                requestedAppointmentTime: moment(appDateTime).format(format),
                appointmentForMinor: false
            },
                {
                    params: {
                        loggedUsername: decodedToken.username
                    }
                })
                .then((res) => {
                    console.log(res)
                    if (res.status == 200) {
                        navigate("/")
                    }
                })
                .catch((err) => {
                    setShowModal(true)
                })
        } else if (minor == true) {
            const format = "YYYY-MM-DD HH:mm"
            const format2 = "YYYY-MM-DD"
            axios.post("http://localhost:11001/api/appointment/newAppointment", {
                documentType: docType,
                requestedAppointmentTime: moment(appDateTime).format(format),
                appointmentForMinor: true,
                minorFirstName: firstNameMinor,
                minorLastName: surnameMinor,
                minorDateOfBirth: moment(dateOfBirthMinor).format(format2),
                minorCountryOfBirth: countryOfBirthMinor,
                minorCitizenship: citizenshipMinor,
                minorGender: genderMinor,
                minorJmbg: jmbgMinor
            },
                {
                    params: {
                        loggedUsername: decodedToken.username
                    }
                })
                .then((res) => {
                    console.log(res)
                    if (res.status == 200) {
                        navigate("/")
                    }
                })
                .catch((err) => {
                    console.log(err)
                    if (err.status != 200) {
                        setShowModal(true)
                    }
                })
        }
    }

    function documentType() {
        if (docType && docType == "DOCUMENT_IDCARD") {
            return "Document ID Card"
        } else if (docType && docType == "DOCUMENT_PASSPORT") {
            return "Document Passport"
        }
    }

    return (
        <>
             <Modal centered show={showModal} onHide={handleClose}>
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
            <div className="formaDiv">
                <Form>
                    <fieldset disabled>
                        <Form.Group className="mb-3" controlId="formBasicDocType">
                            <Form.Label>Document type</Form.Label>
                            <Form.Control type="text" placeholder={documentType()} />
                        </Form.Group>
                    </fieldset>

                    <Form.Group className="mb-3" controlId="formBasicAppTime">
                        <Form.Label>Appointment date-time</Form.Label>
                        <Form.Control type="datetime-local" onChange={(e) => setAppDateTimeMinor(e.target.value)} />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="formBasicCheckbox">
                        {minor ?
                            <Form.Check checked type="checkbox" label="Appointment for minor" onChange={(e) => setAppForMinor(true)} />
                            :
                            <fieldset disabled>
                                <Form.Check type="checkbox" label="Appointment for minor" onChange={(e) => setAppForMinor(false)} />
                            </fieldset>
                        }
                    </Form.Group>

                    {minor &&
                        <>
                            <Form.Group className="mb-3" controlId="formNameMinor">
                                <Form.Label>First name minor</Form.Label>
                                <Form.Control type="text" onChange={(e) => setFirstNameMinor(e.target.value)} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formSurnameMinor">
                                <Form.Label>Surname minor</Form.Label>
                                <Form.Control type="text" onChange={(e) => setSurnameMinor(e.target.value)} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formDateOfBirthMinor">
                                <Form.Label>Date of birth minor</Form.Label>
                                <Form.Control type="date" onChange={(e) => setDateOfBirthMinor(e.target.value)} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formCountryOfBirthMinor">
                                <Form.Label>Country of birth minor</Form.Label>
                                <Form.Control type="text" onChange={(e) => setCountryOfBirthMinor(e.target.value)} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formCitizenshipMinor">
                                <Form.Label>Citizenship minor</Form.Label>
                                <Form.Control type="text" onChange={(e) => setCitizenshipMinor(e.target.value)} />
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formGenderMinor">
                                <Form.Label>Gender minor</Form.Label>
                                <Form.Select aria-label="genderSelect" onChange={(e) => setGenderMinor(e.target.value)}>
                                    <option value="null">-</option>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    <option value="Other">Other</option>
                                </Form.Select>
                            </Form.Group>
                            <Form.Group className="mb-3" controlId="formJMBGMinor">
                                <Form.Label>JMBG minor</Form.Label>
                                <Form.Control type="text" onChange={(e) => setJMGBMinor(e.target.value)} />
                            </Form.Group>
                        </>
                    }

                    <Button variant="primary" onClick={handleSubmit}>
                        Submit
                    </Button>
                </Form>
            </div>
        </>
    )
}

export default ZakazivanjeForma