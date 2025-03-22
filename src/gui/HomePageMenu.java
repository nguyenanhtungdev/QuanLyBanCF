package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import connectDB.ConnectDB;
import dao.chiTietHD_DAO;
import dao.hoaDon_DAO;
import dao.nhanVien_DAO;
import dao.sanPham_DAO;
import dao.taiKhoan_DAO;
import entity.ChiTietHD;
import entity.HoaDon1;
import entity.NhanVien;
import entity.QuanLyHoaDon;
import entity.SanPham;
import entity.TaiKhoan;
import main.DangNhap;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Insets;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class HomePageMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private HomePageTable pageTable = new HomePageTable();
	private PhieuTamTinh phieuTamTinh = new PhieuTamTinh();
	private HoaDon1 hoaDon = new HoaDon1();
	private QuanLySanPham quanLySanPham = new QuanLySanPham();
	private QuanLyDH quanLyDH = new QuanLyDH();
	private QuanLyNV quanLyNV = new QuanLyNV();
	private CaiDat caiDat = new CaiDat();
	private QuanLyNCC quanLyNCC = new QuanLyNCC();
	
	private JPanel contentPane;
	private JButton btnCaiDat, btnSoDo, btnMenu, btnTamTinh, btnMonAn, btnDoUong, btnCombo,
	btnHayDung, btnTim, btnQuanLy, btnCaidat;
	private JPanel panelCenter, panelMenu_Title, panelYeuThich, panelThongTin, panelChiTiet, panel_14, panelListSP, BanPane, panelTitile;
	private JTextField txtTim;
	private JLabel lblNewLabel_36,lblNewLabel_37,lblNewLabel_38,lblNewLabel_39,lblNewLabel_40, lblKhonTimThay;
	private JTable listDonHangTable, listMenuTable;
	private DefaultTableModel listDonHangModel, listMenuModel;
	private JPanel content = new JPanel(), danhSach = new JPanel();
	public CardLayout cardLayout = new CardLayout(); // Tham chiếu đến CardLayout
	public String title[] = {"menu","table","phieutam","donhuy","hoadon","caidat","qlSP","qlNV","qlTK","nhanVien","qlNCC"};
	//Khởi tạo lớp đối tượng
	private NhanVien nhanVien;
	private TaiKhoan taiKhoan;
	private SanPham sanPham = new SanPham();
	private HoaDon1 hoaDon1 = new HoaDon1();
	private ChiTietHD chiTietHD = new ChiTietHD();
	private QuanLyTK quanLyTK = new QuanLyTK();
	//Dao lay du lieu
	private taiKhoan_DAO taiKhoan_DAOs = new taiKhoan_DAO();
	private nhanVien_DAO nhanVien_DAOs = new nhanVien_DAO();
	private sanPham_DAO sanPham_DAOs = new sanPham_DAO();
	private hoaDon_DAO hoaDon_DAO = new hoaDon_DAO();
	private chiTietHD_DAO chiTietHD_DAO = new chiTietHD_DAO();
	
	private int choose = 1;
	private JPanel panelMenu;
	private JPanel listMenu;
	private JButton btnXoaSP; 
	private JButton btnHuySP;
	private JButton btnLuuSP;
	private JButton btnThanhToanSP;
	private int vitri = -1, STT = 1, viTriTrung, viTriXoa = -1;
	private JLabel lblTongTien1;
	private NumberFormat currencyFormat;
	private JButton btnThemSP;
	private Random ran = new Random();
	private int x = 100, y = 1000;
	private JLabel lblMaDon;
	private JLabel lblThoiGian;
	private JLabel lblNewLabel;
	private JComboBox comboHT;
	private String maBan;
	
 	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 HomePageMenu frame = new HomePageMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 	
	public HomePageMenu(String maBan) throws HeadlessException {
		this.maBan = maBan;
	}

	public HomePageMenu() {
		
		//Khởi tạo kết nối đến sql
		try {
			ConnectDB.getInstance().connect();
			System.out.println("Connected!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		setTitle("Phần Mềm Quản Lý Quán CF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1440, 880);
		setLocationRelativeTo(null);
//		setResizable(false);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//card layout
		content.setLayout(cardLayout);
		content.setBounds(0, 82, 1443, 736);
		contentPane.add(content);
		
		panelTitile = new JPanel();
		panelTitile.setBounds(0, 0, 1920, 82);
		panelTitile.setBackground(new Color(30,144,255));
		contentPane.add(panelTitile);
		panelTitile.setLayout(null);
		
		btnSoDo = new JButton("Sơ Đồ");
		btnSoDo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSoDo.setBackground(new Color(70,130,180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSoDo.setBackground(new Color(30,144,255));
			}
		});
		btnSoDo.setIcon(new ImageIcon("Image\\IconMap.png"));
		btnSoDo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(content,title[1]);
				content.setVisible(true);
			}
		});
		btnSoDo.setForeground(Color.WHITE);
		btnSoDo.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSoDo.setFocusPainted(false);
		btnSoDo.setBorderPainted(false);
		btnSoDo.setBackground(new Color(30, 144, 255));
		btnSoDo.setBounds(31, 24, 125, 37);
		panelTitile.add(btnSoDo);
		
		btnMenu = new JButton("Thực Đơn");
		btnMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMenu.setBackground(new Color(70,130,180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMenu.setBackground(new Color(30,144,255));
			}
		});
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(content,title[0]);
			}
		});
		btnMenu.setIcon(new ImageIcon("Image\\IconMenu.png"));
		btnMenu.setForeground(Color.WHITE);
		btnMenu.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnMenu.setFocusPainted(false);
		btnMenu.setBorderPainted(false);
		btnMenu.setBackground(new Color(30, 144, 255));
		btnMenu.setBounds(177, 24, 156, 37);
		panelTitile.add(btnMenu);
		
		btnTamTinh = new JButton("Phiếu Tạm Tính");
		btnTamTinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(content,title[2]);
			}
		});
		btnTamTinh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTamTinh.setBackground(new Color(70,130,180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnTamTinh.setBackground(new Color(30,144,255));
			}
		});
		btnTamTinh.setIcon(new ImageIcon("Image\\IconTamTinh.png"));
		btnTamTinh.setForeground(Color.WHITE);
		btnTamTinh.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTamTinh.setFocusPainted(false);
		btnTamTinh.setBorderPainted(false);
		btnTamTinh.setBackground(new Color(30, 144, 255));
		btnTamTinh.setBounds(354, 24, 212, 37);
		panelTitile.add(btnTamTinh);
		
		//Tạo Menu Item
		JMenuItem menuItem1 = new JMenuItem("Quản Lý Sản Phẩm");
		JMenuItem menuItem2 = new JMenuItem("Quản Lý Đơn Hàng");
		JMenuItem menuItem3 = new JMenuItem("Quản Lý Nhân Viên ");
		JMenuItem menuItem4 = new JMenuItem("Quản Lý Tài Khoản");
		JMenuItem menuItem7 = new JMenuItem("Quản Lý Nhà Cung Cấp");
		//Chỉnh font
		Font font = new Font("Arial", Font.BOLD, 17);
		menuItem1.setFont(font);
		menuItem2.setFont(font);
		menuItem3.setFont(font);
		menuItem4.setFont(font);
		menuItem7.setFont(font);
		
		//Tạo JPopupMenu
		JPopupMenu quanLy = new JPopupMenu();
		quanLy.add(menuItem1);
		quanLy.add(menuItem2);
		quanLy.add(menuItem3);
		quanLy.add(menuItem4);
		quanLy.add(menuItem7);
		
		 // Thêm ActionListener cho mỗi JMenuItem
        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				cardLayout.show(content,title[6]);
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				cardLayout.show(content,title[7]);
            }
        });

        menuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				cardLayout.show(content,title[8]);
            }
        });
		
        menuItem4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				cardLayout.show(content,title[9]);
            }
        });
        
        menuItem7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				cardLayout.show(content,title[10]);
            }
        });
        
		btnQuanLy = new JButton("Quản Lý");
		btnQuanLy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 quanLy.show(btnQuanLy, 0, btnQuanLy.getHeight());
			}
		});
		btnQuanLy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnQuanLy.setBackground(new Color(70,130,180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnQuanLy.setBackground(new Color(30,144,255));
			}
		});
		btnQuanLy.setIcon(new ImageIcon("Image\\Iconquanly.png"));
		btnQuanLy.setForeground(Color.WHITE);
		btnQuanLy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnQuanLy.setFocusPainted(false);
		btnQuanLy.setBorderPainted(false);
		btnQuanLy.setBackground(new Color(30, 144, 255));
		btnQuanLy.setBounds(588, 24, 146, 37);
		panelTitile.add(btnQuanLy);
		
		//Tạo Menu Item
		JMenuItem menuItem5 = new JMenuItem("Tài khoản");
		JMenuItem menuItem6 = new JMenuItem("Đăng Xuất");
		//Chỉnh font
		Font font1 = new Font("Arial", Font.BOLD, 16);
		menuItem5.setFont(font);
		menuItem6.setFont(font);
		//Tạo JPopupMenu
		JPopupMenu quanLy1 = new JPopupMenu();
		quanLy1.add(menuItem5);
		quanLy1.add(menuItem6);
		
		 // Thêm ActionListener cho mỗi JMenuItem
        menuItem5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//				cardLayout.show(content,title[6]);
            }
        });
		 // Thêm ActionListener cho mỗi JMenuItem
        menuItem6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	DangNhap dangNhap = new DangNhap();
            	JOptionPane.showMessageDialog(null, "Đăng xuất thành công!");
            	dangNhap.setVisible(true);
            	dispose();
            }
        });
		
		btnCaidat = new JButton("Cài Đặt");
		btnCaidat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quanLy1.show(btnCaidat, 0, btnCaidat.getHeight());
			}
		});
		btnCaidat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCaidat.setBackground(new Color(70,130,180));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCaidat.setBackground(new Color(30,144,255));
			}
		});
		btnCaidat.setIcon(new ImageIcon("Image\\Iconcaidat.png"));
		btnCaidat.setForeground(Color.WHITE);
		btnCaidat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCaidat.setFocusPainted(false);
		btnCaidat.setBorderPainted(false);
		btnCaidat.setBackground(new Color(30, 144, 255));
		btnCaidat.setBounds(765, 24, 135, 37);
		panelTitile.add(btnCaidat);
		
		panelCenter = new JPanel();
		panelCenter.setBounds(0, 82, 1443, 752);
		
		content.add(panelCenter,title[0]); //them giao dien menu
		panelCenter.setLayout(null);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelLeft.setForeground(Color.BLACK);
		panelLeft.setBounds(0, 0, 876, 736);
		panelCenter.add(panelLeft);
		panelLeft.setLayout(null);
		
		JPanel panelTimKiem = new JPanel();
		panelTimKiem.setBackground(new Color(220,220,220));
		panelTimKiem.setBounds(0, 75, 876, 53);
		panelLeft.add(panelTimKiem);
		panelTimKiem.setLayout(null);
		
		txtTim = new JTextField();
		txtTim.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (txtTim.getText().trim().equals("")) {
					//Them menu
			        panelMenu.removeAll(); // Xóa tất cả các thành phần hiện có trên panelLeft
			        choose = 1;
			        panelMenu.add(getListMenu()); // Thêm danh sách món ăn mới vào panelLeft
			        panelMenu.revalidate(); // Cập nhật lại giao diện
			        panelMenu.repaint();
				}
				else {
					//Them menu
			        panelMenu.removeAll(); // Xóa tất cả các thành phần hiện có trên panelLeft
			        choose = 5;
			        panelMenu.add(getListMenu()); // Thêm danh sách món ăn mới vào panelLeft
			        panelMenu.revalidate(); // Cập nhật lại giao diện
			        panelMenu.repaint();
				}
			}
		});
		txtTim.setToolTipText("Nhập từ khóa cần tìm kiếm");
		txtTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTim.setBounds(30, 10, 675, 32);
		panelTimKiem.add(txtTim);
		txtTim.setColumns(10);
		
		btnTim = new JButton("Tìm Kiếm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Them menu
		        panelMenu.removeAll(); // Xóa tất cả các thành phần hiện có trên panelLeft
		        choose = 5;
		        panelMenu.add(getListMenu()); // Thêm danh sách món ăn mới vào panelLeft
		        panelMenu.revalidate(); // Cập nhật lại giao diện
		        panelMenu.repaint();
			}
		});
		btnTim.setForeground(Color.BLACK);
		btnTim.setBackground(new Color(30,144,255));
		btnTim.setBorderPainted(false); // Loại bỏ đường viền cho button
		btnTim.setFocusPainted(false); // Loại bỏ đường viền khi button được focus
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnTim.setBounds(717, 11, 127, 32);
		panelTimKiem.add(btnTim);
		
		panelYeuThich = new JPanel();
		panelYeuThich.setBackground(Color.WHITE);
		panelYeuThich.setBounds(28, 11, 825, 53);
		panelLeft.add(panelYeuThich);
		panelYeuThich.setLayout(null);
		
		btnHayDung = new JButton("Hay Dùng");
		btnHayDung.setBounds(55, 11, 148, 32);
		panelYeuThich.add(btnHayDung);
		btnHayDung.setIcon(new ImageIcon("Image\\IconLike.png"));
		btnHayDung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Them menu
		        panelMenu.removeAll(); // Xóa tất cả các thành phần hiện có trên panelLeft
		        choose = 1;
		        panelMenu.add(getListMenu()); // Thêm danh sách món ăn mới vào panelLeft
		        panelMenu.revalidate(); // Cập nhật lại giao diện
		        panelMenu.repaint();
			}
		});
		btnHayDung.setForeground(new Color(70,130,180));
		btnHayDung.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHayDung.setFocusPainted(false);
		btnHayDung.setBorderPainted(false);
		btnHayDung.setBackground(Color.WHITE);
		
		btnMonAn = new JButton("Món Ăn");
		btnMonAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Them menu
		        panelMenu.removeAll(); // Xóa tất cả các thành phần hiện có trên panelLeft
		        choose = 2;
		        panelMenu.add(getListMenu()); // Thêm danh sách món ăn mới vào panelLeft
		        panelMenu.revalidate(); // Cập nhật lại giao diện
		        panelMenu.repaint();
			}
		});
		btnMonAn.setIcon(new ImageIcon("Image\\IconMonAn.png"));
		btnMonAn.setForeground(new Color(70, 130, 180));
		btnMonAn.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMonAn.setFocusPainted(false);
		btnMonAn.setBorderPainted(false);
		btnMonAn.setBackground(Color.WHITE);
		btnMonAn.setBounds(258, 11, 138, 32);
		panelYeuThich.add(btnMonAn);
		
		btnDoUong = new JButton("Đồ Uống");
		btnDoUong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Them menu
		        panelMenu.removeAll(); // Xóa tất cả các thành phần hiện có trên panelLeft
		        choose = 3;
		        panelMenu.add(getListMenu()); // Thêm danh sách món ăn mới vào panelLeft
		        panelMenu.revalidate(); // Cập nhật lại giao diện
		        panelMenu.repaint();
			}
		});
		btnDoUong.setIcon(new ImageIcon("Image\\IconDoUong.png"));
		btnDoUong.setForeground(new Color(70, 130, 180));
		btnDoUong.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDoUong.setFocusPainted(false);
		btnDoUong.setBorderPainted(false);
		btnDoUong.setBackground(Color.WHITE);
		btnDoUong.setBounds(451, 11, 138, 32);
		panelYeuThich.add(btnDoUong);
		
		btnCombo = new JButton("Combo");
		btnCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Them menu
		        panelMenu.removeAll(); // Xóa tất cả các thành phần hiện có trên panelLeft
		        choose = 4;
		        panelMenu.add(getListMenu()); // Thêm danh sách món ăn mới vào panelLeft
		        panelMenu.revalidate(); // Cập nhật lại giao diện
		        panelMenu.repaint();
			}
		});
		btnCombo.setIcon(new ImageIcon("Image\\IconCombo.png"));
		btnCombo.setForeground(new Color(70, 130, 180));
		btnCombo.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCombo.setFocusPainted(false);
		btnCombo.setBorderPainted(false);
		btnCombo.setBackground(Color.WHITE);
		btnCombo.setBounds(644, 11, 138, 32);
		panelYeuThich.add(btnCombo);
		
		panelMenu = new JPanel();
		panelMenu.setBackground(Color.WHITE);
		panelMenu.setBounds(0, 127, 876, 610);
		panelLeft.add(panelMenu);
		panelMenu.setLayout(null);
		
		//Them menu
		panelMenu.add(getListMenu());
		
		
		panelMenu_Title = new JPanel();
		panelMenu_Title.setBorder(new EmptyBorder(0, 1, 9, 1));
		panelMenu_Title.setBounds(60, 16, 1108, 53);
		panelMenu_Title.setLayout(null);
		panelMenu_Title.setBackground(Color.WHITE);
		
		JPanel panel_Right = new JPanel();
		panel_Right.setBackground(Color.LIGHT_GRAY);
		panel_Right.setBounds(876, 0, 568, 741);
		panelCenter.add(panel_Right);
		panel_Right.setLayout(null);
		
		panelThongTin = new JPanel();
		panelThongTin.setBounds(0, 0, 568, 108);
		panelThongTin.setBackground(new Color(245, 245, 245));
		panelThongTin.setBackground(Color.WHITE);
		panel_Right.add(panelThongTin);
		panelThongTin.setLayout(null);
		
		lblNewLabel_39 = new JLabel("Mã Đơn Hàng:");
		lblNewLabel_39.setBounds(10, 29, 121, 21);
		lblNewLabel_39.setForeground(Color.BLACK);
		lblNewLabel_39.setFont(new Font("Times New Roman", Font.BOLD, 18));
		panelThongTin.add(lblNewLabel_39);
		
		lblNewLabel_40 = new JLabel("Hình Thức:");
		lblNewLabel_40.setForeground(Color.BLACK);
		lblNewLabel_40.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_40.setBounds(10, 55, 103, 21);
		panelThongTin.add(lblNewLabel_40);
		
		JLabel lblNewLabel_40_1 = new JLabel("Thời Gian Tạo:");
		lblNewLabel_40_1.setForeground(Color.BLACK);
		lblNewLabel_40_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_40_1.setBounds(10, 80, 121, 21);
		panelThongTin.add(lblNewLabel_40_1);
		
		JLabel lblDonHang = new JLabel("Đơn Hàng");
		lblDonHang.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblDonHang.setBounds(232, 6, 134, 25);
		panelThongTin.add(lblDonHang);
		
		lblMaDon = new JLabel("");
		lblMaDon.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblMaDon.setBounds(134, 29, 148, 21);
		panelThongTin.add(lblMaDon);
		
		lblThoiGian = new JLabel("");
		lblThoiGian.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblThoiGian.setBounds(135, 80, 358, 21);
		panelThongTin.add(lblThoiGian);
		
		comboHT = new JComboBox();
		comboHT.setForeground(new Color(30, 144, 255));
		comboHT.setFont(new Font("Times New Roman", Font.BOLD, 17));
		comboHT.setModel(new DefaultComboBoxModel(new String[] {"Tại Quán", "Mang Về", "Đặt Trước "}));
		comboHT.setBounds(110, 53, 129, 25);
		panelThongTin.add(comboHT);
		
		panelChiTiet = new JPanel();
		panelChiTiet.setBounds(0, 107, 568, 43);
		panelChiTiet.setBackground(new Color(245, 245, 245));
		panel_Right.add(panelChiTiet);
		panelChiTiet.setLayout(null);
		
		lblNewLabel_36 = new JLabel("Tên Món");
		lblNewLabel_36.setForeground(Color.BLACK);
		lblNewLabel_36.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_36.setBounds(170, 8, 74, 28);
		panelChiTiet.add(lblNewLabel_36);
		
		lblNewLabel_37 = new JLabel("SL");
		lblNewLabel_37.setForeground(Color.BLACK);
		lblNewLabel_37.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_37.setBounds(390, 8, 35, 28);
		panelChiTiet.add(lblNewLabel_37);
		
		lblNewLabel_38 = new JLabel("Thành Tiền");
		lblNewLabel_38.setForeground(Color.BLACK);
		lblNewLabel_38.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_38.setBounds(455, 8, 100, 28);
		panelChiTiet.add(lblNewLabel_38);
		
		JLabel lblNewLabel_36_1 = new JLabel("Mã Món");
		lblNewLabel_36_1.setForeground(Color.BLACK);
		lblNewLabel_36_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_36_1.setBounds(57, 8, 74, 28);
		panelChiTiet.add(lblNewLabel_36_1);
		
		JLabel lblNewLabel_36_1_1 = new JLabel("STT");
		lblNewLabel_36_1_1.setForeground(Color.BLACK);
		lblNewLabel_36_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_36_1_1.setBounds(10, 8, 42, 28);
		panelChiTiet.add(lblNewLabel_36_1_1);
		
		lblNewLabel = new JLabel("Đơn Giá");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(295, 8, 74, 28);
		panelChiTiet.add(lblNewLabel);
		
		panel_14 = new JPanel();
		panel_14.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, new Color(0, 0, 255)));
		panel_14.setBackground(Color.LIGHT_GRAY);
		panel_14.setBounds(0, 669, 558, 69);
		panel_Right.add(panel_14);
		panel_14.setLayout(null);
		
		btnThemSP = new JButton("Thêm ");
		btnThemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addValuesDH();
			}
		});
		btnThemSP.setMargin(new Insets(2, 4, 2, 4));
		btnThemSP.setBackground(new Color(245, 245, 245));
		btnThemSP.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemSP.setIcon(new ImageIcon("Image/IconThem.png"));
		btnThemSP.setBounds(28, 17, 92, 37);
		btnThemSP.setFocusPainted(false);//Xóa lựa chọn
		btnThemSP.setBorderPainted(false);//Xóa border button
		panel_14.add(btnThemSP);
		
		btnXoaSP = new JButton("Xóa");
		btnXoaSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (viTriXoa >= 0 && viTriXoa < listDonHangTable.getRowCount()) {
					if (JOptionPane.showConfirmDialog(null,"Bạn có muốn xóa sản phẩm này ?","Xóa sản phẩm",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "Xóa sản phẩm thành công");
						listDonHangModel.removeRow(viTriXoa);
						updateSTTXoa();
					}
		        } else {
		            JOptionPane.showMessageDialog(null, "Vị trí xóa không hợp lệ!");
		        }
			}
		});
		btnXoaSP.setMargin(new Insets(2, 4, 2, 4));
		btnXoaSP.setBackground(new Color(245, 245, 245));
		btnXoaSP.setIcon(new ImageIcon("Image/IconXoa.png"));
		btnXoaSP.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoaSP.setFocusPainted(false);//Xóa lựa chọn
		btnXoaSP.setBorderPainted(false);//Xóa border button
		btnXoaSP.setBounds(170, 17, 92, 37);
		panel_14.add(btnXoaSP);
		
		btnHuySP = new JButton("Hủy");
		btnHuySP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listDonHangModel.setRowCount(0);
				lblTongTien1.setText(currencyFormat.format(tinhTongTien()));
				STT = 1;
			}
		});
		btnHuySP.setIcon(new ImageIcon("Image/IconHuy.png"));
		btnHuySP.setMargin(new Insets(2, 4, 2, 4));
		btnHuySP.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnHuySP.setBackground(new Color(245, 245, 245));
		btnHuySP.setBounds(302, 17, 92, 37);
		btnHuySP.setFocusPainted(false);//Xóa lựa chọn
		btnHuySP.setBorderPainted(false);//Xóa border button
		panel_14.add(btnHuySP);
		
		btnLuuSP = new JButton("Lưu");
		btnLuuSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listDonHangTable.getRowCount() > 0) {
					JOptionPane.showMessageDialog(null, "Tạo đơn hàng thành công!"); 
					themDuLieuVaoDH();// lưu tạm cái đơn hàng vào lớp đơn hàng
					xoaTrangDH();
					cardLayout.show(content,title[2]);
				}
				else {
					JOptionPane.showMessageDialog(null, "Tạo đơn hàng thất bại!"); 					
				}
			}
		});
		btnLuuSP.setIcon(new ImageIcon("Image/IconXong.png"));
		btnLuuSP.setMargin(new Insets(2, 4, 2, 4));
		btnLuuSP.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLuuSP.setBackground(new Color(245, 245, 245));
		btnLuuSP.setBounds(438, 17, 92, 37);
		btnLuuSP.setFocusPainted(false);//Xóa lựa chọn
		btnLuuSP.setBorderPainted(false);//Xóa border button
		panel_14.add(btnLuuSP);
		
//		btnThanhToanSP = new JButton("Thanh Toán");
//		btnThanhToanSP.setMargin(new Insets(2, 4, 2, 4));
//		btnThanhToanSP.setFont(new Font("Tahoma", Font.BOLD, 13));
//		btnThanhToanSP.setBackground(new Color(245, 245, 245));
//		btnThanhToanSP.setBounds(430, 17, 111, 37);
//		btnThanhToanSP.setFocusPainted(false);//Xóa lựa chọn
//		btnThanhToanSP.setBorderPainted(false);//Xóa border button
//		panel_14.add(btnThanhToanSP);
		
		panelListSP = new JPanel();
		panelListSP.setBackground(Color.WHITE);
		panelListSP.setBounds(0, 150, 558, 478);
		panel_Right.add(panelListSP);
		panelListSP.setLayout(new BorderLayout(0, 0));
	
		panelListSP.add(getTableDH(), BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 626, 558, 43);
		panel_Right.add(panel);
		panel.setLayout(null);
		
		JLabel lblTongTien = new JLabel("Tổng Tiền:");
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongTien.setBounds(310, 9, 103, 27);
		panel.add(lblTongTien);
		
		lblTongTien1 = new JLabel("0đ");
		lblTongTien1.setForeground(Color.RED);
		lblTongTien1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTongTien1.setBounds(423, 12, 120, 23);
		panel.add(lblTongTien1);
		khoiTaoDH();
		themCard(); //Thêm phần tử vào card
	}
	//Trả về giao diện
	public JPanel getPanelMenu() {
		return panelCenter;
	}
	//Thêm phần tử vào cardlayout, lưu ý muốn design được thì hủy gọi hàm này đi
	public void themCard() {
//		content.add(panelMenu,title[0]); //để nguyên đó không sửa dòng này
		content.add(pageTable.getBanPane(),title[1]);
		content.add(phieuTamTinh.getPhieuTamTinh(),title[2]);
		content.add(caiDat.getCaiDat(),title[5]);
		content.add(quanLySanPham.getQuanLySP(),title[6]);
		content.add(quanLyDH.getQuanLyDH(),title[7]);
		content.add(quanLyNV.getQuanLyNV(),title[8]);
		content.add(quanLyTK.getQuanLyTK(),title[9]);
		content.add(quanLyNCC.getQuanLyNCC(),title[10]);
	}
	
	public ArrayList<SanPham> timKiemReGexSP(String s){
		ArrayList<SanPham> listSp = new ArrayList<SanPham>();
		
		String regex = ".*" + s.toUpperCase() + ".*";
		
		for(SanPham sp : sanPham_DAOs.getalltbSanPham()) {
			if(sp.getMaSP().toUpperCase().matches(regex)) {
				listSp.add(sp);
				continue;
			}
			else if(sp.getTenSP().toUpperCase().matches(regex)) {
				listSp.add(sp);
				continue;
			}
		}
		
		return listSp;
	}
	
	//Lấy danh sách sản phẩm đưa vào table 
	public JPanel getListMenu() {
	    List<SanPham> sanPhams = sanPham_DAOs.getalltbSanPham();

	    listMenu = new JPanel();
	    listMenu.setBounds(0, 0, 876, 610);
	    listMenu.setLayout(null);
	    listMenu.add(getTableMenu());
	    listMenu.setBackground(Color.WHITE);
	    
	    if(choose == 1) {
		    for (SanPham sp : sanPhams) {
		        if(sp.getTrangThai().equals("Còn bán")) {
		            ImageIcon imageIcon = new ImageIcon(sp.getHinhAnh().trim());
		            Object[] rowData = {imageIcon, sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP(), sp.getDonGia(),
		                    sp.getMoTa(), sp.getTrangThai()};
		            listMenuModel.addRow(rowData);
		        }
		    }
	    }
	    else if(choose == 2) {
		    for (SanPham sp : sanPhams) {
		        if(sp.getTrangThai().equals("Còn bán") && sp.getLoaiSP().equals("Đồ ăn nhẹ")) {
		            ImageIcon imageIcon = new ImageIcon(sp.getHinhAnh().trim());
		            Object[] rowData = {imageIcon, sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP(), sp.getDonGia(),
		                    sp.getMoTa(), sp.getTrangThai()};
		            listMenuModel.addRow(rowData);
		        }
		    }
	    }
	    else if(choose == 3) {
		    for (SanPham sp : sanPhams) {
		        if(sp.getTrangThai().equals("Còn bán") && sp.getLoaiSP().equals("Đồ uống")) { 
		            ImageIcon imageIcon = new ImageIcon(sp.getHinhAnh().trim());
		            Object[] rowData = {imageIcon, sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP(), sp.getDonGia(),
		                    sp.getMoTa(), sp.getTrangThai()};
		            listMenuModel.addRow(rowData);
		        }
		    }
	    }
	    else if(choose == 4) {
		    for (SanPham sp : sanPhams) {
		        if(sp.getTrangThai().equals("Còn bán") && sp.getLoaiSP().equals("Combo")) { 
		            ImageIcon imageIcon = new ImageIcon(sp.getHinhAnh().trim());
		            Object[] rowData = {imageIcon, sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP(), sp.getDonGia(),
		                    sp.getMoTa(), sp.getTrangThai()};
		            listMenuModel.addRow(rowData);
		        }
		    }
	    }
	    else if(choose == 5) {
		    for (SanPham sp : timKiemReGexSP(txtTim.getText().trim())) {
		        if(sp.getTrangThai().equals("Còn bán")) { 
		            ImageIcon imageIcon = new ImageIcon(sp.getHinhAnh().trim());
		            Object[] rowData = {imageIcon, sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP(), sp.getDonGia(),
		                    sp.getMoTa(), sp.getTrangThai()};
		            listMenuModel.addRow(rowData);
		        }
		    }
	    }
	    
	    return listMenu;
	}
	
    public JScrollPane getTableMenu(){
        String[] columns = {"Ảnh", "Mã SP", "Tên SP", "Loại SP", "Đơn giá", "Mô tả", "Trạng thái"};
        listMenuModel = new DefaultTableModel(columns, 0) {
        	@Override
        	public boolean isCellEditable(int row,int column) {
        		return false;
        	}
        };
        listMenuTable = new JTable(listMenuModel);
        listMenuTable.getTableHeader().setReorderingAllowed(false); // Không cho phép di chuyển vị trí cột
        listMenuTable.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        	}
        	@Override
        	public void mousePressed(MouseEvent e) {
        		vitri = listMenuTable.getSelectedRow();
        	}
        });
        listMenuTable.setBackground(Color.WHITE);
        listMenuTable.setRowHeight(100);
        listMenuTable.setIntercellSpacing(new Dimension(0, 0)); //ẩn các viền theo từng cột
        listMenuTable.setShowGrid(false); //ẩn các viền border của table
        listMenuTable.setGridColor(Color.BLACK); // Set grid color to black
        // Căn giữa chữ trong table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        listMenuTable.setDefaultRenderer(Object.class, centerRenderer);//Áp dụng cho table
	    // Cập nhật cái cột đầu tiên để hiển thị được hình ảnh
        listMenuTable.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer(100));
        //Chỉnh size chữ cho hearder
        JTableHeader header = listMenuTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD,16));
        header.setPreferredSize(new Dimension(header.getWidth(),30));
        //Chỉnh size chữ cho các dòng
        listMenuTable.setFont(new Font("Arial", ABORT,14));
        //Chỉnh size cho cái chi tiết
        TableColumn columnChiTiet = listMenuTable.getColumnModel().getColumn(5);
        columnChiTiet.setPreferredWidth(170);
        //Chỉnh size cho cái ảnh
        TableColumn columnAnh = listMenuTable.getColumnModel().getColumn(0);
        columnAnh.setPreferredWidth(100);
        // Hiển thị gạch dưới
        listMenuTable.setShowHorizontalLines(true);
        //Chỉnh màu chữ của cột, và xóa border cho từng cột
        listMenuTable.getColumnModel().getColumn(1).setCellRenderer(new CustomColorRenderer());
        listMenuTable.getColumnModel().getColumn(2).setCellRenderer(new CustomColorRenderer());
        listMenuTable.getColumnModel().getColumn(3).setCellRenderer(new CustomColorRenderer());
        listMenuTable.getColumnModel().getColumn(4).setCellRenderer(new CustomColorRenderer());
        listMenuTable.getColumnModel().getColumn(5).setCellRenderer(new CustomColorRenderer());
        listMenuTable.getColumnModel().getColumn(6).setCellRenderer(new CustomColorRenderer());
        
        listMenuTable.getColumnModel().getColumn(2).setPreferredWidth(110);
        
	    JScrollPane scrollPaneMenu = new JScrollPane(listMenuTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPaneMenu.setBounds(0, 0, 877, 608);
	    scrollPaneMenu.setBackground(Color.WHITE);
        return scrollPaneMenu;
    }
    
    public JScrollPane getTableDH() {
    	listDonHangTable = new JTable();
    	listDonHangTable.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mousePressed(MouseEvent e) {
    			viTriXoa = listDonHangTable.getSelectedRow();
    		}
    	});
    	listDonHangTable.setBackground(Color.WHITE);
    	listDonHangTable.setFont(new Font("Times New Roman", Font.PLAIN, 19));
    	listDonHangTable.getTableHeader().setReorderingAllowed(false); // Không cho phép di chuyển vị trí cột
		listDonHangModel =  new DefaultTableModel(0,6) {
        	@Override
        	public boolean isCellEditable(int row,int column) {
        		return column == 4; // chỉ cho phép cột số 4 chỉnh sửa, còn lại không được phép
        	}
		};
		
		listDonHangTable.setModel(listDonHangModel);
		listDonHangTable.setTableHeader(null); //Xóa dòng tiêu đề
		listDonHangTable.getColumnModel().getColumn(0).setPreferredWidth(50); 
		listDonHangTable.getColumnModel().getColumn(1).setPreferredWidth(60); 
		listDonHangTable.getColumnModel().getColumn(2).setPreferredWidth(160);
		listDonHangTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		listDonHangTable.getColumnModel().getColumn(5).setPreferredWidth(110);
		listDonHangTable.setRowHeight(30);//Chỉnh chiều cao tất cả các dòng trong table
		listDonHangTable.setIntercellSpacing(new java.awt.Dimension(0, 0)); //Xóa border của các cột và dòng
		listDonHangTable.setShowGrid(false);
		listDonHangTable.setGridColor(java.awt.Color.WHITE);
		
		//Căn giữa chữ
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumn column = listDonHangTable.getColumnModel().getColumn(0);
		column.setCellRenderer(cellRenderer); //áp dụng cho căn gữa cột
		
		//Chỉnh màu chữ của cột, và xóa border cho từng cột
        listDonHangTable.getColumnModel().getColumn(0).setCellRenderer(new CustomColorRenderer1());
        listDonHangTable.getColumnModel().getColumn(1).setCellRenderer(new CustomColorRenderer1());
        listDonHangTable.getColumnModel().getColumn(2).setCellRenderer(new CustomColorRenderer1());
        listDonHangTable.getColumnModel().getColumn(3).setCellRenderer(new CustomColorRenderer1());
        listDonHangTable.getColumnModel().getColumn(4).setCellRenderer(new CustomColorRenderer1());
        listDonHangTable.getColumnModel().getColumn(5).setCellRenderer(new CustomColorRenderer1());
        
        createQuantityColumnRenderer(); // Tạo renderer cho cột số lượng
        
        // Tạo một đối tượng NumberFormat cho tiền tệ của Việt Nam
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Loại bỏ các số 0 không cần thiết ở cuối cùng
        currencyFormat.setMaximumFractionDigits(0);
        
		JScrollPane scrollPane = new JScrollPane(listDonHangTable);
		return scrollPane;
    }
    public class CustomTableModel extends DefaultTableModel {
        private boolean[] editableColumns; 

        // Constructor
        public CustomTableModel(Object[][] data, Object[] columnNames, boolean[] editableColumns) {
            super(data, columnNames);
            this.editableColumns = editableColumns;
        }

        // Override isCellEditable to control which cells are editable
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return editableColumns[columnIndex]; // Trả về true nếu cột được chỉ định là có thể chỉnh sửa, ngược lại trả về false
        }
    }
 // Tạo lớp tùy chỉnh cho ô có nút tăng giảm
    class SpinnerEditor extends DefaultCellEditor {
        private JSpinner spinner;

        public SpinnerEditor() {
            super(new JTextField());
            spinner = new JSpinner();
            spinner.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub`
				}
			});
            spinner.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1)); // Giá trị mặc định và khoảng giá trị của spinner
            editorComponent = spinner;
            delegate = new DefaultCellEditor.EditorDelegate() {
                public void setValue(Object value) {
                    spinner.setValue(value);
                }

                public Object getCellEditorValue() {
                    return spinner.getValue();
                }
            };
        }
    }
    
    // Tạo renderer cho cột số lượng
    public void createQuantityColumnRenderer() {
        TableColumn column = listDonHangTable.getColumnModel().getColumn(4);
        column.setCellEditor(new SpinnerEditor()); // Sử dụng lớp tùy chỉnh cho ô có nút tăng giảm
    }
    
    // Lấy dữ liệu của table khi được chọn
    public Object[] getValuesTable(int index) {
    	Object ob[] = {
    		listMenuTable.getValueAt(index, 1),
    		listMenuTable.getValueAt(index, 2),
    		listMenuTable.getValueAt(index, 3),
    		listMenuTable.getValueAt(index, 4),
    		listMenuTable.getValueAt(index, 5),
    		listMenuTable.getValueAt(index, 6),
    	}; 
    	return ob;
    }
 // Thêm dữ liệu vào Đơn hàng
    public void addValuesDH() {
        if (vitri != -1) {
        	int sl = 1;
            Object ob[] = getValuesTable(vitri);
            Object[] temp = new Object[6];
            temp[0] = STT;
            temp[1] = ob[0];
            temp[2] = ob[1];
            temp[3] = ob[3];
            temp[4] = sl; // Giá trị mặc định là 1
            temp[5] = ob[3];
            if (listDonHangModel.getRowCount() > 0 && isProductExistsInOrder(ob[0])) {
            	int currentQuantity = (int) listDonHangTable.getValueAt(viTriTrung, 4);
            	listDonHangTable.setValueAt(++currentQuantity, viTriTrung, 4);
            	listDonHangTable.setValueAt(tinhThanhTien(currentQuantity,(Double)ob[3]),viTriTrung, 5);
            	
            } else {
            	++STT;
                listDonHangModel.addRow(temp); // Thêm hàng mới nếu sản phẩm chưa tồn tại trong đơn hàng
            }
        	lblTongTien1.setText(currencyFormat.format(tinhTongTien()));
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
        }
    }

    private boolean isProductExistsInOrder(Object product) {
    	for(int i=0;i<listDonHangModel.getRowCount();i++) {
    		if(listDonHangModel.getValueAt(i, 1).equals(product)) {
    			viTriTrung = i;
    			return true;
    		}
    	}
    	return false;
    }
	//DefaultTableModel không thể chứa các đối tượng không phải là các kiểu dữ liệu cơ bản như String, Integer, v.v. Nên dùng hàm này mới chứa được
    public class ImageRenderer extends DefaultTableCellRenderer {
        private int imageHeight;

        public ImageRenderer(int imageHeight) {
            this.imageHeight = imageHeight;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel();
            if (value instanceof ImageIcon) {
                ImageIcon originalIcon = (ImageIcon) value;
                // Thay đổi kích thước của hình ảnh
                Image scaledImage = originalIcon.getImage().getScaledInstance(-1, imageHeight, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaledImage));
            }
            label.setHorizontalAlignment(JLabel.CENTER);
            return label;
        }
    }
    //Chỉnh màu chữ của cột cố định, chỉnh không được phép hiện border cột chỉ hiển border dòng  cho table menu
    public class CustomColorRenderer extends DefaultTableCellRenderer {
        // Lưu trữ index của cột đang được chọn
        private int selectedColumnIndex = -1;

        public void setSelectedColumnIndex(int columnIndex) {
            selectedColumnIndex = columnIndex;
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if(column == 1) {
            	cellComponent.setForeground(new Color(30, 144, 255));
            	cellComponent.setFont(new Font("Arial",Font.BOLD, 16));
            }
            else if(column == 4) {
            	cellComponent.setForeground(Color.RED);
            	cellComponent.setFont(new Font("Arial",Font.BOLD, 14));
            }
         // Kiểm tra xem cột hiện tại có phải là cột được chọn hay không
            if (column == selectedColumnIndex) {
                // Nếu là cột được chọn, thì không hiển thị border
                ((JComponent) cellComponent).setBorder(BorderFactory.createEmptyBorder());
            } else {
                // Nếu không, hiển thị border mặc định
                ((JComponent) cellComponent).setBorder(UIManager.getBorder("Table.cellBorder"));
            }
            ((DefaultTableCellRenderer) cellComponent).setHorizontalAlignment(SwingConstants.CENTER);
            return cellComponent;
        }
    }
    
    public class CustomColorRenderer1 extends DefaultTableCellRenderer {
        // Lưu trữ index của cột đang được chọn
        private int selectedColumnIndex = -1;

        public void setSelectedColumnIndex(int columnIndex) {
            selectedColumnIndex = columnIndex;
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            if(column == 1) {
            	cellComponent.setForeground(new Color(30, 144, 255));
            	cellComponent.setFont(new Font("Arial",Font.BOLD, 16));
            }
            else if(column == 5) {
            	cellComponent.setForeground(Color.RED);
            	cellComponent.setFont(new Font("Arial",Font.BOLD, 16));
            }
         // Kiểm tra xem cột hiện tại có phải là cột được chọn hay không
            if (column == selectedColumnIndex) {
                // Nếu là cột được chọn, thì không hiển thị border
                ((JComponent) cellComponent).setBorder(BorderFactory.createEmptyBorder());
            } else {
                // Nếu không, hiển thị border mặc định
                ((JComponent) cellComponent).setBorder(UIManager.getBorder("Table.cellBorder"));
            }
            ((DefaultTableCellRenderer) cellComponent).setHorizontalAlignment(SwingConstants.CENTER);
            return cellComponent;
        }
    }
    public double tinhThanhTien(int sl, double tienSP) {
    	return sl*tienSP;
    }
    
    //Cập nhật stt và tổng tiền khi xóa 1 dòng 
    public void updateSTTXoa(){
    	if (viTriXoa >= 0) {
    		STT--;
    		lblTongTien1.setText(currencyFormat.format(tinhTongTien())); 
            for(int i = viTriXoa;i< listDonHangTable.getRowCount();i++) {
            	int sttGiam = i + 1;
            	listDonHangTable.setValueAt(sttGiam, i, 0); // Cập nhật giá trị mới cho hàng viTriXoa            	
            }
        } else {
        	System.out.println(viTriXoa);
        	System.out.println(listDonHangTable.getRowCount());
            JOptionPane.showMessageDialog(this, "Vị trí xóa không hợp lệ!");
        }
    }
    
    public double tinhTongTien() {
    	double tong = 0;
    	for(int i = 0;i < listDonHangTable.getRowCount(); i++) {
    		tong += Double.parseDouble(listDonHangModel.getValueAt(i, 5).toString());
    	}
    	return tong;
    }
    //Chưa triển khai 
    public void setTrangThaiButton(JButton btn) {
		btn.setFocusPainted(false);//Xóa lựa chọn
		btn.setBorderPainted(false);//Xóa border button
    }
    //Khởi tạo đơn hàng
    public void khoiTaoDH() {
		int randomNumber = ran.nextInt(y - x + 1) + x;
		lblMaDon.setText("HD"+randomNumber);
		lblThoiGian.setText(formattime());
    }
    //Định dạng ngày giờ hiện tại 
    public	String formattime() {
    	LocalDateTime time = LocalDateTime.now();
		//Định dạng lại về thời gian theo kiểu việt nam
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.forLanguageTag("vi-VN"));
		return time.format(formatter);
    }
  //Thêm dữ liệu vào database 
    public void themDuLieuVaoDH() {
    	// Giả sử lblThoiGian.getText() trả về một chuỗi đại diện cho ngày tháng, bạn cần chuyển đổi nó thành LocalDate.
    	LocalDateTime ngayLapHoaDon = LocalDateTime.now();
    	// Khởi tạo đối tượng HoaDon1 với các tham số phù hợp
    	NhanVien nv = new NhanVien("NV006");
    	hoaDon1 = new HoaDon1(lblMaDon.getText(), ngayLapHoaDon, "Không", null, nv, null, 10, "Tiền mặt", "", (String) comboHT.getSelectedItem());
    	for(int i=0;i<listDonHangModel.getRowCount();i++) {
    		String maMon = (String)listDonHangTable.getValueAt(i, 1);
    		String tenMon = (String) listDonHangTable.getValueAt(i, 2);
    		double donGia = (Double) listDonHangTable.getValueAt(i, 3);
    		int sl = (Integer) listDonHangTable.getValueAt(i, 4);
    		ChiTietHD chiTietHD = new ChiTietHD(lblMaDon.getText(),maMon, donGia, sl);
    		chiTietHD_DAO.addChiTietHD(chiTietHD);
    		hoaDon1.themChiTietHD(chiTietHD);
        }
    	hoaDon_DAO.addHoaDon(hoaDon1);
    }
    //Xóa trắng và random lại
    public void xoaTrangDH() {
    	lblMaDon.setText("");
    	lblThoiGian.setText("");
    	comboHT.setSelectedIndex(0);
    	STT = 1;
    	lblTongTien1.setText("");
    	listDonHangModel.setRowCount(0);
    	khoiTaoDH();
    }
}