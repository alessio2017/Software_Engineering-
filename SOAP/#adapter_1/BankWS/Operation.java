package bankws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlType(propOrder = {"op_code", "client_code", "amount", "description"})
public class Operation {
    private int op_code, client_code, amount;
    private String description;
    private LocalDate op_date;

    public Operation() {
    }

    Operation(int op_code, int client_code, String op_date, int amount, String description) {
        this.op_code = op_code;
        this.client_code = client_code;
        this.amount = amount;
        this.description = description;
        this.op_date = LocalDate.parse(op_date);
    }

    public int getOp_code() {
        return op_code;
    }

    @XmlElement(name = "Operation_Code", required = true)
    public void setOp_code(int op_code) {
        this.op_code = op_code;
    }

    public int getClient_code() {
        return client_code;
    }

    @XmlElement(name = "Client_Code", required = true)
    public void setClient_code(int client_code) {
        this.client_code = client_code;
    }

    public int getAmount() {
        return amount;
    }

    @XmlElement(name = "Amount", required = true)
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(name = "Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getOp_date() {
        return op_date;
    }

    @XmlElement(name = "Operation_Date", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    public void setOp_date(String op_date) {
        this.op_date = LocalDate.parse(op_date);
    }
}
