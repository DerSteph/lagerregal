package display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;

import lagerregal.Holz;
import lagerregal.Start;
import lagerregal.Stein;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AddStuffToLagerWindowNew extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lagertext;
	JButton[] label;
	int checkliste[];

	/**
	 * Create the frame.
	 */
	public AddStuffToLagerWindowNew(int num) {
		// Grafikelemente durch WindowBuilder
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
		
		// Prüft, welche Plätze verfügbar sind
		checkliste = Start.lager.getArrayVonFreienPlaetzen(Start.lager.getAuftrag(num));
		int check = 0;
		for (int i = 0; i < 27; i++) {
			if (checkliste[i] == 1) {
				check++;
			}
		}
		int j = 0;
		// Erstellt für jedes Lager einen einzelnen Button
		for (int i = 0; i < 27; i++) {
			final int temp = i;
			label[i] = new JButton();
			String text = Start.lager.getLagerplatzInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(i));
			if (text == null) {
				if (checkliste[Start.window.getLagerplatzZuGrafiklagerplatz(i)] == 0) {
					label[i].setEnabled(false);
				} else {
					label[i].setEnabled(true);
				}
				text = "leer";
			} else {
				label[i].setEnabled(false);
			}
			label[i].setMargin(new Insets(0,0,0,0));
			label[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int i = temp;
					if(Start.lager.auftragAbarbeiten(num, Start.window.getLagerplatzZuGrafiklagerplatz(i)))
					{
						Start.window.UpdateMainLagerraum();
						Start.window.UpdateAuftragListe();
						if(Start.lager.getInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(i)) instanceof Holz)
						{
							if(((Holz) Start.lager.getInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(i))).getForm() == "Balken")
							{
								Start.window.letzteAktion.setText("Letzte Aktion: Einlagerung von " + Start.lager.getLagerplatzInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(i)));								
							}
						}
						else
						{
							Start.window.letzteAktion.setText("Letzte Aktion: Einlagerung von " + Start.lager.getLagerplatzInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(i)) + " in Lager " + Start.window.getLagerplatzFromInhalt(Start.window.getRightLagerplatz(i)));
						}
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
			ImageIcon icon = Start.window.bekommeBild(Start.lager.getInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(i)));
			Image image = Start.window.getSkaliertesBild(icon.getImage(), 64, 64);
			label[i].setIcon(new ImageIcon(image));
		}
		// Text für Beschreibung des Produktes
		lagertext = new JLabel();
		if (check != 0) {
			lagertext.setText("<html><center>" + Start.lager.getAuftrag(num).getInhalt() + "<br>Es sind noch " + check
					+ " Plaetze frei.");
			if(Start.lager.getAuftrag(num) instanceof Stein)
			{
				if(((Stein) Start.lager.getAuftrag(num)).getGewicht().equals("Schwer"))
				{
					lagertext.setText(lagertext.getText() + "<br><font color='#00BFFF'>Schwere Steine können nur<br> unten gelagert werden!</font>");
				}
			}
			if(Start.lager.getAuftrag(num) instanceof Holz)
			{
				if(((Holz) Start.lager.getAuftrag(num)).getForm().equals("Balken"))
				{
					lagertext.setText(lagertext.getText() + "<br><font color='#00BFFF'>Balken benoetigen 3 Lagerplaetze<br> nach hinten!</font>");
				}
			}
			lagertext.setText(lagertext.getText() + "</center></html>");
			ImageIcon icon2 = Start.window.bekommeBild(Start.lager.getAuftrag(num));
			Image test = Start.window.getSkaliertesBild(icon2.getImage(), 64, 64);
			lagertext.setIcon(new ImageIcon(test));
		} else {
			lagertext.setForeground(Color.RED);
			lagertext.setText(
					"<html><body><center>Es sind keine Plaetze mehr frei! <br>Du musst einige Produkte umlagern oder loeschen!</center></body></html>");
		}
		panel_3.setLayout(new GridLayout(1, 1));
		lagertext.setHorizontalAlignment(JLabel.CENTER);
		
		panel_3.add(lagertext);
		
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
}
