import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class login extends JFrame implements ActionListener, MouseListener {

    JButton signIButton, clearButton, signUpButton, adminButton;
    JTextField card;
    String applicationNo;
    JPasswordField pinTextField;

    login() {
        

        setLayout(null);

        JLabel text = new JLabel("Bank Of Bharat");
        text.setBounds(200, 30, 400, 50);
        text.setFont(new Font("Ralwey", Font.BOLD, 40));
        text.setForeground(Color.WHITE);
        add(text);
        JLabel signJLabel = new JLabel("Signup");
        signJLabel.setBounds(330, 290, 45, 30);
        signJLabel.setFont(new Font("Ralwey", Font.BOLD, 13));
        signJLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signJLabel.setForeground(Color.WHITE);
        signJLabel.addMouseListener((MouseListener) this);
        add(signJLabel);

        JLabel signJLabel2 = new JLabel("Ｘ");
        signJLabel2.setBounds(650, 10, 200, 30);
        signJLabel2.setFont(new Font("Ralwey", Font.BOLD, 20));
        signJLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signJLabel2.setForeground(Color.WHITE);
        signJLabel2.addMouseListener((MouseListener) this);
        add(signJLabel2);

        JLabel signJLabel1 = new JLabel("Don't have an account,click on");
        signJLabel1.setBounds(150, 290, 200, 30);
        signJLabel1.setFont(new Font("Osward", Font.BOLD, 12));
        signJLabel1.setForeground(Color.BLACK);
        add(signJLabel1);

        getContentPane().setBackground(Color.WHITE);

        JLabel CardNo = new JLabel("Username:");
        CardNo.setBounds(20, 150, 200, 50);
        CardNo.setFont(new Font("Railway", Font.BOLD, 20));
        CardNo.setForeground(Color.WHITE);
        add(CardNo);

        JLabel pin = new JLabel("Password:");
        pin.setBounds(20, 200, 200, 50);
        pin.setFont(new Font("Railway", Font.BOLD, 20));
        pin.setForeground(Color.WHITE);
        add(pin);

        card = new JTextField("50205668");
        card.setBounds(150, 165, 200, 25);
        card.setFont(new Font("Arial", Font.PLAIN, 15));
        add(card);

        pinTextField = new JPasswordField("123456");
        pinTextField.setBounds(150, 216, 200, 25);
        add(pinTextField);

        // adminButton = new JButton("Admin Login");
        // adminButton.setBounds(530, 400, 110, 30);
        // adminButton.setBackground(new Color(30, 28, 36));
        // adminButton.setForeground(Color.WHITE);
        // adminButton.addActionListener(this);
        // add(adminButton);

        signIButton = new JButton("LogIn");
        signIButton.setBounds(150, 260, 95, 30);
        signIButton.setBackground(new Color(30, 28, 36));
        signIButton.setForeground(Color.WHITE);
        signIButton.addActionListener(this);
        add(signIButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(255, 260, 95, 30);
        clearButton.setBackground(new Color(30, 28, 36));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(this);
        add(clearButton);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("log.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setBounds(0, 0, 700, 500);
        add(label);

        setSize(700, 500);
        setLocation(420, 150);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clearButton) {
            card.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == signIButton) {

            connectionsql c = new connectionsql();
            String username = card.getText();
            String pin = pinTextField.getText();
            String query = "select * from users where accountNo = '" + username + "' and password ='" + pin + "' ";

            try {
                ResultSet rs = connectionsql.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new home(pin,username).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == signUpButton) {
            setVisible(false);
            new signup().setVisible(true);

        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseClicked(MouseEvent e) {
        JLabel clickedLabel = (JLabel) e.getSource();

        if (clickedLabel.getText().equals("Signup")) {
            // Handle the click for the "Signup" JLabel
            // For example, open a sign-up frame
            setVisible(false);
            new signup().setVisible(true);
        } else if (clickedLabel.getText().equals("Ｘ")) {
            System.exit(0);
        }
    }

    

    public static void main(String[] args) throws Exception {
        String str ="javax.swing.plaf.nimbus.NimbusLookAndFeel";
        UIManager.setLookAndFeel(str);
        new login();
    }
}
