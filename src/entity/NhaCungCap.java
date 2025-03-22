package entity;

import java.util.Objects;

public class NhaCungCap {

	private String maNCC;
	private String tenNCC;
	private String emailNCC;
	private String sdtNCC;
	private String diaChiNCC;
	private String trangThai;
	
	public NhaCungCap() {
		super();
	}

	public NhaCungCap(String maNCC) {
		super();
		this.maNCC = maNCC;
	}

	public NhaCungCap(String maNCC, String tenNCC, String emailNCC, String sdtNCC, String diaChiNCC) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.emailNCC = emailNCC;
		this.sdtNCC = sdtNCC;
		this.diaChiNCC = diaChiNCC;
	}
	

	public NhaCungCap(String maNCC, String tenNCC, String emailNCC, String sdtNCC, String diaChiNCC, String trangThai) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.emailNCC = emailNCC;
		this.sdtNCC = sdtNCC;
		this.diaChiNCC = diaChiNCC;
		this.trangThai = trangThai;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getEmailNCC() {
		return emailNCC;
	}

	public void setEmailNCC(String emailNCC) {
		this.emailNCC = emailNCC;
	}

	public String getSdtNCC() {
		return sdtNCC;
	}

	public void setSdtNCC(String sdtNCC) {
		this.sdtNCC = sdtNCC;
	}

	public String getDiaChiNCC() {
		return diaChiNCC;
	}

	public void setDiaChiNCC(String diaChiNCC) {
		this.diaChiNCC = diaChiNCC;
	}
	
	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", emailNCC=" + emailNCC + ", sdtNCC=" + sdtNCC
				+ ", diaChiNCC=" + diaChiNCC + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNCC.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		NhaCungCap other = (NhaCungCap) obj;
		return Objects.equals(maNCC.toLowerCase(), other.maNCC.toLowerCase());
	}

}
