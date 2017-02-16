package com.simpledeveloper.eventbasedapp;


public class NotifyActivityEvent {

    private long id;
    private boolean isActionTriggered;

    public NotifyActivityEvent(long id, boolean isActionTriggered) {
        this.id = id;
        this.isActionTriggered = isActionTriggered;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActionTriggered() {
        return isActionTriggered;
    }

    public void setActionTriggered(boolean actionTriggered) {
        isActionTriggered = actionTriggered;
    }
}
