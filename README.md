# JSF-PrimeFaces User Management Application

This is a web application built using **JSF (Jakarta Server Faces)** and **PrimeFaces** for the user interface. The application provides user authentication, user management, and data validation features. It is designed to manage user information, including personal details, addresses, and account balances.

---

## Features

### 1. **User Authentication**
- Users can log in with their credentials.
- Role-based redirection:
  - **Admin**: Redirected to the user management page.
  - **User**: Redirected to a page displaying their detailed information.
  - **Invalid Role**: Redirected to an error page.

### 2. **User Management**
- A **DataTable** displays the list of users.
- Admins can **edit** or **delete** users.
- A button **"Add User"** redirects to a page for adding new users.

### 3. **Add User Page**
- Users can input the following details:
  - **First Name**
  - **Last Name**
  - **Date of Birth** (format: XX/XX/XXXX)
  - **Account Balance** (in MAD)
  - **Address** (format: `Number - Street Name`)
  - **City and Postal Code** (postal code starts with a digit)
  - **Country**

### 4. **Custom Converters**
- **Date of Birth**: Converts to the format XX/XX/XXXX.
- **Account Balance**: Displays with the **MAD (Dirham Marocain)** unit.
- **Address**: Extracts and formats the street number, street name, city, and postal code.

### 5. **Data Validation**
- Validators ensure:
  - Correct date format for the date of birth.
  - Valid account balance (numeric and positive).
  - Properly structured address fields.

---

## Technologies Used
- **JSF (Jakarta Server Faces)**: For building the web interface.
- **PrimeFaces**: For rich UI components.
- **Java EE / Jakarta EE**: For backend logic and user management.
- **JPA / Hibernate**: For database access and ORM.
- **Maven**: For dependency management.

---

## Prerequisites
- **Java Development Kit (JDK) 11 or higher**
- **Apache Maven 3.6 or higher**
- **Application Server** (e.g., Apache Tomcat, WildFly, or Payara)
- **Database** (e.g., MySQL, PostgreSQL, or H2)

---
