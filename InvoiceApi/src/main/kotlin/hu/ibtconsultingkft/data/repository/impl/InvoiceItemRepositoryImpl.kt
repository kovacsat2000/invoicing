package hu.ibtconsultingkft.data.repository.impl

import hu.ibtconsultingkft.data.model.InvoiceItem
import hu.ibtconsultingkft.data.repository.InvoiceItemRepository
import hu.ibtconsultingkft.data.storage.invoiceItemStorage

class InvoiceItemRepositoryImpl : InvoiceItemRepository {

    override fun getInvoiceItems(): List<InvoiceItem> {
        return invoiceItemStorage
    }

}
