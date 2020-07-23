package lagerregal;

import java.net.URL;
import java.util.*;

public class Lager {
	private ArrayList<Produkt> lagerinhalt;
	private ArrayList<Produkt> auftragsliste;
	//private ArrayList<Aufgabe> aufgabeninhalt;
	private int listlaenge = 0;
	private CSV datei;
	public Lager() {
		this.lagerinhalt = new ArrayList<Produkt>();
		for(int i = 0; i < 27; i++) {
			lagerinhalt.add(i, null);
		}
		this.auftragsliste = new ArrayList<Produkt>();
		for(int i = 0; i < 3; i++)
		{
			auftragsliste.add(i, null);
		}
		/*this.aufgabeninhalt = new ArrayList<Aufgabe>();
		for(int i = 0; i < 27; i++)
		{
			aufgabeninhalt.add(i, null);
		}*/
		try
		{
			URL name = Start.class.getResource("auftrag.csv");
			datei = new CSV(name.toString().substring(5)); // laesst das "file://" am Anfang weg
		}
		catch(Exception e) {
			System.out.println("Datei kann nicht geladen werden.");
		}
	}
	// Fuegt Auftrag der Auftragsliste hinzu
	public boolean auftragHinzufuegen() {
		if(listlaenge == 3)
		{
			System.out.println("Die Auftragsliste ist voll");
			return false;
		}
		else
		{
			if(getAuftrag(0) == null)
			{
				auftragsliste.set(0, datei.firstSchlange());
			}
			else if(getAuftrag(1) == null) {
				auftragsliste.set(1, datei.firstSchlange());
			}
			else if(getAuftrag(2) == null) {
				auftragsliste.set(2, datei.firstSchlange());
			}
			else
			{
				auftragsliste.set(listlaenge, datei.firstSchlange());
			}
			datei.removeSchlange();
			listlaenge++;
			return true;
		}
	}
	// Stellt Auftrag wieder hinten an die Schlange an
	public boolean auftragZurueckstellen(int nummer) {
		if(listlaenge == 0) {
			return false;
		}
		else
		{
			datei.addSchlange(auftragsliste.get(nummer));
			auftragsliste.set(nummer, null);
			listlaenge--;
			return true;
		}
	}
	// Loescht den Auftrag vollstaendig!
	public boolean auftragAblehnen(int nummer) {
		if(listlaenge == 0) {
			return false;
		}
		else
		{
			Start.bilanz.removeGesamtkonto(getAuftrag(nummer).getKosten(), getAuftrag(nummer), "Abgelehnt");
			auftragsliste.set(nummer, null);
			listlaenge--;
			return true;
		}
	}
	// Löscht den Auftrag für das Abschliessen eines Auftrags
	private boolean auftragLoeschen(int nummer) {
		if(listlaenge == 0) {
			return false;
		}
		else
		{
			auftragsliste.set(nummer, null);
			listlaenge--;
			return true;
		}
	}

	// Rufe Produkt ab in der Auftragsliste
	public Produkt getAuftrag(int nummer) {
		return auftragsliste.get(nummer);
	}
	
	// Rufe Produkt ab im Lager
	public Produkt getInhalt(int nummer) {
		return lagerinhalt.get(nummer);
	}
	
	public void consolePrintAuftraege() {
		System.out.println("Auftraege zur Abarbeitung:");
		for(int i = 0; i < 3; i++)
		{
			if(getAuftrag(i) == null)
			{
				System.out.println("Platz " + i + ": ---");
			}
			else
			{
				System.out.println("Platz " + i + ": " + getAuftrag(i).toString());	
			}
		}
	}
	public void consolePrintGesamtesLager() {
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
			if(lagerinhalt.get(i) instanceof Papier)
			{
				System.out.print(" P ");	
			}
			else if(lagerinhalt.get(i) instanceof Stein)
			{
				System.out.print(" S ");
			}
			else if(lagerinhalt.get(i) instanceof Holz)
			{
				System.out.print(" H ");
			}
			else
			{
				System.out.print(" _ ");
			}
		}
		System.out.println();
	}
	
	public boolean isAuftragListeVoll() {
		if(listlaenge == 3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	// Abarbeiten des Auftrags für Einlagerung und Auslagerung
	public boolean auftragAbarbeiten(int auswahl, int lagerplatz) {
		if(auswahl > 3 || auswahl < 0)
		{
			System.out.println("Es koennen nur aktive Auftraege zwischen 0 und 2 gewaehlt werden!");
		}
		Produkt auftrag = (Produkt) getAuftrag(auswahl);
		if(lagerplatz > 26 || lagerplatz < 0)
		{
			System.out.println("Der Lagerplatz ist ausserhalb des moeglichen");
			return false;
		}
		if(((Produkt) auftrag).getLagerungsart().equals("Einlagerung"))
		{
			if(auftrag instanceof Papier)
			{
				if(lagerplatz % 9 >= 0 && lagerplatz % 9 <= 2)
				{
					for(int i = 1; i < 3; i++) {
						if(lagerinhalt.get(lagerplatz + i*3) != null) {
							System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
							return false;
						}
					}
				}
				if(lagerplatz % 9 >= 3 && lagerplatz % 9 <= 5)
				{
					if(lagerinhalt.get(lagerplatz + 3) != null) {
						System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
						return false;
					}
				}
				if(lagerinhalt.get(lagerplatz) == null) {
					lagerinhalt.set(lagerplatz, (Produkt) auftrag);
					auftragLoeschen(auswahl);
					Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
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
				// Extra Abfrage für Balken
				if(((Holz) auftrag).getForm().equals("Balken"))
				{
					int k = HolzLagerstelleAbfragen(lagerplatz);
					if(lagerinhalt.get(k) == null && lagerinhalt.get(k-3) == null && lagerinhalt.get(k-6) == null)
					{
						lagerinhalt.set(k, auftrag);
						lagerinhalt.set(k-3, auftrag);
						lagerinhalt.set(k-6, auftrag);
						auftragLoeschen(auswahl);
						Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
						System.out.println("Erfolgreich eingelagert.");
						return true;
					}
					else
					{
						System.out.println("Holzbalken koennen nur auf 3 Plaetzen hintereinander gelagert werden!");
						return false;
					}
				}
				else
				{
					if(lagerplatz % 9 >= 0 && lagerplatz % 9 <= 2)
					{
						for(int i = 1; i < 3; i++) {
							if(lagerinhalt.get(lagerplatz + i*3) != null) {
								System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
								return false;
							}
						}
					}
					if(lagerplatz % 9 >= 3 && lagerplatz % 9 <= 5)
					{
						if(lagerinhalt.get(lagerplatz + 3) != null) {
							System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
							return false;
						}
					}
					if(lagerinhalt.get(lagerplatz) == null) {
						lagerinhalt.set(lagerplatz, (Produkt) auftrag);
						auftragLoeschen(auswahl);
						Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
						System.out.println("Erfolgreich eingelagert.");
						return true;
					}
					else
					{
						System.out.println("Der Lagerplatz ist bereits belegt.");
						return false;
					}
				}
			}
			else if(auftrag instanceof Stein) {
				// Extra Abfrage für schwere Steine
				if(((Stein) auftrag).getGewicht().equals("Schwer"))
				{
					if(lagerplatz < 9) {
						if(lagerplatz % 9 >= 0 && lagerplatz % 9 <= 2)
						{
							for(int i = 1; i < 3; i++) {
								if(lagerinhalt.get(lagerplatz + i*3) != null) {
									System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
									return false;
								}
							}
						}
						if(lagerplatz % 9 >= 3 && lagerplatz % 9 <= 5)
						{
							if(lagerinhalt.get(lagerplatz + 3) != null) {
								System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
								return false;
							}
						}
						if(lagerinhalt.get(lagerplatz) == null) {
							lagerinhalt.set(lagerplatz, (Produkt) auftrag);
							auftragLoeschen(auswahl);
							Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
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
						System.out.println("Schwere Steine koennen nur unten gelagert werden!");
						return false;
					}	
				}
				else
				{
					if(lagerplatz % 9 >= 0 && lagerplatz % 9 <= 2)
					{
						for(int i = 1; i < 3; i++) {
							if(lagerinhalt.get(lagerplatz + i*3) != null) {
								System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
								return false;
							}
						}
					}
					if(lagerplatz % 9 >= 3 && lagerplatz % 9 <= 5)
					{
						if(lagerinhalt.get(lagerplatz + 3) != null) {
							System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
							return false;
						}
					}
					if(lagerinhalt.get(lagerplatz) == null) {
						lagerinhalt.set(lagerplatz, (Produkt) auftrag);
						auftragLoeschen(auswahl);
						Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
						System.out.println("Erfolgreich eingelagert.");
						return true;
					}
					else
					{
						System.out.println("Der Lagerplatz ist bereits belegt.");
						return false;
					}
				}
			}
			else {
				System.out.println("Entspricht keiner Lagersache.");
				return false;
			}
		}
		else if(((Produkt) auftrag).getLagerungsart().equals("Auslagerung"))
		{
			if(auftrag instanceof Papier)
			{
				if(lagerinhalt.get(lagerplatz) instanceof Papier)
				{
					if(((Papier) lagerinhalt.get(lagerplatz)).getFarbe().equals(((Papier) auftrag).getFarbe()))
					{
						if(((Papier) lagerinhalt.get(lagerplatz)).getGroesse().equals(((Papier) auftrag).getGroesse()))
						{
							if(checkObProduktNichtBlockiert(lagerplatz))
							{
								lagerinhalt.set(lagerplatz, null);
								auftragLoeschen(auswahl);
								Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
								System.out.println("Erfolgreich ausgelagert.");
								return true;	
							}
							else
							{
								System.out.println("Produkt ist blockiert.");
								return false;
							}
						}
					}
				}
			}
			else if(auftrag instanceof Holz)
			{
				if(lagerinhalt.get(lagerplatz) instanceof Holz)
				{
					if(((Holz) lagerinhalt.get(lagerplatz)).getArt().equals(((Holz) auftrag).getArt()))
					{
						if(((Holz) lagerinhalt.get(lagerplatz)).getForm().equals(((Holz) auftrag).getForm()))
						{
							if(((Holz) lagerinhalt.get(lagerplatz)).getForm().equals("Balken"))
							{
								int k = 0;
								if(lagerplatz < 9)
								{
									if(lagerplatz % 3 == 0)
									{
										k = 6;
									}
									if(lagerplatz % 3 == 1)
									{
										k = 7;
									}
									if(lagerplatz % 3 == 2)
									{
										k = 8;
									}
								}
								else if(lagerplatz < 18)
								{
									if(lagerplatz % 3 == 0)
									{
										k = 15;
									}
									if(lagerplatz % 3 == 1)
									{
										k = 16;
									}
									if(lagerplatz % 3 == 2)
									{
										k = 17;
									}
								}
								else
								{
									if(lagerplatz % 3 == 0)
									{
										k = 24;
									}
									if(lagerplatz % 3 == 1)
									{
										k = 25;
									}
									if(lagerplatz % 3 == 2)
									{
										k = 26;
									}
								}
								lagerinhalt.set(k, null);
								lagerinhalt.set(k-3, null);
								lagerinhalt.set(k-6, null);
								auftragLoeschen(auswahl);
								Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
								System.out.println("Erfolgreich ausgelagert.");
								return true;
							}
							else
							{
								lagerinhalt.set(lagerplatz, null);
								auftragLoeschen(auswahl);
								Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
								System.out.println("Erfolgreich ausgelagert.");
								return true;	
							}
						}
					}
				}
			}
			else if(auftrag instanceof Stein)
			{
				if(lagerinhalt.get(lagerplatz) instanceof Stein)
				{
					if(((Stein) lagerinhalt.get(lagerplatz)).getArt().equals(((Stein) auftrag).getArt()))
					{
						if(((Stein) lagerinhalt.get(lagerplatz)).getGewicht().equals(((Stein) auftrag).getGewicht()))
						{
							if(checkObProduktNichtBlockiert(lagerplatz)) {
								lagerinhalt.set(lagerplatz, null);
								auftragLoeschen(auswahl);
								Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
								System.out.println("Erfolgreich ausgelagert.");
								return true;	
							}
							else
							{
								System.out.println("Produkt ist blockiert.");
								return false;
							}
						}
					}
				}
			}
			else
			{
				System.out.println("Hat keine Funktion");
			}
		}
		else
		{
			System.out.print("Hat keine Funktion");
		}
		System.out.println("Irgendwas ist kaputt.");
		return false;
	}
	
	// Lagerplatzinhalt wird an der Stelle verschrottet
	public boolean Verschrotten(int lagerplatz) {
		if(lagerinhalt.get(lagerplatz) != null) {
			Produkt verschrottung = lagerinhalt.get(lagerplatz);
			if(lagerinhalt.get(lagerplatz) instanceof Papier || lagerinhalt.get(lagerplatz) instanceof Stein) {
				lagerinhalt.set(lagerplatz, null);
			}
			if(lagerinhalt.get(lagerplatz) instanceof Holz)
			{
				int k = HolzLagerstelleAbfragen(lagerplatz);
				lagerinhalt.set(k, null);
				lagerinhalt.set(k-3, null);
				lagerinhalt.set(k-6, null);
			}
			Start.bilanz.removeGesamtkonto(500, verschrottung, "Verschrottung");
			System.out.println("Erfolgreich verschrottet");
			return true;
		}
		System.out.println("An der Stelle existiert nichts.");
		return false;
	}
	
	// Prüfe, wo man das eingegeben Produkt im Lager setzen kann
	public int[] getArrayVonFreienPlaetzen(Produkt name)
	{
		// 0 = belegt, 1 = frei, 2 = ausgewaehlter Gegenstand
		int checkliste[] = new int[27];
		int ohneAbfrage = -1;
		for(int i = 0; i < checkliste.length; i++)
		{
			checkliste[i] = 1;
		}
		if(name != null)
		{
			if(name instanceof Papier)
			{
				// Gucken, welche Plaetze ueberhaupt frei sind!
				for(int i = 26; i > 0; i--)
				{
					if(checkliste[i] == 0)
					{
						continue;
					}
					if(ohneAbfrage != -1)
					{
						// Wenn der Gegenstand + 3 = Abgefragter Gegenstand ist
						if(i % 9 >= 3 && i % 9 <= 5 && i+3 == ohneAbfrage)
						{
							checkliste[i] = 1;
							continue;
						}
						if(i % 9 >= 0 && i % 9 <= 2 && i+6 == ohneAbfrage)
						{
							checkliste[i] = 1;
							continue;
						}
					}
					// Wenn Lagerplatz der abgefragte Gegenstand ist -> bei nächsten Schritt die hinteren Plätze blockieren
					if(lagerinhalt.get(i) == name)
					{
						ohneAbfrage = i;
						checkliste[i] = 2;
					}
					// Plätze dahinter blockieren, wenn Lagerplatz ein anderer Gegenstand ist
					else if(lagerinhalt.get(i) != null)
					{
						if(i % 9 >= 6 && i % 9 <= 8)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
							checkliste[i - 2*3] = 0;
						}
						if(i % 9 >= 3 && i % 9 <= 5)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
						}
						if(i % 9 >= 0 && i % 9 <= 2)
						{
							checkliste[i] = 0;
						}
					}
				}
			}
			if(name instanceof Stein)
			{
				for(int i = 26; i > 0; i--)
				{
					// spezielle Abfrage, falls Stein schwer ist!
					if(((Stein) name).getGewicht().equals("Schwer") && i > 8)
					{
						checkliste[i] = 0;
						continue;
					}
					if(checkliste[i] == 0)
					{
						continue;
					}
					if(ohneAbfrage != -1)
					{
						// Wenn der Gegenstand + 3 = Abgefragter Gegenstand ist
						if(i % 9 >= 3 && i % 9 <= 5 && i+3 == ohneAbfrage)
						{
							checkliste[i] = 1;
							continue;
						}
						if(i % 9 >= 0 && i % 9 <= 2 && i+6 == ohneAbfrage)
						{
							checkliste[i] = 1;
							continue;
						}
					}
					if(lagerinhalt.get(i) == name)
					{
						ohneAbfrage = i;
						checkliste[i] = 2;
					}
					else if(lagerinhalt.get(i) != null)
					{
						if(i % 9 >= 6 && i % 9 <= 8)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
							checkliste[i - 2*3] = 0;
						}
						if(i % 9 >= 3 && i % 9 <= 5)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
						}
						if(i % 9 >= 0 && i % 9 <= 2)
						{
							checkliste[i] = 0;
						}
					}
				}
			}
			if(name instanceof Holz)
			{
				// Abfrage für Balken
				if(((Holz) name).getForm().equals("Balken"))
				{
					int k = 0;
					for(int j = 0; j < 3; j++)
					{
						for(int i = 0; i < 3; i++)
						{
							if(lagerinhalt.get(i+k) != null || lagerinhalt.get(i+k+3) != null || lagerinhalt.get(i+k+6) != null)
							{
								checkliste[i+k] = 0;
								checkliste[i+k+3] = 0;
								checkliste[i+k+6] = 0;
							}
						}	
						k = k + 9;
					}
				}
				else
				{
					// Sonst auch ganz normal für andere
					for(int i = 26; i >= 0; i--)
					{
						if(checkliste[i] == 0)
						{
							continue;
						}
						if(ohneAbfrage != -1)
						{
							// Wenn der Gegenstand + 3 = Abgefragter Gegenstand ist
							if(i % 9 >= 3 && i % 9 <= 5 && i+3 == ohneAbfrage)
							{
								checkliste[i] = 1;
								continue;
							}
							if(i % 9 >= 0 && i % 9 <= 2 && i+6 == ohneAbfrage)
							{
								checkliste[i] = 1;
								continue;
							}
						}
						if(lagerinhalt.get(i) == name)
						{
							ohneAbfrage = i;
							checkliste[i] = 2;
						}
						else if(lagerinhalt.get(i) != null)
						{
							if(i % 9 >= 6 && i % 9 <= 8)
							{
								checkliste[i] = 0;
								checkliste[i - 1*3] = 0;
								checkliste[i - 2*3] = 0;
							}
							if(i % 9 >= 3 && i % 9 <= 5)
							{
								checkliste[i] = 0;
								checkliste[i - 1*3] = 0;
							}
							if(i % 9 >= 0 && i % 9 <= 2)
							{
								checkliste[i] = 0;
							}
						}
					}
				}
			}
		}
		else
		{
			System.out.println("Es wurde kein Lagerelement ausgewaehlt");
		}
		return checkliste;	
	}
	// Prüfe für das Produkt an Lagerstelle, wo freie Plätze für wären
	public int[] getArrayVonFreienPlaetzen(int lagerquelle)
	{
		Produkt name = lagerinhalt.get(lagerquelle);
		return getArrayVonFreienPlaetzen(name);
	}
	
	public int getAnzahlFreierVerfuegbarerPlaetze(Produkt name) {
		// 0 = belegt, 1 = frei, 2 = ausgewaehlter gegenstand
		// Technik genau gleich wie bei getArrayVonFreienPlaetzen, hier werden die Plaetze aber gezaehlt
		int checkliste[] = new int[27];
		int ohneAbfrage = -1;
		for(int i = 0; i < checkliste.length; i++)
		{
			checkliste[i] = 1;
		}
		if(name != null)
		{
			if(name instanceof Papier)
			{
				// Gucken, welche Plaetze ueberhaupt frei sind!
				for(int i = 26; i > 0; i--)
				{
					if(checkliste[i] == 0)
					{
						continue;
					}
					if(ohneAbfrage != -1)
					{
						// Wenn der Gegenstand + 3 = Abgefragter Gegenstand ist
						if(i % 9 >= 3 && i % 9 <= 5 && i+3 == ohneAbfrage)
						{
							checkliste[i] = 1;
							continue;
						}
						if(i % 9 >= 0 && i % 9 <= 2 && i+6 == ohneAbfrage)
						{
							checkliste[i] = 1;
							continue;
						}
					}
					if(lagerinhalt.get(i) == name)
					{
						ohneAbfrage = i;
						checkliste[i] = 2;
					}
					else if(lagerinhalt.get(i) != null)
					{
						if(i % 9 >= 6 && i % 9 <= 8)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
							checkliste[i - 2*3] = 0;
						}
						if(i % 9 >= 3 && i % 9 <= 5)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
						}
						if(i % 9 >= 0 && i % 9 <= 2)
						{
							checkliste[i] = 0;
						}
					}
				}
				// Ausgeben
			}
			if(name instanceof Stein)
			{
				for(int i = 26; i > 0; i--)
				{
					if(checkliste[i] == 0)
					{
						continue;
					}
					if(ohneAbfrage != -1)
					{
						// Wenn der Gegenstand + 3 = Abgefragter Gegenstand ist
						if(i % 9 >= 3 && i % 9 <= 5 && i+3 == ohneAbfrage)
						{
							checkliste[i] = 1;
							continue;
						}
						if(i % 9 >= 0 && i % 9 <= 2 && i+6 == ohneAbfrage)
						{
							checkliste[i] = 1;
							continue;
						}
					}
					if(lagerinhalt.get(i) == name)
					{
						ohneAbfrage = i;
						checkliste[i] = 2;
					}
					else if(lagerinhalt.get(i) != null)
					{
						if(i % 9 >= 6 && i % 9 <= 8)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
							checkliste[i - 2*3] = 0;
						}
						if(i % 9 >= 3 && i % 9 <= 5)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
						}
						if(i % 9 >= 0 && i % 9 <= 2)
						{
							checkliste[i] = 0;
						}
					}
				}
			}
			if(name instanceof Holz)
			{
				if(((Holz) name).getForm().equals("Balken"))
				{
					int k = 0;
					for(int j = 0; j < 3; j++)
					{
						for(int i = 0; i < 3; i++)
						{
							if(lagerinhalt.get(i+k) != null || lagerinhalt.get(i+k+3) != null || lagerinhalt.get(i+k+6) != null)
							{
								checkliste[i+k] = 0;
								checkliste[i+k+3] = 0;
								checkliste[i+k+6] = 0;
							}
						}	
						k = k + 9;
					}	
				}
				else
				{
					for(int i = 26; i >= 0; i--)
					{
						if(checkliste[i] == 0)
						{
							continue;
						}
						if(ohneAbfrage != -1)
						{
							// Wenn der Gegenstand + 3 = Abgefragter Gegenstand ist
							if(i % 9 >= 3 && i % 9 <= 5 && i+3 == ohneAbfrage)
							{
								checkliste[i] = 1;
								continue;
							}
							if(i % 9 >= 0 && i % 9 <= 2 && i+6 == ohneAbfrage)
							{
								checkliste[i] = 1;
								continue;
							}
						}
						if(lagerinhalt.get(i) == name)
						{
							ohneAbfrage = i;
							checkliste[i] = 2;
						}
						else if(lagerinhalt.get(i) != null)
						{
							if(i % 9 >= 6 && i % 9 <= 8)
							{
								checkliste[i] = 0;
								checkliste[i - 1*3] = 0;
								checkliste[i - 2*3] = 0;
							}
							if(i % 9 >= 3 && i % 9 <= 5)
							{
								checkliste[i] = 0;
								checkliste[i - 1*3] = 0;
							}
							if(i % 9 >= 0 && i % 9 <= 2)
							{
								checkliste[i] = 0;
							}	
					}	
					}
				}
			}
			int check = 0;
			for(int i = 0; i < 27; i++)
			{
				if(checkliste[i] == 1)
				{
					check++;
				}
			}
			return check;
		}
		else
		{
			System.out.println("Es wurde kein Lagerelement ausgewaehlt");
		}
		return 0;
	}
	
	public void consolePrintAlleFreiePlaetze(Produkt name)
	{
		// 0 = belegt, 1 = frei, 2 = ausgewaehlter gegenstand
		int checkliste[] = new int[27];
		for(int i = 0; i < checkliste.length; i++)
		{
			checkliste[i] = 1;
		}
		if(name != null)
		{
			if(name instanceof Papier)
			{
				// Gucken, welche Plaetze ueberhaupt frei sind!
				for(int i = 26; i > 0; i--)
				{
					if(checkliste[i] == 0)
					{
						continue;
					}
					if(lagerinhalt.get(i) != null)
					{
						if(i % 9 >= 6 && i % 9 <= 8)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
							checkliste[i - 2*3] = 0;
						}
						if(i % 9 >= 3 && i % 9 <= 5)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
						}
						if(i % 9 >= 0 && i % 9 <= 2)
						{
							checkliste[i] = 0;
						}
					}
				}
			}
			if(name instanceof Stein)
			{
				for(int i = 26; i > 0; i--)
				{
					if(checkliste[i] == 0)
					{
						continue;
					}
					if(lagerinhalt.get(i) != null)
					{
						if(i % 9 >= 6 && i % 9 <= 8)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
							checkliste[i - 2*3] = 0;
						}
						if(i % 9 >= 3 && i % 9 <= 5)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
						}
						if(i % 9 >= 0 && i % 9 <= 2)
						{
							checkliste[i] = 0;
						}
					}
				}
			}
			if(name instanceof Holz)
			{
				int k = 0;
				for(int j = 0; j < 3; j++)
				{
					for(int i = 0; i < 3; i++)
					{
						if(lagerinhalt.get(i+k) != null || lagerinhalt.get(i+k+3) != null || lagerinhalt.get(i+k+6) != null)
						{
							checkliste[i+k] = 0;
							checkliste[i+k+3] = 0;
							checkliste[i+k+6] = 0;
						}
					}	
					k = k + 9;
				}
			}
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
				if(checkliste[i] == 1)
				{
					System.out.print(" _ ");
				}
				else
				{
					System.out.print(" x ");
				}
			}
		}
		else
		{
			System.out.println("Es wurde kein Lagerelement ausgewï¿½hlt");
		}
	}

	public void consolePrintAlleFreiePlaetze(int lagerplatz) {
		// 0 = belegt, 1 = frei, 2 = ausgewaehlter gegenstand
		int checkliste[] = new int[27];
		for(int i = 0; i < checkliste.length; i++)
		{
			checkliste[i] = 1;
		}
		if(lagerinhalt.get(lagerplatz) != null)
		{
			if(lagerinhalt.get(lagerplatz) instanceof Papier)
			{
				// Gucken, welche Plaetze ueberhaupt frei sind!
				for(int i = 26; i > 0; i--)
				{
					if(checkliste[i] == 0)
					{
						continue;
					}
					if(lagerplatz == i)
					{
						checkliste[i] = 2;
						continue;
					}
					if(lagerinhalt.get(i) != null)
					{
						if(i % 9 >= 6 && i % 9 <= 8)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
							checkliste[i - 2*3] = 0;
						}
						if(i % 9 >= 3 && i % 9 <= 5)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
						}
						if(i % 9 >= 0 && i % 9 <= 2)
						{
							checkliste[i] = 0;
						}
					}
				}
				// Ausgeben
			}
			if(lagerinhalt.get(lagerplatz) instanceof Stein)
			{
				for(int i = 26; i > 0; i--)
				{
					if(checkliste[i] == 0)
					{
						continue;
					}
					if(lagerplatz == i)
					{
						checkliste[i] = 2;
						continue;
					}
					if(lagerinhalt.get(i) != null)
					{
						if(i % 9 >= 6 && i % 9 <= 8)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
							checkliste[i - 2*3] = 0;
						}
						if(i % 9 >= 3 && i % 9 <= 5)
						{
							checkliste[i] = 0;
							checkliste[i - 1*3] = 0;
						}
						if(i % 9 >= 0 && i % 9 <= 2)
						{
							checkliste[i] = 0;
						}
					}
				}
			}
			if(lagerinhalt.get(lagerplatz) instanceof Holz)
			{
				int k = 0;
				for(int j = 0; j < 3; j++)
				{
					for(int i = 0; i < 3; i++)
					{
						if(lagerinhalt.get(i+k) != null || lagerinhalt.get(i+k+3) != null || lagerinhalt.get(i+k+6) != null)
						{
							checkliste[i+k] = 0;
							checkliste[i+k+3] = 0;
							checkliste[i+k+6] = 0;
						}
					}	
					k = k + 9;
				}
			}
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
				if(checkliste[i] == 1)
				{
					System.out.print(" _ ");
				}
				else if(checkliste[i] == 2)
				{
					System.out.print(" " + lagerinhalt.get(lagerplatz).toShortString() + " ");
				}
				else
				{
					System.out.print(" x ");
				}
			}
			System.out.println();
		}
		else
		{
			System.out.println("Es wurde kein Lagerelement ausgewaehlt");
		}
	}

	public void consolePrintPlaetzeWoProduktGelagert(Produkt name) {
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
			if(name instanceof Papier)
			{
				if(lagerinhalt.get(i) instanceof Papier)
				{
					if(((Papier) lagerinhalt.get(i)).getFarbe().equals(((Papier) name).getFarbe()))
					{
						if(((Papier) lagerinhalt.get(i)).getGroesse().equals(((Papier) name).getGroesse()))
						{
							System.out.print(" P ");
						}
						else
						{
							System.out.print(" p ");
						}
					}
					else
					{
						System.out.print(" p ");
					}
				}
				else
				{
					if(lagerinhalt.get(i) == null)
					{
						System.out.print(" _ ");
					}
					else
					{
						System.out.print(" " + lagerinhalt.get(i).toShortStringSmall() + " ");	
					}
				}
			}
			if(name instanceof Holz)
			{
				if(lagerinhalt.get(i) instanceof Holz)
				{
					if(((Holz) lagerinhalt.get(i)).getArt().equals(((Holz) name).getArt()))
					{
						if(((Holz) lagerinhalt.get(i)).getForm().equals(((Holz) name).getForm()))
						{
							System.out.print(" H ");
						}
						else
						{
							System.out.print(" h ");
						}
					}
					else
					{
						System.out.print(" h ");
					}
				}
				else
				{
					if(lagerinhalt.get(i) == null)
					{
						System.out.print(" _ ");
					}
					else
					{
						System.out.print(" " + lagerinhalt.get(i).toShortStringSmall() + " ");	
					}
				}
			}
			if(name instanceof Stein)
			{
				if(lagerinhalt.get(i) instanceof Stein)
				{
					if(((Stein) lagerinhalt.get(i)).getArt().equals(((Stein) name).getArt()))
					{
						if(((Stein) lagerinhalt.get(i)).getGewicht().equals(((Stein) name).getGewicht()))
						{
							System.out.print(" S ");
						}
						else
						{
							System.out.print(" s ");
						}
					}
					else
					{
						System.out.print(" s ");
					}
				}
				else
				{
					if(lagerinhalt.get(i) == null)
					{
						System.out.print(" _ ");
					}
					else
					{
						System.out.print(" " + lagerinhalt.get(i).toShortStringSmall() + " ");	
					}
				}
			}
		}
	}
	
	// Prüft für Auslagerung, ob das Produkt an der jeweiligen Stelle gelagert ist.
	public boolean checkObProduktDortGelagert(int i, Produkt name) {
		if(name instanceof Papier)
		{
			if(lagerinhalt.get(i) instanceof Papier)
			{
				if(((Papier) lagerinhalt.get(i)).getFarbe().equals(((Papier) name).getFarbe()))
				{
					if(((Papier) lagerinhalt.get(i)).getGroesse().equals(((Papier) name).getGroesse()))
					{
						System.out.print(" P ");
						return true;
					}
					else
					{
						System.out.print(" p ");
						return false;
					}
				}
				else
				{
					System.out.print(" p ");
					return false;
				}
			}
			else
			{
				if(lagerinhalt.get(i) == null)
				{
					System.out.print(" _ ");
					return false;
				}
				else
				{
					System.out.print(" " + lagerinhalt.get(i).toShortStringSmall() + " ");	
					return false;
				}
			}
		}
		if(name instanceof Holz)
		{
			if(lagerinhalt.get(i) instanceof Holz)
			{
				if(((Holz) lagerinhalt.get(i)).getArt().equals(((Holz) name).getArt()))
				{
					if(((Holz) lagerinhalt.get(i)).getForm().equals(((Holz) name).getForm()))
					{
						System.out.print(" H ");
						return true;
					}
					else
					{
						System.out.print(" h ");
						return false;
					}
				}
				else
				{
					System.out.print(" h ");
					return false;
				}
			}
			else
			{
				if(lagerinhalt.get(i) == null)
				{
					System.out.print(" _ ");
					return false;
				}
				else
				{
					System.out.print(" " + lagerinhalt.get(i).toShortStringSmall() + " ");	
					return false;
				}
			}
		}
		if(name instanceof Stein)
		{
			if(lagerinhalt.get(i) instanceof Stein)
			{
				if(((Stein) lagerinhalt.get(i)).getArt().equals(((Stein) name).getArt()))
				{
					if(((Stein) lagerinhalt.get(i)).getGewicht().equals(((Stein) name).getGewicht()))
					{
						System.out.print(" S ");
						return true;
					}
					else
					{
						System.out.print(" s ");
						return false;
					}
				}
				else
				{
					System.out.print(" s ");
					return false;
				}
			}
			else
			{
				if(lagerinhalt.get(i) == null)
				{
					System.out.print(" _ ");
					return false;
				}
				else
				{
					System.out.print(" " + lagerinhalt.get(i).toShortStringSmall() + " ");
					return false;
				}
			}
		}
		return false;
	}
	
	// Prüft, wo Plätze im Lager zum Umlagern verfügbar sind, gibt es als Array zurück
	public int[] getPlaetzeZumUmlagern() {
		int[] checkliste = new int[27];
		for(int i = 0; i < 27; i++)
		{
			checkliste[i] = 1;
		}
		for(int i = 0; i < 27; i++)
		{
			if(checkObProduktNichtBlockiert(i) == false)
			{
				checkliste[i] = 0;
			}
			else
			{
				System.out.println(getLagerplatzInhalt(i));
			}
		}
		return checkliste;
	}
	
	// Prüft, ob ein Produkt von einem davorstehenden blockiert wird
	public boolean checkObProduktNichtBlockiert(int i) {
		if(lagerinhalt.get(i) instanceof Papier || lagerinhalt.get(i) instanceof Stein)
		{
			if(i % 9 >= 0 && i % 9 <= 2)
			{
				if(lagerinhalt.get(i+1*3) == null && lagerinhalt.get(i+2*3) == null)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			if(i % 9 >= 3 && i % 9 <= 5)
			{
				if(lagerinhalt.get(i+1*3) == null)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			if(i % 9 >= 6 && i % 9 <= 8)
			{
				return true;
			}
		}
		if(lagerinhalt.get(i) instanceof Holz)
		{
			if(((Holz) lagerinhalt.get(i)).getForm().equals("Balken"))
			{
				return true;
			}
			else
			{
				if(i % 9 >= 0 && i % 9 <= 2)
				{
					if(lagerinhalt.get(i+1*3) == null && lagerinhalt.get(i+2*3) == null)
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				if(i % 9 >= 3 && i % 9 <= 5)
				{
					if(lagerinhalt.get(i+1*3) == null)
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				if(i % 9 >= 6 && i % 9 <= 8)
				{
					return true;
				}
			}
		}
		return false;
	}

	// Funktion zum Umlagern von Lagerquelle zu Lagerziel
	public boolean Umlagern(int lagerquelle, int lagerziel) {
		// Prüft, ob Lagerquelle leer ist
		if(lagerinhalt.get(lagerquelle) == null)
		{
			System.out.println("Der Lagerplatz ist leer");
			return false;
		}
		if(lagerinhalt.get(lagerquelle) instanceof Papier)
		{
			if(lagerinhalt.get(lagerziel) != null)
			{
				consolePrintGesamtesLager();
				System.out.println(lagerinhalt.get(lagerziel));
				System.out.println("Das Lagerziel ist bereits besetzt");
				return false;
			}
			else
			{
				lagerinhalt.set(lagerziel, lagerinhalt.get(lagerquelle));
				lagerinhalt.set(lagerquelle, null);
				Start.bilanz.removeGesamtkonto(100, lagerinhalt.get(lagerziel), "Umlagern");
				System.out.println("Das Lagerziel wurde erfolgreich zur Lagerquelle umgelagert.");
				return true;
			}
		}
		if(lagerinhalt.get(lagerquelle) instanceof Holz)
		{
			if(((Holz) lagerinhalt.get(lagerquelle)).getForm().equals("Balken"))
			{
				// Damit immer die vordeste Position ausgewählt wird
				int holz_lagerziel = HolzLagerstelleAbfragen(lagerziel);
				int holz_lagerquelle = HolzLagerstelleAbfragen(lagerquelle);
				if(lagerinhalt.get(holz_lagerziel) == null && lagerinhalt.get(holz_lagerziel-3) == null && lagerinhalt.get(holz_lagerziel-6) == null)
				{
					lagerinhalt.set(holz_lagerziel, lagerinhalt.get(lagerquelle));
					lagerinhalt.set(holz_lagerziel-3, lagerinhalt.get(lagerquelle));
					lagerinhalt.set(holz_lagerziel-6, lagerinhalt.get(lagerquelle));
					lagerinhalt.set(holz_lagerquelle, null);
					lagerinhalt.set(holz_lagerquelle-3, null);
					lagerinhalt.set(holz_lagerquelle-6, null);
					
					Start.bilanz.removeGesamtkonto(100, lagerinhalt.get(holz_lagerziel), "Umlagern");
					System.out.println("Das Lagerziel wurde erfolgreich zur Lagerquelle umgelagert.");
					return true;
				}
				else
				{
					System.out.println("Das Lagerziel ist bereits besetzt");
				}
			}
			else
			{
				if(lagerinhalt.get(lagerziel) != null)
				{
					System.out.println("Das Lagerziel ist bereits besetzt");
					return false;
				}
				else
				{
					lagerinhalt.set(lagerziel, lagerinhalt.get(lagerquelle));
					lagerinhalt.set(lagerquelle, null);
					Start.bilanz.removeGesamtkonto(100, lagerinhalt.get(lagerziel), "Umlagern");
					System.out.println("Das Lagerziel wurde erfolgreich zur Lagerquelle umgelagert.");
					return true;
				}
			}
		}
		if(lagerinhalt.get(lagerquelle) instanceof Stein)
		{
			if(lagerinhalt.get(lagerziel) != null)
			{
				System.out.println("Das Lagerziel ist bereits besetzt");
				return false;
			}
			else
			{
				if(((Stein) lagerinhalt.get(lagerquelle)).getGewicht().equals("Schwer"))
				{
					if(lagerziel > 8)
					{
						System.out.println("Schwere Steine koennen nur unten gelagert werden!");
					}
					else
					{
						lagerinhalt.set(lagerziel, lagerinhalt.get(lagerquelle));
						lagerinhalt.set(lagerquelle, null);
						Start.bilanz.removeGesamtkonto(100, lagerinhalt.get(lagerziel), "Umlagern");
						System.out.println("Das Lagerziel wurde erfolgreich zur Lagerquelle umgelagert.");
						return true;	
					}
				}
				else
				{
					lagerinhalt.set(lagerziel, lagerinhalt.get(lagerquelle));
					lagerinhalt.set(lagerquelle, null);
					Start.bilanz.removeGesamtkonto(100, lagerinhalt.get(lagerziel), "Umlagern");
					System.out.println("Das Lagerziel wurde erfolgreich zur Lagerquelle umgelagert.");
					return true;
				}
			}
		}
		System.out.println("Irgendwas ist falsch");
		return false;
	}
	
	public boolean isLagerEmpty() {
		for(int i = 0; i < 27; i++)
		{
			if(lagerinhalt.get(i) == null)
			{
				
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	
	public String getLagerplatzInhalt(int lagerplatz) {
		if(lagerinhalt.get(lagerplatz) == null)
		{
			System.out.println("Lagerplatz ist leer");
			return null;
		}
		else
		{
			System.out.println(lagerinhalt.get(lagerplatz).getInhalt());
			return lagerinhalt.get(lagerplatz).getInhalt();
		}
	}
	
	// Bei Holz soll immer die vorderste Position ausgewählt werden, zum Ein und Auslagern, daher die Funktion
	public int HolzLagerstelleAbfragen(int lagerziel) {
		int k = 0;
		if(lagerziel < 9)
		{
			if(lagerziel % 3 == 0)
			{
				k = 6;
			}
			if(lagerziel % 3 == 1)
			{
				k = 7;
			}
			if(lagerziel % 3 == 2)
			{
				k = 8;
			}
		}
		else if(lagerziel < 18)
		{
			if(lagerziel % 3 == 0)
			{
				k = 15;
			}
			if(lagerziel % 3 == 1)
			{
				k = 16;
			}
			if(lagerziel % 3 == 2)
			{
				k = 17;
			}
		}
		else
		{
			if(lagerziel % 3 == 0)
			{
				k = 24;
			}
			if(lagerziel % 3 == 1)
			{
				k = 25;
			}
			if(lagerziel % 3 == 2)
			{
				k = 26;
			}
		}
		return k;
	}
	/*
	 * TODO: Umlagern, prï¿½fen bei EInlagerung, Verschrotten und Auslagern
	 * Viele If Anfrage durch Switch ersetzen
	 * 
	 * 
	 */
}
