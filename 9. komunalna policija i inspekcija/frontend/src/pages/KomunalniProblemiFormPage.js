import StandardLayout from "../layouts/StandardLayout";
import KomunalniProblemiForm from "../components/KomunalniProblemi/KomunalniProblemiForm/KomunalniProblemiForm";


const KomunalniProblemiFormPage = () =>{
    return(
        <StandardLayout title="Podnesi komunalni problem">
            <KomunalniProblemiForm />
        </StandardLayout>
    );
}

export default KomunalniProblemiFormPage;