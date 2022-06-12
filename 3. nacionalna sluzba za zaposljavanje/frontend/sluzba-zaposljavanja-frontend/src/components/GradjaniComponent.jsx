import React, { useEffect, useState } from "react";
import {getAllCitizen} from "../services/GradjaninService"
import "../components.css";


const GradjaniComponent = () => {
  const [gradjani, setGradjani] = useState([]);

  useEffect(() => {
    getAllCitizen().then((response) => setGradjani(response))
    
  }, []);

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
            <tr key={gradjanin.id}>
              <td>{gradjanin.ime}</td>
              <td>{gradjanin.prezime}</td>
              <td>{gradjanin.email}</td>
              <td>{gradjanin.korisnickoIme}</td>
              <td>{gradjanin.datumRodjenja}</td>

              <td>
                <button className="btn-primary">Button</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default GradjaniComponent;
