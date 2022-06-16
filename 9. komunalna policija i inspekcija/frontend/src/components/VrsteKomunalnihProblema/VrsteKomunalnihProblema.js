import {useHistory} from "react-router-dom";
import vrsteKomunalnihProblemaService from "../../services/api/vrste-komunalnih-problema-service";
import {useEffect, useState} from "react";
import {Button, Table} from "react-bootstrap";

const VrsteKomunalnihProblema = () =>{

    const history = useHistory();
    const [vrsteKomunalnihProblema, setVrsteKomunalnihProblema] = useState([]);

    useEffect(() => {
        vrsteKomunalnihProblemaService.getAll()
            .then((data) => {
                setVrsteKomunalnihProblema(data);
            })
    }, [])

    const goToDetailsHandler = (id) => {
        history.push(`/vrste-komunalnih-problema/${id}`);
    }

    const goToFormHandler = () => {
        history.push(`/vrste-komunalnih-problema/form`);
    }

    return (
        <>
            <div className="w-100 d-flex justify-content-end my-3">
                <Button variant="dark" onClick={goToFormHandler}>Kreiraj vrstu komunalnog problema</Button>
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
                    vrsteKomunalnihProblema.map((vrstaKomunalnogProblema) => (
                        <tr key={vrstaKomunalnogProblema.id} className="pointer" onClick={() => goToDetailsHandler(vrstaKomunalnogProblema.id)}>
                            <td>{vrstaKomunalnogProblema.id}</td>
                            <td>{vrstaKomunalnogProblema.naziv}</td>
                        </tr>
                    ))
                }
                </tbody>
            </Table>
        </>
    );

}

export default VrsteKomunalnihProblema;