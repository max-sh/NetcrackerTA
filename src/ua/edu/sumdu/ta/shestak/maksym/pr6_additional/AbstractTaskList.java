package ua.edu.sumdu.ta.shestak.maksym.pr6_additional;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author Maksym Shestak
 * @since 03.06.2016
 */
@SuppressWarnings("Duplicates")
public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    protected final static String taskListTitle = "[EDUCTR][TA]";
    protected int itemsCount = 0;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getClass().getSimpleName());
        stringBuilder.append("[");

        if(size() > 0) {
            Iterator<Task> taskIterator = iterator();
            stringBuilder.append(taskIterator.next().getTitle());

            while(taskIterator.hasNext()) {
                stringBuilder.append(",").append(taskIterator.next().getTitle());
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            AbstractTaskList list = getClass().newInstance();

//            Iterator<Task> taskIterator = iterator();
//            while(taskIterator.hasNext()) {
//                list.add(taskIterator.next());
//            }
            for(Task task : this)
                list.add(task);

            return list;

        } catch(InstantiationException e) {
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        }
        return super.clone();
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
    //public abstract Task getTask(int index);

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
