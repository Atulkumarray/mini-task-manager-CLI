package taskmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskManager {

    private List<Task> tasks;
    private int        nextId;

    public TaskManager() {
        this.tasks  = FileHandler.loadTasks();
        this.nextId = 1;

        // calculate next ID from existing data
        for (Task t : tasks) {
            if (t.getId() >= nextId) {
                nextId = t.getId() + 1;
            }
        }
    }

    // ─────────────────────────────── ADD ─────────────────────────────────

    public void addTask(String title, String priority) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("  ERROR: Title cannot be empty.");
            return;
        }

        String p = priority.trim().toUpperCase();
        if (!p.equals("HIGH") && !p.equals("MEDIUM") && !p.equals("LOW")) {
            System.out.println("  ERROR: Invalid priority '" + priority
                    + "'. Accepted: HIGH, MEDIUM, LOW");
            return;
        }

        Task task = new Task(nextId++, title.trim(), p);
        tasks.add(task);
        FileHandler.saveTasks(tasks);

        System.out.println();
        System.out.println("  Task added successfully!");
        System.out.println(task);
    }

    // ─────────────────────────────── LIST ────────────────────────────────

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\n  No tasks found. Add one with option 1!");
            return;
        }

        // sort by priority order, then pending before done
        List<Task> sorted = new ArrayList<Task>(tasks);
        Collections.sort(sorted, new Comparator<Task>() {
            @Override
            public int compare(Task a, Task b) {
                int cmp = Integer.compare(a.getPriorityOrder(), b.getPriorityOrder());
                if (cmp != 0) return cmp;
                return Boolean.compare(a.isDone(), b.isDone());
            }
        });

        int pending = 0;
        for (Task t : tasks) {
            if (!t.isDone()) pending++;
        }
        int done = tasks.size() - pending;

        System.out.println();
        System.out.println("  " + repeat("=", 70));
        System.out.printf("  %-4s | %-35s | %-8s | %s%n",
                "ID", "TITLE", "PRIORITY", "STATUS");
        System.out.println("  " + repeat("=", 70));
        for (Task t : sorted) {
            System.out.println(t);
        }
        System.out.println("  " + repeat("=", 70));
        System.out.printf("  Total: %d task(s)   |   Pending: %d   |   Done: %d%n",
                tasks.size(), pending, done);
        System.out.println("  " + repeat("=", 70));
        System.out.println("  Saved in: " + FileHandler.getFilePath());
        System.out.println("  " + repeat("=", 70));
    }

    // ──────────────────────────── MARK DONE ──────────────────────────────

    public void markDone(int id) {
        Task task = findById(id);
        if (task == null) {
            System.out.println("  ERROR: No task found with ID " + id);
            return;
        }
        if (task.isDone()) {
            System.out.println("  Task #" + id + " is already marked as done.");
            return;
        }
        task.setDone(true);
        FileHandler.saveTasks(tasks);
        System.out.println("  Task #" + id + " marked as done!");
        System.out.println(task);
    }

    // ─────────────────────────────── DELETE ──────────────────────────────

    public void deleteTask(int id) {
        Task task = findById(id);
        if (task == null) {
            System.out.println("  ERROR: No task found with ID " + id);
            return;
        }
        tasks.remove(task);
        FileHandler.saveTasks(tasks);
        System.out.println("  Task #" + id
                + " (\"" + task.getTitle() + "\") deleted successfully.");
    }

    // ─────────────────────────────── HELPER ──────────────────────────────

    private Task findById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    private static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }
}
