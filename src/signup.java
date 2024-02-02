import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import java.util.Random;

public class signup extends JFrame implements ActionListener {
    JLabel formno, fullName, fname, city, state, address, dob, pin, line, gender, panNo;
    JTextField fullTextField, fnamTextField, panNoTextField, addresstTextField, cityTextField, pinTextField,
            staTextField;
    JRadioButton genButton2, genButton, genButton3;
    JButton next;
    JDateChooser date;
    long random;

    signup() {
        setLayout(null);

        // Window Design
        setTitle("SignUp form");
        getContentPane().setBackground(new Color(148, 184, 232));

        Random ran = new Random();
        random = Math.abs(ran.nextLong() % 9000L + 1000L);
        formno = new JLabel("Application Form :" + random);
        formno.setBounds(80, 10, 500, 50);
        formno.setFont(new Font("Osward", Font.BOLD, 25));
        add(formno);

        line = new JLabel("-----------------------------------");
        line.setBounds(75, 25, 500, 50);
        line.setFont(new Font("Osward", Font.BOLD, 25));
        add(line);

        fullName = new JLabel("Full Name :");
        fullName.setBounds(20, 75, 500, 50);
        fullName.setFont(new Font("Osward", Font.BOLD, 15));
        add(fullName);

        fullTextField = new JTextField();
        fullTextField.setBounds(150, 87, 200, 25);
        add(fullTextField);

        fname = new JLabel("Father's Name :");
        fname.setBounds(20, 115, 500, 50);
        fname.setFont(new Font("Osward", Font.BOLD, 15));
        add(fname);

        fnamTextField = new JTextField();
        fnamTextField.setBounds(150, 127, 200, 25);
        add(fnamTextField);

        gender = new JLabel("Gender :");
        gender.setBounds(20, 155, 300, 50);
        gender.setFont(new Font("Osward", Font.BOLD, 15));
        add(gender);

        genButton = new JRadioButton("male");
        genButton.setBounds(150, 167, 60, 25);
        genButton.setBackground(new Color(148, 184, 232));
        add(genButton);

        genButton2 = new JRadioButton("female");
        genButton2.setBounds(210, 167, 72, 25);
        genButton2.setBackground(new Color(148, 184, 232));
        add(genButton2);
        genButton3 = new JRadioButton("Transgender");
        genButton3.setBounds(280, 167, 100, 25);
        genButton3.setBackground(new Color(148, 184, 232));
        add(genButton3);

        ButtonGroup mb = new ButtonGroup();
        mb.add(genButton);
        mb.add(genButton2);
        mb.add(genButton3);

        dob = new JLabel("DOB :");
        dob.setBounds(20, 195, 500, 50);
        dob.setFont(new Font("Osward", Font.BOLD, 15));
        add(dob);

        date = new JDateChooser();
        date.setBounds(150, 207, 200, 25);
        date.setForeground(new Color(105, 105, 105));
        add(date);

        panNo = new JLabel("PAN No :");
        panNo.setBounds(20, 235, 500, 50);
        panNo.setFont(new Font("Osward", Font.BOLD, 15));
        add(panNo);

        panNoTextField = new JTextField();
        panNoTextField.setBounds(150, 247, 200, 25);
        add(panNoTextField);

        address = new JLabel("Address :");
        address.setBounds(20, 275, 500, 50);
        address.setFont(new Font("Osward", Font.BOLD, 15));
        add(address);

        addresstTextField = new JTextField();
        addresstTextField.setBounds(150, 287, 200, 25);
        add(addresstTextField);

        city = new JLabel("City  :");
        city.setBounds(20, 315, 500, 50);
        city.setFont(new Font("Osward", Font.BOLD, 15));
        add(city);

        cityTextField = new JTextField();
        cityTextField.setBounds(150, 327, 200, 25);
        add(cityTextField);

        pin = new JLabel("Pincode :");
        pin.setBounds(20, 355, 500, 50);
        pin.setFont(new Font("Osward", Font.BOLD, 15));
        add(pin);
        pinTextField = new JTextField();
        pinTextField.setBounds(150, 367, 200, 25);
        add(pinTextField);

        state = new JLabel("State :");
        state.setBounds(20, 395, 500, 50);
        state.setFont(new Font("Osward", Font.BOLD, 15));
        add(state);

        staTextField = new JTextField();
        staTextField.setBounds(150, 407, 200, 25);
        staTextField.setFont(new Font("Osward", Font.PLAIN, 15));
        add(staTextField);

        next = new JButton("Next Page");
        next.setBounds(300, 450, 100, 20);
        next.addActionListener(this);
        add(next);

        setSize(450, 530);
        setUndecorated(true);
        setVisible(true);
        setLocation(550, 100);

    }

    public void actionPerformed(ActionEvent e) {
        String name = fullTextField.getText();
        String fname = fnamTextField.getText();
        String panno = panNoTextField.getText();
        String address = addresstTextField.getText();
        String state = staTextField.getText();
        String city = cityTextField.getText();
        String pin = pinTextField.getText();
        String gender = null;
        if (genButton.isSelected()) {
            gender = "Male";
        } else if (genButton3.isSelected()) {
            gender = "Transgender";
        } else {
            gender = "Female";
        }
        String applicationNo = "" + random;
        String dob = ((JTextField) date.getDateEditor().getUiComponent()).getText();

        // Fields -----------------------------------------------------------

        try {
            connectionsql c = new connectionsql();
            String query1 = "select * from signup where panno = '" + panno + "'";
            ResultSet rs = c.s.executeQuery(query1);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "An account with this PAN card number already exists.");
            } else if (name.equals("") || fname.equals("") || panno.equals("") || address.equals("") || state.equals("")
                    || city.equals("")
                    || pin.equals("")) {
                JOptionPane.showMessageDialog(null, "All Fields are  mandatory");
            } else {

                String query = "insert into signup values('" + applicationNo + "','" + name + "','" + fname + "','"
                        + gender
                        + "','" + dob + "','" + panno + "','" + address + "','" + city + "','" + pin + "','" + state
                        + "')";
                c.s.executeUpdate(query);
                setVisible(false);
                new signup2(applicationNo, name).setVisible(true);
            }
        } catch (Exception es) {
            System.out.println(es);
        }

    }

    public static void main(String[] args) {
        new signup();
    }

}
