package Projektarbeit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CoffeeShop extends JFrame {

    private JPanel jpCoffePanel;
    private JLabel lblTitel;
    private JLabel lblCreateDrink;
    private JLabel lblDrink;
    private JComboBox cbxDrinkBox;
    private JLabel lblSize;
    private JComboBox cbxGroeße;
    private JLabel lblFlavour;
    private JRadioButton vanilleRadioButton;
    private JRadioButton caramellRadioButton;
    private JRadioButton pistazieRadioButton;
    private JLabel lblMilk;
    private JComboBox cbxMilch;
    private JButton btnCreateButton;
    private JTextArea taAusgabe;
    private JTextField jtGesamtpreisText;
    private JButton btnOrderButton;
    private JLabel lblGesamtpreis;
    private JButton speichernButton;
    private JButton clearButton;

    private ArrayList <CoffeeOrder> coffeeOrderlist = new ArrayList<CoffeeOrder>();

    public CoffeeShop() throws HeadlessException {
        setTitle("Eingabe für die Bestellung im Coffe Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setContentPane(jpCoffePanel);
        setVisible(true);



        btnCreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            ausgeben();
            }
        });

        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speichern();


            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // damit man nur einen RadioButton auswählen kann
        ButtonGroup flavourGroup = new ButtonGroup();
        flavourGroup.add(vanilleRadioButton);
        flavourGroup.add(caramellRadioButton);
        flavourGroup.add(pistazieRadioButton);

        btnOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

        public void ausgeben(){
            for (CoffeeOrder coffeeOrder: coffeeOrderlist){ //for each Schleife
                taAusgabe.setText(taAusgabe.getText() + "\n" + coffeeOrder.ausgeben());
            }

    }

        public void speichern(){
                String sDrink = cbxDrinkBox.getSelectedItem().toString();
                String sSize = cbxGroeße.getSelectedItem().toString();
                String sMilk = cbxMilch.getSelectedItem().toString();

                boolean bVanille = vanilleRadioButton.isSelected();
                boolean bCaramell = caramellRadioButton.isSelected();
                boolean bPistazie = pistazieRadioButton.isSelected();

                CoffeeOrder order = new CoffeeOrder(
                        sDrink, bVanille, sSize, bCaramell, bPistazie, sMilk
                );
        }


    public static void main(String[] args) {
        new CoffeeShop();

    }
}