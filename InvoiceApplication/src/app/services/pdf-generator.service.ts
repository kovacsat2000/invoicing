import { Injectable } from '@angular/core';
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PdfGeneratorService {
  baseApiUrl = environment.baseApiUrl;

  constructor(private http: HttpClient) { }

  generatePdf(ids: number[]): Observable<any> {
    return this.http.post(this.baseApiUrl + '/pdfgenerator', ids, {responseType: 'arraybuffer'});
  }
}
