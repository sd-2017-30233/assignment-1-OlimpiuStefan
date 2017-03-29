package BusinessLogic;

import DAL.Account;
import DAL.AccountGateway;
import DAL.Client;
import DAL.ClientGateway;


import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Olimpiu on 3/26/2017.
 */
public class UsersBusiness {

    public void createClient(String name, int cnp, String address)
    {
        ClientGateway clientGateway= new ClientGateway();
        clientGateway.create(name,cnp,address);
    }

    public void updateClient(int idCardNumber, String name, int cnp, String address)
    {
        ClientGateway clientGateway= new ClientGateway();
        clientGateway.update(idCardNumber,name,cnp,address);
    }

    public void deleteClient(int idCardNumber)
    {
        ClientGateway clientGateway= new ClientGateway();
        clientGateway.delete(idCardNumber);
    }

    public DefaultTableModel listAllClientInfo()
    {
        ClientGateway clientGateway = new ClientGateway();
        ArrayList<Client> clientList = clientGateway.findAll();
        try {
            return buildClientTableModel(clientList);
        }
        catch (SQLException se)
        {se.printStackTrace();}
        return null;
    }

    public void createAccount(int idCardNumber, String type, int amount, String creationDate)
    {
        AccountGateway accountGateway= new AccountGateway();
        accountGateway.create(idCardNumber,type, amount, creationDate);
    }

    public void updateAccount(int idNumber, int idCardNumber, String type, int amount, Date creationDate)
    {
        AccountGateway accountGateway= new AccountGateway();
        accountGateway.update(idNumber,idCardNumber,type, amount,creationDate);
    }

    public void deleteAccount(int idNumber)
    {
        AccountGateway accountGateway= new AccountGateway();
        accountGateway.delete(idNumber);
    }


    public DefaultTableModel listAllAccountInfo()
    {
        AccountGateway accountGateway = new AccountGateway();
        ArrayList<Account> accountList = accountGateway.findAll();
        try {
            return buildAccountTableModel(accountList);
        }
        catch (SQLException se)
        {se.printStackTrace();}
        return null;
    }

    public static DefaultTableModel buildClientTableModel(ArrayList<Client> list)
            throws SQLException {

        String[] columnNames = {"ID",
                "Name",
                "CNP",
                "Address"
        };

        Object[][] array = new Object[list.size()][];
        int i = 0;
        for (Client c : list)
        {
            array[i] = new Object[4];
            array[i][0] = c.getIdCardNumber();
            array[i][1] = c.getName();
            array[i][2] = c.getCnp();
            array[i][3] = c.getAddress();

            i++;
        }
        return new DefaultTableModel(array, columnNames);
    }
    public static DefaultTableModel buildAccountTableModel(ArrayList<Account> list)
            throws SQLException {

        String[] columnNames = {"ID Number",
                "ID Card Number",
                "Type",
                "Amount",
                "Creation Date"};

        Object[][] array = new Object[list.size()][];
        int i = 0;
        for (Account c : list)
        {
            array[i] = new Object[5];
            array[i][0] = c.getIdNumber();
            array[i][1] = c.getIdCardNumber();
            array[i][2] = c.getType();
            array[i][3] = c.getAmount();
            array[i][4] = c.getCreationDate();
            i++;
        }
        return new DefaultTableModel(array, columnNames);
    }
    public int transferMoney(int acc1, int acc2, int amount){
        try {
            PrintWriter sender = new PrintWriter( "Account"+acc1+".txt");
            PrintWriter receiver = new PrintWriter("Account"+acc2+".txt");

            AccountGateway accountGateway = new AccountGateway();
            Account senderAccount = accountGateway.findById(acc1);
            Account receiverAccount = accountGateway.findById(acc2);
            if (senderAccount.getAmount() <= amount) {
                return -1;
            } else {
                accountGateway.updateSum(acc1, senderAccount.getAmount() - amount);
                accountGateway.updateSum(acc2, receiverAccount.getAmount() + amount);
                sender.println("Soldul contului cu ID = "+acc1+" a fost debitat cu suma de "+amount+" lei. ");
                receiver.println("Soldul contului cu ID = "+acc2+" a crescut cu suma de "+amount+" lei. ");
                sender.close();
                receiver.close();
                return 1;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    public static void openUserReport(String name)
    {
        try {

            PrintWriter user = new PrintWriter(new FileOutputStream(name+".txt", true));
            user.println("La data de "+new Date()+" user-ul "+ name+" s-a logat.");
            user.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void closeUserReport(String name)
    {
        try {

            PrintWriter user = new PrintWriter(new FileOutputStream(name+".txt", true));
            user.println("La data de "+new Date()+" user-ul "+ name+" s-a delogat.");
            user.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    public static void addUserOperationToReport(String name,String operation,boolean client,String identification)
    {
        try {

            PrintWriter user = new PrintWriter(new FileOutputStream(name+".txt", true));
            if (operation.equals("create"))
            {
                if (client==true)
                {
                    user.println("La data de " + new Date() + " user-ul " + name + " a creat un client nou pe numele"+
                            " = " +identification);
                    user.close();
                }
                if (client==false)
                {
                    user.println("La data de " + new Date() + " user-ul " + name + " a creat un cont nou pentru clientul"+
                            "cu id = " +identification);
                    user.close();
                }
            }
            if (client==true) {
                user.println("La data de " + new Date() + " user-ul " + name + " a facut operatia de "+operation+ "asupra clientului cu " +
                        "id = " +identification);
                user.close();
            }
            else if (client==false)
            {
                user.println("La data de " + new Date() + " user-ul " + name + " a facut operatia de "+operation+ "asupra account cu" +
                        "id = " +identification);
                user.close();
            }

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}
