package DAL;

/**
 * Created by Olimpiu on 3/20/2017.
 */
import java.sql.*;
import Database.DbConn;

import java.util.ArrayList;
import java.util.Date;
public class AccountGateway {
    /**
     * CRUD OPERATIONS
     */
    public ArrayList<Account> findAll() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT idNumber,idCardNumber,type,amount,creationDate FROM Account";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String type = rs.getString("type");
                int idCardNumber = rs.getInt("idCardNumber");
                int idNumber = rs.getInt("idNumber");
                int amount = rs.getInt("amount");
                String creationDate  = rs.getString("creationDate");
                accounts.add(new Account(idNumber,idCardNumber,type,amount,creationDate));
                System.out.print("ID: " + idCardNumber);
                System.out.print(", idNumber: " + idNumber);
                System.out.print(", amount: " + amount);
                System.out.println(", type: " +type);
                System.out.println(", creationDate: " +creationDate);
            }
        // rs.close();
            return accounts;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return null;
    }

    public Account findById(int id) {
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        Account account = new Account();
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT idNumber,idCardNumber,type,amount,creationDate FROM Account WHERE idNumber='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String type = rs.getString("type");
                int idCardNumber = rs.getInt("idCardNumber");
                int idNumber = rs.getInt("idNumber");
                int amount = rs.getInt("amount");
                String creationDate  = rs.getString("creationDate");
                account = new Account(idNumber,idCardNumber,type,amount,creationDate);

                System.out.print("ID: " + idCardNumber);
                System.out.print(", idNumber: " + idNumber);
                System.out.print(", amount: " + amount);
                System.out.println(", type: " +type);
                System.out.println(", creationDate: " +creationDate);
            }

            // rs.close();
            return account;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return null;
    }

    public void create(int idCardNumber, String type, int amount, String creationDate) {
        //dbConn=new dbConn();
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into Account (idCardNumber,type,amount,creationDate) values ('"+idCardNumber+"','"+type+"','"+amount+"','"+creationDate+"')";
            stmt.execute(sql);
        }catch (SQLException se) {
            se.printStackTrace();

        }
        DbConn.closeConnection(conn,stmt);
    }

    public void update(int idNumber, int idCardNumber, String type, int amount, Date creationDate){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "update Account set idCardNumber='" + idCardNumber + "', type='" + type + "', amount='" + amount + "', creationDate='" +creationDate+ "' where idNumber='" + idNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public void updateSum(int idNumber, int amount){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "update Account set amount='" + amount + "' where idNumber='" + idNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public void delete(int idNumber){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "delete FROM Account where idNumber='" + idNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public static void main(String[] args)
    {
        AccountGateway acc= new AccountGateway();
        //cl.create();
        //cl.update(1,"adaa",112,"add");
       // acc.create(8,"spending",200,);
        //cl.delete(6);
        //cl.delete(7);
        //acc.findAll();
    }
}
