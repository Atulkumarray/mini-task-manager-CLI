# Mini Task Management CLI App

A simple command-line Task Manager built using Java.

## Features

- Add tasks with title and priority
- List tasks sorted by priority
- Mark tasks as done
- Delete tasks
- Persistent storage using JSON file

## Technologies Used

- Java 8
- Object-Oriented Programming
- File Handling
- Collections Framework

## Project Structure

```text
src/
└── taskmanager/
    ├── Main.java
    ├── Task.java
    ├── TaskManager.java
    └── FileHandler.java
```

## How to Run

### IntelliJ IDEA
1. Open project folder
2. Run Main.java

### Terminal

Compile:
```bash
javac -d out src/taskmanager/*.java
```

Run:
```bash
java -cp out taskmanager.Main
```

## Storage

Tasks are stored in:
```text
tasks.json
```
