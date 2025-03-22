package gui;

import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class InvoiceGenerator {
	private String maHD;
	private String thuNgan;
	private String ngayLap;
	private String hinhThuc;
	private String tongTienThanhToan;
	private String tongTien;
	private String thue;
	private String tienGiam;
	private Object[][] chiTiet = new Object[100][100];


	public InvoiceGenerator(String maHD, String thuNgan, String ngayLap, String hinhThuc, String tongTienThanhToan,
			Object[][] chiTiet, String tongTien ,String thue, String tienGiam) {
		super();
		this.maHD = maHD;
		this.thuNgan = thuNgan;
		this.ngayLap = ngayLap;
		this.hinhThuc = hinhThuc;
		this.tongTienThanhToan = tongTienThanhToan;
		this.chiTiet = chiTiet;
		this.tongTien = tongTien;
		this.thue = thue;
		this.tienGiam = tienGiam;
	}

	public InvoiceGenerator() {

	}
	
	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getThuNgan() {
		return thuNgan;
	}

	public void setThuNgan(String thuNgan) {
		this.thuNgan = thuNgan;
	}

	public String getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(String ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getHinhThuc() {
		return hinhThuc;
	}

	public void setHinhThuc(String hinhThuc) {
		this.hinhThuc = hinhThuc;
	}

	public String gettongTienThanhToan() {
		return tongTienThanhToan;
	}

	public void settongTienThanhToan(String tongTienThanhToan) {
		this.tongTienThanhToan = tongTienThanhToan;
	}
	
	public String getTongTienThanhToan() {
		return tongTienThanhToan;
	}

	public void setTongTienThanhToan(String tongTienThanhToan) {
		this.tongTienThanhToan = tongTienThanhToan;
	}

	public String getTongTien() {
		return tongTien;
	}

	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}

	public String getThue() {
		return thue;
	}

	public void setThue(String thue) {
		this.thue = thue;
	}

	public Object[][] getChiTiet() {
		return chiTiet;
	}

	public void setChiTiet(Object[][] chiTiet) {
		this.chiTiet = chiTiet;
	}

	public static String getDest() {
		return DEST;
	}
	
	public static final String DEST = "C:\\Users\\Admin\\Downloads\\hoadon.pdf";

    public void createInvoice(String dest) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        addMetaData(document);
        addTitlePage(document);
        addContent(document);

        document.close();
    }

    private void addMetaData(Document document) {
        document.addTitle("Invoice");
        document.addAuthor("Your Name");
        document.addCreator("Your Name");
    }

    private void addTitlePage(Document document) throws DocumentException {
    	BaseFont bf;
		try {
			bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf,22,Font.BOLD);
			Font font1 = new Font(bf ,13);
	        Paragraph preface = new Paragraph();
	        // Tạo một Chunk chứa dòng cần căn giữa
            Paragraph title = new Paragraph(new Paragraph("QUÁN CÀ PHÊ CENTER", font));
            Paragraph title1 = new Paragraph(new Paragraph("HÓA ĐƠN BÁN HÀNG", font));
            title.setAlignment(Paragraph.ALIGN_CENTER);//Căn giữa
            title1.setAlignment(Paragraph.ALIGN_CENTER);//Căn giữa
            preface.add(title);
            preface.add(title1);
            
            preface.add(new Paragraph("Mã hóa đơn: " + maHD,font1));
	        preface.add(new Paragraph("Thu ngân: " + thuNgan,font1));
	        preface.add(new Paragraph("Ngày lập hóa đơn: " + ngayLap,font1));
	        preface.add(new Paragraph("Hình thức: " + hinhThuc,font1));
	        preface.add(new Paragraph("Địa chỉ: 114/7 Đường Dương Quảng Hàm, Quận Gò Vấp,TP.HCM",font1));

	        preface.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
	        document.add(preface);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void addContent(Document document) throws DocumentException {
    	BaseFont bf;
		try {
			bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf,13,Font.BOLD);
			Font font1 = new Font(bf,16,Font.BOLD);
			Font font2 = new Font(bf,12,Font.BOLD);
			Font font3 = new Font(bf,12);
			Font font4 = new Font(bf,14,Font.BOLD);
	    	
	        PdfPTable table = new PdfPTable(4);
	        table.setWidthPercentage(100);
	        table.addCell(new Paragraph("Mặt hàng",font));
	        table.addCell(new Paragraph("Đơn giá",font));
	        table.addCell(new Paragraph("Số lượng",font));
	        table.addCell(new Paragraph("Thành tiền",font));

	        // Add rows with data
	        for (Object[] row : chiTiet) {
	            for (Object cell : row) {
	                table.addCell(new Paragraph(cell.toString(), font3));
	            }
	        }
	        // Add more rows here if needed

	        document.add(table);

	        // Add total payment
	        Paragraph total = new Paragraph("Tổng tiền: " + tongTien, font4);
	        Paragraph total3 = new Paragraph("Tiền giảm: -" + tienGiam, font4);
	        Paragraph total2 = new Paragraph("Thuế 10%: +" + thue, font4);
	        Paragraph total1 = new Paragraph("Tổng tiền thanh toán: " + tongTienThanhToan, font1);
	        total.setAlignment(Element.ALIGN_RIGHT);
	        total1.setAlignment(Element.ALIGN_RIGHT);
	        total2.setAlignment(Element.ALIGN_RIGHT);
	        total3.setAlignment(Element.ALIGN_RIGHT);
	        document.add(total);
	        document.add(total3);
	        document.add(total2);
	        document.add(total1);

	        // Add a line separator
	        Paragraph separator = new Paragraph();
	        separator.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
	        document.add(separator);

	        // Add thank you message
	        Paragraph thankYou = new Paragraph("Cảm ơn quý khách. Hẹn gặp lại!",font2);
	        thankYou.setAlignment(Element.ALIGN_CENTER);
	        document.add(thankYou);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
