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
	public UmlagernWindow() {
		JFrame fenster = new JFrame();
		fenster.setSize(400,400);
		fenster.setLocationRelativeTo(null);
		fenster.setTitle("Wähle die Quelle");
		
		JLabel lagerauswahl = new JLabel("Lager 1");
		
		JPanel lagerraum = new JPanel();
		lagerraum.setLayout(new java.awt.GridLayout(3,3));
		JButton[] label = new JButton[9];
		for(int i = 0; i < 9; i++)
		{
			final int temp = i;
			label[i] = new JButton();
			String text = Start.lager.getLagerplatzInhalt(i);
			if(text == null) {
				text = "leer";
				label[i].setBorderPainted(false);
				label[i].setEnabled(false);
			}
			else
			{
				label[i].setBorderPainted(true);
				label[i].setEnabled(true);
			}
			label[i].setText(text);
			label[i].setText(text);
			label[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(modus == "Lagerquelle")
					{
						modus = "Lagerziel";
						if(lagerauswahl.getText().equals("Lager 1"))
						{
							lagerquelle = Start.window.getRightLagerplatz(temp);
						}
						else if(lagerauswahl.getText().equals("Lager 2"))
						{
							lagerquelle = Start.window.getRightLagerplatz(temp+9);
						}
						else
						{
							lagerquelle = Start.window.getRightLagerplatz(temp+18);
						}
						JButton button = (JButton)e.getSource();
						button.setEnabled(false);
						fenster.setTitle("Wähle das Ziel");
						textbeschreibung.setText("Wähle nun das Ziel aus, wo das Produkt umgelagert werden soll.");
						for(int i = 0; i < 9; i++)
						{
							if(label[i].getText().equals("leer"))
							{
								label[i].setEnabled(true);
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
						if(lagerauswahl.getText().equals("Lager 1"))
						{
							lagerziel = Start.window.getRightLagerplatz(temp);
						}
						else if(lagerauswahl.getText().equals("Lager 2"))
						{
							lagerziel = Start.window.getRightLagerplatz(temp+9);
						}
						else
						{
							lagerziel = Start.window.getRightLagerplatz(temp+18);
						}
						if(Start.lager.Umlagern(lagerquelle, lagerziel))
						{
							Start.window.UpdateMainLagerraum();
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
					if(modus == "Lagerquelle")
					{
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+18));
							if(text == null) {
								text = "leer";
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							else
							{
								label[i].setBorderPainted(true);
								label[i].setEnabled(true);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 3");	
					}
					else
					{
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+18));
							if(text == null)
							{
								text = "leer";
								label[i].setBorderPainted(true);
								label[i].setBorderPainted(true);
							}
							else
							{
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 3");
					}
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					if(modus == "Lagerquelle")
					{
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i));
							if(text == null) {
								text = "leer";
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							else
							{
								label[i].setBorderPainted(true);
								label[i].setEnabled(true);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 1");
					}
					else
					{
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i));
							if(text == null) {
								text = "leer";
								label[i].setBorderPainted(true);
								label[i].setEnabled(true);
							}
							else
							{
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 1");
					}
				}
				else if(lagerauswahl.getText().equals("Lager 3"))
				{
					if(modus == "Lagerquelle")
					{
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+9));
							if(text == null) {
								text = "leer";
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							else
							{
								label[i].setBorderPainted(true);
								label[i].setEnabled(true);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 2");
					}
					else
					{
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+9));
							if(text == null) {
								text = "leer";
								label[i].setBorderPainted(true);
								label[i].setEnabled(true);
							}
							else
							{
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 2");
					}
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
					if(modus == "Lagerquelle")
					{
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+9));
							if(text == null) {
								text = "leer";
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							else
							{
								label[i].setBorderPainted(true);
								label[i].setEnabled(true);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 2");	
					}
					else
					{
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+9));
							if(text == null) {
								text = "leer";
								label[i].setBorderPainted(true);
								label[i].setEnabled(true);
							}
							else
							{
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 2");	
					}
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					if(modus == "Lagerquelle")
					{
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+18));
							if(text == null) {
								text = "leer";
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							else
							{
								label[i].setBorderPainted(true);
								label[i].setEnabled(true);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 3");	
					}
					else
					{
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i+18));
							if(text == null) {
								text = "leer";
								label[i].setBorderPainted(true);
								label[i].setEnabled(true);
							}
							else
							{
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 3");	
					}
				}
				else if(lagerauswahl.getText().equals("Lager 3"))
				{
					if(modus == "Lagerquelle") {
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i));
							if(text == null) {
								text = "leer";
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							else
							{
								label[i].setBorderPainted(true);
								label[i].setEnabled(true);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 1");
					}
					else
					{
						for(int i = 0; i < 9; i++)
						{
							String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatz(i));
							if(text == null) {
								text = "leer";
								label[i].setBorderPainted(true);
								label[i].setEnabled(true);
							}
							else
							{
								label[i].setBorderPainted(false);
								label[i].setEnabled(false);
							}
							label[i].setText(text);
						}
						lagerauswahl.setText("Lager 1");
					}
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
		
		textbeschreibung.setText("Wähle ein Produkt aus, was du umlagern möchtest.");
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
