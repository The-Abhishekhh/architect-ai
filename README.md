# 🚀 ArchitectAI

An AI-powered backend application built with **Java, Spring Boot, Spring Data JPA, MySQL, and Google Gemini** to simulate technical interview evaluation.

ArchitectAI is designed as a production-style backend system rather than a simple CRUD application. The project focuses on clean architecture, separation of concerns, extensibility, validation, dynamic data retrieval, external AI integration, exception handling, and automated testing.

---

## ✨ What ArchitectAI Does

ArchitectAI allows users to submit technical interview questions and answers and receive an AI-generated evaluation.

The application:

- Accepts interview questions and answers
- Evaluates answers through an AI provider
- Stores interview results in MySQL
- Provides searchable interview history
- Supports dynamic filtering and sorting
- Supports pagination
- Validates incoming requests
- Validates AI responses before processing
- Handles application and external-service failures
- Exposes consistent API response structures
- Provides automated unit and controller tests

---

## 🧠 Core Capabilities

### 🤖 AI-Powered Evaluation

ArchitectAI uses an `AiProvider` abstraction to decouple the application from a specific AI provider.

This allows the application to support different AI implementations without changing the business logic.

```text
InterviewService
       ↓
    AiProvider
       ↓
 ┌─────┴─────┐
 ↓           ↓
Gemini    Mock Provider
