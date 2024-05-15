package domain

import org.example.domain.Transaction
import org.example.utils.hash
import java.time.Instant

data class Block(val previousHash: String,
                 val transactions: MutableList<Transaction> = mutableListOf(),
                 val timestamp: Long = Instant.now().toEpochMilli(),
                 val nonce: Long = 0,
                 var hash: String = "") {
    init {
        hash = calculateHash()
    }

    fun calculateHash(): String {
        return "$previousHash$timestamp$nonce".hash()
    }

    fun addTransaction(transaction: Transaction) : Block {

        if (transaction.isSignatureValid())
            transactions.add(transaction)
        return this
    }

}