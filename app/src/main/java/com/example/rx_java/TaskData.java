package com.example.rx_java;

public class TaskData {
    String title;
    boolean isComplete;
    int priority;

    public TaskData(String title, boolean isComplete, int priority) {
        this.title = title;
        this.isComplete = isComplete;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
