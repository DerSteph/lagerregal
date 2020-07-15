package display;

import javax.swing.*;

import lagerregal.Holz;
import lagerregal.Papier;
import lagerregal.Start;
import lagerregal.Stein;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;


public class MainWindow {
	public JPanel lagerraum = new JPanel();
	public JLabel[] lagerraum_feld = new JLabel[9];
	
	public JLabel auftrag_art[] = new JLabel[3];
	public JButton auftrag_annehmen[] = new JButton[3];
	public JButton auftrag_zurueckstellen[] = new JButton[3];
	/*public JLabel auftrag_produkt[][] = new JLabel[3][3];*/
	public JLabel auftrag_produkt[] = new JLabel[3];
	public JLabel auftrag_kosten[] = new JLabel[3];
	
	public JLabel lagerauswahl_main = new JLabel("Lager 1");
	public JLabel kontostand = new JLabel();
	public MainWindow() {
		JFrame fenster = new JFrame();
		fenster.setSize(new Dimension(800, 600));
		fenster.setResizable(false);
		fenster.setLocationRelativeTo(null);
		fenster.setTitle("Lagerregal - das Spiel");
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lagerraum.setLayout(new java.awt.GridLayout(3,3));
		/*JLabel label1 = new JLabel();
		label1.setText("Lager 1");
		panel.add(label1);*/
		for(int i = 0; i < 9; i++)
		{
			lagerraum_feld[i] = new JLabel();
			String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i));
			if(text == null) {
				text = "leer";
				lagerraum_feld[i].setForeground(Color.gray);
			}
			lagerraum_feld[i].setText(text);
			lagerraum.add(lagerraum_feld[i]);
		}
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new java.awt.GridLayout(2,1));
		
		JPanel auftragsauswahl = new JPanel();
		auftragsauswahl.setLayout(new java.awt.GridLayout(3,5));
		
		for(int i = 0; i < 3; i++)
		{
			final int temp = i;
			auftrag_annehmen[i] = new JButton("Ann");
			auftrag_annehmen[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() instanceof JButton)
					{
						String text = ((JButton) e.getSource()).getText();
						if(!text.equals(""))
						{
							if(Start.lager.getAuftrag(temp).getLagerungsart().equals("Einlagerung"))
							{
								new AddStuffToLagerWindow(temp);
							}
							else
							{
								new RemoveStuffFromLagerWindow(temp);
							}
						}
					}
				}
			});
			auftrag_art[i] = new JLabel();
			//auftrag_art[i].setText(Start.lager.getAuftrag(i).getLagerungsart());
			/*for(int k = 0; k < 3; k++)
			{
				auftrag_produkt[i][k] = new JLabel();
				if(Start.lager.getAuftrag(i) instanceof Papier)
				{
					if(k == 0)
					{
						auftrag_produkt[i][k].setText(Start.lager.getAuftrag(i).toString());
					}
					if(k == 1)
					{
						auftrag_produkt[i][k].setText(((Papier) Start.lager.getAuftrag(i)).getFarbe());
					}
					if(k == 2)
					{
						auftrag_produkt[i][k].setText(((Papier) Start.lager.getAuftrag(i)).getGroesse());
					}
				}
				if(Start.lager.getAuftrag(i) instanceof Holz)
				{
					if(k == 0)
					{
						auftrag_produkt[i][k].setText(Start.lager.getAuftrag(i).toString());
					}
					if(k == 1)
					{
						auftrag_produkt[i][k].setText(((Holz) Start.lager.getAuftrag(i)).getArt());
					}
					if(k == 2)
					{
						auftrag_produkt[i][k].setText(((Holz) Start.lager.getAuftrag(i)).getForm());
					}
				}
				if(Start.lager.getAuftrag(i) instanceof Stein)
				{
					if(k == 0)
					{
						auftrag_produkt[i][k].setText(Start.lager.getAuftrag(i).toString());
					}
					if(k == 1)
					{
						auftrag_produkt[i][k].setText(((Stein) Start.lager.getAuftrag(i)).getArt());
					}
					if(k == 2)
					{
						auftrag_produkt[i][k].setText(((Stein) Start.lager.getAuftrag(i)).getGewicht());
					}
				}
			}*/
			auftrag_produkt[i] = new JLabel();
			auftrag_kosten[i] = new JLabel();
			//auftrag_kosten[i].setText(Integer.toString(Start.lager.getAuftrag(i).getKosten()));
			auftrag_zurueckstellen[i] = new JButton("Abl");
			auftrag_zurueckstellen[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					/*
					 * 
					 * Noch nicht implementiert
					 * 
					 */
				}
			});
			
			auftragsauswahl.add(auftrag_art[i]);
			/*JPanel produktdetails = new JPanel();
			//produktdetails.setLayout(new java.awt.GridLayout(3,1));
			for(int k = 0; k < 3; k++)
			{
				produktdetails.add(auftrag_produkt[i][k]);
			}
			auftragsauswahl.add(produktdetails);*/
			auftragsauswahl.add(auftrag_produkt[i]);
			auftragsauswahl.add(auftrag_kosten[i]);
			auftragsauswahl.add(auftrag_annehmen[i]);
			auftragsauswahl.add(auftrag_zurueckstellen[i]);
			auftrag_annehmen[i].setVisible(false);
			auftrag_zurueckstellen[i].setVisible(false);
		}
		
		JPanel actionbuttons = new JPanel();
		actionbuttons.setLayout(new java.awt.GridLayout(1,3));

		JButton neuerauftrag = new JButton("Neuer Auftrag");
		neuerauftrag.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragHinzufuegen();
				Start.lager.printAuftraege();
				UpdateAuftragListe();
			}
		});
		
		JButton umlagern = new JButton("Umlagern");
		umlagern.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UmlagernWindow();
			}
		});
		JButton verschrotten = new JButton("Verschrotten");
		verschrotten.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VerschrottenWindow();
			}
			
		});
		actionbuttons.add(neuerauftrag);
		actionbuttons.add(umlagern);
		actionbuttons.add(verschrotten);
		
		buttons.add(actionbuttons);
		buttons.add(auftragsauswahl);
		
		JPanel auswahl = new JPanel();
		auswahl.setLayout(new java.awt.FlowLayout());
		JButton links = new JButton("<");
		links.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String text;
				if(lagerauswahl_main.getText().equals("Lager 1"))
				{
					for(int i = 0; i < 9; i++)
					{
						text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i+18));
						if(text == null) {
							text = "leer";
							lagerraum_feld[i].setForeground(Color.gray);
						}
						lagerraum_feld[i].setText(text);
					}
					lagerauswahl_main.setText("Lager 3");
				}
				else if(lagerauswahl_main.getText().equals("Lager 2"))
				{
					for(int i = 0; i < 9; i++)
					{
						text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i));
						if(text == null) {
							text = "leer";
							lagerraum_feld[i].setForeground(Color.gray);
						}
						lagerraum_feld[i].setText(text);
					}
					lagerauswahl_main.setText("Lager 1");
				}
				else if(lagerauswahl_main.getText().equals("Lager 3"))
				{
					for(int i = 0; i < 9; i++)
					{
						text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i+9));
						if(text == null) {
							text = "leer";
							lagerraum_feld[i].setForeground(Color.gray);
						}
						lagerraum_feld[i].setText(text);
					}
					lagerauswahl_main.setText("Lager 2");
				}
				else
				{
					
				}
				Start.lager.getLagerinhalt();
			}
		});
		JButton rechts = new JButton(">");
		rechts.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(lagerauswahl_main.getText().equals("Lager 1"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i+9));
						if(text == null) {
							text = "leer";
							lagerraum_feld[i].setForeground(Color.gray);
						}
						lagerraum_feld[i].setText(text);
					}
					lagerauswahl_main.setText("Lager 2");
				}
				else if(lagerauswahl_main.getText().equals("Lager 2"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i+18));
						if(text == null) {
							text = "leer";
							lagerraum_feld[i].setForeground(Color.gray);
						}
						lagerraum_feld[i].setText(text);
					}
					lagerauswahl_main.setText("Lager 3");
				}
				else if(lagerauswahl_main.getText().equals("Lager 3"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i));
						if(text == null) {
							text = "leer";
							lagerraum_feld[i].setForeground(Color.gray);
						}
						lagerraum_feld[i].setText(text);
					}
					lagerauswahl_main.setText("Lager 1");
				}
				else
				{
					
				}
				Start.lager.getLagerinhalt();
			}
		});
		auswahl.add(links);
		auswahl.add(lagerauswahl_main);
		auswahl.add(rechts);
		
		JPanel geld = new JPanel();
		geld.setLayout(new java.awt.GridLayout(2,1));
		
		kontostand.setText("Dein Kontostand: 0€");
		geld.add(kontostand);
		
		JButton bilanz = new JButton("Bilanz anzeigen");
		geld.add(bilanz);
		bilanz.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new BilanzWindow();
			}
		});
		
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
	
	public int getRightLagerplatz(int number) {
		int rightNumber[] = new int[]{24,25,26,15,16,17,6,7,8,21,22,23,12,13,14,3,4,5,18,19,20,9,10,11,0,1,2};
		return rightNumber[number];
	}
	
	public void UpdateAuftragListe() {
		for(int i = 0; i < 3; i++)
		{
			if(Start.lager.getAuftrag(i) != null)
			{
				auftrag_art[i].setText(Start.lager.getAuftrag(i).getLagerungsart());
				auftrag_produkt[i].setText(Start.lager.getAuftrag(i).getInhalt());
				auftrag_kosten[i].setText(Integer.toString(Start.lager.getAuftrag(i).getKosten()));
				auftrag_annehmen[i].setVisible(true);
				auftrag_zurueckstellen[i].setVisible(true);
			}
			else
			{
				auftrag_art[i].setText("");
				auftrag_produkt[i].setText("");
				auftrag_kosten[i].setText("");
				auftrag_annehmen[i].setVisible(false);
				auftrag_zurueckstellen[i].setVisible(false);
			}
		}
	}
	
	public void UpdateMainLagerraum () {
		if(lagerauswahl_main.getText().equals("Lager 1"))
		{
			for(int i = 0; i < 9; i++)
			{
				String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i));
				if(text == null) {
					text = "leer";
					lagerraum_feld[i].setForeground(Color.gray);
				}
				lagerraum_feld[i].setText(text);
			}
		}
		else if(lagerauswahl_main.getText().equals("Lager 2"))
		{
			for(int i = 0; i < 9; i++)
			{
				String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i+9));
				if(text == null) {
					text = "leer";
					lagerraum_feld[i].setForeground(Color.gray);
				}
				lagerraum_feld[i].setText(text);
			}
		}
		else if(lagerauswahl_main.getText().equals("Lager 3"))
		{
			for(int i = 0; i < 9; i++)
			{
				String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i+18));
				if(text == null) {
					text = "leer";
					lagerraum_feld[i].setForeground(Color.gray);
				}
				lagerraum_feld[i].setText(text);
			}
		}
		else
		{
			
		}
	}
}
