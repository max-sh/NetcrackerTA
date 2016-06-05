package ua.edu.sumdu.ta.shestak.maksym.pr3;

import ua.edu.sumdu.ta.shestak.maksym.pr2.Task;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Maksym Shestak
 * @since 03.06.2016
 */
class ArrayTaskList extends AbstractTaskList {

    private int size = 5;
    private static int arrayTaskListCount = 0;
    private Task list[] = new Task[size];


    ArrayTaskList() {
        arrayTaskListCount++;
    }

    /**
     * Метод получения актуального количества списков
     * @return количество списков
     */
    public static int getArrayTaskListCount() {
        return arrayTaskListCount;
    }

    @Override
    public void add(Task task) {
        if(task == null || Objects.equals(task.getTitle(), "")) return;


        if(itemsCount < size) {
            task.setTitle(taskListTitle + task.getTitle());
            list[itemsCount] = task;
            itemsCount++;
        } else {
            task.setTitle(taskListTitle + task.getTitle());
            size += 10;
            Task[] tmp = new Task[size];

            System.arraycopy(list, 0, tmp, 0, 5);
            list = tmp;

            list[itemsCount] = task;
            itemsCount++;
        }
        //System.out.println("{ArrayTaskList} size = " + size + "\titems: " + size());
    }

    @Override
    public void remove(Task task) {
        if(task == null || Objects.equals(task.getTitle(), "")) return;

        Task tmp[] = new Task[list.length];
        boolean deleted = true;
        while(deleted) {
            deleted = false;
            for(int i = 0; i < itemsCount; i++) {
                if(Objects.equals(list[i].getTitle(), task.getTitle())) {
                    System.arraycopy(list, 0, tmp, 0, i);
                    System.arraycopy(list, i + 1, tmp, i, itemsCount - i - 1);
                    itemsCount--;
                    System.arraycopy(tmp, 0, list, 0, itemsCount);
                    deleted = true;
                    break;
                }
            }
        }
        list = tmp;
    }

    @Override
    public Task getTask(int index) {
        if(index >= 0 && index < itemsCount)
            return list[index];
        return null;
    }

//    public void print() {
//        System.out.println(Arrays.toString(list));
//        System.out.println(itemsCount);
//    }
}
