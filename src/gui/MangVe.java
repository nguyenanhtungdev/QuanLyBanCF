package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;

import com.itextpdf.text.DocumentException;

import connectDB.ConnectDB;
import dao.hoaDon_DAO;
import dao.khachHang_DAO;
import entity.ChiTietHD;
import entity.HoaDon1;
import entity.KhachHang;
import entity.SanPham;



public class MangVe extends JFrame {

	public static ThanhToan thanhToan = new ThanhToan();
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private GridBagConstraints gbc;
	private int x = 0, y = 0;
	private hoaDon_DAO don_DAO = new hoaDon_DAO();
	private NumberFormat currencyFormat;
	private JPanel panelCenter;
	
	private float thue = (float)0.1;
	private double tongTien = 0;

    public MangVe() {
    	//Khởi tạo kết nối đến sql
		try {
			ConnectDB.getInstance().connect();
			System.out.println("Connected!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 45, 5, 5); // Đặt khoảng cách
        GridBagLayout bagLayout = new GridBagLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1442, 639);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);  
        contentPane.setLayout(null);
        
        JPanel panelNorth = new JPanel();
        panelNorth.setBackground(Color.WHITE);
        panelNorth.setBounds(0, 0, 1430, 38);
        contentPane.add(panelNorth);
        panelNorth.setLayout(null);
        
        panelCenter = new JPanel();
        panelCenter.setBackground(Color.WHITE);
        panelCenter.setBounds(-10, 38, 1430, 608);
        contentPane.add(panelCenter);
        panelCenter.setLayout(bagLayout);
        //Them don hang
        themDonHang();
    }
    
    public JPanel getMangVe() {
        return contentPane;
    }
    
    public void themDonHang() {
        //Thêm đơn hàng mới 
        for(HoaDon1 hoaDon1: don_DAO.getalltHoaDon()) {
            if(hoaDon1.getTrangThaiThanhToan().equals("Chưa thanh toán") && hoaDon1.getHinThuc().equals("Mang Về")) {
                themPhanTu(getDH(hoaDon1));
            }
        }
    }
    public void themPhanTu(JPanel p) {
        if (x < 5 && y < 3) { // Kiểm tra xem x và y có vượt quá kích thước lưới không
            gbc.gridx = x;
            gbc.gridy = y;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            panelCenter.add(p, gbc); // Thêm panel vào lưới tại vị trí x, y
            x++; // Tăng x lên 1 để di chuyển sang cột tiếp theo
            if (x == 5) { // Nếu đã đến cột thứ 5, đặt lại x = 0 và tăng y lên 1 để di chuyển xuống dòng tiếp theo
                x = 0;
                y++;
            }
        }
    }
    
    public JPanel getDH(HoaDon1 hoaDon) {
        // Tạo một đối tượng NumberFormat cho tiền tệ của Việt Nam
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Loại bỏ các số 0 không cần thiết ở cuối cùng
        currencyFormat.setMaximumFractionDigits(0);
        
    	JPanel panelDH = new JPanel();
		panelDH.setBackground(Color.WHITE);
		panelDH.setBounds(113, 64, 217, 146);
		panelDH.setLayout(null);
		
		JPanel panelNorth = new JPanel();
		panelNorth.setBounds(0, 0, 217, 39);
		panelDH.add(panelNorth);
		panelNorth.setBackground(new Color(30, 144, 255));
		panelNorth.setLayout(null);
		
		JLabel lblMa = new JLabel(hoaDon.getMaHD());
		lblMa.setForeground(Color.WHITE);
		lblMa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMa.setBounds(8, 8, 83, 25);
		panelNorth.add(lblMa);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 39, 217, 72);
		panelDH.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.decode("#CCCCCC")));
		panel_2.setBounds(0, 0, 108, 71);
		panel.add(panel_2);
		panel_2.setBackground(Color.decode("#FFF8DC"));
		panel_2.setLayout(null);
		
		JLabel lblBan = new JLabel("Mang Về",JLabel.CENTER);;
		lblBan.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBan.setBounds(0, 26, 108, 23);
		panel_2.add(lblBan);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.decode("#CCCCCC")));
		panel_3.setBounds(107, 0, 110, 71);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.decode("#CCCCCC")));
		panel_1.setBounds(0, 0, 110, 37);
		panel_3.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblGia = new JLabel(currencyFormat.format(hoaDon.getTongTien()),JLabel.CENTER);
		lblGia.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblGia.setBounds(0, 6, 110, 26);
		panel_1.add(lblGia);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.decode("#CCCCCC")));
		panel_4.setBounds(0, 35, 110, 36);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
	
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime thoiGianBan = hoaDon.getNgayLapHD();
		Duration duration = Duration.between(thoiGianBan, now);
		
		// Chuyển đổi khoảng thời gian thành phút
		long minutesDifference = Math.abs(duration.toMinutes());
		
		// Tạo một chuỗi từ số phút
		String thoiGianDifferenceString = Long.toString(minutesDifference);
		
		// Hiển thị chuỗi trong JLabel
		JLabel lblThoiGian = new JLabel(thoiGianDifferenceString+"'");
		lblThoiGian.setIcon(new ImageIcon("Image/IconTime.png"));
		lblThoiGian.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblThoiGian.setHorizontalAlignment(SwingConstants.CENTER);
		lblThoiGian.setBounds(0, 0, 110, 36);
		panel_4.add(lblThoiGian);
		
		JLabel lblTinhTien = new JLabel("",JLabel.CENTER);
		lblTinhTien.setIcon(new ImageIcon("Image/IconTinhTien.png"));
		lblTinhTien.setBorder(new LineBorder(Color.decode("#CCCCCC")));
		lblTinhTien.setBounds(0, 109, 55, 37);
		lblTinhTien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thanhToan = new ThanhToan(hoaDon.getMaHD(), formattime(hoaDon), hoaDon.getTongTien(), 0);
				thanhToan.getThanhToan(hoaDon);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblTinhTien.setBackground(Color.decode("#DCDCDC"));
				lblTinhTien.setOpaque(true); // Cần thiết để màu nền có hiệu lực cho Jlabel
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblTinhTien.setBackground(Color.WHITE);
				lblTinhTien.setOpaque(true); // Cần thiết để màu nền có hiệu lực cho Jlabel
			}
		});
		panelDH.add(lblTinhTien);
		
		JLabel lblChinhSua = new JLabel("", SwingConstants.CENTER);
		lblChinhSua.setIcon(new ImageIcon("Image/IconChinhSua.png"));
		lblChinhSua.setBorder(new LineBorder(Color.decode("#CCCCCC")));
		lblChinhSua.setBounds(54, 109, 55, 37);
		lblChinhSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Bạn đã nhấn chỉnh sửa");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblChinhSua.setBackground(Color.decode("#DCDCDC"));
				lblChinhSua.setOpaque(true); // Cần thiết để màu nền có hiệu lực cho Jlabel
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblChinhSua.setBackground(Color.WHITE);
				lblChinhSua.setOpaque(true); // Cần thiết để màu nền có hiệu lực cho Jlabel
			}
		});
		
		panelDH.add(lblChinhSua);
		
		JLabel lblPhieuTam = new JLabel("", SwingConstants.CENTER);
		lblPhieuTam.setIcon(new ImageIcon("Image/IconPrint.png"));
		lblPhieuTam.setBorder(new LineBorder(Color.decode("#CCCCCC")));
		lblPhieuTam.setBounds(108, 109, 55, 37);
		lblPhieuTam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "In phiếu tạm tính thành công!");
				ThanhToan.getInstance().inHoaDon(hoaDon);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblPhieuTam.setBackground(Color.decode("#DCDCDC"));
				lblPhieuTam.setOpaque(true); // Cần thiết để màu nền có hiệu lực cho Jlabel
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblPhieuTam.setBackground(Color.WHITE);
				lblPhieuTam.setOpaque(true); // Cần thiết để màu nền có hiệu lực cho Jlabel
			}
		});
		panelDH.add(lblPhieuTam);
		
		//Tạo Menu Item
		JMenuItem menuItem1 = new JMenuItem("Chuyển bàn");
		JMenuItem menuItem2 = new JMenuItem("Hủy order");
		JPopupMenu jPopupMenuKhac = new JPopupMenu();
		jPopupMenuKhac.add(menuItem1);
		jPopupMenuKhac.add(menuItem2);
		
		Font font = new Font("Sans-serif", Font.BOLD, 16);
		menuItem1.setFont(font);
		menuItem2.setFont(font);
		
		menuItem1.setIcon(new ImageIcon("Image/IconChuyen.png"));
		menuItem2.setIcon(new ImageIcon("Image/IconHuy1.png"));
		
		JLabel lblKhac = new JLabel("...", SwingConstants.CENTER);
		lblKhac.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblKhac.setBorder(new LineBorder(Color.decode("#CCCCCC")));
		lblKhac.setBounds(162, 109, 55, 37);
		lblKhac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jPopupMenuKhac.show(lblKhac, 0, lblKhac.getHeight());
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblKhac.setBackground(Color.decode("#DCDCDC"));
				lblKhac.setOpaque(true); // Cần thiết để màu nền có hiệu lực cho Jlabel
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblKhac.setBackground(Color.WHITE);
				lblKhac.setOpaque(true); // Cần thiết để màu nền có hiệu lực cho Jlabel
			}
		});
		
		 // Thêm ActionListener cho mỗi JMenuItem
        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int op = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn hủy đơn hàng","Hủy đơn hàng",JOptionPane.YES_NO_OPTION);
            	if(op == JOptionPane.YES_OPTION) {
            		String query = "Update HoaDon " +
                			"SET trangThaiThanhToan=? " +
                			"where maHoaDon=? ";
                	try {
    					PreparedStatement statement = ConnectDB.getConnection().prepareStatement(query);
    					statement.setString(1, "Đã hủy");
    					statement.setString(2, hoaDon.getMaHD());
    					statement.executeUpdate();
    				} catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
                	JOptionPane.showMessageDialog(null, "Hủy đơn hàng thành công!");
            	}
            }
        });
		
		panelDH.add(lblKhac);
		
		return panelDH;
    }
    private void removeElementAt(int index) {
        Component[] components = panelCenter.getComponents();
        if (index >= 0 && index < components.length) {
            panelCenter.remove(index); 
            revalidate();
            repaint(); 

            for (int i = index; i < components.length - 1; i++) {
                int row = i / 5; 
                int col = i % 5;
                GridBagConstraints gbc = ((GridBagLayout) panelCenter.getLayout()).getConstraints(components[i + 1]);
                gbc.gridx = col;
                gbc.gridy = row;
                ((GridBagLayout) panelCenter.getLayout()).setConstraints(components[i + 1], gbc);
            }
        } else {
            System.out.println("Invalid index");
        }
    }
  //Định dạng ngày giờ hiện tại 
    public	String formattime(HoaDon1 hoaDon1) {
    	LocalDateTime time = hoaDon1.getNgayLapHD();
		//Định dạng lại về thời gian theo kiểu việt nam
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss", Locale.forLanguageTag("vi-VN"));
		return time.format(formatter);
    }
}
