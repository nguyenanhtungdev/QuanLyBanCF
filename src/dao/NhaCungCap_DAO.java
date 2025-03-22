package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.NhaCungCap;


public class NhaCungCap_DAO {

	public NhaCungCap_DAO() {
		super();
	}

	ArrayList<NhaCungCap> dsNCC;
	
	
	public ArrayList<NhaCungCap> getalltbNhaCungCap() {
		dsNCC = new ArrayList<NhaCungCap>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from NhaCungCap";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maNCC = rs.getString(1);
				String tenNCC = rs.getString(2);
				String email = rs.getString(3);
				String sdt = rs.getString(4);
				String diaChi = rs.getString(5);
				String trangThai = rs.getString(6);
				
				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, email, sdt, diaChi, trangThai);
				dsNCC.add(ncc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsNCC;
	}
	
	public boolean taoNCC(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("insert into " + "NhaCungCap values(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, ncc.getMaNCC());
			stmt.setString(2, ncc.getTenNCC());
			stmt.setString(3, ncc.getEmailNCC());
			stmt.setString(4, ncc.getSdtNCC());
			stmt.setString(5, ncc.getDiaChiNCC());
			stmt.setString(6, ncc.getTrangThai());
		
			
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
	
	public boolean capNhat( NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("update NhaCungCap set "
					+ "tenNhaCC = ? ," 
					+ "email = ? ,"
					+ "soDienThoai = ? ,"
					+ "diaChi = ? ,"
					+ "trangThai = ? "
					+ "where maNhaCC = ? ");
			
			stmt.setString(1, ncc.getTenNCC());
			stmt.setString(2, ncc.getEmailNCC());
			stmt.setString(3, ncc.getSdtNCC());
			stmt.setString(4, ncc.getDiaChiNCC());
			stmt.setString(5, ncc.getTrangThai());
			stmt.setString(6, ncc.getMaNCC());
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
	
	public NhaCungCap timKiemNCC(String ma) {
		NhaCungCap ncc = new NhaCungCap(ma);
		if (dsNCC.contains(ncc))
			return dsNCC.get(dsNCC.indexOf(ncc));
		else
			return null;
	}
	
	public boolean kiemTraNCC (NhaCungCap ncc) {
		if (dsNCC.contains(ncc))
			return false;
		else
			return dsNCC.add(ncc);
	}

	
}
