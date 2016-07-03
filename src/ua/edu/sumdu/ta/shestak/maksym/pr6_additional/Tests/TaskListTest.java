package ua.edu.sumdu.ta.shestak.maksym.pr6_additional.Tests;

import org.junit.Assert;
import org.junit.Test;
import ua.edu.sumdu.ta.shestak.maksym.pr6_additional.AbstractTaskList;
import ua.edu.sumdu.ta.shestak.maksym.pr6_additional.ArrayTaskList;
import ua.edu.sumdu.ta.shestak.maksym.pr6_additional.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Maksym Shestak
 * @since 28.06.2016
 */
@SuppressWarnings("Duplicates")
public abstract class TaskListTest {

    protected AbstractTaskList tasks;

    // utility functions --------------------------------------------------
    private static void assertContains(Task[] expected, Task[] actualA) {
        assertEquals("Unexpected size", expected.length, actualA.length);
        List<Task> actual = new ArrayList<Task>();
        for (Task task : actualA)
            actual.add(task);
        for (Task task : expected)
            if (actual.contains(task))
                actual.remove(task);
            else
                fail("Task "+ task +" expected to be in list");

        if (! actual.isEmpty())
            fail("Tasks "+ actual +" are unexpected in list");
    }

    private void assertContains(Task[] expected) {
        Task[] actual = new Task[tasks.size()];

        Iterator taskIterator = tasks.iterator();
        for (int i = 0; i < tasks.size(); i++)
            actual[i] = (Task) taskIterator.next();

        assertContains(expected, actual);
    }

    private void addAll(Task[] ts) {
        for (Task t : ts)
            tasks.add(t);
    }

    private static Task task(String title) {
        return new Task(title, 0);
    }

    private static Task task(String title, int time, boolean active) {
        Task t = new Task(title, time);
        t.setActive(active);
        return t;
    }

    private static Task task(String title, int from, int to, int interval, boolean active) {
        Task t = new Task(title, from, to, interval);
        t.setActive(active);
        return t;
    }

    // tests ----------------------------

    @Test
    public void testClone() throws CloneNotSupportedException {

        Task[] ts = new Task[50];
        for(int i = 0; i < 50; i++) {
            ts[i] = task(String.valueOf(i), i, i, i, true);
        }
        addAll(ts);

        AbstractTaskList clone = (AbstractTaskList) tasks.clone();
        assertContains(ts);
    }

    @Test
    public void testEquals() throws CloneNotSupportedException, IllegalAccessException, InstantiationException {

        AbstractTaskList clone = tasks.getClass().newInstance();

        Task[] ts = new Task[50];
        for(int i = 0; i < 50; i++) {
            ts[i] = task(String.valueOf(i), i, i, i, true);
            clone.add(ts[i]);
        }
        addAll(ts);

        assertTrue(tasks.equals(clone));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIteratorOutOfBounds() {
        Task[] ts = {task("A"), task("B"), task("C")};
        addAll(ts);

        Iterator iterator = tasks.iterator();

        for(int i = 0; i <= ts.length; i++) iterator.next();
    }

    @Test
    public void testToString() {
        Task[] ts = {task("A"), task("B"), task("C")};
        addAll(ts);

        String str = tasks.getClass().getSimpleName() + "[[EDUCTR][TA]A, [EDUCTR][TA]B, [EDUCTR][TA]C]";
        assertEquals(str, tasks.toString());
    }

}
