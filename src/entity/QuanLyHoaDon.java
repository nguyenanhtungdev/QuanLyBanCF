package entity;

import java.util.ArrayList;

public class QuanLyHoaDon {
	private ArrayList<HoaDon1> hoaDon1s;

	public QuanLyHoaDon(ArrayList<HoaDon1> hoaDon1s) {
		super();
		this.hoaDon1s = hoaDon1s;
		hoaDon1s = new ArrayList<HoaDon1>();
	}
	public void themHD(HoaDon1 don1) {
		hoaDon1s.add(don1);
	}
	public ArrayList<HoaDon1> getHoaDon1s() {
		return hoaDon1s;
	}

	public void setHoaDon1s(ArrayList<HoaDon1> hoaDon1s) {
		this.hoaDon1s = hoaDon1s;
	}
	
}
