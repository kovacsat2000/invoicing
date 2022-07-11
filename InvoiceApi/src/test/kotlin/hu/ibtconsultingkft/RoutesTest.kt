package hu.ibtconsultingkft

import hu.ibtconsultingkft.data.model.InvoiceItem
import hu.ibtconsultingkft.data.storage.initializeInvoiceItems
import hu.ibtconsultingkft.data.storage.invoiceCountConst
import hu.ibtconsultingkft.plugins.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlin.test.Test
import kotlin.test.assertEquals

class RoutesTest {

    @Test
    fun testInvoiceItemRoutes() = testApplication {

        application {
            initializeInvoiceItems(invoiceCountConst)

            configureCors()
            configureRouting()
            configureMonitoring()
            configureSerialization()
            configureDI()
        }

        client.get("$apiBegin/invoiceitem").apply {
            assertEquals(HttpStatusCode.OK, status)

            val jsonResp = Json.parseToJsonElement(body())
            val typedResp = Json.decodeFromJsonElement<List<InvoiceItem>>(jsonResp)

            assertEquals(invoiceCountConst, typedResp.size)
        }

    }

}
