package hu.ibtconsultingkft.data.model

import kotlinx.serialization.Serializable

@Serializable
data class InvoiceItem(val id: Int, val name: String, val amount: Double)
