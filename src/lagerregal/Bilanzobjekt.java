package lagerregal;

import java.time.LocalDateTime;

public class Bilanzobjekt {
	private Produkt Produkt;
	private String Grund;
	private int Kosten;
	private LocalDateTime Zeit;
	public Bilanzobjekt(Produkt produkt)
	{
		this.Produkt = produkt;
		this.Grund = produkt.getLagerungsart();
		this.Kosten = produkt.getKosten();
		setZeit(LocalDateTime.now());
	}
	public Bilanzobjekt(Produkt produkt, String grund)
	{
		this.Produkt = produkt;
		this.Grund = grund;
		setZeit(LocalDateTime.now());
		if(grund == "Verschrottung")
		{
			this.Kosten = 500;
		}
		if(grund == "Umlagern")
		{
			this.Kosten = 100;
		}
		if(grund == "Abgelehnt")
		{
			this.Kosten = produkt.getKosten();
		}
	}
	public Produkt getProdukt() {
		return Produkt;
	}
	public void setProdukt(Produkt produkt) {
		Produkt = produkt;
	}
	public String getGrund() {
		return Grund;
	}
	public void setGrund(String grund) {
		Grund = grund;
	}
	public int getKosten() {
		return Kosten;
	}
	public void setKosten(int kosten) {
		Kosten = kosten;
	}
	public LocalDateTime getZeit() {
		return Zeit;
	}
	public void setZeit(LocalDateTime zeit) {
		Zeit = zeit;
	}
}
