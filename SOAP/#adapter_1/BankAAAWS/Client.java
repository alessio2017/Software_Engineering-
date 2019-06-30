package bankaaaws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id","name", "surname"} )
public class Client {
    private String name,surname;
    private int id;
    public Client(){}

    Client(int id, String name, String surname) {
        this.name = name;
        this.id=id;
        this.surname = surname;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @XmlElement(name="Name",required = true)
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name="Id",required = true)
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name="Surname",required = true)
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
