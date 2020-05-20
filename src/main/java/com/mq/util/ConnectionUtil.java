package com.mq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @Author: panyusheng
 * @Date: 2020/5/20 16:25
 * MQ连接工具类
 * @Version 1.0
 */
public class ConnectionUtil {

    public static Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("testHost");
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection conn = null;
        try {
            conn = factory.newConnection();
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
