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
	public JPanel anzeige = new JPanel();
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
		
		try
		{
		    //setUIFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,12));
		}
		catch(Exception e){}
		
		anzeige.setLayout(new GridBagLayout());
		fenster.setContentPane(anzeige);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		JPanel lagerraum = new JPanel();
		lagerraum.setLayout(new GridBagLayout());
		GridBagConstraints lagerraum_gbc = new GridBagConstraints();
		lagerraum_gbc.fill = GridBagConstraints.BOTH;
		lagerraum_gbc.weightx = 1;
		lagerraum_gbc.weighty = 1;
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				lagerraum_feld[i] = new JLabel();
				String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i));
				if(text == null) {
					text = "leer";
					lagerraum_feld[i].setForeground(Color.gray);
				}
				lagerraum_feld[i].setText(text);
				lagerraum_gbc.gridx = j;
				lagerraum_gbc.gridy = i;
				lagerraum.add(lagerraum_feld[i], lagerraum_gbc);
			}
		}
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.gridwidth = 2;
		anzeige.add(lagerraum, gbc);
		
		JPanel actionbuttons = new JPanel();
		actionbuttons.setLayout(new GridBagLayout());
		GridBagConstraints actionbuttons_gbc = new GridBagConstraints();
		actionbuttons_gbc.weightx = 1;
		actionbuttons_gbc.weighty = 1;
		actionbuttons_gbc.gridx = 0;
		actionbuttons_gbc.gridy = 0;
		
		JButton neuerauftrag = new JButton("Neuer Auftrag");
		neuerauftrag.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragHinzufuegen();
				Start.lager.printAuftraege();
				UpdateAuftragListe();
			}
		});
		actionbuttons.add(neuerauftrag, actionbuttons_gbc);
		actionbuttons_gbc.gridx = 1;
		actionbuttons_gbc.gridy = 0;
		JButton umlagern = new JButton("Umlagern");
		umlagern.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UmlagernWindow();
			}
		});
		actionbuttons.add(umlagern, actionbuttons_gbc);
		actionbuttons_gbc.gridx = 2;
		actionbuttons_gbc.gridy = 0;
		JButton verschrotten = new JButton("Verschrotten");
		verschrotten.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VerschrottenWindow();
			}
			
		});
		actionbuttons.add(verschrotten, actionbuttons_gbc);
		
		/*JLabel leer[] = new JLabel[3];
		for(int i = 0; i < 3; i++)
		{
			leer[i] = new JLabel();
			actionbuttons_gbc.gridx = i;
			actionbuttons_gbc.gridy = 1;
			actionbuttons.add(leer[i], actionbuttons_gbc);
		}*/
		kontostand.setText("Kontostand: 0€");
		kontostand.setFont(new Font(kontostand.getFont().getName(), Font.PLAIN, 20));
		actionbuttons_gbc.gridx = 0;
		actionbuttons_gbc.gridy = 1;
		actionbuttons_gbc.gridwidth = 3;
		actionbuttons.add(kontostand, actionbuttons_gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		anzeige.add(actionbuttons,gbc);
		
		
		JPanel auftragsauswahl = new JPanel();
		auftragsauswahl.setLayout(new GridBagLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.weightx = 1;
		gbc2.weighty = 1;
		for(int i = 0; i < 3; i++)
		{
			final int temp = i;
			auftrag_annehmen[i] = new JButton("\u2714");
			auftrag_annehmen[i].setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
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
			auftrag_zurueckstellen[i] = new JButton("\u274C");
			auftrag_zurueckstellen[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Start.lager.auftragZurueckstellen(temp);
					UpdateAuftragListe();
				}
			});
			gbc2.gridx = 0;
			gbc2.gridy = i;
			auftragsauswahl.add(auftrag_art[i], gbc2);
			/*JPanel produktdetails = new JPanel();
			//produktdetails.setLayout(new java.awt.GridLayout(3,1));
			for(int k = 0; k < 3; k++)
			{
				produktdetails.add(auftrag_produkt[i][k]);
			}
			auftragsauswahl.add(produktdetails);*/
			gbc2.gridx = 1;
			gbc2.gridy = i;
			auftragsauswahl.add(auftrag_produkt[i], gbc2);
			gbc2.gridx = 2;
			gbc2.gridy = i;
			auftragsauswahl.add(auftrag_kosten[i], gbc2);
			gbc2.gridx = 3;
			gbc2.gridy = i;
			auftragsauswahl.add(auftrag_annehmen[i], gbc2);
			gbc2.gridx = 4;
			gbc2.gridy = i;
			auftragsauswahl.add(auftrag_zurueckstellen[i], gbc2);
			auftrag_annehmen[i].setVisible(false);
			auftrag_zurueckstellen[i].setVisible(false);
		}
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		anzeige.add(auftragsauswahl, gbc);
		
		
		JPanel auswahl = new JPanel();
		auswahl.setLayout(new GridBagLayout());
		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.weightx = 1;
		gbc3.weighty = 1;
		gbc3.gridx = 0;
		gbc3.gridy = 0;
		gbc3.gridwidth = 2;
		JButton links = new JButton("<");
		auswahl.add(links, gbc3);
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
		gbc3.gridx = 2;
		gbc3.gridy = 0;
		gbc3.gridwidth = 1;
		auswahl.add(lagerauswahl_main, gbc3);
		gbc3.gridx = 3;
		gbc3.gridy = 0;
		gbc3.gridwidth = 2;
		JButton rechts = new JButton(">");
		auswahl.add(rechts, gbc3);
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
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		fenster.add(auswahl, gbc);
		
		JPanel geld = new JPanel();
		
		// Füge die Dinge zum Fenster hinzu
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
	public static void setUIFont(javax.swing.plaf.FontUIResource f)
	{   
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while(keys.hasMoreElements())
	    {
	        Object key = keys.nextElement();
	        Object value = UIManager.get(key);
	        if(value instanceof javax.swing.plaf.FontUIResource) UIManager.put(key, f);
	    }
	}
}
