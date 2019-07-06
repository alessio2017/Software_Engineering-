package com.mycompany.rabbit_client;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;


public class Sender {
    
    
    private final static String QUEUE_NAME = "hello";
    
    public static void main(String[] argv) throws Exception {
        
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.49.81");
        while(true){
            try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()){
                //Il secondo parametro nella guida è messo a true-->true,false,false,null
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = ".....";
                
                channel.basicPublish("", QUEUE_NAME, 
                        MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
  }
}
