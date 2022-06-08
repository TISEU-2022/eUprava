import axios from 'axios';
import React, { useEffect, useState } from 'react'

const OglasiComponent = () => {

    const [oglasi, setOglasi] = useState([]);

    useEffect(() => {
        const fetchOglase = async () => {
            const res = await axios.get('http://localhost:8080/api/oglasi')
            setOglasi(res.data);
        }
        fetchOglase();
    }, []);

  return (
    <div>
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

                </tr>
            </thead>
            <tbody>
                {
                    oglasi.map(
                        oglas=>
                        <tr key={oglas.id}>
                            <td>{oglas.naziv}</td>
                            <td>{oglas.opis}</td>
                            <td>{oglas.datumOd}</td>
                            <td>{oglas.datumDo}</td>
                            <td>{oglas.firma.imeFirme}</td>

                            <td></td>
                            <td><button></button></td>
                        </tr>
                    )
                }
            </tbody>
        </table>


    </div>
  )
}

export default OglasiComponent