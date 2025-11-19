# 🎟️ Ticketing System -- Backend + Frontend

A full-stack ticket reservation system built with **Spring Boot**,
**React**, and **PostgreSQL**.\
It supports event creation, section management, seat listing, holding
seats, confirming reservations, and real-time UI updates.

## 🚀 Features

### **Backend (Spring Boot)**

-   Create Events\
-   Create Event Sections\
-   Auto-generate Seats for each Section\
-   Fetch seats by section\
-   Hold seats (Pessimistic Locking)\
-   Confirm reservations\
-   Prevent double-booking\
-   PostgreSQL persistence

### **Frontend (React)**

-   Event form\
-   Section creation\
-   Fetch and show seats\
-   Seat selection\
-   Hold seats\
-   Confirm reservation\
-   Fully dynamic UI

## 🏗️ Tech Stack

### **Backend**

-   Java 17\
-   Spring Boot\
-   Spring Data JPA\
-   PostgreSQL\
-   Lombok

### **Frontend**

-   React + Vite\
-   Axios\
-   Tailwind (optional)

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
