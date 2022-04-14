import React from 'react';
import { Box, Flex, Tab, TabList, TabPanel, TabPanels, Tabs } from '@chakra-ui/react';
import WorkerViewMarriages from './WorkerViewMarriages';
import UserBirthRegisterView from './UserBirthRegisterView';

const WorkerPanel = () => {
  return <Tabs width="95%" isFitted variant="line">
    <TabList>
      <Tab>Marriages</Tab>
      <Tab>User birth registers</Tab>
    </TabList>
    <TabPanels>
      <TabPanel>
      <WorkerViewMarriages />
      </TabPanel>
      <TabPanel>
      <UserBirthRegisterView />
      </TabPanel>
    </TabPanels>
  </Tabs>
};

export default WorkerPanel;
