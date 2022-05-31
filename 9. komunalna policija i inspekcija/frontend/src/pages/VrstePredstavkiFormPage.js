import React from 'react';
import StandardLayout from "../layouts/StandardLayout";
import VrstePredstavkiForm from "../components/VrstePredstavki/VrstePredstavkiForm/VrstePredstavkiForm";

const VrstePredstavkiFormPage = (props) => {

    console.log(props.edit)

    return (
        <StandardLayout title={props.edit ? "Izmeni vrstu predstavke" : "Kreiraj vrstu predstavke"}>
            <VrstePredstavkiForm/>
        </StandardLayout>
    );
};

export default VrstePredstavkiFormPage;