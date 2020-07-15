package display;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorTableCellRenderer implements TableCellRenderer{
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel label = new JLabel((String)value);
		//System.out.println("Row: " + row + ", Column: " + column);
		if(row == 1 && column == 1)
		{
			label.setForeground(Color.GREEN);
			System.out.println("YAY");
		}
		return label;
	}
}
