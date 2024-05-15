package domain

import org.example.core.BlockChain
import org.example.domain.TransactionOutput
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey

data class Wallet(val publicKey: PublicKey, val privateKey: PrivateKey, val blockChain: BlockChain) {

    companion object {
        fun create(blockChain: BlockChain): Wallet {
            val generator = KeyPairGenerator.getInstance("RSA")
            generator.initialize(2048)
            val keyPair = generator.generateKeyPair()

            return Wallet(keyPair.public, keyPair.private, blockChain)
        }
    }

    val balance: Int get() {
        return getMyTransactions().sumBy { it.amount }
    }

    private fun getMyTransactions() : Collection<TransactionOutput> {
        return blockChain.UTXO.filterValues { it.isMine(publicKey) }.values
    }
}
