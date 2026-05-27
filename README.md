# 📝 Mini Task Manager CLI

<p align="center">
  <img src="https://img.shields.io/badge/Java-8-orange?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/CLI-Application-blue?style=for-the-badge&logo=windowsterminal&logoColor=white"/>
  <img src="https://img.shields.io/badge/Storage-JSON-green?style=for-the-badge&logo=json&logoColor=white"/>
  <img src="https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge"/>
</p>

<p align="center">
  A clean, lightweight <strong>Command-Line Task Manager</strong> built in <strong>Java 8</strong>.<br/>
  Add, list, complete, and delete tasks — all saved automatically to a <code>tasks.json</code> file.
</p>

---

## ✨ Features

| Feature | Description |
|---|---|
| ➕ **Add Task** | Create a task with a title and priority (HIGH / MEDIUM / LOW) |
| 📋 **List Tasks** | View all tasks sorted by priority — HIGH first |
| ✅ **Mark as Done** | Update any task's status to completed |
| 🗑️ **Delete Task** | Remove a task permanently by its ID |
| 💾 **Auto Save** | Every change is instantly saved to `tasks.json` |

---

## 🖥️ Preview

```
  +==========================================+
  |      TASK MANAGER CLI - Java Edition    |
  |          Built by  Atul Kumar           |
  +==========================================+
  Tasks are auto-saved to --> tasks.json

  +------------------------------------+
  |            MAIN  MENU             |
  +------------------------------------+
  |   1.  Add a Task                  |
  |   2.  List All Tasks              |
  |   3.  Mark Task as Done           |
  |   4.  Delete a Task               |
  |   5.  Exit                        |
  +------------------------------------+
  Enter choice (1-5):
```

```
  ======================================================================
  ID   | TITLE                               | PRIORITY | STATUS
  ======================================================================
  1    | Complete the assignment             | HIGH     | [PENDING]
  2    | Buy groceries                       | MEDIUM   | [DONE]
  3    | Read a book                         | LOW      | [PENDING]
  ======================================================================
  Total: 3 task(s)   |   Pending: 2   |   Done: 1
  ======================================================================
```

---

## 🗂️ Project Structure

```
mini-task-manager-CLI/
│
└── src/
    └── taskmanager/
        ├── Main.java           ← CLI menu & user input
        ├── Task.java           ← Task data model
        ├── TaskManager.java    ← Core business logic
        └── FileHandler.java    ← JSON read & write
```

---

## ⚙️ Technologies Used

- **Java 8** — Core language
- **OOP** — Clean class-based design (Single Responsibility)
- **Collections Framework** — List, Comparator for sorting
- **File I/O** — Manual JSON parsing, no external libraries

---

## 🚀 How to Run

### ▶️ Option 1 — IntelliJ IDEA

1. Clone or download this repository
2. Open the project folder in **IntelliJ IDEA**
3. Right-click `src` → **Mark Directory As → Sources Root**
4. Open `Main.java` → click the **▶ Run** button
5. The CLI starts in the terminal panel at the bottom

### ▶️ Option 2 — Command Line / Terminal

**Step 1 — Compile:**
```bash
javac -d out src/taskmanager/*.java
```

**Step 2 — Run:**
```bash
java -cp out taskmanager.Main
```

---

## 💾 Data Storage

Tasks are automatically saved and loaded from:

```
tasks.json  (created automatically on first run)
```

**Sample `tasks.json`:**
```json
[
  {
    "id": 1,
    "title": "Complete the assignment",
    "priority": "HIGH",
    "done": false
  },
  {
    "id": 2,
    "title": "Buy groceries",
    "priority": "MEDIUM",
    "done": true
  }
]
```

---

## 🏗️ Design Highlights

- **No external libraries** — pure Java 8, zero dependencies
- **Persistent data** — tasks survive after the app is closed
- **Input validation** — handles empty titles and invalid priorities gracefully
- **Sorted listing** — tasks always displayed HIGH → MEDIUM → LOW

---

## 👤 Author

**Atul Kumar**  
🔗 [github.com/Atulkumarray](https://github.com/Atulkumarray)

---

<p align="center">⭐ If you found this useful, consider starring the repo!</p>
