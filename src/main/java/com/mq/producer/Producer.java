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

    private static final String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取mq连接
        Connection conn = ConnectionUtil.getConnection();
        // 创建频道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        for (int i = 0; i < 100; i++) {
            // 消息内容
            String message = "龙骨" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" 生产者生产消息：" + message);

            Thread.sleep(i * 10);
        }

        // 关闭资源
        channel.close();
        conn.close();
    }

}
