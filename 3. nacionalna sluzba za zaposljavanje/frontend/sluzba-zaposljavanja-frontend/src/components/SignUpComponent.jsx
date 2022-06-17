import React from 'react'
import { getAdvertisementById } from '../services/OglasService';
import { useEffect, useState } from 'react';
import { AuthenticationService } from '../services/AuthenticationService';
import { useNavigate, useParams } from 'react-router-dom';
import { TokenService } from '../services/TokenService';

const SignUpComponent = () => {
    const [konkurs, setKonkurs] = useState({});
    const [gradjanin, setGradjanin] = useState({});
    const [oglas, setOglas] = useState({});
    const [oglasZaPosao, setOglasZaPosao] = useState({});
    const [username, setUsername] = useState("");

    const navigate = useNavigate();
    const params = useParams();

    useEffect(() => {
        getAdvertisementById(params.id).then((response) => {
            console.log(response);
            setOglas(response);
          }).catch(error => {
            console.log(error);
          })

        const username = AuthenticationService.getUsername();
        setUsername(username);
        console.log(username)
        
        

    }, []);

    const onSubmit = () => {
        let konkurss = {
          gradjanin:gradjanin,
          oglasZaPosao: oglas,
          datumKonkurisanja:'2022-03-03',
          zavrseno:false
        }
        console.log(konkurss)
        //addAdvertisement(oglass)
      }

  return (
    <div className="body">
                <div className="container" style={{marginTop:'50px'}}>
                    <div className="card col-md-6 offset-md-3" >
                        
                        <div className="text-center" style={{marginTop:"8px"}}>
                        <form>
                          <div className="row">
                              <label htmlFor="naziv" style={{color:"black", fontWeight:"400"}}>Naziv Oglasa:
                                <input type="text" name="naziv" id="naziv" value={oglas.naziv} readonly />
                              </label>
                          </div><br/>

                          <div className="row">
                              <label htmlFor="opis" style={{color:"black", fontWeight:"400"}}>Opis Oglasa:
                                <input type="text" name="opis" value={oglas.opis} readonly />
                              </label>
                          </div><br/>

                           <div className="row">
                              <label htmlFor="opis" style={{color:"black", fontWeight:"400"}}>Oglas:
                                <input type="text" name="opis" value={oglas} readonly />
                              </label>
                          </div><br/>

                           <div className="row">
                              <label htmlFor="opis" style={{color:"black", fontWeight:"400"}}>Username:
                                <input type="text" name="opis" value={username} readonly />
                              </label>
                          </div><br/>

                          
                        
                        </form>
                        <button className="btn btn-success" style={{ marginTop: "8px", width:"150px" }} onClick={onSubmit}>Confirm SignUp</button>
                        <br/><br/>
                        
                    
                    </div>
                </div>    
                
                </div>   
            </div>  
  )
}

export default SignUpComponent