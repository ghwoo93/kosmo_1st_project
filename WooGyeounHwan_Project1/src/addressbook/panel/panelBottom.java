package addressbook.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import addressbook.AddressBookLogic;

public class panelBottom extends JPanel{
	
	JButton btnSave;
	JButton btnExit;
	
	AddressBookLogic logic = AddressBookLogic.getInstance();
	
	ActionListener handler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSave) {
				try {
					logic.saveAddr();
				} catch (IOException e1) {
					System.out.println("저장 완료");
				}
			}
		}
	};
	
	public panelBottom() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnSave = new JButton("Save");
		btnExit = new JButton("Exit");
		this.add(btnSave);
		this.add(btnExit);
	}
	
}
