import React, {useEffect, useState} from 'react';
import {useHistory} from "react-router-dom";
import Input from "../../util/Input/Input";
import {Button} from "react-bootstrap";
import predstavkeService from "../../../services/api/predstavke-service";
import vrstePredstavkiService from "../../../services/api/vrste-predstavki-service";
import Select from "../../util/Select/Select";
import TextArea from "../../util/TextArea/TextArea";

const today = new Date();

const PredstavkeForm = () => {

    const history = useHistory();
    const [vrstePredstavki, setVrstePredstavki] = useState([]);
    const [predstavka, setPredstavka] = useState({
        naslov: "",
        adresaDogadjaja: "",
        mestoDogadjaja: "",
        datumDogadjaja: today.toISOString().slice(0,10),
        vrstaPredstavke: {
            id: 0
        },
        opis: "",
        datoteke: [],
        podnosilac: {
            ime: "",
            prezime: "",
            jmbg: "",
            adresa: "",
            mesto: "",
            email: "",
            telefon: "",
            pttBroj: 21000
        }
    });

    useEffect(() => {
        vrstePredstavkiService.getAll()
            .then((data) => {
                if(data)
                    changeVrstaPredstavkeHandler(data[0].id)
                setVrstePredstavki(data);
            })
    }, [])

    const changeImeHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            podnosilac: {
                ...prevVrstaPredstavke.podnosilac,
                ime: value
            }
        }))
    }

    const changePrezimeHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            podnosilac: {
                ...prevVrstaPredstavke.podnosilac,
                prezime: value
            }
        }))
    }

    const changeJmbgHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            podnosilac: {
                ...prevVrstaPredstavke.podnosilac,
                jmbg: value
            }
        }))
    }

    const changeAdresaHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            podnosilac: {
                ...prevVrstaPredstavke.podnosilac,
                adresa: value
            }
        }))
    }

    const changeMestoHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            podnosilac: {
                ...prevVrstaPredstavke.podnosilac,
                mesto: value
            }
        }))
    }

    const changeEmailHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            podnosilac: {
                ...prevVrstaPredstavke.podnosilac,
                email: value
            }
        }))
    }

    const changeTelefonHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            podnosilac: {
                ...prevVrstaPredstavke.podnosilac,
                telefon: value
            }
        }))
    }

    const changePttBrojHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            podnosilac: {
                ...prevVrstaPredstavke.podnosilac,
                pttBroj: value
            }
        }))
    }

    const changeNaslovHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            naslov: value
        }))
    }

    const changeAdresaDogadjajaHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            adresaDogadjaja: value
        }))
    }

    const changeMestoDogadjajaHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            mestoDogadjaja: value
        }))
    }

    const changeDatumDogadjajaHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            datumDogadjaja: value
        }))
    }

    const changeVrstaPredstavkeHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            vrstaPredstavke: {
                id: value
            }
        }))
    }

    const changeOpisHandler = (value) => {
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            opis: value
        }))
    }

    const addDatotekaHandler = (value) => {
        console.log(value)
        console.log(predstavka.datoteke)
        let image = value.split(",")[1];
        console.log(image);
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            datoteke: [
                ...prevVrstaPredstavke.datoteke,
                image
            ]
        }))
    }
        console.log(predstavka.datoteke)

    const submitFormHandler = (event) => {
        event.preventDefault();
        console.log(predstavka)
        predstavkeService.create(predstavka)
            .then((data) => {
                history.push(`/predstavke/${data}`);
            })
    }

    const options = vrstePredstavki.map(vrstaPredstavke => ({
        value: vrstaPredstavke.id,
        name: vrstaPredstavke.naziv
    }));

    return (
        <form className="w-50 mx-auto">
            <div>
                <h3>Podnosilac</h3>
                <Input type="text" title="Ime" value={predstavka.podnosilac.ime} setValue={changeImeHandler}/>
                <Input type="text" title="Prezime" value={predstavka.podnosilac.prezime} setValue={changePrezimeHandler}/>
                <Input type="text" title="JMBG" value={predstavka.podnosilac.jmbg} setValue={changeJmbgHandler}/>
                <Input type="text" title="Email" value={predstavka.podnosilac.email} setValue={changeEmailHandler}/>
                <Input type="text" title="Broj telefona" value={predstavka.podnosilac.telefon} setValue={changeTelefonHandler}/>
                <Input type="text" title="Adresa" value={predstavka.podnosilac.adresa} setValue={changeAdresaHandler}/>
                <Input type="text" title="Mesto" value={predstavka.podnosilac.mesto} setValue={changeMestoHandler}/>
                <Input type="text" title="PTT broj" value={predstavka.podnosilac.pttBroj} setValue={changePttBrojHandler}/>
            </div>
            <div className="mt-5">
                <h3>Predstavka</h3>
                <Input type="text" title="Naslov" value={predstavka.naslov} setValue={changeNaslovHandler}/>
                <Input type="text" title="Adresa doga??aja" value={predstavka.adresaDogadjaja} setValue={changeAdresaDogadjajaHandler}/>
                <Input type="text" title="Mesto doga??aja" value={predstavka.mestoDogadjaja} setValue={changeMestoDogadjajaHandler}/>
                <Input type="date" title="Datum doga??aja" value={predstavka.datumDogadjaja} setValue={changeDatumDogadjajaHandler}/>
                <Select title="Vrsta predstavke" value={predstavka.vrstaPredstavke.id} setValue={changeVrstaPredstavkeHandler} options={options}/>
                <TextArea title="Opis" value={predstavka.opis} setValue={changeOpisHandler}/>
                <Input type="file" title="Unesite datoteke" setValue={addDatotekaHandler}/>
                {
                    predstavka.datoteke.map((datoteka, index) => (
                        <img style={{maxWidth: "100%", objectFit: "cover"}} src={"data:image/png;base64, " + datoteka} alt={`${predstavka.naslov} - ${index}`}/>
                    ))
                }
            </div>
            <div className="w-100 d-flex justify-content-end my-3">
                <Button type="submit" variant="dark" onClick={submitFormHandler}>Kreiraj</Button>
            </div>
        </form>
    );
};

export default PredstavkeForm;