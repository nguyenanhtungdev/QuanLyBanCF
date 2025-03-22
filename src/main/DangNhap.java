package main;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import connectDB.ConnectDB;
import dao.nhanVien_DAO;
import dao.taiKhoan_DAO;
import dao.nhanVien_DAO;
import dao.taiKhoan_DAO;
import gui.HomePageMenu;
import gui.MaHoa;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.Button;

public class DangNhap extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField textPass;
	private JCheckBox chckbxPass;
	private Button btnDN;
	private Button btnThoat;
	private taiKhoan_DAO tk_dao;
	private nhanVien_DAO nv_dao;
	
	public DangNhap() { 
		try {
            ConnectDB.getInstance().connect();
//            System.out.println("Connect!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		tk_dao = new taiKhoan_DAO();
		nv_dao = new nhanVien_DAO();
		
		tk_dao.getalltbTaiKhoan();
		nv_dao.getalltbNhanVien();
		
		setTitle("Đăng nhập");
		setFont(new Font("Dialog", Font.PLAIN, 77));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setSize(1059, 747);
		setResizable(false);
		this.setIconImage(new ImageIcon("image/logo.png").getImage());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(173, 173, 173, 173);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel background = new JPanel();
		background.setBounds(10, 0, 500, 700);
		contentPane.add(background);
		
		JLabel lblBg = new JLabel("");
		background.add(lblBg);
		lblBg.setIcon(new ImageIcon("image\\bg-form.jpg"));
		lblBg.setPreferredSize(new Dimension(500, 700));
		
		JPanel formLogin = new JPanel();
		formLogin.setBounds(520, 0, 512, 700);
		formLogin.setBackground(new Color(255, 255, 255));
		contentPane.add(formLogin);
		formLogin.setLayout(null);
		
		JLabel lblTitle = new JLabel("ĐĂNG NHẬP");
		lblTitle.setBounds(169, 127, 220, 43);
		formLogin.add(lblTitle);
		lblTitle.setToolTipText("");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblTitle.setForeground(new Color(160, 82, 45));
		
		JLabel lblUsername = new JLabel("Mã nhân viên:"); 
		lblUsername.setBounds(28, 223, 153, 25);
		formLogin.add(lblUsername);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 21));
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Arial", Font.PLAIN, 18));
		textUsername.setBounds(191, 219, 297, 33);
		formLogin.add(textUsername);
		textUsername.setColumns(10);
		
		// Di chuyển qua txt khác
		textUsername.addActionListener(e -> SwingUtilities.invokeLater(() -> textPass.requestFocus()));
		
		JLabel lblPass = new JLabel("Mật khẩu:");
		lblPass.setBounds(28, 282, 110, 25);
		formLogin.add(lblPass);
		lblPass.setFont(new Font("Arial", Font.PLAIN, 21));
		
		textPass = new JPasswordField();
		textPass.setFont(new Font("Arial", Font.PLAIN, 18));
		textPass.setColumns(10);
		textPass.setBounds(191, 278, 297, 33);
		formLogin.add(textPass);
		
		// enter để đăng ký
		textPass.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		        	 ActionEvent event = new ActionEvent(btnDN, ActionEvent.ACTION_PERFORMED, "");
		             for (ActionListener listener : btnDN.getActionListeners()) {
		                 listener.actionPerformed(event);
		             }
		        }
		    }
		});
		
		chckbxPass = new JCheckBox("Hiện mật khẩu");
		chckbxPass.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxPass.setForeground(new Color(0, 0, 0));
		chckbxPass.setBackground(new Color(255, 255, 255));
		chckbxPass.setFont(new Font("Arial", Font.PLAIN, 17));
		chckbxPass.setBounds(188, 322, 146, 21);
		formLogin.add(chckbxPass);
		
		btnDN = new Button("Đăng nhập");
		btnDN.setFont(new Font("Arial", Font.PLAIN, 20));
		btnDN.setForeground(new Color(255, 255, 255));
		btnDN.setBackground(new Color(153, 51, 0));
		btnDN.setBounds(109, 390, 146, 52);
		formLogin.add(btnDN);
		
		btnThoat = new Button("Thoát");
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setFont(new Font("Arial", Font.PLAIN, 20));
		btnThoat.setBackground(new Color(204, 153, 102));
		btnThoat.setBounds(271, 390, 146, 52);
		formLogin.add(btnThoat);
		
		// đăng ký sự kiện
		
		chckbxPass.addActionListener(this);
		
		btnDN.addActionListener(this);
	
		setLocationRelativeTo(null);
	}
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {

		// hiện và không hiện mật khẩu
		if (chckbxPass.isSelected()) {
			textPass.setEchoChar((char)0);
		} else {
			textPass.setEchoChar((char)8226);
		}
		
		Object o = e.getSource();
		
		if (o.equals(btnDN)) {
			String maNV = textUsername.getText();
			char[] pass = textPass.getPassword();
			String matKhau = new String(pass);
			
			if (maNV.equals("") || matKhau.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
				return;
			}
				
			matKhau = MaHoa.toSHA1(matKhau);
			
//			if (tk_dao.kiemTraTK(maNV, matKhau)) {
//				JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
//				dispose();
//				HomePageMenu frame = new HomePageMenu();
//				frame.setVisible(true);
//			}
//			else {
//				JOptionPane.showMessageDialog(this, "Mật khẩu hoặc tài khoản không chính xác!");
//				textUsername.requestFocus();
//				textUsername.selectAll();
//			}
			dispose();
			HomePageMenu frame = new HomePageMenu();
			frame.setVisible(true);
			
		}
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}


