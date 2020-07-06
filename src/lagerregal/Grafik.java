package lagerregal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Grafik {
	public Grafik() {
		JFrame fenster = new JFrame();
		fenster.setSize(400,400);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel lagerraum = new JPanel();
		lagerraum.setLayout(new java.awt.GridLayout(3,3));
		/*JLabel label1 = new JLabel();
		label1.setText("Lager 1");
		panel.add(label1);*/
		JLabel[] label = new JLabel[9];
		for(int i = 0; i < 9; i++)
		{
			label[i] = new JLabel();
			label[i].setText("Lager " + (i+1));
			lagerraum.add(label[i]);
		}
		
		JPanel buttons = new JPanel();
		JButton button1 = new JButton("Neuer Auftrag");
		button1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Test.test.leseZeile();
			}
		});
		buttons.add(button1);
		
		JPanel auswahl = new JPanel();
		auswahl.setLayout(new java.awt.FlowLayout());
		JButton links = new JButton("<");
		JButton rechts = new JButton(">");
		JLabel lagerauswahl = new JLabel("Lager 1");
		auswahl.add(links);
		auswahl.add(lagerauswahl);
		auswahl.add(rechts);
		
		JPanel geld = new JPanel();
		JLabel geldgesamt = new JLabel();
		geldgesamt.setText("Gesamt: 0€");
		geld.add(geldgesamt);
		
		JPanel anzeige = new JPanel();
		anzeige.setLayout(new java.awt.GridLayout(2,2));
		anzeige.add(lagerraum);
		anzeige.add(buttons);
		anzeige.add(auswahl);
		anzeige.add(geld);
		
		//fenster.getContentPane().add(lagerraum);
		fenster.getContentPane().add(anzeige);
		fenster.setVisible(true);
	}
}
