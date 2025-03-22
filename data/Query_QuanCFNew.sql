CREATE DATABASE QuanLyCF
--- SU DUNG CO SO DU LIEU QUAN LY CAFE
USE QuanLyCF

--- Tao table tai khoan
--Tao table nhan vien
CREATE TABLE NhanVien (
	maNV CHAR(5) PRIMARY KEY,
	hoNV NVARCHAR(50) NOT NULL,
	tenNV NVARCHAR(50) NOT NULL,
	ngaySinh DATE,
	gioiTinh BIT,
	soDienThoai VARCHAR(15),
	chucVu NVARCHAR(30) NOT NULL,
	heSoLuong DECIMAL(10,2),
	trangThai NVARCHAR(20)
)
CREATE TABLE TaiKhoan
(
	maTK CHAR(5) PRIMARY KEY,
	tenTK VARCHAR(20) NOT NULL,
	matKhau VARCHAR(50) NOT NULL,
	quyen VARCHAR(20) NOT NULL,
	INDEX idx_tenTK (maTK),
	maNV CHAR(5),
	trangThai NVARCHAR(20),
	CONSTRAINT quyen_ck CHECK (quyen IN ('Admin','Manager','Employee')),
	
);
--Tao table khach hang
CREATE TABLE KhachHang
(
	maKH CHAR(5) PRIMARY KEY,
	tenKH NVARCHAR(70) NOT NULL,
	sdt VARCHAR(15),
	loaiKH NVARCHAR(15)
)
--Tạo table giảm giá
CREATE TABLE GiamGia (
	maGiamGia CHAR(4) PRIMARY KEY,
	loaiGiamGia NVARCHAR(20),
	trangThai NVARCHAR(20),
	ngayHetHan DATE,
)
--Tao table hoa don
CREATE TABLE HoaDon
(
	maHoaDon CHAR(5) PRIMARY KEY,
	ngayLapHoaDon DATETIME,
	ghiChu NVARCHAR(50),
	maGiamGia CHAR(4),
	maKH CHAR(5),
	maNV CHAR(5),
	thue FLOAT,
	tongTien FLOAT,
	phuongThucThanhToan NVARCHAR(50),
	trangThaiThanhToan NVARCHAR(50),
	hinhThuc NVARCHAR(50)
)
--Tao table SanPham
CREATE TABLE SanPham
(
	maSanPham CHAR(5) PRIMARY KEY,
	tenSanPham NVARCHAR(50) NOT NULL,
	loaiSanPham NVARCHAR(20) NOT NULL,
	donGia FLOAT NOT NULL,
	moTa NVARCHAR(50),
	trangThai NVARCHAR(20),
	hinhAnh VARCHAR(100)
)
--Tao table chi tiet hoa don
CREATE TABLE ChiTietHoaDon (
    maHoaDon CHAR(5),
    maSanPham CHAR(5),
    soLuong INT,
    donGia FLOAT,
    thanhTien FLOAT,
);
-- Tao table nha cung cap
CREATE TABLE NhaCungCap
(
	maNhaCC CHAR(6) PRIMARY KEY,
	tenNhaCC NVARCHAR(50) NOT NULL,
	email VARCHAR(30),
	soDienThoai VARCHAR(15),
	diaChi NVARCHAR(100),
	trangThai NVARCHAR(20),
)

--Tao table nguyen lieu
CREATE TABLE NguyenLieu
(
	maNguyenLieu CHAR(5) PRIMARY KEY,
	tenNguyenLieu NVARCHAR(30) NOT NULL,
	giaThuMua FLOAT NOT NULL,
	donVi NVARCHAR(20),
	soLuong INT,
	moTa NVARCHAR(50),
	loaiNguyenLieu NVARCHAR(30),
	ngayNhapKho DATE,
	hanSuDung DATE,
	maSanPham CHAR(5),
	trangThai NVARCHAR(20),
)
-- Tao table cung cap
CREATE TABLE CungCap
(
	maNguyenLieu CHAR(5),
	maNhaCC CHAR(6),
	ngayCungCap DATE NOT NULL,
	soLuong INT NOT NULL,
	donGia FLOAT NOT NULL
	PRIMARY KEY(maNguyenLieu,maNhaCC)
)

-- Tao cac rang buoc khoa ngoai
ALTER TABLE [dbo].[TaiKhoan] ADD CONSTRAINT maNV_FK FOREIGN KEY (maNV) REFERENCES NhanVien(maNV)
ALTER TABLE [dbo].[HoaDon] ADD CONSTRAINT maHD_NV_FK FOREIGN KEY([maKH]) REFERENCES [dbo].[KhachHang]([maKH])
ALTER TABLE [dbo].[HoaDon] ADD CONSTRAINT maHD_KH_FK FOREIGN KEY([maNV]) REFERENCES [dbo].[NhanVien]([maNV])
ALTER TABLE [dbo].[HoaDon] ADD CONSTRAINT maGiamGia_FK FOREIGN KEY([maGiamGia]) REFERENCES [dbo].[GiamGia]([maGiamGia])

ALTER TABLE [dbo].[ChiTietHoaDon] ADD CONSTRAINT chiTietHD_HD_FK FOREIGN KEY([maHoaDon]) REFERENCES [dbo].[HoaDon]([maHoaDon])
ALTER TABLE [dbo].[ChiTietHoaDon] ADD CONSTRAINT chiTietHD_SP_FK FOREIGN KEY([maSanPham]) REFERENCES [dbo].[SanPham]([maSanPham])
---Tắt kiểm tra ràng buộc bảng chi tiết hóa đơn
ALTER TABLE [dbo].[ChiTietHoaDon] NOCHECK CONSTRAINT chiTietHD_HD_FK
ALTER TABLE [dbo].[ChiTietHoaDon] NOCHECK CONSTRAINT chiTietHD_SP_FK

ALTER TABLE [dbo].[NguyenLieu] ADD CONSTRAINT nguyenLieu_FK FOREIGN KEY([maSanPham]) REFERENCES [dbo].[SanPham]([maSanPham])
ALTER TABLE [dbo].[CungCap] ADD CONSTRAINT cungCap_NL_FK FOREIGN KEY([maNguyenLieu]) REFERENCES [dbo].[NguyenLieu]([maNguyenLieu])
ALTER TABLE [dbo].[CungCap] ADD CONSTRAINT cungCap_NCC_FK FOREIGN KEY([maNhaCC]) REFERENCES [dbo].[NhaCungCap]([maNhaCC])

-- Thêm dữ liệu vào bảng Nhân viên (NhanVien)
INSERT INTO NhanVien (maNV, hoNV, tenNV, ngaySinh, gioiTinh, soDienThoai, chucVu, heSoLuong, trangThai)
VALUES 
('NV001', N'Trần', N'Thị Ngọc Anh', '1998-03-20', 0, '0987657654', N'Nhân viên bán hàng', 1.0, N'Nghỉ làm'),
('NV002', N'Phạm', N'Thị Bích', '1999-12-10', 0, '0987652345', N'Nhân viên pha chế', 1.0, N'Nghỉ làm'),
('NV003', N'Nguyễn', N'Tấn Phát', '1997-04-20', 1, '0987651246', N'Nhân viên bán hàng', 1.0, N'Còn làm'),
('NV004', N'Nguyễn', N'Bá Hải', '1998-03-22', 1, '0987650812', N'Nhân viên phục vụ', 1.0, N'Nghỉ làm'),
('NV005', N'Trần', N'Thị Ngọc', '1993-06-12', 0, '0987655619', N'Nhân viên bán hàng', 1.0, N'Còn làm'),
('NV006', N'Trần', N'Thị Khanh', '1999-07-31', 0, '0987654321', N'Nhân viên bán hàng', 1.0, N'Còn làm'),
('NV007', N'Lê', N'Văn Long', '1995-08-15', 1, '0123456789', N'Nhân viên phục vụ', 1.0, N'Còn làm'),
('NV008', N'Phạm', N'Thị Miếng', '1991-12-10', 0, '0123456789', N'Nhân viên bán hàng', 1.0, N'Nghỉ làm'),
('NV009', N'Nguyễn', N'Văn Ninh', '1990-06-25', 1, '0987654321', N'Nhân viên pha chế', 1.0, N'Còn làm'),
('NV010', N'Huỳnh', N'Thị Phương', '1994-11-30', 0, '0123456789', N'Nhân viên bán hàng', 1.0, N'Còn làm'),
('NV011', N'Cao', N'Văn Lộc', '1994-11-21', 0, '01234564363', N'Nhân viên bán hàng', 1.0, N'Còn làm'),
('NV012', N'Nguyễn', N'Thị Hồng', '1995-10-21', 0, '01234564363', N'Nhân viên phục vụ', 1.0, N'Còn làm'),
('NV013', N'Cao', N'Văn Lộc', '1995-03-11', 1, '01234564363', N'Nhân viên bán hàng', 1.0, N'Còn làm'),
('NV014', N'Phạm', N'Hữu Phước', '1990-11-11', 2, '01234564363', N'Nhân viên bán hàng', 1.0, N'Còn làm'),
('NV015', N'Trần', N'Hồng Ánh', '1999-01-01', 0, '01234564363', N'Nhân viên pha chế', 1.0, N'Nghỉ làm'),
('NV016', N'Nguyễn', N'Tuyết Mai', '1992-11-01', 0, '01234564363', N'Nhân viên pha chế', 1.0, N'Nghỉ làm'),
('NV017', N'Nguyễn', N'Hồng Phúc', '1999-08-04', 0, '01234564363', N'Nhân viên bán hàng', 1.0, N'Còn làm'),
('NV018', N'Phạm', N'Ngọc Hùng', '1992-04-12', 0, '01234564363', N'Nhân viên bán hàng', 1.0, N'Nghỉ làm'),
('NV019', N'Nguyễn', N'Quỳnh Như', '1999-11-01', 0, '01234564363', N'Nhân viên phục vụ', 1.0, N'Còn làm'),
('NV020', N'Trần', N'Ngọc Hải', '1994-12-04', 0, '01234564363', N'Nhân viên pha chế', 1.0, N'Còn làm');

-- Thêm dữ liệu vào bảng Tài khoản (TaiKhoan)
-- Password là tên tài khoảng thêm @ vào cuối (vd: Khanh006@)
INSERT INTO TaiKhoan (maTK, tenTK, matKhau, quyen, maNV, trangThai)
VALUES 
('TK001', 'Anh001', 'bBRCD/Y6i1wHhGRvOcoc5dwIXtY=', 'Employee', 'NV001', N'Không sử dụng'),
('TK002', 'Bich002', '2fPXr94TJ9stcGrBazCUvFdklfI=', 'Manager', 'NV002', N'Không sử dụng'),
('TK003', 'Phat003', 'LWHs/rilLHwaABg60HF05w0p+V4=', 'Employee', 'NV003', N'Còn sử dụng'),
('TK004', 'Hai004', 'NUQLxiOyMC4YcpH5y3dPrkTMa7k=', 'Employee', 'NV004', N'Không sử dụng'),
('TK005', 'Ngoc005', 'vwd4Ze+0RpOHO+KjJa+xQ8dOn/Y=', 'Manager', 'NV005', N'Còn sử dụng'),
('TK006', 'Khanh006', 'x9yveNs8RyI5nYt6eF6s5RVM8b0=', 'Manager', 'NV006', N'Còn sử dụng'),
('TK007', 'Long007', '271ggVdtnjR+BWNs9ASMic8uXDk=', 'Employee', 'NV007', N'Còn sử dụng'),
('TK008', 'Mieng008', 'WjeIdVE0GiKAzueQd0SndKgMSTY=', 'Employee', 'NV008', N'Không sử dụng'),
('TK009', 'Ninh009', 'TzLkjN+H0e+a1SQq3SkdABvEles=', 'Employee', 'NV009', N'Còn sử dụng'),
('TK010', 'Phuong010', 'TsWQ58qL6KI2oMBGjlJvlJpdya4=', 'Admin', 'NV010', N'Còn sử dụng'),
('TK011', 'Loc011', 'NiJWjydTmzdL+h5XrNiF9nolsS8=', 'Admin', 'NV011', N'Không sử dụng'),
('TK012', 'Hong012', 'BJId3MmKuziMjJJWgkRFcUVbX2c=', 'Employee', 'NV011', N'Còn sử dụng'),
('TK013', 'Loc013', 'fIIpqxmXBHKniV90Sp+EvggxLdk=', 'Employee', 'NV011', N'Còn sử dụng'),
('TK014', 'Phuoc014', 'UMTBrcJK4jLfXkqhqtRHggUyhbQ=', 'Employee', 'NV011', N'Còn sử dụng'),
('TK015', 'Anh015', 'dD84HtjtrWaqdAk6ZjyD6D2cCUw=', 'Manager', 'NV011', N'Còn sử dụng'),
('TK016', 'Mai016', 'xM+qnqEnfbj4hpsj+x+Ca6OJtmA=', 'Admin', 'NV011', N'Còn sử dụng'),
('TK017', 'Phuc017', 'boQg/QeNUuyZrRAezBDg8acMYFw=', 'Employee', 'NV011', N'Còn sử dụng'),
('TK018', 'Hung018', 'NOJm0+cUMHGXPjft3y7Tcqgvl/c=', 'Employee', 'NV011', N'Còn sử dụng'),
('TK019', 'Nhu019', 'zF3yA9tvtO+NVaR4PwMFfgCL1Ss=', 'Manager', 'NV011', N'Không sử dụng'),
('TK020', 'Hai020', '8tNn0cA7nZzGdy8x/G2Thx8ySEU=', 'Employee', 'NV011', N'Không sử dụng');


-- Thêm dữ liệu vào bảng Khách hàng (KhachHang)
INSERT INTO KhachHang (maKH, tenKH, sdt, loaiKH)
VALUES 
('KH001', N'Trần Văn Quang', '0123451234', N'Vàng'),
('KH002', N'Trần bá Sương', '0123456111', N'Bạc'),
('KH003', N'Lê Quang Phúc', '0123455433', N'Bạc'),
('KH004', N'Phạm Quỳnh Hương', '0123451211', N'VIP'),
('KH005', N'Võ Tấn Phát', '0123456333', N'Bạc'),
('KH006', N'Trần Văn Quang', '0123456789', N'Vàng'),
('KH007', N'Lê Thị Riêng', '0987654321', N'VIP'),
('KH008', N'Phạm Văn Sáng', '0123456789', N'Bạc'),
('KH009', N'Nguyễn Thị Tú', '0987654321', N'Vàng'),
('KH010', N'Huỳnh Văn Uyên', '0123456789', N'VIP'),
('KH011', N'Nguyễn Thị Tú', '0987658766', N'Vàng'),
('KH012', N'Nguyễn Thị Ánh Minh', '0987653544', N'Vàng'),
('KH013', N'Nguyễn Thị Quỳnh Như', '0987651299', N'Bạc'),
('KH014', N'Đặng Yến Nhi', '0987658900', N'Bạc'),
('KH015', N'Mai Nhật Ánh', '0987658777', N'Bạc'),
('KH016', N'Nguyễn Thị Duyên', '0987655488', N'Vàng'),
('KH017', N'Nguyễn Hữu Cảnh', '0987654355', N'Vàng'),
('KH018', N'Phạm Văn Tiến', '0987652377', N'Bạc'),
('KH019', N'Nguyễn Thị Mai Liên', '0987658933', N'Bạc'),
('KH020', N'Phạm Trần Hạnh', '0987652077', N'VIP');
-- Thêm dữ liệu vào bảng Giảm giá (GiamGia) 
INSERT INTO GiamGia (maGiamGia, loaiGiamGia, trangThai, ngayHetHan) 
VALUES 
('MG10', N'Bạc', N'Còn sử dụng', '2024-06-04'),
('MG20', N'Vàng', N'Còn sử dụng', '2024-09-14'),
('MG40', N'VIP', N'Còn sử dụng', '2024-09-14');

-- Thêm dữ liệu vào bảng Hóa đơn (HoaDon)
INSERT INTO HoaDon (maHoaDon, ngayLapHoaDon, ghiChu, maGiamGia, maKH, maNV, thue, tongTien, phuongThucThanhToan, trangThaiThanhToan, hinhThuc)
VALUES 
('HD006', '2024-05-01 10:00:00', N'', 'MG20', 'KH006', 'NV006',10,150000,N'Ngân Hàng',N'Đã Thanh Toán',N'Mang Về'),
('HD007', '2023-04-11 12:00:00', N'', NULL, 'KH007', 'NV007',10,250000,N'Momo',N'Đã Thanh Toán',N'Tại Quán'),
('HD008', '2022-01-04 14:00:00', N'', NULL, 'KH008', 'NV008',10,350000,N'Momo',N'Đã Thanh Toán',N'Tại Quán'),
('HD009', '2022-05-03 12:00:00', N'', NULL, 'KH006', 'NV009',10,350000,N'Ngân Hàng',N'Đã Thanh Toán',N'Mang Về'),
('HD010', '2021-07-07 09:00:00', N'', NULL, 'KH008', 'NV010',10,230000,N'Thanh Toán Khác',N'Đã Thanh Toán',N'Tại Quán');

-- Thêm dữ liệu vào bảng Sản phẩm (SanPham)
INSERT INTO SanPham (maSanPham, tenSanPham, loaiSanPham, donGia, moTa, trangThai, hinhanh)
VALUES 
('SP006', N'Cà phê sữa nóng', N'Đồ uống', 30000, N'Cà phê sữa nóng không đá', N'Còn bán','Image/anh1.jpg'),
('SP007', N'Bánh mì chảo', N'Đồ ăn nhẹ', 25000, N'Bánh mì chảo nướng giòn', N'Còn bán','Image/anh2.png'),
('SP008', N'Nước cam ép', N'Đồ uống', 20000, N'Nước cam tươi', 'Còn bán','Image/anh3.jpg'),
('SP009', N'Trà bí đao', N'Đồ uống', 35000, N'Trà bí đao có đường', N'Còn bán','Image/anh4.jpg'),
('SP010', N'Bánh mì pate', N'Đồ ăn nhẹ', 18000, N'Bánh mì pate ăn kèm rau sống', N'Còn bán','Image/anh5.jpg'),
('SP011', N'Bánh mì chảo', N'Đồ ăn nhẹ', 25000, N'Bánh mì chảo nướng giòn', N'Còn bán','Image/anh6.jpg'),
('SP012', N'Sinh tố xoài', N'Đồ uống', 20000, N'Xoài chính', N'Nghỉ bán','Image/anh7.jpg'),
('SP013', N'Cà phê muối', N'Đồ uống', 23000, N'Cà Phê ,uối', N'Còn bán','Image/anh8.jpg'),
('SP014', N'Ca cao nóng', N'Đồ uống', 25000, N'Ca cao', N'Còn bán','Image/anh9.jpg'),
('SP015', N'Nước ép dứa', N'Đồ uống', 22000, N'Nước ép dứa nguyên chất', 'Còn bán','Image/anh9.jpg'),
('SP016', N'Bánh kem', N'Đồ ăn nhẹ', 50000, N'Bánh kem lạnh', N'Còn bán','Image/anh9.jpg'),
('SP017', N'Sinh tố bơ', N'Đồ uống', 25000, N'Sinh tố bơ', N'Còn bán','Image/anh9.jpg'),
('SP018', N'Sinh tố nho', N'Đồ uống', 27000, N'Sinh tố nho', N'Còn bán','Image/anh9.jpg'),
('SP019', N'Combo1', N'combo', 55000, N'Bánh mỳ, bánh kem', N'Còn bán','Image/anh9.jpg');

INSERT INTO SanPham (maSanPham, tenSanPham, loaiSanPham, donGia, moTa, trangThai, hinhanh)
VALUES
('SP020', N'Ca cao nóng', N'Đồ ăn nhẹ', 25000, N'Ca cao', N'Còn bán','Image/anh7.jpg'),
('SP021', N'Ca cao nóng', N'Đồ ăn nhẹ', 25000, N'Ca cao', N'Còn bán','Image/anh9.jpg'),
('SP022', N'Ca cao nóng', N'Combo', 25000, N'Ca cao', N'Còn bán','Image/anh4.jpg'),
('SP023', N'Ca cao nóng', N'Combo', 25000, N'Ca cao', N'Còn bán','Image/anh1.jpg'),
('SP024', N'Ca cao nóng', N'Combo', 25000, N'Ca cao', N'Còn bán','Image/anh3.jpg'),
('SP025', N'Ca cao nóng', N'Combo', 25000, N'Ca cao', N'Còn bán','Image/anh5.jpg');

-- Thêm dữ liệu vào bảng Nguyên liệu (NguyenLieu)
INSERT INTO NguyenLieu (maNguyenLieu, tenNguyenLieu, giaThuMua, donVi, soLuong, moTa, loaiNguyenLieu, ngayNhapKho, hanSuDung, maSanPham, trangThai)
VALUES 
('NL006', N'Bột sữa', 30000, N'Kg', 30, N'Bột sữa nguyên chất', N'Sữa', GETDATE(), DATEADD(month, 3, GETDATE()), 'SP006', N'Còn sử dụng'),
('NL007', N'Bí đao', 15000, N'Quả', 20, N'Quả bí đao', N'Quả', '2024-04-015', DATEADD(month, 1, GETDATE()), 'SP009', N'Còn sử dụng'),
('NL008', N'Pate', 25000, N'Kg', 25, N'Pate thịt gà', N'Thịt', GETDATE(), DATEADD(month, 2, GETDATE()), 'SP010', N'Còn sử dụng'),
('NL009', N'Bánh mì', 5000, N'Chiếc', 100, N'Bánh mì tươi', N'Đồ ăn', '2024-04-02', DATEADD(week, 1, GETDATE()), 'SP007', N'Còn sử dụng'),
('NL010', N'Cam', 8000, N'Quả', 30, N'Quả cam', N'Quả', GETDATE(), DATEADD(month, 1, GETDATE()), 'SP008', N'Còn sử dụng'),
('NL011', N'Xoài', 15000, N'Quả', 20, N'Quả xoài', N'Quả', '2024-04-015', DATEADD(month, 1, GETDATE()), 'SP011', N'Không sử dụng');

-- Thêm dữ liệu vào bảng Chi tiết hóa đơn (ChiTietHD), không có khóa chính
INSERT INTO ChiTietHoaDon (maHoaDon, maSanPham, soLuong, donGia, thanhTien)
VALUES 
('HD006', 'SP006', 2, 30000, 60000),
('HD006', 'SP007', 3, 25000, 75000),
('HD006', 'SP008', 1, 20000, 20000),
('HD007', 'SP006', 2, 30000, 60000),
('HD007', 'SP007', 1, 25000, 25000);

-- Thêm dữ liệu vào bảng Nhà cung cấp (NhaCungCap)
INSERT INTO NhaCungCap (maNhaCC, tenNhaCC, email, soDienThoai, diaChi, trangThai)
VALUES 
('NCC005', N'Công ty bột sữa Good', 'good@gmail.com', '0123456789', N'789 Đường 3 tháng 2, Quận 10', 'Còn cung câp'),
('NCC006', N'Công ty trái cây Yuki', 'yuki@gmail.com', '0987654321', N'Đường 12, Quận Tân Bình', 'Còn cung câp'),
('NCC007', N'Công ty thịt tươi Wonder', 'who@gmail.com', '0987655621', N'45/12A Nguyễn Gia Bảo, Quận Gò Vấp', 'Còn cung câp'),
('NCC008', N'Công ty bánh mì Look', 'look@gmail.com', '0987651529', N'231 Nguyễn Thái Sơn, Quận Gò Vấp', 'Còn cung câp');

-- Thêm dữ liệu vào bảng Cung cấp (CungCap)
INSERT INTO CungCap (maNguyenLieu, maNhaCC, ngayCungCap, soLuong, donGia)
VALUES 
('NL006', 'NCC005', GETDATE(), 10, 25000),
('NL007', 'NCC006', '2024-04-02', 15, 12000),
('NL008', 'NCC007', '2024-04-12', 3, 50000),
('NL009', 'NCC008', GETDATE(), 25, 7000),
('NL010', 'NCC006', GETDATE(), 10, 15000);
