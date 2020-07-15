package display;

import javax.swing.*;

import lagerregal.Start;

import java.awt.*;

public class BilanzWindow {
	public BilanzWindow() {
		JFrame bilanzfenster = new JFrame();
		bilanzfenster.setSize(400,400);
		bilanzfenster.setLocationRelativeTo(null);
		bilanzfenster.setTitle("Bilanz");
		
		JPanel oben = new JPanel();
		oben.setLayout(new GridLayout(1,3));
		
		JLabel kontostand = new JLabel(Integer.toString(Start.bilanz.getGesamtkonto()));
		JLabel gesamtplus = new JLabel(Integer.toString(Start.bilanz.getGesamtplus()));
		JLabel gesamtminus = new JLabel(Integer.toString(Start.bilanz.getGesamtminus()));
		
		oben.add(kontostand);
		oben.add(gesamtplus);
		oben.add(gesamtminus);
		
		JPanel unten = new JPanel();
		JTable verlauf = new JTable();
		Container content = new Container();
		
		content.add(new JScrollPane());
		content.setLayout(new BorderLayout());
		content.add(verlauf.getTableHeader(), BorderLayout.PAGE_START);
		content.add(verlauf, BorderLayout.CENTER);
		
		JPanel anzeige = new JPanel();
		anzeige.setLayout(new GridLayout(2,1));
		anzeige.add(oben);
		anzeige.add(unten);
		bilanzfenster.getContentPane().add(anzeige);
		bilanzfenster.setVisible(true);
	}
}
