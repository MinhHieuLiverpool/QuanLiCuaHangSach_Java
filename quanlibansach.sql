-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 18, 2024 lúc 09:20 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlibansach`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietctgg`
--

CREATE TABLE `chitietctgg` (
  `maChiTietCTGG` varchar(50) NOT NULL,
  `maCTGG` varchar(50) DEFAULT NULL,
  `phanTramGiamGia` int(11) DEFAULT NULL,
  `maSach` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietctgg`
--

INSERT INTO `chitietctgg` (`maChiTietCTGG`, `maCTGG`, `phanTramGiamGia`, `maSach`) VALUES
('1', '1', 50, '0001'),
('2', '1', 90, '0002'),
('3', '2', 90, '1'),
('4', '1', 90, '1'),
('5', '3', 50, '0004'),
('6', '6', 40, '0010');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `maHoaDon` int(11) DEFAULT NULL,
  `maSach` varchar(50) DEFAULT NULL,
  `tenSach` varchar(255) DEFAULT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `donGia` double DEFAULT NULL,
  `giamGia` double DEFAULT NULL,
  `thanhTien` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`maHoaDon`, `maSach`, `tenSach`, `soLuong`, `donGia`, `giamGia`, `thanhTien`) VALUES
(7, '0001', '5 Phương trình làm thay đổi thế giới', 1, 35000, 17500, 17500),
(15, '0014', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 5', 10, 35000, 0, 350000),
(15, '0015', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 1', 10, 35000, 0, 350000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `maPhieuNhap` varchar(50) DEFAULT NULL,
  `maSach` varchar(50) DEFAULT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `donGia` double DEFAULT NULL,
  `tongTienNhap` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`maPhieuNhap`, `maSach`, `soLuong`, `donGia`, `tongTienNhap`) VALUES
('1', '0001', 45, 35000, 1575000),
('1', '0001', 34, 35000, 1190000),
('1', '0001', 40, 35000, 1400000),
('2', '0001', 30, 35000, 70000),
('1', '0002', 4, 45000, 90000),
('1', '0004', 12, 55000, 660000),
('2', '0006', 10, 35000, 350000),
('5', '0008', 21, 75000, 1575000),
('4', '0012', 1, 35000, 35000),
('6', '0008', 30, 75000, 2250000),
('1', '0014', 20, 35000, 700000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chucvu`
--

CREATE TABLE `chucvu` (
  `maChucVu` varchar(50) NOT NULL,
  `tenChucVu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chucvu`
--

INSERT INTO `chucvu` (`maChucVu`, `tenChucVu`) VALUES
('1', 'Kho1'),
('11', 'Kho'),
('12', 'Bán Hàng'),
('13', 'Giám Đốc'),
('14', 'Vận Chuyển'),
('15', 'Tiếp thị'),
('2', 'Tiếp thị 1'),
('3', 'Tiếp thị 2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctgg`
--

CREATE TABLE `ctgg` (
  `maCTGG` varchar(50) NOT NULL,
  `tenCTGG` varchar(255) DEFAULT NULL,
  `ngayBatDau` date DEFAULT NULL,
  `ngayKetThuc` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ctgg`
--

INSERT INTO `ctgg` (`maCTGG`, `tenCTGG`, `ngayBatDau`, `ngayKetThuc`) VALUES
('1', 'Ngày Chấm 2 Đồ Án', '2024-05-13', '2024-05-31'),
('2', 'Sắp Dề Quê nè', '2024-05-13', '2024-05-31'),
('3', 'Code lại java thành công', '2024-05-01', '2024-05-31'),
('4', '2 đồ án full điểm', '2024-05-13', '2024-05-31'),
('5', 'Thức trắng đêm đói bụng quá', '2024-05-11', '2024-05-31'),
('6', 'Cố Gắng Lên', '2024-05-06', '2024-05-31');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `maHoaDon` int(11) NOT NULL DEFAULT 0,
  `maKhachHang` varchar(50) DEFAULT NULL,
  `maNhanVien` varchar(50) DEFAULT NULL,
  `mactgg` varchar(50) DEFAULT NULL,
  `ngayLap` date DEFAULT NULL,
  `tongTien` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`maHoaDon`, `maKhachHang`, `maNhanVien`, `mactgg`, `ngayLap`, `tongTien`) VALUES
(1, '2', '12', '1', '2024-05-16', 0),
(2, '2', '12', '1', '2024-05-16', 0),
(3, '2', '12', '1', '2024-05-16', 0),
(4, 'KH00001', '12', '1', '2024-05-16', 0),
(5, '2', '12', '1', '2024-05-16', 0),
(6, '2', '12', '1', '2024-05-16', 0),
(7, '2', 'NV001', '1', '2024-05-16', 0),
(8, '2', '12', '1', '2024-05-16', 0),
(9, '2', '12', '1', '2024-05-16', 0),
(10, '2', '12', '1', '2024-05-16', 0),
(11, '2', '12', '1', '2024-05-16', 0),
(12, '2', '12', '1', '2024-05-16', 0),
(13, '2', '12', '1', '2024-05-16', 0),
(14, '2', '12', '1', '2024-05-17', 0),
(15, '2', '12', '1', '2024-05-17', 700000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `maKhachHang` varchar(50) NOT NULL,
  `hoKhachHang` varchar(255) DEFAULT NULL,
  `tenKhachHang` varchar(255) DEFAULT NULL,
  `soDienThoai` varchar(20) DEFAULT NULL,
  `ngaySinh` date DEFAULT NULL,
  `gioiTinh` varchar(10) DEFAULT NULL,
  `tongChi` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`maKhachHang`, `hoKhachHang`, `tenKhachHang`, `soDienThoai`, `ngaySinh`, `gioiTinh`, `tongChi`) VALUES
('2', 'Bùi', 'Hiếu', '0123456789', '2024-04-18', 'Nữ', 10000),
('KH00001', 'Nguyen', 'Van A', '0987654321', '1990-01-01', 'Nam', 10000),
('KH00002', 'Tran', 'Thi B', '0912345678', '1985-05-15', 'Nữ', 20000),
('KH00004', 'Pham', 'Huong DD', '0987432109', '1988-12-25', 'Nữ', 40000),
('KH00005', 'Hoang', 'Thien E', '0912345609', '1992-07-04', 'Nam', 50000),
('KH00006', 'ffff', 'Linh FF', '0987654309', '1989-11-11', 'Nữ', 60000),
('KH00007', 'Ngo', 'Minh G', '0912345670', '1991-02-19', 'Nam', 70000),
('KH00008', 'Vu', 'Hai H', '0987654320', '1993-06-06', 'Nữ', 80000),
('KH00009', 'Do', 'Quyen I', '0912345678', '1994-09-27', 'Nam', 90000),
('KH00010', 'Phan', 'Thu J', '0987654312', '1996-12-31', 'Nữ', 100000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `maNhaCungCap` varchar(50) NOT NULL,
  `ho` varchar(50) DEFAULT NULL,
  `ten` varchar(50) DEFAULT NULL,
  `soDienThoai` varchar(50) DEFAULT NULL,
  `diachi` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`maNhaCungCap`, `ho`, `ten`, `soDienThoai`, `diachi`) VALUES
('1', '1', '1', '1', '1'),
('2', '2', '2', '2', '2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `maNhanVien` varchar(50) NOT NULL,
  `Ho` varchar(255) DEFAULT NULL,
  `Ten` varchar(255) DEFAULT NULL,
  `soDienThoai` varchar(15) DEFAULT NULL,
  `ngaySinh` date DEFAULT NULL,
  `gioiTinh` varchar(20) DEFAULT NULL,
  `maChucVu` varchar(20) DEFAULT NULL,
  `Luong` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`maNhanVien`, `Ho`, `Ten`, `soDienThoai`, `ngaySinh`, `gioiTinh`, `maChucVu`, `Luong`) VALUES
('12', '12', '12', '0987654321', '2024-04-19', 'Nữ', '2', 11),
('NV001', 'Van A', 'Nguyen', '0987654321', '1990-05-10', 'Nam', '15', 1000),
('NV002', 'Tran', 'Thi B', '0901234567', '1985-12-15', 'Nữ', '2', 12000),
('NV003', 'Le', 'Hoang C', '0909876543', '1988-08-20', 'Nam', '1', 111),
('NV004', 'Pham', 'Huong D', '0987432109', '1992-03-25', 'Nu', '3', 15000),
('NV006', 'Bui', 'Linh F', '0987654309', '1991-06-30', 'Nu', '2', 13333),
('NV007', 'Ngo', 'Minh G', '0912345670', '1989-04-18', 'Nam', '1', 1550),
('NV008', 'Vu', 'Hai H', '0987654320', '1986-09-12', 'Nam', '1', 1250),
('NV009', 'Do', 'Quyen I', '0912345678', '1993-02-28', 'Nu', '2', 11500),
('NV010', 'Phan', 'Thu J', '0987654312', '1990-11-08', 'Nu', '2', 16000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhaxuatban`
--

CREATE TABLE `nhaxuatban` (
  `maNXB` varchar(50) NOT NULL,
  `tenNXB` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhaxuatban`
--

INSERT INTO `nhaxuatban` (`maNXB`, `tenNXB`) VALUES
('NXB001', 'NXB Trẻ'),
('NXB002', 'NXB Chính Trị Quốc Gia Sự Thật'),
('NXB003', 'NXB Văn Học'),
('NXB004', 'NXB Kim Đồng'),
('NXB005', 'NXB Giáo Dục Việt Nam'),
('NXB006', 'NXB Dân Trí'),
('NXB1', 'hihi');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maPhieuNhap` varchar(50) NOT NULL,
  `maNhaCungCap` varchar(50) NOT NULL,
  `maNhanVien` varchar(50) NOT NULL,
  `ngayLap` date NOT NULL,
  `tongTien` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`maPhieuNhap`, `maNhaCungCap`, `maNhanVien`, `ngayLap`, `tongTien`) VALUES
('1', '1', 'NV002', '2024-05-14', 5615000),
('2', '1', '12', '2024-05-13', 420000),
('3', '1', '12', '2024-05-11', 140000),
('4', '1', '12', '2024-05-14', 35000),
('45', '1', '12', '2024-05-16', 0),
('5', '1', '12', '2024-05-09', 1575000),
('6', '2', '12', '2024-05-12', 2250000),
('7', '2', 'NV007', '2024-05-16', 0),
('8', '2', 'NV010', '2024-05-16', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sach`
--

CREATE TABLE `sach` (
  `maSach` varchar(50) NOT NULL,
  `maNXB` varchar(50) DEFAULT NULL,
  `maTheLoai` varchar(50) DEFAULT NULL,
  `maTacGia` varchar(50) DEFAULT NULL,
  `tenSach` varchar(255) DEFAULT NULL,
  `namXuatBan` int(11) DEFAULT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `donGia` double DEFAULT NULL,
  `hinhAnh` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sach`
--

INSERT INTO `sach` (`maSach`, `maNXB`, `maTheLoai`, `maTacGia`, `tenSach`, `namXuatBan`, `soLuong`, `donGia`, `hinhAnh`) VALUES
('0001', 'NXB001', 'TL001', 'TG001', '5 Phương trình làm thay đổi thế giới', 2012, 100, 35000, '5phuongtrinhlamthaydoithegioi.jpg'),
('0002', 'NXB002', 'TL002', 'TG002', 'Bác Hồ với trí thức nam bộ', 2015, 100, 45000, 'BacHovoitrithucNamBo.jpg'),
('0004', 'NXB001', 'TL003', 'TG004', 'Cẩm nang Công Nghệ Hóa Học', 2016, 86, 55000, 'camnangcongnghehoahoc.jpg'),
('0006', 'NXB003', 'TL005', 'TG006', 'Chí Phèo', 2010, 25, 35000, 'chipheo.jpg'),
('0007', 'NXB001', 'TL004', 'TG005', 'Cho tôi xin một vé đi tuổi thơ', 2019, 7, 35000, 'chotoixinmotvedituoitho.jpg'),
('0008', 'NXB001', 'TL006', 'TG007', 'Đặc nhân tâm', 2014, 152, 75000, 'dactamnhan.jpg'),
('0009', 'NXB004', 'TL007', 'TG008', 'Dế Mèn Phiêu Lưu Ký', 2009, 40, 25000, 'demenphieulieuki.jpg'),
('0010', 'NXB003', 'TL005', 'TG006', 'Đời Thừa', 2010, 57, 45000, 'doithua.jpg'),
('0011', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 2', 2001, 96, 35000, 'dora_2.jpg'),
('0012', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 3', 2001, 108, 35000, 'dora_3.jpg'),
('0013', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 4', 2001, 88, 35000, 'dora_4.jpg'),
('0014', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 5', 2001, 110, 35000, 'dora_5.jpg'),
('0015', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 1', 2001, 90, 35000, 'dora1.jpg'),
('0016', 'NXB004', 'TL006', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 7', 2001, 97, 35000, 'dora-6.jpg'),
('0017', 'NXB005', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 11', 2001, 100, 35000, 'dora-7.jpg'),
('0018', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 12', 2001, 100, 35000, 'dora-8.jpg'),
('0019', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 13', 2001, 111, 35000, 'dora9.jpg'),
('0020', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 14', 2001, 100, 35000, 'dora10.jpg'),
('0021', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 15', 2001, 100, 35000, 'dora11.jpg'),
('0022', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 16', 2001, 101, 35000, 'dora12.jpg'),
('0023', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 17', 2001, 100, 35000, 'dora13.jpg'),
('0024', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 18', 2001, 100, 35000, 'dora14.jpg'),
('0025', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 19', 2001, 100, 35000, 'dora15.jpg'),
('0026', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 22', 2001, 100, 35000, 'dora16.jpg'),
('0027', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 23', 2001, 100, 35000, 'dora17.jpg'),
('0028', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 25', 2001, 100, 35000, 'dora18.jpg'),
('0029', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 28', 2001, 100, 35000, 'dora19.jpg'),
('0030', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 29', 2001, 100, 35000, 'dora20.jpg'),
('0031', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 30', 2001, 100, 35000, 'dora21.jpg'),
('0032', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 34', 2001, 100, 35000, 'dora22.jpg'),
('0033', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 36', 2001, 100, 35000, 'dora23.jpg'),
('0034', 'NXB004', 'TL007', 'TG009', 'Doraemon Chú Mèo Máy đến từ tương lai Tập 44', 2001, 100, 35000, 'dora24.jpg'),
('0035', 'NXB002', 'TL002', 'TG010', 'Đường Cách Mệnh', 1999, 60, 85000, 'duongcachmenh.jpg'),
('0036', 'NXB005', 'TL008', 'TG011', 'Giải Phẫu Sinh Lý Người', 2013, 25, 105000, 'giaiphausinhlynguoi.jpg'),
('0037', 'NXB001', 'TL004', 'TG005', 'Hoa hồng sứ khác', 2017, 55, 65000, 'hoahongxukhac.jpg'),
('0038', 'NXB006', 'TL002', 'TG012', 'Hoàng Lê Nhất Thống Chí', 2007, 55, 60000, 'hoanglenhatthongchi.jpg'),
('0039', 'NXB001', 'TL004', 'TG005', 'Làm bạn với bầu trời', 2014, 105, 95000, 'lambanvoibautroi.jpg'),
('0040', 'NXB003', 'TL009', 'TG013', 'Làm Đĩ', 2012, 40, 75000, 'lamdi.jpg'),
('1', 'NXB1', 'TheLoai1', 'TacGia1', '1', 1, 9, 1, 'lao-hac.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tacgia`
--

CREATE TABLE `tacgia` (
  `MaTacGia` varchar(50) NOT NULL,
  `TenTacGia` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tacgia`
--

INSERT INTO `tacgia` (`MaTacGia`, `TenTacGia`) VALUES
('TacGia1', 'Minh Hiếu'),
('TG001', 'Michael Guillen'),
('TG002', 'Ban Tuyên Giáo Thành Ủy TPHCM'),
('TG003', 'TS.Nguyễn Việt Lâm'),
('TG004', 'Walter Biewert'),
('TG005', 'Nguyễn Nhật Ánh'),
('TG006', 'Nam Cao'),
('TG007', 'Dale Carnegie'),
('TG008', 'Tô Hoài'),
('TG009', 'Fujiko F Fujio'),
('TG010', 'Nguyến Ái Quốc'),
('TG011', 'Th.S Nguyễn Thị Hiền - TS. Nguyễn Xuân Trường'),
('TG012', 'Ngô Gia Văn Phái'),
('TG013', 'Vũ Trọng Phụng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `theloai`
--

CREATE TABLE `theloai` (
  `maTheLoai` varchar(50) NOT NULL,
  `tenTheLoai` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `theloai`
--

INSERT INTO `theloai` (`maTheLoai`, `tenTheLoai`) VALUES
('TheLoai1', 'Trinh Thám'),
('TL001', 'Toán Học'),
('TL002', 'Lịch Sử'),
('TL003', 'Khoa Học'),
('TL004', 'Hồi Ký'),
('TL005', 'Văn Học'),
('TL006', 'Tâm Lý Học'),
('TL007', 'Thiếu Nhi'),
('TL008', 'Giải Phẩu'),
('TL009', 'Tiểu Thuyết');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietctgg`
--
ALTER TABLE `chitietctgg`
  ADD PRIMARY KEY (`maChiTietCTGG`),
  ADD KEY `fk_maCTGG` (`maCTGG`),
  ADD KEY `FK_chitietctgg_sach` (`maSach`);

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD KEY `fk_maSach` (`maSach`),
  ADD KEY `FK_chitiethoadon_hoadon` (`maHoaDon`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD KEY `FK_chitietphieunhap_phieunhap` (`maPhieuNhap`),
  ADD KEY `FK_chitietphieunhap_sach` (`maSach`);

--
-- Chỉ mục cho bảng `chucvu`
--
ALTER TABLE `chucvu`
  ADD PRIMARY KEY (`maChucVu`);

--
-- Chỉ mục cho bảng `ctgg`
--
ALTER TABLE `ctgg`
  ADD PRIMARY KEY (`maCTGG`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`maHoaDon`),
  ADD KEY `fk_KhachHang` (`maKhachHang`),
  ADD KEY `fk_NhanVien` (`maNhanVien`),
  ADD KEY `fk_CTGG` (`mactgg`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`maKhachHang`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`maNhaCungCap`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`maNhanVien`),
  ADD KEY `fk_ChucVu111` (`maChucVu`);

--
-- Chỉ mục cho bảng `nhaxuatban`
--
ALTER TABLE `nhaxuatban`
  ADD PRIMARY KEY (`maNXB`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maPhieuNhap`),
  ADD KEY `FK_phieunhap_nhanvien` (`maNhanVien`),
  ADD KEY `FK_phieunhap_nhacungcap` (`maNhaCungCap`);

--
-- Chỉ mục cho bảng `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`maSach`),
  ADD KEY `FK_sach_tacgia` (`maTacGia`),
  ADD KEY `FK_sach_nhaxuatban` (`maNXB`),
  ADD KEY `FK_sach_theloai` (`maTheLoai`);

--
-- Chỉ mục cho bảng `tacgia`
--
ALTER TABLE `tacgia`
  ADD PRIMARY KEY (`MaTacGia`);

--
-- Chỉ mục cho bảng `theloai`
--
ALTER TABLE `theloai`
  ADD PRIMARY KEY (`maTheLoai`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietctgg`
--
ALTER TABLE `chitietctgg`
  ADD CONSTRAINT `FK_chitietctgg_sach` FOREIGN KEY (`maSach`) REFERENCES `sach` (`maSach`),
  ADD CONSTRAINT `fk_maCTGG` FOREIGN KEY (`maCTGG`) REFERENCES `ctgg` (`maCTGG`);

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `FK_chitiethoadon_hoadon` FOREIGN KEY (`maHoaDon`) REFERENCES `hoadon` (`maHoaDon`),
  ADD CONSTRAINT `fk_maSach` FOREIGN KEY (`maSach`) REFERENCES `sach` (`maSach`);

--
-- Các ràng buộc cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `FK_chitietphieunhap_phieunhap` FOREIGN KEY (`maPhieuNhap`) REFERENCES `phieunhap` (`maPhieuNhap`),
  ADD CONSTRAINT `FK_chitietphieunhap_sach` FOREIGN KEY (`maSach`) REFERENCES `sach` (`maSach`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `fk_CTGG` FOREIGN KEY (`mactgg`) REFERENCES `ctgg` (`maCTGG`),
  ADD CONSTRAINT `fk_KhachHang` FOREIGN KEY (`maKhachHang`) REFERENCES `khachhang` (`maKhachHang`),
  ADD CONSTRAINT `fk_NhanVien` FOREIGN KEY (`maNhanVien`) REFERENCES `nhanvien` (`maNhanVien`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `fk_ChucVu111` FOREIGN KEY (`maChucVu`) REFERENCES `chucvu` (`maChucVu`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_phieunhap_nhacungcap` FOREIGN KEY (`maNhaCungCap`) REFERENCES `nhacungcap` (`maNhaCungCap`),
  ADD CONSTRAINT `FK_phieunhap_nhanvien` FOREIGN KEY (`maNhanVien`) REFERENCES `nhanvien` (`maNhanVien`);

--
-- Các ràng buộc cho bảng `sach`
--
ALTER TABLE `sach`
  ADD CONSTRAINT `FK_sach_nhaxuatban` FOREIGN KEY (`maNXB`) REFERENCES `nhaxuatban` (`maNXB`),
  ADD CONSTRAINT `FK_sach_tacgia` FOREIGN KEY (`maTacGia`) REFERENCES `tacgia` (`MaTacGia`),
  ADD CONSTRAINT `FK_sach_theloai` FOREIGN KEY (`maTheLoai`) REFERENCES `theloai` (`maTheLoai`) ON DELETE NO ACTION,
  ADD CONSTRAINT `fk_NXB` FOREIGN KEY (`maNXB`) REFERENCES `nhaxuatban` (`maNXB`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
