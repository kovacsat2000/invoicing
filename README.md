# Invoicing application
Our program is an application to create invoices. 
You receive a list of invoice items, from which you can choose one or more items, and the application generates a PDF file of them. 
If you want, you can download the PDF to your computer, or just simply preview it in your browser. 
## Technologies used 
|                |Technology                     |Description                         |
|----------------|-------------------------------|-----------------------------|
|Design			 |`Primefaces - PrimeNG`		 |For design elements		   |
|Frontend		 |`Angular`            |Web application framework 				           |
|Backend		 |`Ktor`          			 |Framework to build web applications|
|Database        |`Simulated`					 |Mutable list of invoice items|
|Others			 |`Timrs's pdf builder`			 |Open source pdf builder lib  |
|				 |`JVM`							 |Version: 18				   |
|				 |`Kotlin's unit test`		     |For unit tests			   |
## Starting the application
### Installations
Install **gradle** by instructions from [here](https://gradle.org/install/). 
> **Note:** You should have minimum JDK version 18 installed on your computer to run the application

Install **angular** by instructions from [here](https://angular.io/guide/setup-local). 
> **Note:** You should have Node.js and npm package manager installed on your computer

### Backend
Navigate to `InvoiceApi` directory in the command line, and run the following command: **```gradle run```**
> **Note:** The API listening for requests at localhosts 8080 port

### Frontend
Navigate to `InvoiceApplication` directory in the command line, and run the following command: **```ng serve```**
> **Note:** You can access the application at localhosts 4200 port

## Api endpoints
These endpoints allows you to get invoice items and generate pdf files.
### GET ```/api/invoiceitem```
**Response:**
```
[
    {
        "id": 1,
        "name": "Uqktoi Gwydvg",
        "amount": 49.61
    },
    {
        "id": 2,
        "name": "Cfwkxr Ijsur",
        "amount": 62.84
    }
]
```
### POST```/api/pdfgenerator```
**Parameters:**
|Name          |Required |Type                  |Description
|--------------|---------|----------------------|-----------------------------|
|`ids`		   |required |List of integers		|Invoice items ids for which to perform the action|

**Response:**
```
A file of type pdf generated by API
```

![Example](example.pdf)