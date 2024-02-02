import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class signup3 extends JFrame implements ActionListener {
    JLabel head, type, accountNolabel, servicLabel;
    JTextField pinField;
    JButton saveButton, cancelButton;
    JRadioButton t1, t2, t3, t4;
    JCheckBox c1Box, c2Box, c3Box, c4Box, c5Box, c6Box, c7Box;
    String applicationNo, name;
    int accountNo;

    signup3(String applicationNo, String name) {
        this.applicationNo = applicationNo;
        this.name = name;
        Random ran = new Random();
        accountNo = Math.abs(ran.nextInt() % 90000000 + 1000);

        head = new JLabel("Page 3 out of 3: Additional Details ");
        head.setBounds(120, 10, 500, 50);
        head.setFont(new Font("Osward", Font.BOLD, 20));
        add(head);

        type = new JLabel("Account Type :");
        type.setBounds(20, 75, 500, 50);
        type.setFont(new Font("Osward", Font.BOLD, 18));
        add(type);

        t1 = new JRadioButton("SAVING Account");
        t1.setBounds(30, 120, 200, 22);
        t1.setBackground(new Color(148, 184, 232));
        add(t1);

        t2 = new JRadioButton("CURRENT Account");
        t2.setBounds(30, 150, 200, 22);
        t2.setBackground(new Color(148, 184, 232));
        add(t2);

        t3 = new JRadioButton("FIXED DEPOSIT Account");
        t3.setBounds(250, 120, 300, 22);
        t3.setBackground(new Color(148, 184, 232));
        add(t3);

        t4 = new JRadioButton("RECURRING DEPOSIT Account");
        t4.setBounds(250, 150, 300, 22);
        t4.setBackground(new Color(148, 184, 232));
        add(t4);

        ButtonGroup bbButtonGroup = new ButtonGroup();
        bbButtonGroup.add(t1);
        bbButtonGroup.add(t2);
        bbButtonGroup.add(t4);
        bbButtonGroup.add(t3);

        accountNolabel = new JLabel("Please set your password :");
        accountNolabel.setBounds(20, 220, 500, 25);
        accountNolabel.setFont(new Font("Osward", Font.BOLD, 15));
        add(accountNolabel);

        pinField = new JTextField();
        pinField.setBounds(230, 220, 200, 25);
        pinField.setFont(new Font("Osward", Font.PLAIN, 18));
        add(pinField);

        servicLabel = new JLabel("Services Required :");
        servicLabel.setBounds(20, 250, 500, 50);
        servicLabel.setFont(new Font("Osward", Font.BOLD, 18));
        add(servicLabel);

        c1Box = new JCheckBox("ATM Card");
        c1Box.setBounds(30, 300, 90, 25);
        c1Box.setBackground(new Color(148, 184, 232));
        add(c1Box);

        c2Box = new JCheckBox("CheckBook");
        c2Box.setBounds(30, 340, 100, 25);
        c2Box.setBackground(new Color(148, 184, 232));
        add(c2Box);

        c3Box = new JCheckBox("PassBook");
        c3Box.setBounds(230, 300, 90, 25);
        c3Box.setBackground(new Color(148, 184, 232));
        add(c3Box);

        c4Box = new JCheckBox("Internet Banking");
        c4Box.setBounds(230, 340, 130, 25);
        c4Box.setBackground(new Color(148, 184, 232));
        add(c4Box);

        c5Box = new JCheckBox("I, hereby declear that all the information provided by me is accurate.");
        c5Box.setBounds(20, 400, 450, 25);
        c5Box.setBackground(new Color(148, 184, 232));
        add(c5Box);

        saveButton = new JButton("Save");
        saveButton.setBounds(190, 440, 80, 25);
        saveButton.addActionListener(this);
        add(saveButton);

        // cancelButton = new JButton("Cancel");
        // cancelButton.setBounds(260, 440,80,25);
        // cancelButton.addActionListener(this);
        // add(cancelButton);

        getContentPane().setBackground(new Color(148, 184, 232));
        setSize(510, 550);
        setLocation(450, 100);
        setUndecorated(true);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // int accountNo = accountNo;
        Date date1 = new Date();
        String date = DateFormat.getDateTimeInstance().format(date1);

        String password = pinField.getText();
        String service = "";
        String table = Integer.toString(accountNo);

        if (c1Box.isSelected()) {
            service = service + " ATM Card";

        }
        if (c2Box.isSelected()) {
            service = service + " CheckBook";
        }
        if (c3Box.isSelected()) {
            service = service + " Passbook";
        }
        if (c4Box.isSelected()) {
            service = service + " Internet Banking";
        }
        String decl = "";
        if (c5Box.isSelected()) {
            decl = decl + "Decleared";
        }

        String type = "";

        if (t1.isSelected()) {
            type = "Saving Account";

        } else if (t2.isSelected()) {
            type = "CURRENT Account";

        } else if (t3.isSelected()) {
            type = "Fixed Deposit Account";

        } else if (t4.isSelected()) {
            type = "Reccuring Deposit Account";

        } else if (type.equals("")) {
            JOptionPane.showMessageDialog(null, "Account type is required");
        }

        try {
            if (pinField.equals("")) {
                JOptionPane.showMessageDialog(null, "All Fields are  mandatory");
            } else if (decl.equals("")) {
                JOptionPane.showMessageDialog(null, "Please Check the Declearation");
            } else {
                connectionsql c = new connectionsql();
                String query = "insert into signup3 values('" + name + "','" + accountNo + "','" + password + "','"
                        + service + "','" + type + "')";

                String query2 = "INSERT INTO users VALUES('" + accountNo + "', '" + password + "' ) ";
                String query3 = "CREATE TABLE acc" + table
                        + " (id INT AUTO_INCREMENT PRIMARY KEY,accountNo INT, type varchar (255),amount INT, balance INT, date varchar (255))";

                c.s.executeUpdate(query);
                c.s.executeUpdate(query3);
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null,
                        "Your Account Number  is " + accountNo + " and password is " + password);
                int amount = Integer.parseInt(JOptionPane.showInputDialog(null, "Make an deposit"));

                String query4 = "INSERT INTO acc" + table + "" +
                        "(accountNo,type, amount, balance,date) " +
                        "VALUES (" + accountNo + ",'deposit', " + amount + ", " + amount + "," + date + ")";
                setVisible(false);
                c.s.executeUpdate(query4);
                new login().setVisible(true);

            }
        } catch (Exception es) {
            System.out.println(es);
        }

    }

    public static void main(String[] args) {
        new signup3("", "");

    }

}
