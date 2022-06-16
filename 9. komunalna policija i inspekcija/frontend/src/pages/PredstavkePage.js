import StandardLayout from "../layouts/StandardLayout";
import Predstavke from "../components/Predstavke/Predstavke";

const PredstavkePage = () => {

    return (
        <StandardLayout title="Predstavke" fluid>
            <Predstavke/>
        </StandardLayout>
    );
};

export default PredstavkePage;