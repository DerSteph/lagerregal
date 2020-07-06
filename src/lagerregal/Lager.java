package lagerregal;

import java.util.*;

public class Lager {
	private double Umsatz;
	private Object[] inhalt;
	private Queue schlange = new LinkedList();
	private int schlangelaenge = 0;
	public Lager() {
		this.inhalt = new Object[9];
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
}
