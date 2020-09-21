package addressbook;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame {

	private JFrame frame;
	private JPanel bottomP;
	private JTabbedPane tabbedPane;
	static panelCreate panelC;
	
	
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
		frame.getContentPane().add(bottomP, BorderLayout.SOUTH);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		panelC = new panelCreate();
		tabbedPane.addTab("입력", null, panelC, null);
		panelC.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("출력", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("수정", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("삭제", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("검색", null, panel_5, null);
	}
}
