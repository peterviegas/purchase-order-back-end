## purchase-order-back-end
Purchase Order - Creation of objects and tables using Spring Boot 2.x technologies to develop a REST API hosting.

### 📚 Core Concepts:

- **Spring Boot 2.x.x**
- **Spring Data JPA**
- **Hibernate**
- Build a **REST API**.
- Use **Postman** to test the REST API.
- Work with **H2 database** during development.
- Handle exceptions with custom error handling.
- Follow the layered architecture using the **MVC pattern**.
- **DTO pattern** (Data Transfer Objects).
- Integrate **MySQL** with a Spring Boot project.
- Implement **Test** and **Development** profiles.
- Apply validations with the **Validation API**.
- Follow the **RESTful** HTTP protocol standard.

---

### 🛠 Functional Overview

In this project, you will learn to build a complete web application, implementing a CRUD (Create, Read, Update, and Delete) system for a **Purchase Order Management System**.

---

### 🔧 Technical Overview - Backend

The backend is built using **Java** and **Spring Boot 2.x.x**. It includes:
- Building a **REST API** with **MySQL** database integration.
- Using **JPA** with **Hibernate** for ORM and data validation.
- Implementing custom **exception handling**.
- Following proper **RESTful** HTTP protocols.
  
**Version:** Spring Boot 2.7.x

---

#### 📄 Table of Contents
- [What You Will Learn](#📚-what-you-will-learn)
- [Functional Overview](#🛠-functional-overview)
- [Technical Overview - Backend](#🔧-technical-overview---backend)
- [Installation](#installation)
- [Test](#testing)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

---

### 🚀 Installation

To set up the project locally, download the necessary tools:
- [Spring Tools Suite](https://spring.io/tools)
- [Java JDK 11 LTS](https://www.oracle.com/java/technologies/javase-downloads.html)
- [VSCode](https://code.visualstudio.com/download)
- [Heroku Account](https://www.heroku.com/) I used it before, but I no longer do because Heroku changed their free plans.
- [Postman](https://www.postman.com/downloads/)

#### Usage
1. Clone the repository.
2. Import the project into your workspace using Spring Boot.
3. Set up the **MySQL** database and configure it in the application properties.
4. Start the backend server.
5. [Download the Postman collection here](postman/Purchase.postman_collection.json)
6. Run the **Angular** frontend application, which is built to consume the REST API efficiently. https://github.com/peterviegas/purchase-order-front-end

---

### 🧪 Test Coverage

For the tests, the parameters are passed to the API via the **application.properties** file. The tests were created to cover the main functionalities of the project, such as:

- Creating tables in the in-memory **H2** database.
- Executing the following features:
  - Creating two sellers.
  - Updating one seller.
  - Creating two customers.
  - Updating one customer.
  - Creating two **Purchase Orders (PO)**.
  - Deleting one seller.
  - Deleting one customer.

### ℹ️ Additional Information

#### POST - CPF Validation
- Validates the CPF according to Brazilian rules.
- Checks if the CPF already exists in the database to avoid duplicating a customer.

#### PUT - CPF Validation
- Performs validation in case the document being updated is the same as in the POST. If attempting to change to an already existing CPF, it informs that the CPF is already registered.

#### DELETE - Customer Deletion
- Checks if the customer has any **Purchase Orders (PO)**. If they do, deletion is not allowed.

### 📄 PO (Purchase Order)
#### Additional Information:
- **POST**: Uses an **enum** type for priority and status.
- Seller and customer are linked by their IDs as the tables are related.

#### PUT - Update PO
- Example of passing only the body. In the case of the customer, it was being mentioned as a parameter in the URL, but here the ID is inside the body.

### 🛒 Sellers

#### PUT - CPF Validation
- Validates the CPF according to Brazilian rules.
- Checks if the CPF already exists in the database to avoid duplicating a seller.

#### POST - Seller Update
- Receives the ID as a parameter and the body contains the information to be updated.
- If an invalid or already registered CPF is provided, the system informs that the CPF cannot be changed.

#### DELETE - Seller Deletion
- Checks if the seller has any **Purchase Orders (PO)** linked to their ID. If they do, deletion is not allowed.

---

### 🤝 Contributing

Contributions are welcome! Feel free to fork the repository, make changes, and submit a pull request.

#### How You Can Help:
- **Report Bugs:** If you find a bug, please report it using the issues page.
- **Suggest Features:** Have a feature request? Open an issue and let us know.
- **Pull Requests:** If you'd like to contribute code, submit a pull request with your changes.

For more information, check out the **Contribution Guidelines**.

---

### 📜 License
This project is not licensed and is in the public domain. Feel free to use it as you like.

---

### 🙏 Acknowledgments

A special thank you to **Vandir Cezar** for his excellent course on Udemy, which was instrumental in the development of this project.
