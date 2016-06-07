package ua.edu.sumdu.ta.shestak.maksym.pr3;

import ua.edu.sumdu.ta.shestak.maksym.pr2.Task;

/**
 * @author Maksym Shestak
 * @since 03.06.2016
 */
public class Main {
    public static void main(String[] args) {
        ArrayTaskList arrayTaskList = new ArrayTaskList();

        Task t1 = new Task("t1", 1);
        Task t2 = new Task("t2", 2);
        Task t3 = new Task("t3", 3);
        Task t4 = new Task("t4", 4);
        Task t5 = new Task("t5", 5);
        Task t6 = new Task("t6", 3);

        arrayTaskList.add(t1);
        arrayTaskList.add(t2);
        arrayTaskList.add(t3);
        arrayTaskList.add(t4);
        arrayTaskList.add(t5);
        arrayTaskList.add(t6);

        arrayTaskList.remove(t1);

        System.out.println(arrayTaskList);
        //arrayTaskList.print();
        //arrayTaskList.remove(t2);
        //arrayTaskList.print();

    }

}
