package hu.ibtconsultingkft.services.impl

import hu.ibtconsultingkft.data.model.InvoiceItem
import hu.ibtconsultingkft.data.repository.InvoiceItemRepository
import hu.ibtconsultingkft.services.InvoiceItemService

class InvoiceItemServiceImpl(private val invoiceItemRepository: InvoiceItemRepository) : InvoiceItemService {

    override fun getInvoiceItems(): List<InvoiceItem> {
        return invoiceItemRepository.getInvoiceItems()
    }

    override fun getNeededInvoiceItems(ids: List<Int>): List<InvoiceItem> {
        return getInvoiceItems().filter { it.id in ids }
    }

}