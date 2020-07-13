package lagerregal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue; 

public class CSV {
	public File file;
	public BufferedReader in = null;
	public Queue<Produkt> schlangecsv;
	public CSV(String name) {
		file = new File(name);
        schlangecsv = new LinkedList<Produkt>();
		if (!file.canRead() || !file.isFile())
        {
        	System.out.println("Datei kann nicht gelesen werden");
        }
        try {
            in = new BufferedReader(new FileReader(name));
            in.readLine(); // um die erste Zeile nicht zu lesen
            String zeile = null;
            while((zeile = in.readLine()) != null)
            {
    	    	String text = new String();
    	    	String Auftragsart = null;
    	    	String Produkt = null;
    	    	String Attribut1 = null;
    	    	String Attribut2 = null;
    	    	String Belohnung = null;
    	    	int auswahl = 0;
    	    	for (int i = 0; i < zeile.length(); i++) {
    	    	    char c = zeile.charAt(i);
    	    	    if(c == ';'|| i == zeile.length()-1)
    	    	    {
    	    	    	switch(auswahl)
    	    	    	{
    	    	    	case 0:
    	    	    		Start.zaehler++;
    	    	    		break;
    	    	    	case 1:
    	    	    		Auftragsart = text;
    	    	    		break;
    	    	    	case 2:
    	    	    		Produkt = text;
    	    	    		break;
    	    	    	case 3:
    	    	    		Attribut1 = text;
    	    	    		break;
    	    	    	case 4:
    	    	    		Attribut2 = text;
    	    	    		break;
    	    	    	case 5:
    	    	    		// noch überlegen, ob das eleganter geht
    	        	    	text = text + c;
    	    	    		Belohnung = text;
    	    	    		break;
    	    	    	}
    	    	    	auswahl++;
    	    	    	text = null;
    	    	    }
    	    	    else
    	    	    {
    	    	    	if(text == null)
    	    	    	{
    	    	    		text = Character.toString(c);
    	    	    	}
    	    	    	else
    	    	    	{
    	        	    	text = text + c;	
    	    	    	}
    	    	    }
    	    	}
    	    	if(Produkt.equals("Papier"))
    	    	{
    	    		Papier papier = new Papier(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
    	    		addSchlange(papier);
    	    		//System.out.println("Erfolgreich Papier der Schlange hinzugefügt.");
    	    	}
    	    	if(Produkt.equals("Holz"))
    	    	{
    	    		Holz holz = new Holz(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
    	    		addSchlange(holz);
    	    		//System.out.println("Erfolgreich Holz der Schlange hinzugefügt.");
    	    	}
    	    	if(Produkt.equals("Stein"))
    	    	{
    	    		Stein stein = new Stein(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
    	    		addSchlange(stein);
    	    		//System.out.println("Erfolgreich Stein der Schlange hinzugefügt.");
    	    	}
    	    	System.out.println(zeile);
            }
            System.out.println("Erfolgreich alle Zeilen eingelesen");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void addSchlange(Produkt e) {
		schlangecsv.offer(e);
	}
	public Produkt firstSchlange() {
		return schlangecsv.peek();
	}
	public void removeSchlange() {
		schlangecsv.poll();
	}
	/*public void leseZeile() {
		try {
			String zeile = in.readLine();
	    	String text = new String();
	    	String Auftragsart = null;
	    	String Produkt = null;
	    	String Attribut1 = null;
	    	String Attribut2 = null;
	    	String Belohnung = null;
	    	int auswahl = 0;
	    	for (int i = 0; i < zeile.length(); i++) {
	    	    char c = zeile.charAt(i);
	    	    if(c == ';'|| i == zeile.length()-1)
	    	    {
	    	    	switch(auswahl)
	    	    	{
	    	    	case 0:
	    	    		Test.zaehler++;
	    	    		break;
	    	    	case 1:
	    	    		Auftragsart = text;
	    	    		break;
	    	    	case 2:
	    	    		Produkt = text;
	    	    		break;
	    	    	case 3:
	    	    		Attribut1 = text;
	    	    		break;
	    	    	case 4:
	    	    		Attribut2 = text;
	    	    		break;
	    	    	case 5:
	    	    		// noch überlegen, ob das eleganter geht
	        	    	text = text + c;
	    	    		Belohnung = text;
	    	    		break;
	    	    	}
	    	    	auswahl++;
	    	    	text = null;
	    	    }
	    	    else
	    	    {
	    	    	if(text == null)
	    	    	{
	    	    		text = Character.toString(c);
	    	    	}
	    	    	else
	    	    	{
	        	    	text = text + c;	
	    	    	}
	    	    }
	    	}
	    	if(Produkt.equals("Papier"))
	    	{
	    		Papier papier = new Papier(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
	    		if(Test.lager.AuftragHinzufuegen(papier) == false)
	    		{
	    			System.out.println("Schlange ist voll");
	    		}
	    		else
	    		{
	    			System.out.println("Erfolgreich Papier der Schlange hinzugefügt.");
	    		}
	    	}
	    	if(Produkt.equals("Holz"))
	    	{
	    		Holz holz = new Holz(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
	    		if(Test.lager.AuftragHinzufuegen(holz) == false)
	    		{
	    			System.out.println("Schlange ist voll");
	    		}
	    		else
	    		{
	    			System.out.println("Erfolgreich Holz der Schlange hinzugefügt.");
	    		}
	    	}
	    	if(Produkt.equals("Stein"))
	    	{
	    		Stein stein = new Stein(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
	    		if(Test.lager.AuftragHinzufuegen(stein) == false)
	    		{
	    			System.out.println("Schlange ist voll");
	    		}
	    		else
	    		{
	    			System.out.println("Erfolgreich Stein der Schlange hinzugefügt.");
	    		}
	    	}
	    	System.out.println(zeile);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}*/
}
