import React, {useEffect, useState} from 'react';
import {useHistory} from "react-router-dom";
import {Button, Table} from "react-bootstrap"
import komunalniProblemiService from "../../services/api/komunalni-problemi-service";
import authService from "../../services/auth-service";

const dateOptions = { year: 'numeric', month: 'numeric', day: 'numeric', hour: "numeric", minute: "numeric" };

const KomunalniProblemi = () =>{

    const history = useHistory();
    const [komunalniProblemi, setKomunalniProblemi] = useState([]);

    useEffect(() => {
        komunalniProblemiService.getAll()
            .then((data) => {
                setKomunalniProblemi(data);
            })
    }, []);

    const goToDetailsHandler = (id) => {
        history.push(`/komunalni-problemi/${id}`);
    }

    const goToFormHandler = () => {
        history.push(`/komunalni-problemi/form`);
    }

    return (
        <>
            {
                authService.isPodnosilac() &&
                <div className="w-100 d-flex justify-content-end my-3">
                    <Button variant="dark" onClick={goToFormHandler}>Podnesi komunalni problem</Button>
                </div>
            }
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Datum podnošenja</th>
                    <th>Datum događaja</th>
                    <th>Adresa događaja</th>
                    <th>Mesto događaja</th>
                    <th>Podnosilac</th>
                    <th>Vrsta problema</th>
                    <th>Izveštaj</th>
                </tr>
                </thead>

                <tbody>
                {
                    komunalniProblemi.map(komunalniProblem => {
                        const datumPodnosenja = new Date(komunalniProblem.datumPodnosenja);
                        const datumDogadjaja = new Date(komunalniProblem.datumDogadjaja);

                        let izvestaj = "Na čekanju";
                        if(komunalniProblem.izvestaj) {
                            if(komunalniProblem.izvestaj.prihvaceno) {
                                izvestaj = "Prihvaćen";
                            } else {
                                izvestaj = "Odbijen";
                            }
                        }

                        return (
                            <tr key={komunalniProblem.id} className="pointer"
                                onClick={() => goToDetailsHandler(komunalniProblem.id)}
                                >
                                <td>{komunalniProblem.id}</td>
                                <td>{datumPodnosenja.toLocaleDateString("de-DE", dateOptions)}</td>
                                <td>{datumDogadjaja.toLocaleDateString("de-DE", dateOptions)}</td>
                                <td>{komunalniProblem.adresaDogadjaja}</td>
                                <td>{komunalniProblem.mestoDogadjaja}</td>
                                <td>{`${komunalniProblem.podnosilac.ime} ${komunalniProblem.podnosilac.prezime}`}</td>
                                <td>{komunalniProblem.vrstaKomunalnogProblema.naziv}</td>
                                <td>{izvestaj}</td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </Table>
        </>
    );

}

export default KomunalniProblemi;