package lagerregal;

public class Test {
	public static int zaehler;
	public static LeseCSV test;
	public static Lager lager;
	public static void main(String[] args) {
		try
		{
			test = new LeseCSV("C:\\auftrag.csv");
		}
		catch(Exception e) {
			System.out.println("Datei kann nicht geladen werden.");
		}
		lager = new Lager();
		Grafik test = new Grafik();
	}
}
