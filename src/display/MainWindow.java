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
	public JButton auftrag_ablehnen[] = new JButton[3];
	
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
		
		//fenster.setContentPane(anzeige);

		
		JPanel lagerraum = new JPanel();
		lagerraum.setLayout(null);
		
		lagerraum.setLocation(0, 0);
		lagerraum.setSize(new Dimension(402,600));
		
		int j = 0;
		int k = 0;
		for(int i = 0; i < 9; i++)
		{
			lagerraum_feld[i] = new JLabel();
			String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i));
			if(text == null) {
				text = "leer";
				lagerraum_feld[i].setForeground(Color.gray);
			}
			lagerraum_feld[i].setText("<html><div style='text-align: center;'>" + text + "</div></html>");
			lagerraum_feld[i].setBounds(j*134, k*150, 134, 150);
			if(i % 2 == 0)
			{
				lagerraum_feld[i].setBackground(Color.white);
				lagerraum_feld[i].setOpaque(true);
			}
			lagerraum.add(lagerraum_feld[i]);
			if(j == 2)
			{
				j = 0;
				k++;
			}
			else
			{
				j++;
			}
		}
		fenster.add(lagerraum);
		
		
		JPanel actionbuttons = new JPanel(null);
		actionbuttons.setLocation(403, 0);
		JButton neuerauftrag = new JButton("Neuer Auftrag");
		neuerauftrag.setBounds(403,10,120,30);
		neuerauftrag.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragHinzufuegen();
				Start.lager.printAuftraege();
				UpdateAuftragListe();
			}
		});
		JButton umlagern = new JButton("Umlagern");
		umlagern.setBounds(523,10,120,30);
		umlagern.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UmlagernWindow();
			}
		});
		JButton verschrotten = new JButton("Verschrotten");
		verschrotten.setBounds(643,10,120,30);
		verschrotten.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new VerschrottenWindow();
			}
			
		});
		
		JButton bilanz = new JButton("Bilanz");
		actionbuttons.add(bilanz);
		bilanz.setBounds(643, 55, 120, 30);
		bilanz.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new BilanzWindow();
			}
		});
		
		actionbuttons.add(neuerauftrag);
		actionbuttons.add(umlagern);
		actionbuttons.add(verschrotten);
		
		kontostand.setText("Kontostand: 0€");
		kontostand.setFont(new Font(kontostand.getFont().getName(), Font.PLAIN, 20));
		kontostand.setBounds(403, 50, 400, 40);
		actionbuttons.add(kontostand);
		fenster.add(actionbuttons);
		
		
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
			auftrag_produkt[i] = new JLabel();
			auftrag_kosten[i] = new JLabel();
			auftrag_zurueckstellen[i] = new JButton("\u274C");
			auftrag_zurueckstellen[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Start.lager.auftragZurueckstellen(temp);
					UpdateAuftragListe();
				}
			});
			auftrag_ablehnen[i] = new JButton("\uD83D\uDDD1\uFE0F");
			auftrag_ablehnen[i].setFont(new Font(kontostand.getFont().getName(), Font.PLAIN, 30));
			auftrag_ablehnen[i].addActionListener(new java.awt.event.ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Start.lager.auftragAblehnen(temp);
					UpdateAuftragListe();
				}
				
			});
			actionbuttons.add(auftrag_art[i]);
			auftrag_art[i].setBounds(403, 90+i*70, 240, 40);
			actionbuttons.add(auftrag_produkt[i]);
			auftrag_produkt[i].setBounds(403, 110+i*70, 240, 40);
			actionbuttons.add(auftrag_kosten[i]);
			auftrag_kosten[i].setBounds(403, 130+i*70, 240, 40);
			actionbuttons.add(auftrag_annehmen[i]);
			auftrag_annehmen[i].setBounds(573, 108+i*70, 60, 40);
			actionbuttons.add(auftrag_zurueckstellen[i]);
			auftrag_zurueckstellen[i].setBounds(633, 108+i*70, 60, 40);
			actionbuttons.add(auftrag_ablehnen[i]);
			auftrag_ablehnen[i].setBounds(703, 108+i*70, 60, 40);
			auftrag_ablehnen[i].setVisible(false);
			auftrag_annehmen[i].setVisible(false);
			auftrag_zurueckstellen[i].setVisible(false);
		}
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
		links.setBounds(5, 450, 124, 25);
		lagerraum.add(links);
		lagerauswahl_main.setBounds(180, 450, 124, 25);
		lagerraum.add(lagerauswahl_main);
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
		rechts.setBounds(273,450,124,25);
		lagerraum.add(rechts);
		ImageIcon icon = new ImageIcon("lagerregal2020.png");
		System.out.println(System.getProperty("user.dir"));
		JLabel logo = new JLabel();
		logo.setIcon(icon);
		actionbuttons.add(logo);
		logo.setBounds(450,450,350,100);
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
				auftrag_ablehnen[i].setVisible(true);
			}
			else
			{
				auftrag_art[i].setText("");
				auftrag_produkt[i].setText("");
				auftrag_kosten[i].setText("");
				auftrag_annehmen[i].setVisible(false);
				auftrag_zurueckstellen[i].setVisible(false);
				auftrag_ablehnen[i].setVisible(false);
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
