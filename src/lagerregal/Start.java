package lagerregal;

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
		lager.AuftragHinzufuegen();
		lager.AuftragHinzufuegen();
		lager.AuftragHinzufuegen();
		lager.printAuftraege();
		lager.AuftragAbarbeiten(0, 7);
		lager.AuftragAbarbeiten(1, 8);
		lager.AuftragAbarbeiten(2, 11);
		lager.AuftragHinzufuegen();
		lager.AuftragHinzufuegen();
		lager.AuftragHinzufuegen();
		lager.AuftragEntfernen(0);
		lager.AuftragEntfernen(1);
		lager.AuftragEntfernen(2);
		lager.AuftragHinzufuegen();
		lager.AuftragHinzufuegen();
		lager.printAuftraege();
		lager.AuftragAbarbeiten(1, 6);
		lager.getLagerinhalt();
		//lager.zeigeFreiePlaetze(6);
		/*lager.AuftragAbarbeiten(lager.GetAuftrag(0), 0);
		lager.Verschrotten(11);
		lager.getLagerinhalt();
		System.out.println(bilanz.getGesamtkonto());*/
		//Grafik test = new Grafik();
	}
}
