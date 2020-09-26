package addressbook.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import addressbook.Address;
import addressbook.AddressBookLogic;
import addressbook.exception.NameException;

public class PanelDelete extends JPanel{
	JLabel labelName;
	JLabel labelAddr;
	JLabel labelAge;
	JLabel labelCon;
	JLabel labelNotice;
	
	JTextField tfName;
	JTextField tfAddr;
	JTextField tfAge;
	JTextField tfCon;
	
	JButton btnDel;
	
	AddressBookLogic logic = AddressBookLogic.getInstance();
	
	ActionListener handler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnDel) {
				Address addr=null;
				try {
					addr = logic.deleteAddr(tfName.getText());
				} catch (NameException e1) {
					JOptionPane
					.showConfirmDialog(
							tfAddr, e1.getMessage(),"입력 오류",
							JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
				}
				tfAge.setText(Integer.valueOf(addr.getAge()).toString());
				tfAddr.setText(addr.getAddress());
				tfCon.setText(addr.getContact());
				JOptionPane
					.showConfirmDialog(
							null, tfName.getText()+" 삭제완료", "삭제 확인", 
							JOptionPane.CLOSED_OPTION);
				}
		}
	};
	
	public PanelDelete() {
		labelNotice = new JLabel("삭제할");
		add(labelNotice);
		labelNotice.setBounds(32, 30, 57, 15);
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
		tfAddr = new JTextField();
		tfAddr.setBounds(95, 85, 116, 21);
		tfAddr.setEditable(false);
		this.add(tfAddr);
		tfAddr.setColumns(10);
		tfAge = new JTextField();
		tfAge.setBounds(95, 133, 116, 21);
		tfAge.setEditable(false);
		this.add(tfAge);
		tfAge.setColumns(10);
		tfCon = new JTextField();
		tfCon.setBounds(95, 182, 116, 21);
		tfCon.setEditable(false);
		this.add(tfCon);
		tfCon.setColumns(10);
		
		btnDel = new JButton("삭제");
		btnDel.setBounds(63, 224, 97, 23);
		add(btnDel);
		btnDel.addActionListener(handler);
		
		setLayout(null);
		
	}
}
