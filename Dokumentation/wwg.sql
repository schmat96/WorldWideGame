-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 08. Mai 2018 um 23:39
-- Server-Version: 10.1.31-MariaDB
-- PHP-Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `wwg`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ability`
--

CREATE TABLE `ability` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `art` int(11) NOT NULL,
  `modifier` int(11) NOT NULL,
  `attackPattern_id` int(11) NOT NULL,
  `mana` int(11) NOT NULL,
  `element` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `ability`
--

INSERT INTO `ability` (`id`, `name`, `art`, `modifier`, `attackPattern_id`, `mana`, `element`) VALUES
(1, 'Angriff', 0, 1, 1, 0, 0),
(2, 'Raise', 6, 0, 1, 0, 0),
(3, 'Defense', 1, 0, 1, 0, 0),
(4, 'Divine Ruination', 0, 1, 4, 5, 1),
(6, 'Heilung', 5, 1, 1, 5, 0),
(7, 'Eis', 8, 1, 1, 5, 5),
(8, 'Blitz', 8, 1, 1, 5, 1),
(9, 'Feuer', 8, 1, 1, 5, 2),
(10, 'Wasser', 8, 1, 1, 5, 3),
(11, 'Wind', 8, 1, 1, 5, 4),
(12, 'Dispel', 9, 0, 1, 5, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `attackpattern`
--

CREATE TABLE `attackpattern` (
  `id_attackPattern` int(11) NOT NULL,
  `pattern` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `attackpattern`
--

INSERT INTO `attackpattern` (`id_attackPattern`, `pattern`) VALUES
(1, '1=1'),
(2, '100=0.1;100=.1;100=.1;200=.1;100=.1;100=.1;500=0.5'),
(3, '100=.1;100=.1;100=.1;100=.1;100=.1;100=.1;100=.1;100=.1;100=.1;100=.1'),
(4, '100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05;100=.05');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `besitzt`
--

CREATE TABLE `besitzt` (
  `spieler_id` int(11) NOT NULL,
  `charakter_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `besitzt`
--

INSERT INTO `besitzt` (`spieler_id`, `charakter_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(2, 3),
(2, 6),
(2, 7),
(6, 2),
(6, 3),
(11, 4),
(12, 1),
(12, 2),
(12, 3),
(15, 1),
(15, 2),
(15, 3),
(15, 5),
(16, 3),
(16, 5),
(17, 1),
(17, 2),
(17, 3),
(17, 4),
(17, 5),
(17, 6);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `challenge`
--

CREATE TABLE `challenge` (
  `id_challenge` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `schwierigkeit` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `challenge`
--

INSERT INTO `challenge` (`id_challenge`, `name`, `schwierigkeit`) VALUES
(1, 'Gilgamesh', 1),
(3, 'Mike', 10),
(4, 'Unterwasser auf dem Land', 1),
(6, 'Impossible', 20);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `challenge_turn`
--

CREATE TABLE `challenge_turn` (
  `challenge_id` int(11) NOT NULL,
  `turn_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `challenge_turn`
--

INSERT INTO `challenge_turn` (`challenge_id`, `turn_id`) VALUES
(1, 1),
(3, 1),
(3, 2),
(3, 4),
(4, 5),
(6, 6);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `charakter`
--

CREATE TABLE `charakter` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `universum_id` int(11) NOT NULL,
  `hp` int(11) NOT NULL,
  `def` int(11) NOT NULL,
  `spr` int(11) NOT NULL,
  `mag` int(11) NOT NULL,
  `atk` int(11) NOT NULL,
  `mp` int(11) NOT NULL,
  `threat` int(11) NOT NULL,
  `blitzRes` int(11) NOT NULL,
  `feuerRes` int(11) NOT NULL,
  `wasserRes` int(11) NOT NULL,
  `windRes` int(11) NOT NULL,
  `eisRes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `charakter`
--

INSERT INTO `charakter` (`id`, `name`, `universum_id`, `hp`, `def`, `spr`, `mag`, `atk`, `mp`, `threat`, `blitzRes`, `feuerRes`, `wasserRes`, `windRes`, `eisRes`) VALUES
(1, 'Mario', 1, 100, 0, 10, 10, 30, 20, 0, 0, 0, 0, 0, 0),
(2, 'Elsa', 2, 120, 0, 100, 10, 40, 40, 0, 0, 0, 0, 0, 0),
(3, 'Superman', 3, 200, 0, 10, 10, 2, 10, 0, 0, 0, 0, 0, 0),
(4, 'Luigi', 1, 60, 0, 10, 10, 40, 10, 0, 0, 0, 0, 0, 0),
(5, 'Toad', 1, 20, 0, 10, 10, 50, 10, 0, 0, 0, 0, 0, 0),
(6, 'Wario', 1, 300, 20, 10, 10, 30, 10, 0, 0, 0, 0, 0, 0),
(7, 'Yanick', 4, 100, 10, 20, 10, 30, 10, 0, 300, 0, 0, 0, 0),
(9, 'Kevin', 4, 200, 10, 10, 10, 2, 30, 0, 0, 0, 0, 0, 0),
(10, 'Hulk', 3, 200, 100, 10, 10, 10, 10, 0, 0, 0, 0, 0, 0),
(11, 'Iron Man', 3, 200, 10, 10, 10, 75, 20, 0, 10, 10, 10, 10, 10),
(12, 'Anna', 2, 120, 0, 40, 80, 30, 40, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `char_ability`
--

CREATE TABLE `char_ability` (
  `charakter_id` int(11) NOT NULL,
  `ability_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `char_ability`
--

INSERT INTO `char_ability` (`charakter_id`, `ability_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(2, 3),
(2, 6),
(2, 7),
(3, 1),
(3, 3),
(3, 4),
(4, 1),
(4, 3),
(5, 1),
(5, 3),
(5, 4),
(5, 12),
(6, 1),
(6, 4),
(7, 3),
(7, 6),
(9, 1),
(9, 2),
(9, 6),
(10, 1),
(10, 3),
(10, 11),
(11, 1),
(11, 4),
(12, 1),
(12, 6),
(12, 9),
(12, 10),
(12, 11);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `employee`
--

CREATE TABLE `employee` (
  `id` int(11) UNSIGNED NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  `insert_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `employee`
--

INSERT INTO `employee` (`id`, `name`, `role`, `insert_time`) VALUES
(19, 'Lisa', 'Manager', '2018-04-20 09:54:16'),
(20, 'Lisa', 'Manager', '2018-04-20 09:55:57'),
(21, 'Lisa', 'Manager', '2018-04-20 10:51:24'),
(22, 'Lisa', 'Manager', '2018-04-20 10:52:02'),
(23, 'Lisa', 'Manager', '2018-04-20 10:53:04');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `enemy`
--

CREATE TABLE `enemy` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `hp` int(11) NOT NULL,
  `def` int(11) NOT NULL,
  `spr` int(11) NOT NULL,
  `mag` int(11) NOT NULL,
  `atk` int(11) NOT NULL,
  `mp` int(11) NOT NULL,
  `threat` int(11) NOT NULL,
  `blitzRes` int(11) NOT NULL,
  `feuerRes` int(11) NOT NULL,
  `wasserRes` int(11) NOT NULL,
  `windRes` int(11) NOT NULL,
  `eisRes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `enemy`
--

INSERT INTO `enemy` (`id`, `name`, `hp`, `def`, `spr`, `mag`, `atk`, `mp`, `threat`, `blitzRes`, `feuerRes`, `wasserRes`, `windRes`, `eisRes`) VALUES
(1, 'Gilgamesh', 1000, 10, 10, 10, 10, 10, 10, 10, 1000, 10, 10, 10),
(2, 'Susano', 1000, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
(3, 'Zeus', 3000, 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
(4, 'Qualle', 100, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10),
(6, 'Hai', 200, 10, 10, 10, 40, 10, 10, 10, 10, 10, 10, 10);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `spieler`
--

CREATE TABLE `spieler` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `passwort` varchar(100) NOT NULL,
  `geld` int(11) NOT NULL,
  `experience` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `spieler`
--

INSERT INTO `spieler` (`id`, `name`, `passwort`, `geld`, `experience`) VALUES
(1, 'Kevin', 'Welcome$18', 470, 0),
(2, 'Mathias', 'Welcome$18', 93564, 0),
(6, 'Florian', 'Welcome$18', 10, 100),
(7, 'Florian', 'Welcome$18', 10, 100),
(8, 'Scen', 'Dollar18', 100, 0),
(11, 'Test', 'Welcome$18', 100, 0),
(12, 'testi', 'Welcome$18', 110, 0),
(14, 'Johanna', 'Welcome$18', 100, 0),
(15, 'Sven', 'Welcome$18', 610, 0),
(16, 'Winnie', 'Winnie', 100, 0),
(17, 'Smithsen', '1234', 100, 0),
(18, 'HashTest', '[B@7c77baeb', 100, 0),
(20, 'hash', '[B@6df0eeef', 100, 0),
(21, 'test', '[B@c715f61', 100, 0),
(22, 'u', '[B@20bb10f6', 100, 0),
(23, 'a', '[B@2e50b43b', 100, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `turn`
--

CREATE TABLE `turn` (
  `id_turn` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `turn`
--

INSERT INTO `turn` (`id_turn`) VALUES
(1),
(2),
(3),
(4),
(5),
(6);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `turn_enemy`
--

CREATE TABLE `turn_enemy` (
  `turn_id` int(11) NOT NULL,
  `enemy_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `turn_enemy`
--

INSERT INTO `turn_enemy` (`turn_id`, `enemy_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(4, 2),
(4, 4),
(5, 4),
(5, 6),
(6, 1),
(6, 2),
(6, 3),
(6, 4),
(6, 6);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `universum`
--

CREATE TABLE `universum` (
  `id_universum` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `universum`
--

INSERT INTO `universum` (`id_universum`, `name`) VALUES
(1, 'Super-Mario'),
(2, 'Frozen'),
(3, 'Marvel'),
(4, 'Menschen');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `ability`
--
ALTER TABLE `ability`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `attackpattern`
--
ALTER TABLE `attackpattern`
  ADD PRIMARY KEY (`id_attackPattern`);

--
-- Indizes für die Tabelle `besitzt`
--
ALTER TABLE `besitzt`
  ADD PRIMARY KEY (`spieler_id`,`charakter_id`);

--
-- Indizes für die Tabelle `challenge`
--
ALTER TABLE `challenge`
  ADD PRIMARY KEY (`id_challenge`);

--
-- Indizes für die Tabelle `challenge_turn`
--
ALTER TABLE `challenge_turn`
  ADD PRIMARY KEY (`challenge_id`,`turn_id`);

--
-- Indizes für die Tabelle `charakter`
--
ALTER TABLE `charakter`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `char_ability`
--
ALTER TABLE `char_ability`
  ADD PRIMARY KEY (`charakter_id`,`ability_id`);

--
-- Indizes für die Tabelle `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `enemy`
--
ALTER TABLE `enemy`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `spieler`
--
ALTER TABLE `spieler`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `turn`
--
ALTER TABLE `turn`
  ADD PRIMARY KEY (`id_turn`);

--
-- Indizes für die Tabelle `turn_enemy`
--
ALTER TABLE `turn_enemy`
  ADD PRIMARY KEY (`turn_id`,`enemy_id`);

--
-- Indizes für die Tabelle `universum`
--
ALTER TABLE `universum`
  ADD PRIMARY KEY (`id_universum`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `ability`
--
ALTER TABLE `ability`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT für Tabelle `attackpattern`
--
ALTER TABLE `attackpattern`
  MODIFY `id_attackPattern` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `challenge`
--
ALTER TABLE `challenge`
  MODIFY `id_challenge` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT für Tabelle `charakter`
--
ALTER TABLE `charakter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT für Tabelle `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT für Tabelle `enemy`
--
ALTER TABLE `enemy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT für Tabelle `spieler`
--
ALTER TABLE `spieler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT für Tabelle `universum`
--
ALTER TABLE `universum`
  MODIFY `id_universum` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
