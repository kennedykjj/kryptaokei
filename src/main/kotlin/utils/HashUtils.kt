package org.example.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

fun String.hash(algorithm: String = "SHA-256"): String {
        val messageDigest = MessageDigest.getInstance(algorithm)
        messageDigest.update(this.toByteArray())
        return String.format("%064x", BigInteger(1, messageDigest.digest()))
    }