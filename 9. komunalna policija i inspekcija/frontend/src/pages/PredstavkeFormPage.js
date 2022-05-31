import PredstavkeForm from "../components/Predstavke/PredstavkeForm/PredstavkeForm";
import StandardLayout from "../layouts/StandardLayout";

const PredstavkeFormPage = (props) => {

    return (
        <StandardLayout title="Kreiraj predstavku">
            <PredstavkeForm/>
        </StandardLayout>
    );
};

export default PredstavkeFormPage;