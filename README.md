# HEALTH_CARE_MANAGEMENT
The healthcare Management System is a web-based application designed to allow users to manage their healthcare activities online. Developed as a university project, this system utilizes Java Spring Boot for backend services, HTML, CSS, and JavaScript for frontend development, and JDBC with MySQL for database operations. This System offers separate functionalities for Admin, Doctor, and Patient users.




### USAGE AND FUNCTIONALITIES

## User Functionalities

- **Register**: New users (patients) can create accounts.
- **Login**: Access account details and manage profile information.
- **Book Appointment**: Patients can book appointments with doctors for specific dates and times.
- **View Medical History**: Patients can view their previous consultations and medical records.

## Doctor Functionalities

- **Schedule Management**: Doctors can manage their availability and appointments.
- **Patient Records**: View and update medical records of patients.
- **Appointment Management**: Confirm and manage appointments with patients.

## Admin Functionalities

- **User Management**: Admin can manage user accounts for patients and doctors, including adding, editing, and deleting accounts.
- **Appointment Oversight**: Admin can view and manage all system appointments.
- **System Settings**: Configure system settings as needed, including roles and permissions.
- **Security Features**: Data protection via encryption and access control.

## Technology Stack

- **Backend**: Java, Spring Boot, JDBC
- **Frontend**: HTML, CSS, JavaScript
- **Database**: MySQL
- **Tools & Libraries**: 
  - Spring Security (for authentication and authorization)
  - JDBC (for database connectivity and operations)

## Prerequisites

To set up this project locally, you’ll need:

- **Java Development Kit (JDK)**: Version 11 or later
- **Maven**: For dependency management
- **MySQL**: Database management system
- **IDE**: Recommended IntelliJ IDEA or Eclipse
- **Git**: For version control

## Installation

### 1. Clone the Repository

```bash
git clone https://github.com/Parthamesh06/HEALTH_CARE_MANAGEMENT.git
cd HEALTH_CARE_MANAGEMENT
```

### 2. Configure the Database 

Create a new database in MySQL, then update your `application.properties` file to configure the database connection.

```properties
# application.properties

spring.application.name=HealthCare
spring.datasource.url=jdbc:mysql://localhost:3306/healthcare
spring.datasource.username=root
spring.datasource.password=Pratham@8969
spring.jpa.hibernate.ddl-auto=update
spring.resources.static-locations=classpath:/static/
spring.security.content-security-policy=default-src 'self'; style-src 'self' 'unsafe-inline';
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# Enable cache for Thymeleaf (optional)
spring.thymeleaf.cache=true

server.port=8080

```

### 3. Build the Project

Use Maven to install dependencies and build the project:

```bash
mvn clean install
```

### 4. Run the Application

Run the application using Maven:

```bash
mvn spring-boot:run
```

### 5. Access the Application

Open your browser and go to `http://localhost:8080` to access the Online Banking Management System.

## Project Structure

```
Directory structure:
└── parthamesh06-healthcare_final/
    ├── README.md
    └── HealthCare/
        ├── mvnw
        ├── mvnw.cmd
        ├── pom.xml
        ├── .gitattributes
        ├── .gitignore
        ├── src/
        │   ├── main/
        │   │   ├── java/
        │   │   │   └── com/
        │   │   │       └── HealthCare/
        │   │   │           ├── HealthCareApplication.java
        │   │   │           ├── controller/
        │   │   │           │   └── AppController.java
        │   │   │           ├── entity/
        │   │   │           │   └── Appointment.java
        │   │   │           └── repository/
        │   │   │               └── AppointmentRepository.java
        │   │   └── resources/
        │   │       ├── application.properties
        │   │       ├── static/
        │   │       │   ├── styles.css
        │   │       │   ├── styles2.css
        │   │       │   ├── styles3.css
        │   │       │   ├── styles4.css
        │   │       │   ├── styles5.css
        │   │       │   └── images/
        │   │       │       ├── bg-girl-01.webp
        │   │       │       ├── bg-girl-02.webp
        │   │       │       ├── cardiology-bg.webp
        │   │       │       ├── sec_1.webp
        │   │       │       └── sec_2.webp
        │   │       └── templates/
        │   │           ├── about.html
        │   │           ├── index.html
        │   │           ├── login.html
        │   │           ├── make_an_appointment.html
        │   │           └── submitted.html
        │   └── test/
        │       └── java/
        │           └── com/
        │               └── HealthCare/
        │                   └── HealthCareApplicationTests.java
        └── .mvn/
            └── wrapper/
                └── maven-wrapper.properties


```


## JDBC Implementation Notes

- **Database Connection**: Uses Spring's `JdbcTemplate` to connect to MySQL.
- **SQL Queries**: SQL queries will be used within repository interfaces for CRUD operations. Here’s an example of how to manage appointments with Spring Data JPA.

```
package com.HealthCare.repository;

import com.HealthCare.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

```
- **CONTROLLER**: For Handling routes of the page

```
java
package com.HealthCare.controller;

import com.HealthCare.entity.Appointment;
import com.HealthCare.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppController {

    @Autowired
    private AppointmentRepository repository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/make_an_appointment")
    public String showAppointmentForm() {
        return "make_an_appointment";
    }

    @PostMapping("/make_an_appointment")
    public String handleAppointmentForm(
            @RequestParam String name,
            @RequestParam String location,
            @RequestParam String mobile,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (mobile.length() != 10) {
            model.addAttribute("error_message", "Mobile number must be 10 digits long.");
            model.addAttribute("name", name);
            model.addAttribute("location", location);
            return "make_an_appointment";
        }

        Appointment appointment = new Appointment();
        appointment.setName(name);
        appointment.setLocation(location);
        appointment.setMobile(mobile);
        repository.save(appointment);

        redirectAttributes.addFlashAttribute("success_message", "Your response has been submitted!");
        return "redirect:/submitted";
    }

    @GetMapping("/submitted")
    public String submitted() {
        return "submitted";
    }

    @GetMapping("/thank_you")
    public String thankYou() {
        return "thankyou";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

```


### Frontend: HTML and CSS
Each user type (Admin, Doctor, and Patient) will have separate HTML templates under `src/main/resources/templates/` with corresponding styling in `src/main/resources/static/`.


```
css

src/main/resources/
├── templates/
│   ├── about.html
│   ├── index.html
│   ├── login.html
│   ├── make_an_appointment.html
│   └── submitted.html
└── static/
    ├── css/
    │   └── styles.css
    ├── js/
    │   └── scripts.js
    └── images/
```
    
## APPOINTMENT BOOKING (make_an_appointment.html)

Displays a summary of users, appointments, and system settings.

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" th:href="@{/styles.css}"> <!-- Static files served by Spring Boot -->
    <link rel="shortcut icon" th:href="@{/images/gu-favicon.png}" type="image/x-icon">
    <title>Galgotias Hospital</title>
    <style>
        /* Style for the circular button */
        .circle-button {
            background-color: #007bff;
            color: #fff;
            width: 60px;
            height: 60px;
            border-radius: 50%;
            border: none;
            cursor: pointer;
            position: fixed;
            bottom: 20px;
            right: 20px;
            z-index: 9999;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
            transition: background-color 0.3s ease;
        }

        /* Hover effect */
        .circle-button:hover {
            background-color: #0056b3;
        }

        .navbar {
            position: fixed;
            bottom: calc(20px + 60px + 10px);
            right: calc(20px + 60px + 10px);
            z-index: 9998;
            background-color: #f8f9fa;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 10px;
            display: none;
        }

        .navbar a {
            display: block;
            padding: 5px;
            text-decoration: none;
            color: #333;
            transition: background-color 0.3s ease;
        }

        .navbar a:hover {
            background-color: #ddd;
        }

        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');
        body {
            font-family: 'Roboto';
        }

        .head-nav {
            position: absolute;
            top: 0;
            padding: 3rem;
            z-index: 5;
        }

        .icon-and-button {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 85vw;
        }

        .background {
            position: absolute;
            z-index: -1;
        }

        .background img {
            object-fit: cover;
        }

        .top-section {
            position: relative;
            display: flex;
            align-items: center;
            height: 100vh;
            color: #000;
            background-color: #00a2b888;
        }

        .middle-section {
            min-height: 50vh;
            background-color: #fff;
        }

        .rounded-button {
            width: 60px;
            height: 60px;
            color: #000;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            border-radius: 50%;
            border: #000;
            text-decoration: none;
            transition: background 0.2s;
        }

        .rounded-button:hover {
            background: #F2613F;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10%;
            width: 100%;
        }

        .details > h2 {
            font-size: 1.5rem;
            color: #343a4088;
            font-weight: 700;
        }

        .details > h1 {
            font-weight: 700;
            font-size: 3rem;
            color: whitesmoke;
            width: 70%;
        }

        .form-container {
            background-color: #17a2b8;
            width: 400px;
            z-index: 10;
        }

        .wave-container {
            width: 100%;
            position: absolute;
            bottom: 0;
            left: 0;
        }
    </style>

</head>

<body>
<div>
    <!-- Navbar -->
    <nav class="head-nav">
        <div class="container-fluid icon-and-button">
            <a class="navbar-brand" href="https://www.galgotiasuniversity.edu.in/">
                <img th:src="@{/images/logo-gu.png}" width="300px" alt="">
            </a>
            <button class="btn btn-warning text-white shadow">
                <img th:src="@{/images/phone-volume-solid.svg}" width="20px" style="color:white" alt="">
                &nbsp; +91 8969660365
            </button>

            <!-- Navigation bar -->
            <div class="navbar" id="navbar">
                <nav>
                    <ul>
                        <a href="/">Home</a>
                        <a href="/about">About Us</a> <!-- Add this line -->
                    </ul>
                </nav>
            </div>
        </div>
    </nav>

    <!-- Top Section or Header -->
    <section class="top-section">
        <div class="header">
            <div class="details">
                <h2>The Galgotias World of Care</h2>
                <h1>Search Your Doctor, Make an appointment</h1>
                <p>The best of modern healthcare to ensure you stay healthy, always.</p>
            </div>

            <!-- Appointment Form -->
            <form id="myForm" action="/make_an_appointment" method="POST"
                  class="form-container py-4 px-5 rounded-2 shadow">
                <h2 class="fs-4">Make an appointment</h2>
                <div class="mb-3">
                    <input type="text" name="name" placeholder="Name" class="form-control" required>
                </div>
                <select class="form-select" name="location" aria-label="Location">
                    <option value="Delhi">Delhi</option>
                    <option value="Chandigarh">Chandigarh</option>
                    <option value="Haryana">Haryana</option>
                    <option value="Chattisgarh">Chattisgarh</option>
                    <option value="UttarPradesh" selected>UttarPradesh</option>
                    <option value="Bihar">Bihar</option>
                    <option value="Jharkhand">Jharkhand</option>
                    <option value="WestBengal">WestBengal</option>
                    <option value="HimachalPradesh">HimachalPradesh</option>
                    <option value="Mumbai">Mumbai</option>
                    <option value="Andhrapradesh">Andhrapradesh</option>
                    <option value="MadhyaPradesh">MadhyaPradesh</option>
                    <option value="TamilNadu">TamilNadu</option>
                    <option value="Kerala">Kerala</option>
                    <option value="Odisha">Odisha</option>
                    <!-- Add other locations here -->
                </select>
                <div class="my-3">
                    <input type="number" class="form-control" name="mobile" placeholder="Mobile" required>
                </div>
                <button class="btn btn-warning text-white">Book an appointment</button>
            </form>
        </div>

        <!-- Bootstrap carousel -->
        <div id="carouselExampleSlidesOnly" class="carousel slide background" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img th:src="@{/images/indexFirst.jpg}" class="d-block w-100" alt="">
                </div>
                <div class="carousel-item">
                    <img th:src="@{/images/indexSecond.jpg}" class="d-block w-100" alt="">
                </div>
                <div class="carousel-item">
                    <img th:src="@{/images/bg-girl-02.webp}" class="d-block w-100" alt="">
                </div>
            </div>
        </div>
    </section>

    <!-- Wave Container -->
    <div class="wave-container">
        <svg id="wave" viewBox="0 0 1440 310">
            <path fill="url(#sw-gradient-0)" d="M0,93L40,93C80,93,160,93,240,124..."></path>
        </svg>
    </div>
</div>

<!-- Second Section -->
<section class="middle-section" style="text-align: center;">
    <h1>Centres of Excellence</h1>
    <div class="rounded-button">
        <a class="rounded-button" href="/about">
            <!-- Example icon -->
<!--            <img th:src="@{/images/heart-icon.svg}" alt="heart">-->
        </a>
    </div>
</section>

</body>

</html>

```

## Testing
Unit and integration tests are located in the src/test/ directory to ensure each functionality works correctly.
