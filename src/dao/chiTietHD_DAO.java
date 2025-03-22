package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHD;

public class chiTietHD_DAO {
	public chiTietHD_DAO() {
			
	}
	public ArrayList<ChiTietHD> getalltbChiTietHD(){
		ArrayList<ChiTietHD> chiTietHDs = new ArrayList<ChiTietHD>();
		ConnectDB.getInstance();
		Connection con =  ConnectDB.getConnection();
		String sql = "Select maHoaDon, maSanPham, soLuong, donGia From ChiTietHD";
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				String maHD = resultSet.getString(1);
				String maSP = resultSet.getString(2);
				int soLuong = resultSet.getInt(3);
				double donDonGia = resultSet.getInt(4);
				ChiTietHD chiTietHD = new ChiTietHD(maHD, maSP, donDonGia, soLuong);
				chiTietHDs.add(chiTietHD);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chiTietHDs;
	}
	public boolean addChiTietHD(ChiTietHD chiTietHD) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Insert into ChiTietHoaDon values(?,?,?,?,?) ";
		
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			//Them vao
			stmt.setString(1,chiTietHD.getMaHoaDon());
			stmt.setString(2,chiTietHD.getMaSanPham());
			stmt.setInt(3,chiTietHD.getSoLuong());
			stmt.setDouble(4,chiTietHD.getDonGia());
			stmt.setDouble(5, chiTietHD.tinhThanhTien());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
