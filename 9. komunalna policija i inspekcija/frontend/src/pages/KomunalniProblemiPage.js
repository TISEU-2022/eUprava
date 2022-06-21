import StandardLayout from "../layouts/StandardLayout";
import KomunalniProblemi from "../components/KomunalniProblemi/KomunalniProblemi";


const KomunalniProblemiPage = () =>{

    return (
        <StandardLayout title="Komunalni problemi" fluid>
            <KomunalniProblemi />
        </StandardLayout>
    );

}

export default KomunalniProblemiPage;