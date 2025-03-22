package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String hoNV;
	private String tenNV;
	private LocalDate ngaySinh;
	private boolean gioiTinh;
	private String sdtNV;
	private String chucVu;
	private float heSoLuong;
	private String trangThai;
	
	public NhanVien() {
		
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public NhanVien(String maNV, String hoNV, String tenNV, LocalDate ngaySinh, boolean gioiTinh, String sdtNV,
			String chucVu, float heSoLuong, String trangThai) {
		super();
		this.maNV = maNV;
		this.hoNV = hoNV;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.sdtNV = sdtNV;
		this.chucVu = chucVu;
		this.heSoLuong = heSoLuong;
		this.trangThai = trangThai;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoNV() {
		return hoNV;
	}

	public void setHoNV(String hoNV) {
		this.hoNV = hoNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSdtNV() {
		return sdtNV;
	}

	public void setSdtNV(String sdtNV) {
		this.sdtNV = sdtNV;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public float getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(float heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoNV=" + hoNV + ", tenNV=" + tenNV + ", ngaySinh=" + ngaySinh
				+ ", gioiTinh=" + gioiTinh + ", sdtNV=" + sdtNV + ", chucVu=" + chucVu + ", heSoLuong=" + heSoLuong
				+ ", trangThai=" + trangThai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNV.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV.toLowerCase(), other.maNV.toLowerCase());
	}

}
