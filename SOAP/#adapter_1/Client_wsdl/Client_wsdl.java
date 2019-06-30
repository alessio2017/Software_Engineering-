/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_wsdl;



import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Client_wsdl {
    public static void main(String[] args) throws Exception {
        String Description = "benzina autostrada";
        HashMap<Integer, String> clients_names = new HashMap<Integer, String>();
        List<Client> clients = getClients();
        Iterator<Client> client_it = clients.listIterator();
        while (client_it.hasNext()) {
            Client client = client_it.next();
            List<Integer> ops = getOperationsByClientID(client.getId());
            Iterator<Integer> op_it = ops.iterator();
            while (op_it.hasNext()) {
                int op_id = op_it.next();
                Operation op = getOperationDetailsByID(op_id);
                if (op.getDescription().equals(Description)) {
                    clients_names.put(client.getId(), client.getName() + " " + client.getSurname());
                }
            }
        }
        System.out.println("clients that have an operation called: " + Description + ": ");
        Iterator<Map.Entry<Integer, String>> it = clients_names.entrySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next().getValue());
        }
    }

    private static java.util.List<client_wsdl.Client> getClients() {
        client_wsdl.BankAAAWSImplService service = new client_wsdl.BankAAAWSImplService();
        client_wsdl.BankAAAWS port = service.getBankAAAWSImplPort();
        return port.getClients();
    }

    private static Operation getOperationDetailsByID(int arg0) {
        client_wsdl.BankImplService service = new client_wsdl.BankImplService();
        client_wsdl.BankIFace port = service.getBankImplPort();
        return port.getOperationDetailsByID(arg0);
    }

    private static java.util.List<java.lang.Integer> getOperationsByClientID(int arg0) {
        client_wsdl.BankImplService service = new client_wsdl.BankImplService();
        client_wsdl.BankIFace port = service.getBankImplPort();
        return port.getOperationsByClientID(arg0);
    }
        
        
        
    
    
    
        
}

    

    
