import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextField textField;
    private JRadioButton rdbtnOn;
    private JRadioButton rdbtnOff;
    private JButton btnNewButton;
    private JButton btnC;
    private JButton btnNewButton_2;
    private JButton btnNewButton_5;
    private JButton btnNewButton_16_1;
    private JButton btnNewButton_16;
    private JButton btnNewButton_11;
    private JButton btnNewButton_17;
    
    private JButton[] numberButtons;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculator() {
        // Set up the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 385, 431);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Text Field
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBounds(23, 29, 342, 57);
        contentPane.add(textField);

        // Radio Buttons
        rdbtnOn = new JRadioButton("ON");
        rdbtnOn.setFont(new Font("Arial Black", Font.BOLD, 14));
        rdbtnOn.setBounds(23, 88, 64, 29);
        contentPane.add(rdbtnOn);

        rdbtnOff = new JRadioButton("OFF");
        rdbtnOff.setFont(new Font("Arial Black", Font.BOLD, 14));
        rdbtnOff.setBounds(23, 119, 64, 33);
        contentPane.add(rdbtnOff);

        // Number Buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial Black", Font.BOLD, 19));
            numberButtons[i].setBounds(23 + (i % 3) * 88, 162 + (i / 3) * 54, 78, 44);
            numberButtons[i].addActionListener(this);
            contentPane.add(numberButtons[i]);
        }

        // Other Buttons
        btnNewButton = new JButton("<--");
        btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 16));
        btnNewButton.addActionListener(this);
        btnNewButton.setBounds(111, 96, 78, 44);
        contentPane.add(btnNewButton);

        btnC = new JButton("C");
        btnC.setFont(new Font("Arial Black", Font.BOLD, 16));
        btnC.addActionListener(this);
        btnC.setBounds(199, 96, 78, 44);
        contentPane.add(btnC);

        btnNewButton_2 = new JButton("+");
        btnNewButton_2.setFont(new Font("Arial Black", Font.BOLD, 19));
        btnNewButton_2.addActionListener(this);
        btnNewButton_2.setBounds(287, 96, 78, 44);
        contentPane.add(btnNewButton_2);

       
        btnNewButton_5 = new JButton("-");
        btnNewButton_5.setFont(new Font("Arial Black", Font.BOLD, 24));
        btnNewButton_5.addActionListener(this);
        btnNewButton_5.setBounds(287, 162, 78, 44);
        contentPane.add(btnNewButton_5);

       

       

        btnNewButton_11 = new JButton(".");
        btnNewButton_11.setVerticalAlignment(SwingConstants.TOP);
        btnNewButton_11.setFont(new Font("Arial Black", Font.BOLD, 19));
        btnNewButton_11.addActionListener(this);
        btnNewButton_11.setBounds(111, 324, 78, 44);
        contentPane.add(btnNewButton_11);

        

        btnNewButton_16 = new JButton("/");
        btnNewButton_16.setFont(new Font("Arial Black", Font.BOLD, 25));
        btnNewButton_16.addActionListener(this);
        btnNewButton_16.setBounds(287, 268, 78, 44);
        contentPane.add(btnNewButton_16);

        btnNewButton_17 = new JButton("=");
        btnNewButton_17.setFont(new Font("Arial Black", Font.BOLD, 19));
        btnNewButton_17.addActionListener(this);
        btnNewButton_17.setBounds(199, 324, 166, 44);
        contentPane.add(btnNewButton_17);

        btnNewButton_16_1 = new JButton("*");
        btnNewButton_16_1.setFont(new Font("Arial Black", Font.BOLD, 25));
        btnNewButton_16_1.addActionListener(this);
        btnNewButton_16_1.setBounds(287, 216, 78, 44);
        contentPane.add(btnNewButton_16_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("D:\\devi\\countdown\\countdown\\newyear\\src\\assets\\all page background image.png"));
        lblNewLabel.setBounds(0, 3, 371, 391);
        contentPane.add(lblNewLabel);
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }

    // Action Event Handling
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JButton) {
            handleButtonAction((JButton) source);
        } else if (source instanceof JRadioButton) {
            handleRadioButtonAction((JRadioButton) source);
        }
    }

    private void handleButtonAction(JButton source) {
        String buttonText = source.getText();

        if (Character.isDigit(buttonText.charAt(0))) {
            textField.setText(textField.getText() + buttonText);
        } else if (source == btnNewButton_2 || source == btnNewButton_5 || source == btnNewButton_16_1 || source == btnNewButton_16) {
            num1 = Double.parseDouble(textField.getText());
            operator = buttonText.charAt(0);
            textField.setText("");
        } else if (source == btnNewButton_17) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
            operator = '\0';
        } else if (source == btnC) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = '\0';
        } else if (source == btnNewButton_11) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText() + ".");
            }
        }
    }

    private void handleRadioButtonAction(JRadioButton source) {
        if (source == rdbtnOn) {
            rdbtnOff.setSelected(false);
            enableCalculator();
        } else if (source == rdbtnOff) {
            rdbtnOn.setSelected(false);
            disableCalculator();
        }
    }

    private void enableCalculator() {
        textField.setEnabled(true);
        btnNewButton.setEnabled(true);
        btnC.setEnabled(true);
        for (JButton button : numberButtons) {
            button.setEnabled(true);
        }
        // (enable other buttons as needed)
    }

    private void disableCalculator() {
        textField.setEnabled(false);
        btnNewButton.setEnabled(false);
        btnC.setEnabled(false);
        for (JButton button : numberButtons) {
            button.setEnabled(false);
        }
        // (disable other buttons as needed)
    }
}
