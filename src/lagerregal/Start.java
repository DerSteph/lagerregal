package lagerregal;

import display.MainWindow;
import display.MainWindowNew;

public class Start {
	public static int zaehler;
	public static CSV datei;
	public static Lager lager;
	public static Bilanz bilanz;
	//public static MainWindow window;
	public static MainWindowNew window;
	public static void main(String[] args) {
		try
		{
			datei = new CSV("auftrag.csv");
		}
		catch(Exception e) {
			System.out.println("Datei kann nicht geladen werden.");
		}
		lager = new Lager();
		bilanz = new Bilanz();
		//window = new MainWindow();
		window = new MainWindowNew();
		window.setVisible(true);
	}
}
