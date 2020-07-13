package lagerregal;

public class Produkt {
	private String Lagerungsart;
	private int Kosten;
	public String getLagerungsart() {
		return Lagerungsart;
	}
	public void setLagerungsart(String lagerungsart) {
		Lagerungsart = lagerungsart;
	}
	public int getKosten() {
		return Kosten;
	}
	public void setKosten(int kosten) {
		Kosten = kosten;
	}
	public String toString() {
		return this.getClass().getSimpleName();
	}
	public String toSmallString () {
		switch(this.toString())
		{
		case "Papier":
			return "P";
		case "Stein":
			return "S";
		case "Holz":
			return "H";
		default:
			return "x";
		}
	}
}
