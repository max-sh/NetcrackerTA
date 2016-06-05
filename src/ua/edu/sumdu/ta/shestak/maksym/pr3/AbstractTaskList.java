package ua.edu.sumdu.ta.shestak.maksym.pr3;

import ua.edu.sumdu.ta.shestak.maksym.pr2.Task;

/**
 * @author Maksym Shestak
 * @since 03.06.2016
 */
public abstract class AbstractTaskList {
    protected final static String taskListTitle = "[EDUCTR][TA]";
    protected int itemsCount = 0;



    /**
     * метод для добавления не уникальных задач
     * @param task задача
     */
    public abstract void add(Task task);

    /**
     * метод для удаления всех задач равных входной
     * @param task задача
     */
    public abstract void remove(Task task);


    /**
     * Метод получения задачи под заданным номером
     * @param index номер задачи
     * @return задача
     */
    public abstract Task getTask(int index);

    /**
     * Метод получения количества задач в текущем списке
     * @return количество задач в списку
     */
    public int size() {
        return itemsCount;
    }
}
