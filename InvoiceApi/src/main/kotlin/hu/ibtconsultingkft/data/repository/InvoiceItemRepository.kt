package hu.ibtconsultingkft.data.repository

import hu.ibtconsultingkft.data.model.InvoiceItem

interface InvoiceItemRepository {

    fun getInvoiceItems(): List<InvoiceItem>

}