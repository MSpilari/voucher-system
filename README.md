# 🇺🇸(EN-US) Voucher Management System

A simple system to generate and validate vouchers, designed for a small business like a burger shop. This application uses **Spring Boot** for the backend and **Thymeleaf** for server-side rendering. The focus is on simplicity and functionality.

---

## Features

- **Generate Tokens**: A unique voucher token can be generated and displayed in the form of a stylized ticket.
- **Validate Tokens**: Input multiple tokens and validate their authenticity.
- **Validate token with QR Code**: With your smartphone you can validate the token scanning the Qr Code.
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

### Docker Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/MSpilari/voucher-system.git
   cd voucher-system
   ```

2. **Build the project with docker compose**

   ```bash
   docker compose up -d
   ```

### Manual Installation

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

### Validate Token with Qr Code

1. Scan the token qr code with your smartphone, if its valid a success message will show on the screen, otherwise a fail message will appear.

---

## Future Enhancements

- **Enhanced Validation**: Add detailed feedback for invalid tokens.
- **Authentication**: Restrict token generation to authorized users.
- **Deployment**: Host on a cloud platform (e.g., AWS, Heroku).

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

Enjoy using the **Voucher Management System**! 🎉

---

# 🇧🇷(PT-BR) Sistema de Gerenciamento de Vouchers

Um sistema simples para gerar e validar vouchers, projetado para pequenos negócios, como uma hamburgueria. Este aplicativo utiliza **Spring Boot** para o backend e **Thymeleaf** para renderização no lado do servidor. O foco está na simplicidade e funcionalidade.

---

## Funcionalidades

- **Gerar Tokens**: Um token de voucher único pode ser gerado e exibido no formato de um bilhete estilizado.
- **Validar Tokens**: Insira múltiplos tokens e valide sua autenticidade.
- **Validar Token com Qr Code**: Valide os tokens com seu smartphone, apenas escaneando o qr code deles.
- **Interface Simples**: Frontend minimalista utilizando templates Thymeleaf com estilização em CSS para uma experiência intuitiva.

---

## Tecnologias Utilizadas

- **Backend**: Spring Boot
- **Frontend**: Thymeleaf
- **Estilização**: CSS
- **Ferramenta de Build**: Maven
- **Banco de Dados**: Redis para armazenamento de vouchers

---

## Estrutura do Projeto

```
src
├── main
│   ├── java
│   │   └── dev.mspilari.voucher_api
│   │       ├── controllers      # Controladores que gerenciam as rotas
│   │       ├── dto              # Objetos de Transferência de Dados (DTO)
│   │       ├── services         # Lógica de negócio
│   │       └── VoucherApiApplication.java # Classe principal
│   ├── resources
│   │   ├── static               # CSS e outros recursos estáticos
│   │   │   └── css
│   │   │       └── style.css    # Estilos para as páginas web
│   │   ├── templates            # Templates Thymeleaf (views HTML)
│   │   │   ├── index.html
│   │   │   ├── createToken.html
│   │   │   └── validateTokens.html
│   │   └── application.properties # Configurações da aplicação
└── test
    └── java                     # Testes unitários e de integração
```

---

## Instalação e Configuração

### Instalação via Docker

1. **Clone o Repositório**

   ```bash
   git clone https://github.com/MSpilari/voucher-system.git
   cd voucher-system
   ```

2. **Rode o projeto com docker compose**

   ```bash
   docker compose up -d
   ```

### Instalação Manual

1. **Clone o Repositório**

   ```bash
   git clone https://github.com/MSpilari/voucher-system.git
   cd sistema-vouchers
   ```

2. **Compile o Projeto**
   Certifique-se de ter o Maven instalado. Execute:

   ```bash
   mvn clean install
   ```

3. **Execute a Aplicação**
   Inicie a aplicação com o comando:

   ```bash
   mvn spring-boot:run
   ```

   O app estará acessível em `http://localhost:8080`.

4. **Acesse o Sistema**
   - **Página Inicial**: `http://localhost:8080/`
   - **Gerar Tokens**: `http://localhost:8080/vouchers/create`
   - **Validar Tokens**: `http://localhost:8080/vouchers/validate`

---

## Como Utilizar

### Gerar Tokens

1. Navegue até a página "Gerar Tokens".
2. Clique no botão "Gerar Token".
3. Um bilhete estilizado será exibido com o token gerado.

### Validar Tokens

1. Navegue até a página "Validar Tokens".
2. Insira cinco tokens nos campos de entrada.
3. Envie o formulário para verificar os tokens.

### Validar Tokens via Qr code

1. Escaneie o Qr Code do token e confirme se o mesmo é válido ou não.

---

## Melhorias Futuras

- **Validação Aprimorada**: Feedback detalhado para tokens inválidos.
- **Autenticação**: Restringir a geração de tokens a usuários autorizados.
- **Deploy**: Hospedar em uma plataforma na nuvem (e.g., AWS, Heroku).

---

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

Aproveite o **Sistema de Gerenciamento de Vouchers**! 🎉
