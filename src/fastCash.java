import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.*;

public class fastCash extends JFrame implements ActionListener {
    JLabel withdrawJLabel;
    JTextField withdrawTextField;
    JButton bb200, bb500, bb1000, bb2000, bb5000, cancel;
    String username;

    fastCash(String username) {
        this.username = username;

        withdrawJLabel = new JLabel("Fast Cash");
        withdrawJLabel.setBounds(120, 20, 300, 25);
        withdrawJLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        withdrawJLabel.setForeground(Color.WHITE);
        add(withdrawJLabel);

        bb200 = new JButton("200");
        bb200.setBounds(35, 80, 120, 25);
        bb200.setFont(new Font("Arial", Font.BOLD, 15));
        bb200.addActionListener(this);
        add(bb200);

        bb500 = new JButton("500");
        bb500.setBounds(190, 80, 120, 25);
        bb500.setFont(new Font("Arial", Font.BOLD, 15));
        bb500.addActionListener(this);
        add(bb500);

        bb1000 = new JButton("1000");
        bb1000.setBounds(35, 120, 120, 25);
        bb1000.setFont(new Font("Arial", Font.BOLD, 15));
        bb1000.addActionListener(this);
        add(bb1000);

        bb2000 = new JButton("2000");
        bb2000.setBounds(190, 120, 120, 25);
        bb2000.setFont(new Font("Arial", Font.BOLD, 15));
        bb2000.addActionListener(this);
        add(bb2000);

        // bb5000 = new JButton("5000");
        // bb5000.setBounds(80, 160, 200, 25);
        // bb5000.setFont(new Font("Arial", Font.BOLD, 15));
        // bb5000.addActionListener(this);
        // add(bb5000);

        cancel = new JButton("Cancel");
        cancel.setBounds(100, 180, 150, 25);
        cancel.setFont(new Font("Arial", Font.BOLD, 15));
        cancel.addActionListener(this);
        add(cancel);

        setLayout(null);
        setSize(350, 270);
        setLocation(600, 210);
        getContentPane().setBackground(Color.BLACK);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        int withdraw = 0;
        if (e.getSource() == cancel) {
            setVisible(false);
            return;
        } else if (e.getSource() == bb200) {
            withdraw = 200;
        } else if (e.getSource() == bb500) {
            withdraw = 500;

        } else if (e.getSource() == bb1000) {
            withdraw = 1000;
        } else if (e.getSource() == bb2000) {
            withdraw = 2000;

        }
        Date date1 = new Date();
            String date = DateFormat.getDateTimeInstance().format(date1);
        try {
            

            connectionsql c = new connectionsql();
            ResultSet rs1 = c.s.executeQuery("SELECT balance FROM acc" + username + " ORDER BY id DESC LIMIT 1");

            if (rs1.next()){
                System.out.println(withdraw);
                int balance1 = rs1.getInt("balance");
                if (balance1 < withdraw){
                    JOptionPane.showMessageDialog(null,"   Insufficient Balance");;
                }
            else{

            String query = "insert into acc" + username + "(accountNo,type, amount,date) values('" + username
                    + "','withdraw', '" + withdraw + "','" + date + "')";

            c.s.executeUpdate(query);

            ResultSet rs = c.s.executeQuery("select * from acc" + username + "");
            int balance = 0;

            int lastId = -1;
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
                JOptionPane.showMessageDialog(null,"Rs "+withdraw+" withdrawn succesfully");
                
            }

            setVisible(false);
       }}
     } catch (Exception es) {
            es.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new fastCash("");

    }

}
