/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientjms;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author biar
 */
public class ClientQuotaz implements MessageListener {
    private final static String target = "Oracle";
    static Session sessionQuotaz;
    static Session sessionOrdini;
    static MessageProducer producerOrdini;
    static MessageConsumer consumerQuotaz;
     
    
    
    public static void main(String[] args) throws NamingException, JMSException {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://127.0.0.1:61616");
        
        Context jndiContext = new InitialContext(props);
        
        ConnectionFactory connectionFactory = (ConnectionFactory)jndiContext.lookup("TopicConnectionFactory");
        Connection connection = connectionFactory.createConnection();   
        
        Destination destinationQuotaz = (Destination)jndiContext.lookup("dynamicTopics/Quotazioni");
        sessionQuotaz = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        consumerQuotaz = sessionQuotaz.createConsumer(destinationQuotaz);
        
        Destination destinationOrdini = (Destination)jndiContext.lookup("dynamicTopics/Ordini");
        sessionOrdini = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producerOrdini = sessionOrdini.createProducer(destinationOrdini);
        
        //asynchronous communication primitive
        ClientQuotaz support = new ClientQuotaz();
        consumerQuotaz.setMessageListener(support);
        connection.start();
    }   
    
    @Override
    public void onMessage(Message msg){
         try {
            TextMessage txt = (TextMessage) msg;
            if(txt.getStringProperty("Nome").equals(target)){
               System.out.println("Found!");
               TextMessage bid = sessionQuotaz.createTextMessage();
               bid.setStringProperty("Utente", "Matteo");
               bid.setStringProperty("Nome", target);
               bid.setFloatProperty("Prezzo", 777);
               bid.setIntProperty("Quantita", 7);
               producerOrdini.send(bid);
            }
        } catch(JMSException e){
            Logger.getLogger(ClientOrdini.class.getName()).log(Level.SEVERE, null,e);
        }
    }
}
