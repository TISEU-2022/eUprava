import React, {useEffect, useState} from 'react';
import {useHistory} from "react-router-dom";
import predstavkeService from "../../services/api/predstavke-service";
import {Button, Table} from "react-bootstrap";
import authService from "../../services/auth-service";

const dateOptions = { year: 'numeric', month: 'numeric', day: 'numeric', hour: "numeric", minute: "numeric" };
const dateAndTimeOptions = { year: 'numeric', month: 'numeric', day: 'numeric' };

const Predstavke = () => {

    const history = useHistory();
    const [predstavke, setPredstavke] = useState([]);

    useEffect(() => {
        predstavkeService.getAll()
            .then((data) => {
                setPredstavke(data);
            })
    }, []);

    const goToDetailsHandler = (id) => {
        history.push(`/predstavke/${id}`);
    }

    const goToFormHandler = () => {
        history.push(`/predstavke/form`);
    }

    return (
        <>
            {
                authService.isPodnosilac() &&
                <div className="w-100 d-flex justify-content-end my-3">
                    <Button variant="dark" onClick={goToFormHandler}>Podnesi predstavku</Button>
                </div>
            }
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>#</th>
                    <th>Naslov</th>
                    <th>Vreme podnošenja</th>
                    <th>Adresa događaja</th>
                    <th>Mesto događaja</th>
                    <th>Datum događaja</th>
                    <th>Podnosilac</th>
                    <th>Vrsta predstavke</th>
                    <th>Izveštaj</th>
                </tr>
                </thead>
                <tbody>
                {
                    predstavke.map((predstavka) => {
                        const vremePodnosenja = new Date(predstavka.vremePodnosenja);
                        const datumDogadjaja = new Date(predstavka.datumDogadjaja);

                        let izvestaj = "Na čekanju";
                        if(predstavka.izvestaj) {
                            if(predstavka.izvestaj.prihvaceno) {
                                izvestaj = "Prihvaćen";
                            } else {
                                izvestaj = "Odbijen";
                            }
                        }

                        return (
                            <tr key={predstavka.id} className="pointer"
                                onClick={() => goToDetailsHandler(predstavka.id)}>
                                <td>{predstavka.id}</td>
                                <td>{predstavka.naslov}</td>
                                <td>{vremePodnosenja.toLocaleDateString("de-DE", dateOptions)}</td>
                                <td>{predstavka.adresaDogadjaja}</td>
                                <td>{predstavka.mestoDogadjaja}</td>
                                <td>{datumDogadjaja.toLocaleDateString("de-DE", dateAndTimeOptions)}</td>
                                <td>{`${predstavka.podnosilac.ime} ${predstavka.podnosilac.prezime}`}</td>
                                <td>{predstavka.vrstaPredstavke.naziv}</td>
                                <td>{izvestaj}</td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </Table>
        </>
    );
};

export default Predstavke;