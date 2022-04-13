import { Box, ButtonGroup } from "@chakra-ui/react";
import { Formik, FormikHelpers } from "formik";
import {
    InputControl,
    ResetButton,
    SubmitButton
} from "formik-chakra-ui";
import * as React from "react";
import * as Yup from "yup";
import { WorkerProps } from ".";
import { addEditWorker } from "../../api/workersApi";
import { useToast } from '@chakra-ui/react'

const AdminAddEditWorker: React.FC<WorkerProps> = (props) => {
    const { worker } = props
    const toast = useToast()

    const validationSchema = Yup.object({
        username: Yup.string().required('Username is required!'),
        identityNumber: Yup.string().required('Identity Number is required!').min(13, 'Identity Number must have 13 digits!').max(13, 'Identity Number must have 13 digits!'),
        firstName: Yup.string().required('First name is required!'),
        lastName: Yup.string().required('Last name is required!'),
        password: Yup.string().min(6, 'Password must contain 6 characters').required('Password is required!')
    });

    const initialValues = {
        identityNumber: worker?.identityNumber || "",
        username: worker?.username || "",
        firstName: worker?.firstName || "",
        lastName: worker?.lastName || "",
        password: worker?.password || "",
    };

    const onSubmit = (values: any, actions: FormikHelpers<{
        identityNumber: string;
        username: string;
        firstName: string;
        lastName: string;
        password: string;
    }>) => {
            addEditWorker(values, worker?._id).then((res) => {
                toast({
                    title: 'Success!',
                    status: 'success',
                    duration: 9000,
                    isClosable: true,
                  })
                  props.onFinish()
            }).catch((error) => {
                let errors = error?.response?.data?.errors
                let newErrors: any = {};
                Object.values(errors).forEach((value: any) => {
                    newErrors[value['path']] = value['message']
                })
                actions.setErrors(newErrors)
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
                    <InputControl name="username" label="Username" />
                    <InputControl name="identityNumber" label="Identity Number" />
                    <InputControl name="firstName" label="First Name" />
                    <InputControl name="lastName" label="Last Name" />
                    <InputControl name="password" inputProps={{ 'type': 'password' }} label="Password" />

                    <ButtonGroup mt={5} spacing={2}>
                        <SubmitButton>Submit</SubmitButton>
                        <ResetButton>Reset</ResetButton>
                    </ButtonGroup>
                </Box>
            )}
        </Formik>
    );
};

export default AdminAddEditWorker;
