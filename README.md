<a href="https://git.io/typing-svg">
  <img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&weight=600&size=50&pause=1000&center=true&vCenter=true&color=FFFF00&width=835&height=70&lines=POS+SYSTEM+BACKEND" alt="pos" />
</a>

# Spring Pos System Backend
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=for-the-badge&logo=apache-tomcat&logoColor=black)

## Project Description
The POS System Backend is a RESTful API that supports the frontend operations of a Point of Sale system. This backend service manages customer, order, and inventory data, providing essential functionalities such as creating, reading, updating, and deleting records, and securing endpoints using authentication.

## Table of Contents
- [Project Description](#project-description)
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features
- Manage customer records
- Process orders and track inventory
- Update item quantities
- Apply discounts and calculate totals
- Secure endpoints with authentication
- Built-in validation for request payloads
- Loggers for application event tracking

## Technologies
- **Backend Framework**: Spring Boot, Spring WebMVC
- **ORM**: Spring Data JPA, Hibernate 6.6.0
- **Database**: MySQL 8.0.33
- **Mapping**: ModelMapper 3.2.1
- **Build Tool**: Maven 4.0.0
- **Application Server**: Apache Tomcat 10
- **JDK**: OpenJDK 17

## Installation
### Prerequisites
- Java 17 (OpenJDK 17)
- Maven
- MySQL 8.0.33
- Apache Tomcat 10

### Steps
1. **Clone the repository - Backend**
    ```bash
   https://github.com/ApsaraWitharana/AAD-Assignment-Spring-Pos-System-Backend.git
    ```
   **Clone the repository - Frontend**
    ```bash
   https://github.com/ApsaraWitharana/AAD-Assignment-Spring-Pos-Frontend.git
    ```

2. **Configure the database**
    - Create a MySQL database named `pos_system`
    - Update the `application.properties` file with your MySQL credentials
   
    ```properties
    
      DriverManagerDataSource dmds = new DriverManagerDataSource();
      dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
      dmds.setUrl("jdbc:mysql://localhost:3306/pos_spring?createDatabaseIfNotExist=true");
      dmds.setUsername("your_username");
      dmds.setPassword("your_password"); 
      return dmds;
    ```

3. **Build the project**
    ```bash
    mvn clean install
    ```

4. **Deploy to Tomcat**
    - Ensure Tomcat is installed and running.
    - Copy the generated WAR file from the `target` directory to the Tomcat `webapps` directory.
    - Restart Tomcat.

## Usage
### Running the Server
After installation, run the server by starting Tomcat. The API will be available at `http://localhost:8080/`.

## Project Structure

### Back-end

The back-end code is organized to follow best practices and maintainability. Important directories and files include:

- `src/main/java`: Contains Java classes.
- `src/main/resources/schema`: Database schema.
- `src/main/resources/logback-spring.xml`: Logger configuration.
- `src/main/webapp/WEB-INF/`: Configuration files for the JavaEE application.

#### Project Packages

(src/main/java/lk.ijse.gdse68/pos_system_back_end)

- **config**: Contains configuration classes, such as security filters and logging filters, responsible for setting up the security mechanisms (e.g., JWT filters) and logging functionality across the application.
- **controller**: Contains classes defining the API endpoints and services.
- **sevice**: Business Objects - classes that encapsulate business logic.
- **dao**: Data Access Objects - classes responsible for database interactions.
- **entity**: Entity classes representing database tables.
- **dto**: Data Transfer Objects - classes used for data exchange between layers.
- **exception**: Contains custom exception classes that handle different types of errors throughout the application..
- **customObject**: Contains custom object classes, which are utility classes or specific objects that don’t necessarily fit into other categories. .
- **util**: Utility classes that provide common functionalities or helper methods used across the application..


## Getting Started

To set up and run the POS system locally, follow these steps:

1. Clone the repository.  
2. Set up the back-end dependencies.  
3. Configure the database connection.  
4. Deploy the JavaEE application on the Apache Tomcat server.

## Dependencies

#### Back-end

- **Spring Boot 3.3.3**: Used for building and deploying the POS system as a RESTful API.
- **Spring Data JPA 3.3.3**: Provides JPA support for database interaction.
- **Hibernate 6.6.0**: Object-relational mapping tool to manage database entities.
- **ModelMapper 3.2.1**: Framework for mapping between object models.
- **Spring WebMVC**: Handles the HTTP requests and responses.

#### Database

- **MySQL Connector**: Java-based driver for connecting to MySQL databases. (Version 8.0.33)
- **JNDI**: Java API for managing database connections efficiently through connection pooling, enhancing performance and scalability.

#### Development Tools

- **Maven 4.0.0**: Build automation and project management tool.
- **Logback**: Logging framework for logging application events.

### Accessing the API
The API will be available at `http://localhost:8080/`.

## API Documentation
For detailed API documentation, please refer to the project’s Swagger UI and Postman collections.

- **Customer API documentation URL**: [Postman Customer API Documentation](https://documenter.getpostman.com/view/35385905/2sAXxTaqKz)
- **Item API documentation URL**: [Postman Item API Documentation](https://documenter.getpostman.com/view/35385905/2sAXxTaqL2)
- **Order API documentation URL**: [Postman Order API Documentation](https://documenter.getpostman.com/view/35385905/2sAXxTaqL3)
- 
## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

<div align="center">

#### This project is licensed under the [MIT License](LICENSE)

#### © 2024 All Right Reserved, Designed By [Sachini Apsara](https://github.com/ApsaraWitharana)

</div>
