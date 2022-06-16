import React from 'react';
import GlasanjeAxiosClient from "./../services/clients/GlasanjeAxiosClient";
import {Card, Col, Container, Row} from "react-bootstrap";
import axios from "axios";
import {Navigate} from "react-router-dom";
import {AuthenticationService} from "../services/AuthenticationService";

export default class AktuelniIzbori extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            izboriLista : []
        };
    }

    componentDidMount() {
        this.findAktuelniIzbori();
    }

    findAktuelniIzbori() {
       /* var izbori = [
            {
                id: 0,
                naziv: "republicki"
            },
            {
                id: 1,
                naziv: "opstinski"
            },
        ];
        this.setState({
            izboriLista: izbori
        });*/

        const id = localStorage.getItem("id");
        let url = "http://localhost:10002/izbori/aktuelni/" + id;

        GlasanjeAxiosClient.get(url)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    izboriLista: data
                });
            });
    };

    render() {
        const {izboriLista} = this.state;

        return (
            <Container className="kontejner">
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><h3>Листа актуелних избора</h3></Card.Header>
                    <Card.Body>
                        {
                            izboriLista.length === 0
                                ?
                                <div>Нема актуелних избора</div>
                                :
                                izboriLista.map((izbori)=>(
                                    <Row key={izbori.id} xs={6} md={4} style={{marginBottom:"45px"}}>
                                        <h3><a href={"glasanje/" + izbori.id}>{izbori.naziv}</a></h3>
                                    </Row>
                                ))
                        }
                    </Card.Body>
                </Card>
            </Container>
        );
    }
}