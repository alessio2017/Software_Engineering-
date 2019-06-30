/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankws;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface BankIFace {
    List<Integer> getOperationsByClientID(int ClientID);

    Operation getOperationDetailsByID(int OpID);
}
