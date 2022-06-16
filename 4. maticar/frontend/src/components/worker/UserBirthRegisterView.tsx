import { Box, Button, Flex, useToast } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { divorceMarriage, getMarriages } from '../../api/marriagesApi';
import { getUsers } from '../../api/workersApi';
import CommonDataTable from '../common/CommonDataTable';
import WorkerAddMarriage from './WorkerAddMarriage';

const UserBirthRegisterView = () => {
    const [users, setUsers] = useState([]);

    const columns = [
        {
            key: 'identification_number',
            numeric: false,
            title: 'Iden number'
        },
        {
            key: 'first_name',
            numeric: false,
            title: 'First name'
        },
        {
            key: 'last_name',
            numeric: false,
            title: 'Last name'
        },
        {
            key: 'citizenship',
            numeric: false,
            title: 'Citizenship'
        },
        {
            key: 'country_of_birth',
            numeric: false,
            title: 'Country of birth'
        },
        {
            key: 'date_of_birth',
            numeric: false,
            title: 'Date of birth'
        },
        {
            key: 'gender',
            numeric: false,
            title: 'Gender'
        },
        {
            key: 'deceased_at',
            title: 'Life status',
            componentInstead: (value: any) => {
                return <>{value?.deceased_at ? 'Deceased' : 'Alive'}</>
            }
        },]

    useEffect(() => {
            getUsers().then((res) => {
                setUsers(res?.data)
            });
    }, []);

    return <Box mt={20}>
            <CommonDataTable
                data={users}
                searchInputProps={{ width: 500, m: 2 }}
                tableHeadings={columns}
            />
        </Box>
};

export default UserBirthRegisterView;
