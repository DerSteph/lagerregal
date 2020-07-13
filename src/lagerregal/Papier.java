package lagerregal;

public class Papier extends Produkt{
	private String Farbe;
	private String Größe;
	private int Kosten;
	public Papier(String Lagerungsart, String Farbe, String Größe, int Kosten)
	{
		super();
		this.setLagerungsart(Lagerungsart);
		this.setKosten(Kosten);
		this.Farbe = Farbe;
		this.Größe = Größe;
	}
	public String getFarbe() {
		return Farbe;
	}
	public void setFarbe(String farbe) {
		Farbe = farbe;
	}
	public String getGröße() {
		return Größe;
	}
	public void setGröße(String größe) {
		Größe = größe;
	}
	public int getKosten() {
		return Kosten;
	}
	public void setKosten(int kosten) {
		Kosten = kosten;
	}
	@Override
	public String getInhalt() {
		return this.getClass().getSimpleName() + ", " + this.getFarbe() + ", " + this.getGröße();
	}
}
