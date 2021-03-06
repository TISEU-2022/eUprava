import React from 'react';
import GlasanjeAxiosClient from "./../services/clients/GlasanjeAxiosClient";
import {Card, Container} from "react-bootstrap";
import Swal from "sweetalert2";
import NavigationBar from "./NavigationBar";

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

        GlasanjeAxiosClient.get(url)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    kandidatiLista: data
                });
            });
    };

    onValueChange(event) {
        console.log(event.target.value);
        this.setState({
            selectedOption: event.target.value
        });
    }

    formSubmit(event) {
        event.preventDefault();

        var izboriId = window.location.href;
        izboriId = izboriId.replace("http://localhost:3000/glasanje/", "");
        izboriId = izboriId.replace("http://localhost:10001/glasanje/", "");

        var korisnikId = localStorage.getItem("id");

        var glas = {
            izbori: izboriId,
            kandidat: this.state.selectedOption,
            korisnik: korisnikId
        };
        console.log(glas);

        GlasanjeAxiosClient.post("http://localhost:10002/glasanje", glas).then((response) => {
            if (response.status === 200) {
                console.log("USPEH");
                Swal.fire({
                    icon: 'success',
                    title: '??????????',
                    text: '?????? ???????? ???? ??????????????????',
                })
            } else if (response.status === 204) {
                console.log("VEC STE GLASALI");
                Swal.fire({
                    icon: 'error',
                    title: '????????????',
                    text: '?????? ?????? ??????????????',
                })
            } else {
                Swal.fire({
                    icon: 'error',
                    title: '????????????',
                    text: '?????????????????? ???????????? ??????????????',
                })
            }
        });
    }

    render() {
        const {kandidatiLista} = this.state;

        return (
            <div>
            <NavigationBar/>
            <Container className="kontejner">
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><h3>?????????? ??????????????????</h3></Card.Header>
                    <Card.Body>
                        {
                            kandidatiLista.length === 0
                            ?
                            <div>???????? ??????????????????</div>
                            :
                            <form onSubmit={this.formSubmit}><div className="radio">
                                {
                                    kandidatiLista.map((kandidat)=>(
                                        <label
                                            key={kandidat.id}>
                                            <input
                                                type="radio"
                                                value= {kandidat.id}
                                                checked={this.state.selectedOption == kandidat.id}
                                                onChange={this.onValueChange}
                                            />
                                            {kandidat.imePredstavnika}
                                            <br/>
                                        </label>
                                    ))
                                }
                                </div>
                                <button className="btn btn-default" type="submit">
                                    ????????????
                                </button>
                            </form>
                        }
                    </Card.Body>
                </Card>
            </Container>
            </div>
        );
    }
}