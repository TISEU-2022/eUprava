import { getAdvertisementById } from "../services/OglasService";
import { getBirthCertificate } from "../services/GradjaninService";
import React, { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { getAllCitizen } from "../services/GradjaninService";

const GradjaninComponent = () => {
  const [gradjanin, setGradjanin] = useState({});
  const navigate = useNavigate();
  const params = useParams();

  useEffect(() => {
    getBirthCertificate(params.jmbg)
      .then((response) => {
        setGradjanin(response.birth_certificate);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  let diplomaSrednja = {
    skola: "Gimnazija 'Jovan Jovanovic Zmaj'",
    smer: "Prirodno-matematicki",
    uspeh:"Odlican",
    godinaUpisa: 2015,
    godinaZavrsetka: 2019
  };

  let diplomaFakultet = {
    fakultet: "Fakultet Tehnickih Nauka",
    smer: "Softverske i informacione tehnologije",
    zvanje:"Strukovni inzinjer elektrotehnike i racunarstva",
    godinaUpisa: 2019,
    godinaZavrsetka: 2022
  };

  return (
    <div className="container">
      <div className="card-group">
        <div className="card">
          <h3>Rodni list {gradjanin.first_name} </h3>

          <div className="text-center" style={{ marginTop: "8px" }}>
            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Ime:</b> {gradjanin.first_name}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Prezime:</b> {gradjanin.last_name}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>JMBG:</b> {gradjanin.identification_number}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Pol:</b> {gradjanin.gender}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Zemlja Rodjenja:</b> {gradjanin.country_of_birth}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Drzavljanstvo:</b> {gradjanin.citizenship}{" "}
              </label>
            </div>
            <br />
            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Datum rodjenja:</b> {gradjanin.date_of_birth}{" "}
              </label>
            </div>
            <br />

            <br />
            <br />
          </div>
        </div>
        <div
          className="card col-md-3 offset-md-3"
          style={{ marginLeft: "18px", marginRight: "18px" }}
        >
          <h3>Diploma - Srednja Skola</h3>

          <div className="text-center" style={{ marginTop: "8px" }}>
            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Ime:</b> {gradjanin.first_name}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Prezime:</b> {gradjanin.last_name}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Skola:</b> {diplomaSrednja.skola}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Smer:</b> {diplomaSrednja.smer}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Uspeh:</b> {diplomaSrednja.uspeh}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Godina Upisa:</b> {diplomaSrednja.godinaUpisa}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Godina Zavrsetka:</b> {diplomaSrednja.godinaZavrsetka}{" "}
              </label>
            </div>
            <br />

            <br />

            <br />
            <br />
          </div>
        </div>
        <div className="card col-md-3 offset-md-3">
          <h3>Diploma - Fakultet </h3>

          <div className="text-center" style={{ marginTop: "8px" }}>
            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Ime:</b> {gradjanin.first_name}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Prezime:</b> {gradjanin.last_name}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Fakultet:</b> {diplomaFakultet.fakultet}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Smer:</b> {diplomaFakultet.smer}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Zvanje:</b> {diplomaFakultet.zvanje}{" "}
              </label>
            </div>
            <br />

            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Godina upisa:</b> {diplomaFakultet.godinaUpisa}{" "}
              </label>
            </div>
            <br />
            <div className="row">
              <label style={{ color: "black", fontWeight: "400" }}>
                <b>Godina zavrsetka:</b> {diplomaFakultet.godinaZavrsetka}{" "}
              </label>
            </div>
            <br />

            <br />
            <br />
          </div>
        </div>{" "}
      </div>
      <div style={{display:"flex"}}>
        <button
          className="btn btn-success"
          style={{ margin: "0 auto", width: "150px"}}
          onClick={() => navigate(`/oglasi/${params.id}`)}
        >
          Prihvati
        </button>
        <button
          className="btn btn-danger"
          style={{ margin: "0 auto", width: "150px" }}
          onClick={() => navigate(`/oglasi/${params.id}`)}
        >
          Odbij
        </button>
      </div>
    </div>
  );
};

export default GradjaninComponent;
