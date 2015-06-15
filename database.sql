SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;


CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `fragment` int(11) DEFAULT NULL,
  `at` date DEFAULT NULL,
  `what` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

INSERT INTO `comments` (`id`, `user`, `fragment`, `at`, `what`) VALUES
(2, 4, 3, '2015-06-08', 'This is my comment'),
(4, 2, 1, '2015-06-09', 'lol'),
(5, 4, 4, '2015-06-09', 'haha'),
(6, 2, 3, '2015-06-09', 'This is my comment');

CREATE TABLE IF NOT EXISTS `fragments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(63) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  `language` int(11) DEFAULT NULL,
  `tags` varchar(127) DEFAULT NULL,
  `at` date DEFAULT NULL,
  `code` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

INSERT INTO `fragments` (`id`, `title`, `user`, `language`, `tags`, `at`, `code`) VALUES
(1, 'Php open tag', 0, 9, NULL, '2015-06-05', 'echo $something'),
(3, 'import C#', NULL, 3, NULL, '2015-06-07', 'using System;'),
(4, 'Bootstrap Icon', NULL, 12, NULL, '2015-06-09', '<i class="glyphicon glyphicon-star"></i>'),
(5, 'Some SQL', NULL, 0, NULL, '2015-06-09', 'SELECT F.id, F.title, F.code, F.at, F.language, count(c.id) as comments FROM fragments F LEFT JOIN comments c ON F.id = C.fragment GROUP BY f.id;');

CREATE TABLE IF NOT EXISTS `languages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

INSERT INTO `languages` (`id`, `name`) VALUES
(1, 'Java'),
(2, 'JavaScript'),
(3, 'C#'),
(5, 'Scala'),
(6, 'C'),
(7, 'Python'),
(9, 'PHP'),
(10, 'Haskell'),
(12, 'HTML'),
(13, 'SQL');

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `avatar` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  `admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

INSERT INTO `users` (`id`, `name`, `username`, `avatar`, `email`, `password`, `admin`) VALUES
(1, 'Altich Showers', 'altich', '', 'root@altich.tv', 'daeccf0ad3c1fc8c8015205c332f5b42', 0),
(2, 'Tich Showers', 'tich', '', 'admin@tichshowers.com', 'daeccf0ad3c1fc8c8015205c332f5b42', 0),
(3, 'Adria Storm', 'adria', '', 'adria@tich.tv', 'daeccf0ad3c1fc8c8015205c332f5b42', 0),
(4, 'Rayne Flash', 'rayne', '', 'rayne@tich.tv', 'daeccf0ad3c1fc8c8015205c332f5b42', 0),
(5, 'Mist Showers', 'mist', '', 'mist@tichshowers.com', 'daeccf0ad3c1fc8c8015205c332f5b42', 0),
(6, 'Administrator', 'admin', '', 'example@domain.com', '21232f297a57a5a743894a0e4a801fc3', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
