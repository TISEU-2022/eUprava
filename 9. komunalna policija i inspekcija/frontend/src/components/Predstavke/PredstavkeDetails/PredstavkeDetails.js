import React, {useEffect, useState} from 'react';
import {useHistory, useParams} from "react-router-dom";
import predstavkeService from "../../../services/api/predstavke-service";
import {Table} from "react-bootstrap";

const dateOptions = { year: 'numeric', month: 'numeric', day: 'numeric', hour: "numeric", minute: "numeric" };
const dateAndTimeOptions = { year: 'numeric', month: 'numeric', day: 'numeric' };
const today = new Date();

const PredstavkeDetails = (props) => {

    const { id } = useParams();
    const history = useHistory();
    const [predstavka, setPredstavka] = useState({
        naslov: "",
        adresaDogadjaja: "",
        mestoDogadjaja: "",
        datumDogadjaja: today.toISOString().slice(0,10),
        vrstaPredstavke: {
            id: 0
        },
        opis: "",
        podnosilac: {}
    });

    useEffect(() => {
        predstavkeService.getById(id)
            .then(data => {
                setPredstavka(data);
            })
    }, [id]);

    const goToFormHandler = () => {
        history.push(`/vrste-predstavki/form/${predstavka.id}`);
    }

    const vremePodnosenja = new Date(predstavka.vremePodnosenja);
    const datumDogadjaja = new Date(predstavka.datumDogadjaja);

    return (
        <div className="w-50 mx-auto">
            <Table striped bordered hover>
                <tbody>
                <tr>
                    <th>#</th>
                    <td>{predstavka.id}</td>
                </tr>
                <tr>
                    <th>Naslov</th>
                    <td>{predstavka.naslov}</td>
                </tr>
                <tr>
                    <th>Vreme podnošenja</th>
                    <td>{vremePodnosenja.toLocaleDateString("de-DE", dateOptions)}</td>
                </tr>
                <tr>
                    <th>Adresa događaja</th>
                    <td>{predstavka.adresaDogadjaja}</td>
                </tr>
                <tr>
                    <th>Mesto događaja</th>
                    <td>{predstavka.mestoDogadjaja}</td>
                </tr>
                <tr>
                    <th>Datum događaja</th>
                    <td>{datumDogadjaja.toLocaleDateString("de-DE", dateAndTimeOptions)}</td>
                </tr>
                <tr>
                    <th>Podnosilac</th>
                    <td>{`${predstavka.podnosilac.ime} ${predstavka.podnosilac.prezime}`}</td>
                </tr>
                <tr>
                    <th>Vrsta predstavke</th>
                    <td>{predstavka.vrstaPredstavke.naziv}</td>
                </tr>
                <tr>
                    <th>Opis</th>
                    <td>{predstavka.opis}</td>
                </tr>
                </tbody>
            </Table>
            {
                predstavka.datoteke && predstavka.datoteke.length > 0 && (
                <div className="mt-5">
                    <h3>Dokazi</h3>
                    {
                        predstavka.datoteke.map((datoteka, index) => (
                            <img style={{maxWidth: "100%", objectFit: "cover"}} src={"data:image/png;base64, " + datoteka} alt={`${predstavka.naslov} - ${index}`}/>
                        ))
                    }
                </div>)
            }
        </div>
    );
};

export default PredstavkeDetails;