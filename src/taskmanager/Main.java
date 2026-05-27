package taskmanager;

import java.util.Scanner;

public class Main {

    private static final Scanner     SCANNER      = new Scanner(System.in);
    private static final TaskManager TASK_MANAGER = new TaskManager();

    public static void main(String[] args) {

        printBanner();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = SCANNER.nextLine().trim();

            switch (choice) {
                case "1":
                    handleAddTask();
                    break;
                case "2":
                    TASK_MANAGER.listTasks();
                    break;
                case "3":
                    handleMarkDone();
                    break;
                case "4":
                    handleDeleteTask();
                    break;
                case "5":
                    running = confirmExit();
                    break;
                default:
                    System.out.println("\n  Invalid option. Please enter a number from 1 to 5.");
            }
        }

        System.out.println("\n  Goodbye! Stay productive.");
        SCANNER.close();
    }

    // ──────────────────────────── DISPLAY ────────────────────────────────

    private static void printBanner() {
        System.out.println();
        System.out.println("  +==========================================+");
        System.out.println("  |      TASK MANAGER CLI - Java Edition    |");
        System.out.println("  |          Built by  Atul Kumar           |");
        System.out.println("  +==========================================+");
        System.out.println("  Tasks are auto-saved to --> tasks.json");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("  +------------------------------------+");
        System.out.println("  |            MAIN  MENU             |");
        System.out.println("  +------------------------------------+");
        System.out.println("  |   1.  Add a Task                  |");
        System.out.println("  |   2.  List All Tasks              |");
        System.out.println("  |   3.  Mark Task as Done           |");
        System.out.println("  |   4.  Delete a Task               |");
        System.out.println("  |   5.  Exit                        |");
        System.out.println("  +------------------------------------+");
        System.out.print("  Enter choice (1-5): ");
    }

    // ──────────────────────────── HANDLERS ───────────────────────────────

    private static void handleAddTask() {
        System.out.println("\n  -- Add New Task ----------------------");
        System.out.print("  Task title              : ");
        String title = SCANNER.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("  ERROR: Title cannot be empty.");
            return;
        }

        System.out.print("  Priority (HIGH/MEDIUM/LOW) : ");
        String priority = SCANNER.nextLine().trim();

        TASK_MANAGER.addTask(title, priority);
    }

    private static void handleMarkDone() {
        System.out.println("\n  -- Mark Task as Done ----------------");
        System.out.print("  Enter Task ID : ");
        int id = readInt();
        if (id == -1) return;
        TASK_MANAGER.markDone(id);
    }

    private static void handleDeleteTask() {
        System.out.println("\n  -- Delete Task -----------------------");
        System.out.print("  Enter Task ID : ");
        int id = readInt();
        if (id == -1) return;
        TASK_MANAGER.deleteTask(id);
    }

    private static boolean confirmExit() {
        System.out.print("\n  Are you sure you want to exit? (y/n): ");
        String answer = SCANNER.nextLine().trim().toLowerCase();
        return !answer.equals("y");   // return false = stop loop
    }

    // ─────────────────────────────── UTIL ────────────────────────────────

    private static int readInt() {
        try {
            return Integer.parseInt(SCANNER.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("  ERROR: Please enter a valid number.");
            return -1;
        }
    }
}
