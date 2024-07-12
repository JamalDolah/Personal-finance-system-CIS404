package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    private final String pin;
    private final TextField textField;

    private final JButton b1;
    private final JButton b2;

    public Deposit(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 400, 35);
        l3.add(label1);

        textField = new TextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460, 230, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(textField);

        b1 = new JButton("DEPOSIT");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.BLACK);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.BLACK);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText().trim(); // Trim to remove leading/trailing spaces
            if (e.getSource() == b1) {
                if (amount.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
                } else {
                    Date date = new Date();
                    Connn c = new Connn();
                    String query = "INSERT INTO bank (pin, account_number, transaction_type, amount, transaction_date) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = c.connection.prepareStatement(query);

                    pstmt.setString(1, pin);
                    pstmt.setString(2, "123456"); // Example account number, replace with actual logic
                    pstmt.setString(3, "Deposit");
                    pstmt.setBigDecimal(4, new BigDecimal(amount));
                    pstmt.setDate(5, new java.sql.Date(date.getTime()));

                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "$ " + amount + " Deposited Successfully");
                    setVisible(false);
                    new main_Class(pin);
                }
            } else if (e.getSource() == b2) {
                setVisible(false);
                new main_Class(pin);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to deposit amount. Please try again later.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}






