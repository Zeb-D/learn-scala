package com.yd.scala.mqtt.hello;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

/**
 * 订阅端
 */
public class SubscribeSample2 {

    public static void main(String[] args) throws MqttException {
        String topic = "cloud/token/in/8cddd8115c7e8d0fd8253a9ab6a2a6fa";
        int qos = 1;
        String broker = "tcp://m2-cn.wgine.com:1883";
        String userName = "cloud_8cddd8115c7e8d0fd8253a9ab6a2a6fa";
        String password = "1893f705127e974fb43bacad3c2f6183";
        String clientId = "cloud_fe49c835798058ab03c9d9213ab1c513";
        try {
            // host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            MqttClient client = new MqttClient(broker, clientId, new MemoryPersistence());
            // MQTT的连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(userName);
            // 设置连接的密码
            options.setPassword(password.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调函数
            client.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost");
                }

                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("topic:" + topic);
                    System.out.println("Qos:" + message.getQos());
                    System.out.println("message:" + new String(message.getPayload(), StandardCharsets.UTF_8));
                    //输出 设备上报信息,解析payload,再输出payload中data
                    System.out.println("time:" + System.currentTimeMillis()
                            + " message content:" + MessageReceive.recevieData(MessageReceive.receviePayload(new String(message.getPayload(), StandardCharsets.UTF_8))));

                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }

            });
            client.connect(options);
            client.subscribe(topic, qos);
//            while (true){
//                try{
//                    client.connect(options);
//                    client.subscribe(topic, qos);
//                }catch (MqttException me) {
//                    System.out.println("reason " + me.getReasonCode());
//                    System.out.println("msg " + me.getMessage());
//                    System.out.println("loc " + me.getLocalizedMessage());
//                    System.out.println("cause " + me.getCause());
//                    System.out.println("excep " + me);
//                    me.printStackTrace();
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Thread.sleep(100);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}