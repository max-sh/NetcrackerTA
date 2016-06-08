package ua.edu.sumdu.ta.shestak.maksym.pr5;

/**
 * @author <a href="mailto:slvr.max@gmail.com">Maksym Shestak</a>
 * @since 02.06.2016
 */


/**
 * The class that describes the type "task" data,
 * which contains information about the essence of the problem,
 * its status (active / inactive), the time interval warning
 * time, through which you need to repeat the warning about it
 */
public class Task {
    private String title;
    private boolean active;

    private int time, startTime, endTime, repeatTime;


    public Task() {
        super();
    }
    /**
     * The constructor for a one-time task
     * @param title task title
     * @param time time on task notifications
     */
    public Task(String title, int time) {
        setTitle(title);
        setTime(time);
        setActive(false);
    }

    /**
     * The constructor for a recurring task
     * @param title task title
     * @param startTime the start time of the notification
     * @param endTime time alerting end
     * @param repeatTime the time after which you must repeat the notification
     */
    public Task(String title, int startTime, int endTime, int repeatTime) {
        setTitle(title);
        setTime(startTime, endTime, repeatTime);
        setActive(false);
    }

    /**
     * Method for getting task title
     * @return task title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method for setting task title
     * @param title task title
     */
    public void setTitle(String title) {
        if(title.equals("") || title == null) {
            throw new RuntimeException();
        }
        this.title = title;
    }

    /**
     * Method for getting task status
     * @return status
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Method for setting task  status
     * @param active статус задачи
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * The method for getting start time alerts (for repetitive tasks) or single alarm time (for a one-time task)
     * @return task time notification
     */
    public int getTime() {
        if(repeatTime != 0) return startTime;
        return time;
    }

    /**
     * The method of setting the start time for the one-time task, where time - time notifications about the task
     * @param time time notifications about the task
     */
    public void setTime(int time) {
        if(time < 0) {
            throw new RuntimeException();
        }

        this.time = time;
        this.startTime = time;
        this.endTime = time;
        this.repeatTime = 0;
    }

    /**
     * The method of setting the start time alerts for repeating tasks,
     * @param start notification start time
     * @param end notification end time
     * @param repeat the time interval at which you need to repeat notification
     */
    public void setTime(int start, int end, int repeat) {
        if(start < 0 || end < 0 || repeat < 0 || start > end) {
            System.out.println("incorrect input");
            throw new RuntimeException();
        }
        this.time = start;
        this.startTime = start;
        this.endTime = end;
        this.repeatTime = repeat;
    }

    /**
     * Method for getting the start time of notification (for repetitive tasks)
     * or a time of single notification (for a one-time task)
     * @return task start time
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Method for getting the end-time of notification (for repetitive tasks)
     * or a time of single notification (for a one-time task)
     * @return task end time
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Method for getting the time interval at which you need to repeat the notification
     * about the task (for repetitive tasks) or 0 (for a one-time task)
     * @return repeat interval
     */
    public int getRepeatInterval() {
        return repeatTime;
    }

    /**
     * A method for obtaining information about whether the task is repeated
     * @return repeat status
     */
    public boolean isRepeated() {
        if(repeatTime > 0)  return true;
        else                return false;
    }

    /**
     * The method returns a description of the task
     * @return task description
     */
    @Override
    public String toString() {
        if(!active)             return "Task \"" + title + "\"" + " is inactive";
        else if(repeatTime == 0)    return "Task \"" + title + "\"" + " at " + time;
        else                    return "Task \"" + title + "\"" + " from " + startTime +
                                        " to " + endTime + " every " + repeatTime + " seconds";
    }

    /**
     * The method returns the next notification, after a specified time period (but not including)
     * @param time time
     * @return time of next notification
     */
    public int nextTimeAfter(int time) {
        int nextTime = -1;

        if(time < 0)
            throw new RuntimeException();
        else if(active) {

            // for no-repeat task
            if(repeatTime == 0) {
                if(time < this.time) nextTime = this.time;
            }
            //for repeated task
            else {
                if(time < startTime) nextTime = startTime;
                else
                    for(int currentTime = startTime; currentTime <= endTime; currentTime += repeatTime)
                        if(currentTime > time) {
                            nextTime = currentTime;
                            break;
                        }
            }
        }
        return nextTime;
    }

}
