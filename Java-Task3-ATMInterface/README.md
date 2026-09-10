# ATM Interface

A console-based ATM Interface developed in **Java** using **Object-Oriented Programming (OOP)** principles. The application simulates essential ATM operations including user authentication, account management, withdrawals, deposits, fund transfers, and transaction history.

## 📌 Project Overview

The ATM Interface is designed to provide a simple and secure simulation of an Automated Teller Machine. Users can authenticate using their User ID and PIN and perform common banking transactions through an interactive console-based menu.

The project demonstrates practical implementation of **Java OOP, encapsulation, ArrayList, input validation, authentication, and modular class design**.

## ✨ Features

* 🔐 **User Authentication**

  * Login using User ID and PIN
  * Maximum of 3 incorrect login attempts
  * Access denied after failed authentication

* 👤 **Account Management**

  * Create a new bank account
  * Generate a unique account number
  * Validate User ID uniqueness
  * Set a secure 4-digit PIN
  * Initial deposit support

* 💰 **Banking Transactions**

  * Check current account balance
  * Withdraw funds
  * Deposit funds
  * Transfer funds to another account
  * Validate sufficient balance before withdrawal and transfer

* 📋 **Transaction History**

  * Records completed transactions
  * Displays withdrawal, deposit, and transfer details
  * Uses `ArrayList` for transaction storage

* 🛡️ **Input Validation**

  * Validates PIN format
  * Prevents invalid or negative transaction amounts
  * Handles invalid account IDs
  * Prevents transfers to the same account

## 🛠️ Technologies Used

* **Java**
* **Object-Oriented Programming (OOP)**
* **ArrayList**
* **Java Collections**
* **Console-based User Interface**
* **IntelliJ IDEA**

## 🏗️ Project Structure

```text
Java-Task3-ATMInterface/
│
├── src/
│   └── atm/
│       ├── Main.java
│       │
│       ├── model/
│       │   ├── Account.java
│       │   ├── Bank.java
│       │   └── Transaction.java
│       │
│       ├── service/
│       │   └── ATM.java
│       │
│       ├── ui/
│       │
│       └── util/
│
└── README.md
```

## 🧩 Core Classes

### `Main`

Handles the application's user interface, menus, authentication flow, account creation, and transaction operations.

### `ATM`

Contains the main ATM business logic including login, withdrawal, deposit, transfer, transaction history, and logout functionality.

### `Account`

Represents a bank account and stores account information such as User ID, account number, PIN, account holder name, and balance.

### `Bank`

Manages multiple accounts and provides account searching and account creation functionality.

### `Transaction`

Represents individual banking transactions and stores transaction type, amount, and description.

## 🔄 Application Flow

```text
Start Application
       ↓
Welcome Menu
       ↓
Login / Create Account
       ↓
PIN Authentication
       ↓
ATM Dashboard
       ↓
Choose Transaction
       ↓
┌───────────────┬──────────────┬──────────────┐
│   Withdraw    │   Deposit    │   Transfer   │
└───────────────┴──────────────┴──────────────┘
       ↓
Transaction History
       ↓
Logout / Exit
```

## 🔑 Sample Login Credentials

For testing purposes, the application includes demo accounts.

| User ID | PIN    | Initial Balance |
| ------- | ------ | --------------: |
| `user1` | `1234` |      PKR 50,000 |
| `user2` | `5678` |      PKR 30,000 |
| `user3` | `1111` |      PKR 20,000 |

Users can also create a new account directly from the application.

## ▶️ How to Run

1. Clone or download the repository.
2. Open the project in **IntelliJ IDEA**.
3. Configure a compatible Java JDK.
4. Open `Main.java`.
5. Run the `Main` class.
6. Select an option from the welcome menu.
7. Login or create a new account.
8. Perform ATM transactions through the dashboard.

## 🎯 Learning Outcomes

This project demonstrates practical understanding of:

* Object-Oriented Programming
* Classes and Objects
* Encapsulation
* Constructors and Methods
* ArrayList
* Conditional Statements
* Switch-Case
* Loops
* Input Validation
* Authentication Logic
* Basic Banking Transaction Logic
* Modular Java Application Design

## 👩‍💻 Author

**Anchal Wadhwani**

Java Development Internship Project **OASIS INFOBYTE / SIP**

## 📄 Internship Task

**Track:** Java Development
**Task:** Task 3 — ATM Interface

---

⭐ If you find this project useful, feel free to explore the repository and review the implementation.
