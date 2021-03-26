-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2021 at 11:16 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `karyawan1`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `datadepartment` ()  BEGIN
select department,count(*) as jumlah from worker group by department;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bonus`
--

CREATE TABLE `bonus` (
  `worker_ref_id` int(6) NOT NULL,
  `bonus_date` datetime NOT NULL,
  `bonus_amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bonus`
--

INSERT INTO `bonus` (`worker_ref_id`, `bonus_date`, `bonus_amount`) VALUES
(1, '2021-03-27 13:16:04', 6000),
(2, '2021-04-27 13:16:04', 7000),
(3, '2021-03-27 13:18:04', 8000);

-- --------------------------------------------------------

--
-- Table structure for table `title`
--

CREATE TABLE `title` (
  `worker_ref_id` int(6) NOT NULL,
  `worker_title` varchar(30) NOT NULL,
  `affected_from` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `title`
--

INSERT INTO `title` (`worker_ref_id`, `worker_title`, `affected_from`) VALUES
(1, 'Admin', '2021-04-08 13:14:23'),
(2, 'Manager', '2021-03-26 15:28:19'),
(3, 'Executive', '2021-03-26 15:28:45'),
(4, 'Manager', '2021-03-26 15:28:45'),
(5, 'Lead', '2021-03-26 15:29:40'),
(6, 'Asst.Manager', '2021-03-26 15:29:40'),
(9, 'Lead', '2021-04-08 13:14:23');

-- --------------------------------------------------------

--
-- Table structure for table `worker`
--

CREATE TABLE `worker` (
  `worker_id` int(6) NOT NULL,
  `first_name` varchar(60) NOT NULL,
  `last_name` varchar(60) NOT NULL,
  `salary` double NOT NULL,
  `joining_date` datetime NOT NULL,
  `department` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `worker`
--

INSERT INTO `worker` (`worker_id`, `first_name`, `last_name`, `salary`, `joining_date`, `department`) VALUES
(1, 'Dilana', 'Pritasiaa', 4000000, '2021-03-26 12:09:47', 'Admin'),
(2, 'Nina', 'Zain', 6000000, '2020-04-26 12:09:47', 'HRD'),
(3, 'Vishal', 'Singhal', 7000000, '2021-03-26 13:50:09', 'Admin'),
(4, 'Amith', 'Singh', 7500000, '2021-03-26 13:50:09', 'HRD'),
(6, 'Vipul', 'Diwam', 6700000, '2021-03-26 13:53:53', 'HRD'),
(7, 'Satish', 'Kurma', 7600000, '2021-03-26 13:53:53', 'Account'),
(8, 'Yana', 'Yushisha', 5000000, '2021-03-26 14:13:49', 'Account'),
(9, 'Ardi', 'Masryanto', 5000000, '2021-03-26 14:13:49', 'Account');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bonus`
--
ALTER TABLE `bonus`
  ADD PRIMARY KEY (`worker_ref_id`);

--
-- Indexes for table `title`
--
ALTER TABLE `title`
  ADD PRIMARY KEY (`worker_ref_id`);

--
-- Indexes for table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`worker_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
