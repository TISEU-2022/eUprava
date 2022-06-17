import React, { useState } from "react";
import {Button, Card, Col, Container, Form, Row} from "react-bootstrap";
import { AuthenticationService } from "../services/AuthenticationService";
import NavigationBar from "./NavigationBar";
import {BrowserRouter as Router} from "react-router-dom";

const Login =() => {

    if (localStorage.getItem("id") != null) {
        window.location.assign("/AktuelniIzbori");
    }

    const [credentials, setCredentials] = useState({
        jmbg: "",
        lozinka: "",
    });

    // Funkcija koja prima naziv polja koje će se ažurirati, a potom i događaj koji je doveo do tog ažuriranja
    // Iz događaja je moguće izvući novu vrednost polja
    const handleFormInputChange = (name) => (event) => {
        const val = event.target.value;

        // ... - Destructuring assignment - omogućuje raspakivanje vrednosti objekata ili nizova
        // setCredentails će zameniti stanje novim objektom koji uzima vrednosti iz prethodnog stanja kredencijala
        // ali sa ažuriranom vrednošću [name] polja
        setCredentials({ ...credentials, [name]: val });
    };

    const login = async () => {
        await AuthenticationService.login(credentials);
    };

    return (
        <div>
            <NavigationBar/>
            <Container className={"kontejner"}>
                <Card style={{ width: '70rem', margin: 'auto'}} className={"border border-dark bg-dark text-white"}>
                    <Row>
                        <Col md={{ span: 6, offset: 3 }} style={{ textAlign: "center" }}>
                            <h1>Пријава</h1>
                            <hr/>
                            <Form>
                                <Form.Group>
                                    <Form.Label>JМБГ</Form.Label>
                                    <Form.Control
                                        type="text"
                                        name="jmbg"
                                        value={credentials.jmbg}
                                        onChange={handleFormInputChange("jmbg")}
                                    />
                                </Form.Group>
                                <Form.Group>
                                    <Form.Label>Лозинка</Form.Label>
                                    <Form.Control
                                        type="password"
                                        name="lozinka"
                                        value={credentials.lozinka}
                                        onChange={handleFormInputChange("lozinka")}
                                    />
                                </Form.Group>
                                <Button variant="success" href="http://localhost:4101/auth/login?successUrl=http://localhost:10001/auth">
                                    Пријави се
                                </Button>
                            </Form>
                        </Col>
                    </Row>
                </Card>
            </Container>
        </div>
    );
};
export default Login;

