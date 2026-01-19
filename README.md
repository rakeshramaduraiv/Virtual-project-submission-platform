# Virtual Science Fair Project Submission Platform

A full-stack web application that allows students to submit science fair projects online and enables evaluators/admins to review, manage, and evaluate submissions efficiently.

---

## 📌 Project Overview

The **Virtual Science Fair Project Submission Platform** is designed to digitize the traditional science fair process.
It provides a centralized platform where:

* Students can register and submit their projects online
* Admins can manage users and submissions
* Evaluators can review, score, and provide feedback
* All data is stored securely and managed efficiently

This system reduces manual work, improves accessibility, and ensures a transparent evaluation process.

---

## 🏗️ System Architecture

* **Frontend:** React.js
* **Backend:** Spring Boot (Java)
* **Database:** MySQL (or your actual DB)
* **API Communication:** RESTful APIs

```
[ React Frontend ]  →  [ Spring Boot Backend ]  →  [ Database ]
```

---

## ✨ Features

### 👩‍🎓 Student Module

* User registration and login
* Project submission with details and files
* View submission status and feedback

### 🧑‍💼 Admin Module

* Manage students and evaluators
* View and manage all submissions
* Assign evaluators to projects

### 🧑‍🏫 Evaluator Module

* View assigned projects
* Evaluate and score projects
* Provide feedback

### 🔐 Security

* Authentication and authorization
* Role-based access control

---

## 🛠️ Technologies Used

### Frontend

* React.js
* HTML5, CSS3, JavaScript
* Axios for API calls

### Backend

* Spring Boot
* Spring Data JPA
* Spring Security

### Database

* MySQL

### Tools

* Git & GitHub
* Maven
* Postman

---

## 📂 Project Structure

```
safesrs/
│── frontend/        # React application
│── backend/         # Spring Boot application
│── .gitignore
│── README.md
│── run-app.bat
```

---

## 🚀 How to Run the Project

### 1️⃣ Run Backend (Spring Boot)

Go to backend folder:

```bash
cd backend
mvn spring-boot:run
```

Backend runs at:

```
http://localhost:8080
```

---

### 2️⃣ Run Frontend (React)

Go to frontend folder:

```bash
cd frontend
npm install
npm start
```

Frontend runs at:

```
http://localhost:3000
```

---

## 🔗 API Endpoints (Sample)

* `POST /api/auth/register` – Register user
* `POST /api/auth/login` – Login
* `POST /api/projects/submit` – Submit project
* `GET /api/projects` – View all projects
* `POST /api/evaluate` – Submit evaluation

---

## 🧪 Testing

* Unit testing with JUnit (backend)
* API testing using Postman
* Manual UI testing on frontend

---

## 📈 Future Enhancements

* File upload with cloud storage
* Email notifications
* Analytics dashboard
* Mobile app integration
* Multi-language support

---

## 👨‍💻 Developed By

**Rakesh R**
Final Year Project – Virtual Science Fair Project Submission Platform

---

## 📜 License

This project is developed for academic purposes and can be extended for commercial use with proper licensing.
