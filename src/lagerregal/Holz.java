package lagerregal;

public class Holz {
	private String Lagerungsart;
	private String Art;
	private String Form;
	private int Kosten;
	public Holz(String Lagerungsart, String Art, String Form, int Kosten) {
		this.Lagerungsart = Lagerungsart;
		this.Art = Art;
		this.Form = Form;
		this.Kosten = Kosten;
	}
	public String getLagerungsart() {
		return Lagerungsart;
	}
	public void setLagerungsart(String lagerungsart) {
		Lagerungsart = lagerungsart;
	}
	public String getArt() {
		return Art;
	}
	public void setArt(String art) {
		Art = art;
	}
	public String getForm() {
		return Form;
	}
	public void setForm(String form) {
		Form = form;
	}
	public int getKosten() {
		return Kosten;
	}
	public void setKosten(int kosten) {
		Kosten = kosten;
	}

}
