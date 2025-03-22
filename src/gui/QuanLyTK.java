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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import connectDB.ConnectDB;
import dao.nhanVien_DAO;
import dao.taiKhoan_DAO;
import entity.NhanVien;
import entity.TaiKhoan;


public class QuanLyTK extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMaTK;
	private JTextField textTen;
	private JTextField textMatKhau;
	private Button btnThem;
	private Button btnCapNhat;
	private JLabel lblTim;
	private JTextField textTimMa;
	private Button btnTim;
	private JLabel lblquyen;
	private JLabel lblTThai;
	private JComboBox<String> cboxLocQuyen;
	private JComboBox<String> cboxLocTrangThai;
	private JPanel pTable;
	private nhanVien_DAO nv_dao;
	private taiKhoan_DAO tk_dao;
	private Button btnXoaTrang;
	private JTextField textMaNV;
	private JComboBox<String> cboxTrangThai;
	private JComboBox<String> cboxQuyen;
	private DefaultTableModel modelTK;
	private JTable tableTK;
	private JScrollPane panelTK;
	public QuanLyTK() {
		try {
			ConnectDB.getInstance().connect();
//			System.out.println("Connect");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		nv_dao = new nhanVien_DAO();
		tk_dao = new taiKhoan_DAO();
		
		nv_dao.getalltbNhanVien();
//		tk_dao.getalltbTaiKhoan();
		
		setTitle("Quản Lý Nhân Viên");
		setFont(new Font("Dialog", Font.PLAIN, 77));
		setSize(1059, 747);
		setResizable(false);
		this.setIconImage(new ImageIcon("image/logo.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel(); //JPanel gốc, muốn tạo hay thêm các thành phần gì thì thêm vào ở đây
		contentPane.setBackground(Color.WHITE);
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
		pList.setBounds(0, 0, 824, 742);
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
		btnTim.setBounds(225, 69, 78, 25);
		pList.add(btnTim);
		
		lblquyen = new JLabel("Quyền");
		lblquyen.setFont(new Font("Arial", Font.BOLD, 18));
		lblquyen.setBounds(353, 31, 120, 34);
		pList.add(lblquyen);
		
		cboxLocQuyen = new JComboBox<>();
		cboxLocQuyen.setBackground(new Color(255, 255, 255));
		cboxLocQuyen.addItem("Tất cả");
		cboxLocQuyen.addItem("Admin");
		cboxLocQuyen.addItem("Manager");
		cboxLocQuyen.addItem("Employee");
		cboxLocQuyen.setBounds(353, 69, 193, 25);
		cboxLocQuyen.setFont(new Font("Arial", Font.PLAIN, 16));
		pList.add(cboxLocQuyen);
	
		lblTThai = new JLabel("Trạng thái");
		lblTThai.setFont(new Font("Arial", Font.BOLD, 18));
		lblTThai.setBounds(605, 31, 120, 34);
		pList.add(lblTThai);
		
		cboxLocTrangThai = new JComboBox<>();
		cboxLocTrangThai.setBackground(new Color(255, 255, 255));
		cboxLocTrangThai.addItem("Tất cả");
		cboxLocTrangThai.addItem("Còn sử dụng");
		cboxLocTrangThai.addItem("Không sử dụng");
		cboxLocTrangThai.setBounds(605, 69, 193, 25);
		cboxLocTrangThai.setFont(new Font("Arial", Font.PLAIN, 16));
		pList.add(cboxLocTrangThai);
		
		// Phần bảng
		pTable = new JPanel();
		pTable.setBackground(Color.WHITE);
		pTable.setBounds(26, 120, 772, 611);
		pList.add(pTable);
		
		String[] header = {"Mã tài khoản", "Tên tài khoản", "Mã nhân viên", "Quyền", "Trạng thái"};
		modelTK = new DefaultTableModel(header, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		modelTK.setColumnIdentifiers(header); 
		
		tableTK = new JTable(modelTK);
		panelTK = new JScrollPane(tableTK);
		panelTK.setBounds(0, 5, 772, 611);
		panelTK.setPreferredSize(new Dimension(772, 520));
		
		JTableHeader headerTable = tableTK.getTableHeader();
		Font fontHeader = new Font("Tahoma", Font.BOLD, 13); // Font chữ "Arial", đậm, kích thước 14
		headerTable.setFont(fontHeader);
		headerTable.setPreferredSize(new Dimension(tableTK.getWidth(), 40));
		
		tableTK.setRowHeight(40);
		tableTK.setFont(new Font("Tahoma", Font.PLAIN, 14));
		for (int i = 0; i <= 4; i++) {
			tableTK.getColumnModel().getColumn(i).setMinWidth(154);
			tableTK.getColumnModel().getColumn(i).setMaxWidth(154);
		}
		pTable.setLayout(null);

		pTable.add(panelTK);
		
		docDuLieuArrayListVaoMode();
		
		
		// phần form
		JPanel pForm = new JPanel();
		pForm.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pForm.setBounds(826, 0, 605, 742);
		contentPane.add(pForm);
		pForm.setLayout(null);
		
		JLabel lblTitle = new JLabel("Quản Lý Tài Khoản");
		lblTitle.setBounds(171, 53, 306, 31);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 33));
		pForm.add(lblTitle);
		
		JLabel lblMaTK = new JLabel("Mã tài khoản:");
		lblMaTK.setFont(new Font("Arial", Font.BOLD, 20));
		lblMaTK.setBounds(48, 118, 144, 36);
		pForm.add(lblMaTK);
		
		textMaTK = new JTextField();
		textMaTK.setFont(new Font("Arial", Font.PLAIN, 19));
		textMaTK.setBounds(202, 120, 350, 36);
		pForm.add(textMaTK);
		textMaTK.setColumns(10);
		
		textMaTK.addActionListener(e -> SwingUtilities.invokeLater(() -> textTen.requestFocus()));

		JLabel lblTen = new JLabel("Tên tài khoản:");
		lblTen.setFont(new Font("Arial", Font.BOLD, 20));
		lblTen.setBounds(48, 185, 144, 36);
		pForm.add(lblTen);
		
		textTen = new JTextField();
		textTen.setFont(new Font("Arial", Font.PLAIN, 19));
		textTen.setColumns(10);
		textTen.setBounds(202, 187, 350, 36);
		pForm.add(textTen);
		
		textTen.addActionListener(e -> SwingUtilities.invokeLater(() -> textMatKhau.requestFocus()));
		
		JLabel lblQuyen = new JLabel("Quyền: ");
		lblQuyen.setFont(new Font("Arial", Font.BOLD, 20));
		lblQuyen.setBounds(48, 393, 144, 36);
		pForm.add(lblQuyen);
		
		textMatKhau = new JTextField();
		textMatKhau.setFont(new Font("Arial", Font.PLAIN, 19));
		textMatKhau.setColumns(10);
		textMatKhau.setBounds(202, 256, 350, 36);
		pForm.add(textMatKhau);
		
		textMatKhau.addActionListener(e -> SwingUtilities.invokeLater(() -> textMaNV.requestFocus()));
		
		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		lblMaNV.setFont(new Font("Arial", Font.BOLD, 20));
		lblMaNV.setBounds(48, 323, 144, 36);
		pForm.add(lblMaNV);
		
		cboxTrangThai = new JComboBox<>();
		cboxTrangThai.addItem("Còn sử dụng");
		cboxTrangThai.addItem("Không sử dụng");
		cboxTrangThai.setBounds(202, 468, 350, 36);
		cboxTrangThai.setFont(new Font("Arial", Font.PLAIN, 19));
		cboxTrangThai.setBackground(new Color(240, 240, 240));
		pForm.add(cboxTrangThai);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Arial", Font.BOLD, 20));
		lblTrangThai.setBounds(48, 466, 144, 36);
		pForm.add(lblTrangThai);
		
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
		
		textMaNV = new JTextField();
		textMaNV.setFont(new Font("Arial", Font.PLAIN, 19));
		textMaNV.setColumns(10);
		textMaNV.setBounds(202, 325, 350, 36);
		pForm.add(textMaNV);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu:");
		lblMatKhau.setFont(new Font("Arial", Font.BOLD, 20));
		lblMatKhau.setBounds(48, 254, 144, 36);
		pForm.add(lblMatKhau);
		
		cboxQuyen = new JComboBox<>();
		cboxQuyen.addItem("Admin");
		cboxQuyen.addItem("Manager");
		cboxQuyen.addItem("Employee");
		cboxQuyen.setBounds(202, 395, 350, 36);
		cboxQuyen.setFont(new Font("Arial", Font.PLAIN, 19));
		cboxQuyen.setBackground(new Color(240, 240, 240));
		pForm.add(cboxQuyen);
		
		textMaTK.addActionListener(this);
		textMatKhau.addActionListener(this);
		textTen.addActionListener(this);
		textMaNV.addActionListener(this);
		
		btnCapNhat.addActionListener(this);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		
		tableTK.addMouseListener(this); 
		
		cboxLocQuyen.addActionListener(this);
		cboxLocTrangThai.addActionListener(this);
		
		//Chỉnh màu chữ của cột, và xóa border cho từng cột
        tableTK.getColumnModel().getColumn(0).setCellRenderer(new CustomColorRenderer1());
        tableTK.getColumnModel().getColumn(1).setCellRenderer(new CustomColorRenderer1());
        tableTK.getColumnModel().getColumn(2).setCellRenderer(new CustomColorRenderer1());
        tableTK.getColumnModel().getColumn(3).setCellRenderer(new CustomColorRenderer1());
        tableTK.getColumnModel().getColumn(4).setCellRenderer(new CustomColorRenderer1());
		
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int dongDuocChon = tableTK.getSelectedRow();
		
		textMaTK.setText(tableTK.getValueAt(dongDuocChon, 0).toString());
		textTen.setText(tableTK.getValueAt(dongDuocChon, 1).toString());
		textMaNV.setText(tableTK.getValueAt(dongDuocChon, 2).toString());
		cboxQuyen.setSelectedItem(tableTK.getValueAt(dongDuocChon, 3).toString());
		cboxTrangThai.setSelectedItem(tableTK.getValueAt(dongDuocChon, 4).toString());
		
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
			tableTK.clearSelection();
			cboxLocQuyen.setSelectedIndex(0);
			cboxLocTrangThai.setSelectedIndex(0);
			timKiemTheoMa();
			textTimMa.setText("");
			
		}
		
		else if (o.equals(btnXoaTrang)) {
			tableTK.clearSelection();
			xoaTrang();			
		}
	
		else if (o.equals(btnCapNhat)) {
			tableTK.clearSelection();
			
			if (kiemTraRegex()) {
				String maCanCN = textMaTK.getText();
				tk = tk_dao.TimTK(maCanCN);
				nv = nv_dao.timKiemNV(tk.getNv().getMaNV());
				
				if ( tk != null) {
					try {
						tk = layThongTin();
						if (tk_dao.capNhat(tk)) {
							if (nv != null)
								tk_dao.capNhatTrangThaiNV(maCanCN, tk.getTrangThai());
							
							JOptionPane.showMessageDialog(this, "Cập nhật thành công");
							tableTK.clearSelection();
						}
						else
							JOptionPane.showMessageDialog(this, "Cập nhật không thành công!");
						
						xoaHetDuLieuTrenModel();
						docDuLieuArrayListVaoMode();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(this, "Trung ma");
					}
				}
				else
					JOptionPane.showMessageDialog(this, "Không tồn tại mã " + maCanCN);
			}
			
			xoaTrang();
		}
		else if (o.equals(btnThem)) {
			tableTK.clearSelection();
			
			
			if (kiemTraRegex()) {
				
				if (textMatKhau.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
					return;
				}
				tk = layThongTin();
				nv = new NhanVien(tk.getNv().getMaNV());
				
				if (!tk_dao.kiemTraTK(tk)) {
					thongBaoLoi("Trùng mã tài khoản", textMaTK);
					return;
				}
				
				if (!nv_dao.kiemTraNV(nv)) {
					thongBaoLoi("Trùng mã nhân viên", textMaNV);
					return;
				} 
				
				try {
					if (tk_dao.taoTK(tk)) {
						JOptionPane.showMessageDialog(this, "Thêm thành công!");
						addModal(tk);
					}
					else {
						JOptionPane.showMessageDialog(this, "Thêm không thành công!");
					}
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			xoaTrang();
		}
		
		else if (o.equals(cboxLocQuyen)) {
			String quyen = cboxLocQuyen.getSelectedItem().toString();
			String trangThai = cboxLocTrangThai.getSelectedItem().toString();
			
			if (quyen.equalsIgnoreCase("Tất cả") && trangThai.equalsIgnoreCase("Tất cả")) {
				xoaHetDuLieuTrenModel();
				docDuLieuArrayListVaoMode();
			} else {
				xoaHetDuLieuTrenModel();
				locTheoDieuKien(quyen, trangThai);
			}
		}
		else if (o.equals(cboxLocTrangThai)) {
			String quyen = cboxLocQuyen.getSelectedItem().toString();
			String trangThai = cboxLocTrangThai.getSelectedItem().toString();
			
			if (quyen.equalsIgnoreCase("Tất cả") && trangThai.equalsIgnoreCase("Tất cả")) {
				xoaHetDuLieuTrenModel();
				docDuLieuArrayListVaoMode();
			} else {
				xoaHetDuLieuTrenModel();
				locTheoDieuKien(quyen, trangThai);
			}
		}
	}
	
	private void xoaTrang() {
		textMatKhau.setText("");
		textMaTK.setText("");
		textMatKhau.setText("");
		textTen.setText("");
		cboxQuyen.setSelectedIndex(0);
		cboxTrangThai.setSelectedIndex(0);
	}
	
	private void docDuLieuArrayListVaoMode() {
		List<TaiKhoan> list = tk_dao.getalltbTaiKhoan();
		
		for (TaiKhoan tk : list) {
			addModal(tk);
		}
	}
	
	private void addModal(TaiKhoan tk) {
		
		modelTK.addRow(new Object[] {
				tk.getMaTK(),
				tk.getTenTK(),
				tk.getNv().getMaNV(),
				tk.getQuyen(),
				tk.getTrangThai()
		});
	}
	
	private void  thongBaoLoi(String mess, JTextField txt ) {
		txt.requestFocus();
		txt.selectAll();
		JOptionPane.showMessageDialog(this, mess);
	}
	
	private void xoaHetDuLieuTrenModel() {
		DefaultTableModel dm = (DefaultTableModel) tableTK.getModel();
		dm.getDataVector().removeAllElements();
//		DefaultTableModel dm = (DefaultTableModel) tableTK.getModel();
//	    dm.setRowCount(0);
//	    dm.fireTableDataChanged();
	}
	
	private TaiKhoan layThongTin() {
		String maTK = textMaTK.getText();
		String ten = textTen.getText();
		
		String matKhau = null;
		if (!textMatKhau.getText().equals("")) {
			matKhau = textMatKhau.getText();
			matKhau = MaHoa.toSHA1(matKhau);
		}
		String maNV = textMaNV.getText();
		NhanVien nv = new NhanVien(maNV);
		String quyen = cboxQuyen.getSelectedItem().toString();
		String trangThai = cboxTrangThai.getSelectedItem().toString();
	
		TaiKhoan tk = new TaiKhoan(maTK, ten, matKhau, quyen, nv, trangThai);
		
		return tk;
	}
	
	private void timKiemTheoMa() {
		String maCanTim = textTimMa.getText();
		
		if (maCanTim != null && maCanTim.trim().length() > 0) {
			try {
				TaiKhoan tk = tk_dao.TimTK(maCanTim);
				if (tk != null) {
					xoaHetDuLieuTrenModel();
					addModal(tk);
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
			tableTK.setModel(modelTK);
		}
	}
	
	private boolean kiemTraRegex() {
		if (textMaNV.getText().equals("") || textMaTK.getText().equals("") || textTen.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
			return false;
		}
		
		String maTK = textMaTK.getText();
		String maNV = textMaNV.getText();
		
		if (!(maTK.length() > 0 && maTK.matches("^TK\\d{3}"))) {
			thongBaoLoi("Mã tài khoản phải bắt đầu bằng TK và có 3 ký tự số (vd: TK000)", textMaTK);
			return false;
		}
		
		if (!(maNV.length() > 0 && maNV.matches("^NV\\d{3}"))) {
			thongBaoLoi("Mã nhân viên phải bắt đầu bằng NV và có 3 ký tự số (vd: NV000)", textMaNV);
			return false;
		}
		
		return true;
	}

	private void locTheoQuyen(String quyen) {
		List<TaiKhoan> list = tk_dao.getalltbTaiKhoan();
		
		for (TaiKhoan tk : list) {
			if (tk.getQuyen().equalsIgnoreCase(quyen))
				addModal(tk);
		}
	}
	
	private void locTheoTrangThai(String trangThai) {
		List<TaiKhoan> list = tk_dao.getalltbTaiKhoan();
		
		for (TaiKhoan tk : list) {
			if (tk.getTrangThai().equalsIgnoreCase(trangThai))
				addModal(tk);
		}
	}
	
	private void locTheoDieuKien(String quyen, String trangThai) {
		List<TaiKhoan> list = tk_dao.getalltbTaiKhoan();
		
		for (TaiKhoan tk : list) {
			if (trangThai.equalsIgnoreCase("Tất cả") && !quyen.equalsIgnoreCase("Tất cả")) {
				locTheoQuyen(quyen);
				return;
			}
			else if (quyen.equalsIgnoreCase("Tất cả")  && !trangThai.equalsIgnoreCase("Tất cả")) {
				locTheoTrangThai(trangThai);
				return;
			}
			else if (tk.getTrangThai().equalsIgnoreCase(trangThai) && tk.getQuyen().equalsIgnoreCase(quyen)) {
				addModal(tk);
			}
		}		
		
	}
	public JPanel getQuanLyTK() {
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
