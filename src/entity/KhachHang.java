package entity;

import java.util.Objects;

public class KhachHang {

	private String maKH;
	private String tenKH;
	private String sdtKH;
	private String loaiKH;
	
	public KhachHang() {
		
	}

	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}

	public KhachHang(String maKH, String tenKH, String sdtKH, String loaiKH) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdtKH = sdtKH;
		this.loaiKH = loaiKH;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getSdtKH() {
		return sdtKH;
	}

	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}

	public String getLoaiKH() {
		return loaiKH;
	}

	public void setLoaiKH(String loaiKH) {
		this.loaiKH = loaiKH;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", sdtKH=" + sdtKH + ", loaiKH=" + loaiKH + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKH.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH.toLowerCase(), other.maKH.toLowerCase());
	}

}
