#  Spring Security Basic Learning

**Name:** Soyed Md. Solaman Fajul  
**Date:** 27 April, 2025

---

##  Why Use Spring Security?

- Protect applications from unauthorized access.
- Handle login, logout, and role-based access.
- Defend against attacks like CSRF, Session hijacking.

---

##  Key Concepts of Spring Security

| Term | Meaning | বাংলা ব্যাখ্যা |
|:-----|:--------|:--------------|
| Authentication | Verify who you are (Login) | তুমি কে সেটা চেক করা |
| Authorization | Verify what you can do (Access Control) | তুমি কি করতে পারবে সেটা চেক করা |
| Security Filter Chain | Series of security filters on each request | প্রতিটা রিকুয়েস্টে চেক করার ফিল্টার লাইন |
| AuthenticationManager | Authenticates credentials | ইউজার নেম, পাসওয়ার্ড যাচাই করে |
| SecurityContext | Stores authenticated user information | লগ-ইন ইউজারের তথ্য ধরে রাখে |
| PasswordEncoder | Encrypts and checks passwords | পাসওয়ার্ড এনক্রিপ্ট ও যাচাই করে |

---

##  What Happens Internally?

1. User sends a request to API.
2. SecurityFilterChain intercepts the request.
3. AuthenticationManager checks credentials.
4. SecurityContext stores user info after login.
5. Authorization checks if user has permission.

---

## Let's Start a Mini Project: Try to make a project**



---

