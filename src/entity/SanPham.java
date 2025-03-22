package entity;

import java.util.Objects;

public class SanPham {

	private String maSP;
	private String tenSP;
	private String loaiSP;
	private double donGia;
	private String moTa;
	private String trangThai;
	private String hinhAnh;
	public SanPham() {
	
	}

	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}
	
	public SanPham(String maSP, String tenSP, double donGia) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.donGia = donGia;
	}

	public SanPham(String maSP, String tenSP, String loaiSP, double donGia, String moTa,String trangThai,String hinhAnh) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.loaiSP = loaiSP;
		this.donGia = donGia;
		this.moTa = moTa;
		this.trangThai = trangThai;
		this.hinhAnh = hinhAnh;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getLoaiSP() {
		return loaiSP;
	}

	public void setLoaiSP(String loaiSP) {
		this.loaiSP = loaiSP;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	
	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", loaiSP=" + loaiSP + ", donGia=" + donGia + ", moTa="
				+ moTa + ", trangThai=" + trangThai + ", hinhAnh=" + hinhAnh + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maSP.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSP.toLowerCase(), other.maSP.toLowerCase());
	}
}
