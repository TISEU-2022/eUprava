import {useHistory, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import komunalniProblemiService from "../../../services/api/komunalni-problemi-service";
import {Table} from "react-bootstrap";

const dateOptions = { year: 'numeric', month: 'numeric', day: 'numeric', hour: "numeric", minute: "numeric" };
const today = new Date();

const KomunalniProblemiDetails = () =>{

    const { id } = useParams();
    const history = useHistory();
    const [komunalniProblem, setKomunalniProblem] = useState({
        adresaDogadjaja: "",
        mestoDogadjaja: "",
        datumDogadjaja: today.toISOString().slice(0,10),
        vrstaKomunalnogProblema: {
            id: 0
        },
        opis: ""
    });

    useEffect(() => {
        komunalniProblemiService.getById(id)
            .then(data => {
                setKomunalniProblem(data);
            })
    }, [id]);

    const goToFormHandler = () => {
        history.push(`/komunalni-problemi/form/${komunalniProblem.id}`);
    }

    const datumPodnosenja = new Date(komunalniProblem.datumPodnosenja);
    const datumDogadjaja = new Date(komunalniProblem.datumDogadjaja);

    return (
        <div className="w-50 mx-auto">
            <Table striped bordered hover>
                <tbody>
                <tr>
                    <th>#</th>
                    <td>{komunalniProblem.id}</td>
                </tr>
                <tr>
                    <th>Datum podnošenja</th>
                    <td>{datumPodnosenja.toLocaleDateString("de-DE", dateOptions)}</td>
                </tr>
                <tr>
                    <th>Adresa događaja</th>
                    <td>{komunalniProblem.adresaDogadjaja}</td>
                </tr>
                <tr>
                    <th>Mesto događaja</th>
                    <td>{komunalniProblem.mestoDogadjaja}</td>
                </tr>
                <tr>
                    <th>Datum događaja</th>
                    <td>{datumDogadjaja.toLocaleDateString("de-DE", dateOptions)}</td>
                </tr>
                <tr>
                    <th>Vrsta komunalnog problema</th>
                    <td>{komunalniProblem.vrstaKomunalnogProblema.naziv}</td>
                </tr>
                <tr>
                    <th>Opis</th>
                    <td>{komunalniProblem.opis}</td>
                </tr>
                </tbody>
            </Table>
            {
                komunalniProblem.datoteke && komunalniProblem.datoteke.length > 0 && (
                    <div className="mt-5">
                        <h3>Dokazi</h3>
                        {
                            komunalniProblem.datoteke.map((datoteka, index) => (
                                <img style={{maxWidth: "100%", objectFit: "cover"}} src={"data:image/png;base64, " + datoteka} alt="Dokaz komunalnog problema"/>
                            ))
                        }
                    </div>)
            }
        </div>
    );

}

export default KomunalniProblemiDetails;