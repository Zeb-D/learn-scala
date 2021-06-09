package com.yd.scala.hello.handler;

import org.springframework.context.ApplicationEvent;

/**
 * @author created by Zeb灬D on 2021-03-09 16:13
 */
public class Event extends ApplicationEvent {
    private String requestId;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public Event(Object source) {
        super(source);
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "requestId='" + requestId + '\'' +
                '}';
    }
}
