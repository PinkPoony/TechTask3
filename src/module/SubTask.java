package module;

public class SubTask extends Task {
    // RED+
    // Рушится инкапсуляция за счет отсутствия модификатора доступа
     private long epicId;

    public SubTask(String name, String description, String status, long epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public long getEpicId() {
        return epicId;
    }

    public void setEpicId(long epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
