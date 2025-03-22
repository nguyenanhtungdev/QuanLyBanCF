package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;


public class taiKhoan_DAO {

	public taiKhoan_DAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	ArrayList<TaiKhoan> dsTK;
	
	public ArrayList<TaiKhoan> getalltbTaiKhoan() {
		dsTK = new ArrayList<TaiKhoan>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from TaiKhoan";
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery(sql);
			
			while (rs.next()) {
				String maTK = rs.getString(1);
				String tenTK = rs.getString(2);
				String matKhau = rs.getString(3);
				String quyen = rs.getString(4);
				NhanVien nv = new NhanVien(rs.getString("maNV"));
				String trangThai = rs.getString(6);
				
				TaiKhoan tk = new TaiKhoan(maTK, tenTK, matKhau, quyen, nv, trangThai);
				
				dsTK.add(tk);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsTK; 
	}
	
	public boolean taoTK(TaiKhoan tk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			stmt = con.prepareStatement("ALTER TABLE TaiKhoan NOCHECK CONSTRAINT maNV_FK; insert into " + "TaiKhoan values(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, tk.getMaTK());
			stmt.setString(2, tk.getTenTK());
			stmt.setString(3, tk.getMatKhau());
			stmt.setString(4, tk.getQuyen());
			stmt.setString(5, tk.getNv().getMaNV());
			stmt.setString(6, tk.getTrangThai());
			
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
	
	public boolean capNhat(TaiKhoan tk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		try {
			if (tk.getMatKhau() == null) {
				stmt = con.prepareStatement( "update TaiKhoan set "
						+ "tenTK = ? ,"
						+ "quyen = ? ,"
						+ "trangThai = ? "
						+ "where maTK = ? ");
				
				stmt.setString(1, tk.getTenTK());
				stmt.setString(2, tk.getQuyen());
				stmt.setString(3, tk.getTrangThai());
				stmt.setString(4, tk.getMaTK());
				n = stmt.executeUpdate();
			}
			
			else {
				stmt = con.prepareStatement( "update TaiKhoan set "
						+ "tenTK = ? ,"
						+ "matKhau = ? ,"
						+ "quyen = ? ,"
						+ "trangThai = ? "
						+ "where maTK = ? ");
				
				stmt.setString(1, tk.getTenTK());
				stmt.setString(2, tk.getMatKhau());
				stmt.setString(3, tk.getQuyen());
				stmt.setString(4, tk.getTrangThai());
				stmt.setString(5, tk.getMaTK());
				n = stmt.executeUpdate();
			}
			
			
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
	
	
	public TaiKhoan timKiemTKTheoMaNV(String maNV) {
		for (TaiKhoan tk : dsTK) {
			if (tk.getNv().getMaNV().equalsIgnoreCase(maNV)) {
				return tk;
			}	
		}
	
		return null;
	}
	
	public boolean capNhatTrangThaiTK(String ma, String trangThaiNV) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		TaiKhoan tk = timKiemTKTheoMaNV(ma);
		
		if (tk != null) {
			try {
				if (trangThaiNV.equals("Còn làm")) {
					stmt = con.prepareStatement("update TaiKhoan set trangThai = ? where maNV = ?");
					stmt.setString(1, "Còn sử dụng");
					stmt.setString(2, ma);
					n = stmt.executeUpdate();
				}
				else {
					stmt = con.prepareStatement("update TaiKhoan set trangThai = ? where maNV = ?");
					stmt.setString(1, "Không sử dụng");
					stmt.setString(2, ma);
					n = stmt.executeUpdate();
				}
					
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
		
		return true;
	}
	
	public boolean capNhatTrangThaiNV(String ma, String trangThaiTK) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		int n = 0;
		
		TaiKhoan tk = timKiemTKTheoMaNV(ma);
		
		if (tk != null) {
			try {
				if (trangThaiTK.equals("Còn sử dụng")) {
					stmt = con.prepareStatement("update NhanVien set trangThai = ? where maNV = ?");
					stmt.setString(1, "Còn làm");
					stmt.setString(2, ma);
					n = stmt.executeUpdate();
				}
				else {
					stmt = con.prepareStatement("update NhanVien set trangThai = ? where maNV = ?");
					stmt.setString(1, "Nghỉ làm");
					stmt.setString(2, ma);
					n = stmt.executeUpdate();
				}
					
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
		
		return true;
	}
	
	public boolean kiemTraTK(TaiKhoan tk) {
		if (dsTK.contains(tk))
			return false;
		else
			return dsTK.add(tk);
	}
	
	// tìm tài khoản cho đăng nhập
	public TaiKhoan TimTK(String maTK) {
		for (TaiKhoan tk : dsTK) {
			if (tk.getMaTK().equalsIgnoreCase(maTK)) {
				return tk;
			}	
		}
	
		return null;
	}
	
	public TaiKhoan timNV(String maNV) {
		for (TaiKhoan tk : dsTK) {
			if (tk.getNv().getMaNV().equalsIgnoreCase(maNV)) {
				return tk;
			}	
		}
	
		return null;
	}
	
	public boolean kiemTraTK(String maNV, String matKhau) {
		TaiKhoan tk = timNV(maNV); 
	
		if (tk != null) {
			String mk = tk.getMatKhau();
			String trangThai = tk.getTrangThai();
			
			if (mk.equals(matKhau) && trangThai.equalsIgnoreCase("Còn sử dụng"))
				return true;
				
		}
		
		return false;
	}

	
	
}
