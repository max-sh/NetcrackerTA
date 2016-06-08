package ua.edu.sumdu.ta.shestak.maksym.pr5.Test;

import org.junit.Before;
import ua.edu.sumdu.ta.shestak.maksym.pr5.LinkedTaskList;

public class LinkedTaskListTest extends TaskListTest {
    @Before 
    public void createTaskList() {
        tasks = new LinkedTaskList();
    }
}

