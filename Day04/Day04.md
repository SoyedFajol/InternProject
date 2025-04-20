  #  Daily Task - 17 April & 18 April

  ## Soyed Md. Solaman Fajul

##  Task 01: Questions & Answers

### 1. IntelliJ IDEA Fix Problem
- **Common Fixes:**


---

### 2. Layer vs MVC

| Layer Architecture | MVC Architecture |
|--------------------|------------------|
| Divides app into logical layers: Controller, Service, Repository | Separates app into: Model, View, Controller |
| Focuses on business logic separation | Focuses on UI and interaction |
| Common layers: Controller → Service → Repository → DB | Model (Data), View (UI), Controller (Logic) |

---

### 3. `Options`, `List`, `Map`

- **`Optional<T>`** – A container that may or may not contain a value (used to avoid `null`).
- **`List<T>`** – An ordered collection (e.g., `ArrayList`, `LinkedList`).
- **`Map<K, V>`** – Key-value pair collection (e.g., `HashMap`, `TreeMap`).

---

### 4. Dependency

- **Dependency** means one class depends on another to perform its function.
- In Spring, we manage dependencies using:
  - **Constructor Injection**
  - **Setter Injection**
  - **Field Injection (@Autowired)**

---

### 5.  Maven vs Gradle



---

### 6. 🔗 `@Autowired`

- Injects Spring-managed beans automatically.
- Works on fields, setters, or constructors.
```java
@Autowired


##  Task 02: Getting Started with Spring – Overview

###  What is Spring?

Spring is a powerful, lightweight framework for building Java-based enterprise applications. It simplifies the development process through several key features:

### Why Use Spring Boot?

Spring Boot makes it easy to create stand-alone, production-grade Spring applications. Key advantages include:

-  No need for XML configurations
-  Embedded web server (Tomcat/Jetty)
-  Auto-configuration of Spring features
-  Minimal setup for REST API or database apps