package com.github.houttuyniae.rocketmqorderexample.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author:zhangd
 * @date:2020/5/8 15:20
 */

@Configuration
@ConfigurationProperties(prefix = "rocketmq")
public class RocketMQConfig {

    private String nameServerAddr;
    private final RocketMQConfig.Producer producer = new RocketMQConfig.Producer();
    private final RocketMQConfig.Consumer consumer = new RocketMQConfig.Consumer();

    public static class Producer{

        private String group;

        private String topic;

        public String getGroup() {
            return group;
        }

        public Producer setGroup(String group) {
            this.group = group;
            return this;
        }

        public String getTopic() {
            return topic;
        }

        public Producer setTopic(String topic) {
            this.topic = topic;
            return this;
        }


    }


    public static class Consumer{

        private String group;

        private String topic;

        public String getGroup() {
            return group;
        }

        public Consumer setGroup(String group) {
            this.group = group;
            return this;
        }

        public String getTopic() {
            return topic;
        }

        public Consumer setTopic(String topic) {
            this.topic = topic;
            return this;
        }
    }

    public String getNameServerAddr() {
        return nameServerAddr;
    }

    public RocketMQConfig setNameServerAddr(String nameServerAddr) {
        this.nameServerAddr = nameServerAddr;
        return this;
    }

    public Producer getProducer() {
        return producer;
    }

    public Consumer getConsumer() {
        return consumer;
    }
}
