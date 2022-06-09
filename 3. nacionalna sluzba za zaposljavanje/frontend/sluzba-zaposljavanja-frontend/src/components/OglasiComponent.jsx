import { render } from '@testing-library/react';
import axios from 'axios';
import React, { useEffect, useState } from 'react'
import {useNavigate} from 'react-router-dom'
import OglasService from '../services/OglasService';

class OglasiComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            oglasi:[]
        }
       
        this.viewOglas = this.viewOglas.bind(this);
    }

    componentDidMount(){
        OglasService.getOglase().then((response) => {
            this.setState({oglasi:response.data})
        });
    }

    viewOglas(id){
        this.context.history.push(`/viewOglas/${id}`);
    }


     render(){
  return (
    <div style={{marginTop: "20px"}}>
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
                {
                    this.state.oglasi.map(
                        oglas=>
                        <tr key={oglas.id}>
                            <td>{oglas.naziv}</td>
                            <td>{oglas.opis}</td>
                            <td>{oglas.datumOd}</td>
                            <td>{oglas.datumDo}</td>
                            <td>{oglas.firma.imeFirme}</td>

                            <td></td>
                            <td>
                                <button onClick={ () => this.viewOglas(oglas.id)} className="btn-primary">View</button>
                                <button className="btn-success">SignUp</button>
                                <button className="btn-danger">Delete</button>
                            </td>
                        </tr>
                    )
                }
            </tbody>
        </table>


    </div>
  )}
}

export default OglasiComponent