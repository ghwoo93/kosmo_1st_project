package addressbook.panel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import addressbook.Address;
import addressbook.AddressBookLogic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;
import java.util.Vector;

import javax.swing.JScrollPane;

public class panelList extends JPanel implements TableModelListener{
	private JTable table;
	private JScrollPane scrollPane;
	private static Vector<String> tblCol = new Vector<String>();
//	private Vector<Vector<String>> tblRow = new Vector<Vector<String>>();
//	private Vector<String> addrRow = new Vector<String>();
	private DefaultTableModel model;
//	private DefaultTableModel modelCol;
	
	AddressBookLogic logic = AddressBookLogic.getInstance();
	
	/**
	 * Create the panel.
	 */
	public panelList() {
		initialize();
	}
	public void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		model = new DefaultTableModel(getRow(), getHeaderVector());
		table = new JTable(model);
		getRow();
		scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
//		table.addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyTyped(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void keyReleased(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// TODO Auto-generated method stub
//				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
//					System.out.println("이벤트발생");
//					int row = table.getSelectedRow();
//                    int col = table.getSelectedColumn();
//                    System.out.println("row:"+model.getValueAt(row, col));
//                    System.out.println("row:"+row+"col:"+col);
//                    System.out.println(getRow().size());
//                    if (col != 3) {
//                    	System.out.println("이벤트발생");
//                        return;
//                    }
//                    
//					tableRefresh();
//				}
//			}
//		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==1) {
					System.out.println("이벤트발생");
					int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();
                    System.out.println("row:"+model.getValueAt(row, col));
                    System.out.println("row:"+row+"col:"+col);
                    System.out.println(getRow().size());
                    if (col != 3) {
                    	System.out.println("이벤트발생");
                        return;
                    }
                    
					tableRefresh();
				}
			}
		});
//		table.getModel().addTableModelListener(this);
	}
	public void tableRefresh() {
		model=(DefaultTableModel)table.getModel();
		model.insertRow(getRow().size()-1, getRow());
//		model.setDataVector(getRow(), getHeaderVector());
		table.setModel(model);
	}
	
//	public Vector<String> getRow() {
//		Vector<Vector<String>> tblRow = new Vector<Vector<String>>();
//		Vector<String> addrRow = new Vector<String>();
//		Set<Character> keys = logic.getAddrBook().keySet();
//		for (Character key : keys) {
//			for (Address addr : logic.getAddrBook().get(key)) {
//				addrRow.add(addr.getName());
//				addrRow.add(addr.getAddress());
//				addrRow.add(Integer.valueOf(addr.getAge()).toString());
//				addrRow.add(addr.getContact());
//			}
//		}
//		return addrRow;
//	}
	
	public Vector<Vector<String>> getRow() {
		Vector<Vector<String>> tblRow = new Vector<Vector<String>>();
		Set<Character> keys = logic.getAddrBook().keySet();
		for (Character key : keys) {
			for (Address addr : logic.getAddrBook().get(key)) {
				Vector<String> addrRow = new Vector<String>();
				addrRow.add(addr.getName());
				addrRow.add(addr.getAddress());
				addrRow.add(Integer.valueOf(addr.getAge()).toString());
				addrRow.add(addr.getContact());
				tblRow.add(addrRow);
			}
		}
		return tblRow;
	}
	
	public Vector<String> getHeaderVector(){
		tblCol.add("이름");
		tblCol.add("주소");
		tblCol.add("나이");
		tblCol.add("연락처");
		return tblCol;
	}
	
	//테이블 변하면 데이터 수정
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		System.out.println("들어옴");
		int row = e.getFirstRow();
        int column = e.getColumn();
        model = (DefaultTableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        
		
	}

}
