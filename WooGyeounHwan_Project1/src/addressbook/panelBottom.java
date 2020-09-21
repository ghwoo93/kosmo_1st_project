package addressbook;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class panelBottom extends JPanel{
	
	JButton btnSave;
	JButton btnExit;
	
	public panelBottom() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnSave = new JButton("Save");
		btnExit = new JButton("Exit");
		this.add(btnSave);
		this.add(btnExit);
	}
	
}
