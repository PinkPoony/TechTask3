package manager;

import module.Epic;
import module.SubTask;
import module.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Long, Task> taskMap;
    private HashMap<Long, Epic> epicMap;
    private HashMap<Long, SubTask> subTaskMap;
    private long nextId = 1;

    public TaskManager() {
        taskMap = new HashMap<>();
        epicMap = new HashMap<>();
        subTaskMap = new HashMap<>();
    }

    public long createTask(Task task) {
        task.setId(nextId);
        taskMap.put(nextId, task);
        nextId++;
        return task.getId();
    }

    public Long createSubTask(SubTask sub) {
        long epicId = sub.getEpicId();
        if (!epicMap.containsKey(epicId)) {
            System.out.println("Ошибка! Не был найден ID эпика");
            return null;
        }
        sub.setId(nextId);
        Epic epic = epicMap.get(epicId);
        epic.addSubTask(sub);
        subTaskMap.put(nextId, sub);
        nextId++;

        return sub.getId();
    }

    public long createEpic(Epic epic) {
        epic.setId(nextId);
        epicMap.put(nextId, epic);
        nextId++;
        return epic.getId();
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(taskMap.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epicMap.values());
    }

    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTaskMap.values());
    }

    public void deleteTasks() {
        taskMap.clear();
    }

    public void deleteEpics() {
        epicMap.clear();
        subTaskMap.clear();
    }

    public void deleteSubTasks() {
        subTaskMap.clear();
        for (Epic epic : epicMap.values()) {
            epic.removeSubtasks();
        }
    }

    public Task getTaskByID(long id) {
        return taskMap.get(id);
    }

    public SubTask getSubTaskByID(long id) {
        return subTaskMap.get(id);
    }

    public ArrayList<SubTask> getSubTasksByEpicId(long epicId) {
        Epic epic = epicMap.get(epicId);
        return new ArrayList<>(epic.getSubTasks().values());
    }

    public void deleteTaskByID(long id) {
        taskMap.remove(id);
    }

    public void deleteEpicByID(long id) {
        Epic epic = epicMap.remove(id);
        if (epic != null) {
            for (SubTask subTask : epic.getSubTasks().values()) {
                subTaskMap.remove(subTask.getId());
            }
        }
    }

    public void deleteSubTaskByID(long id) {
        SubTask subTask = subTaskMap.remove(id);
        if (subTask != null) {
            Epic epic = epicMap.get(subTask.getEpicId());
            if (epic != null) {
                epic.getSubTasks().remove(subTask);
                epic.updateStatus();
            }
        }
    }

    public void updateTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        Epic oldEpic = epicMap.get(epic.getId());
        HashMap<Long, SubTask> subTasks = oldEpic.getSubTasks();
        epic.addSubTasks(subTasks);
        epicMap.put(epic.getId(), epic);
    }

    public void updateSubTask(SubTask subTask) {
        // RED
        // Необходимо так же заменить старую сабтаску на новую внутри эпика
        subTaskMap.put(subTask.getId(), subTask);
        Epic epic = epicMap.get(subTask.getEpicId());
        if (epic != null) {
            epic.updateStatus();
        }

    }

}
