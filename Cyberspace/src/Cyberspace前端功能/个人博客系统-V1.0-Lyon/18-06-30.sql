# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.18)
# Database: myblog
# Generation Time: 2018-06-30 11:02:37 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table articles
# ------------------------------------------------------------

CREATE TABLE `articles` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `author` varchar(30) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL,
  `subtitle` varchar(500) DEFAULT NULL,
  `content` text,
  `createdate` datetime DEFAULT NULL,
  `editdate` datetime DEFAULT NULL,
  `cid` int(20) DEFAULT NULL,
  `mid` int(20) DEFAULT NULL,
  `commentNum` int(20) DEFAULT NULL,
  `thankNum` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_article_categary` (`cid`),
  KEY `fk_article_maincat` (`mid`),
  CONSTRAINT `fk_article_categary` FOREIGN KEY (`cid`) REFERENCES `categary_2` (`id`),
  CONSTRAINT `fk_article_maincat` FOREIGN KEY (`mid`) REFERENCES `categary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table articles_copy
# ------------------------------------------------------------

CREATE TABLE `articles_copy` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `userid` int(10) NOT NULL,
  `article_title` varchar(200) DEFAULT NULL,
  `article_abstract` varchar(500) DEFAULT NULL,
  `article_content` text,
  `create_date` datetime DEFAULT NULL,
  `edit_date` datetime DEFAULT NULL,
  `article_second_id` int(10) DEFAULT NULL,
  `article_main_id` int(5) DEFAULT NULL,
  `article_comment` int(20) DEFAULT NULL,
  `article_thank` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_article_categary` (`article_second_id`),
  KEY `fk_article_maincat` (`article_main_id`),
  CONSTRAINT `articles_copy_ibfk_1` FOREIGN KEY (`article_second_id`) REFERENCES `categary_2` (`id`),
  CONSTRAINT `articles_copy_ibfk_2` FOREIGN KEY (`article_main_id`) REFERENCES `categary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table articleTags
# ------------------------------------------------------------

CREATE TABLE `articleTags` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(50) NOT NULL DEFAULT '',
  `createDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tagName` (`tagName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table categary
# ------------------------------------------------------------

CREATE TABLE `categary` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table categary_2
# ------------------------------------------------------------

CREATE TABLE `categary_2` (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `mainId` int(10) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_secondary_category` (`mainId`),
  CONSTRAINT `fk_secondary_category` FOREIGN KEY (`mainId`) REFERENCES `categary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table comments
# ------------------------------------------------------------

CREATE TABLE `comments` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `articleId` int(30) DEFAULT NULL,
  `articleTitle` varchar(30) DEFAULT NULL,
  `userId` int(30) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `comment` text,
  `commentDate` datetime DEFAULT NULL,
  `star` int(20) DEFAULT NULL,
  `reComment` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_articles_id` (`articleId`),
  KEY `fk_User_id` (`userId`),
  CONSTRAINT `fk_User_id` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `fk_articles_id` FOREIGN KEY (`articleId`) REFERENCES `articles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table commentStars
# ------------------------------------------------------------

CREATE TABLE `commentStars` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `articleId` int(30) DEFAULT NULL,
  `articleTitle` varchar(30) DEFAULT NULL,
  `mainUserid` int(30) DEFAULT NULL,
  `mainUsername` varchar(30) DEFAULT NULL,
  `comment` text,
  `userId` int(30) DEFAULT NULL,
  `userName` varchar(30) DEFAULT NULL,
  `starDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_articles_id` (`articleId`),
  KEY `fk_User_id` (`userId`),
  CONSTRAINT `commentstars_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `commentstars_ibfk_2` FOREIGN KEY (`articleId`) REFERENCES `articles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table recomments
# ------------------------------------------------------------

CREATE TABLE `recomments` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `articleId` int(30) DEFAULT NULL,
  `mainCommentid` int(30) DEFAULT NULL,
  `userId` int(30) DEFAULT NULL,
  `userName` varchar(30) DEFAULT NULL,
  `reComment` text,
  `reCommentDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_articles_id` (`articleId`),
  KEY `fk_User_id` (`userId`),
  CONSTRAINT `recomments_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `recomments_ibfk_2` FOREIGN KEY (`articleId`) REFERENCES `articles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table tagRelations
# ------------------------------------------------------------

CREATE TABLE `tagRelations` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `articleId` int(20) NOT NULL,
  `tagName` varchar(50) NOT NULL DEFAULT '',
  `tagId` int(20) NOT NULL,
  `createDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table thanks
# ------------------------------------------------------------

CREATE TABLE `thanks` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `articleId` int(30) DEFAULT NULL,
  `userId` int(30) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  `thankDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_articleid_userid` (`articleId`,`userId`),
  KEY `fk_articles_id` (`articleId`),
  KEY `fk_User_id` (`userId`),
  CONSTRAINT `thanks_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`),
  CONSTRAINT `thanks_ibfk_2` FOREIGN KEY (`articleId`) REFERENCES `articles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table User
# ------------------------------------------------------------

CREATE TABLE `User` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `telephone` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `usrname` varchar(30) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `telephone` (`telephone`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
