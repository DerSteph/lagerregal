package lagerregal;

public class Test {
	public static int zaehler;
	public static void main(String[] args) {
		try
		{
			LeseCSV.ladeDatei("C:\\Users\\plays\\OneDrive\\Dokumente\\GitHub\\lagerregal\\src\\lagerregal\\auftrag.csv");
		}
		catch(Exception e) {
			System.out.println("Datei kann nicht geladen werden.");
		}
		Grafik test = new Grafik();
	}
}
