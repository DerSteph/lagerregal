package lagerregal;

public class Holz extends Produkt {
	private String Art;
	private String Form;
	public Holz(String Lagerungsart, String Art, String Form, int Kosten) {
		super();
		this.setLagerungsart(Lagerungsart);
		this.setKosten(Kosten);
		this.Art = Art;
		this.Form = Form;
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
	@Override
	public String getInhalt() {
		return this.getClass().getSimpleName() + ", " + this.getArt() + ", " + this.getForm();
	}
}
