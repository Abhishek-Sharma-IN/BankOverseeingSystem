import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test extends JFrame implements ActionListener {
    private JTextField amountField;
    private JTextArea transactionArea;

    public test() {
        setTitle("Admin Page");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        amountField = new JTextField(10);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton viewTransactionsButton = new JButton("View Transactions");

        transactionArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(transactionArea);

        // Set layout
        setLayout(new FlowLayout());

        // Add components to the frame
        add(new JLabel("Amount: "));
        add(amountField);
        add(depositButton);
        add(withdrawButton);
        add(viewTransactionsButton);
        add(scrollPane);

        // Register action listeners
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        viewTransactionsButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Deposit")) {
            performDeposit();
        } else if (e.getActionCommand().equals("Withdraw")) {
            performWithdrawal();
        } else if (e.getActionCommand().equals("View Transactions")) {
            viewTransactions();
        }
    }

    private void performDeposit() {
        // Add logic for depositing amount to the database
        String amountText = amountField.getText();
        if (!amountText.isEmpty()) {
            double amount = Double.parseDouble(amountText);
            // Perform deposit logic here
            transactionArea.append("Deposit: $" + amount + "\n");
            amountField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
        }
    }

    private void performWithdrawal() {
        // Add logic for withdrawing amount from the database
        String amountText = amountField.getText();
        if (!amountText.isEmpty()) {
            double amount = Double.parseDouble(amountText);
            // Perform withdrawal logic here
            transactionArea.append("Withdrawal: $" + amount + "\n");
            amountField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
        }
    }

    private void viewTransactions() {
        // Add logic to fetch and display transaction history from the database
        // In a real application, you would retrieve this information from the database
        transactionArea.setText("Transaction History:\n");
        transactionArea.append("1. Deposit: $100.00\n");
        transactionArea.append("2. Withdrawal: $50.00\n");
        transactionArea.append("3. Deposit: $200.00\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new test());
    }
}
