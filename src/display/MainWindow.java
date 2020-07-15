package display;

import javax.swing.*;

import lagerregal.Start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;


public class MainWindow {
	public JPanel lagerraum = new JPanel();
	public JLabel[] lagerraum_feld = new JLabel[9];
	public JButton auftrag[] = new JButton[3];
	public JLabel lagerauswahl_main = new JLabel("Lager 1");
	public JLabel kontostand = new JLabel();
	public MainWindow() {
		JFrame fenster = new JFrame();
		fenster.setSize(600,600);
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
		buttons.setLayout(new java.awt.GridLayout(4,1));
		JButton neuerauftrag = new JButton("Neuer Auftrag");
		for(int k = 0; k < 3; k++)
		{
			auftrag[k] = new JButton();
			final int temp = k;
			auftrag[k].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() instanceof JButton)
					{
						String text = ((JButton) e.getSource()).getText();
						if(!text.equals(""))
						{
							FensterAuftragHinzufuegenAuswahl(temp);
						}
					}
				}
			});
		}
		neuerauftrag.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragHinzufuegen();
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
			}
		});
		auswahl.add(links);
		auswahl.add(lagerauswahl_main);
		auswahl.add(rechts);
		
		JPanel geld = new JPanel();
		geld.setLayout(new java.awt.GridLayout(2,1));
		
		kontostand.setText("Dein Kontostand: 0€");
		geld.add(kontostand);
		
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
	
	public void FensterAuftragHinzufuegenAuswahl(int num) {
		JFrame auftraghinzufuegen = new JFrame();
		auftraghinzufuegen.setSize(400,400);
		auftraghinzufuegen.setLocationRelativeTo(null);
		auftraghinzufuegen.setTitle("Wähle ein Lagerplatz aus!");
		
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
			}
			label[i].setText(text);
			label[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(lagerauswahl.getText().equals("Lager 1"))
					{
						if(Start.lager.AuftragAbarbeiten(num, getRightLagerplatz(temp)))
						{
							UpdateMainLagerraum();
							auftrag[num].setText("");
							auftraghinzufuegen.dispatchEvent(new WindowEvent(auftraghinzufuegen, WindowEvent.WINDOW_CLOSING));
							kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Der Lagerplatz ist bereits durch andere Dinge davor blockiert.");
						}
					}
					else if(lagerauswahl.getText().equals("Lager 2"))
					{
						if(Start.lager.AuftragAbarbeiten(num, getRightLagerplatz(temp+9)))
						{
							UpdateMainLagerraum();
							auftrag[num].setText("");
							auftraghinzufuegen.dispatchEvent(new WindowEvent(auftraghinzufuegen, WindowEvent.WINDOW_CLOSING));
							kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Der Lagerplatz ist bereits durch andere Dinge davor blockiert.");
						}
					}
					else if(lagerauswahl.getText().equals("Lager 2"))
					{
						if(Start.lager.AuftragAbarbeiten(num, getRightLagerplatz(temp+18)))
						{
							UpdateMainLagerraum();
							auftrag[num].setText("");
							auftraghinzufuegen.dispatchEvent(new WindowEvent(auftraghinzufuegen, WindowEvent.WINDOW_CLOSING));
							kontostand.setText("Dein Kontostand: " + Integer.toString(Start.bilanz.getGesamtkonto()) + "€");
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
						String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i+18));
						if(text == null) {
							text = Integer.toString(getRightLagerplatz(i+18));
							lagerraum_feld[i].setForeground(Color.gray);
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 3");
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i));
						if(text == null) {
							text = Integer.toString(getRightLagerplatz(i));
							lagerraum_feld[i].setForeground(Color.gray);
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 1");
				}
				else if(lagerauswahl.getText().equals("Lager 3"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i+9));
						if(text == null) {
							text = Integer.toString(getRightLagerplatz(i+9));
							lagerraum_feld[i].setForeground(Color.gray);
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
						String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i+9));
						if(text == null) {
							text = "leer";
							lagerraum_feld[i].setForeground(Color.gray);
						}
						label[i].setText(text);	
					}
					lagerauswahl.setText("Lager 2");
				}
				else if(lagerauswahl.getText().equals("Lager 2"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i+18));
						if(text == null) {
							text = "leer";
							lagerraum_feld[i].setForeground(Color.gray);
						}
						label[i].setText(text);
					}
					lagerauswahl.setText("Lager 3");
				}
				else if(lagerauswahl.getText().equals("Lager 3"))
				{
					for(int i = 0; i < 9; i++)
					{
						String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i));
						if(text == null) {
							text = "leer";
							lagerraum_feld[i].setForeground(Color.gray);
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
