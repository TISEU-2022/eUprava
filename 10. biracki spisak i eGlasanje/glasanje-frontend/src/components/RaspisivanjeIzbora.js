import React from 'react';
import {Button, Card, Col, Container, Form} from "react-bootstrap";

export default class RaspisivanjeIzbora extends React.Component {

    constructor(props) {
        super(props);
        this.submit = this.submit.bind(this);
    }

    submit(event) {
        event.preventDefault();
        console.log("aaaaaaaaa")
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
                                                  placeholder = "Унесите назив"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridDatum"}>
                                    <Form.Label>Датум</Form.Label>
                                    <Form.Control required type="date" min-value = "0" name = "datum" autoComplete = "off"
                                                  className={"bg-dark text-white"}
                                                  placeholder = "Унесите датум"/>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridTipIzbora"}>
                                    <Form.Label>Тип избора</Form.Label>
                                    <Form.Select required type="text" name = "tipIzbora" autoComplete = "off"
                                                 className={"bg-dark text-white"}
                                                 placeholder = "Унесите тип избора">
                                        <option>Републички</option>
                                        <option>Општински</option>
                                    </Form.Select>
                                </Form.Group>
                                <Form.Group as={Col} controlId={"formGridOpstina"}>
                                    <Form.Label>Општина</Form.Label>
                                    <Form.Select required type="text" name = "tipOpstina" autoComplete = "off"
                                                 className={"bg-dark text-white"}
                                                 placeholder = "Изаберите општину">
                                        <option>Београд</option>
                                        <option>Бачка Топола</option>
                                        <option>Мали Иђош</option>
                                    </Form.Select>
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