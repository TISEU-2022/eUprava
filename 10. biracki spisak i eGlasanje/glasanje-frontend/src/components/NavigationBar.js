import React from 'react';
import {Navbar} from "react-bootstrap";
import {AuthenticationService} from "../services/AuthenticationService";

export default class NavigationBar extends React.Component {
    render() {
        console.log(AuthenticationService.getRole())
        return (
            <Navbar className="navigacija" bg="dark" variant="dark">
                {
                    localStorage.getItem("id") == null
                        ?
                        <b/>
                        :
                        <a className="btn btn-primary"
                           href={"/"}
                           role="button">
                            Актуелни избори
                        </a>
                }
                {
                    localStorage.getItem("id") == null
                        ?
                        <b/>
                        :
                        <a className="btn btn-primary"
                           href={"/zavrseniIzbori"}
                           role="button">
                            Завршени избори
                        </a>
                }
                {
                    localStorage.getItem("id") !== null && AuthenticationService.getRole() === "SLUZBENIK"
                        ?
                        <a className="btn btn-primary"
                           href={"/raspisivanje"}
                           role="button">
                            Расписивање избора
                        </a>
                        :
                        <b/>
                }
                {
                    localStorage.getItem("id") == null
                        ?
                        <b/>
                        :
                        <a className="btn btn-primary"
                           onClick={() => AuthenticationService.logout()}
                           role="button">
                            Одјава
                        </a>
                }
            </Navbar>
        );
    }
}