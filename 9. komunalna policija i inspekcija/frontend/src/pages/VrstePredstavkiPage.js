import React from 'react';
import StandardLayout from "../layouts/StandardLayout";
import VrstePredstavki from "../components/VrstePredstavki/VrstePredstavki";

const VrstePredstavkiPage = () => {

    return (
        <StandardLayout title="Vrste predstavki">
            <VrstePredstavki/>
        </StandardLayout>
    );
};

export default VrstePredstavkiPage;