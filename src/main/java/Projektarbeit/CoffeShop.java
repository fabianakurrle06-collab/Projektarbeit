package Projektarbeit;

import javax.swing.*;
import java.awt.*;

public class CoffeShop extends JFrame {

    private JPanel CoffePanel;
    private JLabel titleLabel;
    private JLabel createDrinkLabel;
    private JLabel drinkLabel;
    private JComboBox drinkcomboBox;
    private JLabel sizeLabel;
    private JComboBox groeßecomboBox;
    private JLabel flavourLabel;
    private JRadioButton vanilleRadioButton;
    private JRadioButton caramellRadioButton;
    private JRadioButton pistazieRadioButton;
    private JLabel milkLabel;
    private JComboBox comboBox1;
    private JButton createButton;
    private JTextArea ausgabeTextArea;

    public CoffeShop() throws HeadlessException {
        setTitle("Eingabe für die Bestellung im Coffe Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setContentPane(CoffePanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CoffeShop();
    }
}