package lagerregal;

import java.util.*;

public class Lager {
	private double Umsatz;
	private ArrayList<Produkt> inhalt;
	private ArrayList<Produkt> schlange;
	private int listlaenge = 0;
	public Lager() {
		this.inhalt = new ArrayList<Produkt>();
		for(int i = 0; i < 27; i++) {
			inhalt.add(i, null);
		}
		// Jo, also zweidimensionales und als Stack
		this.schlange = new ArrayList<Produkt>();
		for(int i = 0; i < 3; i++)
		{
			schlange.add(i, null);
		}
		this.Umsatz = 0;
	}
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
				schlange.set(0, Start.datei.firstSchlange());
			}
			else if(getAuftrag(1) == null) {
				schlange.set(1, Start.datei.firstSchlange());
			}
			else if(getAuftrag(2) == null) {
				schlange.set(2, Start.datei.firstSchlange());
			}
			else
			{
				schlange.set(listlaenge, Start.datei.firstSchlange());
			}
			Start.datei.removeSchlange();
			listlaenge++;
			return true;
		}
	}
	public boolean auftragZurueckstellen(int nummer) {
		// Hier muss der Auftrag dann hinten rangestellt werden!!
		if(listlaenge == 0) {
			return false;
		}
		else
		{
			Start.datei.addSchlange(schlange.get(nummer));
			schlange.set(nummer, null);
			listlaenge--;
			return true;
		}
	}
	// Lï¿½scht den Auftrag vollstï¿½ndig!
	public boolean auftragAblehnen(int nummer) {
		if(listlaenge == 0) {
			return false;
		}
		else
		{
			Start.bilanz.removeGesamtkonto(getAuftrag(nummer).getKosten(), getAuftrag(nummer), "Abgelehnt");
			schlange.set(nummer, null);
			listlaenge--;
			return true;
		}
	}
	// Löscht den Auftrag für das Abschliessen
	private boolean auftragLoeschen(int nummer) {
		if(listlaenge == 0) {
			return false;
		}
		else
		{
			schlange.set(nummer, null);
			listlaenge--;
			return true;
		}
	}

	public Produkt getAuftrag(int nummer) {
		return schlange.get(nummer);
	}
	public void printAuftraege() {
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
				System.out.print(" _ ");
			}
		}
		System.out.println();
	}
	public boolean AuftragAbarbeiten(int auswahl, int lagerplatz) {
		if(auswahl > 3 || auswahl < 0)
		{
			System.out.println("Es kï¿½nnen nur aktive Auftrï¿½ge zwischen 0 und 2 gewï¿½hlt werden!");
		}
		Produkt auftrag = (Produkt) getAuftrag(auswahl);
		if(lagerplatz > 26 || lagerplatz < 0)
		{
			System.out.println("Der Lagerplatz ist auï¿½erhalb des mï¿½glichen");
			return false;
		}
		if(((Produkt) auftrag).getLagerungsart().equals("Einlagerung"))
		{
			if(auftrag instanceof Papier)
			{
				if(lagerplatz % 9 >= 0 && lagerplatz % 9 <= 2)
				{
					for(int i = 1; i < 3; i++) {
						if(inhalt.get(lagerplatz + i*3) != null) {
							System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
							return false;
						}
					}
				}
				if(lagerplatz % 9 >= 3 && lagerplatz % 9 <= 5)
				{
					if(inhalt.get(lagerplatz + 3) != null) {
						System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
						return false;
					}
				}
				if(inhalt.get(lagerplatz) == null) {
					inhalt.set(lagerplatz, (Produkt) auftrag);
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
				boolean pruefeObPlatzVerfuegbar = true;
				if(lagerplatz < 9) {
					for(int i = 0; i < 9; i++) {
						if(inhalt.get(i) != null)
						{
							pruefeObPlatzVerfuegbar = false;
						}
					}
					if(pruefeObPlatzVerfuegbar == true) {
						for(int i = 0; i < 9; i++) {
							inhalt.set(i, (Produkt) auftrag);
						}
						auftragLoeschen(auswahl);
						Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
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
							pruefeObPlatzVerfuegbar = false;
						}
					}
					if(pruefeObPlatzVerfuegbar == true) {
						for(int i = 9; i < 18; i++) {
							inhalt.set(i, (Produkt) auftrag);
						}
						auftragLoeschen(auswahl);
						Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
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
					for(int i = 18; i < 27; i++) {
						if(inhalt.get(i) != null)
						{
							pruefeObPlatzVerfuegbar = false;
						}
					}
					if(pruefeObPlatzVerfuegbar == true) {
						for(int i = 18; i < 27; i++) {
							inhalt.set(i, (Produkt) auftrag);
						}
						auftragLoeschen(auswahl);
						Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
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
					if(lagerplatz % 9 >= 0 && lagerplatz % 9 <= 2)
					{
						for(int i = 1; i < 3; i++) {
							if(inhalt.get(lagerplatz + i*3) != null) {
								System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
								return false;
							}
						}
					}
					if(lagerplatz % 9 >= 3 && lagerplatz % 9 <= 5)
					{
						if(inhalt.get(lagerplatz + 3) != null) {
							System.out.println("Der Lagerplatz wird durch andere Dinge davor blockiert.");
							return false;
						}
					}
					if(inhalt.get(lagerplatz) == null) {
						inhalt.set(lagerplatz, (Produkt) auftrag);
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
					System.out.println("Steine kï¿½nnen nur unten gelagert werden!");
					return false;
				}
			}
			else {
				System.out.println("Entspricht keiner Lagersache.");
				return false;
			}
			// Zeige alle freien Plï¿½tzen
		}
		else if(((Produkt) auftrag).getLagerungsart().equals("Auslagerung"))
		{
			if(auftrag instanceof Papier)
			{
				if(inhalt.get(lagerplatz) instanceof Papier)
				{
					if(((Papier) inhalt.get(lagerplatz)).getFarbe().equals(((Papier) auftrag).getFarbe()))
					{
						if(((Papier) inhalt.get(lagerplatz)).getGroesse().equals(((Papier) auftrag).getGroesse()))
						{
							inhalt.set(lagerplatz, null);
							auftragLoeschen(auswahl);
							Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
							System.out.println("Erfolgreich ausgelagert.");
							return true;
						}
					}
				}
			}
			else if(auftrag instanceof Holz)
			{
				if(inhalt.get(lagerplatz) instanceof Holz)
				{
					if(((Holz) inhalt.get(lagerplatz)).getArt().equals(((Holz) auftrag).getArt()))
					{
						if(((Holz) inhalt.get(lagerplatz)).getForm().equals(((Holz) auftrag).getForm()))
						{
							if(lagerplatz < 9)
							{
								for(int i = 0; i < 9; i++)
								{
									inhalt.set(i, null);
								}
							}
							else if(lagerplatz < 18)
							{
								for(int i = 9; i < 18; i++)
								{
									inhalt.set(i, null);
								}
							}
							else if(lagerplatz < 27)
							{
								for(int i = 18; i < 27; i++)
								{
									inhalt.set(i, null);
								}
							}
							else
							{
								
							}
							auftragLoeschen(auswahl);
							Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
							System.out.println("Erfolgreich ausgelagert.");
							return true;
						}
					}
				}
			}
			else if(auftrag instanceof Stein)
			{
				if(inhalt.get(lagerplatz) instanceof Stein)
				{
					if(((Stein) inhalt.get(lagerplatz)).getArt().equals(((Stein) auftrag).getArt()))
					{
						if(((Stein) inhalt.get(lagerplatz)).getGewicht().equals(((Stein) auftrag).getGewicht()))
						{
							inhalt.set(lagerplatz, null);
							auftragLoeschen(auswahl);
							Start.bilanz.addGesamtkonto(((Produkt) auftrag).getKosten(), auftrag);
							System.out.println("Erfolgreich ausgelagert.");
							return true;
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

	public boolean Verschrotten(int lagerplatz) {
		if(inhalt.get(lagerplatz) != null) {
			Produkt verschrottung = inhalt.get(lagerplatz);
			if(inhalt.get(lagerplatz) instanceof Papier || inhalt.get(lagerplatz) instanceof Stein) {
				inhalt.set(lagerplatz, null);
			}
			if(inhalt.get(lagerplatz) instanceof Holz)
			{
				if(lagerplatz < 9) {
					for(int i = 0; i < 9; i++) {
						inhalt.set(i, null);
					}
				}
				else if(lagerplatz < 18) {
					for(int i = 9; i < 18; i++) {
						inhalt.set(i, null);
					}
				}
				else if(lagerplatz < 27) {
					for(int i = 18; i < 27; i++) {
						inhalt.set(i, null);
					}
				}
				else
				{
					
				}
			}
			Start.bilanz.removeGesamtkonto(500, verschrottung, "Verschrottung");
			System.out.println("Erfolgreich verschrottet");
			return true;
		}
		System.out.println("An der Stelle existiert nichts.");
		return false;
	}

	public void zeigeFreiePlaetze(Produkt name)
	{
		// 0 = belegt, 1 = frei, 2 = ausgewï¿½hlter gegenstand
		int checkliste[] = new int[27];
		for(int i = 0; i < checkliste.length; i++)
		{
			checkliste[i] = 1;
		}
		if(name != null)
		{
			if(name instanceof Papier)
			{
				// Gucken, welche Plï¿½tze ï¿½berhaupt frei sind!
				for(int i = 26; i > 0; i--)
				{
					if(checkliste[i] == 0)
					{
						continue;
					}
					if(inhalt.get(i) != null)
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
					if(i > 8)
					{
						checkliste[i] = 0;
						continue;
					}
					if(checkliste[i] == 0)
					{
						continue;
					}
					if(inhalt.get(i) != null)
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
				for(int j = 0; j < 3; j++)
				{
					boolean pruefeObPlatzVerfuegbar = true;
					for(int i = 0; i < 9; i++)
					{
						if(inhalt.get(i+j*9) != null)
						{
							pruefeObPlatzVerfuegbar = false;
						}
					}
					if(pruefeObPlatzVerfuegbar == false)
					{
						for(int i = 0; i < 9; i++)
						{
							checkliste[i+j*9] = 0;
						}
					}
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
	public void zeigeFreiePlaetze(int lagerplatz) {
		// 0 = belegt, 1 = frei, 2 = ausgewï¿½hlter gegenstand
		int checkliste[] = new int[27];
		for(int i = 0; i < checkliste.length; i++)
		{
			checkliste[i] = 1;
		}
		if(inhalt.get(lagerplatz) != null)
		{
			if(inhalt.get(lagerplatz) instanceof Papier)
			{
				// Gucken, welche Plï¿½tze ï¿½berhaupt frei sind!
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
					if(inhalt.get(i) != null)
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
			if(inhalt.get(lagerplatz) instanceof Stein)
			{
				for(int i = 26; i > 0; i--)
				{
					if(i > 8)
					{
						checkliste[i] = 0;
						continue;
					}
					if(checkliste[i] == 0)
					{
						continue;
					}
					if(lagerplatz == i)
					{
						checkliste[i] = 2;
						continue;
					}
					if(inhalt.get(i) != null)
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
			if(inhalt.get(lagerplatz) instanceof Holz)
			{
				for(int j = 0; j < 3; j++)
				{
					boolean pruefeObPlatzVerfuegbar = true;
					for(int i = 0; i < 9; i++)
					{
						if(inhalt.get(i+j*9) != null)
						{
							pruefeObPlatzVerfuegbar = false;
						}
					}
					if(pruefeObPlatzVerfuegbar == false)
					{
						for(int i = 0; i < 9; i++)
						{
							checkliste[i+j*9] = 0;
						}
					}
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
					System.out.print(" " + inhalt.get(lagerplatz).toShortString() + " ");
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
			System.out.println("Es wurde kein Lagerelement ausgewï¿½hlt");
		}
	}

	public void zeigePlaetzeWoProduktGelagert(Produkt name) {
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
				if(inhalt.get(i) instanceof Papier)
				{
					if(((Papier) inhalt.get(i)).getFarbe().equals(((Papier) name).getFarbe()))
					{
						if(((Papier) inhalt.get(i)).getGroesse().equals(((Papier) name).getGroesse()))
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
					if(inhalt.get(i) == null)
					{
						System.out.print(" _ ");
					}
					else
					{
						System.out.print(" " + inhalt.get(i).toShortStringSmall() + " ");	
					}
				}
			}
			if(name instanceof Holz)
			{
				if(inhalt.get(i) instanceof Holz)
				{
					if(((Holz) inhalt.get(i)).getArt().equals(((Holz) name).getArt()))
					{
						if(((Holz) inhalt.get(i)).getForm().equals(((Holz) name).getForm()))
						{
							if(i < 9)
							{
								for(int j = 0; i < 9; i++)
								{
									if(i % 3 == 0)
									{
										System.out.println();
									}
									else
									{
										System.out.print(" H ");
									}
								}
							}
							else if(i < 18)
							{
								for(int j = 9; i < 18; i++)
								{
									if(i % 3 == 0)
									{
										System.out.println();
									}
									else
									{
										System.out.print(" H ");
									}
								}
							}
							else if(i < 27)
							{
								for(int j = 18; i < 27; i++)
								{
									if(i % 3 == 0)
									{
										System.out.println();
									}
									else
									{
										System.out.print(" H ");
									}
								}
							}
							else
							{
								
							}
							i = i + 9;
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
					if(inhalt.get(i) == null)
					{
						System.out.print(" _ ");
					}
					else
					{
						System.out.print(" " + inhalt.get(i).toShortStringSmall() + " ");	
					}
				}
			}
			if(name instanceof Stein)
			{
				if(inhalt.get(i) instanceof Stein)
				{
					if(((Stein) inhalt.get(i)).getArt().equals(((Stein) name).getArt()))
					{
						if(((Stein) inhalt.get(i)).getGewicht().equals(((Stein) name).getGewicht()))
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
					if(inhalt.get(i) == null)
					{
						System.out.print(" _ ");
					}
					else
					{
						System.out.print(" " + inhalt.get(i).toShortStringSmall() + " ");	
					}
				}
			}
		}
	}

	public boolean Umlagern(int lagerquelle, int lagerziel) {
		if(inhalt.get(lagerquelle) == null)
		{
			System.out.println("Der Lagerplatz ist leer");
			return false;
		}
		if(inhalt.get(lagerquelle) instanceof Papier)
		{
			if(inhalt.get(lagerziel) != null)
			{
				System.out.println("Das Lagerziel ist bereits besetzt");
				return false;
			}
			else
			{
				inhalt.set(lagerziel, inhalt.get(lagerquelle));
				inhalt.set(lagerquelle, null);
				Start.bilanz.removeGesamtkonto(100, inhalt.get(lagerziel), "Umlagern");
				System.out.println("Das Lagerziel wurde erfolgreich zur Lagerquelle umgelagert.");
				return true;
			}
		}
		if(inhalt.get(lagerquelle) instanceof Holz)
		{
			boolean pruefeObPlatzVerfuegbar = true;
			if(lagerziel < 9)
			{
				for(int i = 0; i < 9; i++)
				{
					if(inhalt.get(i) != null)
					{
						pruefeObPlatzVerfuegbar = false;
					}
				}
			}
			else if(lagerziel < 18)
			{
				for(int i = 9; i < 18; i++)
				{
					if(inhalt.get(i) != null)
					{
						pruefeObPlatzVerfuegbar = false;
					}
				}
			}
			else if(lagerziel < 27)
			{
				for(int i = 18; i < 27; i++)
				{
					if(inhalt.get(i) != null)
					{
						pruefeObPlatzVerfuegbar = false;
					}
				}
			}
			else
			{
				
			}
			if(pruefeObPlatzVerfuegbar == false)
			{
				System.out.println("Das Lagerziel ist bereits besetzt");
			}
			else
			{
				if(lagerziel < 9)
				{
					for(int i = 0; i < 9; i++)
					{
						inhalt.set(i, inhalt.get(lagerquelle));
					}
				}
				else if(lagerziel < 18)
				{
					for(int i = 9; i < 18; i++)
					{
						inhalt.set(i, inhalt.get(lagerquelle));
					}
				}
				else if(lagerziel < 27)
				{
					for(int i = 18; i < 27; i++)
					{
						inhalt.set(i, inhalt.get(lagerquelle));
					}
				}
				else
				{
					
				}
				if(lagerquelle < 9)
				{
					for(int i = 0; i < 9; i++)
					{
						inhalt.set(i, null);
					}
				}
				else if(lagerquelle < 18)
				{
					for(int i = 9; i < 18; i++)
					{
						inhalt.set(i, null);
					}
				}
				else if(lagerquelle < 27)
				{
					for(int i = 18; i < 27; i++)
					{
						inhalt.set(i, null);
					}
				}
				else
				{
					
				}
				System.out.println("Das Lagerziel wurde erfolgreich zur Lagerquelle umgelagert.");
				return true;
			}
		}
		if(inhalt.get(lagerquelle) instanceof Stein)
		{
			if(inhalt.get(lagerziel) != null)
			{
				System.out.println("Das Lagerziel ist bereits besetzt");
				return false;
			}
			else
			{
				if(lagerziel > 8)
				{
					System.out.println("Steine kï¿½nnen nur unten gelagert werden!");
				}
				else
				{
					inhalt.set(lagerziel, inhalt.get(lagerquelle));
					inhalt.set(lagerquelle, null);
					Start.bilanz.removeGesamtkonto(100, inhalt.get(lagerziel), "Umlagern");
					System.out.println("Das Lagerziel wurde erfolgreich zur Lagerquelle umgelagert.");
					return true;	
				}
			}
		}
		System.out.println("Irgendwas ist falsch");
		return false;
	}
	
	public String getLagerplatzInhalt(int lagerplatz) {
		if(inhalt.get(lagerplatz) == null)
		{
			//System.out.println("Lagerplatz ist leer");
			return null;
		}
		else
		{
			//System.out.println(inhalt.get(lagerplatz).getInhalt());
			return inhalt.get(lagerplatz).getInhalt();
		}
	}
	/*
	 * TODO: Umlagern, prï¿½fen bei EInlagerung, Verschrotten und Auslagern
	 * Viele If Anfrage durch Switch ersetzen
	 * 
	 * 
	 */
}
