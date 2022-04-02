import { Box, Flex } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { getWorkers } from '../../api/api';
import CommonDataTable, { CommonDataTableHeadings } from '../common/CommonDataTable';

const AdminPanel = () => {
  const [workers, setWorkers] = useState([]);

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
    }]

  useEffect(() => {
    getWorkers().then((res) => {
      setWorkers(res?.data)
    });
  }, []);

  return <Box mt={20} ml={50} mr={50}>
    <CommonDataTable
      data={workers}
      searchInputProps={{width: 200, m: 2}}
      onRowClickHandler={(value) => {console.log(value)}}
      tableHeadings={columns}
    />
  </Box>
};

export default AdminPanel;
