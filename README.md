🛒 Grocery Inventory Tracker (Java + JDBC + MySQL)

A beginner-friendly project built using Java Collections, JDBC, and MySQL to manage grocery store inventory efficiently.
It allows you to add, update, delete, view, and search grocery items both locally (via Collections) and in a connected MySQL database.

💡 Features

➕ Add new grocery items

🔄 Update item quantity and price

🗑️ Delete items

👀 View all items from MySQL

🔍 Search for a specific item

💰 Calculate total inventory value

🧾 Sort items by name or price

⚙️ Technologies Used

Java (Collections Framework)

JDBC (Java Database Connectivity)

MySQL Database

VS Code / IntelliJ / Eclipse

🗂️ Database Setup

Open MySQL and create the database:

CREATE DATABASE grocery_db;
USE grocery_db;
CREATE TABLE grocery_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    quantity INT,
    price DOUBLE
);


Update your DBConnection.java file if needed:

String url = "jdbc:mysql://localhost:3306/grocery_db";
String user = "root";
String password = "root123";

▶️ Run the Project

In your VS Code terminal, use:

javac -cp ".;lib/mysql-connector-j-9.5.0.jar" *.java
java -cp ".;lib/mysql-connector-j-9.5.0.jar" Main

📦 Folder Structure
GroceryInventoryProject/
│
├── Main.java
├── InventoryManager.java
├── GroceryItem.java
├── DBConnection.java
├── lib/
│   └── mysql-connector-j-9.5.0.jar
└── README.md

💬 About

This project was created as a simple Java-based database project for beginners who want to learn how to integrate MySQL with Java using JDBC.
