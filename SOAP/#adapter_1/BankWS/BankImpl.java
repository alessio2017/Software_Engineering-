/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankws;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@WebService(endpointInterface = "bankws.BankIFace")
public class BankImpl implements BankIFace {

    private final Map<Integer, Operation> operationDB = new TreeMap<Integer, Operation>();

    BankImpl() {
        operationDB.put(1, new Operation(1, 1, "2019-03-22", 130, "cena al ristorante"));
        operationDB.put(2, new Operation(2, 1, "2019-03-19", 30, "benzina autostrada"));
        operationDB.put(3, new Operation(3, 2, "2019-03-18", 1400, "riparazione motorino"));
        operationDB.put(4, new Operation(4, 2, "2019-03-17", 600, "festa"));
        operationDB.put(5, new Operation(5, 3, "2019-03-17", 45, "benzina autostrada"));
    }


    @Override
    public ArrayList<Integer> getOperationsByClientID(int ClientID) {
        ArrayList<Integer> result = new ArrayList();
        Iterator it = operationDB.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Operation aux = (Operation) pair.getValue();
            if (aux.getClient_code() == ClientID) result.add(aux.getOp_code());
        }
        return result;
    }

    @Override
    public Operation getOperationDetailsByID(int OpID) {
        return operationDB.get(OpID);
    }
}
