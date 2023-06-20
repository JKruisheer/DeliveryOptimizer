import {useEffect, useState} from "react";
import axios from "axios";
import {TransactionType} from "../data/TransactionType.ts";
import {Table, TableCaption, TableContainer, Tbody, Td, Th, Thead, Tr} from "@chakra-ui/react";

export const TransactionsOverview = () => {
    const [transactions, setTransactions] = useState<TransactionType[]>([]);
    useEffect(() => {
        axios
            .get("http://localhost:8080/api/v1/inventory/all")
            .then((res) => {
                setTransactions(res.data)
            })
    }, []);
    return (
        <TableContainer>
            <Table variant='simple'>
                <TableCaption>Transaction orders</TableCaption>
                <Thead>
                    <Tr>
                        <Th>Id</Th>
                        <Th>Name</Th>
                    </Tr>
                </Thead>
                <Tbody>
                    {transactions.map((item) => {
                        return (
                        <Tr key={item.id}>
                            <Td>{item.id}</Td>
                                <Td>{item.orderName}</Td>
                        </Tr>
                       )
                    })}
                </Tbody>
            </Table>
        </TableContainer>
    )
}