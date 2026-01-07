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
    private ButtonGroup flavourGroup;
    private JLabel lblMilk;
    private JComboBox cbxMilch;
    private JTextArea taAusgabe;
    private JTextField jtGesamtpreisText;
    private JButton btnOrderButton;
    private JLabel lblGesamtpreis;
    private JButton speichernButton;
    private JButton clearButton;
    private JTextField jtAnzahl;
    private JLabel lblAbzahl;
    private JButton btnFilterVeganbutton;
    private JTextArea taVeganAusgabe;


    private ArrayList <CoffeeOrder> coffeeOrderlist = new ArrayList<CoffeeOrder>();

    public CoffeeShop() throws HeadlessException {
        setTitle("Eingabe für die Bestellung im Coffe Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setContentPane(jpCoffePanel);
        setVisible(true);
        initObjekte();

        // Flavour-Group initialiseren (damit nur 1 wählbar ist)
        flavourGroup = new ButtonGroup();
        flavourGroup.add(vanilleRadioButton);
        flavourGroup.add(caramellRadioButton);
        flavourGroup.add(pistazieRadioButton);


        // Speichern
        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speichern();
            }
        });

        // Clear
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            taAusgabe.setText("");
            coffeeOrderlist.clear();
            jtGesamtpreisText.setText("");
            }
        });

        // Gesamtpreis
        btnOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                berechneGesamtsumme();
            }
        });

        setVisible(true);
        btnFilterVeganbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtereNachVegan();
            }
        });
        cbxDrinkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ausgewählt = cbxDrinkBox.getSelectedItem().toString();

                if (ausgewählt.equals("Espresso")){
                    //Felder deaktivieren
                    cbxMilch.setEnabled(false);
                    vanilleRadioButton.setEnabled(false);
                    caramellRadioButton.setEnabled(false);
                    pistazieRadioButton.setEnabled(false);

                    //Auswahl zurücksetzen, damit kein Flavour/Milch mitgespeichert wird
                    cbxMilch.setSelectedIndex(0); //wählt das erste Element ("Milch" oder "keine")
                    flavourGroup.clearSelection();
                } else {
                    //für alle anderen Getränke wieder einschalten
                    cbxMilch.setEnabled(true);
                    vanilleRadioButton.setEnabled(true);
                    caramellRadioButton.setEnabled(true);
                    pistazieRadioButton.setEnabled(true);
                }
            }
        });
    }
        // Methoden


        public void initObjekte(){
        coffeeOrderlist.add(new CoffeeOrder("Espresso", false,"Small", false, false, "Milch", 1));
            coffeeOrderlist.add(new CoffeeOrder("Cappuccino", true,"Medium", false, false, "Hafermilch", 1));
            coffeeOrderlist.add(new CoffeeOrder("Latte Macchiato", false,"Large", true, false, "Sojamilch", 1));
            ausgeben();
        }


        public void ausgeben(){
            taAusgabe.setText(""); //vorher leeren
            for (CoffeeOrder order: coffeeOrderlist){ //for each Schleife
                taAusgabe.append(order.ausgeben() + "---------------\n");
            }


    }

        public void berechneGesamtsumme(){
            double summe = 0;
            for (CoffeeOrder order : coffeeOrderlist){
                summe += order.berechnePreis();
            }
            jtGesamtpreisText.setText(summe+ " €");
            }

        public void speichern(){
            try {
                // 1. leere Eingaben verhindern
                if (jtAnzahl.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this,  "Bitte geben Sie eine Anzahl ein!", "Eingabefehler", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // 2. Zahl umwandeln
                int iAnzahl = Integer.parseInt(jtAnzahl.getText());
                String sDrink = cbxDrinkBox.getSelectedItem().toString();
                String sSize = cbxGroeße.getSelectedItem().toString();

                //3. negative Zahl oder null
                if (iAnzahl <= 0) {
                    JOptionPane.showMessageDialog(this, "Die Anzahl muss eine positive Zahl (mindestens 1) sein!", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Espresso
                String sMilk;
                boolean bVanille, bCaramell, bPistazie;
                if (sDrink.equals("Espresso")) {
                    sMilk = "Keine"; // Espresso hat keine Milch
                    bVanille = false;
                    bCaramell = false;
                    bPistazie = false;
                } else {
                    //normales Auslesen für alle anderen Getränke
                    sMilk = cbxMilch.getSelectedItem().toString();
                    bVanille = vanilleRadioButton.isSelected();
                    bCaramell = caramellRadioButton.isSelected();
                    bPistazie = pistazieRadioButton.isSelected();
                }

                // Objekt erstellen und speichern
                CoffeeOrder order = new CoffeeOrder(
                        sDrink, bVanille, sSize, bCaramell, bPistazie, sMilk, iAnzahl);

                coffeeOrderlist.add(order);
                ausgeben();

                } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Fehler: 'Anzahl' muss eine Zahl sein!", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
            }
        }

        public void filtereNachVegan(){
        // 1. Textfeld leeren
            taAusgabe.setText("");

        // 2. Schleife über die Liste
        for (CoffeeOrder order : coffeeOrderlist) {
            //3. Selektive Auswahl (Filter)
            //wir prüfen, ob das Attribut sMilk dem Filter entspricht
            if (order.getsMilk().equals("Hafermilch") || order.getsMilk().equals("Sojamilch")){
                String alterText = taAusgabe.getText();
                String neuerEintrag = order.ausgeben() + "---------------\n";
                taAusgabe.setText(alterText + neuerEintrag);
            }
        }

        // falls nicht gefunden wurde
            if (taAusgabe.getText().equals("")){
                taAusgabe.setText("Keine vegane Bestellung vorhanden.");
            }
    }

    public static void main(String[] args) {
        new CoffeeShop();

    }
}