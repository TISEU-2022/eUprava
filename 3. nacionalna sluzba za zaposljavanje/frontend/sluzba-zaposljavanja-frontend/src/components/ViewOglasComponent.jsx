import {getAdvertisementById} from "../services/OglasService";
import React, { useEffect, useState } from "react";
import {useLocation, useParams} from 'react-router-dom';

const ViewOglasComponent = (id) => {
    const [oglas, setOglas] = useState({});
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
                            <label style={{color:"black", fontWeight:"400"}}>Naziv: {oglas.naziv}   </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}>Opis: {oglas.opis}  </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}>Datum Od: {oglas.datumOd} </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}>Datum Do: {oglas.datumDo} </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}> Firma: </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}> Vrsta Posla: </label>
                        </div>

                        <br/><br/>
                        
                    
                    </div>
                </div>    
                </div>   
            </div>  
        );
    }

 
export default ViewOglasComponent;