package lagerregal;

public class Stein extends Produkt {
	private String Art;
	private String Gewicht;
	public Stein(String Lagerungsart, String Art, String Gewicht, int Kosten) {
		super();
		this.setLagerungsart(Lagerungsart);
		this.setKosten(Kosten);
		this.Art = Art;
		this.Gewicht = Gewicht;
	}
	public String getArt() {
		return Art;
	}
	public void setArt(String art) {
		Art = art;
	}
	public String getGewicht() {
		return Gewicht;
	}
	public void setGewicht(String gewicht) {
		Gewicht = gewicht;
	}
	@Override
	public String getInhalt() {
		return this.getClass().getSimpleName() + ", " + this.getArt() + ", " + this.getGewicht();
	}
}
