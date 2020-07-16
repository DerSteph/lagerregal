package display;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import lagerregal.Start;

public class VerschrottenWindow {
	public VerschrottenWindow() {
		if(Start.lager.isLagerEmpty())
		{
			JOptionPane.showMessageDialog(null, "Das Lager ist noch leer!");
		}
		else
		{
			JFrame fenster = new JFrame();
			fenster.setSize(400,400);
			fenster.setLocationRelativeTo(null);
			fenster.setTitle("Verschrotten");
			
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
					label[i].setEnabled(false);
				}
				else
				{
					label[i].setEnabled(true);
				}
				label[i].setText(text);
				label[i].setText(text);
				label[i].addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							if(lagerauswahl.getText().equals("Lager 1"))
							{
								if(Start.lager.Verschrotten(Start.window.getRightLagerplatz(temp)))
								{
									Start.window.UpdateMainLagerraum();
									fenster.dispatchEvent(new WindowEvent(fenster, WindowEvent.WINDOW_CLOSING));
									Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Die Lagerquelle ist leer.");
								}
							}
							else if(lagerauswahl.getText().equals("Lager 2"))
							{
								if(Start.lager.Verschrotten(Start.window.getRightLagerplatz(temp+9)))
								{
									Start.window.UpdateMainLagerraum();
									fenster.dispatchEvent(new WindowEvent(fenster, WindowEvent.WINDOW_CLOSING));
									Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Die Lagerquelle ist leer.");
								}
							}
							else
							{
								if(Start.lager.Verschrotten(Start.window.getRightLagerplatz(temp+18)))
								{
									Start.window.UpdateMainLagerraum();
									fenster.dispatchEvent(new WindowEvent(fenster, WindowEvent.WINDOW_CLOSING));
									Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Die Lagerquelle ist leer.");
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
				public void actionPerformed(ActionEvent e) {
					if(lagerauswahl.getText().equals("Lager 1"))
					{
							for(int i = 0; i < 9; i++)
							{
								String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+18));
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
							lagerauswahl.setText("Lager 3");	
					}
					else if(lagerauswahl.getText().equals("Lager 2"))
					{
							for(int i = 0; i < 9; i++)
							{
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
									label[i].setEnabled(false);
								}
								else
								{
									label[i].setEnabled(true);
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
				public void actionPerformed(ActionEvent e) {
					if(lagerauswahl.getText().equals("Lager 1"))
					{
							for(int i = 0; i < 9; i++)
							{
								String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+9));
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
							lagerauswahl.setText("Lager 2");	
					}
					else if(lagerauswahl.getText().equals("Lager 2"))
					{
							for(int i = 0; i < 9; i++)
							{
								String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+18));
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
							lagerauswahl.setText("Lager 3");	
					}
					else if(lagerauswahl.getText().equals("Lager 3"))
					{
							for(int i = 0; i < 9; i++)
							{
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
			
			JPanel unten = new JPanel();
			JLabel textbeschreibung = new JLabel();
			textbeschreibung.setText("<html><body><center>Wähle ein Produkt aus, was verschrottet werden soll.<br>Verschrotten kostet <b>500€</b></center></body></html>");
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
}
