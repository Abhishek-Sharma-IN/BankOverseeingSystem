import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.*;

public class deposit extends JFrame implements ActionListener {
    JLabel depositJLabel;
    JTextField deposTextField;
    JButton deposit,cancel;
    String username;
    deposit(String username){
        this.username = username;

        
        depositJLabel = new JLabel("Deposit");
        depositJLabel.setBounds(145, 20, 300, 25);
        depositJLabel.setFont(new Font("Georgia", Font.BOLD, 25));
        depositJLabel.setForeground(Color.WHITE);
        add(depositJLabel);

        deposTextField = new JTextField();
        deposTextField.setBounds(50, 70, 300, 25);
        deposTextField.setFont(new Font("Georgia", Font.BOLD, 25));
        add(deposTextField);

        deposit = new JButton("Submit");
        deposit.setBounds(85, 120, 100, 20);
        deposit.setFont(new Font("Arial", Font.BOLD, 15));
        deposit.addActionListener(this);
        add(deposit);

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
        
        if(e.getSource() == cancel){
           setVisible(false);
        } else if (e.getSource() == deposit){
            Date date1 = new Date();
            String date = DateFormat.getDateTimeInstance().format(date1);
           

        // // Create an instance of SimpleDateFormat.
        // SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // // Format the date.
        // String strDate = formatter.format(date);
            // try {
            //     connectionsql c = new connectionsql();
            //     int deposit = Integer.parseInt(deposTextField.getText());
            //     String query = "insert into transactions values('"+ applicationNo +"')";
            //      c.s.executeQuery(query);
            //     String query1 = "update transactions set deposit ='"+ deposit +"', balance = deposit - withdraw where applicationNo='"+applicationNo+"'";
            //      c.s.executeQuery(query1);
            //     setVisible(false);

            //     // String query1 = "update transactions set deposit = '" + deposit + "', balance = deposit - withdraw where applicationNo = '" + applicationNo + "'";

            // } catch (Exception es) {
                
            // }

            try {
                connectionsql c = new connectionsql();
                int deposit = Integer.parseInt(deposTextField.getText());
                String query = "insert into acc"+username+"(accountNo,type, amount,date) values('"+ username +"','deposit', '"+ deposit +"','"+date+"')";
                // String query1 = "UPDATE acc"+username+" SET balance = balance + "+deposit+" where date = '"+date+"' ";
               c.s.executeUpdate(query);
               
                ResultSet rs = c.s.executeQuery("select * from acc"+username+"");
                int balance = 0;
                // while (rs.next()) {
                //     if (rs.getString("type").equals("deposit")) {
                //         balance = balance + Integer.parseInt(rs.getString("amount"));
                        
                //     }
                    
                // }
                //  String query1 = "UPDATE acc" + username + " SET balance = " + balance + " WHERE id = (SELECT MAX(id) )";
                //           c.s.executeUpdate(query1);
                
                int lastId = -1;
           while (rs.next()) {
                if (rs.getString("type").equals("deposit")) {
            balance += Integer.parseInt(rs.getString("amount"));
            lastId=rs.getInt("id");
        }
        else{             balance -= Integer.parseInt(rs.getString("amount"));
            lastId=rs.getInt("id");

        }
    }
    rs.close();
    if (lastId != -1) {
        String query1 = "UPDATE acc" + username + " SET balance = " + balance + " WHERE id = " + lastId;
        c.s.executeUpdate(query1);
    }
    

                
                setVisible(false);
            } catch (Exception es) {
                es.printStackTrace();
            }

            // try {
            //            connectionsql c = new connectionsql();
            //     int deposit = Integer.parseInt(deposTextField.getText());
            //      String query = "insert into acc"+username+"(accountNo, deposit) values('"+ username +"', '"+ deposit +"')";
            //      c.s.executeUpdate(query);
                
            //      String query1="select balance from acc"+username+" ";

            //      ResultSet rs = c.s.executeQuery(query1);
            //      if (rs.next()) {
            //         int balance = rs.getInt("balance");
            //         balance = balance + deposit;
            //         String query2 = "insert into acc"+username+"(accountNo, deposit,balance,date) values('"+ username +"', '"+ deposit +"','"+balance+"','"+date+"')";
            //         c.s.executeUpdate(query2);


            //         setVisible(false);


            //      }

                
            // } catch (Exception es) {
            //     es.printStackTrace();
            // }
          
        }
        
        }
    public static void main(String[] args) {
        new deposit("");
    }
   
    
}
