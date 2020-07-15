package lagerregal;

public class Papier extends Produkt{
	private String Farbe;
	private String Groesse;
	private int Kosten;
	public Papier(String Lagerungsart, String Farbe, String Groesse, int Kosten)
	{
		super();
		this.setLagerungsart(Lagerungsart);
		this.setKosten(Kosten);
		this.Farbe = Farbe;
		this.Groesse = Groesse;
	}
	public String getFarbe() {
		return Farbe;
	}
	public void setFarbe(String farbe) {
		Farbe = farbe;
	}
	public String getGroesse() {
		return Groesse;
	}
	public void setGroesse(String groesse) {
		Groesse = groesse;
	}
	public int getKosten() {
		return Kosten;
	}
	public void setKosten(int kosten) {
		Kosten = kosten;
	}
	@Override
	public String getInhalt() {
		return this.getClass().getSimpleName() + ", " + this.getFarbe() + ", " + this.getGroesse();
	}
}
