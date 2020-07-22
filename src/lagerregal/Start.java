package lagerregal;

import java.net.URL;

import display.MainWindowLayered;

public class Start {
	public static Lager lager;
	public static Bilanz bilanz;
	//public static MainWindow window;
	public static MainWindowLayered window;
	public static void main(String[] args) {
		lager = new Lager();
		bilanz = new Bilanz();
		//window = new MainWindow();
		window = new MainWindowLayered();
	}
}
