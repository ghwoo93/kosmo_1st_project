package addressbook;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class panelCreate extends JPanel{
	JLabel labelName;
	JLabel labelAddr;
	JLabel labelAge;
	JLabel labelCon;
	
	JTextField tfName;
	JTextField tfAddr;
	JTextField tfAge;
	JTextField tfCon;
	
	JButton btnSave;
	
	AddressBookLogic logic = AddressBookLogic.getInstance();
	
	ActionListener handler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSave) {
				logic.setAddress(
						tfName.getText(),tfAddr.getText(),
						tfAge.getText(),tfCon.getText());
				logic.printAddr();
			}
		}
	};
	
	public panelCreate() {
		labelName = new JLabel("이름"); 
		labelName.setBounds(32, 44, 57, 15);
		this.add(labelName);
		labelAddr = new JLabel("주소");
		labelAddr.setBounds(32, 88, 57, 15);
		this.add(labelAddr);
		labelAge = new JLabel("나이");
		labelAge.setBounds(32, 136, 57, 15);
		this.add(labelAge);
		labelCon = new JLabel("연락처");
		labelCon.setBounds(32, 185, 57, 15);
		this.add(labelCon);
		
		tfName = new JTextField("한글 이름");
		tfName.setBounds(95, 41, 116, 21);
		this.add(tfName);
		tfName.setColumns(10);
		tfAddr = new JTextField("한글 주소");
		tfAddr.setBounds(95, 85, 116, 21);
		this.add(tfAddr);
		tfAddr.setColumns(10);
		tfAge = new JTextField("숫자만 기입");
		tfAge.setBounds(95, 133, 116, 21);
		this.add(tfAge);
		tfAge.setColumns(10);
		tfCon = new JTextField("010-1234-5678");
		tfCon.setBounds(95, 182, 116, 21);
		this.add(tfCon);
		tfCon.setColumns(10);
		
		btnSave = new JButton("즈장");
		btnSave.setBounds(63, 224, 97, 23);
		add(btnSave);
	}
	
	
}
