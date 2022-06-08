import React, { useEffect, useState } from 'react'
import GradjaninService from '../services/GradjaninService';
import axios from 'axios';

const GradjaniComponent = () => {

    const [gradjani, setGradjani] = useState([]);

    useEffect(() => {
        const fetchGradjane = async () => {
            //setLoading(true);
            const res = await axios.get('http://localhost:8080/api/gradjani');
            setGradjani(res.data);
            //setLoading(false);
        };
        fetchGradjane();
    }, []);

  return (
    <div>
        <h3>Svi gradjani</h3>
        <table className="table table-striped" border="1">
            <thead>
                <tr>
                    <th>Ime</th>
                    <th>Prezime</th>
                    <th>Email</th>
                    <th>Korisnicko Ime</th>
                    <th>Datum Rodjenja</th>
                </tr>
            </thead>
            <tbody>
                {
                    gradjani.map(
                        gradjanin=>
                        <tr key={gradjanin.id}>
                            <td>{gradjanin.ime}</td>
                            <td>{gradjanin.prezime}</td>
                            <td>{gradjanin.email}</td>
                            <td>{gradjanin.korisnickoIme}</td>
                            <td>{gradjanin.datumRodjenja}</td>
                            
                            <td><button></button></td>
                        </tr>
                    )
                }
            </tbody>
        </table>


    </div>
  )
}

export default GradjaniComponent
