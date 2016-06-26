package ua.edu.sumdu.ta.shestak.maksym.pr6;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author Maksym Shestak
 * @since 03.06.2016
 */
public abstract class AbstractTaskList implements Cloneable {
    protected final static String taskListTitle = "[EDUCTR][TA]";
    protected int itemsCount = 0;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {

            AbstractTaskList list = getClass().newInstance();
            for(int i = 0; i < size(); i++) list.add(getTask(i));
            return list;

        } catch(InstantiationException e) {
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }
        return super.clone();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getClass().getSimpleName());
        stringBuilder.append("[");

        if(size() > 0) {
            stringBuilder.append(getTask(0).getTitle());
            for(int i = 1; i < size(); i++) stringBuilder.append(",").append(getTask(i).getTitle());
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Method for adding tasks
     *
     * @param task task
     */
    public abstract void add(Task task);

    /**
     * Method for removing tasks that equals input
     *
     * @param task task
     */
    public abstract void remove(Task task);


    /**
     * Method for getting task with given index
     *
     * @param index index
     * @return task
     */
    public abstract Task getTask(int index);

    /**
     * Method for getting task count in list
     *
     * @return task count in list
     */
    public int size() {
        return itemsCount;
    }

    /**
     * Method for getting array of tasks, which time between 'from' and 'to'(including)
     *
     * @param from start time
     * @param to   end time
     * @return task array
     */
    public abstract Task[] incoming(int from, int to);
}
