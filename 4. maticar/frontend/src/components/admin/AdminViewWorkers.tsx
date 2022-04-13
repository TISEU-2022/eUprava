import { Box, Button, Flex, useToast } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { WorkerSchemaType } from '.';
import { deleteWorker, getWorkers } from '../../api/workersApi';
import CommonDataTable from '../common/CommonDataTable';
import AdminAddEditWorker from './AdminAddEditWorker';

const AdminViewWorkers = () => {
    const [workers, setWorkers] = useState([]);
    const [isAddEdit, setAddEdit] = useState(false);
    const [selectedWorker, setSelectedWorker] = useState<WorkerSchemaType>();
    const toast = useToast();
    const onAddEditWorker = (worker?: WorkerSchemaType) => {
        worker && setSelectedWorker(worker);
        setAddEdit(true);
    }
    const onDelete = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>, value: any) => {
        e.stopPropagation()
        deleteWorker(value._id).then((res) => {
            let newWorkers = workers.filter((worker: any) => { return worker._id !== value._id })
            setWorkers(newWorkers)
            toast({
                title: 'Success!',
                status: 'success',
                duration: 9000,
                isClosable: true,
              }) 
        }).catch((error) => {
            console.log(error)
            toast({
                title: 'Error!',
                status: 'error',
                duration: 9000,
                isClosable: true,
              }) 
        })
    }

    const columns = [
        {
            key: 'firstName',
            numeric: false,
            title: 'First name'
        },
        {
            key: 'lastName',
            numeric: false,
            title: 'Last name'
        },
        {
            key: 'identityNumber',
            numeric: false,
            title: 'Identity Number'
        },
        {
            key: 'username',
            numeric: false,
            title: 'Username'
        },
        {
            key: '',
            componentInstead: (value: typeof workers[0]) => {
                return <Button color="red" onClick={(e) => onDelete(e, value)}>Delete</Button>
            }
        }]

    useEffect(() => {
        getWorkers().then((res) => {
            setWorkers(res?.data)
        });
    }, [isAddEdit]);

    return isAddEdit ?
        <>
            <Flex flex={1} justifyContent="center">
                <Button mt={5} width={500} color="teal" onClick={() => { setAddEdit(false); setSelectedWorker(null as any) }}>Back to workers</Button>
            </Flex>
            <AdminAddEditWorker worker={selectedWorker} onFinish={() => setAddEdit(false)} />
        </> :
        <Box mt={20}>
            <CommonDataTable
                data={workers}
                searchInputProps={{ width: 500, m: 2 }}
                onRowClickHandler={(value) => { onAddEditWorker(value) }}
                tableHeadings={columns}
            />
            <Flex flex={1} justifyContent="flex-end">
                <Button mt={5} color="teal" onClick={() => onAddEditWorker()}>Add worker</Button>
            </Flex>
        </Box>
};

export default AdminViewWorkers;
