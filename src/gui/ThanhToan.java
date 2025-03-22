package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.itextpdf.text.DocumentException;

import connectDB.ConnectDB;
import dao.hoaDon_DAO;
import dao.khachHang_DAO;
import entity.HoaDon1;
import entity.KhachHang;


public class ThanhToan extends JFrame {
	
	public static ThanhToan thanhToan = new ThanhToan();
	private String maHoaDon;
	private String thoiGian;
	private double tongTien;
	private double tongThanhToan;
	private final float thue = (float)0.1;
	private double tienVip = 0;
	private double tienThue = 0;
	private float giamNuoc = 0;
	private double tienGiam = 0;
	
	private static final long serialVersionUID = 1L;
	private JTextField txtDT, txtMa, txtTenTV, txtHangTV, txtThue;
	private JButton btnTimMa, btnInTamTinh, btnThanhToan, btnQuayLai;
	private JButton btnKhuyenMaiKhac;
	private JLabel lbl1, lbl2 ,lbl3 ,lbl4, lblTitileBan, lblTitileTG, lblTongTien, lblThue, lblTongTT;
	private JPanel panelTable;
	private JTable listDonHangTable;
	private DefaultTableModel listDonHangModel;
	private NumberFormat currencyFormat;
	private khachHang_DAO khachHang_DAO = new khachHang_DAO();
	private boolean isClicked = false,isClicked2 = false,isClicked3 = false,isClicked4 = false, isClicked5 = false;
	
	public ThanhToan() {
		
	}
	
	public static ThanhToan getInstance() {
		return thanhToan;
	}
	
	public ThanhToan(String maHoaDon, String thoiGian, double tongTien, double tongThanhToan) throws HeadlessException {
		super();
		this.maHoaDon = maHoaDon;
		this.thoiGian = thoiGian;
		this.tongTien = tongTien;
		this.tongThanhToan = tongThanhToan;
	}
	
	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public double getTongThanhToan() {
		return tongThanhToan;
	}

	public void setTongThanhToan(double tongThanhToan) {
		this.tongThanhToan = tongThanhToan;
	}

	public double getTienVip() {
		return tienVip;
	}

	public void setTienVip(double tienVip) {
		this.tienVip = tienVip;
	}

	//Trả về giao diện của phiếu tạm tính
  	public void getThanhToan(HoaDon1 hoaDon1) {
        // Tạo một đối tượng NumberFormat cho tiền tệ của Việt Nam
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Loại bỏ các số 0 không cần thiết ở cuối cùng
        currencyFormat.setMaximumFractionDigits(0);
  		
  	    JFrame frame = new JFrame();
  	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	    frame.setBounds(100, 100, 1243, 825);
  	    frame.setLocationRelativeTo(null);
  	    frame.setTitle("Tính tiền");
  	    frame.setResizable(false);
  	    
  	    JPanel contentPane = new JPanel();
  	    contentPane.setBackground(Color.GRAY);
  	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  	    contentPane.setLayout(new BorderLayout()); // Sử dụng BorderLayout thay vì null layout
  	    frame.setContentPane(contentPane);
  		contentPane.setLayout(null);
  		
  		JPanel panel = new JPanel();
  		panel.setBackground(Color.WHITE);
  		panel.setBounds(0, 0, 490, 790);
  		contentPane.add(panel);
  		panel.setLayout(null);
  		
  		JPanel panel_1 = new JPanel();
  		panel_1.setBounds(0, 0, 490, 44);
  		panel.add(panel_1);
  		panel_1.setLayout(null);
  		
  		JLabel lblThanhVien = new JLabel("Thẻ Thành Viên CofferCenter");
  		lblThanhVien.setIcon(new ImageIcon("Image/IconTheTV.png"));
  		lblThanhVien.setFont(new Font("Tahoma", Font.BOLD, 16));
  		lblThanhVien.setBounds(10, 9, 277, 25);
  		lblThanhVien.setForeground(Color.decode("#0973B9"));
  		panel_1.add(lblThanhVien);
  		
  		JLabel lblDT = new JLabel("Số điện thoại");
  		lblDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
  		lblDT.setBounds(35, 71, 113, 25);
  		panel.add(lblDT);
  		
  		txtDT = new JTextField();
  		txtDT.setFont(new Font("Tahoma", Font.PLAIN, 15));
  		txtDT.setBounds(175, 68, 212, 30);
  		panel.add(txtDT);
  		txtDT.setColumns(10);
  		
		txtMa = new JTextField();
		txtMa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtMa.setEditable(false);
		txtMa.setColumns(10);
		txtMa.setBounds(175, 114, 274, 30);
		panel.add(txtMa);
		
		JLabel lblTenMa = new JLabel("Mã thành viên");
		lblTenMa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenMa.setBounds(35, 116, 113, 25);
		panel.add(lblTenMa);
  		
  		JLabel lblTenTV = new JLabel("Tên thành viên");
  		lblTenTV.setFont(new Font("Tahoma", Font.PLAIN, 16));
  		lblTenTV.setBounds(35, 161, 113, 25);
  		panel.add(lblTenTV);
  		
  		txtTenTV = new JTextField();
  		txtTenTV.setEditable(false);
  		txtTenTV.setFont(new Font("Tahoma", Font.PLAIN, 15));
  		txtTenTV.setColumns(10);
  		txtTenTV.setBounds(175, 159, 274, 30);
  		txtTenTV.setFocusable(false);
  		panel.add(txtTenTV);
  		
  		JLabel lblHangThe = new JLabel("Hạng thẻ");
  		lblHangThe.setFont(new Font("Tahoma", Font.PLAIN, 16));
  		lblHangThe.setBounds(35, 206, 113, 25);
  		panel.add(lblHangThe);
  		
  		btnTimMa = new JButton("");
  		btnTimMa.setBackground(Color.decode("#0973B9"));
  		btnTimMa.setIcon(new ImageIcon("Image/IconTim1.png"));
  		btnTimMa.setBounds(387, 68, 62, 28);
		btnTimMa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!timKhachHang(txtDT.getText())) {
					xoaTrang();
					JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng");
				}
			}
		});
  		btnTimMa.setBorderPainted(false);
  		btnTimMa.setFocusPainted(false);
  		panel.add(btnTimMa);
  		
  		txtHangTV = new JTextField();
  		txtHangTV.setFont(new Font("Tahoma", Font.PLAIN, 15));
  		txtHangTV.setEditable(false);
  		txtHangTV.setColumns(10);
  		txtHangTV.setBounds(175, 204, 274, 30);
  		txtHangTV.setFocusable(false);
  		panel.add(txtHangTV);
  		
  		JPanel panel_1_1 = new JPanel();
  		panel_1_1.setLayout(null);
  		panel_1_1.setBounds(0, 259, 490, 44);
  		panel.add(panel_1_1);
  		
  		JLabel lblChngTrnhKhuyn = new JLabel("Chương trình khuyến mãi");
  		lblChngTrnhKhuyn.setIcon(new ImageIcon("Image/IconQua.png"));
  		lblChngTrnhKhuyn.setForeground(new Color(9, 115, 185));
  		lblChngTrnhKhuyn.setFont(new Font("Tahoma", Font.BOLD, 16));
  		lblChngTrnhKhuyn.setBounds(10, 9, 277, 25);
  		panel_1_1.add(lblChngTrnhKhuyn);

  		lbl1 = new JLabel("Tri ân khách hàng VIP giảm 5%");
  		lbl1.setIcon(new ImageIcon("Image/IconTich.png"));
  		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 16));
  		lbl1.setBounds(35, 326, 260, 30);
  		lbl1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl1.setBackground(Color.decode("#DCDCDC"));
				lbl1.setOpaque(true); // Cần thiết để màu nền có hiệu lực
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl1.setBackground(Color.WHITE);
				lbl1.setOpaque(true); // Cần thiết để màu nền có hiệu lực
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
  		panel.add(lbl1);
  		lbl2 = new JLabel("Mua 3 loại nước bất kỳ giảm 12%");
  		lbl2.setIcon(new ImageIcon("Image/IconTich.png"));
  		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 16));
  		lbl2.setBounds(35, 365, 289, 30);
  		lbl2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl2.setBackground(Color.decode("#DCDCDC"));
				lbl2.setOpaque(true); // Cần thiết để màu nền có hiệu lực
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl2.setBackground(Color.WHITE);
				lbl2.setOpaque(true); // Cần thiết để màu nền có hiệu lực
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
  		panel.add(lbl2);
  		
  		lbl3 = new JLabel("Tặng khăn lạnh");
  		lbl3.setIcon(new ImageIcon("Image/IconTich.png"));
  		lbl3.setFont(new Font("Tahoma", Font.PLAIN, 16));
  		lbl3.setBounds(35, 404, 163, 30);
  		lbl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl3.setBackground(Color.decode("#DCDCDC"));
				lbl3.setOpaque(true); // Cần thiết để màu nền có hiệu lực
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl3.setBackground(Color.WHITE);
				lbl3.setOpaque(true); // Cần thiết để màu nền có hiệu lực
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				 // Kiểm tra trạng thái hiện tại
                if (isClicked3) {
                    // Nếu đã nhấn, quay lại biểu tượng cũ
                    lbl3.setIcon(new ImageIcon("Image/IconTich.png"));
                } else {
                    // Nếu chưa nhấn, chuyển sang biểu tượng mới
                    lbl3.setIcon(new ImageIcon("Image/IconTich2.png"));
                }
                // Đảo ngược trạng thái
                isClicked3 = !isClicked3;
			}
		});
  		panel.add(lbl3);
  		
  		JPanel panel_1_1_1 = new JPanel();
  		panel_1_1_1.setLayout(null);
  		panel_1_1_1.setBounds(0, 738, 490, 52);
  		panel.add(panel_1_1_1);
  		
  		btnKhuyenMaiKhac = new JButton("Khuyến mãi khác");
  		btnKhuyenMaiKhac.setIcon(new ImageIcon("Image/IconThemKM.png"));
  		btnKhuyenMaiKhac.setBackground(Color.WHITE);
  		btnKhuyenMaiKhac.setFont(new Font("Tahoma", Font.BOLD, 15));
  		btnKhuyenMaiKhac.setBounds(20, 8, 220, 35);
  		btnKhuyenMaiKhac.setForeground(new Color(9, 115, 185));
  		btnKhuyenMaiKhac.setBorderPainted(false);
  		btnKhuyenMaiKhac.setFocusPainted(false);
  		panel_1_1_1.add(btnKhuyenMaiKhac);
  		

  		lbl4 = new JLabel("Giảm 15% khi mua 6 loại nước bất kỳ");
  		lbl4.setIcon(new ImageIcon("Image/IconTich.png"));
  		lbl4.setFont(new Font("Tahoma", Font.PLAIN, 16));
  		lbl4.setBounds(35, 443, 316, 30);
  		lbl4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl4.setBackground(Color.decode("#DCDCDC"));
				lbl4.setOpaque(true); // Cần thiết để màu nền có hiệu lực
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbl4.setBackground(Color.WHITE);
				lbl4.setOpaque(true); // Cần thiết để màu nền có hiệu lực
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				 // Kiểm tra trạng thái hiện tại
                if (isClicked4) {
                    // Nếu đã nhấn, quay lại biểu tượng cũ
                    lbl4.setIcon(new ImageIcon("Image/IconTich.png"));
                } else {
                    // Nếu chưa nhấn, chuyển sang biểu tượng mới
                    lbl4.setIcon(new ImageIcon("Image/IconTich2.png"));
                }
                // Đảo ngược trạng thái
                isClicked4 = !isClicked4;
			}
		});
  		panel.add(lbl4);
  		
  		JPanel panel_2 = new JPanel();
  		panel_2.setBackground(Color.WHITE);
  		panel_2.setBounds(497, 0, 734, 790);
  		contentPane.add(panel_2);
  		panel_2.setLayout(null);
  		
  		JPanel panel_3 = new JPanel();
  		panel_3.setBounds(0, 0, 734, 48);
  		panel_2.add(panel_3);
  		panel_3.setLayout(null);
  		
  		lblTitileBan = new JLabel();
  		lblTitileBan.setHorizontalAlignment(SwingConstants.LEFT);
  		lblTitileBan.setBounds(10, 12, 217, 27);
  		lblTitileBan.setForeground(new Color(9, 115, 185));
  		lblTitileBan.setFont(new Font("Tahoma", Font.BOLD, 20));
  		panel_3.add(lblTitileBan);
  		
  		lblTitileTG = new JLabel();
  		lblTitileTG.setHorizontalAlignment(SwingConstants.LEFT);
  		lblTitileTG.setBounds(415, 12, 440, 27);
  		lblTitileTG.setForeground(Color.BLACK);
  		lblTitileTG.setFont(new Font("Tahoma", Font.BOLD, 16));
  		panel_3.add(lblTitileTG);
  		
  		JPanel panel_4 = new JPanel();
  		panel_4.setBorder(new LineBorder(Color.decode("#888888")));
  		panel_4.setBackground(Color.WHITE);
  		panel_4.setBounds(-3, 48, 751, 609);
  		panel_2.add(panel_4);
  		panel_4.setBackground(new Color(9, 115, 185));
  		panel_4.setLayout(null);
  		
  		panelTable = new JPanel();
  		panelTable.setBackground(Color.WHITE);
  		panelTable.setBounds(0, 40, 741, 568);
  		panel_4.add(panelTable);
  		panelTable.add(getTableDH());
  		
  		JLabel lblNewLabel_4 = new JLabel("STT");
  		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
  		lblNewLabel_4.setBounds(38, 7, 95, 26);
  		lblNewLabel_4.setForeground(Color.WHITE);
  		panel_4.add(lblNewLabel_4);
  		
  		JLabel lblNewLabel_3 = new JLabel("Tên Món");
  		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
  		lblNewLabel_3.setBounds(172, 7, 95, 26);
  		lblNewLabel_3.setForeground(Color.WHITE);
  		panel_4.add(lblNewLabel_3);
  		
  		JLabel lblNewLabel_3_2 = new JLabel("Đơn Giá");
  		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 16));
  		lblNewLabel_3_2.setBounds(365, 7, 95, 26);
  		lblNewLabel_3_2.setForeground(Color.WHITE);
  		panel_4.add(lblNewLabel_3_2);
  		
  		JLabel lblNewLabel_3_1 = new JLabel("SL");
  		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
  		lblNewLabel_3_1.setBounds(529, 7, 65, 26);
  		lblNewLabel_3_1.setForeground(Color.WHITE);
  		panel_4.add(lblNewLabel_3_1);
  		
  		
  		JLabel lblNewLabel_3_2_1 = new JLabel("Thành Tiền");
  		lblNewLabel_3_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
  		lblNewLabel_3_2_1.setBounds(620, 8, 95, 26);
  		lblNewLabel_3_2_1.setForeground(Color.WHITE);
  		panel_4.add(lblNewLabel_3_2_1);
  		
  		JPanel panel_5 = new JPanel();
  		panel_5.setBounds(0, 738, 734, 52);
  		panel_2.add(panel_5);
  		panel_5.setLayout(null);
  		
  		btnQuayLai = new JButton("QUAY LẠI");
  		btnQuayLai.setBackground(Color.WHITE);
  		btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 15));
  		btnQuayLai.setBounds(10, 8, 130, 35);
  		btnQuayLai.setForeground(new Color(9, 115, 185));
  		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
  		btnQuayLai.setBorderPainted(false);
  		btnQuayLai.setFocusPainted(false);
  		panel_5.add(btnQuayLai);
  		
  		btnInTamTinh = new JButton("IN TẠM TÍNH");
  		btnInTamTinh.setBackground(Color.WHITE);
  		btnInTamTinh.setFont(new Font("Tahoma", Font.BOLD, 15));
  		btnInTamTinh.setBounds(411, 8, 160, 35); 
  		btnInTamTinh.setForeground(new Color(9, 115, 185));
  		btnInTamTinh.setBorderPainted(false);
  		btnInTamTinh.setFocusPainted(false);
		btnInTamTinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"In phiếu tạm tính thành công!");
				inHoaDon(hoaDon1);
			}
		});
  		panel_5.add(btnInTamTinh);
  		
  		btnThanhToan = new JButton("THU TIỀN");
  		btnThanhToan.setBackground(Color.decode("#FF9900"));
  		btnThanhToan.setForeground(Color.WHITE);
  		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 15));
  		btnThanhToan.setBounds(594, 8, 130, 35);
  		btnThanhToan.setBorderPainted(false);
  		btnThanhToan.setFocusPainted(false);
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themHoaDon(hoaDon1);//Cập nhật trạng thái của hóa đơn
				JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
				
				int temp = JOptionPane.showConfirmDialog(null, "Bạn có muốn in hóa đơn không?","In hoa đơn",JOptionPane.YES_NO_OPTION);
				if(temp == JOptionPane.YES_OPTION) {
					inHoaDon(hoaDon1);
				}
				frame.dispose();
			}
		});
  		panel_5.add(btnThanhToan);
  		
  		JLabel lblNewLabel_3_3 = new JLabel("Tổng Tiền:");
  		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.BOLD, 19));
  		lblNewLabel_3_3.setBounds(23, 668, 133, 26);
  		panel_2.add(lblNewLabel_3_3);
  		
  		lblTongTien = new JLabel();
  		lblTongTien.setHorizontalAlignment(SwingConstants.CENTER);
  		lblTongTien.setForeground(Color.RED);
  		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 19));
  		lblTongTien.setBounds(221, 668, 142, 26);
  		panel_2.add(lblTongTien);
  		
  		lblThue = new JLabel("Thuế GTGT 10%");
  		lblThue.setIcon(new ImageIcon("Image/IconTich2.png"));
  		lblThue.setFont(new Font("Tahoma", Font.PLAIN, 16));
  		lblThue.setBounds(23, 705, 181, 25);
  		
  		panel_2.add(lblThue);
  		
  		txtThue = new JTextField();
  		txtThue.setEditable(false);
  		txtThue.setBounds(227, 705, 117, 26);
  		txtThue.setColumns(10);
  		txtThue.setFocusable(false);
  		txtThue.setFont(new Font("Tahoma", Font.BOLD, 16));
  		panel_2.add(txtThue);
  		
  		JLabel lblNewLabel_3_3_2 = new JLabel("Tổng Thanh Toán:");
  		lblNewLabel_3_3_2.setFont(new Font("Tahoma", Font.BOLD, 22));
  		lblNewLabel_3_3_2.setBounds(376, 705, 207, 26);
  		panel_2.add(lblNewLabel_3_3_2);
  		
  		lblTongTT = new JLabel();
  		lblTongTT.setHorizontalAlignment(SwingConstants.CENTER);
  		lblTongTT.setForeground(Color.RED);
  		lblTongTT.setFont(new Font("Tahoma", Font.BOLD, 22));
  		lblTongTT.setBounds(574, 705, 150, 26);

  		//Gọi các hàm con
  		showHoaDonDetail(hoaDon1);//show list sp co trong hoa don 
  		updateJLabel();
  		
  		panel_2.add(lblTongTT);
  		frame.setVisible(true);
  	}
  	//Tạo bảng
  	public JScrollPane getTableDH() {
    	listDonHangTable = new JTable();
    	listDonHangTable.setBackground(Color.WHITE);
    	listDonHangTable.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		listDonHangModel =  new DefaultTableModel(0,5) {
        	@Override
        	public boolean isCellEditable(int row,int column) {
        		return false; 
        	}
		};
		
		listDonHangTable.setModel(listDonHangModel);
		listDonHangTable.setTableHeader(null); //Xóa dòng tiêu đề
		listDonHangTable.getColumnModel().getColumn(0).setPreferredWidth(30); 
		listDonHangTable.getColumnModel().getColumn(1).setPreferredWidth(160); 
		listDonHangTable.getColumnModel().getColumn(2).setPreferredWidth(100);
		listDonHangTable.getColumnModel().getColumn(3).setPreferredWidth(60);

		
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
        
		JScrollPane scrollPane = new JScrollPane(listDonHangTable);
		scrollPane.setPreferredSize(new Dimension(730,560));//chỉnh chiều rộng, cao table
		
		return scrollPane;
    }
  	
 // Phương thức để hiển thị thông tin chi tiết của hóa đơn
  	public void showHoaDonDetail(HoaDon1 hoaDon) {
        // Tạo một đối tượng NumberFormat cho tiền tệ của Việt Nam
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Loại bỏ các số 0 không cần thiết ở cuối cùng
        currencyFormat.setMaximumFractionDigits(0);
  		
  	    String maHoaDon = hoaDon.getMaHD();
  	    String query = "SELECT ch.maHoaDon, sp.maSanPham, sp.tenSanPham, ch.soLuong, ch.donGia, ch.thanhTien, h.ngayLapHoaDon " +
  	                   "FROM ChiTietHoaDon ch " +
  	                   "JOIN HoaDon h ON ch.maHoaDon = h.maHoaDon "+
  	                   "JOIN SanPham sp on ch.maSanPham = sp.maSanPham " +
  	                   "WHERE ch.maHoaDon = ?";
  	    try {
  	        PreparedStatement statement = ConnectDB.getConnection().prepareStatement(query);
  	        statement.setString(1, maHoaDon);
  	        ResultSet resultSet = statement.executeQuery();
  	        
  	        ArrayList<Object[]> rows = new ArrayList<>();
  	        int i = 1;
  	        while (resultSet.next()) {
  	            Object[] row = {
  	            	i,
  	                resultSet.getString("tenSanPham"),
  	                currencyFormat.format(resultSet.getFloat("donGia")),
  	                resultSet.getInt("soLuong"),
  	                currencyFormat.format(resultSet.getDouble("thanhTien"))
  	            };
  	            rows.add(row);
  	            ++i;
  	        }
  	        // Chuyển đổi danh sách mảng thành mảng hai chiều
  	        Object[][] result = new Object[rows.size()][];
  	        rows.toArray(result);
  	        
  	        //Them du lieu vao table
  	        for (Object[] rowData : result) {
  	            listDonHangModel.addRow(rowData);
  	        }
  	    } catch (SQLException e) {
  	        e.printStackTrace();
  	    }
  	} 	
  	//Lấy mã khách hàng mua hóa đơn
  	public boolean timKhachHang(String std) {
  		for(KhachHang kh : khachHang_DAO.getalltbKH()) {
  			if(kh.getSdtKH().trim().equals(std)) {
  				txtMa.setText(kh.getMaKH());
  				txtTenTV.setText(kh.getTenKH());
  				txtHangTV.setText(kh.getLoaiKH());
  				tienVip = tongTien*0.05;
  				tienGiam = tienVip + giamNuoc*tongTien;
                tongThanhToan = (tongTien - tienVip - giamNuoc*tongTien)*(1+thue);
                lblTongTT.setText(currencyFormat.format(tongThanhToan));
                lbl1.setIcon(new ImageIcon("Image/IconTich2.png"));
  				return true;
  			}
  		}
  		return false;
  	}
  	//Xoá trắng thông tin khách hàng
  	public void xoaTrang() {
  		txtTenTV.setText("");
  		txtHangTV.setText("");
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
    //Định dạng ngày giờ hiện tại 
    public	String formattime(HoaDon1 hoaDon1) {
    	LocalDateTime time = hoaDon1.getNgayLapHD();
		//Định dạng lại về thời gian theo kiểu việt nam
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.forLanguageTag("vi-VN"));
		return time.format(formatter);
    }
    //them hoa don vao csdl
    public void themHoaDon(HoaDon1 hoaDon1) {
    	String query;
    	PreparedStatement smt = null;
    	if(timKhachHang(txtDT.getText())) {
    		query = "Update HoaDon " +
    				"SET maKH=?, " +
    				"trangThaiThanhToan=?, " +
    				"tongTien=? " +
    				"Where maHoaDon=? ";
    		try {
				smt = ConnectDB.getConnection().prepareStatement(query);
				smt.setString(1, txtMa.getText().trim());
				smt.setString(2, "Đã Thanh Toán");
				smt.setDouble(3, tongThanhToan);
				smt.setString(4, hoaDon1.getMaHD());
				smt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else {
    		query = "Update HoaDon " +
    				"SET trangThaiThanhToan=?, " +
    				"tongTien=? " +
    				"Where maHoaDon=? "; 
    		
    		try {
				smt = ConnectDB.getConnection().prepareStatement(query);
				smt.setString(1, "Đã Thanh Toán");
				smt.setDouble(2, tongThanhToan);
				smt.setString(3, hoaDon1.getMaHD());
				smt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    public void inHoaDon(HoaDon1 hoaDon1 ) {
	    String query = "SELECT ch.maHoaDon, sp.maSanPham, sp.tenSanPham, ch.soLuong, ch.donGia, ch.thanhTien, h.ngayLapHoaDon " +
	              "FROM ChiTietHoaDon ch " +
	              "JOIN HoaDon h ON ch.maHoaDon = h.maHoaDon "+
	              "JOIN SanPham sp on ch.maSanPham = sp.maSanPham " +
	              "WHERE ch.maHoaDon = ?";
	    try {
	       PreparedStatement statement = ConnectDB.getConnection().prepareStatement(query);
	       statement.setString(1, hoaDon1.getMaHD());
	       ResultSet resultSet = statement.executeQuery();
	       
	       ArrayList<Object[]> rows = new ArrayList<>();
	       while (resultSet.next()) {
	           Object[] row = {
	               resultSet.getString("tenSanPham"),
	               currencyFormat.format(resultSet.getFloat("donGia")),
	               resultSet.getInt("soLuong"),
	               currencyFormat.format(resultSet.getDouble("thanhTien"))
	           };
	           rows.add(row);
	       }
	       // Chuyển đổi danh sách mảng thành mảng hai chiều
	       Object[][] result = new Object[rows.size()][];
	       rows.toArray(result);
	
			InvoiceGenerator generator = new InvoiceGenerator(hoaDon1.getMaHD(), hoaDon1.getNv().getMaNV(),formattime(hoaDon1) , hoaDon1.getHinThuc(), currencyFormat.format(tongThanhToan),result,currencyFormat.format(hoaDon1.getTongTien()),currencyFormat.format(thue*tongTien),currencyFormat.format(tienGiam));
			try {
				generator.createInvoice(generator.getDest());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	   } catch (SQLException e1) {
	       e1.printStackTrace();
	   }
    }
    //Cập nhật dữ liệu các JLabel
    private void updateJLabel() {
    	tinh();
    	txtThue.setText(currencyFormat.format(tienThue));
    	lblTitileBan.setText("Mã Hóa Đơn: "+maHoaDon);
    	lblTitileTG.setText("Thời Gian Tạo: "+thoiGian);
    	lblTongTien.setText(currencyFormat.format(tongTien));
    	lblTongTT.setText(currencyFormat.format(tongThanhToan));
    }
    //Tinh
    public void tinh() {
    	giamNuoc();
    	tienGiam = giamNuoc*tongTien + tienVip;
    	tienThue = tongTien*thue;
    	tongThanhToan = (tongTien - giamNuoc*tongTien) + tienThue;
    }
    public void giamNuoc() {
        int temp = 0;
        boolean isDrink = false; // Biến này để kiểm tra xem có ít nhất 3 sản phẩm thức uống không
        String sql="SELECT SUM(SoLuong) AS TongSoLuongSanPham " +
        		"FROM ( SELECT ch.maSanPham, SUM(ch.soLuong) AS SoLuong FROM [dbo].[HoaDon] hd  INNER JOIN [dbo].[ChiTietHoaDon] ch ON hd.maHoaDon = ch.maHoaDon WHERE ch.maSanPham IN (SELECT [maSanPham] FROM [dbo].[SanPham] sp WHERE sp.loaiSanPham =? ) AND hd.maHoaDon =? GROUP BY ch.maSanPham ) AS TongSanPham";
        try {
            PreparedStatement stm = ConnectDB.getConnection().prepareStatement(sql);
            stm.setString(1, "Đồ uống"); 
            stm.setString(2, maHoaDon);
            ResultSet resultSet = stm.executeQuery();
            
            if (resultSet.next()) {
                temp = resultSet.getInt(1); // Lấy số lượng sản phẩm thức uống trong hóa đơn
                if (temp >= 3) {
                	lbl2.setIcon(new ImageIcon("Image/IconTich2.png"));
                    isDrink = true; // Đánh dấu là có ít nhất 3 sản phẩm thức uống trong hóa đơn
                }
            }

            // Nếu có ít nhất 3 sản phẩm thức uống trong hóa đơn, giảm giá 10%
            if (isDrink) {
               giamNuoc = (float)0.12;
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ một cách thích hợp
            e.printStackTrace();
        }
    }
}
