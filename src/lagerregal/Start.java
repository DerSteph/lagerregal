package lagerregal;

import java.net.URL;

import display.MainWindow;
import display.MainWindowLayered;
import display.MainWindowNew;

public class Start {
	public static int zaehler;
	public static CSV datei;
	public static Lager lager;
	public static Bilanz bilanz;
	//public static MainWindow window;
	public static MainWindowLayered window;
	public static void main(String[] args) {
		try
		{
			URL name = Start.class.getResource("auftrag.csv");
			datei = new CSV(name.toString().substring(5));
		}
		catch(Exception e) {
			System.out.println("Datei kann nicht geladen werden.");
		}
		lager = new Lager();
		bilanz = new Bilanz();
		//window = new MainWindow();
		window = new MainWindowLayered();
	}
}
