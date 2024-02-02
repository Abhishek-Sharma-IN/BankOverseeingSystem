import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class signup2 extends JFrame implements ActionListener {
    JComboBox religion, category, income, occupation, education;
    JLabel head, religionLabel, categoryLabel, incomLabel, occupationLabel, mobNo, aadhaar, educationLabel, email;
    JTextField mobNoTextField, aadharTextField, emailField;
    JButton next;
    String applicationNo, name;

    signup2(String applicationNo, String name) {
        this.applicationNo = applicationNo;
        this.name = name;

        head = new JLabel("Page 2 out of 3: Additional Details ");
        head.setBounds(100, 10, 500, 50);
        head.setFont(new Font("Osward", Font.BOLD, 20));
        add(head);

        religionLabel = new JLabel("Religion :");
        religionLabel.setBounds(20, 75, 500, 50);
        religionLabel.setFont(new Font("Osward", Font.BOLD, 15));
        add(religionLabel);

        String valreligion[] = { "Hindu", "Muslim", "Sikh", "Christian", "Other" };
        religion = new JComboBox<>(valreligion);
        religion.setBounds(150, 87, 200, 25);
        add(religion);

        categoryLabel = new JLabel("Category :");
        categoryLabel.setBounds(20, 115, 500, 50);
        ;
        categoryLabel.setFont(new Font("Osward", Font.BOLD, 15));
        add(categoryLabel);

        String valCategory[] = { "General", "Obc", "Sc", "St", "Other" };
        category = new JComboBox<>(valCategory);
        category.setBounds(150, 127, 200, 25);
        add(category);

        String valIncome[] = { "0 - 1 Lakh", "1 - 5 Lakh", "5 - 10 Lakh", "More Than 10 Lakh" };
        income = new JComboBox<>(valIncome);
        income.setBounds(150, 167, 200, 25);
        add(income);

        incomLabel = new JLabel("Income :");
        incomLabel.setBounds(20, 155, 300, 50);
        incomLabel.setFont(new Font("Osward", Font.BOLD, 15));
        add(incomLabel);

        String valOccupation[] = { "Government Sector", "Private Sector", "Semi-Government Sector", "Farmer", "Other" };
        occupation = new JComboBox<>(valOccupation);
        occupation.setBounds(150, 207, 200, 25);
        occupation.setFont(new Font("Osward", Font.BOLD, 13));
        add(occupation);

        occupationLabel = new JLabel("Occupation :");
        occupationLabel.setBounds(20, 195, 500, 50);
        occupationLabel.setFont(new Font("Osward", Font.BOLD, 15));
        add(occupationLabel);

        String valEdu[] = { "Non-Educated", "10th", "12th", "Graduate", "Post-Graduate", "Phd" };
        education = new JComboBox<>(valEdu);
        education.setBounds(150, 247, 200, 25);
        education.setFont(new Font("Osward", Font.BOLD, 13));
        add(education);

        educationLabel = new JLabel("Mobile No :");
        educationLabel.setBounds(20, 235, 500, 50);
        educationLabel.setFont(new Font("Osward", Font.BOLD, 15));
        add(educationLabel);

        mobNo = new JLabel("Mobile No :");
        mobNo.setBounds(20, 275, 500, 50);
        mobNo.setFont(new Font("Osward", Font.BOLD, 15));
        add(mobNo);

        mobNoTextField = new JTextField();
        mobNoTextField.setBounds(150, 287, 200, 25);
        add(mobNoTextField);

        aadhaar = new JLabel("Aadhar No :");
        aadhaar.setBounds(20, 315, 500, 50);
        aadhaar.setFont(new Font("Osward", Font.BOLD, 15));
        add(aadhaar);

        aadharTextField = new JTextField();
        aadharTextField.setBounds(150, 327, 200, 25);
        add(aadharTextField);
        email = new JLabel("Email :");
        email.setBounds(20, 355, 500, 50);
        email.setFont(new Font("Osward", Font.BOLD, 15));
        add(email);
        emailField = new JTextField();
        emailField.setBounds(150, 367, 200, 25);
        add(emailField);

        next = new JButton("Next Page");
        next.setBounds(350, 400, 100, 28);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(new Color(148, 184, 232));
        setSize(500, 500);
        setLocation(450, 100);
        setUndecorated(true);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String saadhar = aadharTextField.getText();
        String smobNo = mobNoTextField.getText();
        String semail = emailField.getText();

        try {
            if (aadharTextField.equals("") || mobNoTextField.equals("") || emailField.equals("")) {
                JOptionPane.showMessageDialog(null, "All Fields are  mandatory");
            } else {
                connectionsql c = new connectionsql();
                String query = "insert into signup2 values('" + applicationNo + "','" + name + "','" + sreligion + "','"
                        + soccupation + "','" + smobNo
                        + "','" + sincome + "','" + seducation + "','" + scategory + "','" + saadhar + "','" + semail
                        + "')";
                c.s.executeUpdate(query);
                setVisible(false);
                new signup3(applicationNo, name).setVisible(true);

            }
        } catch (Exception es) {
            System.out.println(es);
        }

    }

    public static void main(String[] args) {
        new signup2("", "");
    }

}
