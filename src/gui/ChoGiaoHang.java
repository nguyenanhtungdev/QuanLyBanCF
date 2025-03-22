package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ChoGiaoHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public ChoGiaoHang() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1442, 639);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(Color.WHITE);
		setContentPane(contentPane);
	}
	public JPanel getChoGiaoHang() {
		return contentPane;
	}
}
