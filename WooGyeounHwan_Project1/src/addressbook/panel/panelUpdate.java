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

public class panelUpdate extends JPanel{
	private JLabel labelName;
	private JLabel labelAddr;
	private JLabel labelAge;
	private JLabel labelCon;
	private JLabel labelNotice;
	
	private JTextField tfName;
	private JTextField tfAddr;
	private JTextField tfAge;
	private JTextField tfCon;
	
	private JButton btnUp;
	private JButton btnSearch;
	
	private Address addr;
	
	private AddressBookLogic logic = AddressBookLogic.getInstance();
	
	ActionListener handler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSearch) {
				addr = logic.searchByName(tfName.getText());
				tfAddr.setText(addr.getAddress());
				tfAge.setText(Integer.valueOf(addr.getAge()).toString());
				tfCon.setText(addr.getContact());
			}else if(e.getSource()==btnUp) {
				addr.setAddress(tfAddr.getText());
				addr.setAge(Integer.parseInt(tfAge.getText()));
				addr.setContact(tfCon.getText());
				JOptionPane
					.showConfirmDialog(null, tfName.getText()+" 수정 완료", "수정 확인", JOptionPane.CLOSED_OPTION);
			}
		}
	};
	
	public panelUpdate() {
		labelNotice = new JLabel("수정할");
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
		this.add(tfAddr);
		tfAddr.setColumns(10);
		tfAge = new JTextField();
		tfAge.setBounds(95, 133, 116, 21);
		this.add(tfAge);
		tfAge.setColumns(10);
		tfCon = new JTextField();
		tfCon.setBounds(95, 182, 116, 21);
		this.add(tfCon);
		tfCon.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.setBounds(30, 224, 97, 23);
		add(btnSearch);
		btnSearch.addActionListener(handler);
		btnUp = new JButton("수정");
		btnUp.setBounds(140, 224, 97, 23);
		add(btnUp);
		btnUp.addActionListener(handler);
		
		setLayout(null);
		
	}
}

