import React, {useEffect, useState} from 'react';
import {useHistory, useParams} from "react-router-dom";
import predstavkeService from "../../../services/api/predstavke-service";
import {Button, Table} from "react-bootstrap";
import Modal from 'react-modal';
import authService from "../../../services/auth-service";

const dateOptions = { year: 'numeric', month: 'numeric', day: 'numeric', hour: "numeric", minute: "numeric" };
const dateAndTimeOptions = { year: 'numeric', month: 'numeric', day: 'numeric' };
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
        predstavkeService.getById(id)
            .then(data => {
                setPredstavka(data);
            })
    }, [id]);

    function modalStateHandler(){
        modalIsOpen ? setModalIsOpen(false) : setModalIsOpen(true)
    }

    const goToFormHandler = () => {
        history.push(`/vrste-predstavki/form/${predstavka.id}`);
    }

    const submitFormHandler = (event) =>{
        event.preventDefault();
        predstavkeService.writeIzvestaj(id, izvestajRequestDTO)
            .then(()=>{
                history.push(`/predstavke`)
            });
    }

    const rejectIzvestajHandler = (event) =>{
        event.preventDefault();
        predstavkeService.rejectIzvestaj(id)
            .then(() =>{
                history.push(`/predstavke`)
            })
    }

    const vremePodnosenja = new Date(predstavka.vremePodnosenja);
    const datumDogadjaja = new Date(predstavka.datumDogadjaja);
    const datumPodnosenjaIzvestaja = predstavka.izvestaj ? new Date(predstavka.izvestaj.vremePodnosenja) : null ;

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
                <div className="my-5">
                    <h3>Dokazi</h3>
                    {
                        predstavka.datoteke.map((datoteka, index) => (
                            <img key={index} style={{maxWidth: "100%", objectFit: "cover"}} src={"data:image/png;base64, " + datoteka} alt={`${predstavka.naslov} - ${index}`}/>
                        ))
                    }
                </div>)
            }
            {predstavka.izvestaj ? 
            (
            <>
            <h3 style={{textAlign: "center"}}>Izveštaj {predstavka.izvestaj.prihvaceno ? "(PRIHVAĆENO)"  : "(ODBIJENO)"}</h3>
            <Table striped bordered hover>
                <tbody>
                <tr>
                    <th>#</th>
                    <td>{predstavka.izvestaj.id}</td>
                </tr>
                <tr>
                    <th>Datum podnošenja</th>
                    <td>{datumPodnosenjaIzvestaja.toLocaleDateString("de-DE", dateOptions)}</td>
                </tr>
                <tr>
                    <th>Izveštaj napisao</th>
                    <td>{`${predstavka.izvestaj.sluzbenikDTO.ime} ${predstavka.izvestaj.sluzbenikDTO.prezime}`}</td>
                </tr>
                <tr>
                    <th>Izveštaj</th>
                    <td>{predstavka.izvestaj.izvestaj}</td>
                </tr>
                </tbody>
            </Table>
            </>
            ) : null}
            {!predstavka.izvestaj && authService.isSluzbenik() ? (<>
            <Button style={{marginRight: "5px", marginBottom: "3rem"}} onClick={modalStateHandler}>Napiši izveštaj</Button>
            <Button style={{marginBottom: "3rem"}} variant="danger" onClick={rejectIzvestajHandler}>Odbij izveštaj</Button>
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
};

export default PredstavkeDetails;