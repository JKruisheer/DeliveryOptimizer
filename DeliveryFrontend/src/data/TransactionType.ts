export interface TransactionType {
    id: number;
    lines: TransactionOrderLine[];
    orderName: string;
}

export interface TransactionOrderLine {
    id: number;
    quantity: number;
}