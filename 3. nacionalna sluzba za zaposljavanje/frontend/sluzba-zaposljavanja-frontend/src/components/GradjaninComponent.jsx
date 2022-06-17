import {getAdvertisementById} from "../services/OglasService";
import {getCitizenByusername} from "../services/GradjaninService";
import React, { useEffect, useState } from "react";
import {useLocation, useNavigate, useParams} from 'react-router-dom';
import { getAllCitizen } from "../services/GradjaninService";

const GradjaninComponent = () => {
    const [oglas, setOglas] = useState({});
    const [gradjanin, setGradjanin] = useState({});
    const navigate = useNavigate();
    const params = useParams();

    useEffect(() => {
      getCitizenByusername(params.username).then((response) => {
            setGradjanin(response);
          }).catch(error => {
            console.log(error);
          });
    }, []);

        return (
            <div className="body">
                <div className="container" style={{marginTop:'15px', marginBottom:'60px'}}>
                    <div className="card col-md-6 offset-md-3" >
                        
                        <div className="text-center" style={{marginTop:"8px"}}>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}><b>Ime:</b> {gradjanin.ime}   </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}><b>Prezime:</b> {gradjanin.prezime}  </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}><b>JMBG:</b> {gradjanin.jmbg} </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}><b>E-mail:</b> {gradjanin.email} </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}><b>Korisnicko ime:</b> {gradjanin.korisnickoIme} </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}><b>Datum rodjenja:</b> {gradjanin.datumRodjenja} </label>
                        </div><br/>

                        <br/><br/>
                        <button className="btn btn-success" style={{ margin: "8px", width:"150px" }} onClick={() => navigate(`/oglasi/${params.id}`)}>Prihvati</button>
                        <button className="btn btn-danger" style={{ margin: "8px", width:"150px" }} onClick={() => navigate(`/oglasi/${params.id}`)}>Odbij</button>
                    </div>
                </div>    
                </div>   
            </div>  

            
        );
    }

 
export default GradjaninComponent;