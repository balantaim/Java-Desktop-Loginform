package loginf;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Component;

public class RegisterForm extends JFrame {

	private JPanel RegisterPane;
	public JTextField nameField;
	private JPasswordField passField, passRepeatField;
	private int xy, xx;
	private static int PosX, PosY;
	private static String uName = "", uPass = "", repPass = "";
	
    static Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
	
	void updatePosition(int a, int b) {
		PosX = a;
		PosY = b;
		System.out.println(a +" "+b);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					JFrame.setDefaultLookAndFeelDecorated(true);
					RegisterForm frame = new RegisterForm();
					frame.setMinimumSize(new Dimension(700, 550));
					frame.updatePosition(PosX, PosY);
					if(PosX==0 && PosY==0) {
						frame.setLocationRelativeTo(null);
					}else {
						frame.setLocation(PosX,PosY);
					}
					frame.setUndecorated(true);
					frame.setVisible(true);
					
//					Initiate DB connection				
					con = DbConnection.connect();
					
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
		setTitle("Register");
//		Logo on the navigation toolbar
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterForm.class.getResource("/Images/orbz-air-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		RegisterPane = new JPanel();
		RegisterPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(RegisterPane);
		RegisterPane.setLayout(null);
		con = DbConnection.connect();
		
//		Drag and drop frame
		JPanel panelHeader = new JPanel();
		panelHeader.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x-xx, y-xy);
			}
		});
		panelHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				 xx = e.getX();
				 xy = e.getY();
			}
		});
		panelHeader.setBounds(0, 0, 720, 112);
		panelHeader.setBackground(new Color(255, 153, 0));
		RegisterPane.add(panelHeader);
		panelHeader.setLayout(null);
		
//		Logo picture
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(RegisterForm.class.getResource("/Images/orbz-air-icon.png")));
		logo.setBounds(35, 0, 128, 112);
		panelHeader.add(logo);
		
		JLabel header = new JLabel("Register to Equilibruim");
		header.setForeground(Color.BLACK);
		header.setFont(new Font("Stencil", Font.BOLD, 28));
		header.setBounds(173, 23, 394, 66);
		panelHeader.add(header);
		
		JLabel btnClose = new JLabel("");
		btnClose.setAlignmentY(Component.TOP_ALIGNMENT);
		btnClose.setAlignmentX(Component.RIGHT_ALIGNMENT);
//		Create resized Icon
		ImageIcon closeRedIc = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-cancel-red-50.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
		ImageIcon closeIc = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-cancel-50.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnClose.setIcon(closeIc);
		
		btnClose.setHorizontalAlignment(SwingConstants.RIGHT);
		btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnClose.setIcon(closeRedIc);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnClose.setIcon(closeIc);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 26));

		btnClose.setForeground(Color.BLACK);
		btnClose.setBounds(638, 11, 40, 45);
		panelHeader.add(btnClose);
		
		JPanel panelBackground = new JPanel();
		panelBackground.setBounds(0, 110, 720, 450);
		panelBackground.setBackground(new Color(0, 102, 51));
		RegisterPane.add(panelBackground);
		panelBackground.setLayout(null);
		
		JLabel txtToLogin = new JLabel("Go back to login.");
		txtToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtToLogin.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtToLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					con.close();
					con=null;
				} catch (SQLException e2) {
					System.out.println(e2);
				}
				LoginForm login= new LoginForm();
				login.setUndecorated(true);
				login.setVisible(true);
				login.setLocationRelativeTo(null);
				login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
				dispose();
			}
		});
		txtToLogin.setForeground(Color.BLACK);
		txtToLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtToLogin.setBounds(294, 351, 121, 21);
		panelBackground.add(txtToLogin);
		
		nameField = new JTextField();
		nameField.setBackground(new Color(0, 102, 51));
		nameField.setBorder(new MatteBorder(0, 2, 2, 0, (Color) Color.BLACK));

		nameField.setText("");
		nameField.setToolTipText("Enter least 8 characters for name. Latin words is mandatory");
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameField.setForeground(Color.BLACK);
		nameField.setBounds(242, 91, 221, 36);
		panelBackground.add(nameField);
		nameField.setColumns(10);
		
		passField = new JPasswordField();
		passField.setToolTipText("Choice strong password");
		passField.setBackground(new Color(0, 102, 51));
		passField.setBorder(new MatteBorder(0, 2, 2, 0, (Color) Color.BLACK));
		passField.setText("");
		passField.setForeground(Color.BLACK);
		passField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passField.setBounds(242, 156, 221, 36);
		panelBackground.add(passField);
		
		passRepeatField = new JPasswordField();
		passRepeatField.setToolTipText("Repeat password again");
		passRepeatField.setForeground(Color.BLACK);
		passRepeatField.setBackground(new Color(0, 102, 51));
		passRepeatField.setBorder(new MatteBorder(0, 2, 2, 0, (Color) Color.BLACK));
		passRepeatField.setText("");
		passRepeatField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passRepeatField.setCaretColor(Color.BLACK);
		passRepeatField.setBounds(242, 217, 221, 36);
		panelBackground.add(passRepeatField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFocusable(false);
		UIManager.put("Button.select", new Color(102, 59, 16));
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegister.setBackground(new Color(176, 93, 11));
				btnRegister.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRegister.setBackground(new Color(255,153,0));
				btnRegister.setForeground(Color.BLACK);
			}
			@Override
			public void mousePressed(MouseEvent e) {
//				btnRegister.setContentAreaFilled(false);
				btnRegister.setForeground(Color.WHITE);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnRegister.setContentAreaFilled(true);
				btnRegister.setBackground(new Color(176, 93, 11));
				btnRegister.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterValidation();
			}
		});
		btnRegister.setBackground(new Color(255, 153, 0));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegister.setForeground(Color.BLACK);
		btnRegister.setBounds(242, 290, 221, 45);
		panelBackground.add(btnRegister);
		
		JLabel txtUserName = new JLabel("User name:");
		txtUserName.setForeground(Color.BLACK);
		txtUserName.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtUserName.setBounds(47, 103, 121, 24);
		panelBackground.add(txtUserName);
		
		JLabel txtPass = new JLabel("Password:");
		txtPass.setForeground(Color.BLACK);
		txtPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPass.setBounds(47, 161, 121, 24);
		panelBackground.add(txtPass);
		
		JLabel txtRepeatPass = new JLabel("Re-password:");
		txtRepeatPass.setForeground(Color.BLACK);
		txtRepeatPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtRepeatPass.setBounds(47, 222, 121, 24);
		panelBackground.add(txtRepeatPass);
		
		passRepeatField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	               btnRegister.doClick();
	               RegisterValidation();
	            }
	        }

	    });
	}
	
	public void RegisterValidation() {
		boolean nField = false, pField = false, repField = false;		
		uName = nameField.getText();
		uPass = String.valueOf(passField.getPassword());
		repPass = String.valueOf(passRepeatField.getPassword());
					
//		Check user name if fill the requirements
		nField = ValidationRegister.checkName(uName);
//		Check password if fill the requirements
		pField = ValidationRegister.checkPass(uPass);
//		Check password is equal to repeat password
		repField = ValidationRegister.repPass(uPass, repPass);
		System.out.println(nField +" "+pField+" "+repField);
		
		if (nField && pField && repField) {
			uPass=SecurePassword.Encoder(uPass);
			try {
				ps = con.prepareStatement("INSERT INTO Acounts (User,Pass,Sudo) VALUES (?,?,?);");
    			ps.setString(1, uName);
    			ps.setString(2, uPass);
    			ps.setString(3, "0");
    			ps.addBatch();
    			ps.executeBatch();
    			con.close();
    			
    			DashboardUser dashboard= new DashboardUser();
				dashboard.setUndecorated(false);
				dashboard.setMinimumSize(new Dimension(700, 550));
				dashboard.setVisible(true);
				dashboard.setLocationRelativeTo(null);
				dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				PosX = 100;
				PosY = 200;
//				dashboard.updatePosition(PosX, PosY);
				dispose();
			} catch (SQLException e2) {
				System.out.println(e2);
			}
		}
	}
}
