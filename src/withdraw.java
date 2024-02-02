import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.*;

public class withdraw extends JFrame implements ActionListener {
    JLabel withdrawJLabel;
    JTextField withdrawTextField;
    JButton withdraw, cancel;
    String username;

    withdraw(String username) {
        this.username = username;

        withdrawJLabel = new JLabel("withdraw");
        withdrawJLabel.setBounds(145, 20, 300, 25);
        withdrawJLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        withdrawJLabel.setForeground(Color.WHITE);
        add(withdrawJLabel);

        withdrawTextField = new JTextField();
        withdrawTextField.setBounds(50, 70, 300, 25);
        withdrawTextField.setFont(new Font("Arial", Font.BOLD, 25));
        add(withdrawTextField);

        withdraw = new JButton("Submit");
        withdraw.setBounds(85, 120, 100, 20);
        withdraw.setFont(new Font("Arial", Font.BOLD, 15));
        withdraw.addActionListener(this);
        add(withdraw);

        cancel = new JButton("Cancel");
        cancel.setBounds(205, 120, 100, 20);
        cancel.setFont(new Font("Arial", Font.BOLD, 15));
        cancel.addActionListener(this);
        add(cancel);

        setLayout(null);
        setSize(400, 200);
        setLocation(600, 300);
        getContentPane().setBackground(Color.BLACK);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cancel) {
            setVisible(false);
        } else if (e.getSource() == withdraw) {

            Date date1 = new Date();
            String date = DateFormat.getDateTimeInstance().format(date1);
            System.out.println(date);

            try {
                int lastId = -1;
                connectionsql c = new connectionsql();
                int withdraw = Integer.parseInt(withdrawTextField.getText());
                ResultSet rs1 = c.s.executeQuery("SELECT balance FROM acc" + username + " ORDER BY id DESC LIMIT 1");

                if (rs1.next()) {
                    int balance1 = rs1.getInt("balance");
                    if (balance1 < withdraw) {
                        JOptionPane.showMessageDialog(null, "   Insufficient Balance");

                    }

                    String query = "insert into acc" + username + "(accountNo,type, amount,date) values('" + username
                            + "','withdraw', '" + withdraw + "','" + date + "')";

                    c.s.executeUpdate(query);

                    ResultSet rs = c.s.executeQuery("select * from acc" + username + "");

                    int balance = 0;

                    while (rs.next()) {

                        if (rs.getString("type").equals("deposit")) {
                            balance += Integer.parseInt(rs.getString("amount"));
                            lastId = rs.getInt("id");
                        } else {
                            balance -= Integer.parseInt(rs.getString("amount"));
                            lastId = rs.getInt("id");
                        }
                    }
                    rs.close();
                    if (lastId != -1) {
                        String query1 = "UPDATE acc" + username + " SET balance = " + balance + " WHERE id = " + lastId;
                        c.s.executeUpdate(query1);

                    }

                    setVisible(false);
                }
            } catch (Exception es) {
                es.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        new withdraw("");
    }

}
