package loginf;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.Toolkit;

import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Dimension;

public class LoginForm extends JFrame {
	
	private JPanel LoginForm;
	private JTextField nameField;
	private JPasswordField passField;
	private int xy, xx;
	private static int PosX, PosY;
	private static String uName = "", uPass = "";
	
    static Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {		
//					Initiate DB connection
					new Thread(() -> DbConnection.CreateDb()).start();
//					new Thread(() -> con = DbConnection.connect()).start();

					LoginForm frame = new LoginForm();
					frame.setMinimumSize(new Dimension(700, 550));
					frame.setUndecorated(true);
					frame.setVisible(true);
					if(PosX==0 && PosY==0) {
						frame.setLocationRelativeTo(null);
					}else {
						frame.setLocation(PosX,PosY);
					}
					

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setResizable(false);
		
		setTitle("Equilibrium Login");
//		Logo on the navigation toolbar
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginForm.class.getResource("/Images/orbz-air-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 550);
		LoginForm = new JPanel();
		setContentPane(LoginForm);
		LoginForm.setLayout(null);
		
		con = DbConnection.connect();
		
		setResizable(true);
		JPanel panelHeader = new JPanel();
//		Drag and Drop frame
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
	
		panelHeader.setBackground(new Color(255, 153, 0));
		panelHeader.setBounds(0, 0, 720, 112);
		LoginForm.add(panelHeader);
		
//		Logo picture
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(LoginForm.class.getResource("/Images/orbz-air-icon.png")));
		
		JLabel btnClose = new JLabel("");
		btnClose.setBackground(Color.WHITE);
		btnClose.setHorizontalTextPosition(SwingConstants.CENTER);
		btnClose.setHorizontalAlignment(SwingConstants.RIGHT);
		
//		Create resized Icon
		ImageIcon closeRedIc = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-cancel-red-50.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		ImageIcon closeIc = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-cancel-50.png")).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		btnClose.setIcon(closeIc);
		
		btnClose.setAlignmentY(1.0f);
		btnClose.setAlignmentX(1.0f);
		btnClose.setForeground(Color.BLACK);
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
		
		JLabel header = new JLabel("Login to Equilibrium");
		header.setFont(new Font("Stencil", Font.BOLD, 28));
		header.setForeground(Color.BLACK);
		GroupLayout gl_panel = new GroupLayout(panelHeader);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(42)
					.addComponent(logo)
					.addGap(28)
					.addComponent(header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(109)
					.addComponent(btnClose)
					.addGap(43))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(logo, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(32)
							.addComponent(header, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnClose)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelHeader.setLayout(gl_panel);
		
		JPanel panelBackground = new JPanel();
		panelBackground.setForeground(Color.BLACK);
		panelBackground.setBorder(null);
		panelBackground.setBackground(new Color(0, 102, 51));
		panelBackground.setBounds(0, 110, 720, 450);
		LoginForm.add(panelBackground);
		panelBackground.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setUI(new MetalButtonUI() {
		    protected Color getDisabledTextColor() {
		        return Color.WHITE;
		    }
		});
		btnLogin.setEnabled(true);
//		btnLogin.setFocusPainted(false);
		btnLogin.setFocusable(false);
		UIManager.put("Button.select", new Color(102, 59, 16));
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(176, 93, 11));
				btnLogin.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(new Color(255,153,0));
				btnLogin.setForeground(Color.BLACK);
			}
			@Override
			public void mousePressed(MouseEvent e) {
//				btnLogin.setContentAreaFilled(false);	
				btnLogin.setForeground(Color.WHITE);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnLogin.setContentAreaFilled(true);
				btnLogin.setBackground(new Color(176, 93, 11));
				btnLogin.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginValidation();
			}
		});
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(new Color(255, 153, 0));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setBounds(234, 264, 251, 45);
		panelBackground.add(btnLogin);
		
		nameField = new JTextField();
		nameField.setToolTipText("Enter your name");
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setForeground(Color.BLACK);
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameField.setBorder(new MatteBorder(0, 2, 2, 0, (Color) Color.BLACK));
		nameField.setBackground(new Color(0, 102, 51));
		nameField.setBounds(234, 106, 251, 36);
		panelBackground.add(nameField);
		nameField.setColumns(10);
		
		passField = new JPasswordField();
		passField.setToolTipText("Enter correct password");
		passField.setHorizontalAlignment(SwingConstants.CENTER);
		passField.setForeground(Color.BLACK);
		passField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passField.setBorder(new MatteBorder(0, 2, 2, 0, (Color) Color.BLACK));
		passField.setBackground(new Color(0, 102, 51));
		passField.setBounds(234, 182, 251, 36);
		panelBackground.add(passField);
		
		JLabel txtToRegister = new JLabel("Do you hava acount? Go to register.");
		txtToRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				RegisterForm regform= new RegisterForm();
				regform.setUndecorated(true);
				regform.setVisible(true);
				regform.setLocationRelativeTo(null);
				regform.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				uName = nameField.getText();
				regform.nameField.setText(uName);
				PosX = 100;
				PosY = 200;
				regform.updatePosition(PosX, PosY);
				dispose();
			}
		});
		txtToRegister.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.BLACK));
		txtToRegister.setForeground(Color.BLACK);
		txtToRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtToRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtToRegister.setBounds(230, 344, 256, 21);
		panelBackground.add(txtToRegister);
		
		JLabel txtUserName = new JLabel("User name:");
		txtUserName.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtUserName.setForeground(Color.BLACK);
		txtUserName.setBounds(67, 111, 107, 25);
		panelBackground.add(txtUserName);
		
		JLabel txtPass = new JLabel("Password:");
		txtPass.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPass.setForeground(Color.BLACK);
		txtPass.setBounds(67, 186, 93, 25);
		panelBackground.add(txtPass);
		passField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
	               btnLogin.doClick();
	               LoginValidation();
	            }
	        }

	    });
	}
	public void LoginValidation() {
		uName = nameField.getText();
		uPass = String.valueOf(passField.getPassword());
//		encode password with base64 encryption
		uPass = SecurePassword.Encoder(uPass);
		String sql = "SELECT * FROM Acounts Where User LIKE ? AND Pass LIKE?;";
		String sql2= "SELECT * FROM Acounts Where User LIKE ? AND Sudo LIKE?;";
		
//		System.out.println(uName +"\n"+ uPass);
		if(uName.length() > 3) {
			try {
                ps = con.prepareStatement(sql);
                ps.setString(1, uName);
                ps.setString(2, uPass);
                rs = ps.executeQuery();

                if(rs.next()) {
                	ps = con.prepareStatement(sql2);
                	ps.setString(1, uName);
                	ps.setString(2, "0");
                	rs = ps.executeQuery();
                	if(rs.next()) {
                		con.close();
        				DashboardUser dashboard= new DashboardUser();
        				dashboard.setUndecorated(false);
        				dashboard.setMinimumSize(new Dimension(700, 550));
        				dashboard.setVisible(true);
        				dashboard.setLocationRelativeTo(null);
        				dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        				PosX = 100;
        				PosY = 200;
//        				dashboard.updatePosition(PosX, PosY);
        				dispose();
                	}else {
                		con.close();
        				DashboardAdmin dashboard= new DashboardAdmin();
        				dashboard.setUndecorated(false);
        				dashboard.setMinimumSize(new Dimension(700, 550));
        				dashboard.setVisible(true);
        				dashboard.setLocationRelativeTo(null);
        				dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        				PosX = 100;
        				PosY = 200;
//        				dashboard.updatePosition(PosX, PosY);
        				dispose();
                	}
                }else {
                        System.out.println("sorry, you cant login");
                }
			}catch(SQLException Err) {
				System.out.println("Error " + Err);
			}
		}
	}
}
