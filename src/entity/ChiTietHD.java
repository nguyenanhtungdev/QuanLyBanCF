package entity;

public class ChiTietHD {
	private String maHoaDon;
	private String maSanPham;
	private double donGia;
	private int soLuong;
	
	public ChiTietHD() {
		
	}
	
	public ChiTietHD(String maHoaDon, String maSanPham, double donGia, int soLuong) {
		super();
		this.maHoaDon = maHoaDon;
		this.maSanPham = maSanPham;
		this.donGia = donGia;
		this.soLuong = soLuong;
	}
	public ChiTietHD(String maSanPham, double donGia, int soLuong) {
		super();
		this.maSanPham = maSanPham;
		this.donGia = donGia;
		this.soLuong = soLuong;
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	//Tính thành tiền
	public double tinhThanhTien() {
		return soLuong * donGia;
	}
	@Override
	public String toString() {
		return "ChiTietHD [maHoaDon=" + maHoaDon + ", maSanPham=" + maSanPham + ", donGia=" + donGia + ", soLuong="
				+ soLuong + "]";
	}
}
