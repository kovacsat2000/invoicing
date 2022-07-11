package hu.ibtconsultingkft.routes

import hu.ibtconsultingkft.plugins.apiBegin
import hu.ibtconsultingkft.services.PdfGeneratorService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.decodeFromJsonElement
import org.koin.ktor.ext.inject

fun Route.pdfGeneratorRouting() {

    val pdfGeneratorServiceImpl by inject<PdfGeneratorService>()

    //responds a needed pdf file with selected invoice items from fe
    route("$apiBegin/pdfgenerator") {

        post {
            val idsJson = call.receive<JsonArray>()
            val ids = Json.decodeFromJsonElement<List<Int>>(idsJson)
            call.respondFile(pdfGeneratorServiceImpl.getPdf(ids))
        }

    }

}
