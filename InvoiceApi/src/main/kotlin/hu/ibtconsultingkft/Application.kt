package hu.ibtconsultingkft

import hu.ibtconsultingkft.data.storage.initializeInvoiceItems
import hu.ibtconsultingkft.data.storage.invoiceCountConst
import hu.ibtconsultingkft.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        //for demo usage, db sim... (const from "db" file)
        initializeInvoiceItems(invoiceCountConst)

        configureCors()
        configureRouting()
        configureMonitoring()
        configureSerialization()
        configureDI()
    }.start(wait = true)
}
