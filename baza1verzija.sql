/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.25-MariaDB : Database - komunalno_preduzece
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`komunalno_preduzece` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `komunalno_preduzece`;

/*Table structure for table `kamion` */

DROP TABLE IF EXISTS `kamion`;

CREATE TABLE `kamion` (
  `kamionID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `brend` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `nosivost` tinyint(20) DEFAULT NULL,
  `vozac` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`kamionID`),
  KEY `VOZAC_FK` (`vozac`),
  CONSTRAINT `VOZAC_FK` FOREIGN KEY (`vozac`) REFERENCES `vozac` (`vozacID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `kamion` */

insert  into `kamion`(`kamionID`,`brend`,`model`,`nosivost`,`vozac`) values 
(1,'Peterblit','COE 320',30,1),
(2,'Volvo','Leach Alpha',25,2),
(3,'Nissan','UD 3300',35,2),
(6,'Drugi','Novi',30,1);

/*Table structure for table `komunalni_radnik` */

DROP TABLE IF EXISTS `komunalni_radnik`;

CREATE TABLE `komunalni_radnik` (
  `komunalniRadnikID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) DEFAULT NULL,
  `prezime` varchar(30) DEFAULT NULL,
  `mesto` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`komunalniRadnikID`),
  KEY `MESTO_FK` (`mesto`),
  CONSTRAINT `MESTO_FK` FOREIGN KEY (`mesto`) REFERENCES `mesto` (`mestoID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `komunalni_radnik` */

insert  into `komunalni_radnik`(`komunalniRadnikID`,`ime`,`prezime`,`mesto`) values 
(1,'Marko','Markovic',1),
(10,'Milan','Milanovic',2),
(11,'Ana','Anic',3),
(12,'Luka','Lukic',2);

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `mestoID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) DEFAULT NULL,
  `ptt` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`mestoID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `mesto` */

insert  into `mesto`(`mestoID`,`naziv`,`ptt`) values 
(1,'Vrsac',127),
(2,'Beograd',127),
(3,'Novi Sad',127),
(4,'Pancevo',127);

/*Table structure for table `termin` */

DROP TABLE IF EXISTS `termin`;

CREATE TABLE `termin` (
  `kamionID` bigint(20) unsigned NOT NULL,
  `komunalniRadnikID` bigint(20) unsigned NOT NULL,
  `datum` date NOT NULL,
  `reciklazaOdlazak` tinyint(1) DEFAULT NULL,
  `deponijaOdlazak` tinyint(1) DEFAULT NULL,
  `vremePocetka` time DEFAULT NULL,
  `vremeZavrsetka` time DEFAULT NULL,
  PRIMARY KEY (`kamionID`,`komunalniRadnikID`,`datum`),
  KEY `KOMUNALNI_RADNIK_FK` (`komunalniRadnikID`),
  CONSTRAINT `KAMION_FK` FOREIGN KEY (`kamionID`) REFERENCES `kamion` (`kamionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `KOMUNALNI_RADNIK_FK` FOREIGN KEY (`komunalniRadnikID`) REFERENCES `komunalni_radnik` (`komunalniRadnikID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `termin` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) DEFAULT NULL,
  `prezime` varchar(30) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userID`,`ime`,`prezime`,`username`,`password`) values 
(1,'Pera','Peric','pera','pera');

/*Table structure for table `vozac` */

DROP TABLE IF EXISTS `vozac`;

CREATE TABLE `vozac` (
  `vozacID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(30) DEFAULT NULL,
  `prezime` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`vozacID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `vozac` */

insert  into `vozac`(`vozacID`,`ime`,`prezime`) values 
(1,'Danilo','Danilovic'),
(2,'Jovan','Jovanovic');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
