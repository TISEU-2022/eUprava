import {useEffect, useState} from 'react';
import {useHistory, useParams} from 'react-router-dom';
import {Button, Table} from "react-bootstrap";
import axios from "axios";


const VrstePredstavkiDetails = () => {

    const { id } = useParams();
    const history = useHistory();
    const [vrstaPredstavke, setVrstaPredstavke] = useState({});

    useEffect(() => {
        axios.get(`/vrsta-predstavke/${id}`)
            .then((response) => {
                setVrstaPredstavke(response.data);
            })
    }, []);

    const deleteHandler = () => {
        const confirmed = window.confirm("Da li ste sigurni da želite da obrišete vrstu predstavke?");
        if(confirmed) {
            axios.delete(`/vrsta-predstavke/${id}`)
                .then((response) => {
                    history.push(`/vrste-predstavki/`);
                })
        }
    }

    const goToFormHandler = () => {
        history.push(`/vrste-predstavki/form/${vrstaPredstavke.id}`);
    }


    return (
        <div className="w-50 mx-auto">
            <Table striped bordered hover>
                <tbody>
                <tr>
                    <th>#</th>
                    <td>{vrstaPredstavke.id}</td>
                </tr>
                <tr>
                    <th>Naziv</th>
                    <td>{vrstaPredstavke.naziv}</td>
                </tr>
                </tbody>
            </Table>
            <div className="w-100 d-flex justify-content-between my-3">
                <Button variant="danger" onClick={deleteHandler}>Obriši vrstu predstavke</Button>
                <Button variant="dark" onClick={goToFormHandler}>Izmeni vrstu predstavke</Button>
            </div>
        </div>
    );
};

export default VrstePredstavkiDetails;