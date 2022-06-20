import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Navbar from 'react-bootstrap/Navbar'
import Container from 'react-bootstrap/Container'
import Nav from 'react-bootstrap/Nav'
import Button from 'react-bootstrap/Button'
import axios from 'axios';
import { useJwt } from "react-jwt";
import Row from 'react-bootstrap/Row'
import Table from 'react-bootstrap/Table'
import moment from 'moment';

export const TabelaZakazivanja = () => {
    const navigate = useNavigate();

    const usertoken = localStorage.getItem("token")
    const { decodedToken, isExpired } = useJwt(usertoken);

    const [appointments, setAppointments] = useState("")

    useEffect(() => {
        if (decodedToken) {
            console.log(decodedToken)
            axios.get("http://localhost:11001/api/appointment/myAppointments", {
                params: {
                    username: decodedToken.username,
                }
            })
                .then((res) => {
                    console.log(res)
                    setAppointments(res.data)
                })
                .catch((err) => {
                    console.log(err)
                    // prikazi text ispod cards nema zakazanih termina
                })
        }
    }, [decodedToken])


    // const poruka = decodedToken && decodedToken ? 
    // <span className="yourApp2">To see your appointments, please create one.</span> 
    // : 
    // <span className="yourApp2">To see your appointments, please log in.</span>

    return (
        <>
            <Row style={{ marginTop: '4rem' }}>
                <span className="yourApp">Your appointments:</span>
            </Row>
            <Row style={{ marginTop: '1rem' }}>
                {appointments && appointments.length > 0  ?
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Document type</th>
                                <th>Date</th>
                                <th>Time</th>
                                <th>For whom</th>
                            </tr>
                        </thead>
                        <tbody>
                            {appointments.map((item =>
                                <tr>
                                    <td>{item.documentType == "DOCUMENT_IDCARD" ? "ID Card" : "Passport"}</td>
                                    <td>{moment(item.appointmentTime).format('DD/MM/YYYY')}</td>
                                    <td>{moment(item.appointmentTime).format('HH:MM')}</td>
                                    <td>{item.appointmentForMinor ? "Minor" : "Adult"}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                    :
                    <span className="yourApp2">To see your appointments, please create one.</span> 
                    }     
            </Row>
        </>
    )
}

export default TabelaZakazivanja