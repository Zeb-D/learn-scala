package com.yd.scala.hello.event.listener;

import com.yd.scala.hello.event.AbstractEvent;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserEvent extends AbstractEvent implements Serializable {

    private static final long serialVersionUID = -4128475227007948414L;
    private String status;
    private String uid;
    private Integer eventType;
}
