package module;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<SubTask> subTasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description, "NEW");
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
    }

    public void updateStatus() {
        boolean hasNew = false;
        boolean hasInProcess = false;
        boolean hasDone = false;

        for (SubTask subTask : subTasks) {
            // YELLOW+
            // Не очень хорошо, что у блока switch нет секции default
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
