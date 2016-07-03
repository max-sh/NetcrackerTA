package ua.edu.sumdu.ta.shestak.maksym.pr6_additional.Tests;


import org.junit.Assert;
import org.junit.Test;
import ua.edu.sumdu.ta.shestak.maksym.pr6_additional.Task;

/**
 * @author Maksym Shestak
 * @since 28.06.2016
 */
public class TaskTest {


    private void assertEquals(Task t1, Task t2) {
        Assert.assertEquals("Titles do not match",t1.getTitle(), t2.getTitle());
        Assert.assertEquals("Time do not match", t1.getTime(), t2.getTime());
        Assert.assertEquals("Start time do not match", t1.getStartTime(), t2.getStartTime());
        Assert.assertEquals("End time do not match", t1.getEndTime(), t2.getEndTime());
        Assert.assertEquals("Repeat interval do not match", t1.getRepeatInterval(), t2.getRepeatInterval());
        Assert.assertEquals("Active state do not match", t1.isActive(), t2.isActive());
        Assert.assertEquals("Repeat state do not match", t1.isRepeated(), t2.isRepeated());
    }


    @Test
    public void testClone() throws CloneNotSupportedException {
        Task task1 = new Task("task1", 1);;
        Task task2 = new Task("task2", 0, 100, 10);

        assertEquals(task1, (Task) task1.clone());
        assertEquals(task2, (Task) task2.clone());
    }

    @Test
    public void testEquals() throws CloneNotSupportedException {
        Task task1 = new Task("task1", 1);;
        Task task2 = new Task("task2", 0, 100, 10);

        Task copy1 = new Task("task1", 1);
        Task copy2 = new Task("task2", 0, 100, 10);

        Assert.assertTrue(task1.equals(copy1));
        Assert.assertTrue(task2.equals(copy2));
    }

    @Test
    public void testHashcode() {
        Task task1 = new Task("task1", 1);;
        Task task2 = new Task("task2", 0, 100, 10);

        Task copy1 = new Task("task1", 1);;
        Task copy2 = new Task("task2", 0, 100, 10);

        Assert.assertEquals(task1.hashCode(), copy1.hashCode());
        Assert.assertEquals(task2.hashCode(), copy2.hashCode());
    }
}
