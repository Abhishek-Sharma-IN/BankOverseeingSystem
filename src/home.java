import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class home extends JFrame implements ActionListener {
    JButton deposit, withdraw, mini, fastCash, balance, exit, pinchange;
    JLabel welcome;
    String password, name1, username;

    home(String password, String username) {
        this.password = password;
        this.username = username;
        setLayout(null);
        try {
            connectionsql c = new connectionsql();
            String query = "select name from signup3 where  accountNo ='" + username + "' ";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                String name1 = rs.getString("name");
                welcome = new JLabel("Welcome Back, " + name1);
                welcome.setBounds(220, 220, 700, 40);
                welcome.setForeground(Color.WHITE);
                welcome.setFont(new Font("Osward", Font.BOLD, 35));
                add(welcome);

            } else {
                welcome = new JLabel("Welcome Back, Dear Customer ");
                welcome.setBounds(220, 220, 700, 40);
                welcome.setForeground(Color.WHITE);
                welcome.setFont(new Font("Osward", Font.BOLD, 35));
                add(welcome);

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        deposit = new JButton("Deposit");
        deposit.setBounds(40, 350, 200, 40);
        deposit.setBackground(new Color(30, 28, 36));
        deposit.setForeground(Color.WHITE);
        deposit.setFont(new Font("Arial", Font.BOLD, 20));
        deposit.addActionListener(this);
        add(deposit);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(40, 410, 200, 40);
        withdraw.setBackground(new Color(30, 28, 36));
        withdraw.setForeground(Color.WHITE);
        withdraw.setFont(new Font("Arial", Font.BOLD, 20));
        withdraw.addActionListener(this);
        add(withdraw);

        fastCash = new JButton("Fast Cash");
        fastCash.setBounds(40, 470, 200, 40);
        fastCash.setBackground(new Color(30, 28, 36));
        fastCash.setForeground(Color.WHITE);
        fastCash.setFont(new Font("Arial", Font.BOLD, 20));
        fastCash.addActionListener(this);
        add(fastCash);

        mini = new JButton("Transactions");
        mini.setBounds(720, 350, 200, 40);
        mini.setBackground(new Color(30, 28, 36));
        mini.setForeground(Color.WHITE);
        mini.setFont(new Font("Arial", Font.BOLD, 20));
        mini.addActionListener(this);
        add(mini);

        balance = new JButton("Balance Enquiry");
        balance.setBounds(720, 410, 200, 40);
        balance.setBackground(new Color(30, 28, 36));
        balance.setForeground(Color.WHITE);
        balance.setFont(new Font("Arial", Font.BOLD, 20));
        balance.addActionListener(this);
        add(balance);

        pinchange = new JButton("Pin Change");
        pinchange.setBounds(720, 470, 200, 40);
        pinchange.setBackground(new Color(30, 28, 36));
        pinchange.setForeground(Color.WHITE);
        pinchange.setFont(new Font("Arial", Font.BOLD, 20));
        pinchange.addActionListener(this);
        add(pinchange);

        exit = new JButton("Logout");
        exit.setBounds(410, 530, 150, 30);
        exit.setBackground(new Color(30, 28, 36));
        exit.setForeground(Color.WHITE);
        exit.setFont(new Font("Arial", Font.BOLD, 20));
        exit.addActionListener(this);
        add(exit);

        ImageIcon themeIcon = new ImageIcon(ClassLoader.getSystemResource("theme.jpg"));
        Image theme2 = themeIcon.getImage().getScaledInstance(1024, 720, Image.SCALE_SMOOTH);
        ImageIcon theme3Icon = new ImageIcon(theme2);
        JLabel themLabel = new JLabel(theme3Icon);
        themLabel.setBounds(0, 0, 1024, 720);
        add(themLabel);

        setSize(950, 600);
        setLocation(290, 60);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                JOptionPane.showConfirmDialog(this, "You have been logged out successfully", "Alert",
                        JOptionPane.DEFAULT_OPTION);
                setVisible(false);
                new login().setVisible(true);
            }
        } else if (e.getSource() == deposit) {
            new deposit(username).setVisible(true);
        } else if (e.getSource() == withdraw) {
            new withdraw(username).setVisible(true);
        } else if (e.getSource() == fastCash) {
            new fastCash(username).setVisible(true);

        } else if (e.getSource() == mini) {

            new mini(username).setVisible(true);

        } else if (e.getSource() == balance) {
            try {
                connectionsql c = new connectionsql();
                ResultSet rs = c.s.executeQuery("SELECT balance FROM acc" + username + " ORDER BY id DESC LIMIT 1");

                if (rs.next()) {
                    int balance1 = rs.getInt("balance");

                    JOptionPane.showMessageDialog(null, "Your Current account balance is " + balance1);

                }
            } catch (Exception es) {
                System.out.println(es);
            }
            // new balance(username).setVisible(true);
        } else if (e.getSource() == pinchange) {
            // Add logic for pinChange button
            // if (e.getSource()=) {

            // }
            try {
                connectionsql c = new connectionsql();
                String npin = JOptionPane.showInputDialog(null, "Choose a new Password", null,
                        JOptionPane.DEFAULT_OPTION);
                String query = "update users set password =" + npin + " where accountNo = " + username + "";
                if (npin == null) {
                    System.out.println();
                } else {
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Your password is changed successfully");
                }
            } catch (Exception es) {
                System.out.println(es);
            }
        }

    }

    public static void main(String[] args) throws Exception {
        String str = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        UIManager.setLookAndFeel(str);
        new home("", "");
    }

}
