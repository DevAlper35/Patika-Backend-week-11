-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 26 Şub 2024, 12:21:17
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `library_db`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `authors`
--

CREATE TABLE `authors` (
  `author_id` bigint UNSIGNED NOT NULL,
  `author_birth_date` date DEFAULT NULL,
  `author_country` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `author_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `authors`
--

INSERT INTO `authors` (`author_id`, `author_birth_date`, `author_country`, `author_name`) VALUES
(1, '1984-02-06', 'Turkey', 'Omer Seyfettin'),
(2, '1995-03-15', 'Turkey', 'Orhan Pamuk'),
(3, '1981-12-06', 'India', 'Paulo Coelho');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `categories`
--

CREATE TABLE `categories` (
  `category_id` bigint UNSIGNED NOT NULL,
  `category_description` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `category_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `categories`
--

INSERT INTO `categories` (`category_id`, `category_description`, `category_name`) VALUES
(1, 'Adventure', 'Adv'),
(2, 'action', 'act');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `publishers`
--

CREATE TABLE `publishers` (
  `publisher_id` bigint UNSIGNED NOT NULL,
  `publisher_address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `publisher_establishment` date DEFAULT NULL,
  `publisher_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `publishers`
--

INSERT INTO `publishers` (`publisher_id`, `publisher_address`, `publisher_establishment`, `publisher_name`) VALUES
(1, 'Izmir', '2024-02-14', 'Halk'),
(2, 'Manisa', '2018-11-12', 'Kirmizi');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `authors`
--
ALTER TABLE `authors`
  ADD PRIMARY KEY (`author_id`),
  ADD UNIQUE KEY `author_id` (`author_id`);

--
-- Tablo için indeksler `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`),
  ADD UNIQUE KEY `category_id` (`category_id`),
  ADD UNIQUE KEY `UK_41g4n0emuvcm3qyf1f6cn43c0` (`category_name`);

--
-- Tablo için indeksler `publishers`
--
ALTER TABLE `publishers`
  ADD PRIMARY KEY (`publisher_id`),
  ADD UNIQUE KEY `publisher_id` (`publisher_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `authors`
--
ALTER TABLE `authors`
  MODIFY `author_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `publishers`
--
ALTER TABLE `publishers`
  MODIFY `publisher_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
