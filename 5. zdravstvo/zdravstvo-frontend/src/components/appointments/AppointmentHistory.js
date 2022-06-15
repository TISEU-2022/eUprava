import React, { useState, useEffect } from 'react'
import { Button, Container, Modal, Table } from 'react-bootstrap';
import { AppointmentService } from '../../services/AppointmentService';
import { UserService } from '../../services/UserService';

const AppointmentHistory = () => {

    const [appointments, setAppointments] = useState([]);
    const [report, setReport] = useState({});
    const [citizen, setCitizen] = useState({})



    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = (id) => {
        getReport(id);
        setShow(true);

    }


    useEffect(() => {
        fetchAppointments();
        getCitizen();

    }, [])



    async function fetchAppointments() {
        try {
            const response = await AppointmentService.getAppoitmentByUser();
            console.log(response.data);
            setAppointments(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }

    async function getCitizen() {
        try {
            const response = await UserService.getUser();
            setCitizen(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }

    async function getReport(id) {
        try {
            const response = await AppointmentService.getAppointmentReport(id);
            console.log(response.data);
            setReport(response.data);
        } catch (e) {
            console.error("Error while getting api")
        }
    }


    return (

        <Container style={{ backgroundColor: "white", height: "500px", padding: "20px", marginTop: "40px" }}>
            <h3>Informacije o građaninu</h3>
            <ul>
                <li>{citizen.firstname} {citizen.lastname}</li>
                <li>{citizen.address}</li>
                <li>{citizen.dateOfBirth}</li>
                <li>{citizen.jmbg}</li>

            </ul>
            <h3>Istorija pregleda</h3>
            <Table bordered striped>
                <thead className='thead-dark'>
                    <tr>
                        <th>Doktor</th>
                        <th>Opis</th>
                        <th>Datum</th>
                        <th>Vreme</th>
                        <th>Status</th>
                        <th>Izveštaj</th>
                    </tr>
                </thead>
                <tbody>
                    {appointments.length === 0 ?
                        <tr>
                            <td className='text-center' colSpan={5}>Нема доступних прегледа!</td>
                        </tr> :
                        appointments.map((a) => {
                            return (
                                <tr key={a.id}>
                                    <td>{a.doctorName}</td>
                                    <td>{a.description}</td>
                                    <td>{a.date}</td>
                                    <td>{a.time}</td>
                                    <td>{a.status}</td>
                                    <td>
                                        {a.status == "ZAVRSEN" ?
                                            <Button variant="primary" onClick={() => handleShow(a.id)}>
                                                Izveštaj
                                            </Button> :
                                            <p>Pregled još nije završen!</p>
                                        }
                                    </td>
                                    <td></td>

                                </tr>

                            )
                        })

                    }
                </tbody>
            </Table>


            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Izveštaj</Modal.Title>
                </Modal.Header>
                <Modal.Body>{report.report}</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Nazad
                    </Button>
                </Modal.Footer>
            </Modal>

        </Container>
    )
}

export default AppointmentHistory;