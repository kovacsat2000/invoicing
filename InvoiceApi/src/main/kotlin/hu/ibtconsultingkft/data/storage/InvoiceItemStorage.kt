package hu.ibtconsultingkft.data.storage

import hu.ibtconsultingkft.data.model.InvoiceItem
import kotlin.math.round
import kotlin.random.Random

const val invoiceCountConst: Int = 5

val invoiceItemStorage = mutableListOf<InvoiceItem>()

//for demo usage, db sim...
fun initializeInvoiceItems(invoiceCount: Int) {
    for (i in 0 until invoiceCount) {
        val randomDbl: Double = round((0.01 + Random.nextDouble() * (99.99 - 0.01)) * 100) / 100
        invoiceItemStorage.add(InvoiceItem(i + 1, getRandomName(), randomDbl))
    }
}

private fun getRandomName(): String {
    val firstName: String
    val lastName: String

    val firstNameLength = (3..8).random()
    val lastNameLength = (3..8).random()

    val allowedChars = ('a'..'z')
    firstName = (1..firstNameLength)
        .map { allowedChars.random() }
        .joinToString("")
    lastName = (1..lastNameLength)
        .map { allowedChars.random() }
        .joinToString("")

    return "${firstName.replaceFirstChar { it.titlecase() }} ${lastName.replaceFirstChar { it.titlecase() }}"
}
