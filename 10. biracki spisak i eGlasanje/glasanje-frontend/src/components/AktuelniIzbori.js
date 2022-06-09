import React from 'react';
import GlasanjeAxiosClient from "./../services/clients/GlasanjeAxiosClient";
import {Card, Col, Container, Row} from "react-bootstrap";

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
        let url = "http://localhost:10001/izbori/";
        /*if (AuthenticationService.getRole() === "ROLE_PRODAVAC") {
            url += "1";
        } else {
            let lokacija = window.location.href;
            lokacija = lokacija.replace("http://localhost:3000/artikli-prodavca/", "");
            url += lokacija;
        }*/

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
                        <Row>
                            {
                                izboriLista.length === 0
                                    ?
                                    <div>Нема актуелних избора</div>
                                    :
                                    izboriLista.map((izbori)=>(
                                        <Col key={izbori.id} xs={6} md={4} style={{marginBottom:"45px"}}>
                                            <h3>{izbori.naziv}</h3>
                                        </Col>
                                    ))
                            }
                        </Row>
                    </Card.Body>
                </Card>
            </Container>
        );
    }
}