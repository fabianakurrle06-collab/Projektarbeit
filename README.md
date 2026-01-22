Coffee Shop Bestellsystem


Dieses Java Projekt wurde von Marie Barke und Fabiana Kurrle im Rahmen der Projektarbeit für das Modul Programmiertechnik IMUK 1 (WiSe 2025/2026) programmiert. Es ermöglicht die Aufnahme, Verwaltung und Filterung von Kaffeebestellungen über eine grafische Benutzeroberfläche.

So funktioniert es:
1.	CoffeShop Datei durchführen lassen
2.	Getränk (Drink), Größe (Size), Geschmack (Flavour) und Milchsorte (Milk) auswählen und eine Zahl bei „Anzahl“ für Ihre gewünschte Menge an Kaffee hinzufügen
3.	Speicher Button tätigen. Automatisierte Prüfung auf Fehlereingaben durch Try-Catch (zB. „Keine Zahl" bei „Anzahl“ oder auch „kein Getränk/Größe/Milchsorte ausgewählt")
4.	Speicherung aller Bestellungen in einer ArrayList<CoffeeOrder>
5.	Durch Vegan Button die veganen Optionen in der Bestellung anzeigen lassen (Hafer-/Sojamilch)
6.	Und durch den Order Button den Gesamtpreis aller Objekte in der Bestellung (ArrayList) anzeigen lassen
7.	Zum Schluss werden durch den Clear Button die ausgegebene Bestellung, Preis und Vegane Optionen gelöscht und die Comboboxen zurück auf „Wähle … aus“ gesetzt

Projektstruktur:
-	Src/Projektarbeit/CoffeeShop.java: Hauptklasse für die grafische Oberfläche (GUI), die ArrayList und alle Button-Aktivitäten
-	Src/Projektarbeit/CoffeeOrder.java: Kernlogik für Preisberechnung und die Datenspeicherung
-	Test/Projektarbeit/CoffeeOrderTest.java: JUnit-Tests (berechnePreis) zur Sicherstellung, dass Preise immer korrekt berechnet werden 
