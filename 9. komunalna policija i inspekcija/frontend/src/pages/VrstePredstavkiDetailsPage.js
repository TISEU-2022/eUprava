import StandardLayout from "../layouts/StandardLayout";
import VrstePredstavkiDetails from "../components/VrstePredstavki/VrstePredstavkiDetails/VrstePredstavkiDetails";

const VrstePredstavkiDetailsPage = () => {

    return (
        <StandardLayout title="Detalji vrste predstavki">
            <VrstePredstavkiDetails/>
        </StandardLayout>
    );
};

export default VrstePredstavkiDetailsPage;