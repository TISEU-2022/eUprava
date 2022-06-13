import React from 'react';
import authService, {authServiceLogin, authServiceLogout} from "../../services/auth-service";
import navbarService from "../../services/navbar-service";
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import styles from './Navigation.module.css';
import {Link} from "react-router-dom";
import {Button} from "react-bootstrap";

const Navigation = () => {
    
    const navigationLinks = navbarService.getAllowedNavbarLinks().map((navLink, index) => (
        <Link key={index} to={navLink.url} className={styles.link}>
            {navLink.text}
        </Link>
    ));
    
    return (
        <Navbar collapseOnSelect expand="lg" className="bg-dark text-white m-0">
            <Container>
                <Link to="/" className={styles.brand}>
                    eUprava
                </Link>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" className={styles.toggle}/>
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="ml-auto">
                        {navigationLinks}
                    </Nav>
                    {
                        authService.isLoggedIn() ? (
                            <Button size="sm" variant="light" className="ml-4" href={authServiceLogout}>
                                Logout
                            </Button>
                        ) : (
                            <Button size="sm" variant="light" className="ml-4" href={authServiceLogin}>
                                Login
                            </Button>
                        )
                    }
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default Navigation;