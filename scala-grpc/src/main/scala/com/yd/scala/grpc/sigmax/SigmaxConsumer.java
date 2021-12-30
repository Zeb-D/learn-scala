package com.yd.scala.grpc.sigmax;

import apiserver.ApiServerGrpc;
import apiserver.Apiserver;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuya.grpc.client.annotation.GrpcCallMethodAnnotation;
import com.tuya.grpc.client.consumer.AbstractConsumer;
import io.grpc.Channel;
import io.grpc.ServiceDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SigmaxConsumer extends AbstractConsumer<ApiServerGrpc.ApiServerBlockingStub> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SigmaxConsumer.class);

    public SigmaxConsumer(String serverAppName) {
        super(serverAppName);
    }

    @GrpcCallMethodAnnotation
    public boolean addJob(String clientId, long expireTime) {
        JSONObject object = new JSONObject();
        object.put("clientId", clientId);
        KafkaMqData kickConnect = new KafkaMqData<>(object);
        Apiserver.JobActionMQ mq = Apiserver.JobActionMQ.newBuilder()
                .setTopic("mqtt")
                .setMessage(JSON.toJSONString(kickConnect))
                .build();
        Apiserver.JobAction jobAction = Apiserver.JobAction.newBuilder().setMode("MQ").setMq(mq).build();

        Apiserver.Job job = Apiserver.Job.newBuilder()
                .setUuid("123456")
                .setType(1).setAction(jobAction)
                .setFirstTrigTime(System.currentTimeMillis() / 1000 + expireTime)
                .setBizType("grpc").setRunMode(1)
                .setRetries(1)//重复执行次数
                .build();

        Apiserver.JobRequest requestMsg = Apiserver.JobRequest.newBuilder().setJob(job).build();
        ApiServerGrpc.ApiServerBlockingStub stub = getStub();
        try {
            Apiserver.APIResponse replyMsg = stub
                    .withDeadlineAfter(1000, TimeUnit.MILLISECONDS).addJob(requestMsg);
            LOGGER.info("sigmax mqtt clientId addJob get response:" + replyMsg.toString());
            return replyMsg.getSuccess();
        } catch (Exception e) {
            LOGGER.warn("sigmax mqtt clientId addJob error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public ApiServerGrpc.ApiServerBlockingStub initStub(Channel channel) {
        ApiServerGrpc.ApiServerBlockingStub stub = ApiServerGrpc.newBlockingStub(channel);
        return stub;
    }

    @Override
    public ServiceDescriptor getServiceDescriptor() {
        return ApiServerGrpc.getServiceDescriptor();
    }
}