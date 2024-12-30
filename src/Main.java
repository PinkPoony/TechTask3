import managers.TaskManager;
import modules.Epic;
import modules.SubTask;
import modules.Task;

import java.util.ArrayList;

// YELLOW
// Мелочь, но папки(пакеты) принято называть в единственном числе
// то есть manager, model и тд
public class Main{
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("Поесть", "Приготовить еду", "To do");
        Task task2 = new Task("Погулять с собакой", "Надо собрать и выйти", "To do");
        Epic epic = new Epic("Переезд", "в новый дом");
        SubTask subTask = new SubTask("Собрать коробки", "много коробок", "IN PROGRESS", 3);
        SubTask subTask2 = new SubTask("Подготовить собак", "Собрать игрушки и еду", "DONE", 3);

        taskManager.createTask(task);
        taskManager.createTask(task2);
        taskManager.createEpic(epic);
        taskManager.createSubTask(subTask);
        taskManager.createSubTask(subTask2);


        System.out.println("Таски");
        System.out.println(taskManager.getTaskByID(2));

        ArrayList<Task> tasks = taskManager.getTasks();
        System.out.println(tasks);

        taskManager.deleteTaskByID(1);

        System.out.println("После удаления");
        System.out.println(tasks);

        System.out.println("Эпики");
        System.out.println(taskManager.getEpics());

        System.out.println(taskManager.getSubTasks());

        System.out.println("Получение subtask");
        System.out.println(taskManager.getSubTaskByID(4));

        taskManager.deleteSubTaskByID(4);

        System.out.println("Удаление сабтаска");
        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubTasks());


    }
}
