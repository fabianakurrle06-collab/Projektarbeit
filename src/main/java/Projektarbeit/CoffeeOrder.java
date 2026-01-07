package Projektarbeit;

public class CoffeeOrder {
    private String sDrink;
    private String sSize;
    private boolean bVanille;
    private boolean bCaramell;
    private boolean bPistazie;
    private String sMilk;
    private int iAnzahl;

    public CoffeeOrder(String sDrink, boolean bVanille, String sSize, boolean bCaramell, boolean bPistazie, String sMilk, int iAnzahl) {
        this.sDrink = sDrink;
        this.bVanille = bVanille;
        this.sSize = sSize;
        this.bCaramell = bCaramell;
        this.bPistazie = bPistazie;
        this.sMilk = sMilk;
        this.iAnzahl = iAnzahl;
    }

    public String ausgeben() {
            String flavour = "";
            if (bVanille) flavour += "Vanille ";
            if (bCaramell) flavour += "Caramell ";
            if (bPistazie) flavour += "Pistazie ";

            // wenn String leer, dann "keine"
            if (flavour.isEmpty()){
                flavour = "keine";
            }

            return "Drink: " + sDrink + " (" + iAnzahl + "x)" +
                    "\nGröße: " + sSize +
                    "\nFlavour: " + flavour +
                    "\nMilch: " + sMilk +
                    "\nPreis: " + berechnePreis() + " €\n";
        }


    public double berechnePreis() {
        double preis = 0.00;



        if (sDrink.equals("Espresso")) preis = 2.70;
        if (sDrink.equals("Cappuccino")) preis = 3.50;
        if (sDrink.equals("Latte Macchiato")) preis = 5.00;
        if (sDrink.equals("Americano")) preis = 4.00;

        if (sSize.equals("Small")) preis += 0.00;
        if (sSize.equals("Medium")) preis += 1.00;
        if (sSize.equals("Large")) preis += 2.00;

        if (bVanille) preis += 0.50;
        if (bCaramell) preis += 1.00;
        if (bPistazie) preis += 1.50;

        if (sMilk.equals("Milch")) preis += 0.00;
        else
            preis += 0.50;


        return preis * iAnzahl;
    }

    //diese Methode braucht die Tabelle um die Werte anzuzeigen
    public String getsDrink(){
        return sDrink;
    }
    public String getsSize(){
        return sSize;
    }
    public String getsMilk(){
        return sMilk;
    }
    public int getiAnzahl(){
        return iAnzahl;
    }
}
