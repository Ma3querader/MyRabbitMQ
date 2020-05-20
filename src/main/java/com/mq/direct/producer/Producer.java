package com.mq.direct.producer;

import com.mq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author: panyusheng
 * @Date: 2020/5/21 0:28
 * 路由模式生产者
 * @Version 1.0
 */
public class Producer {
    /*
        生产者申明一个direct类型交换机，然后发送消息到这个交换机指定路由键。
        消费者指定消费这个交换机的这个路由键，即可接收到消息，其他消费者收不到。
    */

    // 定义交换机
    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException {
        // 获取mq连接并创建频道
        Connection conn = ConnectionUtil.getConnection();
        Channel channel = conn.createChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        // 消息内容
        String message = "删除...";
        // 消息发送到交换机，而不是队列
        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
//        System.out.println("生产者生产了：" + message);
        String msg = "插入...";
        // 消息发送到交换机，而不是队列
        channel.basicPublish(EXCHANGE_NAME, "insert", null, msg.getBytes());
        // 释放资源
        channel.close();
        conn.close();
    }

}
