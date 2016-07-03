package ua.edu.sumdu.ta.shestak.maksym.pr6_additional.Tests;

import org.junit.Before;
import ua.edu.sumdu.ta.shestak.maksym.pr6_additional.LinkedTaskList;

/**
 * @author Maksym Shestak
 * @since 28.06.2016
 */
public class LinkedTaskListTest extends TaskListTest {

    @Before
    public void createTaskList() {
        tasks = new LinkedTaskList();
    }
}
