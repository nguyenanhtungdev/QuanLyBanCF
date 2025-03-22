package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CaiDat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public CaiDat() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel(); //JPanel gốc, muốn tạo hay thêm các thành phần gì thì thêm vào ở đây
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 82, 1443, 763);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Các phần tử thêm vào đây. Cài đặt");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(579, 329, 400, 20);
		contentPane.add(lblNewLabel);
	}
	public JPanel getCaiDat() {
		return contentPane;
	}
}
