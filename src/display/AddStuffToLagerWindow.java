package display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

import lagerregal.Start;

public class AddStuffToLagerWindow {
	JLabel lagerauswahl;
	JButton[] label;
	int checkliste[];
	public AddStuffToLagerWindow(int num) {
		JFrame auftraghinzufuegen = new JFrame();
		auftraghinzufuegen.setSize(400, 400);
		auftraghinzufuegen.setLocationRelativeTo(null);
		auftraghinzufuegen.setTitle("Einlagerung");

		lagerauswahl = new JLabel("Lager 1");

		JPanel lagerraum1 = new JPanel();
		lagerraum1.setLayout(new java.awt.GridLayout(3, 3));
		
		JPanel lagerraum2 = new JPanel();
		lagerraum2.setLayout(new java.awt.GridLayout(3, 3));
		
		JPanel lagerraum3 = new JPanel();
		lagerraum3.setLayout(new java.awt.GridLayout(3, 3));
		
		label = new JButton[27];

		checkliste = Start.lager.getArrayVonFreienPlaetzen(Start.lager.getAuftrag(num));
		int check = 0;
		for (int i = 0; i < 27; i++) {
			if (checkliste[i] == 1) {
				check++;
			}
		}

		for (int i = 0; i < 27; i++) {
			final int temp = i;
			label[i] = new JButton();
			String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i));
			if (text == null) {
				if (checkliste[Start.window.getRightLagerplatz(i)] == 0) {
					label[i].setEnabled(false);
				} else {
					label[i].setBackground(Color.GREEN);
					label[i].setEnabled(true);
				}
				text = "leer";
			} else {
				label[i].setEnabled(false);
			}
			label[i].setText(text);
			label[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int i = temp;
					/*if(lagerauswahl.getText().equals("Lager 2"))
					{
						i = temp + 9;
					}
					if(lagerauswahl.getText().equals("Lager 3"))
					{
						i = temp + 18;
					}*/
					if(Start.lager.AuftragAbarbeiten(num, Start.window.getRightLagerplatz(i)))
					{
						Start.window.UpdateMainLagerraum();
						//Start.window.GotoLager(i);
						Start.window.UpdateAuftragListe();
						Start.window.letzteAktion.setText("Letzte Aktion: Einlagerung von " + Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i)) + " in Lager " + Start.lager.getLagerplatzFromInhalt(Start.window.getRightLagerplatz(i)));
						auftraghinzufuegen
								.dispatchEvent(new WindowEvent(auftraghinzufuegen, WindowEvent.WINDOW_CLOSING));
						Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
					}
					else
					{
						JOptionPane.showMessageDialog(null,
								"Der Lagerplatz ist bereits durch andere Dinge davor blockiert.");
					}
				}
			});
			if(i < 9)
			{
				lagerraum1.add(label[i]);	
			}
			else if(i < 18)
			{
				lagerraum2.add(label[i]);
			}
			else
			{
				lagerraum3.add(label[i]);
			}
		}

		/*JPanel auswahl = new JPanel();
		auswahl.setLayout(new java.awt.FlowLayout());
		JButton links = new JButton("<");
		links.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lagerauswahl.getText().equals("Lager 1"))
				{
					lagerauswahl.setText("Lager 3");
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					lagerauswahl.setText("Lager 1");
				}
				else
				{
					lagerauswahl.setText("Lager 2");
				}
				UpdateLager();
			}
		});
		JButton rechts = new JButton(">");
		rechts.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lagerauswahl.getText().equals("Lager 1"))
				{
					lagerauswahl.setText("Lager 2");
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					lagerauswahl.setText("Lager 3");
				}
				else
				{
					lagerauswahl.setText("Lager 1");
				}
				UpdateLager();
			}
		});
		auswahl.add(links);
		auswahl.add(lagerauswahl);
		auswahl.add(rechts);*/

		JPanel text_panel = new JPanel();
		JLabel text = new JLabel();
		if (check != 0) {
			text.setText("<html><body><center>" + Start.lager.getAuftrag(num).getInhalt() + "<br>Es sind noch " + check
					+ " Plaetze frei.</center></body></html>");
		} else {
			text.setForeground(Color.RED);
			text.setText(
					"<html><body><center>Es sind keine Plaetze mehr frei! <br>Du musst einige Produkte umlagern oder loeschen!</center></body></html>");
		}
		text_panel.add(text);

		JPanel anzeige = new JPanel();
		anzeige.setLayout(new java.awt.GridLayout(6, 1));
		anzeige.add(lagerraum1);
		anzeige.add(new JLabel());
		anzeige.add(lagerraum2);
		anzeige.add(new JLabel());
		anzeige.add(lagerraum3);
		//anzeige.add(auswahl);
		anzeige.add(text_panel);
		auftraghinzufuegen.getContentPane().add(anzeige);
		auftraghinzufuegen.setVisible(true);
	}
	
	public void UpdateLager() {
		int adder = 0;
		if(lagerauswahl.getText().equals("Lager 2"))
		{
			adder = 9;
		}
		if(lagerauswahl.getText().equals("Lager 3"))
		{
			adder = 18;
		}
		for(int i = 0; i < 9; i++)
		{
			label[i].setBackground(new JButton().getBackground());
			String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i + adder));
			if (text == null) {
				if (checkliste[Start.window.getRightLagerplatz(i + adder)] == 0) {
					label[i].setEnabled(false);
				} else {
					label[i].setBackground(Color.GREEN);
					label[i].setEnabled(true);
				}
				text = "leer";
			} else {
				label[i].setEnabled(false);
			}
			label[i].setText(text);
		}
	}
}
