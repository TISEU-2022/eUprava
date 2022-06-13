import {useHistory, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import komunalniProblemiService from "../../../services/api/komunalni-problemi-service";
import {Button, Table} from "react-bootstrap";
import Modal from 'react-modal';
import authService from "../../../services/auth-service";

const dateOptions = { year: 'numeric', month: 'numeric', day: 'numeric', hour: "numeric", minute: "numeric" };
const today = new Date();
const customStyles = {
    overlay: {
        backgroundColor: 'rgba(119,119,119,0.7)'
    },
    content: {
      top: '50%',
      left: '50%',
      right: 'auto',
      bottom: 'auto',
      marginRight: '-50%',
      transform: 'translate(-50%, -50%)',

      boxShadow: '0 0 10px rgba(0,0,0,0.6)',
      MozBoxShadow: "0 0 10px rgba(0,0,0,0.6)",
      WebkitBoxShadow: "0 0 10px rgba(0,0,0,0.6)",
      OBoxShadow: "0 0 10px rgba(0,0,0,0.6)",
    },

  };

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
        opis: "",
        podnosilac: {},
        izvestaj: {
            id: 0,
            izvestaj: "",
            prihvaceno: false,
            vremePodnosenja: today.toISOString().slice(0, 10),
            sluzbenikDTO: {
                ime: "",
                prezime: ""
            }
        }
    });
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [izvestajRequestDTO, setReport] = useState({report:""});

    useEffect(() => {
        komunalniProblemiService.getById(id)
            .then(data => {
                setKomunalniProblem(data);
            })
    }, [id]);

    function modalStateHandler(){
        modalIsOpen ? setModalIsOpen(false) : setModalIsOpen(true)
    }

    const submitFormHandler = (event) =>{
        event.preventDefault();
        komunalniProblemiService.writeIzvestaj(id, izvestajRequestDTO)
            .then(()=>{
                history.push(`/komunalni-problemi`)
            });
    }

    const rejectIzvestajHandler = (event) =>{
        event.preventDefault();
        komunalniProblemiService.rejectIzvestaj(id)
            .then(() =>{
                history.push(`/komunalni-problemi`)
            })
    }

    const datumPodnosenja = new Date(komunalniProblem.datumPodnosenja);
    const datumDogadjaja = new Date(komunalniProblem.datumDogadjaja);
    const datumPodnosenjaIzvestaja = komunalniProblem.izvestaj ? new Date(komunalniProblem.izvestaj.vremePodnosenja) : null ;

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
                    <th>Podnosilac</th>
                    <td>{`${komunalniProblem.podnosilac.ime} ${komunalniProblem.podnosilac.prezime}`}</td>
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
                    <div className="my-5">
                        <h3>Dokazi</h3>
                        {
                            komunalniProblem.datoteke.map((datoteka, index) => (
                                <img key={index} style={{maxWidth: "100%", objectFit: "cover"}} src={"data:image/png;base64, " + datoteka} alt={`Dokaz - ${index}`}/>
                            ))
                        }
                    </div>)
            }
            {komunalniProblem.izvestaj ? 
            (
            <>
            <h3 style={{textAlign: "center"}}>Izveštaj {komunalniProblem.izvestaj.prihvaceno ? "(PRIHVAĆENO)"  : "(ODBIJENO)"}</h3>
            <Table striped bordered hover>
                <tbody>
                <tr>
                    <th>#</th>
                    <td>{komunalniProblem.izvestaj.id}</td>
                </tr>
                <tr>
                    <th>Datum podnošenja</th>
                    <td>{datumPodnosenjaIzvestaja.toLocaleDateString("de-DE", dateOptions)}</td>
                </tr>
                <tr>
                    <th>Izveštaj napisao</th>
                    <td>{`${komunalniProblem.izvestaj.sluzbenikDTO.ime} ${komunalniProblem.izvestaj.sluzbenikDTO.prezime}`}</td>
                </tr>
                <tr>
                    <th>Izveštaj</th>
                    <td>{komunalniProblem.izvestaj.izvestaj}</td>
                </tr>
                </tbody>
            </Table>
            </>
            ) : null}
            {!komunalniProblem.izvestaj && authService.isSluzbenik() ? (<>
            <Button style={{marginRight: "5px"}} onClick={modalStateHandler}>Napiši izveštaj</Button>
            <Button variant="danger" onClick={rejectIzvestajHandler}>Odbij izveštaj</Button>
            </>) : null}
            <Modal
                isOpen={modalIsOpen}
                onRequestClose={modalStateHandler}
                style={customStyles}
            >
                <h2>Izveštaj</h2>
                <div>
                    <textarea value={izvestajRequestDTO.report} rows="5" cols="50"  onChange={(event) => setReport(izvestajRequestDTO =>({...izvestajRequestDTO, report: event.target.value}))}/>
                    <div>
                        <Button style={{marginRight: "5px"}} onClick={submitFormHandler}>Pošalji</Button>
                        <Button variant="danger" onClick={modalStateHandler}>Odustani</Button>
                    </div>
                </div>

            </Modal>
        </div>
    );

}

export default KomunalniProblemiDetails;