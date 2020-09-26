package addressbook.panel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import addressbook.AddressBookLogic;
import addressbook.exception.NotAddr;
import addressbook.exception.NotAge;
import addressbook.exception.NotContact;
import addressbook.exception.NameException;

public class PanelCreate extends JPanel{
	private JLabel labelName;
	private JLabel labelAddr;
	private JLabel labelAge;
	private JLabel labelCon;
	
	private JTextField tfName;
	private JTextField tfAddr;
	private JTextField tfAge;
	private JTextField tfCon;
	
	private JButton btnSave;
	
	AddressBookLogic logic = AddressBookLogic.getInstance();
	
	ActionListener handler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSave) {
				try {
					logic.setAddress(
							tfName.getText(),tfAddr.getText(),
							tfAge.getText(),tfCon.getText());
				} catch (NameException | NotAddr | NotAge | NotContact e1) {
					JOptionPane
					.showConfirmDialog(
							null, e1.getMessage(),"입력 오류",
							JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
				}
				
				System.out.println(logic.getAddrBook().size());
				logic.printAddr();
			}
		}
	};
	
	public PanelCreate() {
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
		tfCon = new JTextField("010");
		tfCon.setBounds(95, 182, 116, 21);
		this.add(tfCon);
		tfCon.setColumns(10);
		
		btnSave = new JButton("즈장");
		btnSave.setBounds(63, 224, 97, 23);
		add(btnSave);
		btnSave.addActionListener(handler);
		
		setLayout(null);
		
	}
	
	
}
