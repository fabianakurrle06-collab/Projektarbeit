package Projektarbeit;

public class CoffeOrder {
    private String sDrink;
    private String sSize;
    private boolean bVanille;
    private boolean bCaramell;
    private boolean bPistazie;
    private String sMilk;

    public CoffeOrder(String sDrink, boolean bVanille, String sSize, boolean bCaramell, boolean bPistazie, String sMilk) {
        this.sDrink = sDrink;
        this.bVanille = bVanille;
        this.sSize = sSize;
        this.bCaramell = bCaramell;
        this.bPistazie = bPistazie;
        this.sMilk = sMilk;
    }

    public String ausgeben() {
        return "Drink: \" + sDrink + \", Size: \" + sSize +
    }
}
