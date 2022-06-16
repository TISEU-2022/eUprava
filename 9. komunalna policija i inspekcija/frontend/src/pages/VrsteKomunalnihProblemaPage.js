import React from 'react';
import StandardLayout from "../layouts/StandardLayout";
import VrsteKomunalnihProblema from "../components/VrsteKomunalnihProblema/VrsteKomunalnihProblema";

const VrsteKomunalnihProblemaPage = () =>{
    return (
        <StandardLayout title="Vrste komunalnih problema">
            <VrsteKomunalnihProblema />
        </StandardLayout>
    );
}

export default VrsteKomunalnihProblemaPage;