/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientjms;

import javax.jms.Connection;
import java.util.Properties;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author biar
 */
public class ClientOrdini {
    private final static String target = "Matteo";
    
    public static void main(String[] args) throws NamingException, JMSException {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://127.0.0.1:61616");
        
        Context jndiContext = new InitialContext(props);
        
        ConnectionFactory connectionFactory = (ConnectionFactory)jndiContext.lookup("TopicConnectionFactory");
        Destination destinationOrdini = (Destination)jndiContext.lookup("dynamicTopics/Ordini");
        
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumerOrdini = session.createConsumer(destinationOrdini);
        
        //implementing synchronous communication primitive
        Message response;
        connection.start();
        
        while(true){
            response = consumerOrdini.receive();
            if(response != null){
                processResponse(response);
            }
        }   
    }
    
    private static void processResponse(Message response) {
        try {
            TextMessage txt = (TextMessage) response;
            if(txt.getStringProperty("Utente").equals(target) && txt.getObjectProperty("Status")!=null && txt.getStringProperty("Status").equals("true")){
                System.out.println("Complimenti! Bid Accepted");
            } else
                System.out.println("Not accepted... I'm sorry ;(");
        } catch(JMSException e){
            Logger.getLogger(ClientOrdini.class.getName()).log(Level.SEVERE, null,e);
        }
                
    }
    
}
