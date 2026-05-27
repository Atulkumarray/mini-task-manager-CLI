package taskmanager;

public class Task {

    private int     id;
    private String  title;
    private String  priority;   // HIGH | MEDIUM | LOW
    private boolean done;

    public Task() {}

    public Task(int id, String title, String priority) {
        this.id       = id;
        this.title    = title;
        this.priority = priority.toUpperCase();
        this.done     = false;
    }

    public int     getId()       { return id; }
    public String  getTitle()    { return title; }
    public String  getPriority() { return priority; }
    public boolean isDone()      { return done; }
    public void    setDone(boolean done) { this.done = done; }

    public int getPriorityOrder() {
        switch (priority) {
            case "HIGH":   return 1;
            case "MEDIUM": return 2;
            case "LOW":    return 3;
            default:       return 4;
        }
    }

    @Override
    public String toString() {
        String status = done ? "[DONE]   " : "[PENDING]";
        return String.format("  %-4d | %-35s | %-8s | %s",
                id, title, priority, status);
    }
}
