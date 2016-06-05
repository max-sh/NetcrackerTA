package ua.edu.sumdu.ta.shestak.maksym.pr4;

import ua.edu.sumdu.ta.shestak.maksym.pr2.Task;

/**
 * @author Maksym Shestak
 * @since 03.06.2016
 */
public class Main {
    public static void main(String[] args) {

        LinkedTaskList list = new LinkedTaskList();

        Task t1 = new Task("t1", 1);
        Task t2 = new Task("t2", 2);
        Task t3 = new Task("t3", 3);
        Task t4 = new Task("t4", 4);
        Task t5 = new Task("t5", 5);
        Task t6 = new Task("t6", 3);

        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);

        //System.out.println(list.getTask(5).getTitle());
        list.remove(t6);

        System.out.println(list.toString());
        //System.out.println(list.size());

        //list.remove(t1);
        //System.out.println(list.getTask(0).getTitle());
    }
}
