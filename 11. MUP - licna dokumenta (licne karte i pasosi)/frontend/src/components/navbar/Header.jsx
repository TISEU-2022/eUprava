import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from 'react-bootstrap/Navbar'
import Container from 'react-bootstrap/Container'
import Nav from 'react-bootstrap/Nav'
import Button from 'react-bootstrap/Button'
import axios from 'axios';
import { useJwt } from "react-jwt";

export const Header = () => {
    const navigate = useNavigate();

    const [user, setUser] = useState(false)
    const [username, setUsername] = useState(false)
    const usertoken = localStorage.getItem("token")
    const { decodedToken, isExpired } = useJwt(usertoken);

    // console.log(isExpired)

    useEffect(() => {
        if (decodedToken && isExpired) {
            localStorage.removeItem('token');
            navigate('/');
        }
    }, [])

    const logOut = () => {
        window.open(
            `http://localhost:5101/auth/logout?successUrl=http://localhost:11000/auth/logout_handler`,
            '_self',
        );
    }

    return (
        <>
            <Navbar bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand href="/">MUP - Licna dokumenta (Tim 11)</Navbar.Brand>
                    <Navbar.Toggle />
                    <Navbar.Collapse className="justify-content-end">
                        {decodedToken && decodedToken.username && !isExpired &&
                            <>
                                <Navbar.Text>
                                    Signed in as: <a href="#">{decodedToken.username}</a>
                                </Navbar.Text>
                                <Nav.Link onClick={() => logOut()}>Logout</Nav.Link>
                            </>
                        }

                        {!decodedToken &&
                            <>
                                <Nav.Link onClick={() => {
                                    window.open(
                                        `http://localhost:5101/auth/login?successUrl=http://localhost:11000/auth/token_handler`,
                                        '_self',
                                    );
                                }}>Login</Nav.Link>
                                <Nav.Link onClick={() => navigate("/registration")}>Registration</Nav.Link>
                            </>
                        }
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    )
}

export default Header