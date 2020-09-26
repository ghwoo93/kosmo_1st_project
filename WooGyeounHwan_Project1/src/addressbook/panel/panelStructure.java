package addressbook.panel;

import javax.swing.JLabel;
import javax.swing.JTextField;

public interface panelStructure {
	JLabel labelName = new JLabel("이름");
	JLabel labelAddr = new JLabel("주소");
	JLabel labelAge = new JLabel("나이");
	JLabel labelCon = new JLabel("연락처");
	
	JTextField tfName = new JTextField();
	JTextField tfAddr = new JTextField();
	JTextField tfAge = new JTextField();
	JTextField tfCon = new JTextField();
}
