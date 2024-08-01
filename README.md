backend-firman-ismai-hariri

# E-commerce Platform

## Description
This project is an e-commerce platform that includes product management, order management, and user authentication functionalities. The application is built using Spring Boot and integrates various services to handle products, orders, and user login.

## Features
- User authentication and authorization
- Role-based access control for merchants and customers
- CRUD operations for products
- Order creation and management by customers
- Secure access to APIs using JWT tokens

## Technologies Used
- Java
- Spring Boot
- Spring Security
- JPA/Hibernate
- MySQL
- JWT
- Thymeleaf

## Setup
### Prerequisites
- Java 11+
- Maven
- MySQL

### Installation
1. **Clone the repository:**
    ```sh
    git clone https://github.com/https://github.com/akayasha/backend-firman-ismail-hariri.git
    cd your-repository
    ```

2. **Configure the database:**
    - Update the `application.properties` file with your MySQL database configuration.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/test-backend
    spring.datasource.username=root
    spring.datasource.password=pass
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build the project:**
    ```sh
    mvn clean install
    ```

4. **Run the application:**
    ```sh
    mvn spring-boot:run
    ```

### API Endpoints
#### Authentication
- **Login**
    ```
    POST /api/auth/login
    Request Body: { "username": "user", "password": "password" }
    Response: { "token": "jwt-token" }
    ```

#### Products
- **Create Product** (Merchant only)
    ```
    POST /api/products/create
    Request Body: { "name": "Product Name", "price": 100, "description": "Product Description" }
    Headers: { "Authorization": "Bearer jwt-token" }
    ```

- **List Products**
    ```
    GET /api/products
    Response: [ { "id": 1, "name": "Product Name", "price": 100, "description": "Product Description" }, ... ]
    ```

- **Update Product** (Merchant only)
    ```
    PUT /api/products/update
    Request Body: { "id": 1, "name": "Updated Product Name", "price": 150, "description": "Updated Description" }
    Headers: { "Authorization": "Bearer jwt-token" }
    ```

- **Delete Product** (Merchant only)
    ```
    DELETE /api/products/delete/{id}
    Headers: { "Authorization": "Bearer jwt-token" }
    ```

#### Orders
- **Create Order** (Customer only)
    ```
    POST /api/orders/create
    Request Body: { "productId": 1, "quantity": 2 }
    Headers: { "Authorization": "Bearer jwt-token" }
    ```

- **List Orders by Customer** (Customer only)
    ```
    GET /api/orders/by-customer
    Headers: { "Authorization": "Bearer jwt-token" }
    Response: [ { "id": 1, "productId": 1, "quantity": 2, "status": "Pending" }, ... ]
    ```

- **List Orders by Merchant** (Merchant only)
    ```
    GET /api/orders/by-merchant
    Headers: { "Authorization": "Bearer jwt-token" }
    Response: [ { "id": 1, "customerId": 1, "productId": 1, "quantity": 2, "status": "Pending" }, ... ]
    ```

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -m 'Add some YourFeature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Open a pull request

## License
This project is licensed under the MIT License.

## Contact
For any inquiries, please contact [akayasha99@gmail.com](mailto:akayasha99@gmail.com).

