import React from 'react';
import GlasanjeAxiosClient from "./../services/clients/GlasanjeAxiosClient";
import {Button, Card, Col, Container, Form} from "react-bootstrap";
import NavigationBar from "./NavigationBar";

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

        inputList: [{ imeStranke: "", imePredstavnika: "", slogan: "" }]
    }

    handleInputChange = (e, index) => {
        const { name, value } = e.target;
        const list = [...this.state.inputList];
        list[index][name] = value;
        this.state.inputList = list;
        this.setState(this.state);
    };

    handleRemoveClick = index => {
        const list = [...this.state.inputList];
        list.splice(index, 1);
        this.state.inputList = list;
        this.setState(this.state);
    };

    handleAddClick = () => {
        this.state.inputList = [...this.state.inputList, { imeStranke: "", imePredstavnika: "", slogan: "" }];
        this.setState(this.state);
    };

    formChange = event => {
        this.setState({
            [event.target.name]:event.target.value
        });
        this.setState(this.state);
    }

    submit(event) {
        event.preventDefault();

        var izbori = {
            naziv: this.state.naziv,
            datum: this.state.datum,
            etipIzbora: this.state.eTipIzbora,
            eopstina: this.state.eOpstina,
            kandidatiDTO: this.state.inputList
        };
        console.log(izbori);

        GlasanjeAxiosClient.post("http://localhost:10002/izbori/raspisivanje", izbori).then(() => console.log("USPEH"));
    }

    render() {
        return (
            <div>
            <NavigationBar/>
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
                                {this.state.inputList.map((x, i) => {
                                return (
                                    <div
                                        key={i} className="box">
                                        <div className="btn-box">
                                            <h3>{"Кандидат број " + (i + 1)}</h3>
                                        </div>
                                        <Form.Group as={Col} controlId={"formGridOpstina"}>
                                            <Form.Control required type="text" name = "imeStranke" autoComplete = "off"
                                                          className={"bg-dark text-white"}
                                                          onChange={e => this.handleInputChange(e, i)}
                                                          placeholder = "Име странке"/>
                                        </Form.Group>
                                        <Form.Group as={Col} controlId={"formGridOpstina"}>
                                            <Form.Control required type="text" name = "imePredstavnika" autoComplete = "off"
                                                          className={"bg-dark text-white"}
                                                          onChange={e => this.handleInputChange(e, i)}
                                                          placeholder = "Име кандидата"/>
                                        </Form.Group>
                                        <Form.Group as={Col} controlId={"formGridOpstina"}>
                                            <Form.Control required type="text" name = "slogan" autoComplete = "off"
                                                          className={"bg-dark text-white"}
                                                          onChange={e => this.handleInputChange(e, i)}
                                                          placeholder = "Слоган"/>
                                        </Form.Group>
                                        <br/>
                                        <div className="btn-box">
                                            {this.state.inputList.length - 1 === i
                                            &&
                                            <Button size={"sm"} variant={"success"} onClick={this.handleAddClick}>Додај</Button>}
                                        </div>
                                    </div>
                                );
                            })}
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
            </div>
        );
    }
}