-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Nov 04, 2015 at 06:43 AM
-- Server version: 5.5.42
-- PHP Version: 5.6.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `muleDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `GAME`
--

CREATE TABLE `GAME` (
  `SAVE` varchar(5) NOT NULL,
  `CURRENT_PLAYER` int(11) NOT NULL,
  `ROUND` int(11) NOT NULL,
  `DIFFICULTY` int(11) NOT NULL,
  `NUM_PLAYERS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `GAME`
--

INSERT INTO `GAME` (`SAVE`, `CURRENT_PLAYER`, `ROUND`, `DIFFICULTY`, `NUM_PLAYERS`) VALUES
('save1', -1, 2, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `PLAYERS`
--

CREATE TABLE `PLAYERS` (
  `SAVE` varchar(5) NOT NULL,
  `NAME` varchar(25) DEFAULT NULL,
  `POSITION` int(11) NOT NULL,
  `RED` double DEFAULT NULL,
  `GREEN` double DEFAULT NULL,
  `BLUE` double DEFAULT NULL,
  `RACE` varchar(25) DEFAULT NULL,
  `MONEYS` int(11) DEFAULT NULL,
  `ORE` int(11) DEFAULT NULL,
  `ENERGY` int(11) DEFAULT NULL,
  `FOOD` int(11) DEFAULT NULL,
  `MULE` int(11) DEFAULT NULL,
  `CRYSTITE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `PLAYERS`
--

INSERT INTO `PLAYERS` (`SAVE`, `NAME`, `POSITION`, `RED`, `GREEN`, `BLUE`, `RACE`, `MONEYS`, `ORE`, `ENERGY`, `FOOD`, `MULE`, `CRYSTITE`) VALUES
('save1', 'Edward', 0, 1, 0, 0, 'BONZOID', 1000, 0, 4, 8, NULL, 0),
('save1', 'Laurel', 1, 0, 0, 1, 'GOLLUMER', 875, 0, 4, 8, NULL, 0),
('save1', 'Chris', 2, 1, 1, 0, 'PACKER', 1000, 0, 4, 8, NULL, 0),
('save1', NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `STORE`
--

CREATE TABLE `STORE` (
  `SAVE` varchar(5) NOT NULL,
  `ORE` int(11) DEFAULT NULL,
  `ENERGY` int(11) DEFAULT NULL,
  `FOOD` int(11) DEFAULT NULL,
  `CRYSTITE` int(11) DEFAULT NULL,
  `MULE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `STORE`
--

INSERT INTO `STORE` (`SAVE`, `ORE`, `ENERGY`, `FOOD`, `CRYSTITE`, `MULE`) VALUES
('save1', 0, 16, 16, 0, 25);

-- --------------------------------------------------------

--
-- Table structure for table `TILES`
--

CREATE TABLE `TILES` (
  `SAVE` varchar(5) NOT NULL,
  `X_POS` int(11) NOT NULL,
  `Y_POS` int(11) NOT NULL,
  `TYPE` varchar(25) DEFAULT NULL,
  `MULE` int(11) DEFAULT NULL,
  `OWNER` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TILES`
--

INSERT INTO `TILES` (`SAVE`, `X_POS`, `Y_POS`, `TYPE`, `MULE`, `OWNER`) VALUES
('save1', 0, 0, NULL, NULL, NULL),
('save1', 0, 1, NULL, NULL, NULL),
('save1', 0, 2, NULL, NULL, NULL),
('save1', 0, 3, NULL, NULL, NULL),
('save1', 0, 4, NULL, NULL, NULL),
('save1', 1, 0, NULL, NULL, NULL),
('save1', 1, 1, NULL, NULL, NULL),
('save1', 1, 2, NULL, NULL, NULL),
('save1', 1, 3, NULL, NULL, NULL),
('save1', 1, 4, NULL, NULL, NULL),
('save1', 2, 0, NULL, NULL, NULL),
('save1', 2, 1, NULL, NULL, NULL),
('save1', 2, 2, NULL, NULL, NULL),
('save1', 2, 3, NULL, NULL, NULL),
('save1', 2, 4, NULL, NULL, NULL),
('save1', 3, 0, NULL, NULL, 0),
('save1', 3, 1, NULL, NULL, 1),
('save1', 3, 2, NULL, NULL, NULL),
('save1', 3, 3, NULL, NULL, NULL),
('save1', 3, 4, NULL, NULL, NULL),
('save1', 4, 0, NULL, 1, 1),
('save1', 4, 1, NULL, NULL, NULL),
('save1', 4, 2, NULL, NULL, NULL),
('save1', 4, 3, NULL, NULL, NULL),
('save1', 4, 4, NULL, NULL, NULL),
('save1', 5, 0, NULL, NULL, 2),
('save1', 5, 1, NULL, NULL, 0),
('save1', 5, 2, NULL, NULL, NULL),
('save1', 5, 3, NULL, NULL, NULL),
('save1', 5, 4, NULL, NULL, NULL),
('save1', 6, 0, NULL, NULL, NULL),
('save1', 6, 1, NULL, NULL, NULL),
('save1', 6, 2, NULL, NULL, NULL),
('save1', 6, 3, NULL, NULL, NULL),
('save1', 6, 4, NULL, NULL, NULL),
('save1', 7, 0, NULL, NULL, NULL),
('save1', 7, 1, NULL, NULL, NULL),
('save1', 7, 2, NULL, NULL, NULL),
('save1', 7, 3, NULL, NULL, NULL),
('save1', 7, 4, NULL, NULL, NULL),
('save1', 8, 0, NULL, NULL, NULL),
('save1', 8, 1, NULL, NULL, NULL),
('save1', 8, 2, NULL, NULL, NULL),
('save1', 8, 3, NULL, NULL, NULL),
('save1', 8, 4, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `GAME`
--
ALTER TABLE `GAME`
  ADD PRIMARY KEY (`SAVE`);

--
-- Indexes for table `PLAYERS`
--
ALTER TABLE `PLAYERS`
  ADD PRIMARY KEY (`SAVE`,`POSITION`);

--
-- Indexes for table `STORE`
--
ALTER TABLE `STORE`
  ADD PRIMARY KEY (`SAVE`);

--
-- Indexes for table `TILES`
--
ALTER TABLE `TILES`
  ADD PRIMARY KEY (`SAVE`,`X_POS`,`Y_POS`);
