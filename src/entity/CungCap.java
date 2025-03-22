package entity;

import java.time.LocalDate;

public class CungCap {
	
	private LocalDate ngayCungCap;
	private int soLuong;
	private double donGia;
	private NguyenLieu nl;
	private NhaCungCap ncc;
	
	public CungCap() {
		
	}
	
	public CungCap(NguyenLieu nl, NhaCungCap ncc) {
		super();
		this.nl = nl;
		this.ncc = ncc;
	}

	public CungCap(NguyenLieu nl, NhaCungCap ncc, LocalDate ngayCungCap, int soLuong, double donGia) {
		super();
		this.nl = nl;
		this.ncc = ncc;
		this.ngayCungCap = ngayCungCap;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public NguyenLieu getNl() {
		return nl;
	}

	public void setNl(NguyenLieu nl) {
		this.nl = nl;
	}

	public NhaCungCap getNcc() {
		return ncc;
	}

	public void setNcc(NhaCungCap ncc) {
		this.ncc = ncc;
	}

	public LocalDate getNgayCungCap() {
		return ngayCungCap;
	}

	public void setNgayCungCap(LocalDate ngayCungCap) {
		this.ngayCungCap = ngayCungCap;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	@Override
	public String toString() {
		return "CungCap [nl=" + nl + ", ncc=" + ncc + ", ngayCungCap=" + ngayCungCap + ", soLuong=" + soLuong
				+ ", donGia=" + donGia + "]";
	}
}
