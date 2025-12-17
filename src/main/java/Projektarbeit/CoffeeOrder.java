package Projektarbeit;

public class CoffeeOrder {
    private String sDrink;
    private String sSize;
    private boolean bVanille;
    private boolean bCaramell;
    private boolean bPistazie;
    private String sMilk;

    public CoffeeOrder(String sDrink, boolean bVanille, String sSize, boolean bCaramell, boolean bPistazie, String sMilk) {
        this.sDrink = sDrink;
        this.bVanille = bVanille;
        this.sSize = sSize;
        this.bCaramell = bCaramell;
        this.bPistazie = bPistazie;
        this.sMilk = sMilk;
    }

    public String ausgeben() {
            String flavour = "";
            if (bVanille) flavour += "Vanille ";
            if (bCaramell) flavour += "Caramell ";
            if (bPistazie) flavour += "Pistazie ";

            return "Drink: " + sDrink +
                    "\nGröße: " + sSize +
                    "\nFlavour: " + flavour +
                    "\nMilch: " + sMilk +
                    "\nPreis: " + berechnePreis() + " €\n";
        }


    public double berechnePreis() {
        double preis = 3.0; // Basispreis

        if (sSize.equals("Mittel")) preis += 1.0;
        if (sSize.equals("Groß")) preis += 2.0;

        if (bVanille) preis += 0.5;
        if (bCaramell) preis += 0.5;
        if (bPistazie) preis += 0.7;

        if (sMilk.equals("Hafer")) preis += 0.5;

        return preis;
    }

}
