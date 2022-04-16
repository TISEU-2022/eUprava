import { Button, Flex, Heading } from '@chakra-ui/react';
import React from 'react';
import { jsPDF } from "jspdf";
import { useRecoilValueLoadable } from 'recoil';

const UserPanel = () => {
  const onBirthCertificateButton = () => {
  }
  return <Flex justifyContent={'center'} flexDirection={'column'} gap={10}>
    <Heading>UserPanel</Heading>
    <Button width={400} color={'teal'} onClick={onBirthCertificateButton}>Download your Birth Certificate PDF</Button>
    <Button width={400} color={'teal'}>Download your Citizenship PDF</Button>
  </Flex>
};

export default UserPanel;
