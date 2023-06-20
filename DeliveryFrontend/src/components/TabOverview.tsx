import {Tabs, TabList, TabPanels, Tab, TabPanel} from '@chakra-ui/react'
import {TransactionsOverview} from "./TransactionsOverview.tsx";

const TabOverview = () => {
    return (
        <Tabs variant='enclosed'>
            <TabList>
                <Tab>Inventory</Tab>
                <Tab>Transactions</Tab>
            </TabList>
            <TabPanels>
                <TabPanel>
                    <p>Here comes the inventory list</p>
                </TabPanel>
                <TabPanel>
                    <TransactionsOverview/>
                </TabPanel>
            </TabPanels>
        </Tabs>
    )
}

export default TabOverview;