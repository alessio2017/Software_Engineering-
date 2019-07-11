/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.sapienza.softeng.bankws;
import javax.xml.ws.Endpoint;
/**
 *
 * @author biar
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException{
        // TODO code application logic here
        BankImpl implementor = new BankImpl();
        String address = "http://127.0.0.1:8081/BankInterface"; //era 8080 cambiata perchè ho due server che startano insieme
        Endpoint.publish(address, implementor);
        while(true) {
            Thread.sleep(60 * 1000);
            
            System.out.println("ciao");
            
        }
        
    }
}
