package module;

import java.util.HashMap;

public class Epic extends Task {
    // На хеш-мапу
    private HashMap<Long, SubTask> subTasksMap = new HashMap<>();

    public Epic(String name, String description) {
        super(name, description, "NEW");
    }

    public HashMap<Long, SubTask> getSubTasks() {
        return subTasksMap;
    }

    public void removeSubtasks() {
        subTasksMap.clear();
        status = "NEW";
    }

    public void addSubTasks(HashMap<Long, SubTask> subTasks) {
        subTasksMap.putAll(subTasks);
        updateStatus();
    }

    public void addSubTask(SubTask subTask) {
        subTasksMap.put(subTask.getId(), subTask);
        updateStatus();
    }

    public void updateStatus() {
        boolean hasNew = false;
        boolean hasInProcess = false;
        boolean hasDone = false;

        for (SubTask subTask : subTasksMap.values()) {
            switch (subTask.getStatus()) {
                case "NEW":
                    hasNew = true;
                    break;
                case "IN PROCESS":
                    hasInProcess = true;
                    break;
                case "DONE":
                    hasDone = true;
                    break;
                default:
                    System.err.println("Неизвестный статус" + subTask.getStatus());
            }
        }

        if (hasInProcess || (hasNew && hasDone)) {
            status = "IN PROCESS";
        } else if (hasNew) {
            status = "NEW";
        } else if (hasDone) {
            status = "DONE";
        } else {
            status = "NEW";
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
