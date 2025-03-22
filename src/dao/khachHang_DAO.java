package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;

public class khachHang_DAO {
	public khachHang_DAO() {
		
	}
	public ArrayList<KhachHang> getalltbKH(){
		ArrayList<KhachHang> khachHangs = new ArrayList<KhachHang>();
		ConnectDB.getInstance();
		Connection con =  ConnectDB.getConnection();
		String sql = "Select *From KhachHang";
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				String maKH = resultSet.getString(1);
				String tenKH = resultSet.getString(2);
				String std = resultSet.getString(3);
				String loaiKH = resultSet.getString(4);
				KhachHang khachHang = new KhachHang(maKH, tenKH, std, loaiKH);
				khachHangs.add(khachHang);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return khachHangs;
	}
}
