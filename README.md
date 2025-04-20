# 💰 MSBankingSystem

A full-stack, cloud-hosted banking system built with **Java Spring Boot (backend)** and **React + Material UI (frontend)**. The system supports account creation, customer registration/login, deposits, withdrawals, transfers, and account closure.

## 🌐 Live Demo

**Frontend:**

🔗 [https://storage.googleapis.com/jlbank-frontend/index.html](https://storage.googleapis.com/jlbank-frontend/index.html)

**Backend:**

Hosted on Google App Engine Standard

*(Endpoints are accessible via frontend and secured by CORS)*

## 📦 Tech Stack

**Backend**

* Spring Boot REST API
* MySQL (hosted via Google Cloud SQL)
* JPA/Hibernate for ORM
* Deployed to Google App Engine
* CORS configured for cross-origin access

**Frontend**

* React (with Hooks and React Router)
* Material UI for styling
* Deployed to Google Cloud Storage as a static website

## 🚀 Deployment & Hosting

✅ **Frontend Hosting**

Static React app deployed to a GCP Storage bucket
Website settings configured with:

```bash
gsutil web set -m index.html -e 404.html gs://jlbank-frontend
```

✅ **Backend Hosting**

Spring Boot JAR deployed via:

```bash
gcloud app deploy target/backend-java-1.0.0.jar
```

✅ **Database**

Google Cloud SQL (MySQL 8)
Secured with private IP and authentication
Connected via JDBC

## ⚠️ Known Issue: Frontend Routing

When refreshing on a route like `/accounts` or `/register`, the static website initially returns a 404 error because Google Cloud Storage doesn't natively support React Router-style client-side routing.

## 🔧 Fix

The fix is to redirect all unknown routes to `index.html` so React can handle routing on the client side.
Done via:

```bash
gsutil web set -m index.html -e 404.html gs://jlbank-frontend
```

Additionally, this line was added in `App.js` to force unknown paths back to the homepage:

```javascript
<Route path="*" element={<Navigate to="/" />} />
```

This ensures the app still loads, even on hard refreshes or deep links.

## 🔐 Security Notes

Database credentials should not be committed to Git.
Future improvements will include using Google Secret Manager or environment variables for secure configuration.

## 📌 Summary

The MSBankingSystem is now fully deployed and functional. It showcases:

* Full-stack cloud-native deployment
* Real-world account and customer operations
* CI/CD-ready architecture
