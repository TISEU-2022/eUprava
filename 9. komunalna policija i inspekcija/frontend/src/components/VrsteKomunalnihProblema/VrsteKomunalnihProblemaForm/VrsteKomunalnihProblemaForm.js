import {useHistory, useParams} from "react-router-dom";
import vrsteKomunalnihProblemaService from "../../../services/api/vrste-komunalnih-problema-service";
import {useEffect, useState} from "react";
import Input from "../../util/Input/Input";
import {Button} from "react-bootstrap";


const VrsteKomunalnihProblemaForm = () =>{

    const { id } = useParams();
    const history = useHistory();
    const [vrstaKomunalnogProblema, setVrstaKomunalnogProblema] = useState({
        id: NaN,
        naziv: ""
    });

    useEffect(() => {
        console.log(id);
        if(id) {
            vrsteKomunalnihProblemaService.getById(id)
                .then(data => {
                    setVrstaKomunalnogProblema(data);
                })
        }
    }, [id]);

    const changeNazivHandler = (value) => {
        setVrstaKomunalnogProblema(prevVrstaKomunalnogProblema => ({
            ...prevVrstaKomunalnogProblema,
            naziv: value
        }))
    }

    const submitFormHandler = (event) => {
        event.preventDefault();
        if(id) {
            vrsteKomunalnihProblemaService.update(id, vrstaKomunalnogProblema)
                .then(() => {
                    history.push(`/vrste-komunalnih-problema/${id}`);
                })
        } else {
            vrsteKomunalnihProblemaService.create(vrstaKomunalnogProblema)
                .then((data) => {
                    history.push(`/vrste-komunalnih-problema/${data}`);
                })
        }
    }


    return (
        <form className="w-50 mx-auto">
            <Input type="text" title="Naziv" value={vrstaKomunalnogProblema.naziv} setValue={changeNazivHandler}/>
            <div className="w-100 d-flex justify-content-end my-3">
                <Button type="submit" variant="dark" onClick={submitFormHandler}>{ id ? "Izmeni": "Kreiraj"}</Button>
            </div>
        </form>
    );

}
export default VrsteKomunalnihProblemaForm;