package lagerregal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException; 

public class LeseCSV {
	public static void ladeDatei(String name) {
		File file = new File(name);
        if (!file.canRead() || !file.isFile())
        	System.out.println("Programm ist abgestürzt");
        	System.exit(0);
        	BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(name));
            String zeile = null;
            while ((zeile = in.readLine()) != null) {
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
                	    	//System.out.println(c);
            	    	}
            	    }
            	}
            	if(Produkt.equals("Papier"))
            	{
            		Papier papier = new Papier(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
            		System.out.println(papier.getFarbe());
            	}
            	if(Produkt.equals("Holz"))
            	{
            		Holz holz = new Holz(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
            		System.out.println(holz.getArt());
            	}
            	if(Produkt.equals("Stein"))
            	{
            		Stein stein = new Stein(Auftragsart, Attribut1, Attribut2, Integer.parseInt(Belohnung));
            	}
            	//System.out.println(zeile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        } 
	}
}
