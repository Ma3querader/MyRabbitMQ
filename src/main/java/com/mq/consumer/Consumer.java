package com.mq.consumer;

import com.mq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

/**
 * @Author: panyusheng
 * @Date: 2020/5/20 16:45
 * 消费者
 * @Version 1.0
 */
public class Consumer {

    private static final String QUEUE_NAME = "q_test_1";

    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取mq连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建频道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("消费者获取消息：" + message);
        }
    }

}
