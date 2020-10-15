package addressbook.panel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;


public class MyFrame {

	private JFrame frame;
	private JTabbedPane tabbedPane;
	private PanelBottom bottomP;
	private PanelCreate panelC;
	private PanelList panelL;
	private PanelDelete panelD;
	private PanelUpdate panelU;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MyFrame window = new MyFrame();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public MyFrame() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("주소록");
		frame.setBounds(100, 100, 623, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		bottomP = new PanelBottom();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelC = new PanelCreate();
		panelL = new PanelList();
		panelD = new PanelDelete();
		panelU = new PanelUpdate();
		
		
		frame.getContentPane().add(bottomP, BorderLayout.SOUTH);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("입력", null, panelC, null);
		tabbedPane.addTab("출력", null, panelL, null);
		tabbedPane.addTab("수정", null, panelU, null);
		tabbedPane.addTab("삭제", null, panelD, null);
		
	}
}
