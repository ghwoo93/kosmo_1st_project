package addressbook.panel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import addressbook.Address;
import addressbook.AddressBookLogic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;
import java.util.Vector;

import javax.swing.JScrollPane;

public class panelList extends JPanel {
	private JTable table;
	private Vector<String> tblCol = new Vector<String>();
	private Vector<String> tblRow = new Vector<String>();
	private DefaultTableModel model;
	
	AddressBookLogic logic = AddressBookLogic.getInstance();
	
	WindowAdapter handler = new WindowAdapter() {
		@Override
		public void windowGainedFocus(WindowEvent e) {
			super.windowGainedFocus(e);
			setRow();
		}
	};
	
	/**
	 * Create the panel.
	 */
	public panelList() {
		initialize();
	}
	public void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		tblCol.addElement("이름");
		tblCol.addElement("주소");
		tblCol.addElement("나이");
		tblCol.addElement("연락처");
		
		model = new DefaultTableModel(tblCol,0);
		table = new JTable(model);
		
		setRow();
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void setRow() {
		Set<Character> keys = logic.getAddrBook().keySet();
		for (Character key : keys) {
			for (Address addr : logic.getAddrBook().get(key)) {
				//뷰단 출력
				tblRow.addElement(addr.getName());
				tblRow.addElement(addr.getAddress());
				tblRow.addElement(Integer.valueOf(addr.getAge()).toString());
				tblRow.addElement(addr.getContact());
				model.addRow(tblRow);
				//메모리저장
//				logic.setAddress(
//						addr.getName(), addr.getAddress(), 
//						Integer.valueOf(addr.getAge()).toString(), 
//						addr.getContact());
			}
		}
		model.fireTableDataChanged();
	}

}
