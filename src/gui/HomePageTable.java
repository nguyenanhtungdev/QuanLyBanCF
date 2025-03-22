package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class HomePageTable extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel BanPane;
	private JTextField textField;
	private JButton btnKhuA, btnKhuB, btnKhuC, btnNgoaiTroi, btnMayLanh;
	private KhuA a = new KhuA();
	private KhuB b = new KhuB();
	private KhuC c = new KhuC();
	private KhuNT nt = new KhuNT();
	private KhuML ml = new KhuML();
	private JPanel panelCenter;
	private CardLayout cardLayout = new CardLayout();
	private String title[] = {"khuA","khuB","khuC","Nt","Ml"};
	
	public HomePageTable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 82, 1443, 763);
		BanPane = new JPanel();
		BanPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		BanPane.setBounds(0, 82, 1443, 752);
		setContentPane(BanPane);
		BanPane.setLayout(null);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.WHITE);
		panelLeft.setAutoscrolls(true);
		panelLeft.setBounds(10, 85, 245, 643);
		BanPane.add(panelLeft);
		panelLeft.setLayout(null);
		
		btnKhuA = new JButton("Khu A");
		btnKhuA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCenter,title[0]);
			}
		});
		btnKhuA.setForeground(Color.WHITE);
		btnKhuA.setBackground(Color.ORANGE);
		btnKhuA.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnKhuA.setBounds(54, 45, 139, 47);
		panelLeft.add(btnKhuA);
		
		btnKhuB = new JButton("Khu B");
		btnKhuB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCenter,title[1]);
			}
		});
		btnKhuB.setForeground(Color.WHITE);
		btnKhuB.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnKhuB.setBackground(Color.ORANGE);
		btnKhuB.setBounds(54, 147, 139, 47);
		panelLeft.add(btnKhuB);
		
		btnKhuC = new JButton("Khu C");
		btnKhuC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCenter,title[2]);
			}
		});
		btnKhuC.setForeground(Color.WHITE);
		btnKhuC.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnKhuC.setBackground(Color.ORANGE);
		btnKhuC.setBounds(54, 249, 139, 47);
		panelLeft.add(btnKhuC);
		
		btnNgoaiTroi = new JButton("Ngoài Trời");
		btnNgoaiTroi.setForeground(Color.WHITE);
		btnNgoaiTroi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelCenter,title[3]);
			}
		});
		btnNgoaiTroi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNgoaiTroi.setBackground(Color.ORANGE);
		btnNgoaiTroi.setBounds(54, 351, 139, 47);
		panelLeft.add(btnNgoaiTroi);
		
		btnMayLanh = new JButton("Máy Lạnh");
		btnMayLanh.setForeground(Color.WHITE);
		btnMayLanh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(panelCenter,title[4]);
			}
		});
		btnMayLanh.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnMayLanh.setBackground(Color.ORANGE);
		btnMayLanh.setBounds(54, 453, 139, 47);
		panelLeft.add(btnMayLanh);
		
		btnKhuA.setBorderPainted(false); // Loại bỏ đường viền cho button
		btnKhuA.setFocusPainted(false); // Loại bỏ đường viền khi button được focus
		btnKhuB.setBorderPainted(false); 
		btnKhuB.setFocusPainted(false);
		btnKhuC.setBorderPainted(false); 
		btnKhuC.setFocusPainted(false);
		btnNgoaiTroi.setBorderPainted(false); 
		btnNgoaiTroi.setFocusPainted(false);
		btnMayLanh.setBorderPainted(false); 
		btnMayLanh.setFocusPainted(false);
		
		JLabel lblDs = new JLabel("Danh Sách Các Bàn");
		lblDs.setBounds(10, 25, 245, 49);
		BanPane.add(lblDs);
		lblDs.setForeground(Color.WHITE);
		lblDs.setHorizontalAlignment(SwingConstants.CENTER);
		lblDs.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblDs.setBackground(new Color(30,144,255)); 
		lblDs.setOpaque(true); //cho phép ghi đè
		
		JPanel panelNorth = new JPanel();
		panelNorth.setBackground(Color.WHITE);
		panelNorth.setBounds(262, 25, 1159, 49);
		BanPane.add(panelNorth);
		panelNorth.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(399, 10, 359, 30);
		panelNorth.add(textField);
		textField.setColumns(10);
		
		JButton btnTim = new JButton("Tìm Kiếm");
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTim.setBackground(new Color(30,144,255));
		btnTim.setBounds(272, 10, 121, 30);
		panelNorth.add(btnTim);
		btnTim.setBorderPainted(false); 
		btnTim.setFocusPainted(false);
		
		String s[] = {"Khu A","Khu B","Khu C","Ngoài Trời","Máy Lạnh"};
		JComboBox<String> comboBox = new JComboBox<String>(s);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(777, 10, 89, 30);
		panelNorth.add(comboBox);
		
		
		panelCenter = new JPanel();
		panelCenter.setBackground(Color.WHITE);
		panelCenter.setBounds(265, 85, 1156, 643);
		panelCenter.setLayout(cardLayout);
		BanPane.add(panelCenter);
		//Thêm các khu vào
//		thêm vào nó bị lỗi và ko thể design, để design nó
		panelCenter.add(a.getContentPaneA(),title[0]);
		panelCenter.add(b.getContentPaneB(),title[1]);
		panelCenter.add(c.getContentPaneC(),title[2]);
		panelCenter.add(nt.getContentPaneNT(),title[3]);
		panelCenter.add(ml.getContentPaneML(),title[4]);
	}
	public JPanel getBanPane() {
		return BanPane;
	}
}