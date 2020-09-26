package addressbook.panel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;


public class MyFrame {

	private JFrame frame;
	private JTabbedPane tabbedPane;
	private panelBottom bottomP;
	private panelCreate panelC;
	private panelList panelL;
	private panelDelete panelD;
	private panelUpdate panelU;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame window = new MyFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MyFrame() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 623, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bottomP = new panelBottom();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelC = new panelCreate();
		panelL = new panelList();
		panelD = new panelDelete();
		panelU = new panelUpdate();
		
		
		frame.getContentPane().add(bottomP, BorderLayout.SOUTH);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("입력", null, panelC, null);
		tabbedPane.addTab("출력", null, panelL, null);
		tabbedPane.addTab("수정", null, panelU, null);
		tabbedPane.addTab("삭제", null, panelD, null);
		
	}
}
