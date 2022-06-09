import React,{useState, useEffect} from 'react'
import { Button, Container, Table } from 'react-bootstrap';
import Swal from 'sweetalert2';
import { AppointmentService } from '../../services/AppointmentService';

const BookAppointment = () => {

  const [appointments, setAppointments] = useState([]);


  useEffect(() => {
    fetchAppointments();

  }, [])

  let appointment = [
    {
        'id':1,
        'description':"Stomatolog",
        'doctorName':'Miloš Stefanović',
        'date':'2022-06-05',
        'time':'19:00',
    }
]



  async function fetchAppointments() {
    try {
      const response = await AppointmentService.getFreeAppointments();
      console.log(response.data);
      setAppointments(response.data);
    } catch (e) {
      setAppointments(appointment);
      console.error("Error while getting api")
    }
  }

  async function bookAppointment(id){
    try {
      await AppointmentService.bookAppointment(id);
      setAppointments((a) => appointments.filter((a) => a.id !== id));
      Swal.fire('Obaveštenje','Pregled uspešno zakazan!', 'success')

    } catch (e) {
      console.error("Error while getting api")
    }
  }


  return (
    <Container style={{backgroundColor:"white", height:"500px", padding:"20px", marginTop:"40px"}}>
      <h2 style={{textAlign:"center"}}>Slobodni pregledi</h2>
      <Table bordered striped>
        <thead className='thead-dark'>
          <tr>
            <th>Doktor</th>
            <th>Opis</th>
            <th>Datum</th>
            <th>Vreme</th>
            <th>Zakaži</th>
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
                  <td>
                    <Button onClick={bookAppointment(a.id)}>Zakaži</Button>
                  </td>

                </tr>

              )
            })

          }
        </tbody>
      </Table>


    </Container>
  )
}

export default BookAppointment;