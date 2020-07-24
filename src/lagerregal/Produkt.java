package lagerregal;

public class Produkt {
	private String Lagerungsart;
	private int Kosten;

	// als Vorfunktion für die geerbten Klassen
	public String getInhalt() {
		return null;
	}

	public String getInhaltFormatted() {
		return null;
	}

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

	// Für Konsolenausgabe
	public String toShortString() {
		switch (this.toString()) {
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

	public String toShortStringSmall() {
		switch (this.toString()) {
		case "Papier":
			return "p";
		case "Stein":
			return "s";
		case "Holz":
			return "h";
		default:
			return "x";
		}
	}
}
