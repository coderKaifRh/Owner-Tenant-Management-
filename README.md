# 🏢 TAK Limited - Tenant & Flat Management Web Application

[![Java](https://img.shields.io/badge/Java-21%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)](https://www.thymeleaf.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0%2B-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

**TAK Limited** is a modern, full-stack web application designed to streamline property rental management, flat listings, tenant search, and relocation services. Built with **Spring Boot 3**, **Spring Data JPA (Hibernate)**, **Thymeleaf**, and **MySQL**.

---

## 🌟 Key Features

### 👤 Role-Based Authentication & Session Management
- **Dual Role System**: Dedicated workflows for **Property Owners** and **Tenants**.
- **Secure Registration & Login**: Phone number validation (+8801XXXXXXXXX) and session tracking.
- **Smart Redirects**: Automatic dashboard routing based on user role.

### 🏡 Property Owner Portal
- **Dashboard**: View all registered properties and current listing statuses (Available / Rented).
- **Listing Creation**: Add new apartments with detailed specifications:
  - Location / Area
  - Monthly Rent
  - Square Footage (Size)
  - Bedroom & Washroom count
- **Multi-Photo Upload**: High-resolution image upload support with automatic local storage and WebMvc static resource serving.
- **Financial Calculation**: Instant 5% owner listing fee calculation.

### 🔍 Tenant Search & Discovery
- **Dynamic Property Search**: Filter available flats simultaneously by:
  - Target Location
  - Maximum Budget (Rent limit)
  - Minimum Size requirement
- **Advance Estimation**: Automatic calculation of standard 2-month security advance based on selected rent.
- **Photo Galleries**: High-resolution image carousel/preview for each listed flat.

### 🚚 Integrated Transport & Relocation Estimator
- **Fare Calculator**: Instant moving service cost calculation based on:
  - Truck Capacity: **Small**, **Medium**, or **Large**
  - Manpower / Helper count (custom labor cost calculation)
- **Hassle-Free Booking**: Dedicated relocation planning page for moving tenants.

### ⚡ Developer & User Experience
- **Auto-Launch on Startup**: Opens `http://localhost:8081/login` in your default browser automatically as soon as Spring Boot is ready.
- **Pre-Configured IDE Settings**: Ready-to-run configuration for IntelliJ IDEA with zero manual configuration.
- **Zero-Install Maven Wrapper**: Ships with `mvnw` and `mvnw.cmd` so you don't need Maven pre-installed.

---

## 🛠️ Technology Stack

| Layer | Technology |
|---|---|
| **Backend Framework** | Spring Boot 3.4.3 |
| **Web MVC & Security** | Spring Web MVC, HTTP Session Management |
| **Persistence / ORM** | Spring Data JPA, Hibernate Core 6.6 |
| **Database** | MySQL Server 8.0+ |
| **Template Engine** | Thymeleaf 3.1 |
| **Frontend Styling** | HTML5, CSS3, Modern Responsive Layouts |
| **Build & Dependency Tool** | Apache Maven 3.9 (Maven Wrapper included) |

---

## 📋 Prerequisites

Before running the application, make sure you have:
1. **Java Development Kit (JDK)**: JDK 21 or higher installed (compatible with JDK 21, 23, 25).
2. **MySQL Server**: MySQL 8.0+ running locally on port `3306`.
3. **Database**: A database named `tak_limited` created in MySQL.

---

## ⚙️ Database Setup

1. Open your MySQL client (MySQL Workbench, Command Line, or phpMyAdmin).
2. Create the database:
   ```sql
   CREATE DATABASE tak_limited;
   ```
3. *(Optional)* Import the provided [`schema.sql`](schema.sql) if you wish to pre-populate tables:
   ```bash
   mysql -u root -p tak_limited < schema.sql
   ```
   > **Note**: Hibernate is configured with `spring.jpa.hibernate.ddl-auto=update`, so tables will also be created automatically upon first run.

4. Verify your credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tak_limited?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=YOUR_MYSQL_PASSWORD
   ```

---

## 🚀 How to Run

### Option 1: IntelliJ IDEA (1-Click Run ▶️)
1. Open IntelliJ IDEA.
2. Select **Open** and choose the `tak-web` folder.
3. Wait for IntelliJ to sync dependencies via Maven.
4. In the top-right toolbar, select **`TakWebApplication`** from the run configurations dropdown.
5. Click the green **Play (▶️)** button or press `Shift + F10`.
6. Once the server starts, **your default web browser will automatically open** to `http://localhost:8081/login`!

---

### Option 2: Terminal / Command Prompt (Using Maven Wrapper)
You do **not** need Maven installed on your machine. Run the wrapper from the project root:

- **Windows (PowerShell / Command Prompt):**
  ```powershell
  .\mvnw.cmd spring-boot:run
  ```

- **Linux / macOS:**
  ```bash
  chmod +x mvnw
  ./mvnw spring-boot:run
  ```

Once running, access the web app at:
👉 **[http://localhost:8081/login](http://localhost:8081/login)**

---

## 🗺️ Application Routes & Endpoints

| Endpoint | Method | Role | Description |
|---|---|---|---|
| `/` | `GET` | Public | Redirects to login page |
| `/login` | `GET` | Public | Displays login view |
| `/login` | `POST` | Public | Authenticates user (Owner/Tenant) and establishes session |
| `/signup` | `GET` | Public | Displays registration view |
| `/signup` | `POST` | Public | Creates new user account |
| `/logout` | `GET` | Authenticated | Clears user session and redirects to login |
| `/owner/dashboard` | `GET` | Owner | Displays owner's registered flats and listing form |
| `/owner/add-flat` | `POST` | Owner | Handles new flat submission and photo upload |
| `/tenant/search` | `GET` | Tenant | Search and filter form for available flats |
| `/tenant/results` | `GET` | Tenant | Filtered flat results and advance calculations |
| `/transport` | `GET` | Tenant / All | Relocation service calculation form |
| `/transport/calculate` | `POST` | Tenant / All | Calculates truck and labor moving cost |
| `/uploads/{fileName}` | `GET` | Public | Serves uploaded flat photos |

---

## 📂 Project Architecture

```text
tak-web/
├── .mvn/wrapper/                  # Maven wrapper binaries & properties
├── .idea/runConfigurations/       # Pre-configured IntelliJ IDEA run targets
├── src/
│   ├── main/
│   │   ├── java/com/tak/web/
│   │   │   ├── TakWebApplication.java    # Application entry point with browser auto-launcher
│   │   │   ├── config/
│   │   │   │   └── WebConfig.java        # Static resource handler for uploads
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java   # Login, signup & logout handling
│   │   │   │   ├── OwnerController.java  # Owner dashboard & flat listings
│   │   │   │   ├── TenantController.java # Flat searching & results
│   │   │   │   └── TransportController.java # Transport fee estimation
│   │   │   ├── model/
│   │   │   │   ├── Flat.java             # Flat JPA entity
│   │   │   │   ├── FlatImage.java        # Image association JPA entity
│   │   │   │   └── User.java             # User account JPA entity
│   │   │   ├── repository/
│   │   │   │   ├── FlatRepository.java   # Spring Data JPA custom queries
│   │   │   │   └── UserRepository.java   # User query methods
│   │   │   └── service/
│   │   │       ├── FlatService.java      # Flat management business logic
│   │   │       ├── TransportService.java # Moving cost calculation logic
│   │   │       └── UserService.java      # Authentication & validation logic
│   │   └── resources/
│   │       ├── application.properties    # Server port, datasource & upload limits
│   │       └── templates/                # Thymeleaf UI views
│   │           ├── login.html
│   │           ├── signup.html
│   │           ├── owner-dashboard.html
│   │           ├── tenant-search.html
│   │           ├── results.html
│   │           └── transport.html
├── uploads/                              # Uploaded flat images directory
├── pom.xml                               # Maven project POM
├── schema.sql                            # Database schema reference
├── mvnw / mvnw.cmd                       # Maven wrapper executables
└── README.md                             # Project documentation
```

---

## 🔧 Configuration Reference (`application.properties`)

Key properties configured in `src/main/resources/application.properties`:

- **Port**: `server.port=8081`
- **Database URL**: `jdbc:mysql://localhost:3306/tak_limited`
- **File Upload Limits**:
  - `spring.servlet.multipart.max-file-size=50MB`
  - `spring.servlet.multipart.max-request-size=100MB`
- **Thymeleaf Cache**: Disabled (`spring.thymeleaf.cache=false`) for live UI updates during development.

---

## 🤝 Contributing

Contributions, suggestions, and pull requests are welcome!
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/NewFeature`)
3. Commit your Changes (`git commit -m 'Add NewFeature'`)
4. Push to the Branch (`git push origin feature/NewFeature`)
5. Open a Pull Request

---

## 📄 License

This project is developed for educational and portfolio purposes. Feel free to use and modify it.
