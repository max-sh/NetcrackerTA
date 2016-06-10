package ua.edu.sumdu.ta.shestak.maksym.pr5;


/**
 * @author Maksym Shestak
 * @since 03.06.2016
 */
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
     * @return number of lists
     */
    public static int getArrayTaskListCount() {
        return arrayTaskListCount;
    }

    @Override
    public void add(Task task) {
        if(task == null || task.getTitle().equals("")) throw new RuntimeException();

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
    public void remove(Task task) {
        if(task == null || task.getTitle().equals("")) throw new RuntimeException();

        int newSize = itemsCount;
        for(int i = 0; i < itemsCount; i++) {
            if(taskArrayList[i].getTitle().equals(task.getTitle())) {
                taskArrayList[i] = null;
                newSize--;
            }
        }

        for(int i = 0; i < itemsCount; i++) {
            if(taskArrayList[i] == null) {
                for(int j = i + 1; j < itemsCount; j++) {
                    if(taskArrayList[j] != null) {
                        taskArrayList[i] = taskArrayList[j];
                        taskArrayList[j] = null;
                        break;
                    }
                }
            }
        }


        Task[] tmp = new Task[newSize];
        System.arraycopy(taskArrayList, 0, tmp, 0, newSize);
        taskArrayList = tmp;
        itemsCount = newSize;
    }

    @Override
    public Task getTask(int index) {
        if(index >= 0 && index < itemsCount)
            return taskArrayList[index];
        else throw new RuntimeException();
    }

    @Override
    public Task[] incoming(int from, int to) {

        int incomingElements = 0;
        Task[] incomingArray = new Task[itemsCount];
        for(int i = 0; i < itemsCount; i++) {
            if(taskArrayList[i].isActive()) {
                if(taskArrayList[i].isRepeated()) {
                    for(int tm = taskArrayList[i].getStartTime(); tm <= taskArrayList[i].getEndTime(); tm += taskArrayList[i].getRepeatInterval()) {
                        if(tm > from && tm <= to) {
                            incomingArray[incomingElements] = taskArrayList[i];
                            incomingElements++;
                            break;
                        }
                    }
                }
                else if(taskArrayList[i].getTime() > from && taskArrayList[i].getTime() <= to) {
                    incomingArray[incomingElements] = taskArrayList[i];
                    incomingElements++;
                }
            }
        }

        Task[] tmp = new Task[incomingElements];
        System.arraycopy(incomingArray, 0, tmp, 0, incomingElements);

        return tmp;
    }

    @Override
    public String toString() {
        String res = "";

        for(int i = 0; i < itemsCount; i++) {
            res += taskArrayList[i] + "\n";
        }

        return res;
    }
}