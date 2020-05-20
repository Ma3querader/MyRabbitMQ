package com.mq.normal.consumer;

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

    private static final String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取mq连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建频道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        // 定义消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("消费者[y]消费消息：" + message);
            Thread.sleep(10);
            // 返回确认状态，注释掉表示使用自动确认模式
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

}
