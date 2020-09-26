package addressbook.panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import addressbook.Address;
import addressbook.AddressBookLogic;
import addressbook.exception.NotAddr;
import addressbook.exception.NotAge;
import addressbook.exception.NotContact;
import addressbook.exception.NotInit;
import addressbook.exception.NameException;
import common.utility.CommonUtilities;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JScrollPane;

public class PanelList extends JPanel{
	private JTable table;
	private JScrollPane scrollPane;
	private static Vector<String> tblCol = new Vector<String>();
//	private Vector<Vector<String>> tblRow = new Vector<Vector<String>>();
	private DefaultTableModel model;
	private JPanel tableBottom;
	
	private JButton refreshBtn;
	private JButton searchBtn;
	
	private JTextField searchTf;
	
	private JComboBox<String> searchColumn;
	
	AddressBookLogic logic = AddressBookLogic.getInstance();
	
	public PanelList() {
		initialize();
	}
	
	public void initialize() {
		
		setLayout(new BorderLayout(0, 0));
		tableBottom = new JPanel();
		refreshBtn = new JButton(new ImageIcon("src/addressbook/image/refresh.png"));
		searchBtn = new JButton("검색");
		
		//맨처음 데이터 생성
		model = new DefaultTableModel(initLoadRow(), getHeaderVector());
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		
		String[] searchColumns = {"초성","이름","주소","나이","연락처"};
		searchColumn = new JComboBox<String>(searchColumns);
		
		
		searchTf = new JTextField();
		searchTf.setColumns(8);
		
		tableBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tableBottom.add(searchColumn);
		tableBottom.add(searchTf);
		tableBottom.add(searchBtn);
		tableBottom.add(refreshBtn);
		add(tableBottom,BorderLayout.SOUTH);
		add(scrollPane, BorderLayout.CENTER);
		
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableRefresh();
				try {
					searchAddrs(
							searchColumn.getSelectedItem(),
							searchTf.getText());
				} catch (
						NotInit | NameException | NotAddr | NotAge | NotContact e1) {
					JOptionPane
					.showConfirmDialog(
							null, e1.getMessage(),"입력 오류",
							JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		refreshBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tableRefresh();
				getRow();
			}
		});
		
	}
	
	public void tableRefresh() {
		model = (DefaultTableModel)table.getModel();
		while(model.getRowCount()>0) {
			model.removeRow(0);
		}
	}
	//맨첨
	public Vector<Vector<String>> initLoadRow() {
		Vector<Vector<String>> tblRow = new Vector<Vector<String>>();
		Vector<String> addrRow = null;
		Set<Character> keys = logic.getAddrBook().keySet();
		//맨처음일때만 돌아주고
		for (Character key : keys) {
			List<Address> listArr = logic.getAddrBook().get(key);
			Collections.sort(listArr);
			for (Address addr : logic.getAddrBook().get(key)) {
				addrRow = new Vector<String>();
				addrRow.add(Character.toString(key));
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
			List<Address> listArr = logic.getAddrBook().get(key);
			Collections.sort(listArr);
			for (Address addr : logic.getAddrBook().get(key)) {
					addrRow = new Vector<String>();
					addrRow.add(Character.toString(key));
					addrRow.add(addr.getName());
					addrRow.add(addr.getAddress());
					addrRow.add(Integer.valueOf(addr.getAge()).toString());
					addrRow.add(addr.getContact());
					//테이블 저장
//					tblRow.add(addrRow);
					//테이블 출력
					model.addRow(addrRow);
			}
		}
		return addrRow;
	}
	
	public Vector<String> getHeaderVector(){
		tblCol.add("초성");
		tblCol.add("이름");
		tblCol.add("주소");
		tblCol.add("나이");
		tblCol.add("연락처");
		return tblCol;
	}
	
	//tf.gettext 확인 후 tf랑 숫자 파라미터 넘겨줌
	//케이스 별로 체크해서 목록에 뿌리기
	public void searchAddrs(Object searchColumn,String searchTf) 
			throws NotInit, NameException, NotAddr, NotAge, NotContact  {
		System.out.println(searchColumn+":"+searchTf);
		Vector<String> addrRow=null;
		for(Address addr :logic.searchAddrBook(searchColumn, searchTf)) {
			addrRow = new Vector<String>();
			addrRow.add(Character.toString(CommonUtilities.getFirstCharacter(addr.getName())));
			addrRow.add(addr.getName());
			addrRow.add(addr.getAddress());
			addrRow.add(Integer.valueOf(addr.getAge()).toString());
			addrRow.add(addr.getContact());
			model.addRow(addrRow);
		}
	}
	
	
}
