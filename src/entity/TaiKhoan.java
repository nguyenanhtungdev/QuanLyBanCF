package entity;

import java.util.Objects;

public class TaiKhoan {

	private String maTK;
	private String tenTK;
	private String matKhau;
	private String quyen;
	private NhanVien nv;
	private String trangThai;
	
	public TaiKhoan() {
		
	}

	public TaiKhoan(String maTK) {
		super();
		this.maTK = maTK;
	}

	public TaiKhoan(String maTK, String tenTK, String matKhau, String quyen, NhanVien nv, String trangThai) {
		super();
		this.maTK = maTK;
		this.tenTK = tenTK;
		this.matKhau = matKhau;
		this.quyen = quyen;
		this.nv = nv;
		this.trangThai = trangThai;
	}

	public String getMaTK() {
		return maTK;
	}

	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}

	public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "TaiKhoan [maTK=" + maTK + ", tenTK=" + tenTK + ", matKhau=" + matKhau + ", quyen=" + quyen + ", nv="
				+ nv + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maTK.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(maTK.toLowerCase(), other.maTK.toLowerCase());
	}
	
}
