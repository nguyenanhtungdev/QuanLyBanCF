package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;
import connectDB.ConnectDB;
import dao.nhanVien_DAO;
import dao.taiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;
import gui.MaHoa;
import gui.QuanLyDH.CustomColorRenderer1;


public class QuanLyNV extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMa;
	private JTextField textHo;
	private JTextField textTen;
	private JDateChooser textNgaySinh;
	private JTextField textSDT;
	private JTextField textHSLuong;
	private JRadioButton rdbtnNam;
	private JRadioButton rdbtnNu;
	private JComboBox<String> cboxChucVu;
	private JComboBox<String>  cboxTrangThai;
	private Button btnThem;
	private Button btnCapNhat;
	private JLabel lblTim;
	private JTextField textTimMa;
	private Button btnTim;
	private JLabel lblCVu;
	private JLabel lblTThai;
	private JComboBox<String> cboxLocChucVu;
	private JComboBox<String> cboxLocTrangThai;
	private JPanel pTable;
	private DefaultTableModel modelNV;
	private JTable tableNV;
	private JScrollPane panelNV;
	private nhanVien_DAO nv_dao;
	private taiKhoan_DAO tk_dao;
	private Button btnXoaTrang;
	
	public QuanLyNV() {
		try {
			ConnectDB.getInstance().connect();
//			System.out.println("Connect");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		nv_dao = new nhanVien_DAO();
		tk_dao = new taiKhoan_DAO();
		
//		nv_dao.getalltbNhanVien();
		tk_dao.getalltbTaiKhoan();
		
		setTitle("Quản Lý Nhân Viên");
		setFont(new Font("Dialog", Font.PLAIN, 77));
		setSize(1059, 747);
		setResizable(false);
		this.setIconImage(new ImageIcon("image/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel(); //JPanel gốc, muốn tạo hay thêm các thành phần gì thì thêm vào ở đây
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 82, 1443, 763);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Phần danh sách
		JPanel pList = new JPanel();
		pList.setBackground(new Color(255, 255, 255));
		pList.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pList.setBounds(0, 0, 824, 726);
		contentPane.add(pList);
		pList.setLayout(null);
		
		lblTim = new JLabel("Mã nhân viên");
		lblTim.setFont(new Font("Arial", Font.BOLD, 18));
		lblTim.setBounds(26, 31, 120, 34);
		pList.add(lblTim);
		
		textTimMa = new JTextField();
		textTimMa.setFont(new Font("Arial", Font.PLAIN, 17));
		textTimMa.setBounds(26, 69, 193, 25);
		pList.add(textTimMa);
		textTimMa.setColumns(10);
		
		textTimMa.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		        	 ActionEvent event = new ActionEvent(btnTim, ActionEvent.ACTION_PERFORMED, "");
		             for (ActionListener listener : btnTim.getActionListeners()) {
		                 listener.actionPerformed(event);
		             }
		        }
		    }
		});
		
		btnTim = new Button("Tìm");
		btnTim.setForeground(Color.WHITE);
		btnTim.setBackground(new Color(30,144,255));
		btnTim.setFont(new Font("Arial", Font.PLAIN, 14));
		btnTim.setBounds(225, 69, 74, 25);
		pList.add(btnTim);
		
		lblCVu = new JLabel("Chức vụ");
		lblCVu.setFont(new Font("Arial", Font.BOLD, 18));
		lblCVu.setBounds(353, 31, 120, 34);
		pList.add(lblCVu);
		
		cboxLocChucVu = new JComboBox<>();
		cboxLocChucVu.setBackground(new Color(255, 255, 255));
		cboxLocChucVu.addItem("Tất cả");
		cboxLocChucVu.addItem("Nhân viên bán hàng");
		cboxLocChucVu.addItem("Nhân viên phục vụ");
		cboxLocChucVu.addItem("Nhân viên pha chế");
		cboxLocChucVu.setBounds(353, 69, 193, 25);
		cboxLocChucVu.setFont(new Font("Arial", Font.PLAIN, 16));
		pList.add(cboxLocChucVu);
	
		lblTThai = new JLabel("Trạng thái");
		lblTThai.setFont(new Font("Arial", Font.BOLD, 18));
		lblTThai.setBounds(605, 31, 120, 34);
		pList.add(lblTThai);
		
		cboxLocTrangThai = new JComboBox<>();
		cboxLocTrangThai.setBackground(new Color(255, 255, 255));
		cboxLocTrangThai.addItem("Tất cả");
		cboxLocTrangThai.addItem("Còn làm");
		cboxLocTrangThai.addItem("Nghỉ làm");
		cboxLocTrangThai.setBounds(605, 69, 193, 25);
		cboxLocTrangThai.setFont(new Font("Arial", Font.PLAIN, 16));
		pList.add(cboxLocTrangThai);
		
		// Phần bảng
		pTable = new JPanel();
		pTable.setBounds(16, 120, 792, 595);
		pList.add(pTable);
		
		String[] header = {"Mã NV", "Họ NV", "Tên NV", "Ngày sinh", "Giới tính", "Số điện thoại", "Chức vụ", "Hệ số lương", "Trạng thái"};
		modelNV = new DefaultTableModel(header, 0) {
			    @Override
			    public boolean isCellEditable(int row, int column) {
			        return false;
			    }
		};
		tableNV = new JTable(modelNV);
		panelNV = new JScrollPane(tableNV);
		panelNV.setBounds(0, 0, 792, 625);
		panelNV.setPreferredSize(new Dimension(772, 520));
		
		JTableHeader headerTable = tableNV.getTableHeader();
		Font fontHeader = new Font("Tahoma", Font.BOLD, 13); // Font chữ "Arial", đậm, kích thước 14
		headerTable.setFont(fontHeader);
		headerTable.setPreferredSize(new Dimension(tableNV.getWidth(), 40));
		
		tableNV.setRowHeight(40);
		tableNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		for (int i = 0; i <= 8; i++) {
			tableNV.getColumnModel().getColumn(i).setMinWidth(86);
			tableNV.getColumnModel().getColumn(i).setMaxWidth(86);
		}
		pTable.setLayout(null);

		pTable.add(panelNV);
		
		docDuLieuArrayListVaoMode();
		
		
		// phần form
		JPanel pForm = new JPanel();
		pForm.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pForm.setBounds(826, 0, 605, 726);
		contentPane.add(pForm);
		pForm.setLayout(null);
		
		JLabel lblTitle = new JLabel("Quản Lý Nhân Viên");
		lblTitle.setBounds(171, 53, 306, 31);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 33));
		pForm.add(lblTitle);
		
		JLabel lblMa = new JLabel("Mã nhân viên:");
		lblMa.setFont(new Font("Arial", Font.BOLD, 18));
		lblMa.setBounds(48, 119, 144, 36);
		pForm.add(lblMa);
		
		textMa = new JTextField();
		textMa.setFont(new Font("Arial", Font.PLAIN, 16));
		textMa.setBounds(202, 125, 350, 25);
		pForm.add(textMa);
		textMa.setColumns(10);
		
		textMa.addActionListener(e -> SwingUtilities.invokeLater(() -> textHo.requestFocus()));
		
		JLabel lblHo = new JLabel("Họ nhân viên:");
		lblHo.setFont(new Font("Arial", Font.BOLD, 18));
		lblHo.setBounds(48, 165, 144, 36);
		pForm.add(lblHo);
		
		textHo = new JTextField();
		textHo.setFont(new Font("Arial", Font.PLAIN, 16));
		textHo.setColumns(10);
		textHo.setBounds(202, 171, 350, 25);

		pForm.add(textHo);
		
		textHo.addActionListener(e -> SwingUtilities.invokeLater(() -> textTen.requestFocus()));
		
		JLabel lblTen = new JLabel("Tên nhân viên:");
		lblTen.setFont(new Font("Arial", Font.BOLD, 18));
		lblTen.setBounds(48, 212, 144, 36);
		pForm.add(lblTen);
		
		textTen = new JTextField();
		textTen.setFont(new Font("Arial", Font.PLAIN, 16));
		textTen.setColumns(10);
		textTen.setBounds(202, 218, 350, 25);
		pForm.add(textTen);
		
		
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		lblNgaySinh.setFont(new Font("Arial", Font.BOLD, 18));
		lblNgaySinh.setBounds(48, 257, 144, 36);
		pForm.add(lblNgaySinh);
		
		textNgaySinh = new JDateChooser();
		textNgaySinh.setDateFormatString("dd-MM-yyyy");
		textNgaySinh.setFont(new Font("Arial", Font.PLAIN, 16));
		textNgaySinh.setBounds(202, 263, 350, 25);
		pForm.add(textNgaySinh);
		
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Arial", Font.BOLD, 18));
		lblGioiTinh.setBounds(48, 303, 144, 36);
		pForm.add(lblGioiTinh);
		
		rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setFont(new Font("Arial", Font.PLAIN, 18));
		rdbtnNam.setBounds(202, 311, 63, 21);
		rdbtnNam.setBorder(null);
		rdbtnNam.setSelected(true);
		pForm.add(rdbtnNam);
		
		rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBackground(new Color(240, 240, 240));
		rdbtnNu.setFont(new Font("Arial", Font.PLAIN, 18));
		rdbtnNu.setBounds(271, 311, 63, 21);
		pForm.add(rdbtnNu);
		
		ButtonGroup gr = new ButtonGroup();
		gr.add(rdbtnNam);
		gr.add(rdbtnNu);
		
		JLabel lblSDT = new JLabel("Số điện thoại: ");
		lblSDT.setFont(new Font("Arial", Font.BOLD, 18));
		lblSDT.setBounds(48, 349, 144, 36);
		pForm.add(lblSDT);
		
		textSDT = new JTextField();
		textSDT.setFont(new Font("Arial", Font.PLAIN, 16));
		textSDT.setColumns(10);
		textSDT.setBounds(202, 355, 350, 25);
		pForm.add(textSDT);
		
		textSDT.addActionListener(e -> SwingUtilities.invokeLater(() -> textHSLuong.requestFocus()));
		
		JLabel lblChucVu = new JLabel("Chức vụ:");
		lblChucVu.setFont(new Font("Arial", Font.BOLD, 18));
		lblChucVu.setBounds(48, 395, 144, 36);
		pForm.add(lblChucVu);
		
		cboxChucVu = new JComboBox<>();
		cboxChucVu.addItem("Nhân viên bán hàng");
		cboxChucVu.addItem("Nhân viên phục vụ");
		cboxChucVu.addItem("Nhân viên pha chế");
		cboxChucVu.setBounds(202, 401, 350, 25);
		cboxChucVu.setFont(new Font("Arial", Font.PLAIN, 18));
		cboxChucVu.setBackground(new Color(240, 240, 240));
		pForm.add(cboxChucVu);
		
		JLabel lblHSLuong = new JLabel("Hệ số lương:");
		lblHSLuong.setFont(new Font("Arial", Font.BOLD, 18));
		lblHSLuong.setBounds(48, 441, 144, 36);
		pForm.add(lblHSLuong);
		
		textHSLuong = new JTextField();
		textHSLuong.setFont(new Font("Arial", Font.PLAIN, 16));
		textHSLuong.setColumns(10);
		textHSLuong.setBounds(202, 447, 350, 25);
		pForm.add(textHSLuong);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Arial", Font.BOLD, 18));
		lblTrangThai.setBounds(48, 495, 144, 36);
		pForm.add(lblTrangThai);
		
		cboxTrangThai = new JComboBox<>();
		cboxTrangThai.addItem("Còn làm");
		cboxTrangThai.addItem("Nghỉ làm");
		cboxTrangThai.setBounds(202, 501, 350, 25);
		cboxTrangThai.setFont(new Font("Arial", Font.PLAIN, 18));
		cboxTrangThai.setBackground(new Color(240, 240, 240));
		pForm.add(cboxTrangThai);
		
		btnThem = new Button("Thêm");
		btnThem.setForeground(Color.WHITE);
		btnThem.setBackground(new Color(30,144,255));
		btnThem.setFont(new Font("Arial", Font.BOLD, 20));
		btnThem.setBounds(93, 570, 122, 44);
		pForm.add(btnThem);
		
		btnCapNhat = new Button("Cập nhật"); 
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setBackground(new Color(30,144,255));
		btnCapNhat.setFont(new Font("Arial", Font.BOLD, 20));
		btnCapNhat.setBounds(244, 570, 122, 44);
		pForm.add(btnCapNhat);
		
		btnXoaTrang = new Button("Xóa trắng");
		btnXoaTrang.setForeground(Color.WHITE);
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnXoaTrang.setBackground(new Color(30,144,255));
		btnXoaTrang.setBounds(393, 570, 122, 44);
		pForm.add(btnXoaTrang);
		
		// đăng ký sự kiện
		textHo.addActionListener(this);
		textHSLuong.addActionListener(this);
		textMa.addActionListener(this);
		
		textSDT.addActionListener(this);
		textTen.addActionListener(this);
		rdbtnNam.addActionListener(this);
		rdbtnNu.addActionListener(this);
		
		btnCapNhat.addActionListener(this);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		
		tableNV.addMouseListener(this); 
		
		cboxLocChucVu.addActionListener(this);
		cboxLocTrangThai.addActionListener(this);
		
		//Chỉnh màu chữ của cột, và xóa border cho từng cột
        tableNV.getColumnModel().getColumn(0).setCellRenderer(new CustomColorRenderer1());
        tableNV.getColumnModel().getColumn(1).setCellRenderer(new CustomColorRenderer1());
        tableNV.getColumnModel().getColumn(2).setCellRenderer(new CustomColorRenderer1());
        tableNV.getColumnModel().getColumn(3).setCellRenderer(new CustomColorRenderer1());
        tableNV.getColumnModel().getColumn(4).setCellRenderer(new CustomColorRenderer1());
        tableNV.getColumnModel().getColumn(5).setCellRenderer(new CustomColorRenderer1());
        tableNV.getColumnModel().getColumn(6).setCellRenderer(new CustomColorRenderer1());
        tableNV.getColumnModel().getColumn(7).setCellRenderer(new CustomColorRenderer1());
        tableNV.getColumnModel().getColumn(8).setCellRenderer(new CustomColorRenderer1());
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int dongDuocChon = tableNV.getSelectedRow();
		
		textMa.setText(modelNV.getValueAt(dongDuocChon, 0).toString());
		textHo.setText(modelNV.getValueAt(dongDuocChon, 1).toString());
		textTen.setText(modelNV.getValueAt(dongDuocChon, 2).toString());
		
		String ngaySinh = modelNV.getValueAt(dongDuocChon, 3).toString();
		LocalDate parsedDate = LocalDate.parse(ngaySinh, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		Date date = java.sql.Date.valueOf(parsedDate);
		textNgaySinh.setDate(date);
		
		rdbtnNam.setSelected(modelNV.getValueAt(dongDuocChon, 4).toString().equals("Nam"));
		rdbtnNu.setSelected(modelNV.getValueAt(dongDuocChon, 4).toString().equals("Nữ"));
		textSDT.setText(modelNV.getValueAt(dongDuocChon, 5).toString());
		cboxChucVu.setSelectedItem(modelNV.getValueAt(dongDuocChon, 6).toString());
		textHSLuong.setText(modelNV.getValueAt(dongDuocChon, 7).toString());
		cboxTrangThai.setSelectedItem(modelNV.getValueAt(dongDuocChon, 8).toString());
		
//		tableNV.clearSelection();
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object o = e.getSource();
		
		NhanVien nv;
		TaiKhoan tk;
		
		if (o.equals(btnTim)) {
			tableNV.clearSelection();
			cboxLocChucVu.setSelectedIndex(0);
			cboxLocTrangThai.setSelectedIndex(0);
			timKiemTheoMa();
			textTimMa.setText("");
			
		}
		else if (o.equals(btnXoaTrang)) {
			tableNV.clearSelection();
			xoaTrang();			
		}
		else if (o.equals(btnThem)) {
			tableNV.clearSelection();
			
			if (kiemTraRegex()) {
				nv  = layThongTin();
				
				if (!nv_dao.kiemTraNV(nv)) {
					thongBaoLoi("Trùng mã", textMa);
					return;
				}
				
				// tạo tài khoản
				int sl = nv.getMaNV().length();
				String ma = nv.getMaNV().substring(sl - 3);
				String maTK = "TK" + ma;
				
				int sl1 = nv.getTenNV().lastIndexOf(" ");
				String ten = nv.getTenNV().substring(sl1 + 1);
				String tenTK = boDauTV(ten) + "" + ma;
				
				String matKhau = MaHoa.toSHA1(tenTK + "@");
				String quyen = "Employee";
				String manv = nv.getMaNV();
				NhanVien nvMoi = new NhanVien(manv);
				String trangThai = "Còn sử dụng";
				
				tk = new TaiKhoan(maTK, tenTK, matKhau, quyen, nvMoi, trangThai);
			
				try {
					if (nv_dao.taoNV(nv) && tk_dao.taoTK(tk)) {
							JOptionPane.showMessageDialog(this, "Thêm thành công!");
							addModal(nv);
					}
					else
						JOptionPane.showMessageDialog(this, "Thêm không thành công!");
					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Trung ma");
				}
			}
			
			xoaTrang();
		}
		else if (o.equals(btnCapNhat)) {
//			tableNV.clearSelection();
			
			if (kiemTraRegex()) {
				String maCanCN = textMa.getText();
				nv = nv_dao.timKiemNV(maCanCN);
				tk = tk_dao.timKiemTKTheoMaNV(maCanCN);
				
				if (nv != null && tk != null) {
					try {
						nv = layThongTin();
						if (nv_dao.capNhat( nv)) {
							tk_dao.capNhatTrangThaiTK(maCanCN, nv.getTrangThai());
							tableNV.clearSelection();
							JOptionPane.showMessageDialog(this, "Cập nhật thành công");
						}
						else
							JOptionPane.showMessageDialog(this, "Cập nhật không thành công!");
						
						xoaHetDuLieuTrenModel();
						docDuLieuArrayListVaoMode();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(this, "Trung ma");
					}
				}
				else {
					thongBaoLoi("Không tồn tại mã " + maCanCN, textMa);
				}
					
			}
			
			xoaTrang();
		}
		
		else if (o.equals(cboxLocChucVu)) {
			String chucVu = cboxLocChucVu.getSelectedItem().toString();
			String trangThai = cboxLocTrangThai.getSelectedItem().toString();
			
			if (chucVu.equalsIgnoreCase("Tất cả") && trangThai.equalsIgnoreCase("Tất cả")) {
				xoaHetDuLieuTrenModel();
				docDuLieuArrayListVaoMode();
			} else {
				xoaHetDuLieuTrenModel();
				locTheoDieuKien(trangThai, chucVu);
			
			}
		}
		else if (o.equals(cboxLocTrangThai)) {
			String trangThai = cboxLocTrangThai.getSelectedItem().toString();
			String chucVu = cboxLocChucVu.getSelectedItem().toString();
			
			if (chucVu.equalsIgnoreCase("Tất cả") && trangThai.equalsIgnoreCase("Tất cả")) {
				xoaHetDuLieuTrenModel();
				docDuLieuArrayListVaoMode();
			} else {
				xoaHetDuLieuTrenModel();
				locTheoDieuKien(trangThai, chucVu);
			}
		}
	}
	
	private void xoaTrang() {
		textHo.setText("");
		textHSLuong.setText("");
		textMa.setText("");
		textNgaySinh.setDate(null);
		textSDT.setText("");
		textTen.setText("");
		cboxChucVu.setSelectedIndex(0);
		cboxTrangThai.setSelectedIndex(0);
		rdbtnNam.setSelected(true);
	}
	
	private void docDuLieuArrayListVaoMode() {
		List<NhanVien> list = nv_dao.getalltbNhanVien();
		
		for (NhanVien nv : list) {
			addModal(nv);
		}
	}
	
	private void addModal(NhanVien nv) {
		String ngaySinh = nv.getNgaySinh().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		
		modelNV.addRow(new Object[] {
				nv.getMaNV(),
				nv.getHoNV(),
				nv.getTenNV(),
				ngaySinh,
				nv.isGioiTinh() == true ? "Nam" : "Nữ",
				nv.getSdtNV(),
				nv.getChucVu(),
				nv.getHeSoLuong(),
				nv.getTrangThai()
		});
	}
	
	private void  thongBaoLoi(String mess, JTextField txt ) {
		txt.requestFocus();
		txt.selectAll();
		JOptionPane.showMessageDialog(this, mess);
	}
	
	private void xoaHetDuLieuTrenModel() {
		DefaultTableModel dm = (DefaultTableModel) tableNV.getModel();
		dm.getDataVector().removeAllElements();
	}
	
	private void timKiemTheoMa() {
		String maCanTim = textTimMa.getText();
		if (maCanTim != null && maCanTim.trim().length() > 0) {
			try {
				NhanVien nv = nv_dao.timKiemNV(maCanTim);
				if (nv != null) {
					xoaHetDuLieuTrenModel();
					addModal(nv);
				}
				else {
					docDuLieuArrayListVaoMode();
					xoaHetDuLieuTrenModel();
					return;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Du lieu khong hop le!");
				textTimMa.selectAll(); // chọn toàn bộ văn bản
				textTimMa.requestFocus(); // đặt trọng tâm vào 1 JTextField
			}
		}
		else {
			xoaHetDuLieuTrenModel();
			docDuLieuArrayListVaoMode();
			tableNV.setModel(modelNV);
		}
	}
	
	private NhanVien layThongTin() {
		String ma = textMa.getText().toUpperCase();
		String ho = textHo.getText();

		// Viết hoa chữ cái đầu
		ho =  Arrays.stream(ho.split("\\s+"))
		        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
		        .collect(Collectors.joining(" "));
		
		String ten = textTen.getText();
		ten =  Arrays.stream(ten.split("\\s+"))
		        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
		        .collect(Collectors.joining(" "));
		
		Date ns = textNgaySinh.getDate();
		LocalDate ngaySinh = ns.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		boolean gioiTinh = false;
		if(rdbtnNam.isSelected())
			gioiTinh = true;
		String sdt = textSDT.getText();
		String chucVu = cboxChucVu.getSelectedItem().toString();
		float hsLuong = Float.parseFloat(textHSLuong.getText());
		String trangThai = cboxTrangThai.getSelectedItem().toString();
		
		NhanVien nv = new NhanVien(ma, ho, ten, ngaySinh, gioiTinh, sdt, chucVu, hsLuong, trangThai);
		
		return nv;
	}
	
	private boolean kiemTraRegex() {
		if (textHo.getText().equals("") || textHSLuong.getText().equals("") ||
				textMa.getText().equals("") || textNgaySinh.getDate() == null ||
				textSDT.getText().equals("") || textTen.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
				return false;
			}
		
		String ma = textMa.getText().toUpperCase();
		String sdt = textSDT.getText();
		float hsLuong = Float.parseFloat(textHSLuong.getText());
		
		if (!(ma.length() > 0 && ma.matches("^NV\\d{3}"))) {
			thongBaoLoi("Mã nhân viên phải bắt đầu bằng NV và có 3 ký tự số (vd: NV000)", textMa);
			return false;
		}
		
		if (!(sdt.length() == 10 && sdt.matches("[0-9]{10}"))) {
			thongBaoLoi("Số điện thoại phải gồm 10 ký tự số", textSDT);
			return false;
		}
		
		if (hsLuong <= 0) {
			thongBaoLoi("Hệ số lương phải > 0", textHSLuong);
			return false;
		}
		
		
		
		return true;
	}
	
	public static String boDauTV(String text) {
		 text = text.toLowerCase();
		 String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
		 normalized = Arrays.stream(normalized.split("\\s+"))
		            .map(word -> {
		                if (word.length() > 0) {
		                    // Nếu từ bắt đầu bằng "Đ", chuyển thành "D"
		                    if (word.charAt(0) == 'đ') {
		                        return "D" + word.substring(1);
		                    } else {
		                        return word.substring(0, 1).toUpperCase() + word.substring(1);
		                    }
		                } else {
		                    return word; // Trả về từ rỗng nếu từ đó không có ký tự
		                }
		            })
		            .collect(Collectors.joining(" "));
		 
	     Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	     return pattern.matcher(normalized).replaceAll("");
    }
	
	private void locTheoChucVu(String chucVu) {
		List<NhanVien> list = nv_dao.getalltbNhanVien();
		
		for (NhanVien nv : list) {
			if (nv.getChucVu().equalsIgnoreCase(chucVu))
				addModal(nv);
		}
	}
	
	private void locTheoTrangThai(String trangThai) {
		List<NhanVien> list = nv_dao.getalltbNhanVien();
		
		for (NhanVien nv : list) {
			if (nv.getTrangThai().equalsIgnoreCase(trangThai))
				addModal(nv);
		}
	}
	
	private void locTheoDieuKien(String trangThai, String chucVu) {
		List<NhanVien> list = nv_dao.getalltbNhanVien();
		
		for (NhanVien nv : list) {
			if (trangThai.equalsIgnoreCase("Tất cả") && !chucVu.equalsIgnoreCase("Tất cả")) {
				locTheoChucVu(chucVu);
				return;
			}
			else if (chucVu.equalsIgnoreCase("Tất cả")  && !trangThai.equalsIgnoreCase("Tất cả")) {
				locTheoTrangThai(trangThai);
				return;
			}
			else if (nv.getTrangThai().equalsIgnoreCase(trangThai) && nv.getChucVu().equalsIgnoreCase(chucVu)) {
				addModal(nv);
			}
		}		
		
	}
	public JPanel getQuanLyNV() {
		return contentPane;
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
}
