package workqueue;

/*
this is the new Send.java 
*/

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {

    private static final String TASK_QUEUE_NAME = "task_queue4234";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

            String[] message = {"...","......", "..", "............."};
            for(int i=0; i<4; i++){
                Thread.sleep(2000);
                channel.basicPublish("", TASK_QUEUE_NAME,MessageProperties.PERSISTENT_TEXT_PLAIN,message[i].getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }
        }
    }

}