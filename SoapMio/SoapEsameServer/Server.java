/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclient.soapesame;

import javax.xml.ws.Endpoint;

/**
 *
 * @author biar
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        ProfessorImpl implementor = new ProfessorImpl();
        String address = "http://localhost:8080/ProfessorInterface";
        Endpoint.publish(address, implementor);
        while(true) {
            Thread.sleep(60 * 1000);
        }
        //System.exit(0);
    }
}
