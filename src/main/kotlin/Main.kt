package org.example

import domain.Block
import domain.Wallet
import org.example.core.BlockChain
import org.example.domain.Transaction
import org.example.domain.TransactionOutput

fun main() {
    val blockChain = BlockChain()
    val wallet1 = Wallet.create(blockChain)
    val wallet2 = Wallet.create(blockChain)

    println("Wallet 1 balance: ${wallet1.balance}")
    println("Wallet 2 balance: ${wallet2.balance}")

    val tx1 = Transaction.create(sender = wallet1.publicKey, recipient = wallet1.publicKey, amount = 100)
    tx1.outputs.add(TransactionOutput(recipient = wallet1.publicKey, amount = 100, transactionHash = tx1.hash))

    var genesisBlock = Block(previousHash = "0")
    genesisBlock.addTransaction(tx1)
    genesisBlock = blockChain.add(genesisBlock)

    println("Wallet 1 balance: ${wallet1.balance}")
    println("Wallet 2 balance: ${wallet2.balance}")

    val tx2 = wallet1.sendFundsTo(recipient = wallet2.publicKey, amountToSend = 50)
    val secondBlock = blockChain.add(Block(genesisBlock.hash).addTransaction(tx2))

    println("Wallet 1 balance: ${wallet1.balance}")
    println("Wallet 2 balance: ${wallet2.balance}")
}