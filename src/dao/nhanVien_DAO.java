package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

import connectDB.ConnectDB;
import entity.NhanVien;

public class nhanVien_DAO {

	public nhanVien_DAO() {
		super();
	}

	ArrayList<NhanVien> dsNV;
	
	
	public ArrayList<NhanVien> getalltbNhanVien() {
		dsNV = new ArrayList<NhanVien>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from NhanVien";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maNV = rs.getString(1);
				String hoNV = rs.getString(2);
				String tenNV = rs.getString(3);
				LocalDate ngaySinh = rs.getDate(4).toLocalDate();
				boolean gioiTinh = rs.getBoolean(5);
				String sdt = rs.getString(6);
				String chucVu = rs.getString(7);
				float heSoLuong = rs.getFloat(8);
				String trangThai = rs.getString(9);
				
				NhanVien nv = new NhanVien(maNV, hoNV, tenNV, ngaySinh, gioiTinh, sdt, chucVu, heSoLuong, trangThai);
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsNV;
	}
	
	public boolean taoNV(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into " + "NhanVien values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, nv.getMaNV());
			stmt.setString(2, nv.getHoNV());
			stmt.setString(3, nv.getTenNV());
			stmt.setDate(4, Date.valueOf(nv.getNgaySinh()));
			stmt.setBoolean(5, nv.isGioiTinh());
			stmt.setString(6, nv.getSdtNV());
			stmt.setString(7, nv.getChucVu());
			stmt.setFloat(8, nv.getHeSoLuong());
			stmt.setString(9, nv.getTrangThai());
			
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
	
	public boolean capNhat( NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("update NhanVien set "
					+ "hoNV = ? ,"
					+ "tenNV = ? ," 
					+ "ngaySinh = ? ,"
					+ "gioiTinh = ? ,"
					+ "soDienThoai = ? ,"
					+ "chucVu = ? ,"
					+ "heSoLuong = ? ,"
					+ "trangThai = ? "
					+ "where maNV = ? ");
			
			stmt.setString(1, nv.getHoNV());
			stmt.setString(2, nv.getTenNV());
			stmt.setDate(3, Date.valueOf(nv.getNgaySinh()));
			stmt.setBoolean(4, nv.isGioiTinh());
			stmt.setString(5, nv.getSdtNV());
			stmt.setString(6, nv.getChucVu());
			stmt.setFloat(7, nv.getHeSoLuong());
			stmt.setString(8, nv.getTrangThai());
			stmt.setString(9, nv.getMaNV());
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
	
	public NhanVien timKiemNV(String ma) {
		NhanVien nvCanTim = new NhanVien(ma);
		if (dsNV.contains(nvCanTim))
			return dsNV.get(dsNV.indexOf(nvCanTim));
		else
			return null;
	}
	
	public boolean kiemTraNV (NhanVien nvMoi) {
		if (dsNV.contains(nvMoi))
			return false;
		else
			return dsNV.add(nvMoi);
	}

	
}
