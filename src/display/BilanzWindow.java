package display;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import lagerregal.Bilanzobjekt;
import lagerregal.Start;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class BilanzWindow {
	public BilanzWindow() {
		JFrame bilanzfenster = new JFrame();
		bilanzfenster.setSize(new Dimension(600,600));
		bilanzfenster.setLocationRelativeTo(null);
		bilanzfenster.setTitle("Bilanz");
		
		JPanel oben = new JPanel();
		oben.setLayout(new GridLayout(1,3));
		
		JLabel kontostand = new JLabel("<html><body><center>Dein Kontostand <br>" + Integer.toString(Start.bilanz.getGesamtkonto()) + "€</center></body></html>");
		JLabel gesamtplus = new JLabel("<html><body><center>Dein Gesamtplus <br>" + Integer.toString(Start.bilanz.getGesamtplus()) + "€</center></body></html>");
		JLabel gesamtminus = new JLabel("<html><body><center>Dein Gesamtminus <br>" + Integer.toString(Start.bilanz.getGesamtminus()) + "€</center></body></html>");
		
		kontostand.setHorizontalAlignment(JLabel.CENTER);
		gesamtplus.setHorizontalAlignment(JLabel.CENTER);
		gesamtminus.setHorizontalAlignment(JLabel.CENTER);
		
		oben.add(kontostand);
		oben.add(gesamtplus);
		oben.add(gesamtminus);
		
		JPanel unten = new JPanel();
		unten.setLayout(new GridLayout(1,1));
		
		// Die Column-Titles
		String[] title = new String[]{
				"Grund", "Produkt", "Kosten", "Datum"
		};
		
		final DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Grund");
		model.addColumn("Produkt");
		model.addColumn("Kosten");
		model.addColumn("Datum");
		
		JTable table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(450,63));
        table.setFillsViewportHeight(true);
		JPanel content = new JPanel();
		
		JScrollPane js = new JScrollPane(table);
		js.setVisible(true);
		content.setLayout(new BorderLayout());
		content.add(js, BorderLayout.CENTER);
		unten.add(content);
		
		if(Start.bilanz.getGeldverlauf().isEmpty())
		{
			for(int i = 0; i < 5; i++)
			{
				Vector<String> data = new Vector<String>(4);
				data.add("");
				data.add("");
				data.add("");
				data.add("");
				model.addRow(data);
			}
		}
		else
		{
			int count = 0;
			for(Bilanzobjekt e: Start.bilanz.getGeldverlauf())
			{
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

				Vector<String> data = new Vector<String>(4);
				data.add(e.getGrund());
				data.add(e.getProdukt().getClass().getSimpleName());
				if(e.getGrund() == "Umlagern" || e.getGrund() == "Verschrotten")
				{
					data.add(Integer.toString(e.getKosten()));
				}
				else
				{
					data.add(Integer.toString(e.getKosten()));
				}
				data.add(dtf.format(e.getZeit()));
				model.addRow(data);
				count++;
			}
			if(count < 5)
			{
				for(int i = count; i < 5; i++)
				{
					Vector<String> data = new Vector<String>(4);
					data.add("");
					data.add("");
					data.add("");
					data.add("");
					model.addRow(data);	
				}
			}
		}
		
		//verlauf.setDefaultRenderer(Object.class, new ColorTableCellRenderer());
		ColorTableCellRenderer ctr = new ColorTableCellRenderer();
		table.getColumnModel().getColumn(0).setCellRenderer(ctr);
		table.getColumnModel().getColumn(1).setCellRenderer(ctr);
		table.getColumnModel().getColumn(2).setCellRenderer(ctr);
		table.getColumnModel().getColumn(3).setCellRenderer(ctr);
		
		JPanel anzeige = new JPanel();
		anzeige.setLayout(new GridLayout(2,1));
		anzeige.add(oben);
		anzeige.add(unten);
		bilanzfenster.getContentPane().add(anzeige);
		bilanzfenster.pack();
		bilanzfenster.setVisible(true);
	}
}
