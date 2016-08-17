-- phpMyAdmin SQL Dump
-- version 4.4.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 15, 2015 at 11:16 PM
-- Server version: 5.6.25
-- PHP Version: 5.5.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clinic`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE IF NOT EXISTS `appointment` (
  `id` int(10) unsigned NOT NULL,
  `datetime` datetime DEFAULT NULL,
  `team_id` int(10) unsigned NOT NULL,
  `patient_id` int(10) unsigned NOT NULL,
  `notes` longtext,
  `prescription` longtext
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`id`, `datetime`, `team_id`, `patient_id`, `notes`, `prescription`) VALUES
(1, '2015-12-12 13:30:00', 1, 1, 'some notes ', 'some prescription'),
(2, '2015-12-12 00:00:00', 1, 2, 'other notes', 'other prescription'),
(3, '2015-12-17 00:00:00', 1, 2, 'notes asdfsad', 'prescription lero lero '),
(4, '2015-12-20 00:00:00', 1, 2, 'notes asdfsad\nasdf\nasdf\nasdf\nasasdfasdfas\nasdf\nasdf\nasdf\nasdfasd', 'prescription lero lero \nasdf\nasdf\nasdf\naasdfasdf\nasd\nasdf\nasdf\nasdf\nasd'),
(5, '2015-12-15 00:00:00', 1, 1, 'some notes \nasdf\nasdf\nasdf\nasdf\nasdf\n', 'some prescription\nasd\nfasd\nfasd\nfasdf\nasdf\nasdf\n'),
(6, '2015-12-15 00:00:00', 1, 3, '', ''),
(7, '2015-12-15 00:00:00', 1, 4, '', ''),
(8, '2015-12-15 00:00:00', 1, 5, '', ''),
(9, '2015-12-15 00:00:00', 1, 6, '', ''),
(10, '2015-12-15 00:00:00', 1, 7, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `date_of_birth` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `name`, `address`, `contact_number`, `date_of_birth`) VALUES
(1, 'giba', '4 coolmine', '087', '1980-08-22'),
(2, 'Keilah Soares', 'Perto da Capel St.', '087', '1990-12-08'),
(3, 'simba', 'brazil', '3322', '1990-08-22'),
(4, 'jose aldo', 'brazil', '087', '1990-08-22'),
(5, 'devid gregorio', 'ireland', '087', '1980-08-22'),
(6, 'hatuna matata', 'brazil', '3322', '1990-08-22'),
(7, 'quem sera', 'brazil', '3322', '1990-08-22');

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE IF NOT EXISTS `team` (
  `id` int(11) NOT NULL,
  `reg_number` varchar(50) DEFAULT '0',
  `profile` varchar(20) DEFAULT '0',
  `name` varchar(20) DEFAULT '0',
  `password` varchar(10) DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='Team - Doctor, Secretary, Pharmacist';

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`id`, `reg_number`, `profile`, `name`, `password`) VALUES
(1, '12345D', 'doctor', 'Gilberto Santos', '1234'),
(2, '12345S', 'Secretary', 'Jeally bean', '1234'),
(3, '12345P', 'Pharmacist', 'Jonh Connolly', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
