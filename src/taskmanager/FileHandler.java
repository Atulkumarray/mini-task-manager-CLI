package taskmanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_PATH = "tasks.json";

    // ─────────────────────────── LOAD ────────────────────────────────────

    public static List<Task> loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<Task>();
        }
        try {
            byte[] bytes   = Files.readAllBytes(file.toPath());
            String content = new String(bytes, "UTF-8");
            return parseJson(content);
        } catch (IOException e) {
            System.out.println("  WARNING: Could not read tasks file. " + e.getMessage());
            return new ArrayList<Task>();
        }
    }

    // ─────────────────────────── SAVE ────────────────────────────────────

    public static void saveTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            sb.append("  {\n");
            sb.append("    \"id\": ").append(t.getId()).append(",\n");
            sb.append("    \"title\": \"").append(escapeJson(t.getTitle())).append("\",\n");
            sb.append("    \"priority\": \"").append(t.getPriority()).append("\",\n");
            sb.append("    \"done\": ").append(t.isDone()).append("\n");
            sb.append("  }");
            if (i < tasks.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");

        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("  WARNING: Could not save tasks. " + e.getMessage());
        }
    }

    // ─────────────────────────── PARSE ───────────────────────────────────

    private static List<Task> parseJson(String json) {
        List<Task> tasks  = new ArrayList<Task>();
        String[]   blocks = json.split("\\{");

        for (String block : blocks) {
            if (!block.contains("\"id\"")) continue;
            try {
                int     id       = Integer.parseInt(extractValue(block, "id"));
                String  title    = extractValue(block, "title");
                String  priority = extractValue(block, "priority");
                boolean done     = Boolean.parseBoolean(extractValue(block, "done"));

                Task task = new Task(id, title, priority);
                task.setDone(done);
                tasks.add(task);
            } catch (Exception e) {
                // skip any malformed entry silently
            }
        }
        return tasks;
    }

    private static String extractValue(String block, String key) {
        String pattern = "\"" + key + "\"";
        int idx = block.indexOf(pattern);
        if (idx == -1) return "";

        int colonIdx = block.indexOf(":", idx);
        int start    = colonIdx + 1;

        // skip whitespace
        while (start < block.length() &&
               (block.charAt(start) == ' '  ||
                block.charAt(start) == '\n' ||
                block.charAt(start) == '\r')) {
            start++;
        }

        if (block.charAt(start) == '"') {
            // string value
            int end = block.indexOf("\"", start + 1);
            return block.substring(start + 1, end);
        } else {
            // number / boolean value
            int end = start;
            while (end < block.length() &&
                   block.charAt(end) != ','  &&
                   block.charAt(end) != '\n' &&
                   block.charAt(end) != '}') {
                end++;
            }
            return block.substring(start, end).trim();
        }
    }

    private static String escapeJson(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static String getFilePath() {
        return new File(FILE_PATH).getAbsolutePath();
    }
}
