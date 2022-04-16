import { Box, Button, Flex, useToast } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { divorceMarriage, getMarriages } from '../../api/marriagesApi';
import CommonDataTable from '../common/CommonDataTable';
import WorkerAddMarriage from './WorkerAddMarriage';

const WorkerViewMarriages = () => {
    const [marriages, setMarriages] = useState([]);
    const [isAdd, setAdd] = useState(false);
    const toast = useToast();
    const [reload, setReload] = useState(false);

    const onDelete = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>, value: any) => {
        e.stopPropagation()
        divorceMarriage(value.id).then((res) => {
            setReload(true);
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
            key: 'party_1_id',
            numeric: false,
            title: 'Party 1 ID'
        },
        {
            key: 'party_2_id',
            numeric: false,
            title: 'Party 2 ID'
        },
        {
            key: 'witness_1_id',
            numeric: false,
            title: 'Witness 1 ID'
        },
        {
            key: 'witness_2_id',
            numeric: false,
            title: 'Witness 2 ID'
        },
        {
            key: 'divorced_at',
            title: 'STATUS',
            componentInstead: (value: any) => {
                return !value?.divorced_at ? <Button color="red" onClick={(e) => onDelete(e, value)}>Divorce</Button> : <>Divorced</>
            }
        }]

    useEffect(() => {
        if (!isAdd) {
            getMarriages().then((res) => {
                setMarriages(res?.data)
            });
        }
    }, [isAdd, reload]);

    return isAdd ?
        <>
            <Flex flex={1} justifyContent="center">
                <Button mt={5} width={500} color="teal" onClick={() => setAdd(false)}>Back to marriages</Button>
            </Flex>
            <WorkerAddMarriage onFinish={() => setAdd(false)} />
        </> :
        <Box mt={20}>
            <CommonDataTable
                data={marriages}
                searchInputProps={{ width: 500, m: 2 }}
                tableHeadings={columns}
            />
            <Flex flex={1} justifyContent="flex-end">
                <Button mt={5} color="teal" onClick={() => setAdd(true)}>Add marriage</Button>
            </Flex>
        </Box>
};

export default WorkerViewMarriages;
