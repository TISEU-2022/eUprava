import React from 'react';
import GlasanjeAxiosClient from "./../services/clients/GlasanjeAxiosClient";
import {Card, Container, Row} from "react-bootstrap";

export default class ZavrseniIzbori extends React.Component {

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

        let url = "http://localhost:10002/izbori/zavrseni";

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
                    <Card.Header><h3>Листа завршених избора</h3></Card.Header>
                    <Card.Body>
                        {
                            izboriLista.length === 0
                                ?
                                <div>Нема завршених избора</div>
                                :
                                izboriLista.map((izbori)=>(
                                    <Row key={izbori.id} xs={6} md={4} style={{marginBottom:"45px"}}>
                                        <h3><a href={"rezultati/" + izbori.id}>{izbori.naziv}</a></h3>
                                    </Row>
                                ))
                        }
                    </Card.Body>
                </Card>
            </Container>
        );
    }
}