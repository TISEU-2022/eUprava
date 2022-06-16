import React from "react";
import { Flex } from "@chakra-ui/react";
import Navbar from "../navbar/Navbar";

const MainLayout: React.FC = (props) => (
  <Flex
    direction="column"
    maxW={{ xl: "1500px" }}
    m="0 auto"
    minH="100vh"
    {...props}
  >
    <Navbar />
    {props.children}
  </Flex>
);

export default MainLayout;