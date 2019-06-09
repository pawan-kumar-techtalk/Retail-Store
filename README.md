# Media Ocean - Retail Store

Demonstration of a Micro-Service based project created using Dropwizard to maintain Products and its Category of a retail store. 
It also contains an API to generate the itemized bill with sales tax.

Assumption:

Please note all UI part is assumed here, however, created relevant APIs in this project to support the requirement.
There will be a thick UI client which will have all product information by calling below mentioned Product APIs.
Once the product scanning is done and selected products are part of a cart, UI client will call below mentioned Billing API 
with Product IDs in cart to generate itemized bill.


Technology stack used:
1) Dropwizard framework
2) Embedded H2 Database (Database included in Project path ./database)
2) Spring IOC
3) JDBI Library for database interaction
4) REST with JAX-RS
5) Maven
6) Swagger for API testing (Refer below for Swagger URL and use it for executing APIs)
7) Mockito with TestNg suite for unit testing (Covered very basic testing due to time crunch)

In order to run this project, please do following steps:
1) Checkout project in local machine
2) Go to project root directory
3) Build using MAVEN (command:  mvn clean package)
4) Run application from command-line: (command:  ./run.sh) 

Ports:

Application run on port: 9000

Debugging Port: 4005

PLEASE USE FOLLOWING SWAGGER URL TO TEST ALL APIs FROM A BROWSER FOR CONVENIENCE:  
Swagger UI URL: http://localhost:9000/media-ocean/retail-store/swagger 

Swagger instructions:
 
1) Make sure application is running
2) Hit above mentioned Swagger URL from a browser
3) Click on Headers to expand API list and select API to execute
4) Update appropriate API request body, if required
5) Click on 'Try it out!' button


Note: 

Correct ProductIDs stored in DB from 1 to 15 (For more details hit Product APIs)

Correct Category IDs stored in DB from 1 to 3 (For more details hit Category APIs)

API Details:
1) To generate itemized bill of given Product IDs along with sales tax based on product category:
 
 POST    http://localhost:9000/media-ocean/retail-store/billing
 
 Header:
  Accept:application/json
  Content-Type:application/json  
  
 Example of body of API Request:
    
    {
      "listProductId": [
        1,2,3
      ]
    }
          
2) To create a category:
 
 POST    http://localhost:9000/media-ocean/retail-store/categories
 
 Header:
  Accept:application/json
  Content-Type:application/json
   
 Example of body of API Request:
 
 {
   "name": "NEW-CAT1",
   "salesTax": 20
 }

4) To get list of all categories:
 
 GET    http://localhost:9000/media-ocean/retail-store/categories
 
 Header:
  Accept:application/json
  Content-Type:application/json

 
5) To create a product:
 
 POST     http://localhost:9000/media-ocean/retail-store/products
 
 Header:
  Accept:application/json
  Content-Type:application/json
 
 Example of body of API Request:
 
 {
   "name": "NEW-PROD1",
   "categoryId": 2,
   "price": 1000
 }
 
 6) To get list of all products:
 
 GET http://localhost:9000/media-ocean/retail-store/products
 
 Header:
  Accept:application/json
  Content-Type:application/json
  
  
