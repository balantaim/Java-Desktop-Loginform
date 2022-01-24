package loginf;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;

public class RegisterForm extends JFrame {

	private JPanel RegisterPane;
	private JTextField txtEnterYourName;
	private JPasswordField passwordField;
	private JPasswordField passwordField2;
	private JTextField txtEnterYourEmail;
	static int xy;
	static int xx;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm frame = new RegisterForm();
					frame.setUndecorated(true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterForm() {
		setTitle("Register Form");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterForm.class.getResource("/Images/orbz-air-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		RegisterPane = new JPanel();
		RegisterPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(RegisterPane);
		RegisterPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x-xx, y-xy);
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				 xx = e.getX();
				 xy = e.getY();
			}
		});
		panel.setBounds(0, 0, 1000, 112);
		panel.setBackground(new Color(255, 153, 0));
		RegisterPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegisterForm.class.getResource("/Images/orbz-air-icon.png")));
		label.setBounds(10, 0, 128, 112);
		panel.add(label);
		
		JLabel lblRegisterToEquilibruim = new JLabel("Register to Equilibruim");
		lblRegisterToEquilibruim.setForeground(Color.BLACK);
		lblRegisterToEquilibruim.setFont(new Font("Stencil", Font.BOLD, 28));
		lblRegisterToEquilibruim.setBounds(161, 24, 394, 66);
		panel.add(lblRegisterToEquilibruim);
		
		JLabel lblNewLabel_1 = new JLabel("  X");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(625, 11, 53, 45);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setToolTipText("Enter your name");
		panel_1.setBounds(0, 110, 780, 453);
		panel_1.setBackground(new Color(0, 102, 51));
		RegisterPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Go back to login.");
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				
				LoginForm login= new LoginForm();
				login.setUndecorated(true);
				login.setVisible(true);
				//regform.pack();
				login.setLocationRelativeTo(null);
				login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
			}
		});
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(294, 351, 121, 21);
		panel_1.add(lblNewLabel);
		
		txtEnterYourName = new JTextField();
		txtEnterYourName.setBackground(new Color(0, 102, 51));
		txtEnterYourName.setBorder(new MatteBorder(0, 2, 2, 0, (Color) Color.BLACK));
		txtEnterYourName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterYourName.setText("");
			}
		});
		txtEnterYourName.setText("Enter your name");
		txtEnterYourName.setToolTipText("");
		txtEnterYourName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEnterYourName.setForeground(Color.BLACK);
		txtEnterYourName.setBounds(242, 27, 221, 36);
		panel_1.add(txtEnterYourName);
		txtEnterYourName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(0, 102, 51));
		passwordField.setBorder(new MatteBorder(0, 2, 2, 0, (Color) Color.BLACK));
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField.setText("");
			}
		});
		passwordField.setText("888888");
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(242, 156, 221, 36);
		panel_1.add(passwordField);
		
		passwordField2 = new JPasswordField();
		passwordField2.setForeground(Color.BLACK);
		passwordField2.setBackground(new Color(0, 102, 51));
		passwordField2.setBorder(new MatteBorder(0, 2, 2, 0, (Color) Color.BLACK));
		passwordField2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				passwordField2.setText("");
			}
		});
		passwordField2.setText("888888");
		passwordField2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField2.setCaretColor(Color.BLACK);
		passwordField2.setBounds(242, 217, 221, 36);
		panel_1.add(passwordField2);
		
		txtEnterYourEmail = new JTextField();
		txtEnterYourEmail.setBackground(new Color(0, 102, 51));
		txtEnterYourEmail.setBorder(new MatteBorder(0, 2, 2, 0, (Color) Color.BLACK));
		txtEnterYourEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEnterYourEmail.setText("");
			}
		});
		txtEnterYourEmail.setText("Enter your Email");
		txtEnterYourEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEnterYourEmail.setForeground(Color.BLACK);
		txtEnterYourEmail.setColumns(10);
		txtEnterYourEmail.setBounds(242, 92, 221, 36);
		panel_1.add(txtEnterYourEmail);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBackground(new Color(255, 153, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(242, 290, 221, 36);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(47, 32, 121, 24);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(47, 97, 121, 24);
		panel_1.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(47, 161, 121, 24);
		panel_1.add(lblPassword);
		
		JLabel lblRepassword = new JLabel("Repassword:");
		lblRepassword.setForeground(Color.BLACK);
		lblRepassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRepassword.setBounds(47, 222, 121, 24);
		panel_1.add(lblRepassword);
	}
}
