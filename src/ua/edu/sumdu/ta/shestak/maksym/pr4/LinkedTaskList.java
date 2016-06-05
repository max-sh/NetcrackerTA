package ua.edu.sumdu.ta.shestak.maksym.pr4;

import ua.edu.sumdu.ta.shestak.maksym.pr2.Task;
import ua.edu.sumdu.ta.shestak.maksym.pr3.AbstractTaskList;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Maksym Shestak
 * @since 03.06.2016
 */
class LinkedTaskList extends AbstractTaskList {
    private class Element {
        Object data;
        Element next;

        public Element(Object data) {
            next = null;
            this.data = data;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }

    private Element head;

    LinkedTaskList() {

    }

    /**
     * Функция для получения массива задач, время которых находится между
     * from (исключительно) и to (включительно).
     * @param from начало интервала времени
     * @param to конец интервала времени
     * @return массив задач
     */
    Task[] incoming(int from, int to) {
        Element current = head;
        int counter = 0;

        while (current != null) {
            if(((Task) current.getData()).getTime() > from &&
                    ((Task) current.getData()).getTime() <= to) {
                counter++;
            }
            current = current.getNext();
        }

        Task[] arr = new Task[counter];
        counter = 0;
        current = head;
        while (current != null) {
            if(((Task) current.getData()).getTime() > from &&
                    ((Task) current.getData()).getTime() <= to) {
                //list.add((Task) current.getData());
                arr[counter] = (Task) current.getData();
                counter++;
            }
            current = current.getNext();
        }

        return arr;
    }

    @Override
    public void add(Task task) {
        if(task == null || Objects.equals(task.getTitle(), "")) return;

        task.setTitle(taskListTitle + task.getTitle());
        if(head == null) {
            head = new Element(task);
        }
        else
        {
            Element tmp = new Element(task);
            Element current = head;
            if (current != null) {
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(tmp);
            }
        }
        itemsCount++;
    }

    @Override
    public void remove(Task task) {
        if(task == null || Objects.equals(task.getTitle(), "")) return;

        boolean deleted = true;
        while(deleted) {
            deleted = false;
            if(Objects.equals(((Task) head.getData()).getTitle(), task.getTitle())) {
                head = head.getNext();
                itemsCount--;
                deleted = true;
            }
        }



        Element current = head;
        for (int i = 0; i < itemsCount; i++) {
            if(current.getNext() == null) break;

            if(Objects.equals(((Task) current.next.getData()).getTitle(), task.getTitle())) {
                current.setNext(current.getNext().getNext());
                itemsCount--;
            }

            current = current.getNext();
        }

    }

    @Override
    public Task getTask(int index) {
        if (index < 0 || index >= itemsCount)
            return null;

        if(index == 0)
            return (Task) head.getData();

        Element current = null;

        if (head != null) {
            current = head.getNext();
            for (int i = 0; i < index; i++) {
                if (current.getNext() == null)
                    return null;

                current = current.getNext();
            }
            return (Task) current.getData();
        }
        return null;
    }

    public String toString() {
        String output = "";
        Element current = head;

        while (current != null) {
            output += "[" + current.getData().toString() + "]";
            current = current.getNext();
        }

        return output;
    }
}
