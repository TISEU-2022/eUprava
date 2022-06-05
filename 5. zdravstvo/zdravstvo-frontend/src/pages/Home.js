import React from 'react'
import { Container } from 'react-bootstrap'


const Home = () => {





  return (
    <Container style={{backgroundColor:"white", height:"500px", padding:"20px", marginTop:"40px"}}>
    <div class="jumbotron ">
      <h1 class="display-4">E-ZDRAVLJE</h1>
      <p class="lead">Dobrodošli na elektronski portal zdravstva!</p>
      <hr/>
        <p class="lead">
          <a class="btn btn-primary btn-lg" href="/book-appointment" role="button">Zakaži pregled!</a>
        </p>
    </div>
    </Container>

  )
}

export default Home
