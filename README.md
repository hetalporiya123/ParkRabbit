# 🚗 Parking Management System (Event-Driven Architecture)

A production-style **event-driven parking management system** built using **Spring Boot**, **React**, **RabbitMQ**, **PostgreSQL**, and **Docker**.

This project demonstrates how backend services communicate **asynchronously** using a message broker to generate and manage notifications without direct frontend involvement.

---

## 📌 Project Goal

The primary goal of this project is to demonstrate:

> **An event-driven notification management system where backend services communicate via RabbitMQ.**

The system focuses on **backend-driven workflows**, scheduled jobs, and message-based communication, while keeping the frontend lightweight and simple.

---

## 🧠 Core Concepts Demonstrated

- Event-Driven Architecture
- Publish–Subscribe pattern (RabbitMQ)
- Backend-triggered notifications
- Decoupled services
- Scheduler / cron-based events
- Containerized deployment (Docker)

---

## 🏗️ System Architecture (High Level)


---

## 🔧 Technology Stack

### Backend
- Java 17
- Spring Boot
- RabbitMQ
- PostgreSQL
- Spring Scheduler

### Frontend
- React (Vite)
- React Router
- Simple in-app notifications (toasts + list)

### Infrastructure
- Docker
- Docker Compose
- GitLab (mono-repo)

---

## ✂️ Scope (Intentionally Minimal)

To keep the focus on **RabbitMQ and notifications**, the project intentionally removes non-essential features.

### ✅ Included
- Single parking lot
- Automatic parking slot assignment
- Reservation creation
- Reservation expiry via scheduler
- Event publishing to RabbitMQ
- Notification service consuming events
- In-app notification display
- Fully containerized setup

### ❌ Excluded
- Authentication / login
- Multiple parking lots
- Maps / geolocation
- Payments & invoices
- Admin dashboard
- Browser push notifications

---

## 🔄 Application Workflow

### 1. Reservation Creation
- User clicks **“Reserve Parking”**
- Parking Service:
  - Creates reservation
  - Publishes `ReservationCreated` event
- Notification Service:
  - Consumes event
  - Stores notification

### 2. Reservation Expiry (Backend-Triggered)
- Scheduler runs periodically
- Expired reservations detected
- Parking Service publishes `ReservationExpired` event
- Notification Service stores expiry notification

### 3. Notification Display
- Frontend polls Notification Service
- New notifications shown as:
  - Toast alerts
  - Notification list

Frontend never triggers notification logic.

---

## 📦 Repository Structure (Mono-Repo)


---

## 🐳 Running the Project (Docker)

### Prerequisites
- Docker
- Docker Compose

### Start the system
```bash
docker-compose up --build


