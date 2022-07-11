import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
import { TopBarComponent } from './components/top-bar/top-bar.component';
import { InvoiceTableComponent } from './components/invoice-table/invoice-table.component';

import { TableModule } from "primeng/table";
import { CheckboxModule } from 'primeng/checkbox';
import { ButtonModule } from 'primeng/button';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    InvoiceTableComponent,
    ErrorPageComponent
  ],
  imports: [
    BrowserModule,
    TableModule,
    CheckboxModule,
    ButtonModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
