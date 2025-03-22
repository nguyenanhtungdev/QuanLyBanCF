package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

public class KhuC extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPaneC;
    public Container panelC;
    private JLabel[] labels = new JLabel[12];
    
    public KhuC() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(262, 85, 1145, 626);
        contentPaneC = new JPanel();
        contentPaneC.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPaneC.setBackground(Color.WHITE);
        setContentPane(contentPaneC);

        panelC = new JPanel();
        panelC.setBackground(Color.WHITE);
        panelC.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 12, 12); // Khoảng cách giữa các phần tử
        contentPaneC.add(panelC);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JPanel) e.getSource()).setBackground(Color.decode("#edede9"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JPanel) e.getSource()).setBackground(Color.WHITE);
            }
        };

        for (int i = 0; i < 12; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Sử dụng FlowLayout
            panel.setBackground(Color.WHITE);
            panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            panel.addMouseListener(mouseAdapter);

            JLabel label = new JLabel();
            label.setIcon(new ImageIcon("Image\\IconBan.png"));
            panel.add(label);

            labels[i] = new JLabel("C-B" + String.format("%02d", i + 1));
            labels[i].setForeground(Color.BLUE);
            labels[i].setFont(new Font("Tahoma", Font.BOLD, 16));
            labels[i].setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(labels[i]);
            //Tìm chỉ số của các vị trí để đặt thành phần
            gbc.gridx = i % 6;
            gbc.gridy = i / 6; //Khi i > 6 chia lấy thương thì nó sẽ xuống xong
            panelC.add(panel, gbc);
        }

    }
    
    public JLabel[] getLabels() {
		return labels;
	}

	public void setLabels(JLabel[] labels) {
		this.labels = labels;
	}

	public Container getContentPaneC() {
        return contentPaneC;
    }
}
