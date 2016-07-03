package ua.edu.sumdu.ta.shestak.maksym.pr6_additional;


import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author Maksym Shestak
 * @since 03.06.2016
 */
@SuppressWarnings("Duplicates")
public class ArrayTaskList extends AbstractTaskList {

    private static int arrayTaskListCount = 0;

    private int size = 5;
    private Task taskArrayList[] = new Task[size];

    public ArrayTaskList() {
        super();
        arrayTaskListCount++;
    }

    /**
     * Method for getting actual number of lists
     *
     * @return number of lists
     */
    public static int getArrayTaskListCount() {
        return arrayTaskListCount;
    }

    @Override
    public void add(Task task) throws IllegalArgumentException {
        if(task == null || task.getTitle().equals(""))
            throw new IllegalArgumentException();

        if(!task.getTitle().startsWith(AbstractTaskList.taskListTitle))
            task.setTitle(AbstractTaskList.taskListTitle + task.getTitle());

        if(itemsCount >= size) {
            size += 10;
            Task[] tmp = new Task[size];

            System.arraycopy(taskArrayList, 0, tmp, 0, itemsCount);
            taskArrayList = tmp;
        }

        taskArrayList[itemsCount] = task;
        itemsCount++;
    }

    @Override
    public void remove(Task task) throws IllegalArgumentException {
        if(task == null || task.getTitle().equals(""))
            throw new IllegalArgumentException();

        int newSize = 0;
        for(int i = 0; i < itemsCount; i++)
            if(!taskArrayList[i].equals(task))
                taskArrayList[newSize++] = taskArrayList[i];

        Task[] tmp = new Task[newSize];
        System.arraycopy(taskArrayList, 0, tmp, 0, newSize);
        taskArrayList = tmp;
        itemsCount = newSize;
    }

    @Override
    public Task[] incoming(int from, int to) {

        int incomingElements = 0;
        Task[] incomingArray = new Task[itemsCount];
        /*
        for(int i = 0; i < itemsCount; i++) {
            if(taskArrayList[i].nextTimeAfter(from) > from && taskArrayList[i].nextTimeAfter(from) <= to)
                incomingArray[incomingElements++] = taskArrayList[i];
        }
        */
        for(Task t: this) {
            if(t.nextTimeAfter(from) > from && t.nextTimeAfter(from) <= to)
                incomingArray[incomingElements++] = t;
        }

        Task[] tmp = new Task[incomingElements];
        System.arraycopy(incomingArray, 0, tmp, 0, incomingElements);

        return tmp;
    }

    @Override
    public Iterator<Task> iterator() {
        return new ArrayTaskListIterator();
    }

    private class ArrayTaskListIterator implements Iterator<Task> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public Task next() throws IndexOutOfBoundsException {
            if(index >= 0 && index < itemsCount)
                return taskArrayList[index++];
            else throw new IndexOutOfBoundsException();
        }
    }
}
