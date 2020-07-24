package lagerregal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.LinkedList;
import java.util.Queue; 

public class CSV {
	private File file;
	private String name;
	private BufferedReader in = null;
	private Queue<Produkt> schlangecsv;
	private int zaehler = 0;
	// Liest Datei ein und erstellt eine Schlange für die Liste
	public CSV(String name) {
		file = new File(name);
        schlangecsv = new LinkedList<Produkt>();
        this.name = name;
        readFile(name);
	}
	public void readFile(String name) {
		// prüft, ob die Datei existiert
		if (!file.canRead() || !file.isFile())
        {
        	System.out.println("Datei kann nicht gelesen werden");
        }
		else
		{
			// öffnen der Datei
	        try {
	            in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(name)));
	            in.readLine(); // um die erste Zeile nicht zu lesen
	            String zeile = null;
	            // Lese jede Zeile ein
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
	    	    	    		zaehler++;
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
	    	    	}
	    	    	if(Produkt.equals("Holz"))
	    	    	{
	    	    		Holz holz = new Holz(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
	    	    		addSchlange(holz);
	    	    	}
	    	    	if(Produkt.equals("Stein"))
	    	    	{
	    	    		Stein stein = new Stein(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
	    	    		addSchlange(stein);
	    	    	}
	    	    	System.out.println(zeile);
	            }
	            System.out.println("Erfolgreich alle Zeilen eingelesen (insgesamt " + zaehler + ")");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }	
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
		// Wenn Schlange wieder leer, soll er die Datei erneut einlesen
		if(schlangecsv.isEmpty())
		{
			readFile(this.name);
		}
	}
}
