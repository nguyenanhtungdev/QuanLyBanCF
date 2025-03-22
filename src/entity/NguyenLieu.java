package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NguyenLieu {

	private String maNL;
	private String tenNL;
	private double giaThuMua;
	private String donVi;
	private int soLuong;
	private String moTa;
	private String loaiNL;
	private LocalDate ngayNhapKho;
	private LocalDate hanSuDung;
	private SanPham sp;
	
	public NguyenLieu() {
		
	}

	public NguyenLieu(String maNL) {
		super();
		this.maNL = maNL;
	}

	public NguyenLieu(String maNL, String tenNL, double giaThuMua, String donVi, int soLuong, String moTa, String loaiNL,
			LocalDate ngayNhapKho, LocalDate hanSuDung, SanPham sp) {
		super();
		this.maNL = maNL;
		this.tenNL = tenNL;
		this.giaThuMua = giaThuMua;
		this.donVi = donVi;
		this.soLuong = soLuong;
		this.moTa = moTa;
		this.loaiNL = loaiNL;
		this.ngayNhapKho = ngayNhapKho;
		this.hanSuDung = hanSuDung;
		this.sp = sp;
	}

	public String getMaNL() {
		return maNL;
	}

	public void setMaNL(String maNL) {
		this.maNL = maNL;
	}

	public String getTenNL() {
		return tenNL;
	}

	public void setTenNL(String tenNL) {
		this.tenNL = tenNL;
	}

	public double getGiaThuMua() {
		return giaThuMua;
	}

	public void setGiaThuMua(double giaThuMua) {
		this.giaThuMua = giaThuMua;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getLoaiNL() {
		return loaiNL;
	}

	public void setLoaiNL(String loaiNL) {
		this.loaiNL = loaiNL;
	}

	public LocalDate getNgayNhapKho() {
		return ngayNhapKho;
	}

	public void setNgayNhapKho(LocalDate ngayNhapKho) {
		this.ngayNhapKho = ngayNhapKho;
	}

	public LocalDate getHanSuDung() {
		return hanSuDung;
	}

	public void setHanSuDung(LocalDate hanSuDung) {
		this.hanSuDung = hanSuDung;
	}

	public SanPham getSp() {
		return sp;
	}

	public void setSp(SanPham sp) {
		this.sp = sp;
	}

	@Override
	public String toString() {
		return "NguyenLieu [maNL=" + maNL + ", tenNL=" + tenNL + ", giaThuMua=" + giaThuMua + ", donVi=" + donVi
				+ ", soLuong=" + soLuong + ", moTa=" + moTa + ", loaiNL=" + loaiNL + ", ngayNhapKho=" + ngayNhapKho
				+ ", hanSuDung=" + hanSuDung + ", sp=" + sp + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNL.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		NguyenLieu other = (NguyenLieu) obj;
		return Objects.equals(maNL.toLowerCase(), other.maNL.toLowerCase());
	}
}
