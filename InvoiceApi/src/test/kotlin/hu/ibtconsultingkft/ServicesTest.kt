package hu.ibtconsultingkft

import hu.ibtconsultingkft.data.storage.initializeInvoiceItems
import hu.ibtconsultingkft.data.storage.invoiceCountConst
import hu.ibtconsultingkft.plugins.repositoryModule
import hu.ibtconsultingkft.plugins.serviceModule
import hu.ibtconsultingkft.services.InvoiceItemService
import hu.ibtconsultingkft.services.PdfGeneratorService
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertEquals

class ServicesTest : KoinTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            initializeInvoiceItems(invoiceCountConst)
        }
    }

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(repositoryModule, serviceModule)
    }

    private val invoiceItemService by inject<InvoiceItemService>()

    @Test
    fun testInvoiceItemService() {
        val allInvoiceItems = invoiceItemService.getInvoiceItems()
        assertEquals(invoiceCountConst, allInvoiceItems.size)
        assertEquals(false, allInvoiceItems.isEmpty())

        val neededInvoiceItems = invoiceItemService.getNeededInvoiceItems(listOf(2, 3, 4))
        assertEquals(3, neededInvoiceItems.size)
        assertEquals(false, neededInvoiceItems.isEmpty())
    }

    private val pdfGeneratorService by inject<PdfGeneratorService>()

    @Test
    fun testPdfGeneratorService() {
        val generatedPdf = pdfGeneratorService.getPdf(listOf(1, 2, 3))
        assertEquals(true, generatedPdf.isFile)
        assertEquals("pdf", generatedPdf.extension)
    }

}