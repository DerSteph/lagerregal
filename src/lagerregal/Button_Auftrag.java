package lagerregal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button_Auftrag implements ActionListener{
	public JButton auftrag[] = new JButton[3];
	public Button_Auftrag() {
		for(int k = 0; k < 3; k++)
		{
			auftrag[k] = new JButton();
			auftrag[k].addActionListener(this);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton)
		{
			String text = ((JButton) e.getSource()).getText();
			if(!text.equals(""))
			{
				//Grafik.FensterAuftragHinzufuegenAuswahl(text);
			}
		}
	}
}
