package display;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorTableCellRenderer implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// Zum Einfärben der Zahlen
		JLabel label = new JLabel((String) value);
		if (column == 2 && value != "") {
			int Test = Integer.valueOf((String) value);
			System.out.println(Test);
			if (Test < 0) {
				label.setForeground(Color.RED);
			} else {
				label.setForeground(Color.GREEN);
			}
		}
		return label;
	}
}