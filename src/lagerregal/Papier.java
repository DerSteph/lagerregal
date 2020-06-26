package lagerregal;

public class Papier {
	private String Lagerungsart;
	private String Farbe;
	private String Größe;
	private int Kosten;
	public Papier(String Lagerungsart, String Farbe, String Größe, int Kosten)
	{
		this.Lagerungsart = Lagerungsart;
		this.Farbe = Farbe;
		this.Größe = Größe;
		this.Kosten = Kosten;
	}
	public String getLagerungsart() {
		return Lagerungsart;
	}
	public void setLagerungsart(String lagerungsart) {
		Lagerungsart = lagerungsart;
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
}
