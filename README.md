# Spring Boot Product API

This project is a simple Spring Boot application that provides RESTful endpoints for managing products. Each product has a name and a price. The application supports retrieving all products, adding a product, finding a product by its name as well as deleting and updating a product by name.  It also features custom exception handling and basic security with basic rol based authentication.

## Features

- **Get All Products**: Retrieve a list of all products.
- **Find Product by Name**: Retrieve a specific product by its name.
- **Update Product by Name**: Updates a specific product by its name.
- **Delete Product by Name**: Deletes a specific product by its name.
- **Add Product**: Adds a product
- **Exception Handling**: Custom exception handling for not found products.
- **Security**: Basic authentication to protect the endpoints.

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/AndreiPopescu95/product-hub-project.git
cd product-hub-project
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start and be accessible at `http://localhost:8082`.

## Security and Authentication

The API endpoints are protected using basic authentication. You need to provide a valid username and password to access the endpoints.

### Sample Security Configuration

The current configuration allows all authenticated users to access the endpoints witht he followinf roles:
- USER
- ADMIN

### Login

To login access the default login form:
```http
http://localhost:8082/login
```
Also, you are able to login by adding the username and password in the request or in Postman's Authentication tab

### Logout
To logout:
```http
http://localhost:8082/logout
```

## API Endpoints

### Get All Products

- **URL**: `/api/products`
- **Method**: `GET`
- **Response**: JSON array of all products

#### Example Request

```http
GET /api/products
Authorization: Basic <base64_encoded_credentials>
```

#### Example Response

```json
[
  {
    "productName": "Product 1",
    "price": 10.0
  },
  {
    "productName": "Product 2",
    "price": 20.0
  }
]
```

### Find Product by name

- **URL**: `/api/products/{productName}`
- **Method**: `GET`
- **Response**: JSON object of the requested product

#### Example Request

```http
GET /api/products/Product%201
Authorization: Basic <base64_encoded_credentials>
```

### Add Product

- **URL**: `/api/products/add`
- **Method**: `POST`
- **Payload**: 
```json
  {
  "productName": "Product 1",
  "price": 10.0
  }
```
- **Response**: JSON object of the created product

#### Example Response
201 Created
```json
{
  "productName": "Product 1",
  "price": 10.0
}
```

### Update Product by name

- **URL**: `/api/products/update/{productName}`
- **Method**: `PUT`
- **Payload**: 
```json
  {
  "price": 10.0
  }
```
- **Response**: JSON object of the updated product

#### Example Request

```http
GET /api/products/delete/Product%201
Authorization: Basic <base64_encoded_credentials>
```

#### Example Response

```json
{
  "productName": "Product 1",
  "price": 10.0
}
```

### Delete Product by name

- **URL**: `/api/products/delete/{productName}`
- **Method**: `DELETE`
- **Response**: 204 ok

#### Example Request

```http
GET /api/products/delete/Product%201
Authorization: Basic <base64_encoded_credentials>
```

### Exception Handling

If a product is not found, the API will return a `404 Not Found` status with a descriptive error message.

#### Example Response for Product Not Found

```json
{
  "message": "Product not found with name: Nonexistent Product"
}
```

## Configuration

### `application.yml`

The application runs on port `8082`. This can be configured in the `application.yml` file.

```yaml
server:
  port: 8082
```

## Running Tests

To run the unit tests, use the following command:

```bash
mvn test
```