package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.hoaDon_DAO;
import entity.HoaDon1;
import gui.QuanLySanPham.CustomColorRenderer1;

import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class QuanLyDH extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaDH;
	private JTable table;
	private hoaDon_DAO hoaDon_DAO = new hoaDon_DAO();
	private JButton btnTimTheoMa;
	private JButton btnTimTheoNgay;
	private JButton btnQuayLai;
	private JButton btnXoa;
	private int tongSoDon = 0, tongSoDonHuy = 0;
	private double tongTienBan = 0;
	private JTextField txtTongSo;
	private JTextField txtTongTienBan;
	private JTextField txtSoHuy;
	private NumberFormat currencyFormat;
	private JTextField txtNgay;
	private JComboBox combNam;
	private JComboBox combThang;
	private String radioText;
	
	public QuanLyDH() {
		setBackground(Color.WHITE);
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		hoaDon_DAO = new hoaDon_DAO();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
        // Tạo một đối tượng NumberFormat cho tiền tệ của Việt Nam
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Loại bỏ các số 0 không cần thiết ở cuối cùng
        currencyFormat.setMaximumFractionDigits(0);
		
		contentPane = new JPanel(); //JPanel gốc, muốn tạo hay thêm các thành phần gì thì thêm vào ở đây
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 82, 1443, 763);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDSDH = new JLabel("DANH SÁCH ĐƠN HÀNG");
		lblDSDH.setHorizontalAlignment(SwingConstants.CENTER);
		lblDSDH.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblDSDH.setBounds(316, 11, 386, 35);
		contentPane.add(lblDSDH);
		
		table = new JTable();
		String[] s= {
				"Mã hóa đơn", "Ngày lập", "Ghi chú", "Mã giảm giá", "Mã KH", "Mã nhân viên","Thuế","Tổng tiền","Phương thức thanh toán","Trạng thái thanh toán","Hình thức"
			};
		DefaultTableModel model = new DefaultTableModel(s, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		model.setColumnIdentifiers(s); 
		

		JTableHeader headerTable = table.getTableHeader();
		Font fontHeader = new Font("Tahoma", Font.BOLD, 13); // Font chữ "Arial", đậm, kích thước 14
		headerTable.setFont(fontHeader);
		headerTable.setPreferredSize(new Dimension(table.getWidth(), 40));
		
		table.setModel(model);
		table.setRowHeight(30);
		
		JScrollPane scrollPane = new JScrollPane(table,
	                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 57, 974, 606);
		contentPane.add(scrollPane);
		docDuLieuTuDatabaseVaoBang();
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 5, 0, 0, Color.decode("#DCDCDC")));
		panel.setBounds(991, 0, 464, 735);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblQunLn = new JLabel("QUẢN LÝ ĐƠN HÀNG");
		lblQunLn.setBounds(51, 30, 339, 35);
		panel.add(lblQunLn);
		lblQunLn.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLn.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JLabel lblMaDH = new JLabel("Mã đơn:");
		lblMaDH.setBounds(22, 123, 95, 30);
		panel.add(lblMaDH);
		lblMaDH.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		txtMaDH = new JTextField();
		txtMaDH.setBounds(115, 122, 164, 35);
		panel.add(txtMaDH);
		txtMaDH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaDH.setColumns(10);
		
		JLabel lblChucNang = new JLabel("Lọc theo tiêu chí");
		lblChucNang.setHorizontalAlignment(SwingConstants.CENTER);
		lblChucNang.setForeground(Color.WHITE);
		lblChucNang.setBounds(22, 175, 192, 30);
		panel.add(lblChucNang);
		lblChucNang.setBackground(new Color(30,144,255));
		lblChucNang.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChucNang.setOpaque(true);
		
		btnTimTheoMa = new JButton("Tìm kiếm");
		btnTimTheoMa.setBounds(293, 122, 120, 35);
		panel.add(btnTimTheoMa);
		btnTimTheoMa.setForeground(Color.WHITE);
		btnTimTheoMa.setBackground(new Color(30,144,255));
		btnTimTheoMa.setBorderPainted(false); // Loại bỏ đường viền cho button
		btnTimTheoMa.setFocusPainted(false); // Loại bỏ đường viền khi button được focus
		btnTimTheoMa.setFont(new Font("Arial", Font.BOLD, 18));
		
		btnTimTheoNgay = new JButton("Tìm kiếm");
		btnTimTheoNgay.setBounds(293, 374, 120, 35);
		panel.add(btnTimTheoNgay);
		btnTimTheoNgay.setForeground(Color.WHITE);
		btnTimTheoNgay.setBackground(new Color(30,144,255));
		btnTimTheoNgay.setBorderPainted(false); // Loại bỏ đường viền cho button
		btnTimTheoNgay.setFocusPainted(false); // Loại bỏ đường viền khi button được focus
		btnTimTheoNgay.setFont(new Font("Arial", Font.BOLD, 18));
		
		btnQuayLai = new JButton("Quay lại");
		btnQuayLai.setBounds(131, 664, 180, 44);
		panel.add(btnQuayLai);
		btnQuayLai.setForeground(Color.WHITE);
		btnQuayLai.setBackground(new Color(30,144,255));
		btnQuayLai.setBorderPainted(false); // Loại bỏ đường viền cho button
		btnQuayLai.setFocusPainted(false); // Loại bỏ đường viền khi button được focus
		btnQuayLai.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel lblNewLabel = new JLabel("Ngày:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(22, 240, 63, 25);
		panel.add(lblNewLabel);
		
		txtNgay = new JTextField();
		txtNgay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNgay.setBounds(84, 240, 44, 25);
		panel.add(txtNgay);
		txtNgay.setColumns(10);
		
		JLabel lblThng = new JLabel("Tháng:");
		lblThng.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblThng.setBounds(151, 240, 63, 25);
		panel.add(lblThng);
		
		JLabel lblNewLabel_1_1 = new JLabel("Năm:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(288, 240, 63, 25);
		panel.add(lblNewLabel_1_1);
		
		combThang = new JComboBox();
		combThang.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		combThang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		combThang.setBounds(217, 240, 52, 25);
		combThang.setSelectedItem(String.valueOf(LocalDate.now().getMonthValue()));
		panel.add(combThang);
		
		combNam = new JComboBox();
		combNam.setModel(new DefaultComboBoxModel(new String[] {"2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015"}));
		combNam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		combNam.setBounds(338, 240, 84, 25);
		panel.add(combNam);
		
		JLabel lblNewLabel_1 = new JLabel("Ngày lập hóa đơn");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(22, 212, 158, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Trạng thái hóa đơn");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(22, 280, 158, 25);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblLcTheoM = new JLabel("Tìm kiếm theo mã");
		lblLcTheoM.setHorizontalAlignment(SwingConstants.CENTER);
		lblLcTheoM.setOpaque(true);
		lblLcTheoM.setForeground(Color.WHITE);
		lblLcTheoM.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLcTheoM.setBackground(new Color(30, 144, 255));
		lblLcTheoM.setBounds(22, 82, 192, 30);
		panel.add(lblLcTheoM);
		
		JRadioButton rdbtn1 = new JRadioButton("Đã thanh toán");
		rdbtn1.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtn1.setBounds(22, 312, 145, 25);
		panel.add(rdbtn1);
		
		JRadioButton rdbtn2 = new JRadioButton("Chưa thanh toán");
		rdbtn2.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtn2.setBounds(169, 312, 158, 25);
		panel.add(rdbtn2);
		
		JRadioButton rdbtn3 = new JRadioButton("Đã hủy");
		rdbtn3.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtn3.setBounds(338, 312, 104, 25);
		panel.add(rdbtn3);
		
		JRadioButton rdbtn0 = new JRadioButton("Tất cả");
		rdbtn0.setSelected(true);
		rdbtn0.setFont(new Font("Tahoma", Font.BOLD, 15));
		rdbtn0.setBounds(22, 340, 95, 25);
		panel.add(rdbtn0);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtn1);
		buttonGroup.add(rdbtn2);
		buttonGroup.add(rdbtn3);
		buttonGroup.add(rdbtn0);
		// Đặt ActionListener cho mỗi radio button
		rdbtn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioText = rdbtn1.getText();
            }
        });

        rdbtn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioText = rdbtn2.getText();
            }
        });

        rdbtn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                radioText = rdbtn3.getText();
            }
        });
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Th\u1ED1ng K\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 670, 974, 64);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTongSo = new JLabel("Tổng số đơn hàng: ");
		lblTongSo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongSo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongSo.setBounds(10, 25, 155, 26);
		panel_1.add(lblTongSo);
		
		txtTongSo = new JTextField();
		txtTongSo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTongSo.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTongSo.setEditable(false);
		txtTongSo.setBounds(175, 26, 38, 26);
		panel_1.add(txtTongSo);
		txtTongSo.setColumns(10);
		
		JLabel lblTongTien = new JLabel("Tổng tiền bán:");
		lblTongTien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTongTien.setBounds(260, 25, 123, 26);
		panel_1.add(lblTongTien);
		
		txtTongTienBan = new JTextField();
		txtTongTienBan.setForeground(Color.RED);
		txtTongTienBan.setHorizontalAlignment(SwingConstants.CENTER);
		txtTongTienBan.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtTongTienBan.setEditable(false);
		txtTongTienBan.setColumns(10);
		txtTongTienBan.setBounds(403, 26, 129, 26);
		panel_1.add(txtTongTienBan);
		
		JLabel lblDonHuy = new JLabel("Tổng số đơn hủy:");
		lblDonHuy.setHorizontalAlignment(SwingConstants.CENTER);
		lblDonHuy.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDonHuy.setBounds(581, 25, 145, 26);
		panel_1.add(lblDonHuy);
		
		txtSoHuy = new JTextField();
		txtSoHuy.setHorizontalAlignment(SwingConstants.CENTER);
		txtSoHuy.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtSoHuy.setEditable(false);
		txtSoHuy.setColumns(10);
		txtSoHuy.setBounds(736, 26, 38, 26);
		panel_1.add(txtSoHuy);
		btnQuayLai.addActionListener(this);
		btnTimTheoNgay.addActionListener(this);
		
		btnTimTheoMa.addActionListener(this);
		
		//Chỉnh màu chữ của cột, và xóa border cho từng cột
        table.getColumnModel().getColumn(0).setCellRenderer(new CustomColorRenderer1());
        table.getColumnModel().getColumn(1).setCellRenderer(new CustomColorRenderer1());
        table.getColumnModel().getColumn(2).setCellRenderer(new CustomColorRenderer1());
        table.getColumnModel().getColumn(3).setCellRenderer(new CustomColorRenderer1());
        table.getColumnModel().getColumn(4).setCellRenderer(new CustomColorRenderer1());
        table.getColumnModel().getColumn(5).setCellRenderer(new CustomColorRenderer1());
        table.getColumnModel().getColumn(6).setCellRenderer(new CustomColorRenderer1());
        table.getColumnModel().getColumn(7).setCellRenderer(new CustomColorRenderer1());
        table.getColumnModel().getColumn(8).setCellRenderer(new CustomColorRenderer1());
        table.getColumnModel().getColumn(9).setCellRenderer(new CustomColorRenderer1());
        table.getColumnModel().getColumn(10).setCellRenderer(new CustomColorRenderer1());
        
        thongKe();
	}
	public JPanel getQuanLyDH() {
		return contentPane;
	}
	
	public void docDuLieuTuDatabaseVaoBang() {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		for (HoaDon1 hd : hoaDon_DAO.getalltHoaDon()) {
			defaultTableModel.addRow(new Object[] {
					hd.getMaHD(),
					hd.getNgayLapHD(),
					hd.getGhiChu(),
					hd.getGiamGia().getMaGiamGia(),
					hd.getNv().getMaNV(),
					hd.getKh().getMaKH(),
					hd.getThue(),
					hd.getTongTien(),
					hd.getPhuongThucThanhToan(),
					hd.getTrangThaiThanhToan(),
					hd.getHinThuc(),
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnTimTheoMa)) {
			String ma = txtMaDH.getText().trim();
			ArrayList<HoaDon1> list = hoaDon_DAO.timHoaDonTheoMa(ma);
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			if(list.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn có mã " + ma + " !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				docDuLieuTuDatabaseVaoBang();
				txtMaDH.setText("");
				txtMaDH.requestFocus();
			}else {
				for (HoaDon1 hd : list) {
					defaultTableModel.addRow(new Object[] {
							hd.getMaHD(),
							hd.getNgayLapHD(),
							hd.getGhiChu(),
							hd.getGiamGia().getMaGiamGia(),
							hd.getNv().getMaNV(),
							hd.getKh().getMaKH(),
							hd.getThue(),
							hd.getTongTien(),
							hd.getPhuongThucThanhToan(),
							hd.getTrangThaiThanhToan(),
							hd.getHinThuc(),
					});
				}
				txtMaDH.setText("");
				txtMaDH.requestFocus();
			}
		}	
		else if(o.equals(btnTimTheoNgay)) {
			boolean temp = false;
		    String ngay = txtNgay.getText().trim();
		    String thang = (String)combThang.getSelectedItem();
		    String nam = (String)combNam.getSelectedItem();
		    tongSoDon = 0;
		    tongTienBan = 0;
		    
		    DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		    defaultTableModel.setRowCount(0);
		    for(HoaDon1 hd: hoaDon_DAO.getalltHoaDon()) {
		        int ngay1 = hd.getNgayLapHD().getDayOfMonth();
		        int thang1 = hd.getNgayLapHD().getMonthValue();
		        int nam1 = hd.getNgayLapHD().getYear();
		        if (ngay.isEmpty()) {
		            if(thang1 == Integer.parseInt(thang) && nam1 == Integer.parseInt(nam)) {
//		            	if(radioText.equals("Đã Thanh Toán")) {
//		            		
//		            	}
		                defaultTableModel.addRow(new Object[] {
		                        hd.getMaHD(),
		                        hd.getNgayLapHD(),
		                        hd.getGhiChu(),
		                        hd.getGiamGia().getMaGiamGia(),
		                        hd.getNv().getMaNV(),
		                        hd.getKh().getMaKH(),
		                        hd.getThue(),
		                        hd.getTongTien(),
		                        hd.getPhuongThucThanhToan(),
		                        hd.getTrangThaiThanhToan(),
		                        hd.getHinThuc()
		                });
		                tongSoDon++;
		                tongTienBan+= hd.getTongTien();
		                temp = true;
		            }
		        } else {
		            if(ngay1 == Integer.parseInt(ngay) && thang1 == Integer.parseInt(thang) && nam1 == Integer.parseInt(nam)) {
		            	defaultTableModel.addRow(new Object[] {
		                        hd.getMaHD(),
		                        hd.getNgayLapHD(),
		                        hd.getGhiChu(),
		                        hd.getGiamGia().getMaGiamGia(),
		                        hd.getNv().getMaNV(),
		                        hd.getKh().getMaKH(),
		                        hd.getThue(),
		                        hd.getTongTien(),
		                        hd.getPhuongThucThanhToan(),
		                        hd.getTrangThaiThanhToan(),
		                        hd.getHinThuc()
		                });
		                tongSoDon++;
		                tongTienBan+= hd.getTongTien();
		                temp = true;
		            }       
		        }
		    }
		    if(!temp) {
		    	JOptionPane.showMessageDialog(null, "Không tìm thấy đơn hàng!");
		    }
		    else {
		    	txtTongSo.setText(tongSoDon+"");
		    	txtTongTienBan.setText(currencyFormat.format(tongTienBan));
		    	txtSoHuy.setText(tongSoDonHuy+"");
		    }
		}

		else if (o.equals(btnQuayLai)) {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			defaultTableModel.setRowCount(0);
			docDuLieuTuDatabaseVaoBang();
		    // Reset các giá trị tổng về 0
		    tongSoDon = 0;
		    tongTienBan = 0;
		    tongSoDonHuy = 0;
			thongKe();
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
    public void thongKe() {
    	for(HoaDon1 hoaDon1: hoaDon_DAO.getalltHoaDon()) {
    		tongSoDon++;
    		if(hoaDon1.getTrangThaiThanhToan().equals("Đã Thanh Toán")) {
    			tongTienBan += hoaDon1.getTongTien();
    		}
    		
    		if(hoaDon1.getTrangThaiThanhToan().equals("Đã hủy")) {
    			tongSoDonHuy++;
    		}
    	}
    	
    	txtTongSo.setText(tongSoDon+"");
    	txtTongTienBan.setText(currencyFormat.format(tongTienBan));
    	txtSoHuy.setText(tongSoDonHuy+"");
    }
}

