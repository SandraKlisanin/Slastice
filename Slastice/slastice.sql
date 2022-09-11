-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2022 at 02:40 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `slastice`
--

-- --------------------------------------------------------

--
-- Table structure for table `osoba`
--

CREATE TABLE `osoba` (
  `ID` int(11) NOT NULL,
  `Ime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Prezime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `JMBG` int(50) NOT NULL,
  `Adresa` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Telefon` int(50) NOT NULL,
  `vrsta_korisnika` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Korisnicko_ime` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Lozinka` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `osoba`
--

INSERT INTO `osoba` (`ID`, `Ime`, `Prezime`, `JMBG`, `Adresa`, `Telefon`, `vrsta_korisnika`, `Korisnicko_ime`, `Lozinka`) VALUES
(1, 'admin', 'admin', 1234567891, 'admin', 63123456, 'admin', 'admin', 'admin123'),
(3, 'Sandra', 'Klisanin', 987654321, 'aetga', 63111111, 'korisnik', 'Sandra', 'Sandra123');

-- --------------------------------------------------------

--
-- Table structure for table `recepti`
--

CREATE TABLE `recepti` (
  `ID` int(11) NOT NULL,
  `Naziv` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `Sastojci` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `Priprema` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `ID_Osobe` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `recepti`
--

INSERT INTO `recepti` (`ID`, `Naziv`, `Sastojci`, `Priprema`, `ID_Osobe`) VALUES
(1, 'vbghnm', 'gthjk', 'fghjk', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `osoba`
--
ALTER TABLE `osoba`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `recepti`
--
ALTER TABLE `recepti`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Osobe` (`ID_Osobe`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `osoba`
--
ALTER TABLE `osoba`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `recepti`
--
ALTER TABLE `recepti`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `recepti`
--
ALTER TABLE `recepti`
  ADD CONSTRAINT `recepti_ibfk_1` FOREIGN KEY (`ID_Osobe`) REFERENCES `osoba` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
