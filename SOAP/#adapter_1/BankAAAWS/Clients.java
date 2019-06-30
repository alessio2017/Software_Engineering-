package bankaaaws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="Clients")
public class Clients {
    private List<Client> clients;
    public Clients(){}

    @XmlElement(name="Client")
    public void setClients(List<Client> clients){
        this.clients=clients;
    }
}
