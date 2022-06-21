import React from 'react';
import GlasanjeAxiosClient from "./../services/clients/GlasanjeAxiosClient";
import {Card, Container, Row} from "react-bootstrap";
import NavigationBar from "./NavigationBar";

export default class RezultatiIzbora extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            rezultati : []
        };
    }

    componentDidMount() {
        this.findRezultati();
    }

    findRezultati() {
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

        var izboriId = window.location.href;
        izboriId = izboriId.replace("http://localhost:3000/rezultati/", "");
        izboriId = izboriId.replace("http://localhost:10001/rezultati/", "");
        let url = "http://localhost:10002/izbori/rezultati/" + izboriId;

        GlasanjeAxiosClient.get(url)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    rezultati: data
                });
            });
    };

    render() {
        const {rezultati} = this.state;

        return (
            <div>
            <NavigationBar/>
            <Container className="kontejner">
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><h3>Резултати избора</h3></Card.Header>
                    <Card.Body>
                        {
                            rezultati.length === 0
                                ?
                                <div>Нема</div>
                                :
                                rezultati.map((rezultat)=>(
                                    <Row key={rezultat.kandidat} xs={6} md={4} style={{marginBottom:"45px"}}>
                                        <h4>{rezultat.kandidat} - {rezultat.br_glasova}</h4>
                                    </Row>
                                ))
                        }
                    </Card.Body>
                </Card>
            </Container>
            </div>
        );
    }
}