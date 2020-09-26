package addressbook.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import addressbook.AddressBookLogic;

public class panelBottom extends JPanel{
	
	private JButton btnSave;
	private JButton btnExit;
	private JButton btnAdd;
	
	AddressBookLogic logic = AddressBookLogic.getInstance();
	
	ActionListener handler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSave) {
				try {
					logic.saveAddressBook();
					logic.printAddr();
				} catch (Exception e1) {
					System.out.println("저장 완료 실패");
				}
			}else if(e.getSource()==btnExit) {
				logic.exitAddrBook();
			}
		}
	};
	
	public panelBottom() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnSave = new JButton("Save");
		btnExit = new JButton("Exit");
		btnAdd = new JButton("Add");
		this.add(btnSave);
		this.add(btnExit);
		btnSave.addActionListener(handler);
		btnExit.addActionListener(handler);
		
		
	}
	
}
