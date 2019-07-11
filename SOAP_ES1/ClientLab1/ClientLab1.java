/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclient.clientlab1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author biar
 */
public class ClientLab1 {

    /**
     * @param args the command line arguments
     */
    
    
    //prima chiamo il metodo creato dal mio server, in questo modo ho tutti gli id che poi passerò ai metodi di mecella
    
    public static void main(String[] args) {
        
        List<String> id=null;
        Map<String,String> idnome= new TreeMap<String,String>();
        Map<Integer, List<Integer>> op = new TreeMap<Integer,List<Integer>>();
       
        
        try { // Call Web Service Operation
            com.mycompany.serverc.BImplService service = new com.mycompany.serverc.BImplService();
            com.mycompany.serverc.BIFace port = service.getBImplPort();
            // TODO process result here
            id = port.getClients();
            System.out.println("Id = "+id);
           for(int i = 0; i<id.size();i++){
               String hh=id.get(i);
               
               for(int z = 0; z<hh.length(); z++){
                   if(hh.charAt(z)==','){
                       idnome.put(hh.substring(0,z),hh.substring(z+1,hh.length()));
                       break;
                   }
           }
               
           }
           System.out.println("Mappa nomi + id"+idnome);
           
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

        
        
        
        //metodi creati dal server di mecella
        
        //per ogni id mi restituisce tutti gli id bancari eseguiti da una persona.
        try { // Call Web Service Operation
            it.sapienza.softeng.bankws.BankImplService service = new it.sapienza.softeng.bankws.BankImplService();
            it.sapienza.softeng.bankws.BankIFace port = service.getBankImplPort();
            // TODO initialize WS operation arguments here
            
            String keys [] =  idnome.keySet().toArray(new String[0]);
            
            for(int i=0;i<keys.length;i++){
                int arg = Integer.parseInt(keys[i]);
                
                
                java.util.List<java.lang.String> result = port.getOperationsByClientID(arg);
                List<Integer> idb = new ArrayList<Integer>();
               
                for(int j=0;j<result.size();j++){
                    String hh = result.get(j);
             
                    for(int z = 0; z<hh.length(); z++){
                        
                        if(hh.charAt(z)==','){
                            
                            idb.add(Integer.parseInt(hh.substring(1,z)));
                            
                            
                        break;
                        }
                    }
                }
                op.put(arg, idb);
                System.out.println(op);
                //System.out.println("Risultati da ID cliente = "+result);
            }
            
            // TODO process result here
            
        } catch (Exception ex) {
           
            // TODO handle custom exceptions here
        }
        
        
      
        
        
        try { // Call Web Service Operation
            it.sapienza.softeng.bankws.BankImplService service = new it.sapienza.softeng.bankws.BankImplService();
            it.sapienza.softeng.bankws.BankIFace port = service.getBankImplPort();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            List<String> nome = new ArrayList<String>();
            Integer keys [] =  op.keySet().toArray(new Integer[0]);
            for(int i=0;i<keys.length;i++){
                ArrayList<Integer> ids=(ArrayList<Integer>) op.get(keys[i]);
                
                for(int j=0;j<ids.size();j++){
                    
                    int k=ids.get(j);
                    java.lang.String result = port.getOperationDetailsByID(k);
                    List<String> m = new ArrayList<String>();
                    int v=0;
                    for(int z=0;z<result.length();z++){
                        
                        if(result.charAt(z)==',' || result.charAt(z)==']'){
                            
                            m.add(result.substring(v,z));
                            v=z+1;
                        }
                    }
                    
                    if(m.get(4).equals("benzina autostrada")){
                        
                        
                        
                        if(nome.contains(idnome.get(keys[i]+""))==false){
                            nome.add(idnome.get(keys[i]+""));}
                        else continue;
                    }
                    
                }
            }
            System.out.println(nome);
        } catch (Exception ex) {
            
            // TODO handle custom exceptions here
        }
        
        
        
    }
    
}
