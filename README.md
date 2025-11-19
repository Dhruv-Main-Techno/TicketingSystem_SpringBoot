# 🎟️ Ticketing System — Simple Spring Boot + HTML UI

A lightweight ticket reservation system built using **Spring Boot**, **static HTML/JS**, and **in-memory or JPA-based persistence**.
The project supports event creation, section creation, seat listing, holding seats with pessimistic locking, and confirming reservations.


## 🚀 Features

### **Backend (Spring Boot)**

* Create Events
* Create Event Sections
* Auto-generate seats for each section
* Fetch seats by section
* Hold seats (**Pessimistic Locking**)
* Confirm reservations
* Prevent double-booking
* Works with any DB supported by Spring Data JPA
  *(H2 / PostgreSQL / MySQL — based on your config)*

### **Frontend (Simple HTML + Vanilla JS)**

* Event creation form
* Section creation form
* Seat listing UI
* Clickable seat selection grid
* Combined **Hold + Confirm reservation** workflow
* No React / no build tools — pure HTML + JS
* Simple to embed anywhere


## 🏗️ Tech Stack

### **Backend**

* Java **21**
* Spring Boot
* Spring Data JPA
* Hibernate (Pessimistic Locking)
* Lombok

### **Frontend**

* Pure **HTML**
* Vanilla **JavaScript** (Fetch API)
* Basic **CSS**
* No React, no bundlers — lightweight and clean

## 📦 Project Structure

    backend/
     └── src/main/java/com/main/ticketing/
          ├── event/
          │    ├── controller
          │    ├── domain
          │    ├── repository
          │    └── service
          ├── reservation/
          │    ├── controller
          │    ├── domain
          │    ├── repository
          │    └── service
          └── common/dto

    frontend/
     └── src/
          ├── components/
          │    ├── EventForm.jsx
          │    ├── SectionForm.jsx
          │    ├── SeatGrid.jsx
          │    └── ReservationPanel.jsx
          ├── pages/
          │    └── BookingPage.jsx
          └── App.jsx

## ⚙️ Setup Instructions

### 🗄️ **Backend Setup**

1.  Create PostgreSQL DB:

    ``` sql
    CREATE DATABASE ticketing;
    ```

2.  Configure `application.properties`:

    ``` properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/ticketing
    spring.datasource.username=postgres
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  Run the backend:

    ``` bash
    mvn spring-boot:run
    ```

4.  The API will be available at:

        http://localhost:8080

### 💻 **Frontend Setup**

1.  Install dependencies:

    ``` bash
    npm install
    ```

2.  Start development server:

    ``` bash
    npm run dev
    ```

3.  App runs at:

        http://localhost:5173

## 🧪 API Endpoints

### **Event API**

  Method   Endpoint                          Description
  -------- --------------------------------- ----------------
  POST     /api/events                       Create event
  POST     /api/events/sections              Create section
  GET      /api/events/sections/{id}/seats   List seats

### **Reservation API**

  ------------------------------------------------------------------------
  Method   Endpoint                                    Description
  -------- ------------------------------------------- -------------------
  POST     /api/reservations/hold                      Hold seats

  POST     /api/reservations/{id}/confirm              Confirm reservation
  ------------------------------------------------------------------------

## 📝 Notes

-   Pessimistic locking prevents double-booking.\
-   Seats automatically get names like `A1`, `A2`, `A3`...\
-   A **405 error** usually means the React frontend is calling the
    wrong HTTP method or URL.
