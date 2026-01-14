package Projektarbeit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeOrderTest {

    @Test
    //Szenario 1: Standard Bestellung
    public void testBerechnePreis() {
        //Espresso (2.70), Small (+0.0), Milch (+0.0), 1 Stück
        //Erwartung: 2.70 * 1 = 2.70
        CoffeeOrder tesOrder = new CoffeeOrder("Espresso", false, "Small", false, false,false, "Milch", 1);

        //assertEquals(Erwartung, Ergebnis der Methode, Toleranz bie Kommazahlen)
        assertEquals(2.70, tesOrder.berechnePreis(), 0.001); //0.001 technisch notwendig, wenn man mit Kommazahlen rechnet sonst: Ungenauigkeit bei Fließkommazahlen, solange der Unterschied kleiner als 0.0001 ist gilt der Test als bestanden
    }

    @Test
    //Szenario 2: Komplexe Bestellung mit Extras
    public void testBeechnePreisExtras() {
        //Latte Macchiato (5.00), Large (+2.00), Pistazie (+1.50), Hafermilch (+0.50), 1 Stück
        //Erwartung: (5.00 + 2.00 + 1.50 + 0.50) * 1 = 9.00
        CoffeeOrder order = new CoffeeOrder("Latte Macchiato", false, "Large", false, true, false, "Hafermilch", 1);

        assertEquals(9.00, order.berechnePreis(), 0.001);
    }

    @Test
    //Sznario 3: Mengen-Test (Multiplikation prüfen)
    public void testBerechnePreisMulitplikation() {
        //Americano (4.00), Medium (+1.00), keine Extras, Milch (+0.00), 3 Stück
        //Erwartung: (4.00 + 1.00) *3 = 15.00
        CoffeeOrder order = new CoffeeOrder("Americano", false, "Medium", false, false, true, "Milch", 3);

        assertEquals(15.00, order.berechnePreis(), 0.001);
    }
}