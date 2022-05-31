import StandardLayout from "../layouts/StandardLayout";
import VrsteKomunalnihProblemaDetails
    from "../components/VrsteKomunalnihProblema/VrsteKomunalnihProblemaDetails/VrsteKomunalnihProblemaDetails";


const VrsteKomunalnihProblemaDetailsPage = () =>{
    return (
        <StandardLayout title="Detalji vrste komunalnog problema">
            <VrsteKomunalnihProblemaDetails />
        </StandardLayout>
    );
}

export default VrsteKomunalnihProblemaDetailsPage;