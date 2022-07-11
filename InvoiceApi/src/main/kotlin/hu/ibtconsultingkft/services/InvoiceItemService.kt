package hu.ibtconsultingkft.services

import hu.ibtconsultingkft.data.model.InvoiceItem

interface InvoiceItemService {

    fun getInvoiceItems(): List<InvoiceItem>

    fun getNeededInvoiceItems(ids: List<Int>): List<InvoiceItem>

}