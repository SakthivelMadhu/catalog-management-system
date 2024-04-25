
# Catalog Management System

This is a Spring Boot application for managing a catalog of products.

## Setup Instructions

### Prerequisites

- JDK (Java Development Kit) installed on your system.
- Maven build tool installed on your system.
- MySQL database server installed and running. Ensure you have the database URL, username, and password.

### Configuration

1. Clone or download the project source code from the repository.
2. Open the `application.properties` file located in the `src/main/resources` directory.
3. Configure the database connection properties:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/catalog_management_system
   spring.datasource.username=root
   spring.datasource.password=Sakthivel1402!
   ```


## Project Structure : 

```bash 
catalog-management-system
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── catalog
│   │   │               ├── controller
│   │   │               │   └── ProductController.java
│   │   │               ├── entity
│   │   │               │   └── Product.java
│   │   │               ├── repository
│   │   │               │   └── ProductRepository.java
│   │   │               ├── service
│   │   │               │   ├── ProductService.java
│   │   │               │   └── ProductServiceImpl.java
│   │   │               └── CatalogManagementSystemApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── data.sql
│   │       └── logback.xml
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── catalog
│                       ├── controller
│                       │   └── ProductControllerTest.java
│                       ├── repository
│                       │   └── ProductRepositoryTest.java
│                       └── service
│                           └── ProductServiceTest.java
└── README.md
└── pom.xml

```





### Build and Run

1. Build the project using Maven:

```bash
mvn clean package
```
2. Run the Spring Boot application:
```bash
java -jar target/catalog-management-system.jar
```

### Accessing API Endpoints

Once the application is running, you can access the following API endpoints:

* GET all products: http://localhost:8080/products
* GET product by ID: http://localhost:8080/products/{id}
* POST create product: http://localhost:8080/products
* PUT update product: http://localhost:8080/products/{id}
* DELETE delete product: http://localhost:8080/products/{id}

## API Request/Response Formats

### GET all products
1. Request: GET /products
2. Response:
    * Status Code: 200 OK
    * Body: Array of product objects.

### GET product by ID
1. Request: GET /products/{id}
2. Response:
    * Status Code: 200 OK if found, 404 Not Found if not found
    * Body: Single product object.


### POST create product
1. Request: POST /products
2. Body:
```bash
{
  "name": "Product Name",
  "brand": "Product Brand",
  "description": "Product Description",
  "price": 10.99,
  "quantity": 100,
  "category": "Product Category"
}
```
3. Response:
    * Status Code: 201 Created
    * Body: Created product object.

### PUT update product
1. Request: PUT /products/{id}
2. Body:
```bash
{
  "name": "Updated Product Name",
  "brand": "Updated Product Brand",
  "description": "Updated Product Description",
  "price": 15.99,
  "quantity": 150,
  "category": "Updated Product Category"
}
```
3. Response:
    * Status Code: 200 OK
    * Body: Updated product object.


### DELETE delete product
1. Request: DELETE /products/{id}
2. Response:
    * Status Code: 204 No Content if successful, 404 Not Found if product not found