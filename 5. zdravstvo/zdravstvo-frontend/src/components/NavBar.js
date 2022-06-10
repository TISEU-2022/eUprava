import React from 'react'
import { Button, Navbar, Container, Nav } from 'react-bootstrap'

const NavBar = () => {
    return (
        <Navbar bg="dark" variant="dark">
            <Container>
                <Navbar.Brand href="/">E - ZDRAVSTVO</Navbar.Brand>
                <Nav className="me-auto">
                    <Nav.Link href="/citizen-manipulation">Uverenja</Nav.Link>
                    <Nav.Link href="/book-appointment">Pregledi</Nav.Link>
                    <Nav.Link href="/medical-record">Medicinski karton</Nav.Link>
                    <Nav.Link href="/doctor-appointments">Termini</Nav.Link>
                </Nav>
            </Container>
        </Navbar>
    )
}

export default NavBar;