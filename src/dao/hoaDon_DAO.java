package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.GiamGia;
import entity.HoaDon1;
import entity.KhachHang;
import entity.NhanVien;


public class hoaDon_DAO {
	public hoaDon_DAO() {
		
	}
	public ArrayList<HoaDon1> getalltHoaDon(){
		ArrayList<HoaDon1> hoaDons = new ArrayList<HoaDon1>();
		ConnectDB.getInstance();
		Connection con =  ConnectDB.getConnection();
		String sql = "Select *From HoaDon";
		try {
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				String maHD = resultSet.getString(1);
				//Chuyển về kiểu date time
				java.sql.Timestamp ngayLap = resultSet.getTimestamp(2);
				LocalDateTime ngayLapLocalDateTime = ngayLap.toLocalDateTime();
				String ghiChu = resultSet.getString(3);
				String maGiamGia = resultSet.getString(4);
				String maKH = resultSet.getString(5);
				String maNV = resultSet.getString(6);
				float thue = resultSet.getFloat(7);
				double tongTien = resultSet.getDouble(8);
				String phuongThucThanhToan = resultSet.getString(9);
				String trangThai = resultSet.getString(10);
				String hinhThuc = resultSet.getString(11);
				GiamGia giamGia = new GiamGia(maGiamGia);
				KhachHang khachHang = new KhachHang(maKH);
				NhanVien nv = new NhanVien(maNV);
				HoaDon1 don = new HoaDon1(maHD, ngayLapLocalDateTime, ghiChu, giamGia, nv, khachHang, thue, tongTien,phuongThucThanhToan, trangThai, hinhThuc);
				hoaDons.add(don);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hoaDons;
	}
	
	public boolean addHoaDon(HoaDon1 hoaDon1) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "Insert into HoaDon values(?,?,?,?,?,?,?,?,?,?,?) ";
		
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1,hoaDon1.getMaHD());
			LocalDateTime ngayLap = LocalDateTime.now(); // hoặc bất kỳ giá trị LocalDateTime nào bạn muốn chèn
			Timestamp ngayLapTimestamp = Timestamp.valueOf(ngayLap);
			stmt.setTimestamp(2, ngayLapTimestamp);
			stmt.setString(3,hoaDon1.getGhiChu());
			stmt.setString(4, null);
			stmt.setString(5, null);
			stmt.setString(6, hoaDon1.getNv().getMaNV());
			stmt.setFloat(7, hoaDon1.getThue());
			stmt.setDouble(8,hoaDon1.tinhTongTien());
			stmt.setString(9,hoaDon1.getPhuongThucThanhToan());
			stmt.setString(10,"Chưa thanh toán");
			stmt.setString(11,hoaDon1.getHinThuc());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<HoaDon1> timHoaDonTheoMa(String ma){
		ArrayList<HoaDon1> ds = new ArrayList<HoaDon1>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from HoaDon where maHoaDon = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma.toLowerCase());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				java.sql.Timestamp ngayLap = rs.getTimestamp(2);
				LocalDateTime ngayLapLocalDateTime = ngayLap.toLocalDateTime();
				String ghiChu = rs.getString(3);
				String maGiamGia = rs.getString(4);
				GiamGia giamGia = new GiamGia(maGiamGia);
				String maKH = rs.getString(5);
				String maNv = rs.getString(6);
				float thue = rs.getFloat(7);
				double tongtien = rs.getDouble(8);
				String pt = rs.getString(9);
				String th = rs.getString(10);
				String ht = rs.getString(11);
				
				NhanVien nv = new NhanVien(maNv);
				KhachHang kh = new KhachHang(maKH);
				HoaDon1 hd = new HoaDon1(maHD, ngayLapLocalDateTime, ghiChu, giamGia, nv, kh,thue,tongtien,pt,th,ht);
				ds.add(hd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return ds;
	}
	public ArrayList<HoaDon1> timHoaDonTheoNgay(LocalDate ngayLap) {
		ArrayList<HoaDon1> ds = new ArrayList<HoaDon1>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		try {
			String sql = "Select * from HoaDon where ngayLapHoaDon = ?";
			stm = con.prepareStatement(sql);
			stm.setDate(1, java.sql.Date.valueOf(ngayLap));
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				java.sql.Timestamp ngayLap1 = rs.getTimestamp(2);
				LocalDateTime ngayLapLocalDateTime = ngayLap1.toLocalDateTime();
				String ghiChu = rs.getString(3);
				String maGiamGia = rs.getString(4);
				GiamGia giamGia = new GiamGia(maGiamGia);
				String maKH = rs.getString(5);
				String maNv = rs.getString(6);
				float thue = rs.getFloat(7);
				double tongtien = rs.getDouble(8);
				String pt = rs.getString(9);
				String th = rs.getString(10);
				String ht = rs.getString(11);
				
				NhanVien nv = new NhanVien(maNv);
				KhachHang kh = new KhachHang(maKH);
				HoaDon1 hd = new HoaDon1(maHD, ngayLapLocalDateTime, ghiChu, giamGia, nv, kh,thue,tongtien,pt,th,ht);
				ds.add(hd);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				stm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return ds;
	}
}
