-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 30, 2026 lúc 01:50 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `sneakerdb`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` datetime(6) DEFAULT NULL,
  `citizen_id` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) NOT NULL,
  `gender` bit(1) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` enum('CUSTOMER','ADMIN','STAFF') NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `updated_at` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `accounts`
--

INSERT INTO `accounts` (`id`, `avatar`, `birthday`, `citizen_id`, `code`, `created_at`, `email`, `full_name`, `gender`, `password`, `phone_number`, `role`, `status`, `updated_at`) VALUES
(1, NULL, '1990-01-15 00:00:00.000000', '001234567890', 'ACC001', '2025-12-11 00:14:59.000000', 'nguyenvananh@gmail.com', 'Nguyễn Hoàng Thiên Bảo', b'1', '$2a$10$l1NcsDjlCaBIsVX4SNg1FeKfUvqWKejOkX3JGYLIYKfF1Li80GZDW', '0901234567', 'ADMIN', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(2, NULL, '1995-05-20 00:00:00.000000', '001234567891', 'ACC002', '2025-12-11 00:14:59.000000', 'tranthibinh@gmail.com', 'Trần Phan Anh Thư', b'0', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '0901234568', 'CUSTOMER', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(3, NULL, '1992-08-10 00:00:00.000000', '001234567892', 'ACC003', '2025-12-11 00:14:59.000000', 'levancuong@gmail.com', 'Lê Nguyễn Minh Khôi', b'1', '$2a$10$rx51w6Fzu07iiMqEif2K3u5rzhO05TvaOusM31.Iq6.pajDkgDd7i', '0901234569', 'STAFF', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(4, NULL, '1993-03-12 00:00:00.000000', '001111111111', 'ACC004', '2025-12-11 00:14:59.000000', 'phamquocdung@gmail.com', 'Phạm Gia Tuấn Kiệt', b'1', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '0901111111', 'CUSTOMER', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(5, NULL, '1994-07-25 00:00:00.000000', '002222222222', 'ACC005', '2025-12-11 00:14:59.000000', 'hoangthigiang@gmail.com', 'Hoàng Nguyễn Diệu Anh', b'0', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '0902222222', 'CUSTOMER', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(6, NULL, '1991-11-08 00:00:00.000000', '003333333333', 'ACC006', '2025-12-11 00:14:59.000000', 'vuvanhung@gmail.com', 'Vũ Hoàng Phương Nam', b'1', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '0903333333', 'CUSTOMER', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(7, NULL, '1996-02-14 00:00:00.000000', '004444444444', 'ACC007', '2025-12-11 00:14:59.000000', 'dangthilinh@gmail.com', 'Đặng Lê Minh Ngọc', b'0', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '0904444444', 'CUSTOMER', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(8, NULL, '1990-09-30 00:00:00.000000', '005555555555', 'ACC008', '2025-12-11 00:14:59.000000', 'buivanminh@gmail.com', 'Bùi Phan Khánh An', b'1', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '0905555555', 'CUSTOMER', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(9, NULL, '1997-04-18 00:00:00.000000', '006666666666', 'ACC009', '2025-12-11 00:14:59.000000', 'ngothingoc@gmail.com', 'Ngô Trần Tuấn Minh', b'0', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '0906666666', 'CUSTOMER', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(10, NULL, '1992-12-05 00:00:00.000000', '007777777777', 'ACC010', '2025-12-11 00:14:59.000000', 'dovanphong@gmail.com', 'Đỗ Hoàng Gia Hân', b'1', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '0907777777', 'CUSTOMER', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(11, NULL, '1995-06-22 00:00:00.000000', '008888888888', 'ACC011', '2025-12-11 00:14:59.000000', 'hathiquynh@gmail.com', 'Hà Phan Thảo Nguyên', b'0', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '0908888888', 'CUSTOMER', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(12, NULL, '1993-10-15 00:00:00.000000', '009999999999', 'ACC012', '2025-12-11 00:14:59.000000', 'lyvanson@gmail.com', 'Lý Nguyễn Trường Giang', b'1', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '0909999999', 'CUSTOMER', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(13, NULL, '1994-01-28 00:00:00.000000', '010000000000', 'ACC013', '2025-12-11 00:14:59.000000', 'maithithao@gmail.com', 'Mai Trần Thanh Trúc', b'0', '$2a$10$MN9dZLTE8q3ZkpA38yVhOed.EG7yyo9G9TMHsPtw/glCQeLZ9kp8K', '0910000000', 'CUSTOMER', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(14, NULL, '1991-05-10 00:00:00.000000', '011111111111', 'ACC014', '2025-12-11 00:14:59.000000', 'trinhvantung@gmail.com', 'Trịnh Hoàng Nhật Minh', b'1', '$2a$10$rx51w6Fzu07iiMqEif2K3u5rzhO05TvaOusM31.Iq6.pajDkgDd7i', '0911111111', 'STAFF', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(15, NULL, '1988-03-20 00:00:00.000000', '012222222222', 'ACC015', '2025-12-11 00:14:59.000000', 'phanvankhanh@gmail.com', 'Phan Lê Kim Ngân', b'1', '$2a$10$l1NcsDjlCaBIsVX4SNg1FeKfUvqWKejOkX3JGYLIYKfF1Li80GZDW', '0912222222', 'ADMIN', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(16, NULL, NULL, NULL, 'CUS001626', '2026-01-01 02:30:28.000000', 'buitranthienan2222@gmail.com', 'Nguyễn Trần Bảo Ngọc', NULL, '$2a$10$rLAlc5W.NWdaeh.0I.IOR.e2qAdsPO45amxcgv5A5Pv7ye92Fp2QW', '0336735283', 'CUSTOMER', 'ACTIVE', '2026-01-01 02:30:28.000000'),
(17, NULL, NULL, NULL, 'CUS001726', '2026-01-01 02:53:45.000000', 'buitranthienan11212@gmail.com', 'Trần Đặng Minh Quân', NULL, '$2a$10$ua1HMZcvl8B.LLhWloFlj.3ddEmGjWCs/B6s4qLNQ0DQCRuIVyeeK', '0902000292', 'CUSTOMER', 'ACTIVE', '2026-01-01 02:53:45.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account_addresses`
--

CREATE TABLE `account_addresses` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `district_id` varchar(255) NOT NULL,
  `is_default` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `province_id` varchar(255) NOT NULL,
  `specific_address` varchar(255) NOT NULL,
  `type` bit(1) DEFAULT NULL,
  `updated_at` datetime(6) NOT NULL,
  `ward_id` varchar(255) NOT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account_addresses`
--

INSERT INTO `account_addresses` (`id`, `created_at`, `district_id`, `is_default`, `name`, `phone_number`, `province_id`, `specific_address`, `type`, `updated_at`, `ward_id`, `account_id`) VALUES
(1, '2025-12-11 00:15:00.000000', '760', b'1', 'Trần Phan Anh Thư', '0901234568', '79', '123 Đường ABC, Phường XYZ', b'0', '2025-12-11 00:15:00.000000', '26734', 2),
(2, '2025-12-11 00:15:00.000000', '760', b'0', 'Trần Phan Anh Thư', '0901234568', '79', '456 Đường DEF, Phường UVW', b'0', '2025-12-11 00:15:00.000000', '26735', 2),
(3, '2025-12-11 00:15:00.000000', '760', b'1', 'Phạm Gia Tuấn Kiệt', '0901111111', '79', '789 Đường GHI, Phường RST', b'0', '2025-12-11 00:15:00.000000', '26734', 4),
(4, '2025-12-11 00:15:00.000000', '001', b'1', 'Hoàng Nguyễn Diệu Anh', '0902222222', '01', '321 Đường JKL, Phường MNO', b'0', '2025-12-11 00:15:00.000000', '00001', 5),
(5, '2025-12-11 00:15:00.000000', '490', b'1', 'Vũ Hoàng Phương Nam', '0903333333', '48', '654 Đường PQR, Phường STU', b'0', '2025-12-11 00:15:00.000000', '20215', 6),
(6, '2025-12-11 00:15:00.000000', '760', b'1', 'Đặng Lê Minh Ngọc', '0904444444', '79', '987 Đường VWX, Phường YZA', b'0', '2025-12-11 00:15:00.000000', '26734', 7),
(7, '2025-12-11 00:15:00.000000', '213', b'1', 'Bùi Phan Khánh An', '0905555555', '24', '147 Đường BCD, Phường EFG', b'0', '2025-12-11 00:15:00.000000', '07198', 8),
(8, '2025-12-11 00:15:00.000000', '760', b'1', 'Ngô Trần Tuấn Minh', '0906666666', '79', '258 Đường HIJ, Phường KLM', b'0', '2025-12-11 00:15:00.000000', '26735', 9),
(9, '2025-12-11 00:15:00.000000', '312', b'1', 'Đỗ Hoàng Gia Hân', '0907777777', '31', '369 Đường NOP, Phường QRS', b'0', '2025-12-11 00:15:00.000000', '11689', 10),
(10, '2025-12-11 00:15:00.000000', '760', b'1', 'Hà Phan Thảo Nguyên', '0908888888', '79', '741 Đường TUV, Phường WXY', b'0', '2025-12-11 00:15:00.000000', '26734', 11),
(11, '2025-12-11 00:15:00.000000', '916', b'1', 'Lý Nguyễn Trường Giang', '0909999999', '92', '852 Đường ZAB, Phường CDE', b'0', '2025-12-11 00:15:00.000000', '31156', 12),
(12, '2025-12-11 00:15:00.000000', '760', b'1', 'Mai Trần Thanh Trúc', '0910000000', '79', '963 Đường FGH, Phường IJK', b'0', '2025-12-11 00:15:00.000000', '26735', 13),
(13, '2025-12-11 00:15:00.000000', '001', b'0', 'Phạm Gia Tuấn Kiệt', '0901111111', '01', '159 Đường LMN, Phường OPQ', b'0', '2025-12-11 00:15:00.000000', '00001', 4),
(14, '2025-12-11 00:15:00.000000', '490', b'0', 'Hoàng Nguyễn Diệu Anh', '0902222222', '48', '357 Đường RST, Phường UVW', b'0', '2025-12-11 00:15:00.000000', '20215', 5),
(15, '2025-12-11 00:15:00.000000', '213', b'0', 'Vũ Hoàng Phương Nam', '0903333333', '24', '468 Đường XYZ, Phường ABC', b'0', '2025-12-11 00:15:00.000000', '07198', 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `brands`
--

CREATE TABLE `brands` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `updated_at` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `brands`
--

INSERT INTO `brands` (`id`, `created_at`, `name`, `status`, `updated_at`) VALUES
(1, '2025-12-11 00:14:59.000000', 'Nike', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(2, '2025-12-11 00:14:59.000000', 'Adidas', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(3, '2025-12-11 00:14:59.000000', 'Puma', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(4, '2025-12-11 00:14:59.000000', 'New Balance', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(5, '2025-12-11 00:14:59.000000', 'Converse', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(6, '2025-12-11 00:14:59.000000', 'Vans', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(7, '2025-12-11 00:14:59.000000', 'Reebok', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(8, '2025-12-11 00:14:59.000000', 'Under Armour', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(9, '2025-12-11 00:14:59.000000', 'Jordan', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(10, '2025-12-11 00:14:59.000000', 'Asics', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(11, '2025-12-11 00:14:59.000000', 'Fila', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(12, '2025-12-11 00:14:59.000000', 'Skechers', 'ACTIVE', '2025-12-11 00:14:59.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `updated_at` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`id`, `created_at`, `name`, `status`, `updated_at`) VALUES
(1, '2025-12-11 00:14:59.000000', 'Running Shoes', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(2, '2025-12-11 00:14:59.000000', 'Basketball Shoes', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(3, '2025-12-11 00:14:59.000000', 'Casual Shoes', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(4, '2025-12-11 00:14:59.000000', 'Sneakers', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(5, '2025-12-11 00:14:59.000000', 'Training Shoes', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(6, '2025-12-11 00:14:59.000000', 'Lifestyle Shoes', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(7, '2025-12-11 00:14:59.000000', 'High-Top Shoes', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(8, '2025-12-11 00:14:59.000000', 'Low-Top Shoes', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(9, '2025-12-11 00:14:59.000000', 'Slip-On Shoes', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(10, '2025-12-11 00:14:59.000000', 'Athletic Shoes', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(11, '2025-12-11 00:14:59.000000', 'Walking Shoes', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(12, '2025-12-11 00:14:59.000000', 'Skateboarding Shoes', 'ACTIVE', '2025-12-11 00:14:59.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chatbot_configs`
--

CREATE TABLE `chatbot_configs` (
  `id` int(11) NOT NULL,
  `config_key` varchar(255) NOT NULL,
  `config_value` text DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chatbot_trainings`
--

CREATE TABLE `chatbot_trainings` (
  `id` int(11) NOT NULL,
  `answer` text NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `question` text NOT NULL,
  `status` enum('ACTIVE','INACTIVE') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chat_histories`
--

CREATE TABLE `chat_histories` (
  `id` int(11) NOT NULL,
  `bot_response` text NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `feedback` text DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `user_message` text NOT NULL,
  `account_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `colors`
--

CREATE TABLE `colors` (
  `id` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `updated_at` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `colors`
--

INSERT INTO `colors` (`id`, `code`, `created_at`, `name`, `status`, `updated_at`) VALUES
(1, '#000000', '2025-12-11 00:14:59.000000', 'Black', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(2, '#FFFFFF', '2025-12-11 00:14:59.000000', 'White', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(3, '#FF0000', '2025-12-11 00:14:59.000000', 'Red', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(4, '#0000FF', '2025-12-11 00:14:59.000000', 'Blue', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(5, '#008000', '2025-12-11 00:14:59.000000', 'Green', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(6, '#FFFF00', '2025-12-11 00:14:59.000000', 'Yellow', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(7, '#808080', '2025-12-11 00:14:59.000000', 'Gray', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(8, '#000080', '2025-12-11 00:14:59.000000', 'Navy', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(9, '#FFC0CB', '2025-12-11 00:14:59.000000', 'Pink', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(10, '#FFA500', '2025-12-11 00:14:59.000000', 'Orange', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(11, '#800080', '2025-12-11 00:14:59.000000', 'Purple', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(12, '#A52A2A', '2025-12-11 00:14:59.000000', 'Brown', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(13, '#F5F5DC', '2025-12-11 00:14:59.000000', 'Beige', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(14, '#C0C0C0', '2025-12-11 00:14:59.000000', 'Silver', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(15, '#FFD700', '2025-12-11 00:14:59.000000', 'Gold', 'ACTIVE', '2025-12-11 00:14:59.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `materials`
--

CREATE TABLE `materials` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `updated_at` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `materials`
--

INSERT INTO `materials` (`id`, `created_at`, `name`, `status`, `updated_at`) VALUES
(1, '2025-12-11 00:14:59.000000', 'Leather', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(2, '2025-12-11 00:14:59.000000', 'Synthetic Leather', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(3, '2025-12-11 00:14:59.000000', 'Mesh', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(4, '2025-12-11 00:14:59.000000', 'Canvas', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(5, '2025-12-11 00:14:59.000000', 'Rubber', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(6, '2025-12-11 00:14:59.000000', 'Knit', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(7, '2025-12-11 00:14:59.000000', 'Suede', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(8, '2025-12-11 00:14:59.000000', 'Nubuck', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(9, '2025-12-11 00:14:59.000000', 'Primeknit', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(10, '2025-12-11 00:14:59.000000', 'Flyknit', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(11, '2025-12-11 00:14:59.000000', 'Textile', 'ACTIVE', '2025-12-11 00:14:59.000000'),
(12, '2025-12-11 00:14:59.000000', 'EVA', 'ACTIVE', '2025-12-11 00:14:59.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notifications`
--

CREATE TABLE `notifications` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `message` text NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` enum('VOUCHER','ORDER','SYSTEM','PROMOTION') NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `notifications`
--

INSERT INTO `notifications` (`id`, `created_at`, `is_read`, `message`, `title`, `type`, `updated_at`, `account_id`) VALUES
(1, '2025-12-11 00:15:00.000000', b'0', 'Đơn hàng ORD001 của bạn đã được xác nhận và đang được chuẩn bị', 'Đơn hàng đã được xác nhận', 'ORDER', '2025-12-11 00:15:00.000000', 2),
(2, '2025-12-11 00:15:00.000000', b'0', 'Đơn hàng ORD012 của bạn đang được vận chuyển', 'Đơn hàng đang được vận chuyển', 'ORDER', '2025-12-11 00:15:00.000000', 2),
(3, '2025-12-11 00:15:00.000000', b'1', 'Đơn hàng ORD002 của bạn đã được giao thành công', 'Đơn hàng đã được giao', 'ORDER', '2025-12-11 00:15:00.000000', 4),
(4, '2025-12-11 00:15:00.000000', b'1', 'Đơn hàng ORD013 của bạn đã hoàn thành', 'Đơn hàng hoàn thành', 'ORDER', '2025-12-11 00:15:00.000000', 4),
(5, '2025-12-11 00:15:00.000000', b'0', 'Đơn hàng ORD003 của bạn đang được vận chuyển', 'Đơn hàng đang vận chuyển', 'ORDER', '2025-12-11 00:15:00.000000', 5),
(6, '2025-12-11 00:15:00.000000', b'0', 'Đơn hàng ORD014 của bạn đang được vận chuyển', 'Đơn hàng đang vận chuyển', 'ORDER', '2025-12-11 00:15:00.000000', 5),
(7, '2025-12-11 00:15:00.000000', b'1', 'Đơn hàng ORD004 của bạn đã được giao thành công', 'Đơn hàng đã được giao', 'ORDER', '2025-12-11 00:15:00.000000', 6),
(8, '2025-12-11 00:15:00.000000', b'0', 'Đơn hàng ORD015 của bạn đã được xác nhận', 'Đơn hàng đã được xác nhận', 'ORDER', '2025-12-11 00:15:00.000000', 6),
(9, '2025-12-11 00:15:00.000000', b'1', 'Đơn hàng ORD005 của bạn đã hoàn thành', 'Đơn hàng hoàn thành', 'ORDER', '2025-12-11 00:15:00.000000', 7),
(10, '2025-12-11 00:15:00.000000', b'0', 'Đơn hàng ORD008 của bạn đã được xác nhận', 'Đơn hàng đã được xác nhận', 'ORDER', '2025-12-11 00:15:00.000000', 8),
(11, '2025-12-11 00:15:00.000000', b'1', 'Đơn hàng ORD007 của bạn đã được giao thành công', 'Đơn hàng đã được giao', 'ORDER', '2025-12-11 00:15:00.000000', 9),
(12, '2025-12-11 00:15:00.000000', b'1', 'Đơn hàng ORD009 của bạn đã hoàn thành', 'Đơn hàng hoàn thành', 'ORDER', '2025-12-11 00:15:00.000000', 11),
(13, '2025-12-11 00:15:00.000000', b'1', 'Đơn hàng ORD011 của bạn đã được giao thành công', 'Đơn hàng đã được giao', 'ORDER', '2025-12-11 00:15:00.000000', 13),
(14, '2025-12-11 00:15:00.000000', b'0', 'Khuyến mãi Summer Sale 2024 với giảm giá lên đến 20%', 'Khuyến mãi mới', 'PROMOTION', '2025-12-11 00:15:00.000000', NULL),
(15, '2025-12-11 00:15:00.000000', b'0', 'Bạn có voucher mới: VOUCHER10 - Giảm 10% cho đơn hàng từ 500.000đ', 'Voucher mới', 'VOUCHER', '2025-12-11 00:15:00.000000', NULL),
(16, '2025-12-11 00:15:00.000000', b'0', 'Hệ thống sẽ bảo trì vào ngày 15/12/2024 từ 2h-4h sáng', 'Thông báo hệ thống', 'SYSTEM', '2025-12-11 00:15:00.000000', NULL),
(17, '2025-12-11 00:15:00.000000', b'0', 'Bạn có voucher mới: NEWUSER - Giảm 15% cho khách hàng mới', 'Voucher mới', 'VOUCHER', '2025-12-11 00:15:00.000000', 2),
(18, '2025-12-11 00:15:00.000000', b'0', 'Khuyến mãi Back to School với giảm giá 15%', 'Khuyến mãi', 'PROMOTION', '2025-12-11 00:15:00.000000', 4),
(19, '2025-12-11 00:15:00.000000', b'0', 'Bạn có voucher: VOUCHER50K - Giảm 50.000đ', 'Voucher', 'VOUCHER', '2025-12-11 00:15:00.000000', 5),
(20, '2025-12-11 00:15:00.000000', b'1', 'Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi', 'Thông báo hệ thống', 'SYSTEM', '2025-12-11 00:15:00.000000', 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `discount` decimal(12,2) NOT NULL,
  `order_status` enum('CHO_XAC_NHAN','CHO_GIAO_HANG','DANG_VAN_CHUYEN','DA_GIAO_HANG','HOAN_THANH','DA_HUY') NOT NULL,
  `payment_method` enum('CASH','BANK_TRANSFER','COD','MIXED') NOT NULL,
  `payment_status` enum('PENDING','PARTIAL_PAID','PAID') NOT NULL,
  `shipping_district_id` varchar(255) DEFAULT NULL,
  `shipping_name` varchar(255) DEFAULT NULL,
  `shipping_phone_number` varchar(255) DEFAULT NULL,
  `shipping_province_id` varchar(255) DEFAULT NULL,
  `shipping_specific_address` varchar(255) DEFAULT NULL,
  `shipping_ward_id` varchar(255) DEFAULT NULL,
  `sub_total` decimal(12,2) NOT NULL,
  `total` decimal(12,2) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `voucher_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id`, `code`, `created_at`, `discount`, `order_status`, `payment_method`, `payment_status`, `shipping_district_id`, `shipping_name`, `shipping_phone_number`, `shipping_province_id`, `shipping_specific_address`, `shipping_ward_id`, `sub_total`, `total`, `updated_at`, `customer_id`, `staff_id`, `voucher_id`) VALUES
(1, 'ORD001', '2025-12-11 00:15:00.000000', 250000.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '760', 'Trần Phan Anh Thư', '0901234568', '79', '123 Đường ABC, Phường XYZ', '26734', 2500000.00, 2250000.00, '2025-12-11 00:15:00.000000', 2, 3, 1),
(2, 'ORD002', '2025-12-11 00:15:00.000000', 0.00, 'CHO_GIAO_HANG', 'BANK_TRANSFER', 'PENDING', '760', 'Phạm Gia Tuấn Kiệt', '0901111111', '79', '789 Đường GHI, Phường RST', '26734', 3200000.00, 3200000.00, '2025-12-11 00:15:00.000000', 4, NULL, NULL),
(3, 'ORD003', '2025-12-11 00:15:00.000000', 50000.00, 'DANG_VAN_CHUYEN', 'COD', 'PENDING', '001', 'Hoàng Nguyễn Diệu Anh', '0902222222', '01', '321 Đường JKL, Phường MNO', '00001', 1800000.00, 1750000.00, '2025-12-11 00:15:00.000000', 5, 3, 3),
(4, 'ORD004', '2025-12-11 00:15:00.000000', 0.00, 'DA_GIAO_HANG', 'CASH', 'PAID', '490', 'Vũ Hoàng Phương Nam', '0903333333', '48', '654 Đường PQR, Phường STU', '20215', 2200000.00, 2200000.00, '2025-12-11 00:15:00.000000', 6, NULL, NULL),
(5, 'ORD005', '2025-12-11 00:15:00.000000', 1000000.00, 'HOAN_THANH', 'BANK_TRANSFER', 'PAID', '760', 'Đặng Lê Minh Ngọc', '0904444444', '79', '987 Đường VWX, Phường YZA', '26734', 5000000.00, 4000000.00, '2025-12-11 00:15:00.000000', 7, 14, 2),
(6, 'ORD006', '2025-12-11 00:15:00.000000', 0.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '213', 'Bùi Phan Khánh An', '0905555555', '24', '147 Đường BCD, Phường EFG', '07198', 1500000.00, 1500000.00, '2025-12-11 00:15:00.000000', 8, NULL, NULL),
(7, 'ORD007', '2025-12-11 00:15:00.000000', 280000.00, 'DA_GIAO_HANG', 'CASH', 'PAID', '760', 'Ngô Trần Tuấn Minh', '0906666666', '79', '258 Đường HIJ, Phường KLM', '26735', 2800000.00, 2520000.00, '2025-12-11 00:15:00.000000', 9, 3, 1),
(8, 'ORD008', '2025-12-11 00:15:00.000000', 0.00, 'DANG_VAN_CHUYEN', 'BANK_TRANSFER', 'PARTIAL_PAID', '312', 'Đỗ Hoàng Gia Hân', '0907777777', '31', '369 Đường NOP, Phường QRS', '11689', 2400000.00, 2400000.00, '2025-12-11 00:15:00.000000', 10, NULL, NULL),
(9, 'ORD009', '2025-12-11 00:15:00.000000', 100000.00, 'HOAN_THANH', 'CASH', 'PAID', '760', 'Hà Phan Thảo Nguyên', '0908888888', '79', '741 Đường TUV, Phường WXY', '26734', 4500000.00, 4400000.00, '2025-12-11 00:15:00.000000', 11, 14, 4),
(10, 'ORD010', '2025-12-11 00:15:00.000000', 0.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '916', 'Lý Nguyễn Trường Giang', '0909999999', '92', '852 Đường ZAB, Phường CDE', '31156', 1600000.00, 1600000.00, '2025-12-11 00:15:00.000000', 12, NULL, NULL),
(11, 'ORD011', '2025-12-11 00:15:00.000000', 0.00, 'DA_GIAO_HANG', 'BANK_TRANSFER', 'PAID', '760', 'Mai Trần Thanh Trúc', '0910000000', '79', '963 Đường FGH, Phường IJK', '26735', 2100000.00, 2100000.00, '2025-12-11 00:15:00.000000', 13, 3, NULL),
(12, 'ORD012', '2025-12-11 00:15:00.000000', 200000.00, 'CHO_GIAO_HANG', 'COD', 'PENDING', '760', 'Trần Phan Anh Thư', '0901234568', '79', '123 Đường ABC, Phường XYZ', '26734', 3000000.00, 2800000.00, '2025-12-11 00:15:00.000000', 2, NULL, 5),
(13, 'ORD013', '2025-12-11 00:15:00.000000', 0.00, 'HOAN_THANH', 'CASH', 'PAID', '760', 'Phạm Gia Tuấn Kiệt', '0901111111', '79', '789 Đường GHI, Phường RST', '26734', 1800000.00, 1800000.00, '2025-12-11 00:15:00.000000', 4, 14, NULL),
(14, 'ORD014', '2025-12-11 00:15:00.000000', 250000.00, 'DANG_VAN_CHUYEN', 'BANK_TRANSFER', 'PARTIAL_PAID', '001', 'Hoàng Nguyễn Diệu Anh', '0902222222', '01', '321 Đường JKL, Phường MNO', '00001', 2500000.00, 2250000.00, '2025-12-11 00:15:00.000000', 5, NULL, 1),
(15, 'ORD015', '2025-12-11 00:15:00.000000', 0.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '490', 'Vũ Hoàng Phương Nam', '0903333333', '48', '654 Đường PQR, Phường STU', '20215', 3200000.00, 3200000.00, '2025-12-11 00:15:00.000000', 6, 3, NULL),
(16, 'DH2026071698', '2026-01-09 20:54:32.000000', 0.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '32', 'Trần Phan Anh Thư', '0901234568', '2', '123 Đường ABC, Phường XYZ, Xã Bản Phùng, Huyện Hoàng Su Phì, Tỉnh Hà Giang, Việt Nam', '1036', 8800000.00, 9240000.00, '2026-01-09 20:54:32.000000', 2, NULL, NULL),
(17, 'DH2026093169', '2026-01-09 20:54:53.000000', 0.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '32', 'Trần Phan Anh Thư', '0901234568', '2', '123 Đường ABC, Phường XYZ, Xã Bản Phùng, Huyện Hoàng Su Phì, Tỉnh Hà Giang, Việt Nam', '1036', 8800000.00, 9240000.00, '2026-01-09 20:54:53.000000', 2, NULL, NULL),
(18, 'DH2026479017', '2026-01-09 21:01:20.000000', 0.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '32', 'Trần Phan Anh Thư', '0901234568', '2', '123 Đường ABC, Phường XYZ, Xã Bản Phùng, Huyện Hoàng Su Phì, Tỉnh Hà Giang, Việt Nam', '1036', 8800000.00, 9240000.00, '2026-01-09 21:01:20.000000', 2, NULL, NULL),
(20, 'DH2026491559', '2026-01-09 21:01:31.000000', 0.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '32', 'Trần Phan Anh Thư', '0901234568', '2', '123 Đường ABC, Phường XYZ, Xã Bản Phùng, Huyện Hoàng Su Phì, Tỉnh Hà Giang, Việt Nam', '1036', 8800000.00, 9240000.00, '2026-01-09 21:01:31.000000', 2, NULL, NULL),
(22, 'DH2026696929', '2026-01-09 21:04:58.000000', 0.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '32', 'Trần Phan Anh Thư', '0901234568', '2', '123 Đường ABC, Phường XYZ, Xã Bản Phùng, Huyện Hoàng Su Phì, Tỉnh Hà Giang, Việt Nam', '1036', 8800000.00, 9240000.00, '2026-01-09 21:04:58.000000', 2, NULL, NULL),
(23, 'DH2026705609', '2026-01-09 21:05:06.000000', 0.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '32', 'Trần Phan Anh Thư', '0901234568', '2', '123 Đường ABC, Phường XYZ, Xã Bản Phùng, Huyện Hoàng Su Phì, Tỉnh Hà Giang, Việt Nam', '1036', 8800000.00, 9240000.00, '2026-01-09 21:05:06.000000', 2, NULL, NULL),
(24, 'DH2026902204', '2026-01-09 21:08:22.000000', 0.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '137', 'Trần Phan Anh Thư', '0901234568', '15', '123 Đường ABC, Phường XYZ, Xã Kim Nọi, Huyện Mù Căng Chải, Tỉnh Yên Bái, Việt Nam', '4477', 8800000.00, 9240000.00, '2026-01-09 21:08:22.000000', 2, NULL, NULL),
(25, 'DH2026027419', '2026-01-09 21:10:27.000000', 0.00, 'CHO_GIAO_HANG', 'COD', 'PENDING', '62', 'Trần Phan Anh Thư', '0901234568', '6', '123 Đường ABC, Phường XYZ, Xã Hiệp Lực, Huyện Ngân Sơn, Tỉnh Bắc Kạn, Việt Nam', '1960', 3200000.00, 3360000.00, '2026-01-11 22:12:54.000000', 2, NULL, NULL),
(26, 'DH2026650395', '2026-01-11 23:20:52.000000', 200000.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '124', 'Phan Lê Kim Ngân', '0912222222', '14', 'Đường Lê Văn Việt, Phường Hiệp Phú, Xã Chiềng Hặc, Huyện Yên Châu, Tỉnh Sơn La, Việt Nam', '4078', 6000000.00, 6100000.00, '2026-01-11 23:20:52.000000', 15, 15, NULL),
(27, 'DH2026900922', '2026-01-11 23:25:01.000000', 200000.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '32', 'Phan Lê Kim Ngân', '0912222222', '2', 'Đường Lê Văn Việt, Phường Hiệp Phú, Xã Nàng Đôn, Huyện Hoàng Su Phì, Tỉnh Hà Giang, Việt Nam', '1054', 2500000.00, 2425000.00, '2026-01-11 23:25:01.000000', 15, 15, NULL),
(28, 'DH2026033550', '2026-01-11 23:27:13.000000', 0.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '27', 'Phan Lê Kim Ngân', '0912222222', '2', 'Đường Lê Văn Việt, Phường Hiệp Phú, Xã Sơn Vĩ, Huyện Mèo Vạc, Tỉnh Hà Giang, Việt Nam', '793', 3200000.00, 3360000.00, '2026-01-11 23:27:13.000000', 15, 15, NULL),
(29, 'DH2026974355', '2026-01-24 08:39:36.000000', 150000.00, 'CHO_XAC_NHAN', 'COD', 'PENDING', '662', 'Trần Phan Anh Thư', '0901234568', '67', '123 Đường ABC, Phường XYZ, Xã Cư Knia, Huyện Cư Jút, Tỉnh Đắk Nông, Việt Nam', '24658', 6900000.00, 7095000.00, '2026-01-24 08:39:36.000000', 2, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_items`
--

CREATE TABLE `order_items` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `order_id` int(11) NOT NULL,
  `variant_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `order_items`
--

INSERT INTO `order_items` (`id`, `created_at`, `price`, `quantity`, `updated_at`, `order_id`, `variant_id`) VALUES
(1, '2025-12-11 00:15:00.000000', 2500000.00, 1, '2025-12-11 00:15:00.000000', 1, 1),
(3, '2025-12-11 00:15:00.000000', 1800000.00, 1, '2025-12-11 00:15:00.000000', 3, 12),
(4, '2025-12-11 00:15:00.000000', 2200000.00, 1, '2025-12-11 00:15:00.000000', 4, 10),
(7, '2025-12-11 00:15:00.000000', 1500000.00, 1, '2025-12-11 00:15:00.000000', 6, 16),
(11, '2025-12-11 00:15:00.000000', 1600000.00, 1, '2025-12-11 00:15:00.000000', 10, 18),
(14, '2025-12-11 00:15:00.000000', 1800000.00, 1, '2025-12-11 00:15:00.000000', 13, 12),
(15, '2025-12-11 00:15:00.000000', 2500000.00, 1, '2025-12-11 00:15:00.000000', 14, 1),
(17, '2025-12-11 00:15:00.000000', 2500000.00, 1, '2025-12-11 00:15:00.000000', 1, 2),
(19, '2025-12-11 00:15:00.000000', 1800000.00, 1, '2025-12-11 00:15:00.000000', 3, 13),
(20, '2025-12-11 00:15:00.000000', 2200000.00, 1, '2025-12-11 00:15:00.000000', 4, 11),
(23, '2026-01-09 20:54:32.000000', 1500000.00, 4, '2026-01-09 20:54:32.000000', 16, 16),
(25, '2026-01-09 20:54:53.000000', 1500000.00, 4, '2026-01-09 20:54:53.000000', 17, 16),
(27, '2026-01-09 21:01:20.000000', 1500000.00, 4, '2026-01-09 21:01:20.000000', 18, 16),
(29, '2026-01-09 21:01:31.000000', 1500000.00, 4, '2026-01-09 21:01:31.000000', 20, 16),
(31, '2026-01-09 21:04:58.000000', 1500000.00, 4, '2026-01-09 21:04:58.000000', 22, 16),
(33, '2026-01-09 21:05:06.000000', 1500000.00, 4, '2026-01-09 21:05:06.000000', 23, 16),
(35, '2026-01-09 21:08:22.000000', 1500000.00, 4, '2026-01-09 21:08:22.000000', 24, 16),
(40, '2026-01-11 23:25:01.000000', 2500000.00, 1, '2026-01-11 23:25:01.000000', 27, 1),
(42, '2026-01-24 08:39:36.000000', 2500000.00, 1, '2026-01-24 08:39:36.000000', 29, 1),
(43, '2026-01-24 08:39:36.000000', 2200000.00, 2, '2026-01-24 08:39:36.000000', 29, 11);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `payments`
--

CREATE TABLE `payments` (
  `id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `bank_transfer_info` text DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `method` enum('CASH','BANK_TRANSFER','COD','VNPAY','MOMO') NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` enum('PENDING','COMPLETED','FAILED','REFUNDED') NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `payments`
--

INSERT INTO `payments` (`id`, `amount`, `bank_transfer_info`, `created_at`, `method`, `note`, `status`, `updated_at`, `order_id`) VALUES
(1, 2250000, NULL, '2025-12-11 00:15:00.000000', 'COD', 'Thanh toán khi nhận hàng', 'PENDING', '2025-12-11 00:15:00.000000', 1),
(2, 3200000, '{\"bank\": \"Vietcombank\", \"account\": \"1234567890\", \"transaction\": \"TXN001\"}', '2025-12-11 00:15:00.000000', 'BANK_TRANSFER', 'Chuyển khoản ngân hàng', 'PENDING', '2025-12-11 00:15:00.000000', 2),
(3, 1750000, NULL, '2025-12-11 00:15:00.000000', 'COD', 'Thanh toán khi nhận hàng', 'PENDING', '2025-12-11 00:15:00.000000', 3),
(4, 2200000, NULL, '2025-12-11 00:15:00.000000', 'CASH', 'Thanh toán tiền mặt tại cửa hàng', 'COMPLETED', '2025-12-11 00:15:00.000000', 4),
(5, 4000000, '{\"bank\": \"BIDV\", \"account\": \"0987654321\", \"transaction\": \"TXN002\"}', '2025-12-11 00:15:00.000000', 'BANK_TRANSFER', 'Đã thanh toán đầy đủ', 'COMPLETED', '2025-12-11 00:15:00.000000', 5),
(6, 1500000, NULL, '2025-12-11 00:15:00.000000', 'COD', 'Thanh toán khi nhận hàng', 'PENDING', '2025-12-11 00:15:00.000000', 6),
(7, 2520000, NULL, '2025-12-11 00:15:00.000000', 'CASH', 'Thanh toán tiền mặt', 'COMPLETED', '2025-12-11 00:15:00.000000', 7),
(8, 1200000, '{\"bank\": \"Techcombank\", \"account\": \"1122334455\", \"transaction\": \"TXN003\"}', '2025-12-11 00:15:00.000000', 'BANK_TRANSFER', 'Thanh toán một phần', 'COMPLETED', '2025-12-11 00:15:00.000000', 8),
(9, 1200000, '{\"bank\": \"Techcombank\", \"account\": \"1122334455\", \"transaction\": \"TXN004\"}', '2025-12-11 00:15:00.000000', 'BANK_TRANSFER', 'Thanh toán phần còn lại', 'PENDING', '2025-12-11 00:15:00.000000', 8),
(10, 4400000, NULL, '2025-12-11 00:15:00.000000', 'CASH', 'Thanh toán tiền mặt đầy đủ', 'COMPLETED', '2025-12-11 00:15:00.000000', 9),
(11, 1600000, NULL, '2025-12-11 00:15:00.000000', 'COD', 'Thanh toán khi nhận hàng', 'PENDING', '2025-12-11 00:15:00.000000', 10),
(12, 2100000, '{\"bank\": \"Vietinbank\", \"account\": \"5566778899\", \"transaction\": \"TXN005\"}', '2025-12-11 00:15:00.000000', 'BANK_TRANSFER', 'Đã thanh toán', 'COMPLETED', '2025-12-11 00:15:00.000000', 11),
(13, 2800000, NULL, '2025-12-11 00:15:00.000000', 'COD', 'Thanh toán khi nhận hàng', 'PENDING', '2025-12-11 00:15:00.000000', 12),
(14, 1800000, NULL, '2025-12-11 00:15:00.000000', 'CASH', 'Thanh toán tiền mặt', 'COMPLETED', '2025-12-11 00:15:00.000000', 13),
(15, 1125000, '{\"bank\": \"ACB\", \"account\": \"9988776655\", \"transaction\": \"TXN006\"}', '2025-12-11 00:15:00.000000', 'BANK_TRANSFER', 'Thanh toán một phần', 'COMPLETED', '2025-12-11 00:15:00.000000', 14),
(16, 1125000, '{\"bank\": \"ACB\", \"account\": \"9988776655\", \"transaction\": \"TXN007\"}', '2025-12-11 00:15:00.000000', 'BANK_TRANSFER', 'Thanh toán phần còn lại', 'PENDING', '2025-12-11 00:15:00.000000', 14),
(17, 3200000, NULL, '2025-12-11 00:15:00.000000', 'COD', 'Thanh toán khi nhận hàng', 'PENDING', '2025-12-11 00:15:00.000000', 15),
(18, 3200000, '{\"transaction\": \"VNPAY001\", \"payment_id\": \"12345\"}', '2025-12-11 00:15:00.000000', 'VNPAY', 'Thanh toán qua VNPay', 'COMPLETED', '2025-12-11 00:15:00.000000', 2),
(19, 4000000, '{\"transaction\": \"MOMO001\", \"payment_id\": \"67890\"}', '2025-12-11 00:15:00.000000', 'MOMO', 'Thanh toán qua MoMo', 'COMPLETED', '2025-12-11 00:15:00.000000', 5),
(20, 2100000, '{\"transaction\": \"VNPAY002\", \"payment_id\": \"11111\"}', '2025-12-11 00:15:00.000000', 'VNPAY', 'Thanh toán qua VNPay', 'COMPLETED', '2025-12-11 00:15:00.000000', 11);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `description` text NOT NULL,
  `name` varchar(255) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `weight` decimal(8,2) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `material_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`id`, `code`, `created_at`, `description`, `name`, `status`, `updated_at`, `weight`, `brand_id`, `category_id`, `material_id`) VALUES
(1, 'PRD001', '2025-12-11 00:14:59.000000', 'Classic running shoe with Air Max cushioning technology. Perfect for daily wear and light running activities.', 'Nike Air Max 90 3M Pack', 'ACTIVE', '2025-12-11 21:51:43.000000', 0.35, 1, 4, 1),
(4, 'PRD004', '2025-12-11 00:14:59.000000', 'Timeless tennis shoe with clean minimalist design. Perfect for casual everyday wear.', 'Adidas Stan Smith', 'ACTIVE', '2025-12-11 00:14:59.000000', 0.30, 2, 3, 1),
(5, 'PRD005', '2025-12-11 00:14:59.000000', 'Classic suede sneaker with retro style. Comfortable and stylish for streetwear.', 'Puma 180 \'Corduroy - Alpine Snow Chocolate Chip\'', 'ACTIVE', '2025-12-11 21:57:07.000000', 0.33, 3, 4, 7),
(7, 'PRD007', '2025-12-11 00:14:59.000000', 'Iconic canvas sneaker with timeless design. Perfect for casual and street style.', 'Converse Chuck Taylor All-Star Lugged Hi A-COLD-WALL', 'ACTIVE', '2025-12-11 21:59:00.000000', 0.28, 5, 3, 4),
(8, 'PRD008', '2025-12-11 00:14:59.000000', 'Classic skate shoe with signature side stripe. Durable and comfortable for skating and casual wear.', 'Vans Old Skool A$AP Worldwide Silver Reflective Flames', 'ACTIVE', '2025-12-11 22:00:36.000000', 0.31, 6, 9, 4),
(11, 'PRD011', '2025-12-11 00:14:59.000000', 'Iconic high-top basketball shoe with Air cushioning. Premium leather construction.', 'Jordan 1 Retro High OG A Ma Maniére', 'ACTIVE', '2025-12-11 22:01:34.000000', 0.42, 9, 2, 1),
(16, 'PRD000010', '2026-01-12 04:56:30.000000', 'dfdfd', 'fdfd', 'ACTIVE', '2026-01-12 04:56:30.000000', 0.50, 9, 12, 5),
(17, 'PRD000011', '2026-01-12 05:33:12.000000', 'dfdfd', 'cxcxv', 'ACTIVE', '2026-01-12 05:33:12.000000', 0.50, 1, 12, 9),
(18, 'PRD018', '2025-12-11 00:14:59.000000', 'Unique Nike Air 1/2 Cent in Black colorway.', 'Nike Air 1/2 Cent Black', 'ACTIVE', '2025-12-11 00:14:59.000000', 0.40, 1, 4, 1),
(19, 'PRD019', '2025-12-11 00:14:59.000000', 'Unique Nike Air 1/2 Cent in Black Green Spark colorway.', 'Nike Air 1/2 Cent Black Green Spark', 'ACTIVE', '2025-12-11 00:14:59.000000', 0.40, 1, 4, 1),
(20, 'PRD020', '2025-12-11 00:14:59.000000', 'Unique Nike Air 1/2 Cent in Silver colorway.', 'Nike Air 1/2 Cent Silver', 'ACTIVE', '2025-12-11 00:14:59.000000', 0.40, 1, 4, 1),
(21, 'PRD021', '2025-12-11 00:14:59.000000', 'Unique Nike Air 1/2 Cent in Royal colorway.', 'Nike Air 1/2 Cent Royal', 'ACTIVE', '2025-12-11 00:14:59.000000', 0.40, 1, 4, 1),
(22, 'PRD022', '2025-12-11 00:14:59.000000', 'Unique Nike Air 1/2 Cent in Cranberry colorway.', 'Nike Air 1/2 Cent Cranberry', 'ACTIVE', '2025-12-11 00:14:59.000000', 0.40, 1, 4, 1),
(23, 'PRD023', '2025-12-11 00:14:59.000000', 'Nike Air Force 1 Low SP x 1017 ALYX 9SM collaboration in Black.', 'Nike Air Force 1 Low SP 1017 ALYX 9SM Black', 'ACTIVE', '2025-12-11 00:14:59.000000', 0.40, 1, 4, 1),
(24, 'PRD024', '2025-12-11 00:14:59.000000', 'Nike Air Force 1 Low SP x 1017 ALYX 9SM collaboration in White.', 'Nike Air Force 1 Low SP 1017 ALYX 9SM White', 'ACTIVE', '2025-12-11 00:14:59.000000', 0.40, 1, 4, 1),
(25, 'PRD025', '2025-12-11 00:14:59.000000', 'Nike Air Force 1 High x 1017 ALYX 9SM collaboration in Black/Red.', 'Nike Air Force 1 High 1017 ALYX 9SM Black Red', 'ACTIVE', '2025-12-11 00:14:59.000000', 0.45, 1, 4, 1),
(26, 'PRD026', '2025-12-11 00:14:59.000000', 'Nike Air Force 1 High x 1017 ALYX 9SM collaboration in Red/Black.', 'Nike Air Force 1 High 1017 ALYX 9SM Black Red', 'ACTIVE', '2025-12-11 00:14:59.000000', 0.45, 1, 4, 1),
(27, 'PRD027', '2025-12-11 00:14:59.000000', 'Nike Air Force 1 High x 1017 ALYX 9SM collaboration in White/Grey (2021).', 'Nike Air Force 1 High 1017 ALYX 9SM White Grey (2021)', 'ACTIVE', '2025-12-11 00:14:59.000000', 0.45, 1, 4, 1),
(28, 'PRD028', '2026-02-01 18:36:32.000000', 'adidas Campus Prince 032c Black. Unique collaboration.', 'adidas Campus Prince 032c Black', 'ACTIVE', '2026-02-01 18:36:32.000000', 0.25, 2, 4, 1),
(29, 'PRD029', '2026-02-01 18:36:32.000000', 'adidas Campus Prince 032c White. Unique collaboration.', 'adidas Campus Prince 032c White', 'ACTIVE', '2026-02-01 18:36:32.000000', 0.25, 2, 4, 1),
(30, 'PRD030', '2026-02-01 18:36:32.000000', 'adidas FYW S-97 Salvation 032c. Retro running style.', 'adidas FYW S-97 Salvation 032c', 'ACTIVE', '2026-02-01 18:36:32.000000', 0.30, 2, 10, 3),
(31, 'PRD031', '2026-02-01 18:36:32.000000', 'adidas GSG Mule 032c Black. Versatile slip-on.', 'adidas GSG Mule 032c Black', 'ACTIVE', '2026-02-01 18:36:32.000000', 0.20, 2, 9, 2),
(32, 'PRD032', '2026-02-01 18:36:32.000000', 'adidas GSG Mule 032c Savannah. Versatile slip-on.', 'adidas GSG Mule 032c Savannah', 'ACTIVE', '2026-02-01 18:36:32.000000', 0.20, 2, 9, 2),
(33, 'PRD033', '2026-02-01 18:36:32.000000', 'adidas GSG Mule 032C Grey Silver. Versatile slip-on.', 'adidas GSG Mule 032C Grey Silver', 'ACTIVE', '2026-02-01 18:36:32.000000', 0.20, 2, 9, 2),
(34, 'PRD034', '2026-02-01 18:36:32.000000', 'adidas GSG TR 032c Hi-Res Yellow. Bold trail design.', 'adidas GSG TR 032c Hi-Res Yellow', 'ACTIVE', '2026-02-01 18:36:32.000000', 0.35, 2, 4, 3),
(35, 'PRD035', '2026-02-01 18:36:32.000000', 'adidas GSG Trail 032C Grey Silver. Bold trail design.', 'adidas GSG Trail 032C Grey Silver', 'ACTIVE', '2026-02-01 18:36:32.000000', 0.35, 2, 4, 3),
(36, 'PRD036', '2026-02-01 18:36:32.000000', 'adidas GSG TR 032c Hi-Res Stone Green. Bold trail design.', 'adidas GSG TR 032c Hi-Res Stone Green', 'ACTIVE', '2026-02-01 18:36:32.000000', 0.35, 2, 4, 3),
(37, 'PRD037', '2026-02-01 18:36:32.000000', '100 Thieves x adidas Adilette Slide. Comfortable slides.', '100 Thieves x adidas Adilette Slide Black Red', 'ACTIVE', '2026-02-01 18:36:32.000000', 0.10, 2, 9, 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_variants`
--

CREATE TABLE `product_variants` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `color_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `size_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product_variants`
--

INSERT INTO `product_variants` (`id`, `created_at`, `price`, `stock`, `updated_at`, `color_id`, `product_id`, `size_id`) VALUES
(1, '2025-12-11 00:14:59.000000', 2500000.00, 48, '2026-01-24 08:39:36.000000', 1, 1, 7),
(2, '2025-12-11 00:14:59.000000', 2500000.00, 45, '2025-12-11 00:14:59.000000', 2, 1, 7),
(3, '2025-12-11 00:14:59.000000', 2500000.00, 30, '2025-12-11 00:14:59.000000', 3, 1, 7),
(10, '2025-12-11 00:14:59.000000', 2200000.00, 70, '2025-12-11 00:14:59.000000', 2, 4, 7),
(11, '2025-12-11 00:14:59.000000', 2200000.00, 63, '2026-01-24 08:39:36.000000', 1, 4, 7),
(12, '2025-12-11 00:14:59.000000', 1800000.00, 50, '2025-12-11 00:14:59.000000', 1, 5, 7),
(13, '2025-12-11 00:14:59.000000', 1800000.00, 45, '2025-12-11 00:14:59.000000', 2, 5, 7),
(16, '2025-12-11 00:14:59.000000', 1500000.00, 52, '2026-01-09 21:08:22.000000', 1, 7, 7),
(17, '2025-12-11 00:14:59.000000', 1500000.00, 75, '2025-12-11 00:14:59.000000', 2, 7, 7),
(18, '2025-12-11 00:14:59.000000', 1600000.00, 60, '2025-12-11 00:14:59.000000', 1, 8, 7),
(19, '2025-12-11 00:14:59.000000', 1600000.00, 55, '2025-12-11 00:14:59.000000', 2, 8, 7),
(24, '2025-12-11 00:14:59.000000', 4500000.00, 30, '2025-12-11 00:14:59.000000', 1, 11, 9),
(25, '2025-12-11 00:14:59.000000', 4500000.00, 25, '2025-12-11 00:14:59.000000', 3, 11, 9),
(26, '2026-01-12 04:56:30.000000', 5578000.00, 67, '2026-01-12 04:56:30.000000', 13, 16, 17),
(27, '2026-01-12 04:56:30.000000', 5578000.00, 10, '2026-01-12 04:56:30.000000', 1, 16, 17),
(28, '2026-01-12 05:33:12.000000', 1287000.00, 0, '2026-01-12 05:33:12.000000', 13, 17, 17),
(29, '2026-01-12 05:33:12.000000', 1287000.00, 10, '2026-01-12 05:33:12.000000', 1, 17, 17),
(30, '2026-01-12 05:33:12.000000', 1287000.00, 10, '2026-01-12 05:33:12.000000', 4, 17, 17),
(31, '2026-01-12 05:33:12.000000', 1287000.00, 10, '2026-01-12 05:33:12.000000', 12, 17, 17),
(32, '2026-01-12 05:33:12.000000', 1287000.00, 10, '2026-01-12 05:33:12.000000', 15, 17, 17),
(33, '2026-01-12 05:33:12.000000', 1287000.00, 10, '2026-01-12 05:33:12.000000', 7, 17, 17),
(34, '2026-01-12 05:33:12.000000', 1287000.00, 10, '2026-01-12 05:33:12.000000', 5, 17, 17),
(35, '2026-01-12 05:33:12.000000', 1287000.00, 10, '2026-01-12 05:33:12.000000', 8, 17, 17),
(36, '2026-01-12 05:33:12.000000', 1287000.00, 10, '2026-01-12 05:33:12.000000', 10, 17, 17),
(37, '2026-01-12 05:33:12.000000', 1287000.00, 10, '2026-01-12 05:33:12.000000', 9, 17, 17),
(38, '2026-01-12 05:33:12.000000', 1315000.00, 10, '2026-01-12 05:33:12.000000', 13, 17, 1),
(39, '2026-01-12 05:33:13.000000', 1315000.00, 10, '2026-01-12 05:33:13.000000', 1, 17, 1),
(40, '2026-01-12 05:33:13.000000', 1315000.00, 10, '2026-01-12 05:33:13.000000', 4, 17, 1),
(41, '2026-01-12 05:33:13.000000', 1315000.00, 10, '2026-01-12 05:33:13.000000', 12, 17, 1),
(42, '2026-01-12 05:33:13.000000', 1315000.00, 10, '2026-01-12 05:33:13.000000', 15, 17, 1),
(43, '2026-01-12 05:33:13.000000', 1315000.00, 10, '2026-01-12 05:33:13.000000', 7, 17, 1),
(44, '2026-01-12 05:33:13.000000', 1315000.00, 10, '2026-01-12 05:33:13.000000', 5, 17, 1),
(45, '2026-01-12 05:33:13.000000', 1315000.00, 10, '2026-01-12 05:33:13.000000', 8, 17, 1),
(46, '2026-01-12 05:33:13.000000', 1315000.00, 10, '2026-01-12 05:33:13.000000', 10, 17, 1),
(47, '2026-01-12 05:33:13.000000', 1315000.00, 10, '2026-01-12 05:33:13.000000', 9, 17, 1),
(48, '2026-01-12 05:33:13.000000', 1371000.00, 10, '2026-01-12 05:33:13.000000', 13, 17, 3),
(49, '2026-01-12 05:33:13.000000', 1371000.00, 10, '2026-01-12 05:33:13.000000', 1, 17, 3),
(50, '2026-01-12 05:33:13.000000', 1371000.00, 10, '2026-01-12 05:33:13.000000', 4, 17, 3),
(51, '2026-01-12 05:33:13.000000', 1371000.00, 10, '2026-01-12 05:33:13.000000', 12, 17, 3),
(52, '2026-01-12 05:33:13.000000', 1371000.00, 10, '2026-01-12 05:33:13.000000', 15, 17, 3),
(53, '2026-01-12 05:33:13.000000', 1371000.00, 10, '2026-01-12 05:33:13.000000', 7, 17, 3),
(54, '2026-01-12 05:33:13.000000', 1371000.00, 10, '2026-01-12 05:33:13.000000', 5, 17, 3),
(55, '2026-01-12 05:33:13.000000', 1371000.00, 10, '2026-01-12 05:33:13.000000', 8, 17, 3),
(56, '2026-01-12 05:33:13.000000', 1371000.00, 10, '2026-01-12 05:33:13.000000', 10, 17, 3),
(57, '2026-01-12 05:33:13.000000', 1371000.00, 10, '2026-01-12 05:33:13.000000', 9, 17, 3),
(58, '2026-01-12 05:33:13.000000', 1427000.00, 10, '2026-01-12 05:33:13.000000', 13, 17, 5),
(59, '2026-01-12 05:33:13.000000', 1427000.00, 10, '2026-01-12 05:33:13.000000', 1, 17, 5),
(60, '2026-01-12 05:33:13.000000', 1427000.00, 10, '2026-01-12 05:33:13.000000', 4, 17, 5),
(61, '2026-01-12 05:33:13.000000', 1427000.00, 10, '2026-01-12 05:33:13.000000', 12, 17, 5),
(62, '2026-01-12 05:33:13.000000', 1427000.00, 10, '2026-01-12 05:33:13.000000', 15, 17, 5),
(63, '2026-01-12 05:33:13.000000', 1427000.00, 10, '2026-01-12 05:33:13.000000', 7, 17, 5),
(64, '2026-01-12 05:33:13.000000', 1427000.00, 10, '2026-01-12 05:33:13.000000', 5, 17, 5),
(65, '2026-01-12 05:33:13.000000', 1427000.00, 10, '2026-01-12 05:33:13.000000', 8, 17, 5),
(66, '2026-01-12 05:33:13.000000', 1427000.00, 10, '2026-01-12 05:33:13.000000', 10, 17, 5),
(67, '2026-01-12 05:33:13.000000', 1427000.00, 10, '2026-01-12 05:33:13.000000', 9, 17, 5),
(68, '2026-01-12 05:33:13.000000', 1483000.00, 10, '2026-01-12 05:33:13.000000', 13, 17, 7),
(69, '2026-01-12 05:33:13.000000', 1483000.00, 10, '2026-01-12 05:33:13.000000', 1, 17, 7),
(70, '2026-01-12 05:33:13.000000', 1483000.00, 10, '2026-01-12 05:33:13.000000', 4, 17, 7),
(71, '2026-01-12 05:33:13.000000', 1483000.00, 10, '2026-01-12 05:33:13.000000', 12, 17, 7),
(72, '2026-01-12 05:33:13.000000', 1483000.00, 10, '2026-01-12 05:33:13.000000', 15, 17, 7),
(73, '2026-01-12 05:33:13.000000', 1483000.00, 10, '2026-01-12 05:33:13.000000', 7, 17, 7),
(74, '2026-01-12 05:33:13.000000', 1483000.00, 10, '2026-01-12 05:33:13.000000', 5, 17, 7),
(75, '2026-01-12 05:33:13.000000', 1483000.00, 10, '2026-01-12 05:33:13.000000', 8, 17, 7),
(76, '2026-01-12 05:33:13.000000', 1483000.00, 10, '2026-01-12 05:33:13.000000', 10, 17, 7),
(77, '2026-01-12 05:33:13.000000', 1483000.00, 10, '2026-01-12 05:33:13.000000', 9, 17, 7),
(78, '2026-01-12 05:33:13.000000', 1539000.00, 10, '2026-01-12 05:33:13.000000', 13, 17, 9),
(79, '2026-01-12 05:33:13.000000', 1539000.00, 10, '2026-01-12 05:33:13.000000', 1, 17, 9),
(80, '2026-01-12 05:33:13.000000', 1539000.00, 10, '2026-01-12 05:33:13.000000', 4, 17, 9),
(81, '2026-01-12 05:33:13.000000', 1539000.00, 10, '2026-01-12 05:33:13.000000', 12, 17, 9),
(82, '2026-01-12 05:33:13.000000', 1539000.00, 10, '2026-01-12 05:33:13.000000', 15, 17, 9),
(83, '2026-01-12 05:33:13.000000', 1539000.00, 10, '2026-01-12 05:33:13.000000', 7, 17, 9),
(84, '2026-01-12 05:33:13.000000', 1539000.00, 10, '2026-01-12 05:33:13.000000', 5, 17, 9),
(85, '2026-01-12 05:33:13.000000', 1539000.00, 10, '2026-01-12 05:33:13.000000', 8, 17, 9),
(86, '2026-01-12 05:33:13.000000', 1539000.00, 10, '2026-01-12 05:33:13.000000', 10, 17, 9),
(87, '2026-01-12 05:33:13.000000', 1539000.00, 10, '2026-01-12 05:33:13.000000', 9, 17, 9),
(88, '2026-01-12 05:33:13.000000', 1595000.00, 10, '2026-01-12 05:33:13.000000', 13, 17, 11),
(89, '2026-01-12 05:33:13.000000', 1595000.00, 10, '2026-01-12 05:33:13.000000', 1, 17, 11),
(90, '2026-01-12 05:33:13.000000', 1595000.00, 10, '2026-01-12 05:33:13.000000', 4, 17, 11),
(91, '2026-01-12 05:33:13.000000', 1595000.00, 10, '2026-01-12 05:33:13.000000', 12, 17, 11),
(92, '2026-01-12 05:33:13.000000', 1595000.00, 10, '2026-01-12 05:33:13.000000', 15, 17, 11),
(93, '2026-01-12 05:33:13.000000', 1595000.00, 10, '2026-01-12 05:33:13.000000', 7, 17, 11),
(94, '2026-01-12 05:33:13.000000', 1595000.00, 10, '2026-01-12 05:33:13.000000', 5, 17, 11),
(95, '2026-01-12 05:33:13.000000', 1595000.00, 10, '2026-01-12 05:33:13.000000', 8, 17, 11),
(96, '2026-01-12 05:33:13.000000', 1595000.00, 10, '2026-01-12 05:33:13.000000', 10, 17, 11),
(97, '2026-01-12 05:33:13.000000', 1595000.00, 10, '2026-01-12 05:33:13.000000', 9, 17, 11),
(98, '2026-01-12 05:33:13.000000', 1623000.00, 10, '2026-01-12 05:33:13.000000', 13, 17, 12),
(99, '2026-01-12 05:33:13.000000', 1623000.00, 10, '2026-01-12 05:33:13.000000', 1, 17, 12),
(100, '2026-01-12 05:33:13.000000', 1623000.00, 10, '2026-01-12 05:33:13.000000', 4, 17, 12),
(101, '2026-01-12 05:33:13.000000', 1623000.00, 10, '2026-01-12 05:33:13.000000', 12, 17, 12),
(102, '2026-01-12 05:33:13.000000', 1623000.00, 10, '2026-01-12 05:33:13.000000', 15, 17, 12),
(103, '2026-01-12 05:33:13.000000', 1623000.00, 10, '2026-01-12 05:33:13.000000', 7, 17, 12),
(104, '2026-01-12 05:33:13.000000', 1623000.00, 10, '2026-01-12 05:33:13.000000', 5, 17, 12),
(105, '2026-01-12 05:33:13.000000', 1623000.00, 10, '2026-01-12 05:33:13.000000', 8, 17, 12),
(106, '2026-01-12 05:33:13.000000', 1623000.00, 10, '2026-01-12 05:33:13.000000', 10, 17, 12),
(107, '2026-01-12 05:33:13.000000', 1623000.00, 10, '2026-01-12 05:33:13.000000', 9, 17, 12),
(108, '2026-01-12 05:33:13.000000', 1651000.00, 10, '2026-01-12 05:33:13.000000', 13, 17, 13),
(109, '2026-01-12 05:33:13.000000', 1651000.00, 10, '2026-01-12 05:33:13.000000', 1, 17, 13),
(110, '2026-01-12 05:33:13.000000', 1651000.00, 10, '2026-01-12 05:33:13.000000', 4, 17, 13),
(111, '2026-01-12 05:33:13.000000', 1651000.00, 10, '2026-01-12 05:33:13.000000', 12, 17, 13),
(112, '2026-01-12 05:33:13.000000', 1651000.00, 10, '2026-01-12 05:33:13.000000', 15, 17, 13),
(113, '2026-01-12 05:33:13.000000', 1651000.00, 10, '2026-01-12 05:33:13.000000', 7, 17, 13),
(114, '2026-01-12 05:33:13.000000', 1651000.00, 10, '2026-01-12 05:33:13.000000', 5, 17, 13),
(115, '2026-01-12 05:33:13.000000', 1651000.00, 10, '2026-01-12 05:33:13.000000', 8, 17, 13),
(116, '2026-01-12 05:33:13.000000', 1651000.00, 10, '2026-01-12 05:33:13.000000', 10, 17, 13),
(117, '2026-01-12 05:33:13.000000', 1651000.00, 10, '2026-01-12 05:33:13.000000', 9, 17, 13),
(118, '2026-01-12 05:33:13.000000', 1679000.00, 10, '2026-01-12 05:33:13.000000', 13, 17, 14),
(119, '2026-01-12 05:33:13.000000', 1679000.00, 10, '2026-01-12 05:33:13.000000', 1, 17, 14),
(120, '2026-01-12 05:33:13.000000', 1679000.00, 10, '2026-01-12 05:33:13.000000', 4, 17, 14),
(121, '2026-01-12 05:33:13.000000', 1679000.00, 10, '2026-01-12 05:33:13.000000', 12, 17, 14),
(122, '2026-01-12 05:33:13.000000', 1679000.00, 10, '2026-01-12 05:33:13.000000', 15, 17, 14),
(123, '2026-01-12 05:33:13.000000', 1679000.00, 10, '2026-01-12 05:33:13.000000', 7, 17, 14),
(124, '2026-01-12 05:33:13.000000', 1679000.00, 10, '2026-01-12 05:33:13.000000', 5, 17, 14),
(125, '2026-01-12 05:33:13.000000', 1679000.00, 10, '2026-01-12 05:33:13.000000', 8, 17, 14),
(126, '2026-01-12 05:33:13.000000', 1679000.00, 10, '2026-01-12 05:33:13.000000', 10, 17, 14),
(127, '2026-01-12 05:33:13.000000', 1679000.00, 10, '2026-01-12 05:33:13.000000', 9, 17, 14),
(128, '2025-12-11 00:14:59.000000', 4750000.00, 50, '2025-12-11 00:14:59.000000', 1, 18, 7),
(129, '2025-12-11 00:14:59.000000', 4750000.00, 50, '2025-12-11 00:14:59.000000', 5, 19, 7),
(130, '2025-12-11 00:14:59.000000', 4750000.00, 50, '2025-12-11 00:14:59.000000', 14, 20, 7),
(131, '2025-12-11 00:14:59.000000', 4750000.00, 50, '2025-12-11 00:14:59.000000', 4, 21, 7),
(132, '2025-12-11 00:14:59.000000', 4750000.00, 50, '2025-12-11 00:14:59.000000', 3, 22, 7),
(133, '2025-12-11 00:14:59.000000', 3875000.00, 50, '2025-12-11 00:14:59.000000', 1, 23, 7),
(134, '2025-12-11 00:14:59.000000', 3875000.00, 50, '2025-12-11 00:14:59.000000', 2, 24, 7),
(135, '2025-12-11 00:14:59.000000', 11250000.00, 50, '2025-12-11 00:14:59.000000', 3, 25, 7),
(136, '2025-12-11 00:14:59.000000', 11250000.00, 50, '2025-12-11 00:14:59.000000', 3, 26, 7),
(137, '2025-12-11 00:14:59.000000', 11525000.00, 50, '2025-12-11 00:14:59.000000', 7, 27, 7),
(138, '2026-02-01 18:36:32.000000', 3250000.00, 50, '2026-02-01 18:36:32.000000', 1, 28, 7),
(139, '2026-02-01 18:36:32.000000', 3250000.00, 50, '2026-02-01 18:36:32.000000', 2, 29, 7),
(140, '2026-02-01 18:36:32.000000', 5250000.00, 50, '2026-02-01 18:36:32.000000', 5, 30, 7),
(141, '2026-02-01 18:36:32.000000', 4000000.00, 50, '2026-02-01 18:36:32.000000', 1, 31, 7),
(142, '2026-02-01 18:36:32.000000', 4000000.00, 50, '2026-02-01 18:36:32.000000', 13, 32, 7),
(143, '2026-02-01 18:36:32.000000', 4000000.00, 50, '2026-02-01 18:36:32.000000', 14, 33, 7),
(144, '2026-02-01 18:36:32.000000', 5500000.00, 50, '2026-02-01 18:36:32.000000', 6, 34, 7),
(145, '2026-02-01 18:36:32.000000', 5500000.00, 50, '2026-02-01 18:36:32.000000', 14, 35, 7),
(146, '2026-02-01 18:36:32.000000', 5500000.00, 50, '2026-02-01 18:36:32.000000', 5, 36, 7),
(147, '2026-02-01 18:36:32.000000', 1000000.00, 50, '2026-02-01 18:36:32.000000', 1, 37, 7);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_variant_images`
--

CREATE TABLE `product_variant_images` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `variant_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product_variant_images`
--

INSERT INTO `product_variant_images` (`id`, `created_at`, `image_url`, `updated_at`, `variant_id`) VALUES
(389, '2025-12-11 21:47:45.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1765488300/sneaker/15/images/sneaker/15/images/c28c2925-4ba8-41a3-a7c0-dd90f1d46c62.png', '2025-12-11 21:47:45.000000', 3),
(390, '2025-12-11 21:48:39.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1765488300/sneaker/15/images/sneaker/15/images/c28c2925-4ba8-41a3-a7c0-dd90f1d46c62.png', '2025-12-11 21:48:39.000000', 2),
(391, '2025-12-11 21:50:50.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/045/793/514/original/CZ2975_001.png.png', '2025-12-11 21:50:50.000000', 1),
(400, '2025-12-11 21:56:19.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/038/918/761/original/EG5933.png.png', '2025-12-11 21:56:19.000000', 11),
(401, '2025-12-11 21:56:22.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/038/918/761/original/EG5933.png.png', '2025-12-11 21:56:22.000000', 10),
(402, '2025-12-11 21:57:15.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/109/440/400/original/394873_02.png.png', '2025-12-11 21:57:15.000000', 12),
(403, '2025-12-11 21:57:18.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/109/440/400/original/394873_02.png.png', '2025-12-11 21:57:18.000000', 13),
(406, '2025-12-11 21:59:05.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/042/787/341/original/628892_00.png.png', '2025-12-11 21:59:05.000000', 17),
(407, '2025-12-11 21:59:06.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/042/787/341/original/628892_00.png.png', '2025-12-11 21:59:06.000000', 16),
(408, '2025-12-11 22:00:44.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/095/740/981/original/VN0A7Q2J6UR.png.png', '2025-12-11 22:00:44.000000', 18),
(409, '2025-12-11 22:00:46.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/095/740/981/original/VN0A7Q2J6UR.png.png', '2025-12-11 22:00:46.000000', 19),
(410, '2025-12-11 22:01:49.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/071/724/031/original/808985_00.png.png', '2025-12-11 22:01:49.000000', 25),
(411, '2025-12-11 22:01:50.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/071/724/031/original/808985_00.png.png', '2025-12-11 22:01:50.000000', 24),
(412, '2026-01-12 04:56:30.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768193774/sneaker/15/images/sneaker/15/images/e35c0119-4b4a-4619-b20b-d4a414bf230d.jpg', '2026-01-12 04:56:30.000000', 26),
(413, '2026-01-12 04:56:30.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768193774/sneaker/15/images/sneaker/15/images/e35c0119-4b4a-4619-b20b-d4a414bf230d.jpg', '2026-01-12 04:56:30.000000', 27),
(414, '2026-01-12 05:33:12.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:12.000000', 28),
(415, '2026-01-12 05:33:12.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:12.000000', 29),
(416, '2026-01-12 05:33:12.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:12.000000', 30),
(417, '2026-01-12 05:33:12.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:12.000000', 31),
(418, '2026-01-12 05:33:12.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:12.000000', 32),
(419, '2026-01-12 05:33:12.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:12.000000', 33),
(420, '2026-01-12 05:33:12.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:12.000000', 34),
(421, '2026-01-12 05:33:12.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:12.000000', 35),
(422, '2026-01-12 05:33:12.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:12.000000', 36),
(423, '2026-01-12 05:33:12.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:12.000000', 37),
(424, '2026-01-12 05:33:12.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:12.000000', 38),
(425, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 39),
(426, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 40),
(427, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 41),
(428, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 42),
(429, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 43),
(430, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 44),
(431, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 45),
(432, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 46),
(433, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 47),
(434, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 48),
(435, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 49),
(436, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 50),
(437, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 51),
(438, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 52),
(439, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 53),
(440, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 54),
(441, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 55),
(442, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 56),
(443, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 57),
(444, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 58),
(445, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 59),
(446, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 60),
(447, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 61),
(448, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 62),
(449, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 63),
(450, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 64),
(451, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 65),
(452, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 66),
(453, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 67),
(454, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 68),
(455, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 69),
(456, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 70),
(457, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 71),
(458, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 72),
(459, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 73),
(460, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 74),
(461, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 75),
(462, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 76),
(463, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 77),
(464, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 78),
(465, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 79),
(466, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 80),
(467, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 81),
(468, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 82),
(469, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 83),
(470, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 84),
(471, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 85),
(472, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 86),
(473, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 87),
(474, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 88),
(475, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 89),
(476, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 90),
(477, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 91),
(478, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 92),
(479, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 93),
(480, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 94),
(481, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 95),
(482, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 96),
(483, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 97),
(484, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 98),
(485, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 99),
(486, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 100),
(487, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 101),
(488, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 102),
(489, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 103),
(490, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 104),
(491, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 105),
(492, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 106),
(493, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 107),
(494, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 108),
(495, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 109),
(496, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 110),
(497, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 111),
(498, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 112),
(499, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 113),
(500, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 114),
(501, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 115),
(502, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 116),
(503, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 117),
(504, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 118),
(505, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 119),
(506, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 120),
(507, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 121),
(508, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 122),
(509, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 123),
(510, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 124),
(511, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 125),
(512, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 126),
(513, '2026-01-12 05:33:13.000000', 'https://res.cloudinary.com/drqbhj6ft/image/upload/v1768195977/sneaker/15/images/sneaker/15/images/37f51140-56d4-4624-8e75-ecec6ed0dd62.jpg', '2026-01-12 05:33:13.000000', 127),
(514, '2025-12-11 00:14:59.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/000/032/117/original/344646_001.png.png', '2025-12-11 00:14:59.000000', 128),
(515, '2025-12-11 00:14:59.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/000/032/118/original/344646_002.png.png', '2025-12-11 00:14:59.000000', 129),
(516, '2025-12-11 00:14:59.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/100/579/287/original/21956_00.png.png', '2025-12-11 00:14:59.000000', 130),
(517, '2025-12-11 00:14:59.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/098/477/735/original/21878_00.png.png', '2025-12-11 00:14:59.000000', 131),
(518, '2025-12-11 00:14:59.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/000/032/122/original/344646_600.png.png', '2025-12-11 00:14:59.000000', 132),
(519, '2025-12-11 00:14:59.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/096/779/934/original/1328101_00.png.png', '2025-12-11 00:14:59.000000', 133),
(520, '2025-12-11 00:14:59.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/096/910/209/original/1328103_00.png.png', '2025-12-11 00:14:59.000000', 134),
(521, '2025-12-11 00:14:59.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/089/001/207/original/CQ4018_004.png.png', '2025-12-11 00:14:59.000000', 135),
(522, '2025-12-11 00:14:59.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/062/778/469/original/CQ4018_601.png.png', '2025-12-11 00:14:59.000000', 136),
(523, '2025-12-11 00:14:59.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/064/337/111/original/766833_00.png.png', '2025-12-11 00:14:59.000000', 137),
(524, '2026-02-01 18:36:32.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/045/246/473/original/FX3495.png.png', '2026-02-01 18:36:32.000000', 138),
(525, '2026-02-01 18:36:32.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/045/246/474/original/FX3496.png.png', '2026-02-01 18:36:32.000000', 139),
(526, '2026-02-01 18:36:32.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/038/918/761/original/EG5933.png.png', '2026-02-01 18:36:32.000000', 140),
(527, '2026-02-01 18:36:32.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/062/137/377/original/764365_00.png.png', '2026-02-01 18:36:32.000000', 141),
(528, '2026-02-01 18:36:32.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/058/628/967/original/764367_00.png.png', '2026-02-01 18:36:32.000000', 142),
(529, '2026-02-01 18:36:32.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/073/409/788/original/815469_00.png.png', '2026-02-01 18:36:32.000000', 143),
(530, '2026-02-01 18:36:32.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/076/863/232/original/764368_00.png.png', '2026-02-01 18:36:32.000000', 144),
(531, '2026-02-01 18:36:32.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/070/626/191/original/815471_00.png.png', '2026-02-01 18:36:32.000000', 145),
(532, '2026-02-01 18:36:32.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/064/377/205/original/764369_00.png.png', '2026-02-01 18:36:32.000000', 146),
(533, '2026-02-01 18:36:32.000000', 'https://image.goat.com/750/attachments/product_template_pictures/images/109/968/339/original/JR9152.png.png', '2026-02-01 18:36:32.000000', 147);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotions`
--

CREATE TABLE `promotions` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `description` text DEFAULT NULL,
  `discount_percent` decimal(5,2) NOT NULL,
  `end_date` datetime(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `updated_at` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `promotions`
--

INSERT INTO `promotions` (`id`, `created_at`, `description`, `discount_percent`, `end_date`, `name`, `start_date`, `status`, `updated_at`) VALUES
(1, '2025-12-11 00:15:00.000000', 'Khuyến mãi mùa hè với giảm giá lớn cho tất cả sản phẩm', 20.00, '2024-08-31 23:59:59.000000', 'Summer Sale 2024', '2024-06-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(2, '2025-12-11 00:15:00.000000', 'Chương trình khuyến mãi cho học sinh, sinh viên', 15.00, '2024-09-30 23:59:59.000000', 'Back to School', '2024-08-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(3, '2025-12-11 00:15:00.000000', 'Siêu sale Black Friday với giảm giá lên đến 50%', 50.00, '2024-11-30 23:59:59.000000', 'Black Friday', '2024-11-25 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(4, '2025-12-11 00:15:00.000000', 'Khuyến mãi năm mới với nhiều ưu đãi hấp dẫn', 25.00, '2025-01-10 23:59:59.000000', 'New Year Sale', '2024-12-20 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(5, '2025-12-11 00:15:00.000000', 'Giảm giá đặc biệt cho bộ sưu tập Nike', 30.00, '2024-12-31 23:59:59.000000', 'Nike Collection Sale', '2024-01-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(6, '2025-12-11 00:15:00.000000', 'Flash sale cho sản phẩm Adidas', 25.00, '2024-12-31 23:59:59.000000', 'Adidas Flash Sale', '2024-01-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(7, '2025-12-11 00:15:00.000000', 'Khuyến mãi cuối tuần cho tất cả sản phẩm', 10.00, '2024-12-31 23:59:59.000000', 'Weekend Special', '2024-01-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(8, '2025-12-11 00:15:00.000000', 'Giảm giá giày chạy bộ', 20.00, '2024-12-31 23:59:59.000000', 'Running Shoes Promotion', '2024-01-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(9, '2025-12-11 00:15:00.000000', 'Khuyến mãi giày bóng rổ', 15.00, '2024-12-31 23:59:59.000000', 'Basketball Shoes Sale', '2024-01-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(10, '2025-12-11 00:15:00.000000', 'Giảm giá bộ sưu tập lifestyle', 12.00, '2024-12-31 23:59:59.000000', 'Lifestyle Collection', '2024-01-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(11, '2025-12-11 00:15:00.000000', 'Khuyến mãi giữa năm', 18.00, '2024-07-15 23:59:59.000000', 'Mid-Year Sale', '2024-06-15 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(12, '2025-12-11 00:15:00.000000', 'Giảm giá cuối mùa', 35.00, '2024-02-29 23:59:59.000000', 'End of Season', '2024-02-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(13, '2025-12-11 00:15:00.000000', 'Khuyến mãi bộ sưu tập mùa xuân', 22.00, '2024-05-31 23:59:59.000000', 'Spring Collection', '2024-03-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(14, '2025-12-11 00:15:00.000000', 'Khuyến mãi mùa đông', 20.00, '2024-12-31 23:59:59.000000', 'Winter Warm Up', '2024-12-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000'),
(15, '2025-12-11 00:15:00.000000', 'Thanh lý siêu lớn', 40.00, '2024-12-31 23:59:59.000000', 'Mega Clearance', '2024-01-01 00:00:00.000000', 'ACTIVE', '2025-12-11 00:15:00.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotion_products`
--

CREATE TABLE `promotion_products` (
  `promotion_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `promotion_products`
--

INSERT INTO `promotion_products` (`promotion_id`, `product_id`) VALUES
(1, 1),
(1, 4),
(1, 5),
(2, 7),
(2, 8),
(3, 1),
(4, 11),
(5, 1),
(6, 4),
(7, 1),
(7, 4),
(7, 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `returns`
--

CREATE TABLE `returns` (
  `id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `items` text DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `status` enum('CHO_XU_LY','DA_HOAN_TIEN','DA_HUY') NOT NULL,
  `total_refund` double NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `original_order_id` int(11) NOT NULL,
  `staff_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `returns`
--

INSERT INTO `returns` (`id`, `code`, `created_at`, `items`, `note`, `reason`, `status`, `total_refund`, `updated_at`, `customer_id`, `original_order_id`, `staff_id`) VALUES
(1, 'RET001', '2025-12-11 00:15:00.000000', '[{\"variantId\": 10, \"quantity\": 1, \"price\": 2200000.00}]', 'Khách hàng yêu cầu đổi size', 'Sản phẩm không đúng kích thước', 'CHO_XU_LY', 2200000, '2025-12-11 00:15:00.000000', 6, 4, 3),
(2, 'RET002', '2025-12-11 00:15:00.000000', '[{\"variantId\": 7, \"quantity\": 1, \"price\": 2800000.00}]', 'Giày bị trầy xước', 'Sản phẩm bị lỗi', 'DA_HOAN_TIEN', 2800000, '2025-12-11 00:15:00.000000', 9, 7, 14),
(3, 'RET003', '2025-12-11 00:15:00.000000', '[{\"variantId\": 23, \"quantity\": 1, \"price\": 4500000.00}]', 'Size không phù hợp', 'Không vừa', 'CHO_XU_LY', 4500000, '2025-12-11 00:15:00.000000', 11, 9, NULL),
(4, 'RET004', '2025-12-11 00:15:00.000000', '[{\"variantId\": 22, \"quantity\": 1, \"price\": 2100000.00}]', 'Màu sắc không đúng', 'Sản phẩm không đúng mô tả', 'DA_HOAN_TIEN', 2100000, '2025-12-11 00:15:00.000000', 13, 11, 3),
(5, 'RET005', '2025-12-11 00:15:00.000000', '[{\"variantId\": 12, \"quantity\": 1, \"price\": 1800000.00}]', 'Chất lượng không như mong đợi', 'Không hài lòng', 'CHO_XU_LY', 1800000, '2025-12-11 00:15:00.000000', 4, 13, 14),
(6, 'RET006', '2025-12-11 00:15:00.000000', '[{\"variantId\": 20, \"quantity\": 1, \"price\": 3000000.00}]', 'Không muốn mua nữa', 'Đổi ý', 'DA_HUY', 3000000, '2025-12-11 00:15:00.000000', 7, 5, NULL),
(7, 'RET007', '2025-12-11 00:15:00.000000', '[{\"variantId\": 1, \"quantity\": 1, \"price\": 2500000.00}]', 'Giày bị nứt đế', 'Sản phẩm bị hỏng', 'DA_HOAN_TIEN', 2250000, '2025-12-11 00:15:00.000000', 2, 1, 3),
(8, 'RET008', '2025-12-11 00:15:00.000000', '[{\"variantId\": 14, \"quantity\": 1, \"price\": 2400000.00}]', 'Size quá nhỏ', 'Không vừa chân', 'CHO_XU_LY', 2400000, '2025-12-11 00:15:00.000000', 10, 8, 14),
(9, 'RET009', '2025-12-11 00:15:00.000000', '[{\"variantId\": 20, \"quantity\": 1, \"price\": 3000000.00}]', 'Có vết bẩn trên giày', 'Sản phẩm lỗi', 'CHO_XU_LY', 2800000, '2025-12-11 00:15:00.000000', 2, 12, NULL),
(10, 'RET010', '2025-12-11 00:15:00.000000', '[{\"variantId\": 10, \"quantity\": 1, \"price\": 2200000.00}]', 'Muốn đổi sang màu khác', 'Đổi màu', 'DA_HOAN_TIEN', 2200000, '2025-12-11 00:15:00.000000', 6, 4, 3),
(11, 'RET011', '2025-12-11 00:15:00.000000', '[{\"variantId\": 8, \"quantity\": 1, \"price\": 2800000.00}]', 'Kiểu dáng không hợp', 'Không phù hợp', 'CHO_XU_LY', 2800000, '2025-12-11 00:15:00.000000', 9, 7, 14),
(12, 'RET012', '2025-12-11 00:15:00.000000', '[{\"variantId\": 22, \"quantity\": 1, \"price\": 2100000.00}]', 'Đường chỉ bị lỗi', 'Sản phẩm lỗi', 'DA_HOAN_TIEN', 2100000, '2025-12-11 00:15:00.000000', 13, 11, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sizes`
--

CREATE TABLE `sizes` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sizes`
--

INSERT INTO `sizes` (`id`, `created_at`, `status`, `updated_at`, `value`) VALUES
(1, '2025-12-11 00:14:59.000000', 'ACTIVE', '2025-12-11 00:14:59.000000', 35),
(3, '2025-12-11 00:14:59.000000', 'ACTIVE', '2025-12-11 00:14:59.000000', 37),
(5, '2025-12-11 00:14:59.000000', 'ACTIVE', '2025-12-11 00:14:59.000000', 39),
(7, '2025-12-11 00:14:59.000000', 'ACTIVE', '2025-12-11 00:14:59.000000', 41),
(9, '2025-12-11 00:14:59.000000', 'ACTIVE', '2025-12-11 00:14:59.000000', 43),
(11, '2025-12-11 00:14:59.000000', 'ACTIVE', '2025-12-11 00:14:59.000000', 45),
(12, '2025-12-11 00:14:59.000000', 'ACTIVE', '2025-12-11 00:14:59.000000', 46),
(13, '2025-12-11 00:14:59.000000', 'ACTIVE', '2025-12-11 00:14:59.000000', 47),
(14, '2025-12-11 00:14:59.000000', 'ACTIVE', '2025-12-11 00:14:59.000000', 48),
(15, '2025-12-11 00:14:59.000000', 'ACTIVE', '2025-12-11 00:14:59.000000', 49),
(17, '2026-01-12 04:40:26.000000', 'ACTIVE', '2026-01-12 04:40:26.000000', 34);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vouchers`
--

CREATE TABLE `vouchers` (
  `id` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `end_date` datetime(6) NOT NULL,
  `max_discount` decimal(10,2) DEFAULT NULL,
  `min_order_value` decimal(10,2) NOT NULL,
  `name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  `type` enum('PERCENTAGE','FIXED_AMOUNT') NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `used_count` int(11) NOT NULL,
  `value` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `vouchers`
--

INSERT INTO `vouchers` (`id`, `code`, `created_at`, `end_date`, `max_discount`, `min_order_value`, `name`, `quantity`, `start_date`, `status`, `type`, `updated_at`, `used_count`, `value`) VALUES
(1, 'VOUCHER10', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', 200000.00, 500000.00, 'Giảm 10%', 100, '2024-12-31 17:00:00.000000', 'ACTIVE', 'PERCENTAGE', '2026-01-11 22:57:53.000000', 5, 10.00),
(2, 'VOUCHER20', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', 500000.00, 1000000.00, 'Giảm 20%', 50, '2024-12-31 17:00:00.000000', 'ACTIVE', 'PERCENTAGE', '2026-01-11 22:57:53.000000', 2, 20.00),
(3, 'VOUCHER50K', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', NULL, 300000.00, 'Giảm 50.000đ', 200, '2024-12-31 17:00:00.000000', 'ACTIVE', 'FIXED_AMOUNT', '2026-01-11 22:57:53.000000', 10, 50000.00),
(4, 'VOUCHER100K', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', NULL, 500000.00, 'Giảm 100.000đ', 100, '2024-12-31 17:00:00.000000', 'ACTIVE', 'FIXED_AMOUNT', '2026-01-11 22:57:53.000000', 3, 100000.00),
(5, 'VOUCHER200K', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', NULL, 1000000.00, 'Giảm 200.000đ', 50, '2024-12-31 17:00:00.000000', 'ACTIVE', 'FIXED_AMOUNT', '2026-01-11 22:57:53.000000', 1, 200000.00),
(6, 'NEWUSER', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', 150000.00, 200000.00, 'Giảm 15% cho khách hàng mới', 500, '2024-12-31 17:00:00.000000', 'ACTIVE', 'PERCENTAGE', '2026-01-11 22:57:53.000000', 25, 15.00),
(7, 'SUMMER2024', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', 400000.00, 800000.00, 'Giảm 25% mùa hè', 30, '2024-12-31 17:00:00.000000', 'ACTIVE', 'PERCENTAGE', '2026-01-11 22:57:53.000000', 0, 25.00),
(8, 'VIP50K', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', NULL, 200000.00, 'Voucher VIP 50K', 100, '2024-12-31 17:00:00.000000', 'ACTIVE', 'FIXED_AMOUNT', '2026-01-11 22:57:53.000000', 5, 50000.00),
(9, 'VIP100K', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', NULL, 500000.00, 'Voucher VIP 100K', 50, '2024-12-31 17:00:00.000000', 'ACTIVE', 'FIXED_AMOUNT', '2026-01-11 22:57:53.000000', 2, 100000.00),
(10, 'WEEKEND10', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', 100000.00, 300000.00, 'Giảm 10% cuối tuần', 200, '2024-12-31 17:00:00.000000', 'ACTIVE', 'PERCENTAGE', '2026-01-11 22:57:53.000000', 15, 10.00),
(11, 'BIRTHDAY20', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', 200000.00, 400000.00, 'Giảm 20% sinh nhật', 1000, '2024-12-31 17:00:00.000000', 'ACTIVE', 'PERCENTAGE', '2026-01-11 22:57:53.000000', 50, 20.00),
(12, 'FLASH30', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', 600000.00, 1500000.00, 'Flash Sale 30%', 20, '2024-12-31 17:00:00.000000', 'ACTIVE', 'PERCENTAGE', '2026-01-11 22:57:53.000000', 0, 30.00),
(13, 'FREESHIP', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', NULL, 200000.00, 'Miễn phí ship', 500, '2024-12-31 17:00:00.000000', 'ACTIVE', 'FIXED_AMOUNT', '2026-01-11 22:57:53.000000', 30, 30000.00),
(14, 'MEGA50', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', 1000000.00, 2000000.00, 'Mega Sale 50%', 10, '2024-12-31 17:00:00.000000', 'ACTIVE', 'PERCENTAGE', '2026-01-11 22:57:53.000000', 0, 50.00),
(15, 'WELCOME15', '2025-12-11 00:15:00.000000', '2026-12-31 16:59:59.000000', 150000.00, 300000.00, 'Chào mừng 15%', 1000, '2024-12-31 17:00:00.000000', 'ACTIVE', 'PERCENTAGE', '2026-01-11 22:57:53.000000', 100, 15.00);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_p2jd4db8821l8voctujboa9oh` (`code`);

--
-- Chỉ mục cho bảng `account_addresses`
--
ALTER TABLE `account_addresses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcnpkm7oht9jx0497sb282y36m` (`account_id`);

--
-- Chỉ mục cho bảng `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_oce3937d2f4mpfqrycbr0l93m` (`name`);

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_t8o6pivur7nn124jehx7cygw5` (`name`);

--
-- Chỉ mục cho bảng `chatbot_configs`
--
ALTER TABLE `chatbot_configs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ipl7l2cowcowrboto6pkr3h19` (`config_key`);

--
-- Chỉ mục cho bảng `chatbot_trainings`
--
ALTER TABLE `chatbot_trainings`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `chat_histories`
--
ALTER TABLE `chat_histories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpw6krviu4uq0t8xngj6ujtdb6` (`account_id`);

--
-- Chỉ mục cho bảng `colors`
--
ALTER TABLE `colors`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `materials`
--
ALTER TABLE `materials`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_p6b0ef7tpc5wohnygnl60qk4j` (`name`);

--
-- Chỉ mục cho bảng `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqye0axqv18a6egslmba3ljtcm` (`account_id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_gt3o4a5bqj59e9y6wakgk926t` (`code`),
  ADD KEY `FKtf8r4umpuwx9bgm7gxcpwf263` (`customer_id`),
  ADD KEY `FK4am13nk87535t4xnaj01bbni1` (`staff_id`),
  ADD KEY `FKdimvsocblb17f45ikjr6xn1wj` (`voucher_id`);

--
-- Chỉ mục cho bảng `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  ADD KEY `FKemq71edpbn9wsxnxncfn1algp` (`variant_id`);

--
-- Chỉ mục cho bảng `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK81gagumt0r8y3rmudcgpbk42l` (`order_id`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_57ivhy5aj3qfmdvl6vxdfjs4p` (`code`),
  ADD KEY `FKa3a4mpsfdf4d2y6r8ra3sc8mv` (`brand_id`),
  ADD KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  ADD KEY `FKj2d4f35svu15l83nru8t5k593` (`material_id`);

--
-- Chỉ mục cho bảng `product_variants`
--
ALTER TABLE `product_variants`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKhphqojv21cy7l87vdxpi574bt` (`product_id`,`color_id`,`size_id`),
  ADD KEY `FKnps1p21p470pq59fdj0ddwnrs` (`color_id`),
  ADD KEY `FKt7j608wes333gojuoh0f8l488` (`size_id`);

--
-- Chỉ mục cho bảng `product_variant_images`
--
ALTER TABLE `product_variant_images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9jwla8t7mpnllbt5gp0le008i` (`variant_id`);

--
-- Chỉ mục cho bảng `promotions`
--
ALTER TABLE `promotions`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `promotion_products`
--
ALTER TABLE `promotion_products`
  ADD KEY `FK9rm5m4rnoamh56kxetmoe1kk9` (`product_id`),
  ADD KEY `FKkn7hllhf1o8jjrolro4rqmxt7` (`promotion_id`);

--
-- Chỉ mục cho bảng `returns`
--
ALTER TABLE `returns`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6e8pns0utjpjxdd17t4mtppvy` (`code`),
  ADD KEY `FKq2p3e71wrkcwktfnpltn51sn4` (`customer_id`),
  ADD KEY `FK5fbkvnn3hucvo9qfmefgwt3do` (`original_order_id`),
  ADD KEY `FKq0t42xwj2kpxccbdse5fvya11` (`staff_id`);

--
-- Chỉ mục cho bảng `sizes`
--
ALTER TABLE `sizes`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `vouchers`
--
ALTER TABLE `vouchers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_30ftp2biebbvpik8e49wlmady` (`code`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `account_addresses`
--
ALTER TABLE `account_addresses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `brands`
--
ALTER TABLE `brands`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `chatbot_configs`
--
ALTER TABLE `chatbot_configs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `chatbot_trainings`
--
ALTER TABLE `chatbot_trainings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `chat_histories`
--
ALTER TABLE `chat_histories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `colors`
--
ALTER TABLE `colors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `materials`
--
ALTER TABLE `materials`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT cho bảng `payments`
--
ALTER TABLE `payments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT cho bảng `product_variants`
--
ALTER TABLE `product_variants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=148;

--
-- AUTO_INCREMENT cho bảng `product_variant_images`
--
ALTER TABLE `product_variant_images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=534;

--
-- AUTO_INCREMENT cho bảng `promotions`
--
ALTER TABLE `promotions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `returns`
--
ALTER TABLE `returns`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `sizes`
--
ALTER TABLE `sizes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `vouchers`
--
ALTER TABLE `vouchers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account_addresses`
--
ALTER TABLE `account_addresses`
  ADD CONSTRAINT `FKcnpkm7oht9jx0497sb282y36m` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

--
-- Các ràng buộc cho bảng `chat_histories`
--
ALTER TABLE `chat_histories`
  ADD CONSTRAINT `FKpw6krviu4uq0t8xngj6ujtdb6` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

--
-- Các ràng buộc cho bảng `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `FKqye0axqv18a6egslmba3ljtcm` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK4am13nk87535t4xnaj01bbni1` FOREIGN KEY (`staff_id`) REFERENCES `accounts` (`id`),
  ADD CONSTRAINT `FKdimvsocblb17f45ikjr6xn1wj` FOREIGN KEY (`voucher_id`) REFERENCES `vouchers` (`id`),
  ADD CONSTRAINT `FKtf8r4umpuwx9bgm7gxcpwf263` FOREIGN KEY (`customer_id`) REFERENCES `accounts` (`id`);

--
-- Các ràng buộc cho bảng `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKemq71edpbn9wsxnxncfn1algp` FOREIGN KEY (`variant_id`) REFERENCES `product_variants` (`id`);

--
-- Các ràng buộc cho bảng `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `FK81gagumt0r8y3rmudcgpbk42l` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKa3a4mpsfdf4d2y6r8ra3sc8mv` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`),
  ADD CONSTRAINT `FKj2d4f35svu15l83nru8t5k593` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`),
  ADD CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Các ràng buộc cho bảng `product_variants`
--
ALTER TABLE `product_variants`
  ADD CONSTRAINT `FKnps1p21p470pq59fdj0ddwnrs` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`),
  ADD CONSTRAINT `FKosqitn4s405cynmhb87lkvuau` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKt7j608wes333gojuoh0f8l488` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`);

--
-- Các ràng buộc cho bảng `product_variant_images`
--
ALTER TABLE `product_variant_images`
  ADD CONSTRAINT `FK9jwla8t7mpnllbt5gp0le008i` FOREIGN KEY (`variant_id`) REFERENCES `product_variants` (`id`);

--
-- Các ràng buộc cho bảng `promotion_products`
--
ALTER TABLE `promotion_products`
  ADD CONSTRAINT `FK9rm5m4rnoamh56kxetmoe1kk9` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKkn7hllhf1o8jjrolro4rqmxt7` FOREIGN KEY (`promotion_id`) REFERENCES `promotions` (`id`);

--
-- Các ràng buộc cho bảng `returns`
--
ALTER TABLE `returns`
  ADD CONSTRAINT `FK5fbkvnn3hucvo9qfmefgwt3do` FOREIGN KEY (`original_order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKq0t42xwj2kpxccbdse5fvya11` FOREIGN KEY (`staff_id`) REFERENCES `accounts` (`id`),
  ADD CONSTRAINT `FKq2p3e71wrkcwktfnpltn51sn4` FOREIGN KEY (`customer_id`) REFERENCES `accounts` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
