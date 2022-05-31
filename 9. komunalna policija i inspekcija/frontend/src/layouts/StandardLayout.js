import Navbar from '../components/Navigation/Navigation';
import Footer from './../components/Footer/Footer';
import {Container} from "react-bootstrap";

const StandardLayout = (props) => (
    <>
        <Navbar navLinks={props.navLinks}/>
        <Container>
            <main>
                { props.title && <h1 className="text-center my-5">{props.title}</h1> }
                {props.children}
            </main>
        </Container>
        <Footer/>
    </>
)

export default StandardLayout;