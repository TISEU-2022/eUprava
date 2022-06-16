import { useEffect, useState } from "react"
import Card from 'react-bootstrap/Card'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Button from 'react-bootstrap/Button'
import { useNavigate } from 'react-router-dom';
import Header from "../components/navbar/Header"

export const Home = () => {
    const navigate = useNavigate();

    const startWorkflow = (route) => {
        let token = localStorage.getItem("token")
        if (!token) {
            window.open(
                `http://localhost:5101/auth/login?successUrl=http://localhost:11000/auth/token_handler`,
                '_self',
            );
        } else {
            navigate(route);
        }
    }

    return (
        <>
            <Header />
            <Container>
                <Row>
                    <Col>
                        <Card border="primary" text={'white'} bg={"secondary"} style={{ width: '18rem' }}>
                            <Card.Body>
                                <Card.Title>Zakazivanje - Licna karta</Card.Title>
                                <Card.Text>
                                    Zakazivanje termina za izradu licne karte
                                </Card.Text>
                                <Button variant="primary" onClick={() => startWorkflow("licna")}>Start</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                    <Col>
                        <Card border="primary" text={'white'} bg={"secondary"} style={{ width: '18rem' }} >
                            <Card.Body>
                                <Card.Title text={'white'}>Zakazivanje za dete - Licna karta</Card.Title>
                                <Card.Text>
                                    Zakazivanje termina za izradu licne karte u svojstvu staratelja maloletnog deteta
                                </Card.Text>
                                <Button variant="primary" onClick={() => startWorkflow("licna-dete")}>Start</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                    <Col>
                        <Card border="warning" text={'white'} bg={"secondary"} style={{ width: '18rem' }}>
                            <Card.Body>
                                <Card.Title>Zakazivanje - Pasos</Card.Title>
                                <Card.Text>
                                    Zakazivanje termina za izradu pasosa
                                </Card.Text>
                                <Button variant="primary" onClick={() => startWorkflow("pasos")}>Start</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                    <Col>
                        <Card border="warning" text={'white'} bg={"secondary"} style={{ width: '18rem' }}>
                            <Card.Body>
                                <Card.Title>Zakazivanje za dete - Pasos </Card.Title>
                                <Card.Text>
                                    Zakazivanje termina za izradu pasosa za dete
                                </Card.Text>
                                <Button variant="primary" onClick={() => startWorkflow("pasos-dete")}>Start</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                </Row>
            </Container>
        </>
    )
}

export default Home