package addressbook.panel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import addressbook.Address;
import addressbook.AddressBookLogic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.Vector;

import javax.swing.JScrollPane;

public class panelList extends JPanel{
	private JTable table;
	private JScrollPane scrollPane;
	private static Vector<String> tblCol = new Vector<String>();
	private DefaultTableModel model;
	private JPanel tableBottom;
	
	private JButton refreshBtn;
	
	AddressBookLogic logic = AddressBookLogic.getInstance();
	
	public panelList() {
		initialize();
	}
	
	public void initialize() {
		setLayout(new BorderLayout(0, 0));
		tableBottom = new JPanel();
		refreshBtn = new JButton("refresh");
		
		//맨처음 데이터 생성
		model = new DefaultTableModel(initRow(), getHeaderVector());
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		
		tableBottom.add(refreshBtn);
		add(tableBottom,BorderLayout.SOUTH);
		add(scrollPane, BorderLayout.CENTER);
		
		refreshBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tableRefresh();
				
			}
		});
		
	}
	public void tableRefresh() {
		model = (DefaultTableModel)table.getModel();
		while(model.getRowCount()>0) {
			model.removeRow(0);
		}
		getRow();
//		model.addRow(getRow());
	}
	//맨첨
	public Vector<Vector<String>> initRow() {
		Vector<Vector<String>> tblRow = new Vector<Vector<String>>();
		Set<Character> keys = logic.getAddrBook().keySet();
		//맨처음일때만 돌아주고
		for (Character key : keys) {
			for (Address addr : logic.getAddrBook().get(key)) {
				Vector<String> addrRow = new Vector<String>();
				addrRow.add(addr.getName());
				addrRow.add(addr.getAddress());
				addrRow.add(Integer.valueOf(addr.getAge()).toString());
				addrRow.add(addr.getContact());
			}
		}
		//두번째일때 부터는 실행
		return tblRow;
	}
	
	public Vector<String> getRow(){
		Vector<String> addrRow=null;
		Set<Character> keys = logic.getAddrBook().keySet();
		for (Character key : keys) {
			for (Address addr : logic.getAddrBook().get(key)) {
				//이름 검색을 해서 없을시에 그린다
					addrRow = new Vector<String>();
					addrRow.add(addr.getName());
					addrRow.add(addr.getAddress());
					addrRow.add(Integer.valueOf(addr.getAge()).toString());
					addrRow.add(addr.getContact());
					model.addRow(addrRow);
			}
		}
		return addrRow;
	}
	
	public Vector<String> getHeaderVector(){
		tblCol.add("이름");
		tblCol.add("주소");
		tblCol.add("나이");
		tblCol.add("연락처");
		return tblCol;
	}
	

}
