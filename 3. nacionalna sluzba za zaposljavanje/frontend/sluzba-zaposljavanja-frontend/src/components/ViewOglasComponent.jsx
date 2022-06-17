import {getAdvertisementById} from "../services/OglasService";
import React, { useEffect, useState } from "react";
import {useLocation, useNavigate, useParams} from 'react-router-dom';

const ViewOglasComponent = (id) => {
    const [oglas, setOglas] = useState({});
    const navigate = useNavigate();
    const params = useParams();

    useEffect(() => {
        getAdvertisementById(params.id).then((response) => {
            console.log(response);
            setOglas(response);
          }).catch(error => {
            console.log(error);
          })
    }, []);

        return (
            <div className="body">
                <div className="container" style={{marginTop:'50px;'}}>
                    <div className="card col-md-6 offset-md-3" >
                        
                        <div className="text-center" style={{marginTop:"8px"}}>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}><b>Naziv:</b> {oglas.naziv}   </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}><b>Opis:</b> {oglas.opis}  </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}><b>Datum Od:</b> {oglas.datumOd} </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}><b>Datum Do:</b> {oglas.datumDo} </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}> <b>Firma:</b> {oglas.firma.imeFirme}</label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}> <b>Vrsta Posla:</b> {oglas.vrstaPosla.ime}</label>
                        </div>

                        <br/><br/>
                        <button className="btn btn-success" style={{ margin: "8px", width:"150px" }} onClick={() => navigate(`/oglasi/${params.id}`)}>Update</button>
                        
                    
                    </div>
                </div>    
                </div>   
            </div>  
        );
    }

 
export default ViewOglasComponent;