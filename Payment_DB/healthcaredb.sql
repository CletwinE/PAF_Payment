-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 06:18 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `healthcaredb`
--

-- --------------------------------------------------------

--
-- Table structure for table `alien`
--

CREATE TABLE `alien` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `points` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alien`
--

INSERT INTO `alien` (`id`, `name`, `points`) VALUES
(102, 'kamal', 55),
(103, 'kamal', 55),
(105, 'kasun', 82),
(107, 'kasun', 82),
(110, 'asiri', 46);

-- --------------------------------------------------------

--
-- Table structure for table `appoinment`
--

CREATE TABLE `appoinment` (
  `ap_id` int(11) NOT NULL,
  `ap_date` varchar(20) DEFAULT NULL,
  `ap_time` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appoinment`
--

INSERT INTO `appoinment` (`ap_id`, `ap_date`, `ap_time`, `id`) VALUES
(1, '2002/12/12', 2, 102),
(1001, '2002/10/2', 10, 110);

-- --------------------------------------------------------

--
-- Table structure for table `doc`
--

CREATE TABLE `doc` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `section` varchar(20) NOT NULL,
  `contact_no` int(11) NOT NULL,
  `email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doc`
--

INSERT INTO `doc` (`id`, `name`, `section`, `contact_no`, `email`) VALUES
(102, 'isuru', 'eye', 78645, 'isuru@gmail'),
(110, 'charitha', 'brain', 786154, 'kamal@gmai');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `PayID` int(11) NOT NULL,
  `cardNo` int(20) NOT NULL,
  `payName` varchar(20) NOT NULL,
  `payDate` date NOT NULL,
  `payAmount` float NOT NULL,
  `DATE` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`PayID`, `cardNo`, `payName`, `payDate`, `payAmount`, `DATE`) VALUES
(43, 42839800, 'Cletwin', '2020-05-06', 5500, '2020-05-06 21:35:50'),
(44, 42865877, 'Dilan.V', '2020-05-01', 2500, '2020-05-06 21:42:07');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `fname` varchar(100) NOT NULL,
  `lname` varchar(100) NOT NULL,
  `telNo` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `nicNo` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uid`, `fname`, `lname`, `telNo`, `email`, `age`, `gender`, `nicNo`, `password`, `username`) VALUES
(101, 'dilan', 'vasandaraj', 5122200, 'dilan@gmail.com', 55, 'male', '454578v', 'dilan123', 'dilan001'),
(110, 'munzir', 'eliyas', 512225397, 'cletwin@gmail.com', 30, 'male', '9696960v', 'dilan123', 'cletwin123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alien`
--
ALTER TABLE `alien`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `appoinment`
--
ALTER TABLE `appoinment`
  ADD PRIMARY KEY (`ap_id`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `doc`
--
ALTER TABLE `doc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PayID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `PayID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=151;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appoinment`
--
ALTER TABLE `appoinment`
  ADD CONSTRAINT `appoinment_ibfk_1` FOREIGN KEY (`id`) REFERENCES `doc` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
