package display;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.border.LineBorder;

import lagerregal.Holz;
import lagerregal.Start;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;

public class AddStuffToLagerWindowNew extends JFrame {

	private JPanel contentPane;
	private JLabel lagertext;
	JButton[] label;
	int checkliste[];
	private JLabel aufgabenbeschreibung;

	/**
	 * Create the frame.
	 */
	public AddStuffToLagerWindowNew(int num) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 444, 802);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel lagerraum1 = new JPanel();
		lagerraum1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lagerraum1.setBackground(SystemColor.inactiveCaptionBorder);
		
		JPanel lagerraum2 = new JPanel();
		lagerraum2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lagerraum2.setBackground(SystemColor.inactiveCaptionBorder);
		
		JPanel lagerraum3 = new JPanel();
		lagerraum3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lagerraum3.setBackground(SystemColor.inactiveCaptionBorder);
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lagerraum1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
						.addComponent(lagerraum2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
						.addComponent(lagerraum3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lagerraum1, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lagerraum2, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lagerraum3, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
					.addContainerGap())
		);
		lagerraum3.setLayout(new GridLayout(3,3));
		lagerraum2.setLayout(new GridLayout(3,3));
		lagerraum1.setLayout(new GridLayout(3,3));
		
		lagertext = new JLabel("New label");
		
		
		label = new JButton[27];

		checkliste = Start.lager.getArrayVonFreienPlaetzen(Start.lager.getAuftrag(num));
		int check = 0;
		for (int i = 0; i < 27; i++) {
			if (checkliste[i] == 1) {
				check++;
			}
		}
		int j = 0;
		for (int i = 0; i < 27; i++) {
			final int temp = i;
			label[i] = new JButton();
			String text = Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatzMain(i));
			if (text == null) {
				if (checkliste[Start.window.getRightLagerplatzMain(i)] == 0) {
					label[i].setEnabled(false);
				} else {
					label[i].setEnabled(true);
				}
				text = "leer";
			} else {
				label[i].setEnabled(false);
			}
			//label[i].setText(text);
			label[i].setMargin(new Insets(0,0,0,0));
			label[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int i = temp;
					/*if(lagerauswahl.getText().equals("Lager 2"))
					{
						i = temp + 9;
					}
					if(lagerauswahl.getText().equals("Lager 3"))
					{
						i = temp + 18;
					}*/
					if(Start.lager.AuftragAbarbeiten(num, Start.window.getRightLagerplatzMain(i)))
					{
						Start.window.UpdateMainLagerraum();
						//Start.window.GotoLager(i);
						Start.window.UpdateAuftragListe();
						if(Start.lager.getInhalt(Start.window.getRightLagerplatzMain(i)) instanceof Holz)
						{
							if(((Holz) Start.lager.getInhalt(Start.window.getRightLagerplatzMain(i))).getForm() == "Balken")
							{
								Start.window.letzteAktion.setText("Letzte Aktion: Einlagerung von " + Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatzMain(i)));								
							}
						}
						else
						{
							Start.window.letzteAktion.setText("Letzte Aktion: Einlagerung von " + Start.lager.getLagerplatzInhalt(Start.window.getRightLagerplatzMain(i)) + " in Lager " + Start.window.getLagerplatzFromInhalt(Start.window.getRightLagerplatz(i)));
						}
						//dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
						Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
						Start.window.button_umlagern.setEnabled(true);
						Start.window.button_verschrotten.setEnabled(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null,
								"Der Lagerplatz ist bereits durch andere Dinge davor blockiert.");
					}
				}
			});
			if(i < 9)
			{
				lagerraum1.add(label[i]);	
			}
			else if(i < 18)
			{
				lagerraum2.add(label[i]);
			}
			else
			{
				lagerraum3.add(label[i]);
			}
			if(j < 3)
			{
				label[i].setHorizontalAlignment(JLabel.RIGHT);
				j++;
			}
			else if(j < 6)
			{
				label[i].setHorizontalAlignment(JLabel.CENTER);
				j++;
			}
			else
			{
				label[i].setHorizontalAlignment(JLabel.LEFT);
				j++;
			}
			if(j == 9)
			{
				j = 0;
			}
			ImageIcon icon2 = Start.window.bekommeBild(Start.lager.getInhalt(Start.window.getRightLagerplatzMain(i)));
			Image test = Start.window.getScaledImage(icon2.getImage(), 64, 64);
			label[i].setIcon(new ImageIcon(test));
		}
		lagertext = new JLabel();
		if (check != 0) {
			lagertext.setText("<html><body><center>" + Start.lager.getAuftrag(num).getInhalt() + "<br>Es sind noch " + check
					+ " Plaetze frei.</center></body></html>");
			ImageIcon icon2 = Start.window.bekommeBild(Start.lager.getAuftrag(num));
			Image test = Start.window.getScaledImage(icon2.getImage(), 64, 64);
			lagertext.setIcon(new ImageIcon(test));
		} else {
			lagertext.setForeground(Color.RED);
			lagertext.setText(
					"<html><body><center>Es sind keine Plaetze mehr frei! <br>Du musst einige Produkte umlagern oder loeschen!</center></body></html>");
		}
		panel_3.setLayout(new GridLayout(1, 2));
		lagertext.setHorizontalAlignment(JLabel.CENTER);
		
		panel_3.add(lagertext);
		
		aufgabenbeschreibung = new JLabel("Aufgabenbeschreibung");
		panel_3.add(aufgabenbeschreibung);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
}
