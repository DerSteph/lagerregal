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
		bilanzfenster.setSize(600,600);
		bilanzfenster.setLocationRelativeTo(null);
		bilanzfenster.setTitle("Bilanz");
		
		JPanel oben = new JPanel();
		oben.setLayout(new GridLayout(1,3));
		
		JLabel kontostand = new JLabel(Integer.toString(Start.bilanz.getGesamtkonto()));
		JLabel gesamtplus = new JLabel(Integer.toString(Start.bilanz.getGesamtplus()));
		JLabel gesamtminus = new JLabel(Integer.toString(Start.bilanz.getGesamtminus()));
		
		oben.add(kontostand);
		oben.add(gesamtplus);
		oben.add(gesamtminus);
		
		JPanel unten = new JPanel();
		unten.setLayout(new GridLayout(1,1));
		
		// Die Column-Titles
		String[] title = new String[]{
				"Grund", "Produkt", "Kosten", "Datum"
		};
		
		final DefaultTableModel model = new DefaultTableModel(title, 0);
		
		JTable verlauf = new JTable(model);
		Container content = new Container();
		
		content.add(new JScrollPane());
		content.setLayout(new BorderLayout());
		content.add(verlauf.getTableHeader(), BorderLayout.PAGE_START);
		content.add(verlauf, BorderLayout.CENTER);
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
		verlauf.getColumnModel().getColumn(0).setCellRenderer(ctr);
		verlauf.getColumnModel().getColumn(1).setCellRenderer(ctr);
		verlauf.getColumnModel().getColumn(2).setCellRenderer(ctr);
		verlauf.getColumnModel().getColumn(3).setCellRenderer(ctr);
		
		JPanel anzeige = new JPanel();
		anzeige.setLayout(new GridLayout(2,1));
		anzeige.add(oben);
		anzeige.add(unten);
		bilanzfenster.getContentPane().add(anzeige);
		bilanzfenster.pack();
		bilanzfenster.setVisible(true);
	}
}
