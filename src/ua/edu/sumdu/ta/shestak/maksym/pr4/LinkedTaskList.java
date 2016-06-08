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
        private Task data;
        private Element next;

        public Element(Task data) {
            next = null;
            this.data = data;
        }

        public Task getData() {
            return data;
        }

        public void setData(Task data) {
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
    private static int linkedTaskListCount = 0;
    public LinkedTaskList() {
        super();
        linkedTaskListCount++;
    }

    @Override
    public Task[] incoming(int from, int to) {
        Element current = head;
        int counter = 0;

        while (current != null) {
            if(current.getData().isActive()) {
                if(current.getData().getTime() > from &&
                        current.getData().getTime() <= to) {
                    counter++;
                }
                if(current.getData().isRepeated()) {
                    for(int tm = current.getData().getStartTime(); tm <= current.getData().getEndTime(); tm += current.getData().getRepeatInterval()) {
                        if(tm > from && tm <= to) {
                            counter++;
                            break;
                        }
                    }
                }
            }
            current = current.getNext();
        }

        Task[] arr = new Task[counter];
        counter = 0;
        current = head;




        while (current != null) {
            if(current.getData().isActive()) {
                if(current.getData().getTime() > from &&
                        current.getData().getTime() <= to) {
                    arr[counter] = current.getData();
                    counter++;
                }
                if(current.getData().isRepeated()) {
                    for(int tm = current.getData().getStartTime(); tm <= current.getData().getEndTime(); tm += current.getData().getRepeatInterval()) {
                        if(tm > from && tm <= to) {
                            arr[counter] = current.getData();
                            counter++;
                            break;
                        }
                    }
                }
            }
            current = current.getNext();
        }

        return arr;
    }

    @Override
    public void add(Task task) {
        if(task == null || task.getTitle().equals("")) return;

        if(!task.getTitle().startsWith(AbstractTaskList.taskListTitle))
            task.setTitle(AbstractTaskList.taskListTitle + task.getTitle());

        if(head == null)
            head = new Element(task);
        else {
            Element current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Element(task));
        }
        itemsCount++;
    }

    @Override
    public void remove(Task task) {
        if(task == null || task.getTitle().equals("")) return;

        while(head.getData().getTitle().equals(task.getTitle())) {
            head = head.getNext();
            itemsCount--;
        }

        Element current = head;
        while(current.getNext() != null) {
            if(current.getNext().getData().getTitle().equals(task.getTitle())) {
                current.setNext(current.getNext().getNext());
                itemsCount--;
            }
            current = current.getNext();
        }
    }

    @Override
    public Task getTask(int index) {
        if (index < 0 || index >= itemsCount || head == null)
            return null;

        if(index == 0)
            return head.getData();

        Element current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
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
