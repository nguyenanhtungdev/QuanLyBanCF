package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.TableCellRenderer;

import dao.sanPham_DAO;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
import gui.HomePageMenu.CustomColorRenderer1;
import gui.HomePageMenu.ImageRenderer;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import connectDB.ConnectDB;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class QuanLySanPham extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel modelSP;
	private JTable tableSP;
	private JScrollPane panelSP;
	private JPanel pTable;
	private sanPham_DAO sp_dao;
	private JLabel lblTim;
	private JTextField textTimMa;
	private JTextField textMaSP;
	private JTextField txtTen;
	private JComboBox<String> cboxLoai;
	private JTextField txtDonGia;
	private JTextField txtMoTa;
	private JTextField txtHinhAnh;
	private JComboBox cboxTrangThai;
	private JButton btnThem;
	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private JLabel lblTThai;
	private JComboBox<String> cboxLocTrangThai;
	private JTextField textField;
	private JComboBox cboxLocLoai;
	private JTextField txtSoLuong;
	private JButton btnTim;
	private JLabel lblLoi;
	public QuanLySanPham() {
		try {
			ConnectDB.getInstance().connect();
//		System.out.println("Connect");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		sp_dao = new sanPham_DAO();

		sp_dao.getalltbSanPham();
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);

			contentPane = new JPanel(); // JPanel gốc, muốn tạo hay thêm các thành phần gì thì thêm vào ở đây
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(0, 82, 1443, 763);
			setLocationRelativeTo(null);
			setContentPane(contentPane);
			contentPane.setLayout(null);

			JPanel pList = new JPanel();
			pList.setBackground(Color.WHITE);
			pList.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pList.setBounds(0, 0, 827, 728);
			contentPane.add(pList);
			pList.setLayout(null);

			lblTim = new JLabel("Mã Sản Phẩm");
			lblTim.setBounds(26, 45, 120, 34);
			lblTim.setFont(new Font("Arial", Font.BOLD, 18));
			pList.add(lblTim);
			btnTim = new JButton("");
			btnTim.setForeground(Color.WHITE);
			btnTim.setIcon(new ImageIcon("Image\\IconTim1.png"));
			btnTim.setBounds(202, 76, 61, 25);
			btnTim.setBackground(new Color(30, 144, 255));
			pList.add(btnTim);
			
			textTimMa = new JTextField();
			textTimMa.setBounds(25, 76, 175, 25);
			textTimMa.setFont(new Font("Arial", Font.PLAIN, 17));
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

			lblTThai = new JLabel("Trạng thái");
			lblTThai.setBounds(280, 45, 120, 34);
			lblTThai.setFont(new Font("Arial", Font.BOLD, 18));
			pList.add(lblTThai);

			cboxLocTrangThai = new JComboBox<>();
			cboxLocTrangThai.setBounds(280, 76, 193, 25);
			cboxLocTrangThai.setBackground(new Color(255, 255, 255));
			cboxLocTrangThai.addItem("Còn Bán");
			cboxLocTrangThai.addItem("Nghỉ Bán");
			cboxLocTrangThai.addItem("Tất Cả");
			cboxLocTrangThai.setFont(new Font("Arial", Font.PLAIN, 16));
			pList.add(cboxLocTrangThai);

			txtSoLuong = new JTextField();
			txtSoLuong.setEditable(false);
			txtSoLuong.setBounds(491, 76, 105, 25);
			txtSoLuong.setFont(new Font("Arial", Font.PLAIN, 16));
			pList.add(txtSoLuong);
			// Phần Bảng
			pTable = new JPanel();
			pTable.setBounds(26, 120, 801, 597);
			pList.add(pTable);
			String[] header = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Loại Sản Phẩm", "Đơn Giá", "Mô Tả", "Trạng Thái",
					"Hình Ảnh" };
			modelSP = new DefaultTableModel(header, 0) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			tableSP = new JTable(modelSP);
			panelSP = new JScrollPane(tableSP, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			panelSP.setBounds(0, 0, 799, 597);
			panelSP.setPreferredSize(new Dimension(772, 520));

			JTableHeader headerTable = tableSP.getTableHeader();
			Font fontHeader = new Font("Tahoma", Font.BOLD, 13); // Font chữ "Arial", đậm, kích thước 14
			headerTable.setFont(fontHeader);
			headerTable.setPreferredSize(new Dimension(tableSP.getWidth(), 40));

			tableSP.setRowHeight(80);
			tableSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
			tableSP.getColumnModel().getColumn(6).setCellRenderer(new ImageRenderer(100));
			tableSP.getColumnModel().getColumn(4).setPreferredWidth(30);
			tableSP.getColumnModel().getColumn(4).setCellRenderer(new DescriptionRenderer());
			pTable.setLayout(null);
			pTable.add(panelSP);

			//Cập nhật chiều rộng
			tableSP.getColumnModel().getColumn(4).setPreferredWidth(90);
			cboxLocLoai = new JComboBox<>();
			cboxLocLoai.addItem("Đồ uống");
			cboxLocLoai.addItem("Đồ ăn nhẹ");
			cboxLocLoai.addItem("Combo");
			cboxLocLoai.addItem("Tất cả");
			cboxLocLoai.setBounds(612, 76, 186, 25);
			cboxLocLoai.setFont(new Font("Arial", Font.PLAIN, 18));
			cboxLocLoai.setBackground(new Color(255, 255, 255));
			pList.add(cboxLocLoai);
			
			JLabel lblSLng = new JLabel("Số lượng");
			lblSLng.setFont(new Font("Arial", Font.BOLD, 18));
			lblSLng.setBounds(491, 45, 108, 34);
			pList.add(lblSLng);
			
			lblLoi = new JLabel("Loại");
			lblLoi.setFont(new Font("Arial", Font.BOLD, 18));
			lblLoi.setBounds(614, 45, 61, 34);
			pList.add(lblLoi);
			

			docDuLieuArrayListVaoMode();
			demSoLuong();
			JPanel pForm = new JPanel();
			pForm.setBackground(new Color(240,240,240));
			pForm.setBorder(new MatteBorder(0, 5, 0, 0, Color.decode("#DCDCDC")));
			pForm.setBounds(826, 0, 605, 728);
			contentPane.add(pForm);
			pForm.setLayout(null);

			JLabel lblTitle = new JLabel("Quản Lý Sản Phẩm");
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setBounds(137, 33, 350, 50);
			lblTitle.setFont(new Font("Arial", Font.BOLD, 33));
			pForm.add(lblTitle);

			JLabel lblMa = new JLabel("Mã Sản Phẩm:");
			lblMa.setFont(new Font("Arial", Font.BOLD, 16));
			lblMa.setBounds(48, 94, 144, 36);
			pForm.add(lblMa);

			textMaSP = new JTextField();
			textMaSP.setBackground(new Color(255, 255, 255));
			textMaSP.setFont(new Font("Arial", Font.PLAIN, 16));
			textMaSP.setBounds(202, 100, 350, 25);
			pForm.add(textMaSP);
			textMaSP.setColumns(10);

			textMaSP.addActionListener(e -> SwingUtilities.invokeLater(() -> txtTen.requestFocus()));

			JLabel lblTen = new JLabel("Tên Sản Phẩm:");
			lblTen.setFont(new Font("Arial", Font.BOLD, 16));
			lblTen.setBounds(48, 144, 144, 36);
			pForm.add(lblTen);

			txtTen = new JTextField();
			txtTen.setBackground(new Color(255, 255, 255));
			txtTen.setFont(new Font("Arial", Font.PLAIN, 16));
			txtTen.setColumns(10);
			txtTen.setBounds(202, 150, 350, 25);
			pForm.add(txtTen);

			JLabel lblLoai = new JLabel("Loại Sản Phẩm:");
			lblLoai.setFont(new Font("Arial", Font.BOLD, 16));
			lblLoai.setBounds(48, 194, 144, 36);
			pForm.add(lblLoai);

			cboxLoai = new JComboBox<>();
			cboxLoai.setBackground(new Color(255, 255, 255));
			cboxLoai.setFont(new Font("Arial", Font.PLAIN, 16));
			cboxLoai.addItem("Đồ ăn nhẹ");
			cboxLoai.addItem("Đồ uống");
			cboxLoai.addItem("Combo");
			cboxLoai.setBounds(202, 200, 350, 25);
			pForm.add(cboxLoai);

			JLabel lblDonGia = new JLabel("Đơn Giá:");
			lblDonGia.setFont(new Font("Arial", Font.BOLD, 16));
			lblDonGia.setBounds(48, 244, 144, 36);
			pForm.add(lblDonGia);

			txtDonGia = new JTextField();
			txtDonGia.setBackground(new Color(255, 255, 255));
			txtDonGia.setColumns(10);
			txtDonGia.setFont(new Font("Arial", Font.PLAIN, 16));
			txtDonGia.setBounds(202, 250, 350, 25);
			pForm.add(txtDonGia);

			JLabel lblMoTa = new JLabel("Mô Tả:");
			lblMoTa.setFont(new Font("Arial", Font.BOLD, 16));
			lblMoTa.setBounds(48, 294, 144, 36);
			pForm.add(lblMoTa);

			txtMoTa = new JTextField();
			txtMoTa.setBackground(new Color(255, 255, 255));
			txtMoTa.setFont(new Font("Arial", Font.PLAIN, 16));
			txtMoTa.setColumns(10);
			txtMoTa.setBounds(202, 300, 350, 25);
			pForm.add(txtMoTa);

			JLabel lblTrangThai = new JLabel("Trạng Thái:");
			lblTrangThai.setFont(new Font("Arial", Font.BOLD, 16));
			lblTrangThai.setBounds(48, 344, 144, 36);
			pForm.add(lblTrangThai);

			cboxTrangThai = new JComboBox<>();
			cboxTrangThai.addItem("Còn bán");
			cboxTrangThai.addItem("Nghỉ bán");
			cboxTrangThai.setBounds(202, 350, 350, 25);
			cboxTrangThai.setFont(new Font("Arial", Font.PLAIN, 16));
			cboxTrangThai.setBackground(new Color(255, 255, 255));
			pForm.add(cboxTrangThai);

			JLabel lblHinhAnh = new JLabel("Hình Ảnh:");
			lblHinhAnh.setFont(new Font("Arial", Font.BOLD, 16));
			lblHinhAnh.setBounds(48, 394, 144, 36);
			pForm.add(lblHinhAnh);

			txtHinhAnh = new JTextField();
			txtHinhAnh.setBackground(new Color(255, 255, 255));
			txtHinhAnh.setColumns(10);
			txtHinhAnh.setFont(new Font("Arial", Font.PLAIN, 16));
			txtHinhAnh.setBounds(202, 400, 350, 25);
			pForm.add(txtHinhAnh);

			btnThem = new JButton("Thêm");
			btnThem.setForeground(Color.WHITE);
			btnThem.setBackground(new Color(30, 144, 255));
			btnThem.setFont(new Font("Arial", Font.BOLD, 20));
			btnThem.setBounds(93, 570, 122, 44);
			pForm.add(btnThem);

			btnCapNhat = new JButton("Cập nhật");
			btnCapNhat.setForeground(Color.WHITE);
			btnCapNhat.setBackground(new Color(30, 144, 255));
			btnCapNhat.setFont(new Font("Arial", Font.BOLD, 20));
			btnCapNhat.setBounds(244, 570, 122, 44);
			pForm.add(btnCapNhat);

			btnXoaTrang = new JButton("Xóa trắng");
			btnXoaTrang.setForeground(Color.WHITE);
			btnXoaTrang.setFont(new Font("Arial", Font.BOLD, 20));
			btnXoaTrang.setBackground(new Color(30, 144, 255));
			btnXoaTrang.setBounds(394, 569, 144, 44);
			pForm.add(btnXoaTrang);

			btnCapNhat.addActionListener(this);
			btnThem.addActionListener(this);
			btnTim.addActionListener(this);
			btnXoaTrang.addActionListener(this);
			cboxLocTrangThai.addActionListener(this);
			cboxLocLoai.addActionListener(this);
			tableSP.addMouseListener(this);
			
			//Chỉnh màu chữ của cột, và xóa border cho từng cột
	        tableSP.getColumnModel().getColumn(0).setCellRenderer(new CustomColorRenderer1());
	        tableSP.getColumnModel().getColumn(1).setCellRenderer(new CustomColorRenderer1());
	        tableSP.getColumnModel().getColumn(2).setCellRenderer(new CustomColorRenderer1());
	        tableSP.getColumnModel().getColumn(3).setCellRenderer(new CustomColorRenderer1());
	        tableSP.getColumnModel().getColumn(4).setCellRenderer(new CustomColorRenderer1());
	        tableSP.getColumnModel().getColumn(5).setCellRenderer(new CustomColorRenderer1());
	        tableSP.getColumnModel().getColumn(6).setCellRenderer(new CustomColorRenderer1());
		    // Cập nhật cái cột đầu tiên để hiển thị được hình ảnh
	        tableSP.getColumnModel().getColumn(6).setCellRenderer(new ImageRenderer(100));
		}
	}

	private void locTheoTrangThai(String trangThai) {
		List<SanPham> list = sp_dao.getalltbSanPham();

		for (SanPham sp : list) {
			if (sp.getTrangThai().equalsIgnoreCase(trangThai))
				addModal(sp);
		}
	}

	private void locTheoLoai(String loai) {
		List<SanPham> list = sp_dao.getalltbSanPham();

		for (SanPham sp : list) {
			if (sp.getLoaiSP().equalsIgnoreCase(loai))
				addModal(sp);
		}
	}

	private void locTheoDieuKien(String trangThai, String loai) {
		List<SanPham> list = sp_dao.getalltbSanPham();

		for (SanPham sp : list) {
			if (trangThai.equalsIgnoreCase("Tất cả") && !loai.equalsIgnoreCase("Tất cả")) {
				locTheoLoai(loai);
				return;
			} else if (loai.equalsIgnoreCase("Tất cả") && !trangThai.equalsIgnoreCase("Tất cả")) {
				locTheoTrangThai(trangThai);
				return;
			} else if (sp.getTrangThai().equalsIgnoreCase(trangThai) && sp.getLoaiSP().equalsIgnoreCase(loai)) {
				addModal(sp);
			}
		}
	}

	private void docDuLieuArrayListVaoMode() {
		List<SanPham> list = sp_dao.getalltbSanPham();
		for (SanPham sp : list) {
			addModal(sp);
		}
	}

	private void demSoLuong() {
		int count = modelSP.getRowCount();
		txtSoLuong.setText(count + "");
	}

	private void addModal(SanPham sp) {
		List<SanPham> list = sp_dao.getalltbSanPham();
		ImageIcon imageIcon = new ImageIcon(sp.getHinhAnh().trim());
		Object[] rowData = { sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP(), sp.getDonGia(), sp.getMoTa(),
				sp.getTrangThai(), imageIcon };
		modelSP.addRow(rowData);

	}

	public class ImageRenderer extends DefaultTableCellRenderer {
		private int imageHeight;

		public ImageRenderer(int imageHeight) {
			this.imageHeight = imageHeight;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
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

	public class DescriptionRenderer extends JTextArea implements TableCellRenderer {
		public DescriptionRenderer() {
			setLineWrap(true);
			setWrapStyleWord(true);

			setFont(new Font("Tahoma", Font.PLAIN, 13));
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText((String) value);
			setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
			if (table.getRowHeight(row) < getPreferredSize().height) {
				table.setRowHeight(row, getPreferredSize().height);
			}
			return this;
		}
	}

	public JPanel getQuanLySP() {
		return contentPane;
	}

	private void xoaHetDuLieuTrenModel() {
		DefaultTableModel dm = (DefaultTableModel) tableSP.getModel();
		dm.getDataVector().removeAllElements();
	}

	private void xoaTrang() {
		textMaSP.setText("");
		txtDonGia.setText("");
		txtHinhAnh.setText("");
		cboxLoai.setSelectedIndex(0);
		txtMoTa.setText("");
		txtTen.setText("");
		cboxTrangThai.setSelectedIndex(0);
	}

	private void thongBaoLoi(String mess, JTextField txt) {
		txt.requestFocus();
		txt.selectAll();
		JOptionPane.showMessageDialog(this, mess);
	}

	private boolean kiemTraRegex() {
		if (textMaSP.getText().equals("") || txtDonGia.getText().equals("") || txtHinhAnh.getText().equals("")
				|| txtMoTa.getText().equals("") || txtTen.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
			return false;
		}
		String ma = textMaSP.getText().toUpperCase();
		double donGia = Double.parseDouble(txtDonGia.getText());
		if (!(ma.length() > 0 && ma.matches("^SP\\d{3}"))) {
			thongBaoLoi("Mã sản phẩm phải bắt đầu bằng SP và có 3 ký tự số (vd: SP000)", textMaSP);
			return false;
		}

		if (donGia <= 0) {
			thongBaoLoi("Đơn giá phải > 0", txtDonGia);
			return false;
		}

		return true;
	}

	private SanPham layThongTin() {
		String ma = textMaSP.getText().toUpperCase();
		String ten = txtTen.getText().toUpperCase();
		// Viết hoa chữ cái đầu
		ten = Arrays.stream(ten.split("\\s+")).map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
				.collect(Collectors.joining(" "));
		String moTa = txtMoTa.getText();
		ten = Arrays.stream(ten.split("\\s+")).map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
				.collect(Collectors.joining(" "));
		double donGia = Double.parseDouble(txtDonGia.getText());
		String hinhAnh = txtHinhAnh.getText();
		String trangThai = cboxTrangThai.getSelectedItem().toString();
		String loai = cboxLoai.getSelectedItem().toString();

		SanPham sp = new SanPham(ma, ten, loai, donGia, moTa, trangThai, hinhAnh);

		return sp;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int dongDuocChon = tableSP.getSelectedRow();
		textMaSP.setText(modelSP.getValueAt(dongDuocChon, 0).toString());
		txtTen.setText(modelSP.getValueAt(dongDuocChon, 1).toString());
		cboxLoai.setSelectedItem(modelSP.getValueAt(dongDuocChon, 2).toString());
		txtDonGia.setText(modelSP.getValueAt(dongDuocChon, 3).toString());
		txtMoTa.setText(modelSP.getValueAt(dongDuocChon, 4).toString());
		cboxTrangThai.setSelectedItem(modelSP.getValueAt(dongDuocChon, 5).toString());
		txtHinhAnh.setText(modelSP.getValueAt(dongDuocChon, 6).toString());

	}

	private void timKiemTheoMa() {
		String maCanTim = textTimMa.getText();
		if (maCanTim != null && maCanTim.trim().length() > 0) {
			try {
				SanPham sp = sp_dao.timKiemSP(maCanTim);
				if (sp != null) {
					xoaHetDuLieuTrenModel();
					addModal(sp);
				} else {
					docDuLieuArrayListVaoMode();
					xoaHetDuLieuTrenModel();
					return;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Du lieu khong hop le!");
				textTimMa.selectAll(); // chọn toàn bộ văn bản
				textTimMa.requestFocus(); // đặt trọng tâm vào 1 JTextField
			}
		} else {
			xoaHetDuLieuTrenModel();
			docDuLieuArrayListVaoMode();
			tableSP.setModel(modelSP);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		SanPham sp;
		if (o.equals(btnTim)) {
			xoaHetDuLieuTrenModel();
			for (SanPham sanPham : timKiemReGexSP(textTimMa.getText().trim())) {
		            ImageIcon imageIcon = new ImageIcon(sanPham.getHinhAnh().trim());
		            Object[] rowData = { sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getLoaiSP(), sanPham.getDonGia(),
		            		sanPham.getMoTa(), sanPham.getTrangThai(), imageIcon};
		            modelSP.addRow(rowData);
		            demSoLuong();
		    }
				
			

		} else if (o.equals(cboxLocTrangThai)) {

			String trangThai = cboxLocTrangThai.getSelectedItem().toString();
			String loai = cboxLocLoai.getSelectedItem().toString();
			if (loai.equalsIgnoreCase("Tất cả") && trangThai.equalsIgnoreCase("Tất cả")) {
				xoaHetDuLieuTrenModel();
				docDuLieuArrayListVaoMode();
				demSoLuong();
			} else {
				xoaHetDuLieuTrenModel();
				locTheoDieuKien(trangThai, loai);
				demSoLuong();
			}
		} else if (o.equals(cboxLocLoai)) {
			String loai = cboxLocLoai.getSelectedItem().toString();
			String trangThai = cboxLocTrangThai.getSelectedItem().toString();

			if (loai.equalsIgnoreCase("Tất cả") && trangThai.equalsIgnoreCase("Tất cả")) {
				xoaHetDuLieuTrenModel();
				docDuLieuArrayListVaoMode();
				demSoLuong();
			} else {
				xoaHetDuLieuTrenModel();
				locTheoDieuKien(trangThai, loai);
				demSoLuong();
			}
		} else if (o.equals(btnXoaTrang)) {
			xoaTrang();
		} else if (o.equals(btnThem)) {
			tableSP.clearSelection();
			if (kiemTraRegex()) {
				sp = layThongTin();

				if (!sp_dao.kiemTraSP(sp)) {
					thongBaoLoi("Trùng mã", textMaSP);
					return;
				}

				try {
					if (sp_dao.taoSP(sp)) {
						JOptionPane.showMessageDialog(this, "Thêm thành công!");
						addModal(sp);
						demSoLuong();
					} else
						JOptionPane.showMessageDialog(this, "Thêm không thành công!");

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Trung ma");
				}
			}

			xoaTrang();
		} else if (o.equals(btnCapNhat)) {
//			if (kiemTraRegex()) {
			String maCanCN = textMaSP.getText();
			sp = sp_dao.timKiemSP(maCanCN);
			if (sp != null) {
				try {
					sp = layThongTin();
					if (sp_dao.capNhat(sp)) {
						tableSP.clearSelection();
						JOptionPane.showMessageDialog(this, "Cập nhật thành công");
					} else
						JOptionPane.showMessageDialog(this, "Cập nhật không thành công!");

					xoaHetDuLieuTrenModel();
					docDuLieuArrayListVaoMode();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Trung ma");
				}
			} else {
				thongBaoLoi("Không tồn tại mã " + maCanCN, textMaSP);
			}

		}

		xoaTrang();
	}
	public ArrayList<SanPham> timKiemReGexSP(String s){
		ArrayList<SanPham> listSp = new ArrayList<SanPham>();
		
		String regex = ".*" + s.toUpperCase() + ".*";
		
		for(SanPham sp : sp_dao.getalltbSanPham()) {
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
    //DefaultTableModel không thể chứa các đối tượng không phải là các kiểu dữ liệu cơ bản như String, Integer, v.v. Nên dùng hàm này mới chứa được
    public class ImageRenderer1 extends DefaultTableCellRenderer {
        private int imageHeight;

        public ImageRenderer1(int imageHeight) {
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
}
