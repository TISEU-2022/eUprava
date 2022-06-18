import { useState } from "react"
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import axios from 'axios';
import Header from "../navbar/Header"
import { useNavigate } from 'react-router-dom';
import Modal from 'react-bootstrap/Modal'
import ModalHeader from 'react-bootstrap/ModalHeader'
import ModalTitle from 'react-bootstrap/ModalTitle'
import ModalBody from 'react-bootstrap/ModalBody'
import ModalFooter from 'react-bootstrap/ModalFooter'

export const Registration = () => {

    const navigate = useNavigate();

    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [identityNumber, setIdentityNumber] = useState("")
    const [roles, setRoles] = useState(["user"])
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const [userExist, setUserExist] = useState("")
    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const handleSubmit = (e) => {
        axios.post("http://localhost:5101/user", {
            firstName: firstName,
            lastName: lastName,
            username: username,
            password: password,
            identityNumber: identityNumber,
            roles: roles
        })
            .then((res) => {
                console.log(res)
                if (res.statusText = "Created") {
                    navigate("/")
                }
            })
            .catch((err) => {
                console.log(err)
                if (err.response.data.errors.username.message) {
                    let error = err.response.data.errors.username.message
                    setUserExist(error)
                    setShow(true);
                }
            })
    }

    return (
        <>
            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Error message</Modal.Title>
                </Modal.Header>
                <Modal.Body>{userExist}</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
            <Header />
            <Container>
                <Row>
                    <Col></Col>
                    <Col>
                        <Form>
                            <Row>
                                <Form.Text id="headerText">
                                    Registration Page
                                </Form.Text>
                            </Row>
                            <Form.Group className="mb-3" controlId="formUsername">
                                <Form.Label>Username</Form.Label>
                                <Form.Control type="username" placeholder="Enter username" onChange={(e) => setUsername(e.target.value)} />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formName">
                                <Form.Label>Firstname</Form.Label>
                                <Form.Control type="text" placeholder="Enter firstname" onChange={(e) => setFirstName(e.target.value)} />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formLastname">
                                <Form.Label>Lastname</Form.Label>
                                <Form.Control type="text" placeholder="Enter lastname" onChange={(e) => setLastName(e.target.value)} />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formIdNumber">
                                <Form.Label>Identity number</Form.Label>
                                <Form.Control type="text" placeholder="Enter identity number" onChange={(e) => setIdentityNumber(e.target.value)} />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)} />
                            </Form.Group>

                            <Row>
                                <Form.Text id="registerLink">
                                    <a href="/login">Login page here.</a>
                                </Form.Text>
                            </Row>

                            <Button variant="primary" onClick={() => handleSubmit()}>
                                Submit
                            </Button>
                        </Form>
                    </Col>
                    <Col></Col>
                </Row>
            </Container>
        </>
    )
}

export default Registration