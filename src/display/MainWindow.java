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
		fenster.setSize(800, 600);
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
		JPanel actionbuttons = new JPanel();
		actionbuttons.setLayout(new java.awt.GridLayout(1,3));

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
							new AddStuffToLagerWindow(temp);
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
		
		JButton umlagern = new JButton("Umlagern");
		umlagern.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UmlagernWindow();
			}
		});
		JButton verschrotten = new JButton("Verschrotten");
		actionbuttons.add(neuerauftrag);
		actionbuttons.add(umlagern);
		actionbuttons.add(verschrotten);
		buttons.add(actionbuttons);
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
