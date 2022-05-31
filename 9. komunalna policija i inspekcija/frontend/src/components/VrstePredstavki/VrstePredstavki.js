import {useEffect, useState} from 'react';
import {Button, Table} from "react-bootstrap";
import {useHistory} from "react-router-dom";
import vrstePredstavkiService from "../../services/api/vrste-predstavki-service";

const VrstePredstavki = () => {

    const history = useHistory();
    const [vrstePredstavki, setVrstePredstavki] = useState([]);

    useEffect(() => {
        vrstePredstavkiService.getAll()
            .then((data) => {
                setVrstePredstavki(data);
            })
    }, [])

    const goToDetailsHandler = (id) => {
        history.push(`/vrste-predstavki/${id}`);
    }

    const goToFormHandler = () => {
        history.push(`/vrste-predstavki/form`);
    }

    return (
        <>
            <div className="w-100 d-flex justify-content-end my-3">
                <Button variant="dark" onClick={goToFormHandler}>Kreiraj vrstu predstavke</Button>
            </div>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Naziv</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        vrstePredstavki.map((vrstaPredstavke) => (
                            <tr key={vrstaPredstavke.id} className="pointer" onClick={() => goToDetailsHandler(vrstaPredstavke.id)}>
                                <td>{vrstaPredstavke.id}</td>
                                <td>{vrstaPredstavke.naziv}</td>
                            </tr>
                        ))
                    }
                </tbody>
            </Table>
        </>
    );
};

export default VrstePredstavki;