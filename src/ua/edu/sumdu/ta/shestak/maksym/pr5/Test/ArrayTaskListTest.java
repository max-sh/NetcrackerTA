package ua.edu.sumdu.ta.shestak.maksym.pr5.Test;

import org.junit.Before;
import ua.edu.sumdu.ta.shestak.maksym.pr5.ArrayTaskList;

public class ArrayTaskListTest extends TaskListTest {
    @Before 
    public void createTaskList() {
        tasks = new ArrayTaskList();
    }
}


