package DAL;

/**
 * Created by Olimpiu on 3/20/2017.
 */
import java.sql.*;
import java.util.ArrayList;

import Database.DbConn;
public class ClientGateway {
    /**
     * CRUD OPERATIONS
     */
    public ArrayList<Client> findAll() {
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            ArrayList<Client> clients = new ArrayList<Client>();
            String sql;
            sql = "SELECT idCardNumber,name,cnp,address FROM Client";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                int idCardNumber = rs.getInt("idCardNumber");
                int CNP = rs.getInt("CNP");
                String address = rs.getString("address");
                clients.add(new Client(idCardNumber,name,CNP,address));
                System.out.print("ID: " + idCardNumber);
                System.out.print(", Name: " + name);
                System.out.print(", CNP: " + CNP);
                System.out.println(", Address: " +address);
            }
            //rs.close();
            return clients;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return null;
    }

    public ResultSet findById(int id) {
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT idCardNumber,name,cnp,address FROM Client WHERE idCardNumber='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name
                String name = rs.getString("name");
                int idCardNumber = rs.getInt("idCardNumber");
                int CNP = rs.getInt("cnp");
                String address = rs.getString("address");

                //Display values
                System.out.print("ID: " + idCardNumber);
                System.out.print(", Name: " + name);
                System.out.print(", CNP: " + CNP);
                System.out.println(", Address: " +address);
            }

           // rs.close();
            return rs;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return null;
    }

    public void create(String name, int cnp, String address) {
        //dbConn=new dbConn();
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into Client (name, cnp, address) values ('"+name+"','"+cnp+"','"+address+"')";
            stmt.execute(sql);
        }catch (SQLException se) {
            se.printStackTrace();

        }
        DbConn.closeConnection(conn,stmt);
    }

    public void update(int idCardNumber, String name, int cnp, String address){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "update Client set name='" + name + "', cnp='" + cnp + "', address='" + address + "' where idCardNumber='" + idCardNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public void delete(int idCardNumber){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "delete FROM Client where idCardNumber='" + idCardNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public static void main(String[] args)
    {
        ClientGateway cl= new ClientGateway();
        //cl.create();
        //cl.update(1,"adaa",112,"add");
        cl.create("boss",13,"adresa1");
        //cl.delete(6);
        //cl.delete(7);
        cl.findAll();
    }
}
