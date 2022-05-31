import React, {useEffect, useState} from 'react';
import {useHistory, useParams} from "react-router-dom";
import axios from "axios";
import Input from "../../util/Input/Input";
import {Button} from "react-bootstrap";

const VrstePredstavkiForm = () => {

    const { id } = useParams();
    const history = useHistory();
    const [vrstaPredstavke, setVrstaPredstavke] = useState({
        id: NaN,
        naziv: ""
    });

    useEffect(() => {
        axios.get(`/vrsta-predstavke/${id}`)
            .then((response) => {
                setVrstaPredstavke(response.data);
            })
    }, []);

    const changeNazivHandler = (value) => {
        setVrstaPredstavke(prevVrstaPredstavke => ({
            ...prevVrstaPredstavke,
            naziv: value
        }))
    }

    const submitFormHandler = (event) => {
        event.preventDefault();
        if(id) {
            axios.put(`/vrsta-predstavke/${id}`, vrstaPredstavke)
                .then((response) => {
                    history.push(`/vrste-predstavki/${id}`);
                })
        } else {
            axios.post(`/vrsta-predstavke`, vrstaPredstavke)
                .then((response) => {
                    history.push(`/vrste-predstavki/${response.data}`);
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