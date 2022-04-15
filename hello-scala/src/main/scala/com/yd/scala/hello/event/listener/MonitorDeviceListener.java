package com.yd.scala.hello.event.listener;

import com.alibaba.fastjson.JSONObject;
import com.google.common.eventbus.Subscribe;
import com.yd.scala.hello.event.EventBusListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EventBusListener
public class MonitorDeviceListener {

    @Subscribe
    public void handleMonitor(DeviceEvent deviceEvent) {
        System.out.println("MonitorDeviceListener--" + JSONObject.toJSONString(deviceEvent));
    }

}