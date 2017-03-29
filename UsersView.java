package Presentation;

import BusinessLogic.UsersBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


/**
 * Created by Olimpiu on 3/27/2017.
 */
public class UsersView {
    private JFrame frame;
    private JTextField textFieldName;
    private JTextField textFieldIDCardNr;
    private JTextField textFieldCNP;
    private JTextField textFieldAddress;
    private JTextField textFieldIDNr;
    private JTextField textFieldType;
    private JTextField textFieldMoney;
    private JTextField textFieldIDAccount;
    private JButton btnUpdateClient;
    private JButton btnCreateClient;
    private JButton btnViewClient;
    private JButton btnCreateAccount;
    private JButton btnUpdateAccount;
    private JButton btnDeleteAccount;
    private JButton btnViewAccount;
    private JButton btnLogout;
    private UsersBusiness user;
    private String loggedInUser;

    public UsersView(String loggedInUser)
    {
        user = new UsersBusiness();
        this.loggedInUser=loggedInUser;
        frame = new JFrame();
        frame.setBounds(100, 100, 736, 246);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        frame.getContentPane().add(panel, BorderLayout.CENTER);
       // panel.setBackground(Color.getHSBColor(132,39,45));
        panel.setLayout(null);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(10, 34, 82, 14);
        panel.add(lblName);

        JLabel lbliDCardNr = new JLabel("ID Card Nr");
        lbliDCardNr.setBounds(10, 71, 82, 14);
        panel.add(lbliDCardNr);

        JLabel CNP = new JLabel("CNP");
        CNP.setBounds(10, 112, 82, 14);
        panel.add(CNP);

        textFieldName = new JTextField();
        textFieldName.setBounds(102, 31, 86, 20);
        panel.add(textFieldName);
        textFieldName.setColumns(10);

        textFieldIDCardNr = new JTextField();
        textFieldIDCardNr.setBounds(102, 68, 86, 20);
        panel.add(textFieldIDCardNr);
        textFieldIDCardNr.setColumns(10);

        textFieldCNP = new JTextField();
        textFieldCNP.setBounds(102, 109, 86, 20);
        panel.add(textFieldCNP);
        textFieldCNP.setColumns(10);

        btnUpdateClient = new JButton("Update");
        btnUpdateClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Updated Client");
                String idCardNr = textFieldIDCardNr.getText();
                String name = textFieldName.getText();
                String cnp = textFieldCNP.getText();
                String address= textFieldAddress.getText();
                if (!idCardNr.equals("") && !name.equals("") && !cnp.equals("") && !address.equals(""))
                {
                    int idCard = Integer.parseInt(idCardNr);
                    int codNumeric = Integer.parseInt(cnp);
                    user.updateClient(idCard,name,codNumeric,address);
                    UsersBusiness.addUserOperationToReport(loggedInUser,"update",true,idCardNr);
                }
                else
                    JOptionPane.showMessageDialog(frame,
                            "Nu ati completat bine","Eroare",
                            JOptionPane.WARNING_MESSAGE);
            }
        });
        btnUpdateClient.setBounds(198, 67, 89, 23);
        panel.add(btnUpdateClient);

        btnCreateClient = new JButton("Create");
        btnCreateClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Created Client");
                String name = textFieldName.getText();
                String cnp = textFieldCNP.getText();
                String address= textFieldAddress.getText();
                if (!name.equals("") && !cnp.equals("") && !address.equals(""))
                {
                    int codNumeric = Integer.parseInt(cnp);
                    user.createClient(name,codNumeric,address);
                    JOptionPane.showMessageDialog(frame,
                            "Client nou creat!");
                    UsersBusiness.addUserOperationToReport(loggedInUser,"create",true,name);

                }
                else
                    JOptionPane.showMessageDialog(frame,
                            "Nu ati completat bine","Eroare",
                            JOptionPane.WARNING_MESSAGE);
            }
        });
        btnCreateClient.setBounds(198, 30, 89, 23);
        panel.add(btnCreateClient);

        btnViewClient= new JButton("View");
        btnViewClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("View Client");
                JTable table =new JTable(user.listAllClientInfo());
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
            }
        });
        btnViewClient.setBounds(198, 108, 89, 23);
        panel.add(btnViewClient);

        JLabel lblRole = new JLabel("Address");
        lblRole.setBounds(10, 157, 60, 14);
        panel.add(lblRole);

        textFieldAddress = new JTextField();
        textFieldAddress.setBounds(102, 154, 86, 20);
        panel.add(textFieldAddress);
        textFieldAddress.setColumns(10);

        JLabel lblClientInformation = new JLabel("Client Information");
        lblClientInformation.setBounds(10, 9, 121, 14);
        panel.add(lblClientInformation);

        JLabel lblClientAccount = new JLabel("Client Account");
        lblClientAccount.setBounds(309, 9, 96, 14);
        panel.add(lblClientAccount);

        JLabel lblIdNr = new JLabel("Card number");
        lblIdNr.setBounds(309, 34, 90, 14);
        panel.add(lblIdNr);

        JLabel lblNewLabel = new JLabel("Type");
        lblNewLabel.setBounds(309, 71, 46, 14);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Money");
        lblNewLabel_1.setBounds(309, 112, 46, 14);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("ID Account");
        lblNewLabel_2.setBounds(309, 157, 96, 14);
        panel.add(lblNewLabel_2);

        textFieldIDNr = new JTextField();
        textFieldIDNr.setBounds(403, 31, 86, 20);
        panel.add(textFieldIDNr);
        textFieldIDNr.setColumns(10);

        textFieldType = new JTextField();
        textFieldType.setBounds(403, 68, 86, 20);
        panel.add(textFieldType);
        textFieldType.setColumns(10);

        textFieldMoney = new JTextField();
        textFieldMoney.setBounds(403, 109, 86, 20);
        panel.add(textFieldMoney);
        textFieldMoney.setColumns(10);


        textFieldIDAccount = new JTextField();
        textFieldIDAccount.setBounds(403, 154, 86, 20);
        panel.add(textFieldIDAccount);
        textFieldIDAccount.setColumns(10);

        btnCreateAccount = new JButton("Create");
        btnCreateAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Create Account");
                String idCardNumber = textFieldIDNr.getText();
                String type = textFieldType.getText();
                String amount= textFieldMoney.getText();
                if (!idCardNumber.equals("") && !type.equals("") && !amount.equals(""))
                {
                    int idCardNr = Integer.parseInt(idCardNumber);
                    int amountMoney = Integer.parseInt(amount);
                    user.createAccount(idCardNr,type,amountMoney,new Date().toString());
                    JOptionPane.showMessageDialog(frame,
                            "Cont nou creat pentru cardul cu ID = "+idCardNumber);
                    UsersBusiness.addUserOperationToReport(loggedInUser,"create",false,idCardNumber);
                }
                else
                    JOptionPane.showMessageDialog(frame,
                            "Completati campurile corespunzator!","Eroare",
                            JOptionPane.WARNING_MESSAGE);

            }
        });
        btnCreateAccount.setBounds(518, 30, 89, 23);
        panel.add(btnCreateAccount);

        btnUpdateAccount = new JButton("Update");
        btnUpdateAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Update Account");
                String idAccount = textFieldIDAccount.getText();
                String idCardNumber = textFieldIDNr.getText();
                String type = textFieldType.getText();
                String amount= textFieldMoney.getText();
                if (!idCardNumber.equals("") && !type.equals("") && !amount.equals("") && !idAccount.equals(""))
                {
                    int idCardNr = Integer.parseInt(idCardNumber);
                    int amountMoney = Integer.parseInt(amount);
                    int id = Integer.parseInt(idAccount);
                    user.updateAccount(id,idCardNr,type,amountMoney,new Date());
                    JOptionPane.showMessageDialog(frame,
                            "Contul cu ID = "+id+ " a fost actualizat!");
                    UsersBusiness.addUserOperationToReport(loggedInUser,"update",false,idAccount);
                }
                else
                    JOptionPane.showMessageDialog(frame,
                            "Completati campurile corespunzator!","Eroare",
                            JOptionPane.WARNING_MESSAGE);

            }
        });
        btnUpdateAccount.setBounds(518, 67, 89, 23);
        panel.add(btnUpdateAccount);

        btnDeleteAccount = new JButton("Delete");
        btnDeleteAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Delete Account");
                String idAccount = textFieldIDAccount.getText();
                if (!idAccount.equals(""))
                {
                    int id = Integer.parseInt(idAccount);
                    user.deleteAccount(id);
                    UsersBusiness.addUserOperationToReport(loggedInUser,"delete",false,idAccount);
                    JOptionPane.showMessageDialog(frame,
                            "Contul cu ID = "+id+ " a fost sters!");
                }
                else
                    JOptionPane.showMessageDialog(frame,
                            "Completati campurile corespunzator!","Eroare",
                            JOptionPane.WARNING_MESSAGE);
            }
        });
        btnDeleteAccount.setBounds(518, 108, 89, 23);
        panel.add(btnDeleteAccount);

        btnViewAccount = new JButton("View");
        btnViewAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("View Account");
                JTable table =new JTable(user.listAllAccountInfo());
                JOptionPane.showMessageDialog(null, new JScrollPane(table));

            }
        });
        btnViewAccount.setBounds(625, 30, 89, 23);
        panel.add(btnViewAccount);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(625, 67, 89, 23);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
                UsersBusiness.closeUserReport(loggedInUser);
                new LoginView();

            }
        });
        panel.add(btnLogout);

        JButton btnTransfer = new JButton("Transfer");
        btnTransfer.setBounds(625, 108, 89, 23);
        btnTransfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new TransferView();
            }
        });
        panel.add(btnTransfer);

        frame.setVisible(true);
    }

    private class TransferView {
        private JFrame frame;
        private JTextField textField;
        private JTextField textField_1;
        private JTextField textField_2;
        private JFrame frameTransfer;
        public TransferView() {
            frame = new JFrame();
            frame.setBounds(100, 100, 308, 208);

            JPanel panel = new JPanel();
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(null);

            JLabel lblSenderAccountId = new JLabel("Sender Account ID");
            lblSenderAccountId.setBounds(30, 31, 113, 14);
            panel.add(lblSenderAccountId);

            textField = new JTextField();
            textField.setBounds(167, 28, 86, 20);
            panel.add(textField);
            textField.setColumns(10);

            JLabel lblReceiverAccountId = new JLabel("Receiver Account ID");
            lblReceiverAccountId.setBounds(30, 62, 113, 14);
            panel.add(lblReceiverAccountId);

            textField_1 = new JTextField();
            textField_1.setBounds(167, 59, 86, 20);
            panel.add(textField_1);
            textField_1.setColumns(10);

            textField_2 = new JTextField();
            textField_2.setBounds(167, 90, 86, 20);
            panel.add(textField_2);
            textField_2.setColumns(10);

            JLabel lblAmount = new JLabel("Amount");
            lblAmount.setBounds(30, 93, 46, 14);
            panel.add(lblAmount);

            JButton btnTransfer = new JButton("Transfer");
            btnTransfer.setBounds(164, 121, 89, 23);
            btnTransfer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    String id1 = textField.getText();
                    String id2 = textField_1.getText();
                    String a = textField_2.getText();
                    if (!id1.equals("") && !id2.equals("") && !a.equals(""))
                    {
                        int idAccount1 = Integer.parseInt(id1);
                        int idAccount2 = Integer.parseInt(id2);
                        int amount = Integer.parseInt(a);
                        int status =user.transferMoney(idAccount1, idAccount2,amount);
                        System.out.println("Transfer money!");
                        if (status == -1)
                        {
                            JOptionPane.showMessageDialog(frame,
                                    "Nu sunt suficiente fonduri pentru a face transferul!","Eroare",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(frame,
                                    "Transferul a fost efectuat cu succes!");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame,
                                "Completati campurile corespunzator!","Eroare",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            panel.add(btnTransfer);
            frame.setVisible(true);
        }
    }
}

