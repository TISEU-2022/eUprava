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
        let image = value.split(",")[1];
        setPredstavka(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            datoteke: [
                ...prevVrstaPredstavke.datoteke,
                image
            ]
        }))
    }

    const submitFormHandler = (event) => {
        event.preventDefault();

        if(!predstavka.podnosilac.ime) {
            alert("Unesite ime!");
            return;
        }

        if(!predstavka.podnosilac.prezime) {
            alert("Unesite prezime!");
            return;
        }

        if(!predstavka.podnosilac.jmbg) {
            alert("Unesite JMBG!");
            return;
        }

        if(predstavka.podnosilac.jmbg && predstavka.podnosilac.jmbg.length !== 13) {
            alert("JMBG mora imati tačno 13 brojeva!");
            return;
        }

        if(!predstavka.podnosilac.email) {
            alert("Unesite email adresu!");
            return;
        }

        if(!predstavka.podnosilac.telefon) {
            alert("Unesite broj telefona!");
            return;
        }

        if(!predstavka.podnosilac.adresa) {
            alert("Unesite adresu prebivališta!");
            return;
        }

        if(!predstavka.podnosilac.mesto) {
            alert("Unesite mesto prebivališta!");
            return;
        }

        if(!predstavka.podnosilac.pttBroj) {
            alert("Unesite poštanski broj prebivališta!");
            return;
        }

        if(!predstavka.naslov) {
            alert("Unesite naslov predstavke!");
            return;
        }

        if(!predstavka.adresaDogadjaja) {
            alert("Unesite adresu događaja!");
            return;
        }

        if(!predstavka.mestoDogadjaja) {
            alert("Unesite mesto događaja!");
            return;
        }

        if(!predstavka.datumDogadjaja) {
            alert("Unesite datum događaja!");
            return;
        }

        if(!predstavka.opis) {
            alert("Unesite opis predstavke!");
            return;
        }

        predstavkeService.create(predstavka)
            .then((response) => {
                    history.push(`/predstavke/${response.data}`);
            }).catch(err=>{
                console.log(err);
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
                <Input type="text" title="Adresa prebivališta" value={predstavka.podnosilac.adresa} setValue={changeAdresaHandler}/>
                <Input type="text" title="Mesto prebivališta" value={predstavka.podnosilac.mesto} setValue={changeMestoHandler}/>
                <Input type="text" title="Poštanski broj prebivališta" value={predstavka.podnosilac.pttBroj} setValue={changePttBrojHandler}/>
            </div>
            <div className="mt-5">
                <h3>Predstavka</h3>
                <Input type="text" title="Naslov predstavke" value={predstavka.naslov} setValue={changeNaslovHandler}/>
                <Input type="text" title="Adresa događaja" value={predstavka.adresaDogadjaja} setValue={changeAdresaDogadjajaHandler}/>
                <Input type="text" title="Mesto događaja" value={predstavka.mestoDogadjaja} setValue={changeMestoDogadjajaHandler}/>
                <Input type="date" title="Datum događaja" value={predstavka.datumDogadjaja} setValue={changeDatumDogadjajaHandler}/>
                <Select title="Vrsta predstavke" value={predstavka.vrstaPredstavke.id} setValue={changeVrstaPredstavkeHandler} options={options}/>
                <TextArea title="Opis predstavke" value={predstavka.opis} setValue={changeOpisHandler}/>
                <Input type="file" title="Unesite datoteke" setValue={addDatotekaHandler}/>
                {
                    predstavka.datoteke.map((datoteka, index) => (
                        <img style={{maxWidth: "100%", objectFit: "cover"}} src={"data:image/png;base64, " + datoteka} alt={`${predstavka.naslov} - ${index}`}/>
                    ))
                }
            </div>
            <div className="w-100 d-flex justify-content-end my-3">
                <Button type="submit" variant="dark" onClick={submitFormHandler}>Podnesi</Button>
            </div>
        </form>
    );
};

export default PredstavkeForm;