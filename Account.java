package DAL;

import java.util.Date;

/**
 * Created by Olimpiu on 3/20/2017.
 */
public class Account {
    private int idNumber;
    private int idCardNumber;
    private String type;
    private int amount;
    private String creationDate;

    public Account(){}

    public Account(int idNumber, int idCardNumber, String type, int amount, String creationDate) {
        this.idNumber = idNumber;
        this.idCardNumber = idCardNumber;
        this.type = type;
        this.amount = amount;
        this.creationDate = creationDate;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(int idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}