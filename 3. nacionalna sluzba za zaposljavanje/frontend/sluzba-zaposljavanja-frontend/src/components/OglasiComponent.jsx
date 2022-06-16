import React, { useEffect, useState } from "react";
import { getAllAdvertisements } from "../services/OglasService";
import { getAdvertisementById } from "../services/OglasService";
import { useNavigate } from "react-router-dom";


const OglasiComponent = (props) => {
  const [oglasi, setOglasi] = useState([]);
  const navigate = useNavigate();
  
  useEffect(() => {
    getAllAdvertisements().then((response)=> setOglasi(response))
    
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

  return (
    <div style={{ marginTop: "20px" }}>
      <h3>Svi Oglasi</h3>
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

              <td></td>
              <td>
                <button
                  onClick={() => ViewOglas(oglas.id)}
                  className="btn-primary"
                >
                  View
                </button>
                <button className="btn-success">SignUp</button>
                <button className="btn-danger">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default OglasiComponent;