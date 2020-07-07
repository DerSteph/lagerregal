package lagerregal;

public class Test {
	public static int zaehler;
	public static LeseCSV test;
	public static Lager lager;
	public static Bilanz bilanz;
	public static void main(String[] args) {
		try
		{
			test = new LeseCSV("C:\\auftrag.csv");
		}
		catch(Exception e) {
			System.out.println("Datei kann nicht geladen werden.");
		}
		lager = new Lager();
		bilanz = new Bilanz();
		test.leseZeile();
		test.leseZeile();
		test.leseZeile();
		lager.AuftragAbarbeiten(lager.GetAuftrag(0), 3);
		lager.AuftragAbarbeiten(lager.GetAuftrag(1), 0);
		lager.AuftragAbarbeiten(lager.GetAuftrag(2), 11);
		lager.getLagerinhalt();
		System.out.println(bilanz.getGesamtkonto());
		test.leseZeile();
		lager.AuftragAbarbeiten(lager.GetAuftrag(0), 0);
		lager.Verschrotten(11);
		lager.getLagerinhalt();
		System.out.println(bilanz.getGesamtkonto());
		//Grafik test = new Grafik();
	}
}
