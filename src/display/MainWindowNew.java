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
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

import lagerregal.Start;
import javax.swing.ImageIcon;

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
		setBounds(100, 100, 930, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JPanel panel_4 = new JPanel();
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(MainWindowNew.class.getResource("/display/lagerregal2020.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
										.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
									.addContainerGap())))
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(icon, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(618, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(icon, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(16))
		);
		
		JButton links = new JButton("<");
		links.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		panel_4.add(links);
		
		lagerauswahl_main = new JLabel("Lager 1");
		panel_4.add(lagerauswahl_main);
		
		JButton rechts = new JButton(">");
		rechts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		panel_4.add(rechts);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.rowHeights = new int[] {3};
		gbl_panel_3.columnWidths = new int[] {3};
		gbl_panel_3.columnWeights = new double[]{};
		gbl_panel_3.rowWeights = new double[]{};
		panel_3.setLayout(gbl_panel_3);
		
		/*for(int i = 0; i < 3; i++)
		{
			final int temp = i;
			auftrag_art[i] = new JLabel();
			auftrag_produkt[i] = new JLabel();
			auftrag_kosten[i] = new JLabel();
			auftrag_annehmen[i] = new JButton("\u2714");
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
			auftrag_ablehnen[i].setVisible(false);
			auftrag_annehmen[i].setVisible(false);
			auftrag_zurueckstellen[i].setVisible(false);
		}*/
		
		auftrag_art[0] = new JLabel("Att1");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 0;
		panel_3.add(auftrag_art[0], gbc_lblNewLabel_5);
		
		auftrag_produkt[0] = new JLabel("Att2");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 0;
		panel_3.add(auftrag_produkt[0], gbc_lblNewLabel_6);
		
		auftrag_kosten[0] = new JLabel("Att3");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 0;
		panel_3.add(auftrag_kosten[0], gbc_lblNewLabel_7);
		
		auftrag_annehmen[0] = new JButton("\u2714");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 3;
		gbc_btnNewButton_4.gridy = 0;
		auftrag_annehmen[0].addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof JButton)
				{
					String text = ((JButton) e.getSource()).getText();
					if(!text.equals(""))
					{
						if(Start.lager.getAuftrag(0).getLagerungsart().equals("Einlagerung"))
						{
							new AddStuffToLagerWindow(0);
						}
						else
						{
							new RemoveStuffFromLagerWindow(0);
						}
					}
				}
			}
		});
		panel_3.add(auftrag_annehmen[0], gbc_btnNewButton_4);
		
		auftrag_zurueckstellen[0] = new JButton("\u274C");
		GridBagConstraints gbc_btnNewButton_9 = new GridBagConstraints();
		gbc_btnNewButton_9.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_9.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_9.gridx = 4;
		gbc_btnNewButton_9.gridy = 0;
		auftrag_zurueckstellen[0].addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragZurueckstellen(0);
				UpdateAuftragListe();
			}
		});
		panel_3.add(auftrag_zurueckstellen[0], gbc_btnNewButton_9);
		
		auftrag_ablehnen[0] = new JButton("\uD83D\uDDD1\uFE0F");
		GridBagConstraints gbc_btnNewButton_12 = new GridBagConstraints();
		gbc_btnNewButton_12.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_12.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_12.gridx = 5;
		gbc_btnNewButton_12.gridy = 0;
		auftrag_ablehnen[0].addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragAblehnen(0);
				UpdateAuftragListe();
			}
			
		});
		panel_3.add(auftrag_ablehnen[0], gbc_btnNewButton_12);
		
		auftrag_art[1] = new JLabel("Att1_2");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 1;
		panel_3.add(auftrag_art[1], gbc_lblNewLabel_8);
		
		auftrag_produkt[1] = new JLabel("Att2_2");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 1;
		panel_3.add(auftrag_produkt[1], gbc_lblNewLabel_9);
		
		auftrag_kosten[1] = new JLabel("Att2_3");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 2;
		gbc_lblNewLabel_10.gridy = 1;
		panel_3.add(auftrag_kosten[1], gbc_lblNewLabel_10);
		
		auftrag_annehmen[1] = new JButton("\u2714");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 3;
		gbc_btnNewButton_5.gridy = 1;
		auftrag_annehmen[1].addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof JButton)
				{
					String text = ((JButton) e.getSource()).getText();
					if(!text.equals(""))
					{
						if(Start.lager.getAuftrag(1).getLagerungsart().equals("Einlagerung"))
						{
							new AddStuffToLagerWindow(1);
						}
						else
						{
							new RemoveStuffFromLagerWindow(1);
						}
					}
				}
			}
		});
		panel_3.add(auftrag_annehmen[1], gbc_btnNewButton_5);
		
		auftrag_zurueckstellen[1] = new JButton("\u274C");
		GridBagConstraints gbc_btnNewButton_10 = new GridBagConstraints();
		gbc_btnNewButton_10.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_10.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_10.gridx = 4;
		gbc_btnNewButton_10.gridy = 1;
		auftrag_zurueckstellen[1].addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragZurueckstellen(1);
				UpdateAuftragListe();
			}
		});
		panel_3.add(auftrag_zurueckstellen[1], gbc_btnNewButton_10);
		
		auftrag_ablehnen[1] = new JButton("\uD83D\uDDD1\uFE0F");
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_7.gridx = 5;
		gbc_btnNewButton_7.gridy = 1;
		auftrag_ablehnen[1].addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragAblehnen(1);
				UpdateAuftragListe();
			}
			
		});
		panel_3.add(auftrag_ablehnen[1], gbc_btnNewButton_7);
		
		auftrag_art[2] = new JLabel("Att1_3");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 2;
		panel_3.add(auftrag_art[2], gbc_lblNewLabel_11);
		
		auftrag_produkt[2] = new JLabel("Att2_3");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 1;
		gbc_lblNewLabel_12.gridy = 2;
		panel_3.add(auftrag_produkt[2], gbc_lblNewLabel_12);
		
		auftrag_kosten[2] = new JLabel("Att3_3");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 2;
		gbc_lblNewLabel_13.gridy = 2;
		panel_3.add(auftrag_kosten[2], gbc_lblNewLabel_13);
		
		auftrag_annehmen[2] = new JButton("\u2714");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 3;
		gbc_btnNewButton_6.gridy = 2;
		auftrag_annehmen[2].addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() instanceof JButton)
				{
					String text = ((JButton) e.getSource()).getText();
					if(!text.equals(""))
					{
						if(Start.lager.getAuftrag(2).getLagerungsart().equals("Einlagerung"))
						{
							new AddStuffToLagerWindow(2);
						}
						else
						{
							new RemoveStuffFromLagerWindow(2);
						}
					}
				}
			}
		});
		panel_3.add(auftrag_annehmen[2], gbc_btnNewButton_6);
		
		auftrag_zurueckstellen[2] = new JButton("\u274C");
		GridBagConstraints gbc_btnNewButton_11 = new GridBagConstraints();
		gbc_btnNewButton_11.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_11.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_11.gridx = 4;
		gbc_btnNewButton_11.gridy = 2;
		auftrag_zurueckstellen[2].addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragZurueckstellen(2);
				UpdateAuftragListe();
			}
		});
		panel_3.add(auftrag_zurueckstellen[2], gbc_btnNewButton_11);
		
		auftrag_ablehnen[2] = new JButton("\uD83D\uDDD1\uFE0F");
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_8.gridx = 5;
		gbc_btnNewButton_8.gridy = 2;
		auftrag_ablehnen[2].addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragAblehnen(2);
				UpdateAuftragListe();
			}
			
		});
		panel_3.add(auftrag_ablehnen[2], gbc_btnNewButton_8);
		for(int i = 0; i< 3; i++)
		{
			auftrag_art[i].setText("-");
			auftrag_produkt[i].setText("-");
			auftrag_kosten[i].setText("-");
			auftrag_art[i].setEnabled(false);
			auftrag_produkt[i].setEnabled(false);
			auftrag_kosten[i].setEnabled(false);
			auftrag_annehmen[i].setEnabled(false);
			auftrag_zurueckstellen[i].setEnabled(false);
			auftrag_ablehnen[i].setEnabled(false);
		}
		/*JLabel lblNewLabel_5 = new JLabel("Att1");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 0;
		panel_3.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Att2");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 0;
		panel_3.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Att3");
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
		
		JLabel lblNewLabel_8 = new JLabel("Att1_2");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 1;
		panel_3.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Att2_2");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 1;
		panel_3.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Att2_3");
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
		
		JLabel lblNewLabel_11 = new JLabel("Att1_3");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 2;
		panel_3.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Att2_3");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 1;
		gbc_lblNewLabel_12.gridy = 2;
		panel_3.add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Att3_3");
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
		panel_3.add(btnNewButton_8, gbc_btnNewButton_8);*/
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		kontostand = new JLabel("Dein Kontostand: 0\u20AC");
		panel_2.add(kontostand);
		
		JButton btnNewButton = new JButton("Neuer Auftrag");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragHinzufuegen();
				Start.lager.printAuftraege();
				UpdateAuftragListe();
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Umlagern");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UmlagernWindow();
			}
		});
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Verschrotten");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VerschrottenWindow();
			}
		});
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Bilanz");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BilanzWindow();
			}
		});
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
				auftrag_art[i].setEnabled(true);
				auftrag_produkt[i].setEnabled(true);
				auftrag_kosten[i].setEnabled(true);
				auftrag_annehmen[i].setEnabled(true);
				auftrag_zurueckstellen[i].setEnabled(true);
				auftrag_ablehnen[i].setEnabled(true);
			}
			else
			{
				auftrag_art[i].setText("");
				auftrag_produkt[i].setText("");
				auftrag_kosten[i].setText("");
				auftrag_art[i].setEnabled(false);
				auftrag_produkt[i].setEnabled(false);
				auftrag_kosten[i].setEnabled(false);
				auftrag_annehmen[i].setEnabled(false);
				auftrag_zurueckstellen[i].setEnabled(false);
				auftrag_ablehnen[i].setEnabled(false);
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
