CREATE DATABASE  IF NOT EXISTS `edfeahs` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `edfeahs`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: edfeahs
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_semester_avg` double DEFAULT NULL,
  `first_semester_finalized` bit(1) NOT NULL,
  `second_semester_avg` double DEFAULT NULL,
  `second_semester_finalized` bit(1) NOT NULL,
  `student_id` bigint DEFAULT NULL,
  `subject_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3fl60s9rc5n1ka4ia5wt62qgm` (`student_id`),
  KEY `FKe9e3k904k6y7ii1832osog86w` (`subject_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,0,_binary '\0',0,_binary '\0',1,1),(2,0,_binary '\0',0,_binary '\0',1,2),(3,0,_binary '\0',0,_binary '\0',1,3),(4,0,_binary '\0',0,_binary '\0',4,1),(5,0,_binary '\0',0,_binary '\0',5,1),(6,0,_binary '\0',0,_binary '\0',5,5);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade_average`
--

DROP TABLE IF EXISTS `grade_average`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade_average` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `grade` int DEFAULT NULL,
  `value` double DEFAULT NULL,
  `student_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqthujfkxpqtc3nfehpgeraxxr` (`student_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade_average`
--

LOCK TABLES `grade_average` WRITE;
/*!40000 ALTER TABLE `grade_average` DISABLE KEYS */;
/*!40000 ALTER TABLE `grade_average` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mark`
--

DROP TABLE IF EXISTS `mark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mark` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `semester` varchar(255) DEFAULT NULL,
  `value` int DEFAULT NULL,
  `attendance_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK78xi75t1tp78ec715gl571v46` (`attendance_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mark`
--

LOCK TABLES `mark` WRITE;
/*!40000 ALTER TABLE `mark` DISABLE KEYS */;
INSERT INTO `mark` VALUES (1,'2022-06-06 11:04:45','FIRST',5,1),(2,'2021-06-06 11:05:15','FIRST',3,1),(3,'2022-06-09 13:11:16','FIRST',5,2),(4,'2022-06-09 13:11:34','SECOND',3,2),(5,'2022-06-09 13:13:06','FIRST',5,4),(6,'2022-09-09 15:13:06','FIRST',2,1),(7,'2022-06-15 17:13:35','FIRST',5,1),(8,'2022-06-15 17:14:27','FIRST',2,1),(9,'2022-06-15 17:14:35','FIRST',2,1),(10,'2022-06-15 17:16:02','FIRST',5,1),(11,'2022-06-15 17:16:30','FIRST',3,1),(12,'2022-06-15 17:16:36','FIRST',5,1),(13,'2022-06-21 18:19:54','SECOND',4,5),(14,'2022-06-21 18:20:54','SECOND',1,6),(15,'2022-06-23 20:43:22','SECOND',5,6),(16,'2022-06-23 20:43:58','SECOND',3,6),(17,'2022-08-23 20:46:05','FIRST',4,6),(18,'2022-06-23 21:49:10','FIRST',2,6),(19,'2022-06-23 23:46:15','FIRST',3,6),(20,'2022-06-23 20:46:18','FIRST',5,6);
/*!40000 ALTER TABLE `mark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `role` varchar(31) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ucn` varchar(13) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `elementary_school_id` bigint DEFAULT NULL,
  `high_school_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdbi1731ghvo2h5fw9e68qowfn` (`elementary_school_id`),
  KEY `FKsnopjq1p9yn1l18m5sg2qkjfy` (`high_school_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES ('ROLE_TEACHER',3,'3333333333333','first3','last3',NULL,NULL,NULL),('ROLE_STUDENT',1,'1111111111111','Davor','Stefanovic',1,NULL,NULL),('ROLE_TEACHER',2,'2222222222222','first2','last2',NULL,NULL,NULL),('ROLE_STUDENT',4,'4444444444444','Janko','Markovic',4,NULL,NULL),('ROLE_STUDENT',5,'5555555555555','Marko','Jankovic',5,NULL,NULL),('ROLE_STUDENT',7,'7777777777777','Child7','Child7',7,NULL,NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES (1,'School1','HIGH'),(2,'School2','HIGH');
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `grade` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `school_id` bigint NOT NULL,
  `teacher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg9ahxfklvfv5o2d8ejweibfo8` (`school_id`),
  KEY `FK40k1dbqit340yuih151rsfs4j` (`teacher_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (2,2,'Subject2',2,2),(3,3,'Subject3',2,2),(4,4,'Subject4',2,2),(1,5,'Subject1',2,2),(5,5,'Subject5',2,2);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-20 19:03:14
