package addressbook.panel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	/**
	 * Create the application.
	 */
	public MyFrame() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 623, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bottomP = new panelBottom();
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelC = new panelCreate();
		panelL = new panelList();
		JPanel panel_3 = new JPanel();
		JPanel panel_4 = new JPanel();
		JPanel panel_5 = new JPanel();
		
		frame.getContentPane().add(bottomP, BorderLayout.SOUTH);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("입력", null, panelC, null);
		
		tabbedPane.addTab("출력", null, panelL, null);
		
		tabbedPane.addTab("수정", null, panel_3, null);
		
		tabbedPane.addTab("삭제", null, panel_4, null);
		
		tabbedPane.addTab("검색", null, panel_5, null);
	}
}
