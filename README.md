# SmartPay Report Service

The **Report Service** is a Spring Boot microservice responsible for generating real-time spending analytics for SmartPay users.

Instead of storing every transaction again, the service consumes transaction events from Kafka and maintains **pre-aggregated daily summaries**, enabling fast analytical queries without scanning the transaction table.

---

## Architecture

```
                Transaction Service
                        │
          Publish Transaction Event
                        │
                        ▼
                   Kafka Topic
             transaction-created
                        │
                        ▼
                 Report Service
                        │
        Aggregate Daily Spending Data
                        │
                        ▼
                 PostgreSQL Database
                        │
                        ▼
              Analytics REST APIs
```

---

## Features

### Event Processing

- Consumes transaction events from Kafka
- Aggregates daily spending per user
- Updates analytics in PostgreSQL
- Event-driven architecture

### Daily Spending Report

Returns spending summary for a particular day.

```
GET /api/reports/users/{userId}/daily?date=YYYY-MM-DD
```

Example

```
GET /api/reports/users/USER001/daily?date=2026-07-04
```

Response

```json
{
  "userId": "USER001",
  "reportDate": "2026-07-04",
  "totalSpent": 1000.00,
  "transactionCount": 3,
  "averageTransaction": 333.33,
  "highestTransaction": 500.00,
  "lastMerchant": "Flipkart"
}
```

---

### Monthly Spending Analytics

Returns aggregated monthly statistics.

```
GET /api/reports/users/{userId}/monthly?year=2026&month=7
```

Example

```
GET /api/reports/users/USER001/monthly?year=2026&month=7
```

Response

```json
{
  "userId": "USER001",
  "year": 2026,
  "month": 7,
  "daysWithTransactions": 10,
  "totalSpent": 17470.00,
  "totalTransactions": 37,
  "averagePerDay": 1747.00,
  "averageTransaction": 472.16,
  "highestDailySpend": 5100.00,
  "lowestDailySpend": 450.00
}
```

---

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Data JPA
- Apache Kafka
- PostgreSQL
- Maven
- Lombok

---

## Database

The service maintains an aggregated table:

```
user_daily_summary
```

Fields include:

- userId
- reportDate
- totalSpent
- transactionCount
- highestTransaction
- lastMerchant
- lastTransactionTime
- updatedAt

---

## Event Flow

```
Transaction Created

        │

        ▼

Kafka Topic

(transaction-created)

        │

        ▼

Report Service Consumer

        │

        ▼

Update Daily Summary

        │

        ▼

Store Analytics

        │

        ▼

REST API
```

---

## Future Enhancements

- Weekly Spending Analytics
- Merchant Analytics
- PDF Report Generation
- Redis Caching
- Scheduled Report Generation
- Unit & Integration Tests

---

## Run Locally

Clone the repository

```bash
git clone https://github.com/Shekhar3783/smartpay-report-service.git
```

Run Kafka, PostgreSQL and other dependencies.

Start the application

```bash
mvn spring-boot:run
```

The service starts on

```
http://localhost:8082
```

---

## Project Status

Current Version

```
Report Service v1.0
```

Completed

- Kafka Consumer
- Daily Spending Aggregation
- Daily Summary API
- Monthly Spending Analytics API

---

## Author

**Shekhar Molaj**
