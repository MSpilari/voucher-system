# Voucher Management System

A simple system to generate and validate vouchers, designed for a small business like a burger shop. This application uses **Spring Boot** for the backend and **Thymeleaf** for server-side rendering. The focus is on simplicity and functionality.

---

## Features

- **Generate Tokens**: A unique voucher token can be generated and displayed in the form of a stylized ticket.
- **Validate Tokens**: Input multiple tokens and validate their authenticity.
- **Simple UI**: Minimalistic frontend using Thymeleaf templates with CSS styling for an intuitive user experience.

---

## Technologies Used

- **Backend**: Spring Boot
- **Frontend**: Thymeleaf
- **Styling**: CSS
- **Build Tool**: Maven
- **Database**: Redis for storing vouchers

---

## Project Structure

```
src
├── main
│   ├── java
│   │   └── dev.mspilari.voucher_api
│   │       ├── controllers      # Controllers handling routes
│   │       ├── dto              # Data Transfer Objects (DTO)
│   │       ├── services         # Business logic
│   │       └── VoucherApiApplication.java # Main class
│   ├── resources
│   │   ├── static               # CSS and other static resources
│   │   │   └── css
│   │   │       └── style.css    # Styles for the web pages
│   │   ├── templates            # Thymeleaf templates (HTML views)
│   │   │   ├── index.html
│   │   │   ├── createToken.html
│   │   │   └── validateTokens.html
│   │   └── application.properties # App configuration
└── test
    └── java                     # Unit and integration tests
```

---

## Installation and Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/MSpilari/voucher-system.git
   cd voucher-system
   ```

2. **Build the Project**
   Make sure you have Maven installed. Run:

   ```bash
   mvn clean install
   ```

3. **Run the Application**
   Start the application by running:

   ```bash
   mvn spring-boot:run
   ```

   The app will be accessible at `http://localhost:8080`.

4. **Access the System**
   - **Home Page**: `http://localhost:8080/`
   - **Generate Tokens**: `http://localhost:8080/vouchers/create`
   - **Validate Tokens**: `http://localhost:8080/vouchers/validate`

---

## How to Use

### Generate Tokens

1. Navigate to the "Generate Tokens" page.
2. Click the "Generate Token" button.
3. A styled ticket will appear with the generated token.

### Validate Tokens

1. Navigate to the "Validate Tokens" page.
2. Enter five tokens in the input fields.
3. Submit the form to verify the tokens.

---

## Future Enhancements

- **Token Storage**: Integrate Redis for token persistence and expiration.
- **Enhanced Validation**: Add detailed feedback for invalid tokens.
- **Authentication**: Restrict token generation to authorized users.
- **Deployment**: Host on a cloud platform (e.g., AWS, Heroku).

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

Enjoy using the **Voucher Management System**! 🎉
