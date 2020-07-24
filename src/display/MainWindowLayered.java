package display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

import lagerregal.Holz;
import lagerregal.Papier;
import lagerregal.Produkt;
import lagerregal.Start;
import lagerregal.Stein;

import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class MainWindowLayered extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel anzeige = new JPanel();
	public JLabel[] lagerraum_feld = new JLabel[27];
	
	public JLabel auftrag_art[] = new JLabel[3];
	public JButton auftrag_annehmen[] = new JButton[3];
	public JButton auftrag_zurueckstellen[] = new JButton[3];
	public JLabel auftrag_produkt[] = new JLabel[3];
	public JLabel auftrag_kosten[] = new JLabel[3];
	public JButton auftrag_ablehnen[] = new JButton[3];
	
	public JLabel lagerauswahl_main = new JLabel("Lager 1");
	public JLabel kontostand = new JLabel();
	
	private JPanel contentPane;
	private JLabel header_auftrag;
	JButton button_neuerauftrag;
	JButton button_umlagern;
	JButton button_verschrotten;
	public JLabel letzteAktion;

	/**
	 * Create the frame.
	 */
	public MainWindowLayered() {
		// Grafikelemente durch WindowBuilder
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindowLayered.class.getResource("/bilder/Stein_marmor_leicht.png")));
		setBackground(Color.WHITE);
		setTitle("LagerRegal 2020");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel(new GridLayout());
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(MainWindowLayered.class.getResource("/display/lagerregal2020.png")));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(160, 160, 160));
		separator.setBackground(new Color(255, 255, 255));
		
		//letzteAktion = new JLabel("");
		
		JPanel panel_4 = new JPanel(new GridLayout());
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBackground(Color.WHITE);
		
		JPanel panel_5 = new JPanel(new GridLayout());
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBackground(Color.WHITE);
		
		JPanel panel_6 = new JPanel();
		
		JPanel panel_7 = new JPanel();
		
		letzteAktion = new JLabel("Letzte Aktion: Programm gestartet");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(letzteAktion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(panel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(panel_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(panel_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(icon, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(letzteAktion)
						.addComponent(icon, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(new Color(25, 25, 112));
		panel_7.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBackground(new Color(25, 25, 112));
		panel_7.add(lblNewLabel_5);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(new Color(25, 25, 112));
		panel_7.add(lblNewLabel_3);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(25, 25, 112));
		panel_6.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBackground(new Color(25, 25, 112));
		lblNewLabel_2.setOpaque(true);
		panel_6.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(25, 25, 112));
		lblNewLabel.setOpaque(true);
		panel_6.add(lblNewLabel);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.rowHeights = new int[] {3};
		gbl_panel_3.columnWidths = new int[] {3};
		gbl_panel_3.columnWeights = new double[]{};
		gbl_panel_3.rowWeights = new double[]{};
		panel_3.setLayout(gbl_panel_3);
		panel_3.setBackground(Color.white);
		
		// Auftragsliste
		header_auftrag = new JLabel("Einlagern / Auslagern");
		header_auftrag.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_header_auftrag = new GridBagConstraints();
		gbc_header_auftrag.insets = new Insets(0, 0, 5, 5);
		gbc_header_auftrag.gridx = 0;
		gbc_header_auftrag.gridy = 0;
		panel_3.add(header_auftrag, gbc_header_auftrag);
		
		JLabel header_produkt = new JLabel("Produkt");
		header_produkt.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_header_produkt = new GridBagConstraints();
		gbc_header_produkt.insets = new Insets(0, 0, 5, 5);
		gbc_header_produkt.gridx = 1;
		gbc_header_produkt.gridy = 0;
		panel_3.add(header_produkt, gbc_header_produkt);
		
		JLabel header_kosten= new JLabel("Verdienst");
		header_kosten.setFont(new Font("Tahoma", Font.BOLD, 11));
		header_kosten.setBackground(SystemColor.window);
		GridBagConstraints gbc_header_kosten = new GridBagConstraints();
		gbc_header_kosten.insets = new Insets(0, 0, 5, 5);
		gbc_header_kosten.gridx = 2;
		gbc_header_kosten.gridy = 0;
		panel_3.add(header_kosten, gbc_header_kosten);
		
		JLabel header_annehmen = new JLabel("Annehmen?");
		header_annehmen.setFont(new Font("Tahoma", Font.BOLD, 11));
		header_annehmen.setBackground(SystemColor.window);
		GridBagConstraints gbc_header_annehmen = new GridBagConstraints();
		gbc_header_annehmen.insets = new Insets(0, 0, 5, 5);
		gbc_header_annehmen.gridx = 3;
		gbc_header_annehmen.gridy = 0;
		panel_3.add(header_annehmen, gbc_header_annehmen);
		
		JLabel header_zurueckstellen = new JLabel("Zurueckstellen?");
		header_zurueckstellen.setFont(new Font("Tahoma", Font.BOLD, 11));
		header_zurueckstellen.setBackground(SystemColor.window);
		GridBagConstraints gbc_header_zurueckstellen = new GridBagConstraints();
		gbc_header_zurueckstellen.insets = new Insets(0, 0, 5, 5);
		gbc_header_zurueckstellen.gridx = 4;
		gbc_header_zurueckstellen.gridy = 0;
		panel_3.add(header_zurueckstellen, gbc_header_zurueckstellen);
		
		JLabel header_ablehnen = new JLabel("Loeschen?");
		header_ablehnen.setFont(new Font("Tahoma", Font.BOLD, 11));
		header_ablehnen.setBackground(SystemColor.window);
		GridBagConstraints gbc_header_ablehnen = new GridBagConstraints();
		gbc_header_ablehnen.insets = new Insets(0, 0, 5, 0);
		gbc_header_ablehnen.gridx = 5;
		gbc_header_ablehnen.gridy = 0;
		panel_3.add(header_ablehnen, gbc_header_ablehnen);
		
		auftrag_art[0] = new JLabel("Att1");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 1;
		panel_3.add(auftrag_art[0], gbc_lblNewLabel_5);
		
		auftrag_produkt[0] = new JLabel("Att2");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 1;
		panel_3.add(auftrag_produkt[0], gbc_lblNewLabel_6);
		
		auftrag_kosten[0] = new JLabel("Att3");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 1;
		panel_3.add(auftrag_kosten[0], gbc_lblNewLabel_7);
		
		auftrag_annehmen[0] = new JButton("\u2714");
		auftrag_annehmen[0].setFont(new Font(auftrag_annehmen[0].getFont().getName(), Font.BOLD, 20));
		auftrag_annehmen[0].setPreferredSize(new Dimension(80, 50));
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 3;
		gbc_btnNewButton_4.gridy = 1;
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
							new AddStuffToLagerWindowNew(0);
						}
						else
						{
							new RemoveStuffFromLagerWindowNew(0);
						}
					}
				}
			}
		});
		panel_3.add(auftrag_annehmen[0], gbc_btnNewButton_4);
		
		auftrag_zurueckstellen[0] = new JButton("\u274C");
		auftrag_zurueckstellen[0].setFont(new Font(auftrag_zurueckstellen[0].getFont().getName(), Font.BOLD, 20));
		auftrag_zurueckstellen[0].setPreferredSize(new Dimension(55, 50));
		GridBagConstraints gbc_btnNewButton_9 = new GridBagConstraints();
		gbc_btnNewButton_9.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_9.gridx = 4;
		gbc_btnNewButton_9.gridy = 1;
		auftrag_zurueckstellen[0].addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragZurueckstellen(0);
				UpdateAuftragListe();
				letzteAktion.setText("Letzte Aktion: Zurueckstellen von Auftrag 0");
			}
		});
		panel_3.add(auftrag_zurueckstellen[0], gbc_btnNewButton_9);
		
		auftrag_ablehnen[0] = new JButton("\uD83D\uDDD1\uFE0F");
		auftrag_ablehnen[0].setFont(new Font(auftrag_ablehnen[0].getFont().getName(), Font.BOLD, 20));
		auftrag_ablehnen[0].setPreferredSize(new Dimension(55, 50));
		GridBagConstraints gbc_btnNewButton_12 = new GridBagConstraints();
		gbc_btnNewButton_12.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_12.gridx = 5;
		gbc_btnNewButton_12.gridy = 1;
		auftrag_ablehnen[0].addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragAblehnen(0);
				Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
				UpdateAuftragListe();
				letzteAktion.setText("Letzte Aktion: Ablehnen von Auftrag 0");
			}
			
		});
		panel_3.add(auftrag_ablehnen[0], gbc_btnNewButton_12);
		
		auftrag_art[1] = new JLabel("Att1_2");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 2;
		gbc_lblNewLabel_8.weighty = 1;
		panel_3.add(auftrag_art[1], gbc_lblNewLabel_8);
		
		auftrag_produkt[1] = new JLabel("Att2_2");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 2;
		panel_3.add(auftrag_produkt[1], gbc_lblNewLabel_9);
		
		auftrag_kosten[1] = new JLabel("Att2_3");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 2;
		gbc_lblNewLabel_10.gridy = 2;
		panel_3.add(auftrag_kosten[1], gbc_lblNewLabel_10);
		
		auftrag_annehmen[1] = new JButton("\u2714");
		auftrag_annehmen[1].setFont(new Font(auftrag_annehmen[1].getFont().getName(), Font.BOLD, 20));
		auftrag_annehmen[1].setPreferredSize(new Dimension(80, 50));
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 3;
		gbc_btnNewButton_5.gridy = 2;
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
							new AddStuffToLagerWindowNew(1);
						}
						else
						{
							new RemoveStuffFromLagerWindowNew(1);
						}
					}
				}
			}
		});
		panel_3.add(auftrag_annehmen[1], gbc_btnNewButton_5);
		
		auftrag_zurueckstellen[1] = new JButton("\u274C");
		auftrag_zurueckstellen[1].setFont(new Font(auftrag_zurueckstellen[1].getFont().getName(), Font.BOLD, 20));
		auftrag_zurueckstellen[1].setPreferredSize(new Dimension(55, 50));
		GridBagConstraints gbc_btnNewButton_10 = new GridBagConstraints();
		gbc_btnNewButton_10.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_10.gridx = 4;
		gbc_btnNewButton_10.gridy = 2;
		auftrag_zurueckstellen[1].addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragZurueckstellen(1);
				UpdateAuftragListe();
				letzteAktion.setText("Letzte Aktion: Zurueckstellen von Auftrag 1");
			}
		});
		panel_3.add(auftrag_zurueckstellen[1], gbc_btnNewButton_10);
		
		auftrag_ablehnen[1] = new JButton("\uD83D\uDDD1\uFE0F");
		auftrag_ablehnen[1].setFont(new Font(auftrag_ablehnen[1].getFont().getName(), Font.BOLD, 20));
		auftrag_ablehnen[1].setPreferredSize(new Dimension(55, 50));
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_7.gridx = 5;
		gbc_btnNewButton_7.gridy = 2;
		auftrag_ablehnen[1].addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragAblehnen(1);
				Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
				UpdateAuftragListe();
				letzteAktion.setText("Letzte Aktion: Ablehnen von Auftrag 2");
			}
			
		});
		panel_3.add(auftrag_ablehnen[1], gbc_btnNewButton_7);
		
		auftrag_art[2] = new JLabel("Att1_3");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 3;
		panel_3.add(auftrag_art[2], gbc_lblNewLabel_11);
		
		auftrag_produkt[2] = new JLabel("Att2_3");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 1;
		gbc_lblNewLabel_12.gridy = 3;
		panel_3.add(auftrag_produkt[2], gbc_lblNewLabel_12);
		
		auftrag_kosten[2] = new JLabel("Att3_3");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 2;
		gbc_lblNewLabel_13.gridy = 3;
		panel_3.add(auftrag_kosten[2], gbc_lblNewLabel_13);
		
		auftrag_annehmen[2] = new JButton("\u2714");
		auftrag_annehmen[2].setFont(new Font(auftrag_annehmen[2].getFont().getName(), Font.BOLD, 20));
		auftrag_annehmen[2].setPreferredSize(new Dimension(80, 50));
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_6.gridx = 3;
		gbc_btnNewButton_6.gridy = 3;
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
							new AddStuffToLagerWindowNew(2);
						}
						else
						{
							new RemoveStuffFromLagerWindowNew(2);
						}
					}
				}
			}
		});
		panel_3.add(auftrag_annehmen[2], gbc_btnNewButton_6);
		
		auftrag_zurueckstellen[2] = new JButton("\u274C");
		auftrag_zurueckstellen[2].setFont(new Font(auftrag_zurueckstellen[2].getFont().getName(), Font.BOLD, 20));
		auftrag_zurueckstellen[2].setPreferredSize(new Dimension(55, 50));
		GridBagConstraints gbc_btnNewButton_11 = new GridBagConstraints();
		gbc_btnNewButton_11.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_11.gridx = 4;
		gbc_btnNewButton_11.gridy = 3;
		auftrag_zurueckstellen[2].addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragZurueckstellen(2);
				UpdateAuftragListe();
				letzteAktion.setText("Letzte Aktion: Zurueckstellen von Auftrag 2");
			}
		});
		panel_3.add(auftrag_zurueckstellen[2], gbc_btnNewButton_11);
		
		auftrag_ablehnen[2] = new JButton("\uD83D\uDDD1\uFE0F");
		auftrag_ablehnen[2].setFont(new Font(auftrag_ablehnen[2].getFont().getName(), Font.BOLD, 20));
		auftrag_ablehnen[2].setPreferredSize(new Dimension(55, 50));
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_8.gridx = 5;
		gbc_btnNewButton_8.gridy = 3;
		auftrag_ablehnen[2].addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragAblehnen(2);
				UpdateAuftragListe();
				Start.window.kontostand.setText("Dein Kontostand: " + Start.bilanz.getGesamtkonto() + "€");
				letzteAktion.setText("Letzte Aktion: Ablehnen von Auftrag 2");
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

		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		kontostand = new JLabel("Dein Kontostand: 0\u20AC");
		kontostand.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_2.add(kontostand);
		
		button_neuerauftrag = new JButton("Neuer Auftrag");
		button_neuerauftrag.setPreferredSize(new Dimension(120, 40));
		button_neuerauftrag.setBackground(Color.GREEN);
		button_neuerauftrag.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_neuerauftrag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start.lager.auftragHinzufuegen();
				Start.lager.consolePrintAuftraege();
				UpdateAuftragListe();
				letzteAktion.setText("Letzte Aktion: Auftrag zur Auftragsliste hinzugefügt");
			}
		});
		panel_1.add(button_neuerauftrag);
		
		// Erstellen der Buttons oben rechts
		button_umlagern = new JButton("Umlagern");
		button_umlagern.setForeground(Color.WHITE);
		button_umlagern.setPreferredSize(new Dimension(110, 40));
		button_umlagern.setBackground(new Color(0, 139, 139));
		button_umlagern.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_umlagern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UmlagernWindowNew();
			}
		});
		panel_1.add(button_umlagern);
		
		button_verschrotten = new JButton("Verschrotten");
		button_verschrotten.setForeground(Color.WHITE);
		button_verschrotten.setPreferredSize(new Dimension(110, 40));
		button_verschrotten.setBackground(new Color(0, 139, 139));
		button_verschrotten.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_verschrotten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VerschrottenWindowNew();
			}
		});
		panel_1.add(button_verschrotten);
		
		JButton btnNewButton_3 = new JButton("Bilanz");
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setPreferredSize(new Dimension(70, 40));
		btnNewButton_3.setBackground(new Color(100, 149, 237));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BilanzWindow();
			}
		});
		panel_1.add(btnNewButton_3);
	
		for(int i = 0; i < 27; i++)
		{
			lagerraum_feld[i] = new JLabel();
		}
		UpdateMainLagerraum();


		JLayeredPane[] ueberpanel = new JLayeredPane[3];
		ueberpanel[0] = new JLayeredPane();
		ueberpanel[1] = new JLayeredPane();
		ueberpanel[2] = new JLayeredPane();
		panel.add(ueberpanel[0]);
		panel_4.add(ueberpanel[1]);
		panel_5.add(ueberpanel[2]);
		
		
		// Code um die Labels auf der Lagerregal grafik anzuzeigen, damit sie auch überlappen
		int k = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(j < 3)
				{
					lagerraum_feld[(i*9)+j].setBounds(140*((i*9)+j-k*3)+10, 10, 138, 64);
					ueberpanel[i].add(lagerraum_feld[(i*9)+j], Integer.valueOf(1));
					if(j == 2)
					{
						k++;
					}
				}
				else if(j < 6)
				{
					lagerraum_feld[(i*9)+j].setBounds(140*((i*9)+j-k*3)+10, 40, 138, 64);
					ueberpanel[i].add(lagerraum_feld[(i*9)+j], Integer.valueOf(2));
					if(j == 5)
					{
						k++;
					}
				}
				else
				{
					lagerraum_feld[(i*9)+j].setBounds(140*((i*9)+j-k*3)+10, 70, 138, 64);
					ueberpanel[i].add(lagerraum_feld[(i*9)+j], Integer.valueOf(3));
					if(j == 8)
					{
						k++;
					}
				}
			}
		}
		
		button_umlagern.setEnabled(false);
		button_verschrotten.setEnabled(false);
		
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	
	/*
	 * Die folgenden 3 Funktionen sind dafür zuständig, um den richtigen Platz auszuwählen, da die Lagerordnung im Lager eine andere ist
	 * als wie sie in der Grafik dargestellt wird. 
	 * 
	 */
	// Bekomme Lagerplatznummer für Lagerregale, die hintereinenader stehen
	public int getRightLagerplatz(int number) {
		int rightNumber[] = new int[]{24,25,26,15,16,17,6,7,8,21,22,23,12,13,14,3,4,5,18,19,20,9,10,11,0,1,2};
		return rightNumber[number];
	}
	
	// Bekomme Lagerplatznummer für Produkte, die in der Grafik dargestellt sind
	public int getLagerplatzZuGrafiklagerplatz(int number) {
		int rightNumber[] = new int[]{18,19,20,21,22,23,24,25,26,9,10,11,12,13,14,15,16,17,0,1,2,3,4,5,6,7,8};
		return rightNumber[number];
	}
	
	// Speziell für Umlagern
	public int getGrafikLagerplatzZuLagerplatz(int number) {
		int rightNumber[] = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};
		return rightNumber[number];
	}
	
	// Updatet die Anzeige der Auftragsliste
	public void UpdateAuftragListe() {
		for(int i = 0; i < 3; i++)
		{
			if(Start.lager.getAuftrag(i) != null)
			{
				auftrag_art[i].setText(Start.lager.getAuftrag(i).getLagerungsart());
				auftrag_produkt[i].setText("");
				auftrag_kosten[i].setText(Integer.toString(Start.lager.getAuftrag(i).getKosten()) + "€");
				auftrag_art[i].setEnabled(true);
				auftrag_produkt[i].setEnabled(true);
				auftrag_kosten[i].setEnabled(true);
				auftrag_annehmen[i].setEnabled(true);
				auftrag_zurueckstellen[i].setEnabled(true);
				auftrag_ablehnen[i].setEnabled(true);
				ImageIcon icon = bekommeBild(Start.lager.getAuftrag(i));
				Image test = getSkaliertesBild(icon.getImage(), 64, 64);
				auftrag_produkt[i].setIcon(new ImageIcon(test));
				auftrag_produkt[i].setToolTipText(Start.lager.getAuftrag(i).getInhalt());
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
				auftrag_produkt[i].setIcon(null);
				auftrag_produkt[i].setToolTipText(null);
			}
		}
		if(Start.lager.isAuftragListeVoll())
		{
			button_neuerauftrag.setEnabled(false);
		}
		else
		{
			button_neuerauftrag.setEnabled(true);
		}
	}
	
	// Funktion um die Bilder für die Produkte zuzuordnen
	public ImageIcon bekommeBild(Produkt name) {
		if(name instanceof Papier)
		{
			if(((Papier) name).getFarbe().equals("Weiß") && ((Papier) name).getGroesse().equals("A3"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Papier_weiss_a3.png"));
			}
			if(((Papier) name).getFarbe().equals("Weiß") && ((Papier) name).getGroesse().equals("A4"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Papier_weiss_a4.png"));
			}
			if(((Papier) name).getFarbe().equals("Weiß") && ((Papier) name).getGroesse().equals("A5"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Papier_weiss_a5.png"));
			}
			if(((Papier) name).getFarbe().equals("Grün") && ((Papier) name).getGroesse().equals("A3"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Papier_gruen_a3.png"));
			}
			if(((Papier) name).getFarbe().equals("Grün") && ((Papier) name).getGroesse().equals("A4"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Papier_gruen_a4.png"));
			}
			if(((Papier) name).getFarbe().equals("Grün") && ((Papier) name).getGroesse().equals("A5"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Papier_gruen_a5.png"));
			}
			if(((Papier) name).getFarbe().equals("Blau") && ((Papier) name).getGroesse().equals("A3"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Papier_blau_a3.png"));
			}
			if(((Papier) name).getFarbe().equals("Blau") && ((Papier) name).getGroesse().equals("A4"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Papier_blau_a4.png"));
			}
			if(((Papier) name).getFarbe().equals("Blau") && ((Papier) name).getGroesse().equals("A5"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Papier_blau_a5.png"));
			}
		}
		if(name instanceof Holz)
		{
			if(((Holz) name).getArt().equals("Kiefer") && ((Holz) name).getForm().equals("Bretter"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Holz_kiefer_bretter.png"));
			}
			if(((Holz) name).getArt().equals("Buche") && ((Holz) name).getForm().equals("Bretter"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Holz_buche_bretter.png"));
			}
			if(((Holz) name).getArt().equals("Eiche") && ((Holz) name).getForm().equals("Bretter"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Holz_eiche_bretter.png"));
			}
			if(((Holz) name).getArt().equals("Kiefer") && ((Holz) name).getForm().equals("Balken"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Holz_kiefer_balken.png"));
			}
			if(((Holz) name).getArt().equals("Buche") && ((Holz) name).getForm().equals("Balken"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Holz_buche_balken.png"));
			}
			if(((Holz) name).getArt().equals("Eiche") && ((Holz) name).getForm().equals("Balken"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Holz_eiche_balken.png"));
			}
			if(((Holz) name).getArt().equals("Kiefer") && ((Holz) name).getForm().equals("Scheit"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Holz_kiefer_scheit.png"));
			}
			if(((Holz) name).getArt().equals("Buche") && ((Holz) name).getForm().equals("Scheit"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Holz_buche_scheit.png"));
			}
			if(((Holz) name).getArt().equals("Eiche") && ((Holz) name).getForm().equals("Scheit"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Holz_eiche_scheit.png"));
			}
		}
		if(name instanceof Stein)
		{
			if(((Stein) name).getArt().equals("Marmor") && ((Stein) name).getGewicht().equals("Leicht"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Stein_marmor_leicht.png"));
			}
			if(((Stein) name).getArt().equals("Marmor") && ((Stein) name).getGewicht().equals("Mittel"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Stein_marmor_mittel.png"));
			}
			if(((Stein) name).getArt().equals("Marmor") && ((Stein) name).getGewicht().equals("Schwer"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Stein_marmor_schwer.png"));
			}
			if(((Stein) name).getArt().equals("Granit") && ((Stein) name).getGewicht().equals("Leicht"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Stein_granit_leicht.png"));
			}
			if(((Stein) name).getArt().equals("Granit") && ((Stein) name).getGewicht().equals("Mittel"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Stein_granit_mittel.png"));
			}
			if(((Stein) name).getArt().equals("Granit") && ((Stein) name).getGewicht().equals("Schwer"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Stein_granit_schwer.png"));
			}
			if(((Stein) name).getArt().equals("Sandstein") && ((Stein) name).getGewicht().equals("Leicht"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Stein_sandstein_leicht.png"));
			}
			if(((Stein) name).getArt().equals("Sandstein") && ((Stein) name).getGewicht().equals("Mittel"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Stein_sandstein_mittel.png"));
			}
			if(((Stein) name).getArt().equals("Sandstein") && ((Stein) name).getGewicht().equals("Schwer"))
			{
				return new ImageIcon(MainWindowLayered.class.getResource("/bilder/Stein_sandstein_schwer.png"));
			}
		}
		return new ImageIcon(MainWindowLayered.class.getResource("/bilder/leer.png"));
	}
	
	// Updaten des Lagers im Fenster
	public void UpdateMainLagerraum () {
		int j = 0;
		for(int i = 0; i < 27; i++)
		{
			String text = Start.lager.getLagerplatzInhalt(getLagerplatzZuGrafiklagerplatz(i));
			if(text == null)
			{
				text = "leer";
			}
			if(j < 3)
			{
				lagerraum_feld[i].setHorizontalAlignment(JLabel.RIGHT);
				j++;
			}
			else if(j < 6)
			{
				lagerraum_feld[i].setHorizontalAlignment(JLabel.CENTER);
				j++;
			}
			else
			{
				lagerraum_feld[i].setHorizontalAlignment(JLabel.LEFT);
				j++;
			}
			if(j == 9)
			{
				j = 0;
			}
			ImageIcon icon2 = bekommeBild(Start.lager.getInhalt(getLagerplatzZuGrafiklagerplatz(i)));
			Image test = getSkaliertesBild(icon2.getImage(), 64, 64);
			lagerraum_feld[i].setIcon(new ImageIcon(test));
			lagerraum_feld[i].setToolTipText(text);
		}
	}
	
	// Funktion um die Größe der Bilder zu verändern
	public Image getSkaliertesBild(Image quelle, int w, int h){
	    BufferedImage ziel = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = ziel.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(quelle, 0, 0, w, h, null);
	    g2.dispose();

	    return ziel;
	}
	// Um Zurückzugeben, welche Reihe der Inhalt ist
	public int getLagerplatzFromInhalt(int i) {
		if(i < 9)
		{
			return 1;
		}
		if(i < 18)
		{
			return 2;
		}
		if(i < 27)
		{
			return 3;
		}
		return 1;
	}
}
