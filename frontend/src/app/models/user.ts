import { Wallet } from "./wallet"

export interface User {
    id: string,
    name: string,
    email: string,
    password: string
    wallet: Wallet
}

