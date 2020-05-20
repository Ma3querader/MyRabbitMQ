package com.mq.fanout.producer;

import com.mq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author: panyusheng
 * @Date: 2020/5/21 0:28
 * 订阅模式生产者
 * @Version 1.0
 */
public class Producer {
    /*
        1、1个生产者，多个消费者
        2、每一个消费者都有自己的一个队列
        3、生产者没有将消息直接发送到队列，而是发送到了交换机
        4、每个队列都要绑定到交换机
        5、生产者发送的消息，经过交换机，到达队列，实现，一个消息被多个消费者获取的目的
    */

    // 定义交换机
    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException {
        // 获取mq连接并创建频道
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 消息内容
        String message = "fuck the fanout";
        // 消息发送到交换机，而不是队列
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println("生产者生产了：" + message);

        // 释放资源
        channel.close();
        conn.close();
    }

}
