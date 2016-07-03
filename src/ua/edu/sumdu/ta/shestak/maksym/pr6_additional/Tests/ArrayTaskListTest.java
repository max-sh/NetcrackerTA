package ua.edu.sumdu.ta.shestak.maksym.pr6_additional.Tests;

import org.junit.Before;
import ua.edu.sumdu.ta.shestak.maksym.pr6_additional.ArrayTaskList;

/**
 * @author Maksym Shestak
 * @since 28.06.2016
 */
public class ArrayTaskListTest extends TaskListTest {
    @Before
    public void createTaskList() {
        tasks = new ArrayTaskList();
    }
}
