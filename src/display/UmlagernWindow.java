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

public class UmlagernWindow {
	private String modus = "Lagerquelle";
	private int lagerquelle = 0;
	private JLabel textbeschreibung = new JLabel();
	private int[] checkliste;
	private JLabel lagerauswahl;
	private JButton[] label;
	public UmlagernWindow() {
		if(Start.lager.isLagerEmpty())
		{
			JOptionPane.showMessageDialog(null, "Das Lager ist noch leer!");
		}
		else
		{
			JFrame fenster = new JFrame();
			fenster.setSize(400,400);
			fenster.setLocationRelativeTo(null);
			fenster.setTitle("Wähle die Quelle");
			
			lagerauswahl = new JLabel("Lager 1");
			
			JPanel lagerraum = new JPanel();
			lagerraum.setLayout(new java.awt.GridLayout(3,3));
			label = new JButton[9];
			for(int i = 0; i < 9; i++)
			{
				final int temp = i;
				label[i] = new JButton();
				String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i));
				if(text == null) {
					text = "leer";
					label[i].setEnabled(false);
				}
				else
				{
					label[i].setEnabled(true);
				}
				label[i].setText(text);
				label[i].addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						int adder = temp;
						if(lagerauswahl.getText().equals("Lager 2"))
						{
							adder = temp + 9;
						}
						if(lagerauswahl.getText().equals("Lager 3")) {
							adder = temp + 18;
						}
						if(modus == "Lagerquelle")
						{
							modus = "Lagerziel";
							lagerquelle = Start.window.getRightLagerplatz(adder);
							checkliste = Start.lager.getArrayVonFreienPlaetzen(Start.window.getRightLagerplatz(adder));

							JButton button = (JButton)e.getSource();
							button.setEnabled(false);
							fenster.setTitle("Wähle das Ziel");
							textbeschreibung.setText("Wähle nun das Ziel aus, wo das Produkt umgelagert werden soll.");
							for(int i = 0; i < 9; i++)
							{
									if(label[i].getText().equals("leer"))
									{
										if (checkliste[Start.window.getRightLagerplatz(adder)] == 0) {
											label[i].setEnabled(false);
										} else {
											label[i].setEnabled(true);
										}
									}
									else
									{
										label[i].setEnabled(false);
									}	
							}
						}
						else
						{
							int lagerziel = 0;
							lagerziel = Start.window.getRightLagerplatz(adder);
							if(Start.lager.Umlagern(lagerquelle, lagerziel))
							{
								Start.window.GotoLager(adder);
								System.out.println(Start.window.getGrafikLagerplatz(lagerquelle) + ","+ Start.window.getGrafikLagerplatz(lagerziel));
								Start.window.letzteAktion.setText("Letzte Aktion: Umlagerung von " + Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(adder)) + " von Lager " + Start.lager.getLagerplatzFromInhalt(Start.window.getGrafikLagerplatz(lagerquelle)) + " zu Lager " + Start.lager.getLagerplatzFromInhalt(Start.window.getGrafikLagerplatz(lagerziel)));
								fenster.dispatchEvent(new WindowEvent(fenster, WindowEvent.WINDOW_CLOSING));
								Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Das Lagerziel ist bereits besetzt.");
							}
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
				public void actionPerformed(ActionEvent e)
				{
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
			auswahl.add(rechts);
			
			JPanel unten = new JPanel();
			
			textbeschreibung.setText("<html><center>Wähle ein Produkt aus, was du umlagern möchtest.<br>Umlagern kostet 100€</center></html>");
			unten.add(textbeschreibung);
			
			JPanel anzeige = new JPanel();
			anzeige.setLayout(new java.awt.GridLayout(3,1));
			anzeige.add(lagerraum);
			anzeige.add(auswahl);
			anzeige.add(unten);
			fenster.getContentPane().add(anzeige);
			fenster.setVisible(true);	
		}
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
		if(modus == "Lagerquelle")
		{
			for(int i = 0; i < 9; i++)
			{
				String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+adder));
				if(text == null) {
					text = "leer";
					label[i].setEnabled(false);
				}
				else
				{
					label[i].setEnabled(true);
				}
				label[i].setText(text);
			}
		}
		else
		{
			for(int i = 0; i < 9; i++)
			{
				String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+adder));
				if(text == null) {
					text = "leer";
					if (checkliste[Start.window.getRightLagerplatz(i+adder)] == 0) {
						label[i].setEnabled(false);
					} else {
						label[i].setEnabled(true);
					}
				}
				else
				{
					label[i].setEnabled(false);
				}
				label[i].setText(text);
			}	
		}
	}
}
