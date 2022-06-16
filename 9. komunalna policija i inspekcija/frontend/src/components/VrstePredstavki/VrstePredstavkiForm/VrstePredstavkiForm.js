import React, {useEffect, useState} from 'react';
import {useHistory, useParams} from "react-router-dom";
import Input from "../../util/Input/Input";
import {Button} from "react-bootstrap";
import vrstePredstavkiService from "../../../services/api/vrste-predstavki-service";

const VrstePredstavkiForm = () => {

    const { id } = useParams();
    const history = useHistory();
    const [vrstaPredstavke, setVrstaPredstavke] = useState({
        id: NaN,
        naziv: ""
    });

    useEffect(() => {
        if(id) {
            vrstePredstavkiService.getById(id)
                .then(data => {
                    setVrstaPredstavke(data);
                })
        }
    }, [id]);

    const changeNazivHandler = (value) => {
        setVrstaPredstavke(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            naziv: value
        }))
    }

    const submitFormHandler = (event) => {
        event.preventDefault();
        if(id) {
            vrstePredstavkiService.update(id, vrstaPredstavke)
                .then(() => {
                    history.push(`/vrste-predstavki/${id}`);
                })
        } else {
            vrstePredstavkiService.create(vrstaPredstavke)
                .then((data) => {
                    history.push(`/vrste-predstavki/${data}`);
                })
        }
    }

    return (
        <form className="w-50 mx-auto">
            <Input type="text" title="Naziv" value={vrstaPredstavke.naziv} setValue={changeNazivHandler}/>
            <div className="w-100 d-flex justify-content-end my-3">
                <Button type="submit" variant="dark" onClick={submitFormHandler}>{ id ? "Izmeni": "Kreiraj"}</Button>
            </div>
        </form>
    );
};

export default VrstePredstavkiForm;