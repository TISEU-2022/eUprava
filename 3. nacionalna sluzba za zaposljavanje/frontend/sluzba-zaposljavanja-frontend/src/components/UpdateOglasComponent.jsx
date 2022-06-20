import {getAdvertisementById, updateAdvertisement} from "../services/OglasService";
import React, { useEffect, useState } from "react";
import {useParams} from 'react-router-dom';
import { getAllFirms } from "../services/FirmaService";
import { getAllJobs } from "../services/VrstaPoslaService";

const UpdateOglasComponent = () => {
    const params = useParams();
    const [oglas, setOglas] = useState([]);
    const [firme, setFirme] = useState([]);
    const [poslovi, setPoslovi] = useState([]);
    const [firmaId, setFirmaId] = useState(1);
    const [posaoId, setPosaoId] = useState(1);
    let oglass = {}
    

    useEffect(() => {
        getAdvertisementById(params.id).then((response) => {setOglas(response); setFirmaId(response.firma.id); setPosaoId(response.vrstaPosla.id)});
        getAllFirms().then((response) => setFirme(response));
        getAllJobs().then((response) => setPoslovi(response));
        
    }, []);

    const handleFirms = (event) =>{
      setFirmaId(parseInt(event.target.value))
    }

    const handleJobs = (event) =>{
      setPosaoId(parseInt(event.target.value))
    }


    const onSubmit = () => {
      oglass = {
        id:params.id,
        naziv:oglas.naziv,
        opis:oglas.opis,
        datumOd:oglas.datumOd,
        datumDo:oglas.datumDo,
        firma:firmaId,
        vrstaPosla:posaoId
      }
      updateAdvertisement(params.id, oglass)
    }

        return (
            <div className="body">
                <div className="container" style={{marginTop:'50px'}}>
                    <div className="card col-md-6 offset-md-3" >
                        
                        <div className="text-center" style={{marginTop:"8px"}}>
                        <form>
                          <div className="row">
                              <label htmlFor="naziv" style={{color:"black", fontWeight:"400"}}>Naziv:
                                <input type="text" name="naziv" id="naziv" value={oglas.naziv} onChange={e => setOglas({...oglas,naziv:e.target.value})} />
                              </label>
                          </div><br/>

                          <div className="row">
                              <label htmlFor="opis" style={{color:"black", fontWeight:"400"}}>Opis:
                                <input type="text" name="opis" value={oglas.opis} onChange={e => setOglas({...oglas,opis:e.target.value})}/>
                              </label>
                          </div><br/>

                          <div className="row">
                              <label htmlFor="datumOd" style={{color:"black", fontWeight:"400"}}>Datum Od:
                                <input type="date" name="datumOd" value={oglas.datumOd} onChange={e => setOglas({...oglas,datumOd:e.target.value})} />
                              </label>
                          </div><br/>

                          <div className="row">
                              <label htmlFor="datumDo" style={{color:"black", fontWeight:"400"}}>Datum Do:
                                <input type="date" name="datumDo" value={oglas.datumDo} onChange={e => setOglas({...oglas,datumDo:e.target.value})}/>
                              </label>
                          </div><br/>
                          Firma:
                          <select className="form-select" name="firma" onChange={handleFirms} style={{width:"300px", margin:"0 auto"}}>
                            {firme.map(firma => <option value={firma.id} selected={firma.id === firmaId}>{firma.imeFirme}</option>)}
                          </select>

                          Vrsta posla:
                          <select className="form-select" name="vrstaPosla" onChange={handleJobs} style={{width:"300px", margin:"0 auto"}}>
                            {poslovi.map(posao => <option value={posao.id} selected={posao.id === posaoId}>{posao.ime}</option>)}
                          </select>
                        </form>
                        <button className="btn btn-success" style={{ marginTop: "8px", width:"150px" }} onClick={onSubmit}>Submit</button>
                        <br/><br/>
                        
                    
                    </div>
                </div>    
                
                </div>   
            </div>  
        );
    }

 
export default UpdateOglasComponent;