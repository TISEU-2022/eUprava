import {getAdvertisementById} from "../services/OglasService";
import {getAllKonkurse} from "../services/KonkursService";
import React, { useEffect, useState } from "react";
import {useLocation, useNavigate, useParams} from 'react-router-dom';

const ViewOglasComponent = (id) => {
    const [oglas, setOglas] = useState({});
    const [konkursi, setKonkursi] = useState([]);
    const navigate = useNavigate();
    const params = useParams();

    useEffect(() => {
        getAdvertisementById(params.id).then((response) => {
            console.log(response);
            setOglas(response);
          }).catch(error => {
            console.log(error);
          });
        getAllKonkurse().then((konkursi) => setKonkursi(konkursi))
    }, []);

        return (
            <div className="body">
                <div className="container" style={{marginTop:'15px', marginBottom:'60px'}}>
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
                            <label style={{color:"black", fontWeight:"400"}}> <b>Firma: </b> </label>
                        </div><br/>

                        <div className="row">
                            <label style={{color:"black", fontWeight:"400"}}> <b>Vrsta Posla: </b> </label>
                        </div>

                        <br/><br/>
                        <button className="btn btn-success" style={{ margin: "8px", width:"150px" }} onClick={() => navigate(`/oglasi/${params.id}`)}>Update</button>
                    </div>
                </div>    
                </div>   
                <h3>Konkurisani</h3>
                <table className="table table-striped" border="1">
                    <thead>
                    <tr>
                        <th>Ime</th>
                        <th>Prezime</th>
                        <th>Email</th>
                        <th>Korisnicko Ime</th>
                        <th>Datum Rodjenja</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {konkursi.map((gradjanin) => (
                        gradjanin.oglasZaPosao.id === oglas.id ? (
                            <tr key={gradjanin.gradjanin.id}>
                            <td>{gradjanin.gradjanin.ime}</td>
                            <td>{gradjanin.gradjanin.prezime}</td>
                            <td>{gradjanin.gradjanin.email}</td>
                            <td>{gradjanin.gradjanin.korisnickoIme}</td>
                            <td>{gradjanin.gradjanin.datumRodjenja}</td>
                            <td>
                                <button className="btn btn-primary" onClick={e => {navigate(`/gradjani/${gradjanin.gradjanin.id}`)}}>VIew</button>
                            </td>
                            </tr>
                    ) : null
                    ))}
                    </tbody>
                </table>
            </div>  

            
        );
    }

 
export default ViewOglasComponent;