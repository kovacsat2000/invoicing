package hu.ibtconsultingkft.services

import java.io.File

interface PdfGeneratorService {

    fun getPdf(ids: List<Int>): File

}