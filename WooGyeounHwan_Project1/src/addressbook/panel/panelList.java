package addressbook.panel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import addressbook.Address;
import addressbook.AddressBookLogic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	
	/**
	 * Create the panel.
	 */
	public panelList() {
		initialize();
	}
	public void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		tblCol.add("이름");
		tblCol.add("주소");
		tblCol.add("나이");
		tblCol.add("연락처");
		model = new DefaultTableModel(tblCol,0);
		table = new JTable(model);
		setRow();
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("이벤트발생");
				setRow();
			}
		});
	}
	
	public void setRow() {
		model = new DefaultTableModel(tblCol,0);
		table.setModel(model);
		Set<Character> keys = logic.getAddrBook().keySet();
		for (Character key : keys) {
			for (Address addr : logic.getAddrBook().get(key)) {
				//뷰단 출력
				tblRow.add(addr.getName());
				tblRow.add(addr.getAddress());
				tblRow.add(Integer.valueOf(addr.getAge()).toString());
				tblRow.add(addr.getContact());
				model.addRow(tblRow);
			}
		}
//		model.fireTableDataChanged();
	}

}
