import StandardLayout from "../layouts/StandardLayout";
import PredstavkeDetails from "../components/Predstavke/PredstavkeDetails/PredstavkeDetails";

const PredstavkeDetailsPage = () => {

    return (
        <StandardLayout title="Detalji predstavke">
            <PredstavkeDetails/>
        </StandardLayout>
    );
};

export default PredstavkeDetailsPage;