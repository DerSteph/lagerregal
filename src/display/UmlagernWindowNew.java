package display;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import lagerregal.Start;

public class UmlagernWindowNew extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String modus = "Lagerquelle";
	private int lagerquelle = 0;
	private JLabel lagertext;
	private int[] checkliste;
	private JButton[] label;

	public UmlagernWindowNew() {
		// Grafikelemente von WindowBuilder
		setTitle("Umlagern");
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
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		lagertext = new JLabel("New label");
		
		
		label = new JButton[27];
		// Suche nach freien Plätzen zum Umlagern
		checkliste = Start.lager.getPlaetzeZumUmlagernOderVerschrotten();
		
		int j = 0;
		for(int i = 0; i < 27; i++)
		{
			final int temp = i;
			label[i] = new JButton();
			String text = Start.lager.getLagerplatzInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(i));
			// Aktiviere / Deaktiviere Buttons je nach Zustand
			if(text == null) {
				label[i].setEnabled(false);
			}
			else if(checkliste[Start.window.getLagerplatzZuGrafiklagerplatz(i)] == 0)
			{
				label[i].setEnabled(false);
			}
			else
			{
				label[i].setEnabled(true);
			}
			label[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Unterschiedliche Zustände, je nach dem ob ein Objekt zum Auslagern ausgewählt wurde
					if(modus == "Lagerquelle")
					{
						modus = "Lagerziel";
						lagerquelle = Start.window.getLagerplatzZuGrafiklagerplatz(temp);
						checkliste = Start.lager.getArrayVonFreienPlaetzen(Start.window.getLagerplatzZuGrafiklagerplatz(temp));

						JButton button = (JButton)e.getSource();
						button.setEnabled(false);
						setTitle("Wähle das Ziel");
						lagertext.setText("Wähle nun das Ziel aus, wo das Produkt umgelagert werden soll.");
						UpdateLager();
					}
					else
					{
						int lagerziel = 0;
						lagerziel = Start.window.getLagerplatzZuGrafiklagerplatz(temp);
						if(Start.lager.Umlagern(lagerquelle, lagerziel))
						{
							Start.window.UpdateMainLagerraum();
							System.out.println(Start.window.getGrafikLagerplatzZuLagerplatz(lagerquelle) + ","+ Start.window.getGrafikLagerplatzZuLagerplatz(lagerziel));
							Start.window.letzteAktion.setText("Letzte Aktion: Umlagerung von " + Start.lager.getLagerplatzInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(temp)) + " von Lager " + Start.window.getLagerplatzFromInhalt(Start.window.getRightLagerplatz(lagerquelle)) + " zu Lager " + Start.window.getLagerplatzFromInhalt(Start.window.getRightLagerplatz(lagerziel)));
							Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Das Lagerziel ist bereits besetzt.");
						}
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
			ImageIcon icon2 = Start.window.bekommeBild(Start.lager.getInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(i)));
			Image test = Start.window.getSkaliertesBild(icon2.getImage(), 64, 64);
			label[i].setIcon(new ImageIcon(test));
		}
		
		lagertext.setText("<html><center>W\u00E4hle ein Produkt aus, was du umlagern m\u00F6chtest.<br><font color='red'>Umlagern kostet <b>100\u20AC</b></font></center></html>");
		lagertext.setHorizontalAlignment(JLabel.CENTER);
		
		panel_3.add(lagertext);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	
	// Um das Lager zu Updaten im Fenster
	private void UpdateLager() {
			for(int i = 0; i < 27; i++)
			{
				String text = Start.lager.getLagerplatzInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(i));
				if(text == null) {
					if (checkliste[Start.window.getLagerplatzZuGrafiklagerplatz(i)] == 0) {
						label[i].setEnabled(false);
					} else {
						label[i].setEnabled(true);
					}
				}
				else
				{
					label[i].setEnabled(false);
				}
			}	
	}

}
