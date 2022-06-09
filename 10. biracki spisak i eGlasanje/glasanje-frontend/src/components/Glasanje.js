import React from 'react';
import GlasanjeAxiosClient from "./../services/clients/GlasanjeAxiosClient";
import {Card, Col, Container, Row} from "react-bootstrap";
import axios from "axios";

export default class Glasanje extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            kandidatiLista : []
        };
        this.formSubmit = this.formSubmit.bind(this);
        this.selectedOption = -1;
        this.onValueChange = this.onValueChange.bind(this);
    }

    componentDidMount() {
        this.findKandidati();
    }

    findKandidati() {

        /*var kandidati = [
            {
                id: 0,
                imePredstavnika: "kolega"
            },
            {
                id: 1,
                imePredstavnika: "kolegica"
            },
        ];
        this.setState({
            kandidatiLista: kandidati
        });*/

        let url = "http://localhost:10002/kandidati/kandidati-izbora/";
        let lokacija = window.location.href;
        lokacija = lokacija.replace("http://localhost:10001/glasanje/", "");
        url += lokacija;

        axios.get(url)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    kandidatiLista: data
                });
            });
    };

    onValueChange(event) {
        this.setState({
            selectedOption: event.target.value
        });
    }

    formSubmit(event) {
        event.preventDefault();
        console.log(this.state.selectedOption)
    }

    render() {
        const {kandidatiLista} = this.state;

        return (
            <Container className="kontejner">
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><h3>Листа кандидата</h3></Card.Header>
                    <Card.Body>
                        {
                            kandidatiLista.length === 0
                            ?
                            <div>Нема кандидата</div>
                            :
                            <form onSubmit={this.formSubmit}><div className="radio">
                                {
                                    kandidatiLista.map((kandidat)=>(
                                        <label
                                            key={kandidat.id}>
                                            <input
                                                type="radio"
                                                value= {kandidat.imePredstavnika}
                                                checked={this.state.selectedOption === kandidat.imePredstavnika}
                                                onChange={this.onValueChange}
                                            />
                                            {kandidat.imePredstavnika}
                                            <br/>
                                        </label>
                                    ))
                                }
                                </div>
                                <button className="btn btn-default" type="submit">
                                    Гласај
                                </button>
                            </form>
                        }
                    </Card.Body>
                </Card>
            </Container>
        );
    }
}