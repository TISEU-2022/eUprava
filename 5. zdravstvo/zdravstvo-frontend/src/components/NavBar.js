import React, { useState, useEffect } from 'react'
import { Button, Navbar, Container, Nav } from 'react-bootstrap'

const NavBar = () => {

    const [user, setUser] = useState({});

    useEffect(() => {
        getUser();

    }, [])

    async function getUser() {
        try {
            const response = await UserService.getUser();
            setUser(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }


    return (
        <Navbar bg="dark" variant="dark">
            <Container>
                <Navbar.Brand href="/">E - ZDRAVSTVO</Navbar.Brand>
                <Nav className="me-auto">
                    {user.role == 'DOCTOR' ?
                        <>
                            <Nav.Link href="/citizen-manipulation">Uverenja</Nav.Link>
                            <Nav.Link href="/book-appointment">Pregledi</Nav.Link>
                        </> :
                        <>
                            <Nav.Link href="/medical-record">Medicinski karton</Nav.Link>
                            <Nav.Link href="/doctor-appointments">Termini</Nav.Link>
                        </>

                    }

                </Nav>
            </Container>
        </Navbar>
    )
}

export default NavBar;