import PredstavkeDetails from "../components/Predstavke/PredstavkeDetails/PredstavkeDetails";
import StandardLayout from "../layouts/StandardLayout";
import KomunalniProblemiDetails
    from "../components/KomunalniProblemi/KomunalniProblemiDetails/KomunalniProblemiDetails";


const KomunalniProblemiDetailsPage = () =>{

    return(
        <StandardLayout title="Detalji predstavke">
            <KomunalniProblemiDetails />
        </StandardLayout>
    );

}

export default KomunalniProblemiDetailsPage;