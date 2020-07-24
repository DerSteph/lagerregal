package lagerregal;

import display.MainWindowLayered;

public class Start {
	public static Lager lager;
	public static Bilanz bilanz;
	public static MainWindowLayered window;

	public static void main(String[] args) {
		// Erstelle Lager
		lager = new Lager();
		// Erstelle Bilanz
		bilanz = new Bilanz();
		// Erstelle Fenster
		window = new MainWindowLayered();
	}
}
