import { Box, ButtonGroup } from "@chakra-ui/react";
import { Formik, FormikHelpers } from "formik";
import {
    InputControl,
    ResetButton,
    SubmitButton
} from "formik-chakra-ui";
import * as React from "react";
import * as Yup from "yup";
import { useToast } from '@chakra-ui/react'
import { MarriageProps } from ".";
import { addMarriage } from "../../api/marriagesApi";

const WorkerAddMarriage: React.FC<MarriageProps> = (props) => {
    const toast = useToast()

    const validationSchema = Yup.object({
        party_1_id: Yup.string().required('Identity Number is required!').min(13, 'Identity Number must have 13 digits!').max(13, 'Identity Number must have 13 digits!'),
        party_2_id: Yup.string().required('Identity Number is required!').min(13, 'Identity Number must have 13 digits!').max(13, 'Identity Number must have 13 digits!'),
        witness_1_id: Yup.string().required('Identity Number is required!').min(13, 'Identity Number must have 13 digits!').max(13, 'Identity Number must have 13 digits!'),
        witness_2_id: Yup.string().required('Identity Number is required!').min(13, 'Identity Number must have 13 digits!').max(13, 'Identity Number must have 13 digits!'),
    });

    const initialValues = {
        party_1_id: "",
        party_2_id: "",
        witness_1_id: "",
        witness_2_id: "",
    };

    const onSubmit = (values: any, actions: FormikHelpers<{
        party_1_id: string;
        party_2_id: string;
        witness_1_id: string;
        witness_2_id: string;
    }>) => {
            addMarriage({...values}).then((res) => {
                toast({
                    title: 'Success!',
                    status: 'success',
                    duration: 9000,
                    isClosable: true,
                  })
                  props.onFinish()
            }).catch((error) => {
                console.log(error)
            }).finally(() => {
                actions.setSubmitting(false);
            })
    };

    return (
        <Formik
            initialValues={initialValues}
            onSubmit={(values, actions) => onSubmit(values, actions)}
            validationSchema={validationSchema}
        >
            {({ handleSubmit }) => (
                <Box
                    borderWidth="1px"
                    rounded="lg"
                    shadow="1px 1px 3px rgba(0,0,0,0.3)"
                    maxWidth={800}
                    p={6}
                    m="10px auto"
                    as="form"
                    onSubmit={handleSubmit as any}
                >
                    <InputControl name="party_1_id" label="Party 1 Identity Number" />
                    <InputControl name="party_2_id" label="Party 2 Identity Number" />
                    <InputControl name="witness_1_id" label="Witness 1 Identity Number" />
                    <InputControl name="witness_2_id" label="Witness 2 Identity Number" />
                    <ButtonGroup mt={5} spacing={2}>
                        <SubmitButton>Submit</SubmitButton>
                        <ResetButton>Reset</ResetButton>
                    </ButtonGroup>
                </Box>
            )}
        </Formik>
    );
};

export default WorkerAddMarriage;
