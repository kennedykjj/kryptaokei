package org.example

import domain.Block

fun main() {
    val genesisBlock = Block(previousHash = "0", data = "I'm the first")
    val secondBlock = Block(genesisBlock.hash, "I'm the second")
    val thirdBlock = Block(secondBlock.hash, "I'm the third")

    println(genesisBlock)
    println(secondBlock)
    println(thirdBlock)}