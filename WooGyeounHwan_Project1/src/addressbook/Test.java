package addressbook;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.GridLayout;

public class Test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
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
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 611, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JMenuBar menuBar_3 = new JMenuBar();
		panel.add(menuBar_3);
		
		JMenuBar menuBar_1 = new JMenuBar();
		panel.add(menuBar_1);
		
		JMenuBar menuBar_2 = new JMenuBar();
		panel.add(menuBar_2);
		
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);
		
		JMenuBar menuBar_4 = new JMenuBar();
		panel.add(menuBar_4);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
	}

}
