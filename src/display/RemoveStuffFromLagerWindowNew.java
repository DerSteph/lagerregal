package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

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

public class RemoveStuffFromLagerWindowNew extends JFrame {

	private JPanel contentPane;
	private JLabel lagertext;
	JButton[] label;
	int checkliste[];

	/**
	 * Create the frame.
	 */
	public RemoveStuffFromLagerWindowNew(int num) {
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

		int j = 0;
		int verfuegbar = 0;
		int blockiert = 0;
		for (int i = 0; i < 27; i++) {
			final int temp = i;
			label[i] = new JButton();
			String text = Start.lager.getLagerplatzInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(i));
			Boolean check = Start.lager.checkObProduktDortGelagert(Start.window.getLagerplatzZuGrafiklagerplatz(i), Start.lager.getAuftrag(num));
			Boolean check2 = false;
			label[i].setBackground(new JButton().getBackground());
			if(check)
			{
				check2 = Start.lager.checkObProduktNichtBlockiert(Start.window.getLagerplatzZuGrafiklagerplatz(i));	
			}
			if(text == null) {
				label[i].setEnabled(false);
			}
			else if(check && check2)
			{
				label[i].setBackground(Color.green);
				label[i].setEnabled(true);
				verfuegbar++;
			}
			else if(check && !check2)
			{
				label[i].setBackground(Color.yellow);
				label[i].setEnabled(true);
				blockiert++;
			}
			else
			{
				label[i].setEnabled(false);
			}
			label[i].addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String text = Start.lager.getLagerplatzInhalt(Start.window.getLagerplatzZuGrafiklagerplatz(temp));
					if(Start.lager.auftragAbarbeiten(num, Start.window.getLagerplatzZuGrafiklagerplatz(temp)))
					{
						Start.window.UpdateMainLagerraum();
						Start.window.UpdateAuftragListe();
						Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
						Start.window.letzteAktion.setText("Letzte Aktion: Auslagern von " + text + " in Lager " + Start.window.getLagerplatzFromInhalt(temp));
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "<html><body><center>Der Lagerplatz ist bereits durch andere Produkte davor blockiert!<br> Versuche mit der Funktion <font color='red'>Umlagern</font> die davorstehenden Produkte umzulagern<br>oder mit der Funktion <font color='red'>Verschrotten</font> die davorstehenden Produkte zu verschrotten.</center></body></html>");
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
		
		if(verfuegbar == 0 && blockiert == 0)
		{
			lagertext.setText("<html><center>" + Start.lager.getAuftrag(num).getInhalt() + "<br><font color='red'>Das Produkt ist nicht im Lager!<br>Notfalls kannst du den Auftrag<br>auch zurueckstellen oder ablehnen.</font></center></html>");	
		}
		else
		{
			if(verfuegbar != 0)
			{
				lagertext.setText("<html><center>" + Start.lager.getAuftrag(num).getInhalt() + "<br>Verfuegbar: " + verfuegbar + " mal<br>" + "Blockiert: " + blockiert + " mal</center></html>");	
			}
			else
			{
				lagertext.setText("<html><center>" + Start.lager.getAuftrag(num).getInhalt() + "<br><font color='red'>Alle " + blockiert + " Produkte werden blockiert. <br>Durch Umlagern anderer Produkte<br>kannst du sie freischaufeln.</font></center></html>");
			}	
		}
		ImageIcon icon2 = Start.window.bekommeBild(Start.lager.getAuftrag(num));
		Image test = Start.window.getSkaliertesBild(icon2.getImage(), 64, 64);
		lagertext.setIcon(new ImageIcon(test));
		lagertext.setHorizontalAlignment(JLabel.CENTER);
		
		panel_3.add(lagertext);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}

}
