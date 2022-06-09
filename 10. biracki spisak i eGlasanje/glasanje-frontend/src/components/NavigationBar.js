import React from 'react';
import {Navbar} from "react-bootstrap";

export default class NavigationBar extends React.Component {
    render() {
        return (
            <Navbar className="navigacija" bg="dark" variant="dark">
                <a className="btn btn-primary"
                   href={
                       localStorage.getItem("id") == null
                       ?
                           "/prijava"
                           :
                           "/"
                   }
                   role="button">
                    Почетна
                </a>
            </Navbar>
        );
    }
}