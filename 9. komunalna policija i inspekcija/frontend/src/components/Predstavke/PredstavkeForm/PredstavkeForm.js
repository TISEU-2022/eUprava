import {useEffect, useState} from 'react';
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
        opis: ""
    });

    useEffect(() => {
        vrstePredstavkiService.getAll()
            .then((data) => {
                if(data)
                    changeVrstaPredstavkeHandler(data[0].id)
                setVrstePredstavki(data);
            })
    }, [])


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
            <Input type="text" title="Naslov" value={predstavka.naslov} setValue={changeNaslovHandler}/>
            <Input type="text" title="Adresa događaja" value={predstavka.adresaDogadjaja} setValue={changeAdresaDogadjajaHandler}/>
            <Input type="text" title="Mesto događaja" value={predstavka.mestoDogadjaja} setValue={changeMestoDogadjajaHandler}/>
            <Input type="date" title="Datum događaja" value={predstavka.datumDogadjaja} setValue={changeDatumDogadjajaHandler}/>
            <Select title="Vrsta predstavke" value={predstavka.vrstaPredstavke.id} setValue={changeVrstaPredstavkeHandler} options={options}/>
            <TextArea title="Opis" value={predstavka.opis} setValue={changeOpisHandler}/>
            <div className="w-100 d-flex justify-content-end my-3">
                <Button type="submit" variant="dark" onClick={submitFormHandler}>Kreiraj</Button>
            </div>
        </form>
    );
};

export default PredstavkeForm;