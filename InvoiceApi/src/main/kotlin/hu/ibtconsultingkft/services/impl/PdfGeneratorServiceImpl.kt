package hu.ibtconsultingkft.services.impl

import com.github.timrs2998.pdfbuilder.Document
import com.github.timrs2998.pdfbuilder.RowElement
import com.github.timrs2998.pdfbuilder.TableElement
import com.github.timrs2998.pdfbuilder.TextElement
import com.github.timrs2998.pdfbuilder.style.Alignment
import com.github.timrs2998.pdfbuilder.style.Border
import com.github.timrs2998.pdfbuilder.style.Margin
import com.github.timrs2998.pdfbuilder.style.Padding
import hu.ibtconsultingkft.data.model.InvoiceItem
import hu.ibtconsultingkft.services.InvoiceItemService
import hu.ibtconsultingkft.services.PdfGeneratorService
import org.apache.pdfbox.pdmodel.PDDocument
import java.awt.Color
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

class PdfGeneratorServiceImpl(private val invoiceItemService: InvoiceItemService) : PdfGeneratorService {

    override fun getPdf(ids: List<Int>): File {
        val filteredInvoiceItems: List<InvoiceItem> = invoiceItemService.getNeededInvoiceItems(ids)

        return generatePdf(filteredInvoiceItems)
    }

    private fun generatePdf(filteredInvoiceItems: List<InvoiceItem>): File {
        val pdfName = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_sss").format(Date())

        val pdf = File("GeneratedPdfs/$pdfName.pdf")

        val isNewFileCreated = pdf.createNewFile()

        if (isNewFileCreated) {
            generateDocument(filteredInvoiceItems).use { pdDocument ->
                pdDocument.save(pdf)
            }
        }

        return pdf
    }

    private fun generateDocument(filteredInvoiceItems: List<InvoiceItem>): PDDocument {
        val document = Document()

        setDocumentContent(document, filteredInvoiceItems)

        return document.render()
    }

    private fun setDocumentContent(document: Document, filteredInvoiceItems: List<InvoiceItem>) {
        val nowDate = SimpleDateFormat("yyyy-MM-dd' 'HH:mm").format(Date())

        document.padding = Padding(0f, 20f, 0f, 20f)

        var totalCount = 0.0

        val titleTable = TableElement(document)
        val titleRow = RowElement(titleTable)

        val titleText = TextElement(titleRow, "Invoice")
        titleText.fontColor = Color(0.345f, 0.345f, 0.345f)
        titleText.margin = Margin(230f, 0f, 50f, 10f)
        titleText.fontSize = 24f

        val titleDate = TextElement(titleRow, nowDate)
        titleDate.margin = Margin(244.4f, 20f, 50f, 0f)
        titleDate.fontColor = Color(0.560f, 0.560f, 0.560f)

        titleRow.columns.add(titleText)
        titleRow.columns.add(titleDate)

        titleTable.rows.add(titleRow)

        titleTable.rows[0].columns[1].horizontalAlignment = Alignment.RIGHT
        document.children.add(titleTable)

        val invoiceItemTable = TableElement(document)

        val headerRow = RowElement(invoiceItemTable)

        val idText = TextElement(headerRow, "ID")
        idText.padding = Padding(10f, 0f, 10f, 23f)
        idText.fontSize = 15f
        headerRow.columns.add(idText)
        val nameText = TextElement(headerRow, "Name")
        nameText.padding = Padding(10f, 0f, 10f, 5f)
        nameText.fontSize = 15f
        headerRow.columns.add(nameText)
        val amountText = TextElement(headerRow, "Amount")
        amountText.horizontalAlignment = Alignment.RIGHT
        amountText.padding = Padding(10f, 5f, 10f, 0f)
        amountText.fontSize = 15f
        headerRow.columns.add(amountText)

        invoiceItemTable.header = headerRow

        for (i in filteredInvoiceItems) {
            totalCount += i.amount

            val row = RowElement(invoiceItemTable)
            row.columns.add(TextElement(row, i.id.toString()))
            row.columns.add(TextElement(row, i.name))
            row.columns.add(TextElement(row, "$" + i.amount))

            invoiceItemTable.rows.add(row)
        }

        for (i in invoiceItemTable.rows) {
            for (j in i.columns) {
                j.padding = Padding(7f, 0f, 7f, 5f)
                j.fontSize = 14f
            }
        }

        for (i in invoiceItemTable.rows) {
            i.columns[0].margin = Margin(0f, 0f, 0f, 20f)
            i.columns[2].horizontalAlignment = Alignment.RIGHT
            i.columns[2].margin = Margin(0f, 5f, 0f, 0f)
        }

        for (i in invoiceItemTable.header!!.columns) {
            i.border = Border(0.7f, Color.DARK_GRAY)
        }

        invoiceItemTable.border = Border(1f, Color.BLACK)

        document.children.add(invoiceItemTable)

        val totalAmountTable = TableElement(document)

        val totalRow = RowElement(totalAmountTable)
        totalRow.columns.add(TextElement(totalRow, ""))
        totalRow.columns.add(TextElement(totalRow, "Total: "))
        totalRow.columns.add(TextElement(totalRow, "$" + (round(totalCount * 100) / 100)))
        totalAmountTable.rows.add(totalRow)

        totalAmountTable.rows[0].fontSize = 16f
        totalAmountTable.rows[0].columns[1].horizontalAlignment = Alignment.RIGHT
        totalAmountTable.rows[0].columns[2].horizontalAlignment = Alignment.RIGHT
        totalAmountTable.rows[0].padding = Padding(7f, 0f, 7f, 5f)
        totalAmountTable.rows[0].columns[1].padding = Padding(right = 5f, top = 7f)
        totalAmountTable.rows[0].columns[2].padding = Padding(right = 5f, top = 7f)
        totalAmountTable.rows[0].margin = Margin(-6f)

        document.children.add(totalAmountTable)
    }

}

