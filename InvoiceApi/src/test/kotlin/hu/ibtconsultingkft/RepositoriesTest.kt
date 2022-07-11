package hu.ibtconsultingkft

import hu.ibtconsultingkft.data.repository.InvoiceItemRepository
import hu.ibtconsultingkft.data.storage.initializeInvoiceItems
import hu.ibtconsultingkft.data.storage.invoiceCountConst
import hu.ibtconsultingkft.plugins.repositoryModule
import junit.framework.TestCase.assertEquals
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class RepositoriesTest : KoinTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            initializeInvoiceItems(invoiceCountConst)
        }
    }

    private val invoiceItemRepository by inject<InvoiceItemRepository>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(repositoryModule)
    }

    @Test
    fun testInvoiceItemRepository() {
        val allInvoiceItems = invoiceItemRepository.getInvoiceItems()

        assertEquals(invoiceCountConst, allInvoiceItems.size)
    }

}
