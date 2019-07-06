package com.mycompany.rabbit_client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receiver {

    
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.49.81");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        
        
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
            
            try {
                doWork(message);
            } finally {
                System.out.println(" [x] Done");
                
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        boolean autoAck = false;

        
        channel.basicConsume(QUEUE_NAME, autoAck, deliverCallback, consumerTag -> { });
    }
    
    
    private static void doWork(String task) {
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                    System.out.println("---------DOING WORK----------");
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
  }
}
