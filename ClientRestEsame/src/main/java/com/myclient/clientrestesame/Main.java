/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclient.clientrestesame;
import java.io.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

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

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 *
 * @author biar
 */
public class Main implements MessageListener{
    private static Client client;
    static Session session;
    static MessageConsumer consumer;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NamingException, JMSException {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://127.0.0.1:61616");
       
        Context jndiContext = new InitialContext(props);
            
        ConnectionFactory connectionFactory = (ConnectionFactory)jndiContext.lookup("TopicConnectionFactory");
        Connection connection = connectionFactory.createConnection();   

        Destination destinationQuotaz = (Destination)jndiContext.lookup("dynamicTopics/professors");
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        consumer = session.createConsumer(destinationQuotaz);
        
        Main c = new Main();
        consumer.setMessageListener(c);
        connection.start();
        
    }
    
    @Override
    public void onMessage(Message msg) {
        try{
            TextMessage txt = (TextMessage) msg;
            String id = txt.getStringProperty("id");
            Float ranking = txt.getFloatProperty("ranking");
            String name = txt.getStringProperty("name");
            String surname = txt.getStringProperty("surname");
            client = ClientBuilder.newClient();
            Professor p = new Professor();
            p.setName(name); p.setSurname(surname);
            Response r = createStudentInCourse(p); //POST
            System.out.println(r.getStatus());
            client.close();
        }catch (JMSException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Response createStudentInCourse(Professor newProfessor){
        WebTarget webTarget = client.target("http://localhost:8080/course/professor");
        Invocation.Builder builder = webTarget.request("text/xml");
        return builder.post(Entity.entity(newProfessor, MediaType.TEXT_XML)); //for POST request
    }
}
