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
     * Method for adding tasks
     * @param task task
     */
    public abstract void add(Task task);

    /**
     * Method for removing tasks that equals input
     * @param task task
     */
    public abstract void remove(Task task);


    /**
     * Method for getting task with given index
     * @param index index
     * @return task
     */
    public abstract Task getTask(int index);

    /**
     * Method for getting task count in list
     * @return task count in list
     */
    public int size() {
        return itemsCount;
    }

    /**
     * Method for getting array of tasks, which time between 'from' and 'to'(including)
     * @param from start time
     * @param to end time
     * @return task array
     */
    public abstract Task[] incoming(int from, int to);
}
