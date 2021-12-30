package com.yd.scala.mqtt.hello;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * 发布端
 *
 * @author created by Zeb-D on 2019-08-09 17:58
 */
public class PublishSample {
    public static void main(String[] args) {
//        String topic = "cloud/token/in/34d197868fa54c94";
        String topic = "cloud/token/out/vdevo155497436794050";
        int qos = 1;
        String broker = "ssl://m1-cn.wgine.com:8883";
        String userName = "cloud_3729d394c3c537f830f07b8e04c6e8c3_1";
        String password = "4d7753f255767cae4e59e1c36d41548c";
        String clientId = "cloud_1e880e29b2be9c085fbca78595fb3ed4";
        // 内存存储
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            // 创建客户端
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            // 创建链接参数
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(true);
            // 设置连接的用户名
            connOpts.setUserName(userName);
            connOpts.setPassword(password.toCharArray());
            // 建立连接
            sampleClient.connect(connOpts);
            // 创建消息
            MqttMessage message = new MqttMessage(MessageCreator.createPayload().getBytes("UTF-8"));
            // 设置消息的服务质量
            message.setQos(qos);
            // 发布消息
            sampleClient.publish(topic, message);

            // 断开连接
//            sampleClient.disconnect();
            // 关闭客户端
//            sampleClient.close();
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
