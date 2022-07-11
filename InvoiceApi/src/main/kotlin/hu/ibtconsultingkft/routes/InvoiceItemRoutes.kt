package hu.ibtconsultingkft.routes

import hu.ibtconsultingkft.plugins.apiBegin
import hu.ibtconsultingkft.services.InvoiceItemService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.invoiceItemRouting() {

    val invoiceItemServiceImpl by inject<InvoiceItemService>()

    //responds whole invoice item list
    route("$apiBegin/invoiceitem") {
        get {
            call.respond(invoiceItemServiceImpl.getInvoiceItems())
        }
    }

}