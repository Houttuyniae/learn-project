package com.github.houttuyniae.rocketmqorderexample.producer;

import com.github.houttuyniae.rocketmqorderexample.config.RocketMQConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author:zhangd
 * @date:2020/5/8 15:07
 */
//@SpringBootConfiguration
public class OrderProducer {

    @Autowired
    private RocketMQConfig rocketMQConfig;

    @Bean(initMethod = "start",destroyMethod = "shutdown")
    public MQProducer getMQProducer() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        DefaultMQProducer  producer = new DefaultMQProducer(
                rocketMQConfig.getNameServerAddr(),
                rocketMQConfig.getProducer().getGroup()
        );
        for (int i = 0; i < 100; i++) {
            int orderId = i % 5;
            Message msg = new Message(rocketMQConfig.getProducer().getTopic(), "*", "KEY" + i,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg, (mqs, msg1, arg) -> mqs.get(1), orderId);
            System.out.printf("%s%n", sendResult);
        }
        return producer;
    }
}
