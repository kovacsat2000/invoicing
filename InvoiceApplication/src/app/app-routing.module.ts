import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";
import { ErrorPageComponent } from "./components/error-page/error-page.component";
import { InvoiceTableComponent } from "./components/invoice-table/invoice-table.component";

const routes: Routes = [
  { path: '',   redirectTo: '/invoice-table', pathMatch: 'full' },
  { path: 'invoice-table', component: InvoiceTableComponent },
  { path: 'error-page', component: ErrorPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
