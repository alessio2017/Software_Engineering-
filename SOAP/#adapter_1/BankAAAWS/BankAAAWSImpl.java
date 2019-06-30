package bankaaaws;

import javax.jws.WebService;
import java.util.*;

@WebService(endpointInterface = "bankaaaws.BankAAAWS")
public class BankAAAWSImpl implements BankAAAWS {

    private final Map<Integer, Client> clientDB = new TreeMap<Integer, Client>();

    public BankAAAWSImpl() {
        clientDB.put(1, new Client(1,"Massimo", "Mecella"));
        clientDB.put(2, new Client(2,"Miguel", "Ceriani"));
        clientDB.put(3, new Client(3,"Francesco", "Leotta"));
    }

    public List<Client> getClients(){
        List<Client> clients=new ArrayList<Client>();
        Iterator<Map.Entry<Integer,Client>> it=clientDB.entrySet().iterator();
        while(it.hasNext()){
            clients.add(it.next().getValue());
        }
        return clients;
    }
}
