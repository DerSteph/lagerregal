package lagerregal;

import java.util.*;

public class Lager {
	private double Umsatz;
	private ArrayList<Produkt> inhalt;
	private Queue schlange = new LinkedList();
	private int schlangelaenge = 0;
	public Lager() {
		this.inhalt = new ArrayList<Produkt>();
		for(int i = 0; i < 27; i++) {
			inhalt.add(i, null);
		}
		this.Umsatz = 0;
	}
	public boolean AuftragHinzufuegen(Object objekt) {
		if(schlangelaenge == 3)
		{
			return false;
		}
		else
		{
			schlange.add(objekt);
			schlangelaenge++;
			return true;
		}
	}
	public boolean AuftragEntfernen() {
		if(schlangelaenge == 0) {
			return false;
		}
		else
		{
			schlange.remove();
			schlangelaenge--;
			return true;
		}
	}
	public Object naechsterAuftrag() {
		return schlange.element();
	}
	public void getLagerinhalt() {
		System.out.println("");
		for(int i = 0; i < 27; i++)
		{
			if(i % 9 == 0) 
			{
				System.out.println();
			}
			if(i % 3 == 0)
			{
				System.out.println();
			}
			if(inhalt.get(i) instanceof Papier)
			{
				System.out.print(" P ");	
			}
			else if(inhalt.get(i) instanceof Stein)
			{
				System.out.print(" S ");
			}
			else if(inhalt.get(i) instanceof Holz)
			{
				System.out.print(" H ");
			}
			else
			{
				System.out.print(" x ");
			}
		}
	}
	public boolean AuftragAbarbeiten(Object auftrag, int lagerplatz) {
		if(lagerplatz > 26 || lagerplatz < 0)
		{
			System.out.println("Der Lagerplatz ist außerhalb des möglichen");
			return false;
		}
		if(((Produkt) auftrag).getLagerungsart() == "Einlagerung")
		{
			if(auftrag instanceof Papier)
			{
				if(inhalt.get(lagerplatz) == null) {
					inhalt.add(lagerplatz, (Produkt) auftrag);
					Test.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten());
					System.out.println("Erfolgreich eingelagert.");
					return true;
				}
				else
				{
					System.out.println("Der Lagerplatz ist bereits belegt.");
					return false;
				}
			}
			else if(auftrag instanceof Holz)
			{
				boolean prüfeObPlatzVerfügbar = true;
				if(lagerplatz < 9) {
					for(int i = 0; i < 9; i++) {
						if(inhalt.get(i) != null)
						{
							prüfeObPlatzVerfügbar = false;
						}
					}
					if(prüfeObPlatzVerfügbar == true) {
						for(int i = 0; i < 9; i++) {
							inhalt.add(i, (Produkt) auftrag);
						}
						Test.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten());
						System.out.println("Erfolgreich eingelagert.");
						return true;
					}
					else
					{
						System.out.println("Die Lagerplatzreihe ist bereits durch andere Dinge belegt.");
						return false;
					}
				}
				else if(lagerplatz < 18) {
					for(int i = 9; i < 18; i++) {
						if(inhalt.get(i) != null)
						{
							prüfeObPlatzVerfügbar = false;
						}
					}
					if(prüfeObPlatzVerfügbar == true) {
						for(int i = 9; i < 18; i++) {
							inhalt.add(i, (Produkt) auftrag);
						}
						Test.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten());
						System.out.println("Erfolgreich eingelagert.");
						return true;
					}
					else
					{
						System.out.println("Die Lagerplatzreihe ist bereits durch andere Dinge belegt.");
						return false;
					}
				}
				else {
					for(int i = 19; i < 27; i++) {
						if(inhalt.get(i) != null)
						{
							prüfeObPlatzVerfügbar = false;
						}
					}
					if(prüfeObPlatzVerfügbar == true) {
						for(int i = 19; i < 27; i++) {
							inhalt.add(i, (Produkt) auftrag);
						}
						Test.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten());
						System.out.println("Erfolgreich eingelagert.");
						return true;
					}
					else
					{
						System.out.println("Die Lagerplatzreihe ist bereits durch andere Dinge belegt.");
						return false;
					}
				}
			}
			else if(auftrag instanceof Stein) {
				if(lagerplatz < 9) {
					if(inhalt.get(lagerplatz) == null) {
						inhalt.add(lagerplatz, (Produkt) auftrag);
						Test.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten());
						System.out.println("Erfolgreich eingelagert.");
						return true;
					}
					else
					{
						System.out.println("Der Lagerplatz ist bereits belegt.");
						return false;
					}
				}
				else
				{
					System.out.println("Steine können nur unten gelagert werden!");
					return false;
				}
			}
			else {
				System.out.println("Entspricht keiner Lagersache.");
				return false;
			}
			// Zeige alle freien Plätzen
		}
		else
		{
			Test.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten());
		}
		return false;
	}
}
