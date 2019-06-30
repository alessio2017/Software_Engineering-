/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclient.soapesameclient;

import javax.jms.Message;
import javax.jms.MessageListener;
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
public class Client implements MessageListener{
    static Session sessionQuotaz;
    static Session sessionOrdini;
    static MessageProducer producerOrdini;
    static MessageConsumer consumerQuotaz;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JMSException, NamingException {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://127.0.0.1:61616");
        
        Context jndiContext = new InitialContext(props);
            
        ConnectionFactory connectionFactory = (ConnectionFactory)jndiContext.lookup("TopicConnectionFactory");
        Connection connection = connectionFactory.createConnection();   

        Destination destinationQuotaz = (Destination)jndiContext.lookup("dynamicTopics/professors");
        sessionQuotaz = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        consumerQuotaz = sessionQuotaz.createConsumer(destinationQuotaz);
            
        Client c = new Client();
        consumerQuotaz.setMessageListener(c);
        connection.start();
    }

    @Override
    public void onMessage(Message msg) {
        try{
            TextMessage txt = (TextMessage) msg;
            String id = txt.getStringProperty("id");
            Float ranking = txt.getFloatProperty("ranking"); 
            try { // Call Web Service Operation
                com.myclient.soapesame.ProfessorImplService service = new com.myclient.soapesame.ProfessorImplService();
                com.myclient.soapesame.ProfessorInterface port = service.getProfessorImplPort();
                
                com.myclient.soapesame.Professor result = port.getDetails(id);
                System.out.println("Ricevuto id = "+ id + " con ranking "+ ranking + " ... bravo "+result.getName()+" "+result.getSurname());
                }catch (Exception ex) {
                        // TODO handle custom exceptions here
                }
        }catch(JMSException e){
            
        }
    }
}
