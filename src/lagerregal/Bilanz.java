package lagerregal;

import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Bilanz {
	private int gesamtkonto = 0;
	private int gesamtplus = 0;
	private int gesamtminus = 0;
	private LinkedList<Bilanzobjekt> geldverlauf;
	public Bilanz() {
		geldverlauf = new LinkedList<Bilanzobjekt>();
	}
	public int getGesamtkonto() {
		return gesamtkonto;
	}
	public int getGesamtplus() {
		return gesamtplus;
	}
	public int getGesamtminus() {
		return gesamtminus;
	}
	public void addGesamtkonto(int geld, Produkt name) {
		this.gesamtkonto = this.gesamtkonto + geld;
		this.gesamtplus = this.gesamtplus + geld;
		geldverlauf.add(new Bilanzobjekt(name));
	}
	public void removeGesamtkonto(int geld, Produkt name) {
		this.gesamtkonto = this.gesamtkonto - geld;
		this.gesamtminus = this.gesamtminus - geld;
		geldverlauf.add(new Bilanzobjekt(name));
	}
	public void removeGesamtkonto(int geld, Produkt name, String grund) {
		this.gesamtkonto = this.gesamtkonto - geld;
		this.gesamtminus = this.gesamtminus - geld;
		geldverlauf.add(new Bilanzobjekt(name, grund));
	}
	public LinkedList<Bilanzobjekt> getGeldverlauf() {
		return geldverlauf;
	}
	public void getGesamtverlauf()
	{
		for(Bilanzobjekt e: geldverlauf)
		{
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			System.out.println(dtf.format(e.getZeit()) + ": " + e.getGrund() + " " + e.getKosten() + " " + e.getProdukt().getClass().getSimpleName());
		}
	}
	/*
	 * TODO: Anzeige von Umsätzen, und Eintrag der Ereignisse in eine Liste
	 * 
	 * 
	 * 
	 * 
	 */
}
