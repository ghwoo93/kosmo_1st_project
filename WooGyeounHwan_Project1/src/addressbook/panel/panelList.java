package addressbook.panel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import addressbook.Address;
import addressbook.AddressBookLogic;

import java.awt.BorderLayout;
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
	
	AddressBookLogic logic = AddressBookLogic.getInstance();
	
	public panelList() {
		initialize();
	}
	
	public void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		//맨처음일때만 돌아주고
		model = new DefaultTableModel(initRow(), getHeaderVector());
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {
					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();
					System.out.println("row:" + model.getValueAt(row, col));
					System.out.println("row:" + row + "col:" + col);
					System.out.println(getRow().size());
					tableRefresh();
				}
			}
		});
//		table.getModel().addTableModelListener(this);
	}
	public void tableRefresh() {
		model=(DefaultTableModel)table.getModel();
//		model=new DefaultTableModel(initRow(), getHeaderVector());
//		model.insertRow(initRow().size()-1, getRow());
		//모델을 새로 만들고 테이블에 모델을 붙인다
		table = new JTable(model);
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
				//여기서 벡터를 넘겨준다 table
				tblRow.add(addrRow);
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
				if(!model.getDataVector().contains(addr.getName())) {
					addrRow = new Vector<String>();
					addrRow.add(addr.getName());
					addrRow.add(addr.getAddress());
					addrRow.add(Integer.valueOf(addr.getAge()).toString());
					addrRow.add(addr.getContact());
					
					}
				}
		}
		model.insertRow(initRow().size()-1, addrRow);
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
