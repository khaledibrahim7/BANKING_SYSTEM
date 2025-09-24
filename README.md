#  Bank Management System (Java SE Project)

# Overview
This project is a **Java SE application** that simulates a simple banking system.  
It demonstrates key concepts of **Object-Oriented Programming (OOP)**, **Collections Framework**, **File I/O (Serialization)**, **Multithreading & Synchronization**, and **Database Integration (SQL Server with JDBC)**.

---

# Features
- Create and manage **Customers** and their **Accounts**.
- Perform banking operations:
  - Deposit
  - Withdraw
  - Transfer (thread-safe using `ReentrantLock`)
- Store and retrieve data using:
  - **Serialization** (save/load `.dat` file)
  - **SQL Server Database** (JDBC integration)
- Ensure **thread-safety** using Locks for concurrent transactions.
- Example usage of **Design Patterns**:
  - Singleton (Database Connection)
  - Observer (for notifications – future extension)

---

# Technologies Used
- **Java SE 7+**
- **Collections API** (`LinkedHashMap`, `LinkedHashSet`)
- **Serialization** (`ObjectOutputStream`, `ObjectInputStream`)
- **Multithreading** (`ReentrantLock`, synchronized blocks)
- **JDBC** (SQL Server Database Connection)
- **Exception Handling** (Custom Exceptions)


