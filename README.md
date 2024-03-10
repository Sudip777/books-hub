# BooksHub: Your Books, Your Hub

Welcome to BooksHub, your ultimate destination for managing your book collection effortlessly! With BooksHub, you can easily add, edit, and delete your favorite books, ensuring your library stays organized and up-to-date.

Join BooksHub today and embark on your personalized book journey!

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Thymeleaf
- MySQL
- Bootstrap 5
- Junit / Mockito

## Features
- Register new books to your collection.
- View available books.
- Manage your personal book list.
- Edit and delete books from your collection.

## Instructions for Running the Application

1. Clone this repository to your local machine.
2. Navigate to the project directory.
3. Ensure you have Maven and Java installed.
4. Connect to your SQL database:
   - Update the `application.properties` file located in `src/main/resources` with your database credentials. 
   - Ensure that MySQL is installed and running on your system.
   
   Example `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update

   

5. Run the following command to start the application:
   ```bash mvn spring-boot:run ```

 6. Open your web browser and go to [http://localhost:9090](http://localhost:9090) to access BooksHub.

 
Replace `your_database_name`, `your_username`, and `your_password` with your actual MySQL database details. This will ensure the Spring Boot application can connect to your MySQL database properly.


## Project Structure
- **com.bookHub.controller**: Contains controllers for managing book-related operations.
- **com.bookHub.entity**: Defines entity classes for books and book lists.
- **com.bookHub.service**: Provides services for interacting with book data.
- **com.bookHub.repository**: Holds repository interfaces for accessing data from the database using Spring Data JPA.

- **com.bookHub.service.security**: Contains security-related services for managing user authentication and authorization.

## Usage
- **Home**: Visit the home page for an overview of BookHub.
- **Available Books**: View a list of available books.
- **My Books**: Access your personal book list.
- **Register new Book**: Add a new book to your collection.

## Footer
BookHub &copy; 2024

Enjoy managing your book collection with BooksHub!


