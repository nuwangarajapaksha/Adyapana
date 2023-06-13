-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: adyapana_db
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `classno` int NOT NULL AUTO_INCREMENT,
  `subno` int NOT NULL,
  `tno` int NOT NULL,
  `timeslot` time NOT NULL,
  PRIMARY KEY (`classno`),
  KEY `fk_class_subject1_idx` (`subno`),
  KEY `fk_class_teacher1_idx` (`tno`),
  CONSTRAINT `fk_class_subject1` FOREIGN KEY (`subno`) REFERENCES `subject` (`subno`),
  CONSTRAINT `fk_class_teacher1` FOREIGN KEY (`tno`) REFERENCES `teacher` (`tno`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,1,1,'03:06:55'),(2,2,1,'04:07:59'),(3,4,2,'03:08:45'),(4,5,4,'02:09:01'),(5,6,5,'06:09:31');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `iNo` int NOT NULL AUTO_INCREMENT,
  `sno` int NOT NULL,
  `tno` int NOT NULL,
  `subno` int NOT NULL,
  `month` varchar(45) NOT NULL,
  `value` double NOT NULL,
  PRIMARY KEY (`iNo`) USING BTREE,
  KEY `fk_invoice_student1_idx` (`sno`),
  KEY `fk_invoice_teacher1_idx` (`tno`),
  KEY `fk_invoice_subject1_idx` (`subno`),
  CONSTRAINT `fk_invoice_student1` FOREIGN KEY (`sno`) REFERENCES `student` (`sno`),
  CONSTRAINT `fk_invoice_subject1` FOREIGN KEY (`subno`) REFERENCES `subject` (`subno`),
  CONSTRAINT `fk_invoice_teacher1` FOREIGN KEY (`tno`) REFERENCES `teacher` (`tno`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,1,5,6,'January',2000),(2,4,4,5,'July',1000),(3,3,1,1,'May',5000),(4,5,3,2,'July',4000),(9,2,2,4,'August',2000);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `sno` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `dob` date NOT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Kamal','292,Gonewela kelaniya','2021-05-24'),(2,'Nimal','452,Kiribathgoda','2021-05-24'),(3,'Sunil','555,Gampaha','2020-05-28'),(4,'Janan','354,waliwariya,pitakotuwe','2015-05-28'),(5,'sanduni','234,werehena','2016-05-28');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subno` int NOT NULL AUTO_INCREMENT,
  `subjectname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`subno`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Maths','High Level',5000),(2,'Science','High Level',4000),(3,'Physics','High Level',2000),(4,'Chemistry','High Level',2000),(5,'English','High Level',1000),(6,'I.C.T','High Level',2000);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `tno` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `subjects` int NOT NULL,
  PRIMARY KEY (`tno`),
  KEY `fk_teacher_subject_idx` (`subjects`),
  CONSTRAINT `fk_teacher_subject` FOREIGN KEY (`subjects`) REFERENCES `subject` (`subno`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Kasun','234,kasun road',1),(2,'Sundeepa','234,aldeniya',4),(3,'Kusum','353,deniyaya',2),(4,'Nadun','345,kelaniya',5),(5,'Mahashi','347,hiyanthuduwe',6);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-30 23:29:21
provinceprovince