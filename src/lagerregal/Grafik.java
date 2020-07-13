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
			String text = Start.lager.getLagerplatzInhalt(i+18);
			if(text == null) {
				text = "leer";
			}
			label[i].setText(text);
			lagerraum.add(label[i]);
		}
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new java.awt.GridLayout(4,1));
		JButton neuerauftrag = new JButton("Neuer Auftrag");
		JButton auftrag[] = new JButton[3];
		int k = 0;
		for(k = 0; k < 3; k++)
		{
			auftrag[k] = new JButton();
			auftrag[k].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(auftrag[k].getText().equals(""))
					{
						FensterAuftragHinzufügenAuswahl();	
					}
				}
			});
		}
		neuerauftrag.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Test.test.leseZeile();
				Start.lager.auftragHinzufügen();
				for(int i = 0; i < 3; i++)
				{
					if(Start.lager.getAuftrag(i) != null)
					{
						auftrag[i].setText(Start.lager.getAuftrag(i).getClass().getSimpleName() + ": " + Start.lager.getAuftrag(i).getKosten());	
					}
				}
			}
		});
		buttons.add(neuerauftrag);
		for(int i = 0; i < 3; i++)
		{
			buttons.add(auftrag[i]);
		}
		JPanel auswahl = new JPanel();
		auswahl.setLayout(new java.awt.FlowLayout());
		JLabel lagerauswahl = new JLabel("Lager 1");
		JButton links = new JButton("<");
		links.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(lagerauswahl.getText().equals("Lager 1"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(i+18);
						if(text == null) {
							text = "leer";
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 3");
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(i);
						if(text == null) {
							text = "leer";
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 1");
				}
				else if(lagerauswahl.getText().equals("Lager 3"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(i+9);
						if(text == null) {
							text = "leer";
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
						String text = Start.lager.getLagerplatzInhalt(i+9);
						if(text == null) {
							text = "leer";
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 2");
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(i + 18);
						if(text == null) {
							text = "leer";
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 3");
				}
				else if(lagerauswahl.getText().equals("Lager 3"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(i);
						if(text == null) {
							text = "leer";
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
	public void FensterAuftragHinzufügenAuswahl() {
		JFrame auftraghinzufügen = new JFrame();
		auftraghinzufügen.setSize(400,400);
		JPanel lagerraum = new JPanel();
		lagerraum.setLayout(new java.awt.GridLayout(3,3));
		JButton[] label = new JButton[9];
		for(int i = 0; i < 9; i++)
		{
			label[i] = new JButton();
			String text = Start.lager.getLagerplatzInhalt(i);
			if(text == null) {
				text = "leer";
			}
			label[i].setText(text);
			lagerraum.add(label[i]);
		}
		
		
		JPanel auswahl = new JPanel();
		auswahl.setLayout(new java.awt.FlowLayout());
		JLabel lagerauswahl = new JLabel("Lager 1");
		JButton links = new JButton("<");
		links.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(lagerauswahl.getText().equals("Lager 1"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(i+18);
						if(text == null) {
							text = "leer";
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 3");
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(i);
						if(text == null) {
							text = "leer";
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 1");
				}
				else if(lagerauswahl.getText().equals("Lager 3"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(i+9);
						if(text == null) {
							text = "leer";
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
						String text = Start.lager.getLagerplatzInhalt(i+9);
						if(text == null) {
							text = "leer";
						}
						label[i].setText(text);	
					}
					lagerauswahl.setText("Lager 2");
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(i + 18);
						if(text == null) {
							text = "leer";
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 3");
				}
				else if(lagerauswahl.getText().equals("Lager 3"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(i);
						if(text == null) {
							text = "leer";
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
		
		JPanel anzeige = new JPanel();
		anzeige.setLayout(new java.awt.GridLayout(2,1));
		anzeige.add(lagerraum);
		anzeige.add(auswahl);
		auftraghinzufügen.getContentPane().add(anzeige);
		auftraghinzufügen.setVisible(true);
	}
}
