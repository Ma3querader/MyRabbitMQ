package com.mq.topic.producer;

import com.mq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author: panyusheng
 * @Date: 2020/5/21 0:28
 * 主题模式生产者
 * @Version 1.0
 */
public class Producer {
    /*
        生产者申明一个topic类型交换机，然后发送消息到这个交换机指定路由键。
        消费者根据路由键匹配和某模式匹配。
    */

    // 定义交换机
    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException {
        // 获取mq连接并创建频道
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 消息内容
        String message = "Fucking...";
        // 消息发送到交换机，而不是队列
        channel.basicPublish(EXCHANGE_NAME, "routekey.1", null, message.getBytes());
        System.out.println("生产者生产了：" + message);

        // 释放资源
        channel.close();
        conn.close();
    }

}
