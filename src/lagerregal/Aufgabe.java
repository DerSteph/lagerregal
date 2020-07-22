package lagerregal;

public class Aufgabe {
	private String Aufgabentext;
	private String Endtext;
	private int lagerplatz;
	public Aufgabe(Produkt name, int lagerplatz)
	{
		this.lagerplatz = lagerplatz;
	    int zufallszahl = (int)(Math.random() * 3);
	    if(name instanceof Papier)
	    {
	    	if(zufallszahl == 0)
	    	{
	    		Aufgabentext = "Die Firma EcoPrint GmbH möchte gerne " + ((Papier) name).getFarbe() +" Papier der Größe " + ((Papier) name).getGroesse() + " zum Weiterverkauf bei uns zwischenlagern. Das sollte doch kein Problem sein, oder?";
	    	}
	    	else if(zufallszahl == 1)
	    	{
	    		Aufgabentext = "Weil die Nachfrage nach " + ((Papier) name).getFarbe() +"em Papier in Größe " + ((Papier) name).getGroesse() + " so gering ist, möchte der Verkäufer Peter sein Papier bei uns zwischenlagern, damit er Platz für andere Produkte hat. Geht das?";
	    	}
	    	else
	    	{
	    		Aufgabentext = "TreeIndustries Inc. hat " + ((Papier) name).getFarbe() + " Papier in Größe " + ((Papier) name).getGroesse() + " überproduziert und kann dies in ihrem Lager nicht lagern. Kannst du es zwischenzeitlich lagern?";
	    	}
	    }
	    else if(name instanceof Holz)
	    {
	    	if(((Holz) name).getArt().equals("Scheit"))
	    	{
	    		Aufgabentext = "Holzfäller Udo hat ein paar Holzscheite mitgebracht. Kannst du die zwischenlagern?";
	    	}
	    	if(((Holz) name).getArt().equals("Balken"))
	    	{
	    		Aufgabentext = "TreeIndustries Inc. braucht dringend für den Weitertransport ihrer Holzbalken Lagerplatz! Denkst du, dass dein Lager noch Platz dafür hat?";
	    	}
	    	if(((Holz) name).getArt().equals("Bretter"))
	    	{
	    		Aufgabentext = "EcoPrint GmbH hatte von der letzten Produktion noch ein paar Bretter übrig und will sie bei uns zwischenlagern. Geht das klar?";
	    	}
	    }
	    else
	    {
	    	if(zufallszahl == 0)
	    	{
	    		Aufgabentext = "Strabager GmbH hatte von einem fertiggestellten Bauprojekt noch einiges an " + ((Holz) name).getArt() + " übrig. Können wir das zwischenlagern?";
	    	}
	    	else if(zufallszahl == 1)
	    	{
	    		Aufgabentext = "Die TransFer GmbH will " + ((Holz) name).getArt() + "e bei uns zwischenlagern, damit DHM AG die " + ((Holz) name).getArt() + "e  morgen weiter transportieren kann. Geht das?";
	    	}
	    	else
	    	{
	    		Aufgabentext = "Das Bauunternehmen HighBuildings Limited benötigt für ein Hochhausbau demnächst, aber nicht zeitnah " + ((Holz) name).getArt() + "e. Aufgrund Sicherheitsbedenken auf dem Bau möchten Sie gerne das Produkt bei uns zwischenlagern. Sollte möglich sein, oder?";
	    	}
	    }
	}
	public String getAufgabentext() {
		return Aufgabentext;
	}
	public String getEndtext() {
		return Endtext;
	}
	public int getLagerplatz() {
		return lagerplatz;
	}
	public void setLagerplatz(int lagerplatz) {
		this.lagerplatz = lagerplatz;
	}
}
