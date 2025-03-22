package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.SanPham;

public class sanPham_DAO {
	public sanPham_DAO() {
		// TODO Auto-generated constructor stub
	}
	ArrayList<SanPham> sanPhams;
	public ArrayList<SanPham> getalltbSanPham(){
		sanPhams = new ArrayList<SanPham>();
		ConnectDB.getInstance();
		Connection con =  ConnectDB.getConnection();
		String sql = "Select *From SanPham";
		
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				String maSP = resultSet.getString(1);
				String tenSP = resultSet.getString(2);
				String loaiSP = resultSet.getString(3);
				Double donGia = resultSet.getDouble(4);
				String moTa = resultSet.getString(5);
				String trangThai = resultSet.getString(6);
				String hinhAnh = resultSet.getString(7);
				SanPham sp = new SanPham(maSP, tenSP, loaiSP, donGia, moTa, trangThai,hinhAnh);
				sanPhams.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sanPhams;
	}
	public boolean taoSP(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into " + "SanPham values(?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, sp.getMaSP());
			stmt.setString(2, sp.getTenSP());
			stmt.setString(3, sp.getLoaiSP());
			stmt.setDouble(4, sp.getDonGia());
			stmt.setString(5, sp.getMoTa());	
			stmt.setString(6, sp.getTrangThai());
			stmt.setString(7, sp.getHinhAnh());

			n = stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return n > 0;
	}
	public boolean capNhat(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("update SanPham set "
					+ "tenSanPham = ? ," 
					+ "loaiSanPham = ? ,"
					+ "donGia = ? ,"
					+ "moTa = ? ,"
					+ "trangThai = ? ,"
					+ "hinhAnh = ? "
					+ "where maSanPham = ? ");
			
			
			stmt.setString(1, sp.getTenSP());
			stmt.setString(2, sp.getLoaiSP());
			stmt.setDouble(3, sp.getDonGia());
			stmt.setString(4, sp.getMoTa());	
			stmt.setString(5, sp.getTrangThai());
			stmt.setString(6, sp.getHinhAnh());
			stmt.setString(7, sp.getMaSP());
			n = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return n > 0;
	}
	public boolean kiemTraSP (SanPham spMoi) {
		if (sanPhams.contains(spMoi))
			return false;
		else
			return sanPhams.add(spMoi);
	}
	public SanPham timKiemSP(String ma) {
		SanPham spCanTim = new SanPham(ma);
		if (sanPhams.contains(spCanTim))
			return sanPhams.get(sanPhams.indexOf(spCanTim));
		else
			return null;
	}
}
