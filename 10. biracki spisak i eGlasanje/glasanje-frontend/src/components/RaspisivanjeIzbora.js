import React from 'react';
import {Button, Card, Col, Container, Form} from "react-bootstrap";
import axios from "axios";

export default class RaspisivanjeIzbora extends React.Component {

    constructor(props) {
        super(props);
        this.state = this.initialState;
        this.submit = this.submit.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
        naziv: '',
        datum: null,
        eTipIzbora: 'REPUBLICKI',
        eOpstina: null,
        //kandidatiDTO: []

        imeKandidata1: '',
        imeStrankeKandidata1: '',
        sloganKandidata1: '',

        imeKandidata2: '',
        imeStrankeKandidata2: '',
        sloganKandidata2: ''
    }

    formChange = event => {
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    submit(event) {
        event.preventDefault();

        var kandidati = [
            {
                imeStranke: this.state.imeKandidata1,
                imePredstavnika: this.state.imeStrankeKandidata1,
                slogan: this.state.sloganKandidata1,
            },
            {
                imeStranke: this.state.imeKandidata2,
                imePredstavnika: this.state.imeStrankeKandidata2,
                slogan: this.state.sloganKandidata2,
            },
        ];
        console.log(kandidati);

        var izbori = {
            naziv: this.state.naziv,
            datum: this.state.datum,
            etipIzbora: this.state.eTipIzbora,
            eopstina: this.state.eOpstina,
            kandidatiDTO: kandidati
        };
        console.log(izbori);

        axios.post("http://localhost:10002/izbori/raspisivanje", izbori).then(() => console.log("USPEH"));
    }

    render() {
        return (
            <Container className="kontejner">
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header><h3>Расписивање ибора</h3></Card.Header>
                    <Card.Body>
                        <Form onSubmit={this.submit} id="userFormId">
                            <Card.Body>
                                <Form.Group as={Col} controlId={"formGridNaziv"}>
                                    <Form.Label>Назив</Form.Label>
                                    <Form.Control required type="text" name = "naziv" autoComplete = "off"
                                                  className={"bg-dark text-white"}
                                                  onChange={this.formChange}
                                                  placeholder = "Унесите назив"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridDatum"}>
                                    <Form.Label>Датум</Form.Label>
                                    <Form.Control required type="date" min-value = "0" name = "datum" autoComplete = "off"
                                                  className={"bg-dark text-white"}
                                                  onChange={this.formChange}
                                                  placeholder = "Унесите датум"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridTipIzbora"}>
                                    <Form.Label>Тип избора</Form.Label>
                                    <Form.Select required type="text" name = "eTipIzbora" autoComplete = "off"
                                                 className={"bg-dark text-white"}
                                                 onChange={this.formChange}
                                                 placeholder = "Унесите тип избора">
                                        <option>REPUBLICKI</option>
                                        <option>OPSTINSKI</option>
                                    </Form.Select>
                                </Form.Group>
                                {
                                    this.state.eTipIzbora === "OPSTINSKI"
                                    ?
                                    <Form.Group as={Col} controlId={"formGridOpstina"}>
                                        <Form.Label>Општина</Form.Label>
                                        <Form.Select required type="text" name = "eOpstina" autoComplete = "off"
                                                     className={"bg-dark text-white"}
                                                     onChange={this.formChange}
                                                     placeholder = "Изаберите општину">
                                            <option>BEOGRAD</option>
                                            <option>BACKA_TOPOLA</option>
                                            <option>MALI_IDJOS</option>
                                        </Form.Select>
                                    </Form.Group>
                                    :
                                    <br/>
                                }
                                <br/>
                                <Form.Label>Кандидат 1</Form.Label>
                                <Form.Group as={Col} controlId={"formGridOpstina"}>
                                    <Form.Control required type="text" name = "imeKandidata1" autoComplete = "off"
                                                  className={"bg-dark text-white"}
                                                  onChange={this.formChange}
                                                  placeholder = "Име кандидата 1"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridOpstina"}>
                                    <Form.Control required type="text" name = "imeStrankeKandidata1" autoComplete = "off"
                                                  className={"bg-dark text-white"}
                                                  onChange={this.formChange}
                                                  placeholder = "Име странке кандидата 1"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridOpstina"}>
                                    <Form.Control required type="text" name = "sloganKandidata1" autoComplete = "off"
                                                  className={"bg-dark text-white"}
                                                  onChange={this.formChange}
                                                  placeholder = "Слоган кандидата 1"/>
                                </Form.Group>
                                <br/>
                                <Form.Label>Кандидат 2</Form.Label>
                                <Form.Group as={Col} controlId={"formGridOpstina"}>
                                    <Form.Control required type="text" name = "imeKandidata2" autoComplete = "off"
                                                  className={"bg-dark text-white"}
                                                  onChange={this.formChange}
                                                  placeholder = "Име кандидата 2"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridOpstina"}>
                                    <Form.Control required type="text" name = "imeStrankeKandidata2" autoComplete = "off"
                                                  className={"bg-dark text-white"}
                                                  onChange={this.formChange}
                                                  placeholder = "Име странке кандидата 2"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridOpstina"}>
                                    <Form.Control required type="text" name = "sloganKandidata2" autoComplete = "off"
                                                  className={"bg-dark text-white"}
                                                  onChange={this.formChange}
                                                  placeholder = "Слоган кандидата 2"/>
                                </Form.Group>
                            </Card.Body>
                            <Card.Footer style={{"textAlign":"center"}}>
                                <Button size={"sm"} variant={"success"} type={"submit"}>
                                    <h4>Потврди</h4>
                                </Button>
                            </Card.Footer>
                        </Form>
                    </Card.Body>
                </Card>
            </Container>
        );
    }
}