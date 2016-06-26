package ua.edu.sumdu.ta.shestak.maksym.pr6;

/**
 * @author Maksym Shestak
 * @since 03.06.2016
 */
public class LinkedTaskList extends AbstractTaskList {
    private class Element {
        private Task data;
        private Element next;

        public Element(Task data) {
            setNext(null);
            setData(data);
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

        while(current != null) {
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
    public void add(Task task) throws IllegalArgumentException {
        if(task == null || task.getTitle().equals(""))
            throw new IllegalArgumentException();

        if(!task.getTitle().startsWith(AbstractTaskList.taskListTitle))
            task.setTitle(AbstractTaskList.taskListTitle + task.getTitle());

        if(head == null)
            head = new Element(task);
        else {
            Element current = head;
            while(current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Element(task));
        }
        itemsCount++;
    }

    @Override
    public void remove(Task task) throws IllegalArgumentException {
        if(task == null || task.getTitle().equals(""))
            throw new IllegalArgumentException();

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
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= itemsCount || head == null)
            throw new IndexOutOfBoundsException();

        if(index == 0)
            return head.getData();

        Element current = head;
        for(int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }
}
