import React, { useEffect, useState } from "react";
import { getAllAdvertisements } from "../services/OglasService";
import { getAdvertisementById } from "../services/OglasService";
import { useNavigate } from "react-router-dom";
import AddOglasComponent from "./AddOglasComponent";
import SignUpComponent from "./SignUpComponent";
import { AuthenticationService } from "../services/AuthenticationService";


const OglasiComponent = (props) => {
  const [oglasi, setOglasi] = useState([]);
  const [role, setRole] = useState('');
  const navigate = useNavigate();
  
  useEffect(() => {
    getAllAdvertisements().then((response)=> setOglasi(response))
    let role = AuthenticationService.getRole()
    setRole(role)
  }, []);

  function ViewOglas(id){
    getAdvertisementById(id).then((response) => {
      console.log(response);
    }).catch(error => {
      console.log(error);
    })
    toViewOglas(id);
  }

  const toViewOglas = (id) => {
    navigate(`/viewOglas/${id}`, JSON.stringify(id))
  }

  function SignUp(id){
    getAdvertisementById(id).then((response) => {
      console.log(response);
    }).catch(error => {
      console.log(error);
    })
    toSignUp(id);
  }

   const toSignUp = (id) => {
    navigate(`/signUp/${id}`, JSON.stringify(id))
  }

  return (
    <div style={{ marginTop: "20px" }}>
      <h3>Svi Oglasi</h3>
      {role == 'biro_sluzbenik' 
      ? (<button style={{ marginBottom: "7px" }} className="btn btn-info" onClick={() => navigate(`/oglasi/add`)}>Add</button>)
      : (<div></div>)}
      <table className="table table-striped" border="1">
        <thead>
          <tr>
            <th>Naziv</th>
            <th>Opis</th>
            <th>Datum Od</th>
            <th>Datum Do</th>
            <th>Firma</th>
            <th>Vrsta Posla</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {oglasi.map((oglas) => (
            <tr key={oglas.id}>
              <td>{oglas.naziv}</td>
              <td>{oglas.opis}</td>
              <td>{oglas.datumOd}</td>
              <td>{oglas.datumDo}</td>
              <td>{oglas.firma.imeFirme}</td>
              <td>{oglas.vrstaPosla.ime}</td>

              
              <td style={{display:'inline-flex'}}>
                <button
                  onClick={() => ViewOglas(oglas.id)}
                  className="btn btn-primary"
                >
                  View
                </button>
                {role == 'biro_gradjanin' 
                ? (<button className="btn btn-success" onClick={() => SignUp(oglas.id)}
                >
                  SignUp
                  </button>)
                  : (<div></div>)}
                  {role == 'biro_sluzbenik' 
                  ? (<button className="btn btn-danger">Delete</button>)
                  : (<div></div>)}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default OglasiComponent;