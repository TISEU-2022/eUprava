import {useHistory, useParams} from "react-router-dom";
import vrsteKomunalnihProblemaService from "../../../services/api/vrste-komunalnih-problema-service";
import {useEffect, useState} from "react";
import {Button, Table} from "react-bootstrap";

const VrsteKomunalnihProblemaDetails = () =>{
    const { id } = useParams();
    const history = useHistory();
    const [vrstaKomunalnogProblema, setVrstaKomunalnogProblema] = useState({});

    useEffect(() => {
        vrsteKomunalnihProblemaService.getById(id)
            .then(data => {
                setVrstaKomunalnogProblema(data);
            })
    }, [id]);


    const deleteHandler = () => {
        const confirmed = window.confirm("Da li ste sigurni da želite da obrišete vrstu komunalnog problema?");
        if(confirmed) {
            vrsteKomunalnihProblemaService.deleteById(id)
                .then(() => {
                    history.push(`/vrste-komunalnih-problema`);
                })
        }
    }

    const goToFormHandler = () => {
        history.push(`/vrste-komunalnih-problema/form/${vrstaKomunalnogProblema.id}`);
    }

    return (
        <div className="w-50 mx-auto">
            <Table striped bordered hover>
                <tbody>
                <tr>
                    <th>#</th>
                    <td>{vrstaKomunalnogProblema.id}</td>
                </tr>
                <tr>
                    <th>Naziv</th>
                    <td>{vrstaKomunalnogProblema.naziv}</td>
                </tr>
                </tbody>
            </Table>
            <div className="w-100 d-flex justify-content-between my-3">
                <Button variant="danger" onClick={deleteHandler}>Obriši vrstu komunalnog problema</Button>
                <Button variant="dark" onClick={goToFormHandler}>Izmeni vrstu komunalnog problema</Button>
            </div>
        </div>
    );
}

export default VrsteKomunalnihProblemaDetails;