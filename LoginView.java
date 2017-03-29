package Presentation;

import BusinessLogic.LoginBusiness;
import BusinessLogic.UsersBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Olimpiu on 3/26/2017.
 */
public class LoginView implements ActionListener {
    private JFrame frame;
    private JTextField textFieldUser;
    private JTextField textFieldPass;
    JButton btnLogin;
    public LoginView () {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 236, 239);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        textFieldUser = new JTextField();
        textFieldUser.setBounds(10, 11, 100, 20);
        panel.add(textFieldUser);
        textFieldUser.setColumns(10);

        textFieldPass = new JTextField();
        textFieldPass.setBounds(10, 42, 100, 20);
        panel.add(textFieldPass);
        textFieldPass.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(162, 14, 73, 14);
        panel.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(162, 45, 73, 14);
        panel.add(lblPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(10, 90, 89, 23);
        panel.add(btnLogin);

        btnLogin.addActionListener(this);
        btnLogin.setActionCommand("Open");
        frame.setVisible(true);
    }
        @Override
        public void actionPerformed (ActionEvent e)
        {
            String cmd = e.getActionCommand();

            if (textFieldUser.getText().equals("")) {
                btnLogin.setEnabled(false);
            } else {
                btnLogin.setEnabled(true);
            }
            String username = textFieldUser.getText();
            String password = textFieldPass.getText();
            int type = LoginBusiness.getUserType(username, password);
            if (cmd.equals("Open") && btnLogin.isEnabled())
                if (type == 0)
                    JOptionPane.showMessageDialog(frame, "Eroare");
                else if (type == 1) {
                    frame.dispose();
                    new AdministratorView();
                } else if (type == 2) {
                    frame.dispose();
                    UsersBusiness.openUserReport(username);
                    new UsersView(username);
                }
            btnLogin.setEnabled(true);
        }


    public static void main(String[] args)
    {
        LoginView login= new LoginView();
    }
}

