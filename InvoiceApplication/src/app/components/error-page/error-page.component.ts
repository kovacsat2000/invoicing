import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.css']
})
export class ErrorPageComponent implements OnInit {

  title: string = 'Oops, something went wrong';
  message: string = 'Can\'t connect to server';

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  retryLoadInvoiceItems() {
    this.router.navigateByUrl('/').then();
  }
}
