package ua.edu.sumdu.ta.shestak.maksym.pr5;

/**
 * @author Maksym Shestak
 * @since 03.06.2016
 */
public class LinkedTaskList extends AbstractTaskList {
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
        int incomingElements = 0;
        Task[] tmp = new Task[itemsCount];

        while (current != null) {
            if(current.getData().nextTimeAfter(from) > from && current.getData().nextTimeAfter(from) <= to)
                tmp[incomingElements++] = current.getData();

            current = current.getNext();
        }
        System.out.println(incomingElements);

        Task[] incomingArray = new Task[incomingElements];
        System.arraycopy(tmp, 0, incomingArray, 0, incomingElements);

        return incomingArray;
    }

    @Override
    public void add(Task task) {
        if(task == null || task.getTitle().equals(""))
            throw new RuntimeException();

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
        if(task == null || task.getTitle().equals(""))
            throw new RuntimeException();

        while(head.getData().getTitle().equals(task.getTitle())) {
            head = head.getNext();
            itemsCount--;
        }

        Element current = head;
        while(current != null && current.getNext() != null) {
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
            throw new RuntimeException();

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
            output += "[" + current.getData().toString() + "]" + "\n";
            current = current.getNext();
        }
        return output;
    }
}
