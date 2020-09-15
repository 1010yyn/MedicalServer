-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: medical_database
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course_history`
--

DROP TABLE IF EXISTS `course_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_history` (
  `user_id` int(11) NOT NULL,
  `course_id` varchar(45) NOT NULL,
  `time_stamp` date NOT NULL,
  PRIMARY KEY (`user_id`,`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_history`
--

LOCK TABLES `course_history` WRITE;
/*!40000 ALTER TABLE `course_history` DISABLE KEYS */;
INSERT INTO `course_history` VALUES (1,'2020082801','2020-08-31'),(1,'2020082802','2020-09-01'),(2,'2020082802','2020-09-08');
/*!40000 ALTER TABLE `course_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_info`
--

DROP TABLE IF EXISTS `course_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_info` (
  `course_id` int(11) NOT NULL,
  `course_title` varchar(45) DEFAULT NULL,
  `office` varchar(45) DEFAULT NULL,
  `sum` varchar(45) DEFAULT NULL,
  `completed` varchar(45) DEFAULT NULL,
  `start` date DEFAULT NULL,
  `end` date DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_info`
--

LOCK TABLES `course_info` WRITE;
/*!40000 ALTER TABLE `course_info` DISABLE KEYS */;
INSERT INTO `course_info` VALUES (2020082801,'第一课','第一科室','10','4','2020-08-29','2020-09-03'),(2020082802,'第二课','第二科室','8','1','2020-08-30','2020-09-10');
/*!40000 ALTER TABLE `course_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_answer`
--

DROP TABLE IF EXISTS `exam_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_answer` (
  `exam_id` int(11) NOT NULL,
  `question_id` varchar(45) NOT NULL,
  `answer_id` varchar(45) NOT NULL,
  `answer_content` blob,
  PRIMARY KEY (`exam_id`,`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_answer`
--

LOCK TABLES `exam_answer` WRITE;
/*!40000 ALTER TABLE `exam_answer` DISABLE KEYS */;
INSERT INTO `exam_answer` VALUES (2020082601,'1','1',NULL);
/*!40000 ALTER TABLE `exam_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_history`
--

DROP TABLE IF EXISTS `exam_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_history` (
  `user_id` int(11) NOT NULL,
  `exam_id` varchar(45) NOT NULL,
  `time_stamp` date NOT NULL,
  PRIMARY KEY (`user_id`,`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_history`
--

LOCK TABLES `exam_history` WRITE;
/*!40000 ALTER TABLE `exam_history` DISABLE KEYS */;
INSERT INTO `exam_history` VALUES (1,'2020082601','2020-08-27'),(1,'2020082801','2020-08-29'),(2,'2020082601','2020-08-28');
/*!40000 ALTER TABLE `exam_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_info`
--

DROP TABLE IF EXISTS `exam_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_info` (
  `exam_id` int(11) NOT NULL,
  `exam_title` varchar(45) NOT NULL,
  `office` varchar(45) DEFAULT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_info`
--

LOCK TABLES `exam_info` WRITE;
/*!40000 ALTER TABLE `exam_info` DISABLE KEYS */;
INSERT INTO `exam_info` VALUES (2020082601,'第一场考试','第一科室','2020-08-06','2020-08-30'),(2020082801,'第二次考试','第二科室','2020-08-26','2020-08-31'),(2020082802,'第三次考试','第三科室','2020-08-28','2020-09-29');
/*!40000 ALTER TABLE `exam_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_question`
--

DROP TABLE IF EXISTS `exam_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_question` (
  `exam_id` int(11) NOT NULL,
  `question_id` varchar(45) NOT NULL,
  `question_items` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_question`
--

LOCK TABLES `exam_question` WRITE;
/*!40000 ALTER TABLE `exam_question` DISABLE KEYS */;
INSERT INTO `exam_question` VALUES (2020082601,'1','第一题题干'),(2020082601,'2','第二题题干'),(2020082801,'1','第一题题干'),(2020082801,'2','第2题题干');
/*!40000 ALTER TABLE `exam_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_info`
--

DROP TABLE IF EXISTS `message_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_info` (
  `message_id` int(11) NOT NULL,
  `message_title` varchar(45) NOT NULL,
  `office` varchar(45) NOT NULL,
  `text` mediumtext NOT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_info`
--

LOCK TABLES `message_info` WRITE;
/*!40000 ALTER TABLE `message_info` DISABLE KEYS */;
INSERT INTO `message_info` VALUES (11111,'一个通知','康复科','正文正文正文正文正文正文正文正正文正文正文正文正文正文正文正正文正文正文正文正文正文正文正正文正文正文正文正文正文正文正'),(33214,'456436','1465453','dasfjdoasflasgj');
/*!40000 ALTER TABLE `message_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `office_list`
--

DROP TABLE IF EXISTS `office_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `office_list` (
  `office_id` varchar(45) NOT NULL,
  `office_name` int(11) NOT NULL,
  PRIMARY KEY (`office_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `office_list`
--

LOCK TABLES `office_list` WRITE;
/*!40000 ALTER TABLE `office_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `office_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_ordinary`
--

DROP TABLE IF EXISTS `user_ordinary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_ordinary` (
  `user_id` varchar(45) NOT NULL,
  `login_id` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_phone` varchar(45) NOT NULL,
  `office` varchar(45) NOT NULL,
  `sex` varchar(45) NOT NULL,
  `post` varchar(45) NOT NULL,
  `head` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_id_UNIQUE` (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_ordinary`
--

LOCK TABLES `user_ordinary` WRITE;
/*!40000 ALTER TABLE `user_ordinary` DISABLE KEYS */;
INSERT INTO `user_ordinary` VALUES ('1','admin','111111','李阳','18273647382','康复科','女','护士',NULL),('2','23445','123456','神乐','1345623456','放射科','男','医生',''),('3','4','8','8','6','fdsfa','dafdas','fsd','');
/*!40000 ALTER TABLE `user_ordinary` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-15 10:01:34
