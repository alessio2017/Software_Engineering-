package bankaaaws;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface BankAAAWS {
    List<Client> getClients();
}
