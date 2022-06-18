import React, { useEffect, useState } from "react";
import {getAllCitizen} from "../services/GradjaninService"
import httpClient from "../auth/JwtInterceptors";
import "../components.css";


const GradjaniComponent = () => {
  const [gradjani, setGradjani] = useState([]);

  useEffect(() => {
    getAllCitizen().then((response) => setGradjani(response))
    
  }, []);

  const skiniPDGradjanina = async (username) => {
    httpClient.get(`http://localhost:3001/api/gradjani/export/${username}`, {
        params: {
            cacheBustTimestamp: Date.now(),
          },
          responseType: 'blob',
          timeout: 120,
    }).then((response) => {
        console.log('>>>', { response });
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'file.pdf'); //or any other extension
        document.body.appendChild(link);
        link.click();
    }).catch(err => alert(err));
    console.log('click')
    }

  return (
    <div style={{ marginTop: "20px" }}>
      <h3>Svi gradjani</h3>
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
          {gradjani.map((gradjanin) => (
            gradjanin.role == 'biro_gradjanin' ? (
            <tr key={gradjanin.id}>
              <td>{gradjanin.ime}</td>
              <td>{gradjanin.prezime}</td>
              <td>{gradjanin.email}</td>
              <td>{gradjanin.korisnickoIme}</td>
              <td>{gradjanin.datumRodjenja}</td>

              <td>
              <button className="btn btn-success" onClick={ () => skiniPDGradjanina(gradjanin.korisnickoIme)}>Preuzmi PDF Zaposlenja</button>
              </td>
            </tr> )
            : (<></>) 
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default GradjaniComponent;
