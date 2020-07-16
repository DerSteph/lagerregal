package display;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

import lagerregal.Start;

public class MainWindowNew extends JFrame {
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
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowNew frame = new MainWindowNew();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public MainWindowNew() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 330, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(143, Short.MAX_VALUE))
		);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 0.0};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 0;
		panel_3.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 0;
		panel_3.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 0;
		panel_3.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JButton btnNewButton_4 = new JButton("\u2714");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 3;
		gbc_btnNewButton_4.gridy = 0;
		panel_3.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton_9 = new JButton("\u274C");
		GridBagConstraints gbc_btnNewButton_9 = new GridBagConstraints();
		gbc_btnNewButton_9.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_9.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_9.gridx = 4;
		gbc_btnNewButton_9.gridy = 0;
		panel_3.add(btnNewButton_9, gbc_btnNewButton_9);
		
		JButton btnNewButton_12 = new JButton("\uD83D\uDDD1\uFE0F");
		GridBagConstraints gbc_btnNewButton_12 = new GridBagConstraints();
		gbc_btnNewButton_12.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_12.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_12.gridx = 5;
		gbc_btnNewButton_12.gridy = 0;
		panel_3.add(btnNewButton_12, gbc_btnNewButton_12);
		
		JLabel lblNewLabel_8 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 1;
		panel_3.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 1;
		panel_3.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 2;
		gbc_lblNewLabel_10.gridy = 1;
		panel_3.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		JButton btnNewButton_5 = new JButton("\u2714");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 3;
		gbc_btnNewButton_5.gridy = 1;
		panel_3.add(btnNewButton_5, gbc_btnNewButton_5);
		
		JButton btnNewButton_10 = new JButton("\u274C");
		GridBagConstraints gbc_btnNewButton_10 = new GridBagConstraints();
		gbc_btnNewButton_10.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_10.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_10.gridx = 4;
		gbc_btnNewButton_10.gridy = 1;
		panel_3.add(btnNewButton_10, gbc_btnNewButton_10);
		
		JButton btnNewButton_7 = new JButton("\uD83D\uDDD1\uFE0F");
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_7.gridx = 5;
		gbc_btnNewButton_7.gridy = 1;
		panel_3.add(btnNewButton_7, gbc_btnNewButton_7);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 2;
		panel_3.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 1;
		gbc_lblNewLabel_12.gridy = 2;
		panel_3.add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 2;
		gbc_lblNewLabel_13.gridy = 2;
		panel_3.add(lblNewLabel_13, gbc_lblNewLabel_13);
		
		JButton btnNewButton_6 = new JButton("\u2714");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 3;
		gbc_btnNewButton_6.gridy = 2;
		panel_3.add(btnNewButton_6, gbc_btnNewButton_6);
		
		JButton btnNewButton_11 = new JButton("\u274C");
		GridBagConstraints gbc_btnNewButton_11 = new GridBagConstraints();
		gbc_btnNewButton_11.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_11.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_11.gridx = 4;
		gbc_btnNewButton_11.gridy = 2;
		panel_3.add(btnNewButton_11, gbc_btnNewButton_11);
		
		JButton btnNewButton_8 = new JButton("\uD83D\uDDD1\uFE0F");
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_8.gridx = 5;
		gbc_btnNewButton_8.gridy = 2;
		panel_3.add(btnNewButton_8, gbc_btnNewButton_8);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_4 = new JLabel("Dein Kontostand: 0\u20AC");
		panel_2.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Neuer Auftrag");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Umlagern");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Verschrotten");
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Bilanz");
		panel_1.add(btnNewButton_3);
		
		/*JLabel grafik_lagerraum0 = new JLabel("New label");
		
		JLabel grafik_lagerraum1 = new JLabel("New label");
		
		JLabel grafik_lagerraum2 = new JLabel("New label");
		
		JLabel grafik_lagerraum3 = new JLabel("New label");
		
		JLabel grafik_lagerraum4 = new JLabel("New label");
		
		JLabel grafik_lagerraum5 = new JLabel("New label");
		
		JLabel grafik_lagerraum6 = new JLabel("New label");
		
		JLabel grafik_lagerraum7 = new JLabel("New label");
		
		JLabel grafik_lagerraum8 = new JLabel("New label");*/
		
		lagerraum_feld[0] = new JLabel("New label");
		lagerraum_feld[1] = new JLabel("New label");
		lagerraum_feld[2] = new JLabel("New label");
		lagerraum_feld[3] = new JLabel("New label");
		lagerraum_feld[4] = new JLabel("New label");
		lagerraum_feld[5] = new JLabel("New label");
		lagerraum_feld[6] = new JLabel("New label");
		lagerraum_feld[7] = new JLabel("New label");
		lagerraum_feld[8] = new JLabel("New label");
		
		for(int i = 0; i < 9; i++)
		{
			lagerraum_feld[i] = new JLabel();
			String text = Start.lager.getLagerplatzInhalt(getRightLagerplatz(i));
			if(text == null) {
				text = "leer";
				lagerraum_feld[i].setForeground(Color.gray);
			}
			lagerraum_feld[i].setText("<html><div style='text-align: center;'>" + text + "</div></html>");
			if(i % 2 == 0)
			{
				lagerraum_feld[i].setBackground(Color.white);
				lagerraum_feld[i].setOpaque(true);
			}
		}
		
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		/*panel.add(grafik_lagerraum0);
		panel.add(grafik_lagerraum1);
		panel.add(grafik_lagerraum2);
		panel.add(grafik_lagerraum3);
		panel.add(grafik_lagerraum4);
		panel.add(grafik_lagerraum5);
		panel.add(grafik_lagerraum6);
		panel.add(grafik_lagerraum7);
		panel.add(grafik_lagerraum8);*/
		for(int i = 0; i < 9; i++)
		{
			panel.add(lagerraum_feld[i]);
		}
		contentPane.setLayout(gl_contentPane);
		
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
}
