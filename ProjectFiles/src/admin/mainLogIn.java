package admin;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import user.Teacher;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainLogIn {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainLogIn window = new mainLogIn();
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
	public mainLogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblChooseALog = new JLabel("choose a log in system type");
		lblChooseALog.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblChooseALog.setBounds(87, 11, 257, 54);
		frame.getContentPane().add(lblChooseALog);
		
		JButton btnNewButton = new JButton("Admin");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admin a = new Admin();
				a.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(131, 76, 172, 41);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("User");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teacher.main();
				frame.setVisible(false);
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(131, 157, 172, 41);
		frame.getContentPane().add(btnNewButton_1);
	}
}
