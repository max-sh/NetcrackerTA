package ua.edu.sumdu.ta.shestak.maksym.test;

import ua.edu.sumdu.ta.shestak.maksym.pr2.Task;

/**
 * @author Maksym Shestak
 * @since 05.06.2016
 */
public class Main {
    public static void main(String[] args) {
        Task task = new Task("some", 10, 100, 20);
        task.setActive(true);
        System.out.println(task.nextTimeAfter(0));
        System.out.println(task.nextTimeAfter(9));
        System.out.println(task.nextTimeAfter(30));

    }
}
