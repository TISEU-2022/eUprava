import StandardLayout from "../layouts/StandardLayout";
import {Button} from "react-bootstrap";

const WelcomePage = () => {

    return (
        <StandardLayout>
            <div className="d-flex flex-column justify-content-center" style={{height: "90vh"}}>
                <h1 className="display-3">Dobro došli!</h1>
                <p className="h5">Kako bi koristili naše usluge, potrebno je da se prijavite na Portalu eUprava korisničkim imenom i lozinkom. Nakon toga bićete na par koraka do svih informacija i elektronskih usluga Komunalne polciije i inspekcije u Republici Srbiji.</p>
                <Button variant="dark" size="lg" style={{width: "200px"}} href="http://localhost:4101/auth/login?successUrl=http://localhost:9001/auth">Prijavite se</Button>
            </div>
        </StandardLayout>
    );
};

export default WelcomePage;