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
    private JComboBox cbxDrink;
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
    private JLabel lblAnzahl;
    private JButton btnFilterVeganbutton;
    private JTextArea taVeganAusgabe;
    private JLabel lblBild1;
    private JLabel lblBild2;
    private JRadioButton keineRadioButton;

    // ArrayList
    private ArrayList <CoffeeOrder> coffeeOrderlist = new ArrayList<CoffeeOrder>();

    public CoffeeShop() throws HeadlessException {
        setTitle("Eingabe für die Bestellung im Coffee Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 1200);
        setContentPane(jpCoffePanel);
        setVisible(true);
        initObjekte();

        // Flavour-Group initialiseren (damit nur 1 wählbar ist) --> Gemini(KI) als Hilfe
        flavourGroup = new ButtonGroup();
        flavourGroup.add(vanilleRadioButton);
        flavourGroup.add(caramellRadioButton);
        flavourGroup.add(pistazieRadioButton);
        flavourGroup.add(keineRadioButton);


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
            clear();
            }
        });

        // Gesamtpreis
        btnOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                berechneGesamtsumme();
            }
        });

        // Vegan Filter
        setVisible(true);
        btnFilterVeganbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtereNachVegan();
            }
        });

        // wenn Espresso ausgewählt wird
        cbxDrink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                espresso();
            }
        });
    }
        // Methoden

        public void initObjekte(){
        coffeeOrderlist.add(new CoffeeOrder("Americano", false,"Small", false, false, true, "Milch", 1));
            coffeeOrderlist.add(new CoffeeOrder("Cappuccino", true,"Medium", false, false, false,"Hafermilch (+0,50)", 1));
            coffeeOrderlist.add(new CoffeeOrder("Latte Macchiato", false,"Large", true, false, false, "Sojamilch (+0,50)", 1));
            ausgeben();
        }


        public void ausgeben(){
            taAusgabe.setText(""); //vorher leeren
            for (CoffeeOrder order: coffeeOrderlist){ //for each Schleife
                taAusgabe.append(order.ausgeben() + "\n");
            }


    }

        public void berechneGesamtsumme(){
            double dSumme = 0.00;
            for (CoffeeOrder order : coffeeOrderlist){
                dSumme += order.berechnePreis();
            }
            jtGesamtpreisText.setText(dSumme+ " €");
            }

        public void speichern(){
            try {
                // wenn Anzahl keine Zahl: Fehlermeldung
                if (jtAnzahl.getText().isEmpty()){
                    JOptionPane.showMessageDialog(this,
            "Bitte geben Sie eine Anzahl ein!", "Eingabefehler", JOptionPane.WARNING_MESSAGE);
                    return;
                }


                // Zahl umwandeln
                int iAnzahl = Integer.parseInt(jtAnzahl.getText());

                String sDrink = cbxDrink.getSelectedItem().toString();
                String sSize = cbxGroeße.getSelectedItem().toString();

                // Auslesen der ComboBoxen
                if (sDrink.equals("- Wähle dein Getränk -")) {
                    JOptionPane.showMessageDialog(this, "Bitte wähle ein Getränk aus!", "Fehler", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (sSize.equals("- Wähle die Größe -")){
                    JOptionPane.showMessageDialog(this, "Wähle eine Größe aus!","Fehler", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // wenn negative Zahl oder null: Fehlermeldung
                if (iAnzahl <= 0) {
                    JOptionPane.showMessageDialog(this,
            "Die Anzahl muss eine positive Zahl (mindestens 1) sein!", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // wenn Espresso, dann kein Flavour, keine Milch
                String sMilk;
                boolean bVanille, bCaramell, bPistazie, bKeine;
                if (sDrink.equals("Espresso")) {
                    sMilk = "Keine"; // Espresso hat keine Milch
                    bVanille = false;
                    bCaramell = false;
                    bPistazie = false;
                    bKeine = false;
                } else {
                    //normales Auslesen für alle anderen Getränke
                    sMilk = cbxMilch.getSelectedItem().toString();
                    // Auswahl Milch - Fehlermeldung
                    if (sMilk.equals("- Wähle die Milch -")){
                        JOptionPane.showMessageDialog(this, "Bitte wähle eine Milchsorte aus!", "Fehler", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    bVanille = vanilleRadioButton.isSelected();
                    bCaramell = caramellRadioButton.isSelected();
                    bPistazie = pistazieRadioButton.isSelected();
                    bKeine = keineRadioButton.isSelected();
                }

                // Objekt erstellen und speichern
                CoffeeOrder order = new CoffeeOrder(
                        sDrink, bVanille, sSize, bCaramell, bPistazie, bKeine, sMilk, iAnzahl);

                coffeeOrderlist.add(order);
                ausgeben();

                } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
        "Fehler: 'Anzahl' muss eine Zahl sein!", "Eingabefehler", JOptionPane.ERROR_MESSAGE);
            }
        }

        public void filtereNachVegan(){
            taVeganAusgabe.setText("");
            //Schleife über die Liste
        for (CoffeeOrder order : coffeeOrderlist) {
            if (order.getsMilk().equals("Hafermilch (+0,50)") || order.getsMilk().equals("Sojamilch (+0,50)")){
                String alterText = taVeganAusgabe.getText();
                String neuerEintrag = order.ausgeben() + "\n";
                taVeganAusgabe.setText(alterText + neuerEintrag);
            }
        }

        // falls keine Vegane Option in Bestellung
            if (taVeganAusgabe.getText().equals("")){
                taVeganAusgabe.setText("Keine vegane Bestellung vorhanden.");
            }
    }

        public void clear(){
            taAusgabe.setText("");
            coffeeOrderlist.clear();
            jtGesamtpreisText.setText("");
            taVeganAusgabe.setText("");
            cbxDrink.setSelectedIndex(0);
            cbxMilch.setSelectedIndex(0);
            cbxGroeße.setSelectedIndex(0);
            jtAnzahl.setText("");
            keineRadioButton.setSelected(true);
            vanilleRadioButton.setSelected(false);
            pistazieRadioButton.setSelected(false);
            caramellRadioButton.setSelected(false);

        }

        public void espresso() {
            String ausgewaehlt = cbxDrink.getSelectedItem().toString();

            if (ausgewaehlt.equals("Espresso")) {
                //Felder deaktivieren --> Gemini(KI) als Hilfe
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

    public static void main(String[] args) {
        new CoffeeShop();

    }
}