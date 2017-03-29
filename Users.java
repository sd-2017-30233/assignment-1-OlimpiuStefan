package DAL;

import Database.DbConn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Olimpiu on 3/20/2017.
 */
public class Users{
    private int id;
    private String username;
    private String password;

    public Users(){}

    public Users(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ResultSet findAll() {
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id,username,password FROM Users";
            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                String username = rs.getString("username");
//                int id = rs.getInt("id");
//                String password = rs.getString("password");
//
//                System.out.print("ID: " + id);
//                System.out.print(", userName: " + username);
//                System.out.print(", password: " + password);
//            }
            //rs.close();
            return rs;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return null;
    }


    public int findIdByUserAndPass(String username, String password)

    {
        Connection conn = DbConn.openConnection();
        Statement stmt =null;
        int result=0;
        String type="";
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT d.id\n" +
                    "    FROM Users d\n" +
                    "    WHERE d.username='"+username+"' and d.password='"+password+"';";
            ResultSet rs=stmt.executeQuery(sql);
            int id=0;
            while(rs.next())
            {
                //Retrieve by column name
                id = rs.getInt("id");
            }
            return id;
        } catch (SQLException se)
        {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return 0;
    }

    public int getTypeByUserAndPass(String username, String password) {
        Connection conn = DbConn.openConnection();
        Statement stmt =null;
        int result=0;
        String type="";
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT d.id, d.username, d.password, f.userType\n" +
                    "    FROM Users d, UsersRole f\n" +
                    "    WHERE d.id = f.userId and d.username='"+username+"' and d.password='"+password+"';";
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next())
            {
                //Retrieve by column name
                int id = rs.getInt("id");
                String user = rs.getString("username");
                String pass = rs.getString("password");
                type = rs.getString("userType");
                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Username: " + user);
                System.out.println(", Password: " +pass);
                System.out.println(", User Type: "+ type);
            }
            if (type.equals(""))
            {//nothing to do
            }
            else
            if (type.equals("admin") )
                result=1;
            else if (type.equals("user"))
                result=2;
            return result;
        } catch (SQLException se)
        {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
        return 0;
    }



    public void create(String username, String password) {
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into Users (username, password) values ('"+username+"','"+password+"')";
            stmt.execute(sql);
        }catch (SQLException se) {
            se.printStackTrace();

        }
        DbConn.closeConnection(conn,stmt);
    }

    public void update(int id, String username, String password){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "update Users set username='" + username + "', password='" + password + "' where id='" + id + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }

    public void delete(int id){
        Connection conn = DbConn.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "delete FROM Users where id='" + id + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DbConn.closeConnection(conn,stmt);
    }
}
