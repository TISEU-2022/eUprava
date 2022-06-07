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
        datoteke: []
    });

    useEffect(() => {
        vrsteKomunalnihProblemaService.getAll()
            .then((data) => {
                if(data)
                    changeVrstaKomunalnogProblemaHandler(data[0].id)
                setVrsteKomunalnihProblema(data);
            })
    }, [])

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
        console.log(value)
        console.log(komunalniProblem.datoteke)
        let image = value.split(",")[1];
        console.log(image);
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
        console.log(komunalniProblem)
        komunalniProblemiService.create(komunalniProblem)
            .then((data) => {
                history.push(`/komunalni-problemi/${data}`);
            })
    }

    const options = vrsteKomunalnihProblema.map(vrstaKomunalnogProblema => ({
        value: vrstaKomunalnogProblema.id,
        name: vrstaKomunalnogProblema.naziv
    }));

    return (
        <form className="w-50 mx-auto">
            <Input type="text" title="Adresa događaja" value={komunalniProblem.adresaDogadjaja} setValue={changeAdresaDogadjajaHandler}/>
            <Input type="text" title="Mesto događaja" value={komunalniProblem.mestoDogadjaja} setValue={changeMestoDogadjajaHandler}/>
            <Input type="date" title="Datum događaja" value={komunalniProblem.datumDogadjaja} setValue={changeDatumDogadjajaHandler}/>
            <Select title="Vrsta predstavke" value={komunalniProblem.vrstaKomunalnogProblema.id} setValue={changeVrstaKomunalnogProblemaHandler} options={options}/>
            <TextArea title="Opis" value={komunalniProblem.opis} setValue={changeOpisHandler}/>
            <Input type="file" title="Unesite datoteke" setValue={addDatotekaHandler}/>
            {
                komunalniProblem.datoteke.map((datoteka, index) => (
                    <img style={{maxWidth: "100%", objectFit: "cover"}} src={"data:image/png;base64, " + datoteka} alt="Slika komunalnog problema"/>
                ))
            }
            <div className="w-100 d-flex justify-content-end my-3">
                <Button type="submit" variant="dark" onClick={submitFormHandler}>Kreiraj</Button>
            </div>
        </form>
    );

}

export default KomunalniProblemiForm;