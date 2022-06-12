import {useHistory} from "react-router-dom";
import React, {useEffect, useState} from "react";
import vrsteKomunalnihProblemaService from "../../../services/api/vrste-komunalnih-problema-service";
import komunalniProblemiService from "../../../services/api/komunalni-problemi-service";
import Input from "../../util/Input/Input";
import Select from "../../util/Select/Select";
import TextArea from "../../util/TextArea/TextArea";
import {Button} from "react-bootstrap";

const today = new Date();

const KomunalniProblemiForm = () =>{

    const history = useHistory();
    const [vrsteKomunalnihProblema, setVrsteKomunalnihProblema] = useState([]);
    const [komunalniProblem, setKomunalniProblem] = useState({
        adresaDogadjaja: "",
        mestoDogadjaja: "",
        datumDogadjaja: today.toISOString().slice(0,10),
        vrstaKomunalnogProblema: {
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
        vrsteKomunalnihProblemaService.getAll()
            .then((data) => {
                if(data)
                    changeVrstaKomunalnogProblemaHandler(data[0].id)
                setVrsteKomunalnihProblema(data);
            })
    }, [])

    const changeImeHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            podnosilac: {
                ...prevVrstaKomunalnogProblema.podnosilac,
                ime: value
            }
        }))
    }

    const changePrezimeHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            podnosilac: {
                ...prevVrstaKomunalnogProblema.podnosilac,
                prezime: value
            }
        }))
    }

    const changeJmbgHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            podnosilac: {
                ...prevVrstaKomunalnogProblema.podnosilac,
                jmbg: value
            }
        }))
    }

    const changeAdresaHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            podnosilac: {
                ...prevVrstaKomunalnogProblema.podnosilac,
                adresa: value
            }
        }))
    }

    const changeMestoHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            podnosilac: {
                ...prevVrstaKomunalnogProblema.podnosilac,
                mesto: value
            }
        }))
    }

    const changeEmailHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            podnosilac: {
                ...prevVrstaKomunalnogProblema.podnosilac,
                email: value
            }
        }))
    }

    const changeTelefonHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            podnosilac: {
                ...prevVrstaKomunalnogProblema.podnosilac,
                telefon: value
            }
        }))
    }

    const changePttBrojHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            podnosilac: {
                ...prevVrstaKomunalnogProblema.podnosilac,
                pttBroj: value
            }
        }))
    }

    const changeAdresaDogadjajaHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            adresaDogadjaja: value
        }))
    }

    const changeMestoDogadjajaHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            mestoDogadjaja: value
        }))
    }

    const changeDatumDogadjajaHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            datumDogadjaja: value
        }))
    }

    const changeOpisHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            opis: value
        }))
    }


    const changeVrstaKomunalnogProblemaHandler = (value) => {
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            vrstaKomunalnogProblema: {
                id: value
            }
        }))
    }

    const addDatotekaHandler = (value) => {
        let image = value.split(",")[1];
        setKomunalniProblem(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            datoteke: [
                ...prevVrstaKomunalnogProblema.datoteke,
                image
            ]
        }))
    }

    const submitFormHandler = (event) => {
        event.preventDefault();

        if(!komunalniProblem.podnosilac.ime) {
            alert("Unesite ime!");
            return;
        }

        if(!komunalniProblem.podnosilac.prezime) {
            alert("Unesite prezime!");
            return;
        }

        if(!komunalniProblem.podnosilac.jmbg) {
            alert("Unesite JMBG!");
            return;
        }

        if(komunalniProblem.podnosilac.jmbg && komunalniProblem.podnosilac.jmbg.length !== 13) {
            alert("JMBG mora imati tačno 13 brojeva!");
            return;
        }

        if(!komunalniProblem.podnosilac.email) {
            alert("Unesite email adresu!");
            return;
        }

        if(!komunalniProblem.podnosilac.telefon) {
            alert("Unesite broj telefona!");
            return;
        }

        if(!komunalniProblem.podnosilac.adresa) {
            alert("Unesite adresu prebivališta!");
            return;
        }

        if(!komunalniProblem.podnosilac.mesto) {
            alert("Unesite mesto prebivališta!");
            return;
        }

        if(!komunalniProblem.podnosilac.pttBroj) {
            alert("Unesite poštanski broj prebivališta!");
            return;
        }

        if(!komunalniProblem.adresaDogadjaja) {
            alert("Unesite adresu događaja!");
            return;
        }

        if(!komunalniProblem.mestoDogadjaja) {
            alert("Unesite mesto događaja!");
            return;
        }

        if(!komunalniProblem.datumDogadjaja) {
            alert("Unesite datum događaja!");
            return;
        }

        if(!komunalniProblem.opis) {
            alert("Unesite opis komunalnog problema!");
            return;
        }

        komunalniProblemiService.create(komunalniProblem)
            .then((response) => {
                    history.push(`/komunalni-problemi/${response.data}`);
            }).catch(err=>{
                console.log(err);
            })
    }

    const options = vrsteKomunalnihProblema.map(vrstaKomunalnogProblema => ({
        value: vrstaKomunalnogProblema.id,
        name: vrstaKomunalnogProblema.naziv
    }));

    return (
        <form className="w-50 mx-auto">
            <div>
                <h3>Podnosilac</h3>
                <Input type="text" title="Ime" value={komunalniProblem.podnosilac.ime} setValue={changeImeHandler}/>
                <Input type="text" title="Prezime" value={komunalniProblem.podnosilac.prezime} setValue={changePrezimeHandler}/>
                <Input type="text" title="JMBG" value={komunalniProblem.podnosilac.jmbg} setValue={changeJmbgHandler}/>
                <Input type="text" title="Email" value={komunalniProblem.podnosilac.email} setValue={changeEmailHandler}/>
                <Input type="text" title="Broj telefona" value={komunalniProblem.podnosilac.telefon} setValue={changeTelefonHandler}/>
                <Input type="text" title="Adresa prebivališta" value={komunalniProblem.podnosilac.adresa} setValue={changeAdresaHandler}/>
                <Input type="text" title="Mesto prebivališta" value={komunalniProblem.podnosilac.mesto} setValue={changeMestoHandler}/>
                <Input type="text" title="Poštanski broj prebivališta" value={komunalniProblem.podnosilac.pttBroj} setValue={changePttBrojHandler}/>
            </div>
            <div className="mt-5">
                <h3>Komunalni problem</h3>
                <Input type="text" title="Adresa događaja" value={komunalniProblem.adresaDogadjaja} setValue={changeAdresaDogadjajaHandler}/>
                <Input type="text" title="Mesto događaja" value={komunalniProblem.mestoDogadjaja} setValue={changeMestoDogadjajaHandler}/>
                <Input type="date" title="Datum događaja" value={komunalniProblem.datumDogadjaja} setValue={changeDatumDogadjajaHandler}/>
                <Select title="Vrsta komunalnog problema" value={komunalniProblem.vrstaKomunalnogProblema.id} setValue={changeVrstaKomunalnogProblemaHandler} options={options}/>
                <TextArea title="Opis" value={komunalniProblem.opis} setValue={changeOpisHandler}/>
                <Input type="file" title="Unesite datoteke" setValue={addDatotekaHandler}/>
                {
                    komunalniProblem.datoteke.map((datoteka, index) => (
                        <img style={{maxWidth: "100%", objectFit: "cover"}} src={"data:image/png;base64, " + datoteka} alt="Slika komunalnog problema"/>
                    ))
                }
            </div>
            <div className="w-100 d-flex justify-content-end my-3">
                <Button type="submit" variant="dark" onClick={submitFormHandler}>Podnesi</Button>
            </div>
        </form>
    );

}

export default KomunalniProblemiForm;