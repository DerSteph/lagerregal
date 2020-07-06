package lagerregal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException; 

public class LeseCSV {
	public File file;
	public BufferedReader in = null;
	public LeseCSV(String name) {
		file = new File(name);
        if (!file.canRead() || !file.isFile())
        {
        	System.out.println("Datei kann nicht gelesen werden");
        }
        try {
            in = new BufferedReader(new FileReader(name));
            in.readLine(); // um die erste Zeile nicht zu lesen
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public void leseZeile() {
		try {
			String zeile = in.readLine();
	    	String text = new String();
	    	String Auftragsart = null;
	    	String Produkt = null;
	    	String Attribut1 = null;
	    	String Attribut2 = null;
	    	String Belohnung = null;
	    	int auswahl = 0;
	    	for (int i = 0, n = zeile.length(); i < n; i++) {
	    	    char c = zeile.charAt(i);
	    	    if(c == ';' || i == zeile.length()-1)
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
	    			
	    		}
	    	}
	    	if(Produkt.equals("Holz"))
	    	{
	    		Holz holz = new Holz(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
	    	}
	    	if(Produkt.equals("Stein"))
	    	{
	    		Stein stein = new Stein(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
	    	}
	    	System.out.println(zeile);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
