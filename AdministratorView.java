package Presentation;
import BusinessLogic.AdminBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


/**
 * Created by Olimpiu on 3/26/2017.
 */
public class AdministratorView {
    private JFrame frame;
    private JTable table;
    private JTextField id;
    private JTextField username;
    private JTextField password;
    private JTextField role;
    private JButton btnLogout;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnCreate;
    private JButton btnListEmployees;
    AdminBusiness admin;
    public AdministratorView(){
        admin = new AdminBusiness();
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 328);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblId = new JLabel("Employee ID");
        lblId.setBounds(30, 34, 82, 14);
        panel.add(lblId);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(30, 71, 82, 14);
        panel.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(30, 112, 82, 14);
        panel.add(lblPassword);

        id = new JTextField();
        id.setBounds(122, 31, 86, 20);
        panel.add(id);
        id.setColumns(10);

        username = new JTextField();
        username.setBounds(122, 68, 86, 20);
        panel.add(username);
        username.setColumns(10);

        password = new JTextField();
        password.setBounds(122, 109, 86, 20);
        panel.add(password);
        password.setColumns(10);

        role = new JTextField();
        role.setBounds(122, 154, 86, 20);
        panel.add(role);
        role.setColumns(10);


        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String idToUpdate=id.getText();
                String user=username.getText();
                String pass=password.getText();
                if (!user.equals("")&& !pass.equals("")&& !idToUpdate.equals("")){
                    int id= Integer.parseInt(idToUpdate);
                    admin.update(id,user,pass);
                }
                System.out.println("updated");
            }
        });
        btnUpdate.setBounds(218, 67, 89, 23);
        panel.add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String idToDelete=id.getText();
                if (!idToDelete.equals("")) {
                    int id= Integer.parseInt(idToDelete);
                    admin.delete(id);
                }
                System.out.println("deleted");
            }
        });
        btnDelete.setBounds(218, 108, 89, 23);
        panel.add(btnDelete);

        btnCreate = new JButton("Create");
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String user=username.getText();
                String pass=password.getText();
                String r=role.getText();
                if (!r.equals("admin")||(!r.equals("user"))){
                    admin.create(user, pass, r);
                    System.out.println("created");
                }
                else {
                    JOptionPane.showMessageDialog(frame,
                            "Puteti introduce doar admin sau user pt rol","Eroare",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnCreate.setBounds(218, 30, 89, 23);
        panel.add(btnCreate);

        btnListEmployees = new JButton("List");
        btnListEmployees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                table =new JTable(admin.listAll());
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
                System.out.println("listedEmployees");
            }
        });
        btnListEmployees.setBounds(318, 30,89, 23);
        panel.add(btnListEmployees);

        JLabel lblRole = new JLabel("Role");
        lblRole.setBounds(30, 157, 46, 14);
        panel.add(lblRole);

        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
                new LoginView();
            }
        });
        btnLogout.setBounds(318, 67,89, 23);
        panel.add(btnLogout);

        JButton btnReport = new JButton("Report");
        btnReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String user=username.getText();
                if (!user.equals("")) {
                    JOptionPane.showMessageDialog(null, admin.showReport(user));
                }
            }
        });
        btnReport.setBounds(318, 108,89, 23);
        panel.add(btnReport);



        frame.setVisible(true);
    }


}

