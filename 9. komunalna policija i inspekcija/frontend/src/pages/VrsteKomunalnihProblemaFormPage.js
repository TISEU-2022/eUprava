import StandardLayout from "../layouts/StandardLayout";
import VrsteKomunalnihProblemaForm
    from "../components/VrsteKomunalnihProblema/VrsteKomunalnihProblemaForm/VrsteKomunalnihProblemaForm";


const VrsteKomunalnihProblemaFormPage = props =>{

    return (
        <StandardLayout title={props.edit ? "Izmeni vrstu komunalnog problema" : "Kreiraj vrstu komunalnog problema"}>
            <VrsteKomunalnihProblemaForm />
        </StandardLayout>
    );

}

export default  VrsteKomunalnihProblemaFormPage;