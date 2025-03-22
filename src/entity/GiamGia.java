package entity;

import java.time.LocalDate;
import java.util.Objects;

public class GiamGia {

	private String maGiamGia;
	private String loaiGiamGia;
	private String trangThai;
	private LocalDate ngayHetHan;
	
	public GiamGia() {
		super();
	}
	
	public GiamGia(String maGiamGia) {
		super();
		this.maGiamGia = maGiamGia;
	}

	public GiamGia(String maGiamGia, String loaiGiamGia, String trangThai, LocalDate ngayHetHan) {
		super();
		this.maGiamGia = maGiamGia;
		this.loaiGiamGia = loaiGiamGia;
		this.trangThai = trangThai;
		this.ngayHetHan = ngayHetHan;
	}

	public String getMaGiamGia() {
		return maGiamGia;
	}

	public void setMaGiamGia(String maGiamGia) {
		this.maGiamGia = maGiamGia;
	}

	public String getLoaiGiamGia() {
		return loaiGiamGia;
	}

	public void setLoaiGiamGia(String loaiGiamGia) {
		this.loaiGiamGia = loaiGiamGia;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public LocalDate getNgayHetHan() {
		return ngayHetHan;
	}

	public void setNgayHetHan(LocalDate ngayHetHan) {
		this.ngayHetHan = ngayHetHan;
	}

	@Override
	public String toString() {
		return "GiamGia [maGiamGia=" + maGiamGia + ", loaiGiamGia=" + loaiGiamGia + ", trangThai=" + trangThai
				+ ", ngayHetHan=" + ngayHetHan + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maGiamGia.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		GiamGia other = (GiamGia) obj;
		return Objects.equals(maGiamGia.toLowerCase(), other.maGiamGia.toLowerCase());
	}
	
	
	
	
}
