package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDon1 {
	private String maHD;
	private LocalDateTime ngayLapHD;
	private String ghiChu;
	private GiamGia giamGia;
	private NhanVien nv;
	private KhachHang kh;
	private float thue;
	private String phuongThucThanhToan;
	private String trangThaiThanhToan;
	private String hinThuc;
	private ArrayList<ChiTietHD> chiTietHDs = new ArrayList<ChiTietHD>();
	//Không ghi vào sơ đồ
	private double tongTien;
	public HoaDon1() {
	
	}
	public HoaDon1(String maHD) {
		super();
		this.maHD = maHD;
	}

	public HoaDon1(String maHD, LocalDateTime ngayLapHD, String ghiChu, GiamGia giamGia, NhanVien nv, KhachHang kh,
			float thue,double tongTien ,String phuongThucThanhToan, String trangThaiThanhToan, String hinThuc) {
		super();
		this.maHD = maHD;
		this.ngayLapHD = ngayLapHD;
		this.ghiChu = ghiChu;
		this.giamGia = giamGia;
		this.nv = nv;
		this.kh = kh;
		this.thue = thue;
		this.phuongThucThanhToan = phuongThucThanhToan;
		this.trangThaiThanhToan = trangThaiThanhToan;
		this.hinThuc = hinThuc;
		this.tongTien = tongTien;
	}

	public HoaDon1(String maHD, LocalDateTime ngayLapHD, String ghiChu, GiamGia giamGia, NhanVien nv, KhachHang kh,
			float thue, String phuongThucThanhToan, String trangThaiThanhToan, String hinThuc) {
		super();
		this.maHD = maHD;
		this.ngayLapHD = ngayLapHD;
		this.ghiChu = ghiChu;
		this.giamGia = giamGia;
		this.nv = nv;
		this.kh = kh;
		this.thue = thue;
		this.phuongThucThanhToan = phuongThucThanhToan;
		this.trangThaiThanhToan = trangThaiThanhToan;
		this.hinThuc = hinThuc;
	}
	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public LocalDateTime getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(LocalDateTime ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public GiamGia getGiamGia() {
		return giamGia;
	}

	public void setGiamGia(GiamGia giamGia) {
		this.giamGia = giamGia;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}
	
	public float getThue() {
		return thue;
	}

	public void setThue(float thue) {
		this.thue = thue;
	}

	public String getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}

	public void setPhuongThucThanhToan(String phuongThucThanhToan) {
		this.phuongThucThanhToan = phuongThucThanhToan;
	}

	public String getTrangThaiThanhToan() {
		return trangThaiThanhToan;
	}

	public void setTrangThaiThanhToan(String trangThaiThanhToan) {
		this.trangThaiThanhToan = trangThaiThanhToan;
	}
	
	public ArrayList<ChiTietHD> getChiTietHDs() {
		return chiTietHDs;
	}

	public void setChiTietHDs(ArrayList<ChiTietHD> chiTietHDs) {
		this.chiTietHDs = chiTietHDs;
	}

	public String getHinThuc() {
		return hinThuc;
	}

	public void setHinThuc(String hinThuc) {
		this.hinThuc = hinThuc;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", ghiChu=" + ghiChu + ", giamGia=" + giamGia
				+ ", nv=" + nv + ", kh=" + kh + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHD.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		HoaDon1 other = (HoaDon1) obj;
		return Objects.equals(maHD.toLowerCase(), other.maHD.toLowerCase());
	}
	//Thêm chi tiết hóa đơn
	public boolean themChiTietHD(ChiTietHD chiTietHD) {
		chiTietHDs.add(chiTietHD);
		return true;
	}
	//Tính tổng tiền của hóa đơn
	public double tinhTongTien() {
		double tong = 0;
		for(ChiTietHD chiTietHD: chiTietHDs) {
			tong += chiTietHD.tinhThanhTien();
		}
		return tong;
	}	
}
