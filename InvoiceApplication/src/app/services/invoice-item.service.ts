import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from "../../environments/environment";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class InvoiceItemService {
  baseApiUrl = environment.baseApiUrl;

  constructor(private http: HttpClient) { }

  getInvoiceItems(): Observable<any> {
    return this.http.get(this.baseApiUrl + '/invoiceitem');
  }
}
