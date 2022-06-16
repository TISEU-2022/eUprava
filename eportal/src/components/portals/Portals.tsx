import { Box, Flex, useColorModeValue, Text, Link, Spacer, Grid, GridItem } from "@chakra-ui/react";
import React from "react";
import { useRecoilValue } from "recoil";
import { tokenAtom } from "../../state/auth/auth.atom";

const Portals: React.FC = () => {
    const currentToken = useRecoilValue(tokenAtom);

    const portalNavigator = (portPrefix: string) => {
        window.location.href = `http://localhost:${portPrefix}/auth/token_handler?token=${currentToken}`
    }

    const portals: [string, string][] = Object.entries({
        1000: "Visoko obrazovanje",
        2000: "Zavod za statistiku",
        3000: "Nacionalna sluzba za zaposljavanje",
        4001: "Maticar",
        5000: "Zdravstvo",
        6000: "MUP Vozila",
        7000: "eObrazovanje",
        8000: "MUP Oruzije",
        9000: "Komunalna milicija i inspekcija",
        10000: "Biracki spisak i eGlasanje",
        11000: "MUP Licna dokumenta",
        12000: "Portal otvorenih podataka",
        13000: "Predskolske ustanove",
        14000: "Saobracajna policija",
        15000: "SUD",
    });
    return <Grid templateColumns={"repeat(4, 1fr)"} gap={6}>
        {portals.map((portal) => (
            <GridItem w='100%' h='12'>
                <Box w="full" maxW="sm" mx="auto" px={4} py={3} bg={useColorModeValue("white", "gray.800")} shadow="md" rounded="md" flex="1">
                    <Text size="xl">
                        <Link onClick={() => portalNavigator(portal[0])}>{portal[1]}</Link>
                    </Text>
                </Box>
            </GridItem>
        ))} </Grid>
}

export default Portals;