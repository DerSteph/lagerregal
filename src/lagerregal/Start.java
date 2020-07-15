package lagerregal;

import display.MainWindow;

public class Start {
	public static int zaehler;
	public static CSV test;
	public static Lager lager;
	public static Bilanz bilanz;
	public static void main(String[] args) {
		try
		{
			test = new CSV("C:\\auftrag.csv");
		}
		catch(Exception e) {
			System.out.println("Datei kann nicht geladen werden.");
		}
		lager = new Lager();
		bilanz = new Bilanz();
		MainWindow test = new MainWindow();
	}
}
