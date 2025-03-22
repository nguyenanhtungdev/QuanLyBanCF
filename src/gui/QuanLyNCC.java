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
import javax.swing.table.TableColumn;

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
import dao.NhaCungCap_DAO;
import dao.nhanVien_DAO;
import dao.taiKhoan_DAO;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.TaiKhoan;
import gui.QuanLyDH.CustomColorRenderer1;


public class QuanLyNCC extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMa;
	private JTextField textTen;
	private JTextField textEmail;
	private JTextField textSDT;
	private JTextField textDC;
	private JComboBox<String>  cboxTrangThai;
	private Button btnThem;
	private Button btnCapNhat;
	private JLabel lblTim;
	private JTextField textTimMa;
	private Button btnTim;
	private JLabel lblTThai;
	private JComboBox<String> cboxLocTrangThai;
	private JPanel pTable;
	private Button btnXoaTrang;
	private NhaCungCap_DAO ncc_dao;
	private DefaultTableModel modelNCC;
	private JTable tableNCC;
	private JScrollPane panelNCC;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyNCC frame = new QuanLyNCC();
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
	public QuanLyNCC() {
		try {
			ConnectDB.getInstance().connect();
//			System.out.println("Connect");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ncc_dao = new NhaCungCap_DAO() ;
		
		setTitle("Quản Lý Nhà Cung cấp");
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
		
		lblTim = new JLabel("Mã nhà cung cấp");
		lblTim.setBounds(26, 31, 164, 34);
		lblTim.setFont(new Font("Arial", Font.BOLD, 18));
		pList.add(lblTim);
		
		textTimMa = new JTextField();
		textTimMa.setBounds(26, 69, 193, 25);
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
		
		btnTim = new Button("Tìm");
		btnTim.setBounds(225, 69, 68, 25);
		btnTim.setForeground(Color.WHITE);
		btnTim.setBackground(new Color(30,144,255));
		btnTim.setFont(new Font("Arial", Font.BOLD, 14));
		pList.add(btnTim);
	
		lblTThai = new JLabel("Trạng thái");
		lblTThai.setBounds(352, 31, 120, 34);
		lblTThai.setFont(new Font("Arial", Font.BOLD, 18));
		pList.add(lblTThai);
		
		cboxLocTrangThai = new JComboBox<>();
		cboxLocTrangThai.setBounds(352, 69, 193, 25);
		cboxLocTrangThai.setBackground(new Color(255, 255, 255));
		cboxLocTrangThai.addItem("Tất cả");
		cboxLocTrangThai.addItem("Còn cung cấp");
		cboxLocTrangThai.addItem("Không cung cấp");
		cboxLocTrangThai.setFont(new Font("Arial", Font.PLAIN, 16));
		pList.add(cboxLocTrangThai);
		
		// Phần bảng
		pTable = new JPanel();
		pTable.setBounds(25, 120, 770, 595);
		pList.add(pTable);
		
		String[] header = {"Mã NCC", "Tên NCC", "Email", "Số điện thoại", "Địa chỉ", "Trạng thái"};
		modelNCC = new DefaultTableModel(header, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		modelNCC.setColumnIdentifiers(header); 
		tableNCC = new JTable(modelNCC);
		panelNCC = new JScrollPane(tableNCC);
		panelNCC.setBounds(0, 5, 770, 590);
		panelNCC.setPreferredSize(new Dimension(772, 520));
		
		JTableHeader headerTable = tableNCC.getTableHeader();
		Font fontHeader = new Font("Tahoma", Font.BOLD, 13); // Font chữ "Arial", đậm, kích thước 14
		headerTable.setFont(fontHeader);
		headerTable.setPreferredSize(new Dimension(tableNCC.getWidth(), 40));
		
		tableNCC.setRowHeight(40);
		tableNCC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		for (int i = 0; i <= 5; i++) {
			tableNCC.getColumnModel().getColumn(i).setMinWidth(128);
			tableNCC.getColumnModel().getColumn(i).setMaxWidth(128);
		}
		pTable.setLayout(null);

		pTable.add(panelNCC);
		
		docDuLieuArrayListVaoMode();
		
		
		// phần form
		JPanel pForm = new JPanel();
		pForm.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pForm.setBounds(826, 0, 605, 726);
		contentPane.add(pForm);
		pForm.setLayout(null);
		
		JLabel lblTitle = new JLabel("Quản Lý Nhà Cung Cấp");
		lblTitle.setBounds(110, 40, 375, 44);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 33));
		pForm.add(lblTitle);
		
		JLabel lblMa = new JLabel("Mã nhà cung cấp:");
		lblMa.setFont(new Font("Arial", Font.BOLD, 18));
		lblMa.setBounds(48, 119, 178, 36);
		pForm.add(lblMa);
		
		textMa = new JTextField();
		textMa.setFont(new Font("Arial", Font.PLAIN, 16));
		textMa.setBounds(221, 119, 331, 31);
		pForm.add(textMa);
		textMa.setColumns(10);
		
		textMa.addActionListener(e -> SwingUtilities.invokeLater(() -> textTen.requestFocus()));
		
//		TableColumn firstNameColumn = tableNCC.getColumnModel().getColumn(1);
//	    firstNameColumn.setPreferredWidth(150);
		
		JLabel lblTen = new JLabel("Tên nhà cung cấp:");
		lblTen.setFont(new Font("Arial", Font.BOLD, 18));
		lblTen.setBounds(48, 178, 178, 36);
		pForm.add(lblTen);
		
		textTen = new JTextField();
		textTen.setFont(new Font("Arial", Font.PLAIN, 16));
		textTen.setColumns(10);
		textTen.setBounds(221, 181, 331, 31);

		pForm.add(textTen);
		
		textTen.addActionListener(e -> SwingUtilities.invokeLater(() -> textEmail.requestFocus()));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 18));
		lblEmail.setBounds(48, 244, 178, 36);
		pForm.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		textEmail.setColumns(10);
		textEmail.setBounds(221, 247, 331, 31);
		pForm.add(textEmail);
		
		
		JLabel lblSDT = new JLabel("Số điện thoại: ");
		lblSDT.setFont(new Font("Arial", Font.BOLD, 18));
		lblSDT.setBounds(48, 311, 144, 36);
		pForm.add(lblSDT);
		
		textSDT = new JTextField();
		textSDT.setFont(new Font("Arial", Font.PLAIN, 16));
		textSDT.setColumns(10);
		textSDT.setBounds(221, 314, 331, 31);
		pForm.add(textSDT);
		
		textSDT.addActionListener(e -> SwingUtilities.invokeLater(() -> textDC.requestFocus()));
		
		JLabel lblDC = new JLabel("Địa chỉ:");
		lblDC.setFont(new Font("Arial", Font.BOLD, 18));
		lblDC.setBounds(48, 376, 144, 36);
		pForm.add(lblDC);
		
		textDC = new JTextField();
		textDC.setFont(new Font("Arial", Font.PLAIN, 16));
		textDC.setColumns(10);
		textDC.setBounds(221, 376, 331, 31);
		pForm.add(textDC);
		
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(new Font("Arial", Font.BOLD, 18));
		lblTrangThai.setBounds(48, 441, 144, 36);
		pForm.add(lblTrangThai);
		
		cboxTrangThai = new JComboBox<>();
		cboxTrangThai.addItem("Còn cung cấp");
		cboxTrangThai.addItem("Không cung cấp");
		cboxTrangThai.setBounds(221, 444, 331, 31);
		cboxTrangThai.setFont(new Font("Arial", Font.PLAIN, 18));
		cboxTrangThai.setBackground(new Color(240, 240, 240));
		pForm.add(cboxTrangThai);
		
		btnThem = new Button("Thêm");
		btnThem.setBackground(new Color(30,144,255));
		btnThem.setFont(new Font("Arial", Font.PLAIN, 20));
		btnThem.setBounds(93, 570, 122, 44);
		pForm.add(btnThem);
		
		btnCapNhat = new Button("Cập nhật"); 
		btnCapNhat.setBackground(new Color(30,144,255));
		btnCapNhat.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCapNhat.setBounds(244, 570, 122, 44);
		pForm.add(btnCapNhat);
		
		btnXoaTrang = new Button("Xóa trắng");
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnXoaTrang.setBackground(new Color(30,144,255));
		btnXoaTrang.setBounds(393, 570, 122, 44);
		pForm.add(btnXoaTrang);
		
		// đăng ký sự kiện
		textTen.addActionListener(this);
		textDC.addActionListener(this);
		textMa.addActionListener(this);
		
		textSDT.addActionListener(this);
		textEmail.addActionListener(this);
		
		btnCapNhat.addActionListener(this);
		btnThem.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		
		tableNCC.addMouseListener(this);
		cboxLocTrangThai.addActionListener(this);
		
		//Chỉnh màu chữ của cột, và xóa border cho từng cột
        tableNCC.getColumnModel().getColumn(0).setCellRenderer(new CustomColorRenderer1());
        tableNCC.getColumnModel().getColumn(1).setCellRenderer(new CustomColorRenderer1());
        tableNCC.getColumnModel().getColumn(2).setCellRenderer(new CustomColorRenderer1());
        tableNCC.getColumnModel().getColumn(3).setCellRenderer(new CustomColorRenderer1());
        tableNCC.getColumnModel().getColumn(4).setCellRenderer(new CustomColorRenderer1());
        tableNCC.getColumnModel().getColumn(5).setCellRenderer(new CustomColorRenderer1());
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int dongDuocChon = tableNCC.getSelectedRow();
		
		textMa.setText(modelNCC.getValueAt(dongDuocChon, 0).toString());
		textTen.setText(modelNCC.getValueAt(dongDuocChon, 1).toString());
		textEmail.setText(modelNCC.getValueAt(dongDuocChon, 2).toString());
		textSDT.setText(modelNCC.getValueAt(dongDuocChon, 3).toString());
		textDC.setText(modelNCC.getValueAt(dongDuocChon, 4).toString());
		cboxTrangThai.setSelectedItem(modelNCC.getValueAt(dongDuocChon, 5).toString());
		
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
		
		NhaCungCap ncc;
		
		if (o.equals(btnTim)) {
			tableNCC.clearSelection();
			cboxLocTrangThai.setSelectedIndex(0);
			timKiemTheoMa();
			textTimMa.setText("");
	
		}
		else if (o.equals(btnXoaTrang)) {
			tableNCC.clearSelection();
			xoaTrang();			
		}
		else if (o.equals(btnThem)) {
			tableNCC.clearSelection();
			
			
				ncc  = layThongTin();
				
				if (!ncc_dao.kiemTraNCC(ncc)) {
					thongBaoLoi("Trùng mã", textMa);
					return;
				}
			
				try {
					if (ncc_dao.taoNCC(ncc)) {
							JOptionPane.showMessageDialog(this, "Thêm thành công!");
							addModal(ncc);
					}
					else
						JOptionPane.showMessageDialog(this, "Thêm không thành công!");
					
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Trung ma");
				}
				xoaTrang();
			}

		else if (o.equals(btnCapNhat)) {
//			tableNV.clearSelection();
			
				String maCanCN = textMa.getText();
				ncc = ncc_dao.timKiemNCC(maCanCN);
		
				
				if (ncc != null ) {
					try {
						ncc = layThongTin();
						if (ncc_dao.capNhat( ncc)) {
							tableNCC.clearSelection();
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
				xoaTrang();
			}
			
		else if (o.equals(cboxLocTrangThai)) {
			String trangThai = cboxLocTrangThai.getSelectedItem().toString();
			
			if (trangThai.equalsIgnoreCase("Tất cả")) {
				xoaHetDuLieuTrenModel();
				docDuLieuArrayListVaoMode();
			} else {
				xoaHetDuLieuTrenModel();
				locTheoTrangThai(trangThai);
			}
		}
	}
	
	
	private void xoaTrang() {
		textTen.setText("");
		textDC.setText("");
		textMa.setText("");
		textSDT.setText("");
		textEmail.setText("");
		cboxTrangThai.setSelectedIndex(0);
		
	}
	
	private void docDuLieuArrayListVaoMode() {
		List<NhaCungCap> list = ncc_dao.getalltbNhaCungCap();
		
		for (NhaCungCap ncc : list) {
			addModal(ncc);
		}
	}
	
	private void addModal(NhaCungCap ncc) {
		
		modelNCC.addRow(new Object[] {
				ncc.getMaNCC(),
				ncc.getTenNCC(),
				ncc.getEmailNCC(),
				ncc.getSdtNCC(),
				ncc.getDiaChiNCC(),
				ncc.getTrangThai()
		});
	}
	
	private void  thongBaoLoi(String mess, JTextField txt ) {
		txt.requestFocus();
		txt.selectAll();
		JOptionPane.showMessageDialog(this, mess);
	}
	
	private void xoaHetDuLieuTrenModel() {
		DefaultTableModel dm = (DefaultTableModel) tableNCC.getModel();
		dm.getDataVector().removeAllElements();
	}
	
	private void timKiemTheoMa() {
		String maCanTim = textTimMa.getText();
		if (maCanTim != null && maCanTim.trim().length() > 0) {
			try {
				NhaCungCap ncc = ncc_dao.timKiemNCC(maCanTim);
				if (ncc != null) {
					xoaHetDuLieuTrenModel();
					addModal(ncc);
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
			tableNCC.setModel(modelNCC);
		}
	}
	
	private NhaCungCap layThongTin() {
		String ma = textMa.getText();
		String ho = textTen.getText();
		String email = textEmail.getText();
		String sdt = textSDT.getText();
		String diaChi = textDC.getText();
		String trangThaiString = cboxTrangThai.getSelectedItem().toString();

		NhaCungCap ncc = new NhaCungCap(ma, ho, email, sdt, diaChi, trangThaiString);
		
		return ncc;
	}
	
//	private void locTheoChucVu(String chucVu) {
//		List<NhaCungCap> list = ncc_dao.getalltbNhaCungCap();
//		
//		for (NhaCungCap ncc : list) {
//			if (ncc.get().equalsIgnoreCase(chucVu))
//				addModal(ncc);
//		}
//	}
//	
	private void locTheoTrangThai(String trangThai) {
		List<NhaCungCap> list = ncc_dao.getalltbNhaCungCap();
		
		for (NhaCungCap ncc : list) {
			if (ncc.getTrangThai().equalsIgnoreCase(trangThai))
				addModal(ncc);
		}
	}
//	
//	private void locTheoDieuKien(String trangThai, String chucVu) {
//		List<NhanVien> list = nv_dao.getalltbNhanVien();
//		
//		for (NhanVien nv : list) {
//			if (trangThai.equalsIgnoreCase("Tất cả") && !chucVu.equalsIgnoreCase("Tất cả")) {
//				locTheoChucVu(chucVu);
//				return;
//			}
//			else if (chucVu.equalsIgnoreCase("Tất cả")  && !trangThai.equalsIgnoreCase("Tất cả")) {
//				locTheoTrangThai(trangThai);
//				return;
//			}
//			else if (nv.getTrangThai().equalsIgnoreCase(trangThai) && nv.getChucVu().equalsIgnoreCase(chucVu)) {
//				addModal(nv);
//			}
//		}		
//		
//	}
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
	public JPanel getQuanLyNCC() {
		return contentPane;
	}
}
