# 🚗 Smart Parking Management System (SPMS)

A cloud-native, microservice-based system that enables real-time parking space discovery, reservation, vehicle tracking, and digital payments. Built with **Spring Boot**, using **Eureka**, **Spring Cloud Gateway**, and **MySQL**.

---

## 📚 Project Structure

| Microservice       | Description                                               |
|--------------------|-----------------------------------------------------------|
| `user-service`     | User and owner registration, login, and profile management |
| `vehicle-service`  | Register vehicles, simulate entry and exit               |
| `parking-service`  | Manage parking spots: add, reserve, release, track status |
| `payment-service`  | Mock payment handling and digital receipt generation     |
| `api-gateway`      | Central API gateway to route all requests                |
| `eureka-server`    | Service discovery registry for all microservices         |
| `config-server`    | Centralized external configuration for services          |

---

## 📦 Technologies Used

- **Spring Boot**
- **Spring Cloud Eureka**
- **Spring Cloud Gateway**
- **Spring Cloud Config**
- **MySQL**
- **Postman** (for API testing)
- **Lombok**
- **Docker (optional)**

---

## 📌 Features

- 🔎 Search and reserve parking in real-time
- 👥 Separate services for users, vehicles, parking, and payments
- 📦 Microservice architecture with Eureka service discovery
- 💳 Simulate digital transactions and generate receipts
- 🚗 Vehicle entry/exit simulation
- 📊 Track parking usage per city/zone/owner
- 📜 Historical logs for users and administrators

---

## 🧪 API Testing

All endpoints were tested using Postman.  
You can find the full collection below:

- 📥 [Postman Collection](./postmam%20collection/SMPS.postman_collection.json)

---

## 📷 Eureka Dashboard Screenshot

Shows all services registered via Eureka Server:

- ![Eureka Dashboard](./docs/Screenshot%202025-06-24%20095900.png)

---

## 🔌 How to Run

1. Ensure MySQL is running and all DBs are created:
    - `user_db`, `vehicle_db`, `parking_db`, `payment_db`

2. Start the services in the following order:
    - `config-server`
    - `eureka-server`
    - `api-gateway`
    - `user-service`
    - `vehicle-service`
    - `parking-service`
    - `payment-service`

3. Import Postman collection into Postman and test all endpoints.
