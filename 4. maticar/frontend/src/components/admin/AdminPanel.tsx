import React from 'react';
import { Tab, TabList, TabPanels, Tabs } from '@chakra-ui/react';
import AdminViewWorkers from './AdminViewWorkers';

const AdminPanel = () => {
  return <Tabs width="95%" isFitted variant="line">
    <TabList>
      <Tab>Workers</Tab>
    </TabList>
    <TabPanels>
      <AdminViewWorkers />
    </TabPanels>
  </Tabs>
};

export default AdminPanel;
