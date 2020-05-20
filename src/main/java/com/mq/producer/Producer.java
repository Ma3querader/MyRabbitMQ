package com.mq.producer;

import com.mq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author: panyusheng
 * @Date: 2020/5/20 16:37
 * 生产者
 * @Version 1.0
 */
public class Producer {

    private static final String QUEUE_NAME = "q_test_1";

    public static void main(String[] args) throws IOException {
        // 获取mq连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建频道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        String message = "hello fucking mq";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("生产者发出消息：" + message);

        // 关闭资源
        channel.close();
        conn.close();
    }

}
