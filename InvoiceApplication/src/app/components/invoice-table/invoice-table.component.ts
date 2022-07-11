import { Component, OnInit } from '@angular/core';
import { InvoiceItemService } from "../../services/invoice-item.service";
import { InvoiceItem } from "../../models/InvoiceItem";
import { PdfGeneratorService } from "../../services/pdf-generator.service";
import { saveAs as importedSaveAs } from "file-saver";
import {catchError, delay, retry} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-invoice-table',
  templateUrl: './invoice-table.component.html',
  styleUrls: ['./invoice-table.component.css']
})
export class InvoiceTableComponent implements OnInit {
  invoiceList: InvoiceItem[] = [];
  selectedValues: boolean[];
  selectedInvoiceItems: InvoiceItem[] = []

  noSelectedItems: boolean = true;
  nothingToPreviewOrDownload: boolean = true;

  generatedPdf: any;
  fileType: string = "application/pdf";

  pdfBlob: Blob;
  fileName: string = "generated_pdf";
  allSelected: boolean = false;
  showSpinner: boolean = true;

  constructor(private invoiceItemService: InvoiceItemService,
              private pdfGeneratorService: PdfGeneratorService,
              private router: Router) { }

  ngOnInit(): void {
    this.invoiceItemService.getInvoiceItems().pipe(
      retry(2),
      delay(1000),
      catchError(err => {
        this.router.navigateByUrl('error-page').then();
        throw err;
      })
    ).subscribe(res => {
      this.showSpinner = false;
      this.invoiceList = res;
    });
  }

  generatePdf() {
    let selectedIdList: number[] = []
    for (let i of this.selectedInvoiceItems) {
      i.checked ? selectedIdList.push(i.id) : null;
    }

    this.pdfGeneratorService.generatePdf(selectedIdList).subscribe( res => {
      this.generatedPdf = res;

      this.pdfBlob = new Blob([res], { type: this.fileType});

      this.nothingToPreviewOrDownload = false;
    });
  }

  reviewSelectionChange() {
    this.selectedInvoiceItems = this.invoiceList.filter(item => item.checked == true);
    this.noSelectedItems = this.selectedInvoiceItems.length == 0;
    this.invoiceList.length == this.selectedInvoiceItems.length ? this.allSelected = true : this.allSelected = false
  }

  previewPdf() {
    let url = window.URL.createObjectURL(this.pdfBlob);
    let pwa = window.open(url);

    if (!pwa || pwa.closed || typeof pwa.closed == 'undefined') {
      alert( 'Please disable your Pop-up blocker and try again.');
    }
  }

  downloadPdf() {
    importedSaveAs(this.pdfBlob, this.fileName);

    this.nothingToPreviewOrDownload = true;
    this.generatedPdf = null;
    this.pdfBlob = null;
    this.deselectSelectedItems();
  }

  deselectSelectedItems() {
    this.noSelectedItems = true;
    for (let i of this.invoiceList)
      i.checked = false;
    this.allSelected = false;
  }

  reviewAllSelectionChange() {
    if (this.allSelected) {
      for (let i of this.invoiceList)
        i.checked = true;
    } else {
      this.deselectSelectedItems();
    }

    this.reviewSelectionChange()
  }
}
