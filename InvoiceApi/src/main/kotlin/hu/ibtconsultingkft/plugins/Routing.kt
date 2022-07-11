package hu.ibtconsultingkft.plugins

import hu.ibtconsultingkft.routes.invoiceItemRouting
import hu.ibtconsultingkft.routes.pdfGeneratorRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val apiBegin: String = "/api";

fun Application.configureRouting() {
    install(StatusPages) {
        exception<AuthenticationException> { call, cause ->
            call.respond(HttpStatusCode.Unauthorized)
        }

        exception<AuthorizationException> { call, cause ->
            call.respond(HttpStatusCode.Forbidden)
        }

        exception<Exception> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, cause.toString())
        }
    }

    routing {
        invoiceItemRouting()
        pdfGeneratorRouting()

        get("/favicon.ico") {
            call.respondBytes(byteArrayOf())
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
