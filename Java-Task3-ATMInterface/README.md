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
├── screenshots/
│   ├── login.png
│   ├── atm-menu.png
│   ├── withdrawal.png
│   ├── deposit.png
│   └── transaction-history.png
│
└── README.md
```

## 📸 Screenshots

### Login Screen
<img width="960" height="440" alt="T3" src="https://github.com/user-attachments/assets/c55bfe8c-28be-4dfc-98f0-028e81edf20e" />


### ATM Menu

<img width="957" height="516" alt="login success T4" src="https://github.com/user-attachments/assets/98b9e119-66f5-440f-b983-17056b2e5ff2" />


### Withdrawal
<img width="586" height="450" alt="WithdrawT4" src="https://github.com/user-attachments/assets/c12a68eb-a950-46db-9d01-b27338b6ba21" />



### Deposit

!<img width="723" height="485" alt="deposit T3" src="https://github.com/user-attachments/assets/397f0848-f177-41d1-abf7-2ea87345b37a" />


### Transaction History

<img width="857" height="520" alt="Transaction t3" src="https://github.com/user-attachments/assets/187f5f72-4787-4a39-ad52-e2b00367c50b" />


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
| ------- | ------ | --------------- |
| `user1` | `1234` | PKR 50,000      |
| `user2` | `5678` | PKR 30,000      |
| `user3` | `1111` | PKR 20,000      |

Users can also create a new account directly from the application.

## ▶️ How to Run

1. Clone or download the repository.
2. Open the project in **IntelliJ IDEA**.
3. Configure a compatible Java JDK.
4. Open `Main.java`.
5. Run the `Main` class.
6. Select an option from the welcome menu.
7. Login using the provided credentials or create a new account.
8. Perform ATM transactions through the dashboard.
9. View the transaction history and logout or exit the application.

## 💳 Main ATM Operations

### Check Balance

Users can check their current account balance through the ATM menu.

### Withdraw

Users can withdraw money from their account after successful authentication. The system checks whether sufficient balance is available.

### Deposit

Users can deposit money into their account. The balance is updated after a successful transaction.

### Transfer

Users can transfer funds to another account. The system validates the destination account and available balance.

### Transaction History

The system maintains a record of completed transactions and displays the transaction history to the user.

### Logout

Users can securely logout from their current ATM session.

## 🛡️ Security and Validation

The application includes basic validation mechanisms to improve reliability and security:

* User ID authentication
* 4-digit PIN validation
* Maximum login attempts
* Balance verification
* Transaction amount validation
* Account ID validation
* Prevention of transfers to the same account

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

## 🎯 Internship Task

**Track:** Java Development

**Task:** Task 3 — ATM Interface

**Organization:** OASIS INFOBYTE / SIP

## 👩‍💻 Author

**Anchal Wadhwani**

BSCS Student | Java Development

---

⭐ If you find this project useful, feel free to explore the repository and review the implementation.
