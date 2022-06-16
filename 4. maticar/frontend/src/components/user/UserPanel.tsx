import { Button, Flex, Heading, useToast } from '@chakra-ui/react';
import React from 'react';
import { jsPDF } from "jspdf";
import { useRecoilValueLoadable } from 'recoil';
import { userSelector } from '../../state/auth/auth.atom';
import { getUserBirthCertificate } from '../../api/userApi';

const UserPanel = () => {
  const user = useRecoilValueLoadable(userSelector).contents;
  const toast = useToast();

  const onBirthCertificateButton = () => {
    getUserBirthCertificate(user?.identityNumber).then((res) => {
      let birth_cert = res?.data?.birth_certificate
      const doc = new jsPDF();
      let textList = []
      doc.setFontSize(20);
      doc.setTextColor(40);
      textList.push("USER BIRTH CERTIFICATE");
      textList.push("")
      textList.push("")
      textList.push(`Identification number: ${birth_cert?.identification_number}`)
      textList.push(`First name: ${birth_cert?.first_name}`)
      textList.push(`Last name: ${birth_cert?.last_name}`)
      textList.push(`Gender: ${birth_cert?.gender}`)
      textList.push(`Date of birth: ${birth_cert?.date_of_birth}`)
      textList.push(`Country of birth: ${birth_cert?.country_of_birth}`)
      if (res?.data?.parents) {
        textList.push("")
        textList.push("")
        textList.push(`Parent 1 Identification number: ${res.data.parents[0]}`)
        textList.push(`Parent 2 Identification number: ${res.data.parents[1]}`)
      }
      doc.text(textList, 10, 10)
      doc.save("user_birth_certificate.pdf");
    }).catch((error) => {
      toast({
        title: 'No birth certificate!',
        status: 'error',
        duration: 9000,
        isClosable: true,
      })
    })
  }


  const onCitizenshipButton = () => {
    getUserBirthCertificate(user?.identityNumber).then((res) => {
      let birth_cert = res?.data?.birth_certificate
      const doc = new jsPDF();
      let textList = []
      doc.setFontSize(20);
      doc.setTextColor(40);
      textList.push("USER CITIZENSHIP");
      textList.push("")
      textList.push("")
      textList.push(`Identification number: ${birth_cert?.identification_number}`)
      textList.push(`First name: ${birth_cert?.first_name}`)
      textList.push(`Last name: ${birth_cert?.last_name}`)
      textList.push(`Citizenship: ${birth_cert?.citizenship}`)
      doc.text(textList, 10, 10)
      doc.save("user_citizenship.pdf");
    }).catch((error) => {
      toast({
        title: 'No citizenship!',
        status: 'error',
        duration: 9000,
        isClosable: true,
      })
    })
  }
  return <Flex justifyContent={'center'} flexDirection={'column'} gap={10}>
    <Heading>User Panel</Heading>
    <Button width={400} color={'teal'} onClick={onBirthCertificateButton}>Download your Birth Certificate PDF</Button>
    <Button width={400} color={'teal'} onClick={onCitizenshipButton}>Download your Citizenship PDF</Button>
  </Flex>
};

export default UserPanel;
