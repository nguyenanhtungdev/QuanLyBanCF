package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.hoaDon_DAO;
import entity.HoaDon1;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class PhieuTamTinh extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnCho, btnMangVe, btnChGiaoHng, btnDatTruoc, btnTim;
	private CardLayout cardLayout = new CardLayout();
	public static String TitleCard[] = {"cho","mangve","giaohang","dattruoc","tim","thanhtoan"};
	private JPanel panelCenter;
	//Khai báo lớp
	private ChoThanhToan choThanhToan = new ChoThanhToan();
	private MangVe mangVe = new MangVe();
	private ChoGiaoHang choGiaoHang = new ChoGiaoHang();
	private ThanhToan thanhToan = new ThanhToan();
	//Khai báo dao
	private DatTruoc datTruoc = new DatTruoc();
	private HoaDon1 hoaDon1 = new HoaDon1();
	private hoaDon_DAO hoaDon_DAO = new hoaDon_DAO();
	//Khai báo các biến kiểu dữ liệu thông thường
	private int slTong = 0, slChuaTT = 0, slMangVe = 0;

	public PhieuTamTinh() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		getSoLuong();//Gọi hàm lấy số lượng
		
		contentPane = new JPanel(); //JPanel gốc, muốn tạo hay thêm các thành phần gì thì thêm vào ở đây
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 82, 1443, 763);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelNorth = new JPanel();
		panelNorth.setBackground(Color.LIGHT_GRAY);
		panelNorth.setBounds(0, 0, 1442, 58);
		contentPane.add(panelNorth);
		panelNorth.setLayout(null);
		
		btnCho = new JButton("Chờ thanh toán "+"("+slChuaTT+")");
		btnCho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCenter, TitleCard[0]);
				btnCho.setBackground(new Color(30, 144, 255));
				btnCho.setForeground(Color.WHITE);
				btnMangVe.setBackground(Color.decode("#F2F2F2"));
				btnMangVe.setForeground(Color.BLACK);
				btnChGiaoHng.setBackground(Color.decode("#F2F2F2"));
				btnChGiaoHng.setForeground(Color.BLACK);
				btnDatTruoc.setBackground(Color.decode("#F2F2F2"));
				btnDatTruoc.setForeground(Color.BLACK);
			}
		});
		btnCho.setForeground(Color.WHITE);
		btnCho.setMargin(new Insets(4, 6, 4, 6));
		btnCho.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCho.setBounds(24, 12, 164, 33);
		btnCho.setBackground(new Color(30, 144, 255));
		btnCho.setFocusPainted(false);//Xóa lựa chọn
		btnCho.setBorderPainted(false);//Xóa border button
		panelNorth.add(btnCho);
		
		btnMangVe = new JButton("Mang về "+"("+slMangVe+")");
		btnMangVe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCenter, TitleCard[1]);
				btnCho.setBackground(Color.decode("#F2F2F2"));
				btnCho.setForeground(Color.BLACK);
				btnMangVe.setBackground(new Color(30, 144, 255));
				btnMangVe.setForeground(Color.WHITE);
				btnChGiaoHng.setBackground(Color.decode("#F2F2F2"));
				btnChGiaoHng.setForeground(Color.BLACK);
				btnDatTruoc.setBackground(Color.decode("#F2F2F2"));
				btnDatTruoc.setForeground(Color.BLACK);
			}
		});
		btnMangVe.setForeground(Color.BLACK);
		btnMangVe.setMargin(new Insets(4, 6, 4, 6));
		btnMangVe.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnMangVe.setBounds(213, 12, 119, 33);
		btnMangVe.setBackground(Color.decode("#F2F2F2"));
		btnMangVe.setFocusPainted(false);//Xóa lựa chọn
		btnMangVe.setBorderPainted(false);//Xóa border button
		panelNorth.add(btnMangVe);
		
		btnChGiaoHng = new JButton("Chờ giao hàng (0)");
		btnChGiaoHng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCenter, TitleCard[2]);
				btnCho.setBackground(Color.decode("#F2F2F2"));
				btnCho.setForeground(Color.BLACK);
				btnMangVe.setBackground(Color.decode("#F2F2F2"));
				btnMangVe.setForeground(Color.BLACK);
				btnChGiaoHng.setBackground(new Color(30, 144, 255));
				btnChGiaoHng.setForeground(Color.WHITE);
				btnDatTruoc.setBackground(Color.decode("#F2F2F2"));
				btnDatTruoc.setForeground(Color.BLACK);
			}
		});
		btnChGiaoHng.setForeground(Color.BLACK);
		btnChGiaoHng.setMargin(new Insets(4, 6, 4, 6));
		btnChGiaoHng.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnChGiaoHng.setBounds(358, 12, 158, 33);
		btnChGiaoHng.setBackground(Color.decode("#F2F2F2"));
		btnChGiaoHng.setFocusPainted(false);//Xóa lựa chọn
		btnChGiaoHng.setBorderPainted(false);//Xóa border button
		panelNorth.add(btnChGiaoHng);
		
		btnDatTruoc = new JButton("Đặt trước (0)");
		btnDatTruoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCenter, TitleCard[3]);
				btnCho.setBackground(Color.decode("#F2F2F2"));
				btnCho.setForeground(Color.BLACK);
				btnChGiaoHng.setBackground(Color.decode("#F2F2F2"));
				btnChGiaoHng.setForeground(Color.BLACK);
				btnMangVe.setBackground(Color.decode("#F2F2F2"));
				btnMangVe.setForeground(Color.BLACK);
				btnDatTruoc.setBackground(new Color(30, 144, 255));
				btnDatTruoc.setForeground(Color.WHITE);
			}
		});
		btnDatTruoc.setMargin(new Insets(4, 6, 4, 6));
		btnDatTruoc.setForeground(Color.BLACK);
		btnDatTruoc.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDatTruoc.setBackground(new Color(242, 242, 242));
		btnDatTruoc.setBounds(541, 12, 132, 33);
		btnDatTruoc.setFocusPainted(false);//Xóa lựa chọn
		btnDatTruoc.setBorderPainted(false);//Xóa border button
		panelNorth.add(btnDatTruoc);
		
		String cobox[] = {"Tìm bàn","Tìm hóa đơn"};
		@SuppressWarnings("unchecked")
		JComboBox comboBox = new JComboBox(cobox);
		comboBox.setToolTipText("");
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox.setBounds(697, 12, 132, 33);
		panelNorth.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(855, 12, 187, 31);
		panelNorth.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(0, 0, 136, 35);
		panel_1.add(textField);
		textField.setToolTipText("Nhập từ khóa");
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);
		
		btnTim = new JButton("");
		btnTim.setBounds(133, 1, 54, 30);
		panel_1.add(btnTim);
		btnTim.setBackground(new Color(242, 242, 242));
		btnTim.setIcon(new ImageIcon("Image/IconTim1.png"));
		btnTim.setFocusPainted(false);//Xóa lựa chọn
		btnTim.setBorderPainted(false);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBounds(0, 692, 1431, 52);
		panelSouth.setBackground(Color.decode("#F0F0F0"));
		contentPane.add(panelSouth);
		panelSouth.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tổng số đơn hàng: "+slTong);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(15, 12, 159, 28);
		panelSouth.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Thêm đơn hàng mới:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(1120, 14, 143, 24);
		panelSouth.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ALT + A");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(1256, 14, 73, 24);
		panelSouth.add(lblNewLabel_1_1);
		
		panelCenter = new JPanel();
		panelCenter.setBackground(Color.LIGHT_GRAY);
		panelCenter.setBounds(0, 53, 1442, 639);
		contentPane.add(panelCenter);
		panelCenter.setLayout(cardLayout);
		addElementCard();//Thêm phần tử vào card
	}
	//Thêm các thành phần vào card
	public void addElementCard() {
		panelCenter.add(choThanhToan.getChoThanhToan(),TitleCard[0]);
		panelCenter.add(mangVe.getMangVe(),TitleCard[1]);
		panelCenter.add(choGiaoHang.getChoGiaoHang(),TitleCard[2]);
		panelCenter.add(datTruoc.getDatTruoc(),TitleCard[3]);
//		panelCenter.add(thanhToan.getThanhToan(),TitleCard[5]);
	}
	//Trả về giao diện của phiếu tạm tính
	public JPanel getPhieuTamTinh() {
		return contentPane;
	}
	//Trả về số lượng đơn hàng chưa được thanh toán
	public void getSoLuong() {
		for(HoaDon1 hd : hoaDon_DAO.getalltHoaDon()) {
			if(hd.getTrangThaiThanhToan().equals("Chưa thanh toán")) {
				if(hd.getHinThuc().equals("Tại Quán")) {
					++slChuaTT;
				}
				else if(hd.getHinThuc().equals("Mang Về")){
					++slMangVe;
				}
				++slTong;
			}
		}
	}
}
