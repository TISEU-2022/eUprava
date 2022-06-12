import PredstavkeForm from "../components/Predstavke/PredstavkeForm/PredstavkeForm";
import StandardLayout from "../layouts/StandardLayout";

const PredstavkeFormPage = (props) => {

    return (
        <StandardLayout title="Podnesi predstavku">
            <PredstavkeForm/>
        </StandardLayout>
    );
};

export default PredstavkeFormPage;