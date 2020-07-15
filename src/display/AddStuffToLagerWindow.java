package display;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lagerregal.Start;

public class AddStuffToLagerWindow {
	public AddStuffToLagerWindow(int num) {
		JFrame auftraghinzufuegen = new JFrame();
		auftraghinzufuegen.setSize(400,400);
		auftraghinzufuegen.setLocationRelativeTo(null);
		auftraghinzufuegen.setTitle("Einlagerung");
		
		JLabel lagerauswahl = new JLabel("Lager 1");
		
		JPanel lagerraum = new JPanel();
		lagerraum.setLayout(new java.awt.GridLayout(3,3));
		JButton[] label = new JButton[9];
		for(int i = 0; i < 9; i++)
		{
			final int temp = i;
			label[i] = new JButton();
			String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i));
			if(text == null) {
				text = "leer";
			}
			else
			{
				label[i].setEnabled(false);
			}
			label[i].setText(text);
			label[i].setText(text);
			label[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(lagerauswahl.getText().equals("Lager 1"))
					{
						if(Start.lager.AuftragAbarbeiten(num, Start.window.getRightLagerplatz(temp)))
						{
							Start.window.UpdateMainLagerraum();
							Start.window.UpdateAuftragListe();
							auftraghinzufuegen.dispatchEvent(new WindowEvent(auftraghinzufuegen, WindowEvent.WINDOW_CLOSING));
							Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Der Lagerplatz ist bereits durch andere Dinge davor blockiert.");
						}
					}
					else if(lagerauswahl.getText().equals("Lager 2"))
					{
						if(Start.lager.AuftragAbarbeiten(num, Start.window.getRightLagerplatz(temp+9)))
						{
							Start.window.UpdateMainLagerraum();
							Start.window.UpdateAuftragListe();
							auftraghinzufuegen.dispatchEvent(new WindowEvent(auftraghinzufuegen, WindowEvent.WINDOW_CLOSING));
							Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Der Lagerplatz ist bereits durch andere Dinge davor blockiert.");
						}
					}
					else if(lagerauswahl.getText().equals("Lager 3"))
					{
						if(Start.lager.AuftragAbarbeiten(num, Start.window.getRightLagerplatz(temp+18)))
						{
							Start.window.UpdateMainLagerraum();
							Start.window.UpdateAuftragListe();
							auftraghinzufuegen.dispatchEvent(new WindowEvent(auftraghinzufuegen, WindowEvent.WINDOW_CLOSING));
							Start.window.kontostand.setText("Dein Kontostand: " + Integer.toString(Start.bilanz.getGesamtkonto()) + "€");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Der Lagerplatz ist bereits durch andere Dinge davor blockiert.");
						}
					}
					else
					{
						
					}
				}
			});
			lagerraum.add(label[i]);
		}
		
		
		JPanel auswahl = new JPanel();
		auswahl.setLayout(new java.awt.FlowLayout());
		JButton links = new JButton("<");
		links.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(lagerauswahl.getText().equals("Lager 1"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+18));
						if(text == null) {
							text = "leer";
							label[i].setEnabled(true);
						}
						else
						{
							label[i].setEnabled(false);
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 3");
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i));
						if(text == null) {
							text = "leer";
							label[i].setEnabled(true);
						}
						else
						{
							label[i].setEnabled(false);
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 1");
				}
				else if(lagerauswahl.getText().equals("Lager 3"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+9));
						if(text == null) {
							text = "leer";
							label[i].setEnabled(true);
						}
						else
						{
							label[i].setEnabled(false);
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 2");
				}
				else
				{
					
				}
			}
		});
		JButton rechts = new JButton(">");
		rechts.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(lagerauswahl.getText().equals("Lager 1"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+9));
						if(text == null) {
							text = "leer";
							label[i].setEnabled(true);
						}
						else
						{
							label[i].setEnabled(false);
						}
						label[i].setText(text);	
					}
					lagerauswahl.setText("Lager 2");
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+18));
						if(text == null) {
							text = "leer";
							label[i].setEnabled(true);
						}
						else
						{
							label[i].setEnabled(false);
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 3");
				}
				else if(lagerauswahl.getText().equals("Lager 3"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i));
						if(text == null) {
							text = "leer";
							label[i].setEnabled(true);
						}
						else
						{
							label[i].setEnabled(false);
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 1");
				}
				else
				{
					
				}
			}
		});
		auswahl.add(links);
		auswahl.add(lagerauswahl);
		auswahl.add(rechts);
		
		JPanel text_panel = new JPanel();
		JLabel text = new JLabel();
		text.setText(Start.lager.getAuftrag(num).getInhalt());
		text_panel.add(text);
		
		JPanel anzeige = new JPanel();
		anzeige.setLayout(new java.awt.GridLayout(3,1));
		anzeige.add(lagerraum);
		anzeige.add(auswahl);
		anzeige.add(text_panel);
		auftraghinzufuegen.getContentPane().add(anzeige);
		auftraghinzufuegen.setVisible(true);
	}
}
