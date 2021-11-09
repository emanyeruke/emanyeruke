-- MySQL dump 10.16  Distrib 10.1.47-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: mynhakamylife
-- ------------------------------------------------------
-- Server version	10.1.47-MariaDB-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accident_product`
--

DROP TABLE IF EXISTS `accident_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accident_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clawback_period` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `person_type` varchar(255) DEFAULT NULL,
  `premium` decimal(19,2) DEFAULT NULL,
  `sum_assured` decimal(19,2) DEFAULT NULL,
  `accident_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjr4n7t82clihh7039qntg801b` (`accident_id`),
  CONSTRAINT `FKjr4n7t82clihh7039qntg801b` FOREIGN KEY (`accident_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accident_product`
--

LOCK TABLES `accident_product` WRITE;
/*!40000 ALTER TABLE `accident_product` DISABLE KEYS */;
INSERT INTO `accident_product` VALUES (4,4,'accident_1000','PRINCIPAL',0.60,1000.00,2),(5,9,'accident_4000',NULL,2.40,4000.00,2),(6,9,'accident_23232','PRINCIPAL',13.94,23232.00,2),(7,4,'accident_10000','PRINCIPAL',6.00,10000.00,2),(8,4,'accident_10000','PRINCIPAL',6.00,10000.00,2);
/*!40000 ALTER TABLE `accident_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `landline` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `agent_id` bigint(20) NOT NULL,
  PRIMARY KEY (`agent_id`),
  CONSTRAINT `FKo14pxfq29gu3me82ua8r4c9l2` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `channel` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `team` varchar(255) DEFAULT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `bpn_number` varchar(255) DEFAULT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `business_name` varchar(255) DEFAULT NULL,
  `copy_of_id_url` varchar(255) DEFAULT NULL,
  `cr14url` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `merchant_code` varchar(255) DEFAULT NULL,
  `merchant_type` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `national_id_or_drivers_licence` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `physical_address_city` varchar(255) DEFAULT NULL,
  `physical_address_street` varchar(255) DEFAULT NULL,
  `physical_address_suburb` varchar(255) DEFAULT NULL,
  `postal_address_city` varchar(255) DEFAULT NULL,
  `postal_address_street` varchar(255) DEFAULT NULL,
  `postal_address_suburb` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `proof_of_residence` varchar(255) DEFAULT NULL,
  `proof_of_residence_url` varchar(255) DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `registration_number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tax_clearance_url` varchar(255) DEFAULT NULL,
  `type_of_business` varchar(255) DEFAULT NULL,
  `agent_profile_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa9lb6g5klcdr6u2m3c51w3672` (`manager_id`),
  KEY `FKj55n3g2jity0e45t5306dncl0` (`agent_profile_id`),
  CONSTRAINT `FKa9lb6g5klcdr6u2m3c51w3672` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`),
  CONSTRAINT `FKj55n3g2jity0e45t5306dncl0` FOREIGN KEY (`agent_profile_id`) REFERENCES `agent_profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
INSERT INTO `agent` VALUES (9,'system','2021-05-19 07:33:41.856865',NULL,'2021-05-19 07:33:41.856865',1,'VOICE','0778227522','munya@gmail.com','Tendai','AGENT','Munyaradzi','NORTH',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'system','2021-06-01 10:54:23.908068',NULL,'2021-06-01 10:54:23.908068',0,'FEET_ON_STREET','9876543214','muna@gmail.com','Farai','AGENT_EXECUTIVE','Mutana','NORTH',13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'system','2021-06-01 11:01:25.908133',NULL,'2021-06-01 11:01:25.908134',0,'FEET_ON_STREET','0775091262','emails@email.com','Gdfgfdg','AGENT_EXECUTIVE','Fdgfdgfdgdg','NORTH',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent_account`
--

DROP TABLE IF EXISTS `agent_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_account` (
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `available_balance` decimal(19,2) DEFAULT NULL,
  `commission_balance` decimal(19,2) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `ledger_balance` decimal(19,2) DEFAULT NULL,
  `reserved_balance` decimal(19,2) DEFAULT NULL,
  `agent_id` bigint(20) NOT NULL,
  PRIMARY KEY (`agent_id`),
  CONSTRAINT `FKawytxvqibpiax5rmy98ak3ae0` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent_account`
--

LOCK TABLES `agent_account` WRITE;
/*!40000 ALTER TABLE `agent_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `agent_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent_profile`
--

DROP TABLE IF EXISTS `agent_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent_profile`
--

LOCK TABLES `agent_profile` WRITE;
/*!40000 ALTER TABLE `agent_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `agent_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent_profile_transaction_type`
--

DROP TABLE IF EXISTS `agent_profile_transaction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_profile_transaction_type` (
  `agent_profile_id` bigint(20) NOT NULL,
  `transaction_type_id` bigint(20) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `max_credit` decimal(19,2) DEFAULT NULL,
  `max_debit` decimal(19,2) DEFAULT NULL,
  `min_credit` decimal(19,2) DEFAULT NULL,
  `min_debit` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`agent_profile_id`,`transaction_type_id`),
  KEY `FKr84tu3tntbpn3w264bmtnbn1a` (`transaction_type_id`),
  CONSTRAINT `FKr84tu3tntbpn3w264bmtnbn1a` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`id`),
  CONSTRAINT `FKs26c10t69qapd0x9uej7tssym` FOREIGN KEY (`agent_profile_id`) REFERENCES `agent_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent_profile_transaction_type`
--

LOCK TABLES `agent_profile_transaction_type` WRITE;
/*!40000 ALTER TABLE `agent_profile_transaction_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `agent_profile_transaction_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent_transaction_fee`
--

DROP TABLE IF EXISTS `agent_transaction_fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_transaction_fee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `fixed` decimal(19,2) DEFAULT NULL,
  `imt_tax_fixed` decimal(19,2) DEFAULT NULL,
  `imt_tax_percentage` decimal(19,2) DEFAULT NULL,
  `max` decimal(19,2) DEFAULT NULL,
  `min` decimal(19,2) DEFAULT NULL,
  `percentage` decimal(19,2) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `agent_profile_id` bigint(20) DEFAULT NULL,
  `transaction_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcutdbn5x7ry852qg9ttyw30n4` (`agent_profile_id`),
  KEY `FKikke5xfe61blveac93xgb3x6k` (`transaction_type_id`),
  CONSTRAINT `FKcutdbn5x7ry852qg9ttyw30n4` FOREIGN KEY (`agent_profile_id`) REFERENCES `agent_profile` (`id`),
  CONSTRAINT `FKikke5xfe61blveac93xgb3x6k` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent_transaction_fee`
--

LOCK TABLES `agent_transaction_fee` WRITE;
/*!40000 ALTER TABLE `agent_transaction_fee` DISABLE KEYS */;
/*!40000 ALTER TABLE `agent_transaction_fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent_velocity`
--

DROP TABLE IF EXISTS `agent_velocity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_velocity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `max_daily_credit` decimal(19,2) DEFAULT NULL,
  `max_daily_debit` decimal(19,2) DEFAULT NULL,
  `max_monthly_credit` decimal(19,2) DEFAULT NULL,
  `max_monthly_debit` decimal(19,2) DEFAULT NULL,
  `max_weekly_credit` decimal(19,2) DEFAULT NULL,
  `max_weekly_debit` decimal(19,2) DEFAULT NULL,
  `min_daily_credit` decimal(19,2) DEFAULT NULL,
  `min_daily_debit` decimal(19,2) DEFAULT NULL,
  `min_monthly_credit` decimal(19,2) DEFAULT NULL,
  `min_monthly_debit` decimal(19,2) DEFAULT NULL,
  `min_weekly_credit` decimal(19,2) DEFAULT NULL,
  `min_weekly_debit` decimal(19,2) DEFAULT NULL,
  `agent_profile_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsakxmccii1p4xhfhxuuwe497e` (`agent_profile_id`),
  CONSTRAINT `FKsakxmccii1p4xhfhxuuwe497e` FOREIGN KEY (`agent_profile_id`) REFERENCES `agent_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent_velocity`
--

LOCK TABLES `agent_velocity` WRITE;
/*!40000 ALTER TABLE `agent_velocity` DISABLE KEYS */;
/*!40000 ALTER TABLE `agent_velocity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `allocation`
--

DROP TABLE IF EXISTS `allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allocation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  `policy_accident_id` bigint(20) DEFAULT NULL,
  `policy_comprehensive_id` bigint(20) DEFAULT NULL,
  `policy_funeral_id` bigint(20) DEFAULT NULL,
  `policy_savings_id` bigint(20) DEFAULT NULL,
  `transaction_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrpvwaqqk6odkw3ml95b4aspa9` (`payment_id`),
  KEY `FKnp2rw3bf8c775vnqj2oircqir` (`policy_accident_id`),
  KEY `FKrf9x8lk5qdj6dw7kwl9iim4lv` (`policy_comprehensive_id`),
  KEY `FK53j1bg82rj0pd97xqe7h930l2` (`policy_funeral_id`),
  KEY `FK412595bs81skw72y70n3q583r` (`policy_savings_id`),
  KEY `FKgc4360okmfe6jrt5e7glveyvs` (`transaction_id`),
  CONSTRAINT `FK412595bs81skw72y70n3q583r` FOREIGN KEY (`policy_savings_id`) REFERENCES `policy` (`id`),
  CONSTRAINT `FK53j1bg82rj0pd97xqe7h930l2` FOREIGN KEY (`policy_funeral_id`) REFERENCES `policy` (`id`),
  CONSTRAINT `FKgc4360okmfe6jrt5e7glveyvs` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`),
  CONSTRAINT `FKnp2rw3bf8c775vnqj2oircqir` FOREIGN KEY (`policy_accident_id`) REFERENCES `policy` (`id`),
  CONSTRAINT `FKrf9x8lk5qdj6dw7kwl9iim4lv` FOREIGN KEY (`policy_comprehensive_id`) REFERENCES `policy` (`id`),
  CONSTRAINT `FKrpvwaqqk6odkw3ml95b4aspa9` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allocation`
--

LOCK TABLES `allocation` WRITE;
/*!40000 ALTER TABLE `allocation` DISABLE KEYS */;
/*!40000 ALTER TABLE `allocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit_action`
--

DROP TABLE IF EXISTS `audit_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit_action` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `action_performed` varchar(255) DEFAULT NULL,
  `client_ip_address` varchar(255) DEFAULT NULL,
  `date_time` datetime(6) DEFAULT NULL,
  `payload` longtext,
  `resource` varchar(255) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_action`
--

LOCK TABLES `audit_action` WRITE;
/*!40000 ALTER TABLE `audit_action` DISABLE KEYS */;
INSERT INTO `audit_action` VALUES (1,'string','string','2021-06-01 07:45:10.000000','string','string','string','string'),(2,'string','string','2021-06-01 07:45:10.000000','string','string','string','string'),(3,'string','string','2021-06-01 07:45:10.000000','string','string','string','string'),(4,'string','string','2021-06-01 07:45:10.000000','string','string','string','string'),(5,'Create employer','196.2.73.6','2021-06-01 08:09:40.562054','[EmployerCreateDT0(name=Employed, representative=string, email=employer@employed.com, contactNumber=263775091262, addressCreateDto=AddressCreateDto(street=string, suburb=string, city=string))]','Employer','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:88.0) Gecko/20100101 Firefox/88.0','anonymousUser'),(6,'Create Agent','196.2.73.6','2021-06-01 10:38:23.833603','[AgentCreateDTO(name=string, surname=string, email=emails@emailing.com, contactNumber=0775091262, channel=FEET_ON_STREET, team=NORTH, position=AGENT_EXECUTIVE, managerId=1)]','Agent','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:88.0) Gecko/20100101 Firefox/88.0','anonymousUser'),(7,'Create Agent','196.2.73.6','2021-06-01 10:39:16.592165','[AgentCreateDTO(name=string, surname=string, email=emails@emailing.com, contactNumber=0775091262, channel=FEET_ON_STREET, team=NORTH, position=AGENT_EXECUTIVE, managerId=1)]','Agent','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:88.0) Gecko/20100101 Firefox/88.0','anonymousUser'),(8,'Create Agent','196.2.73.6','2021-06-01 10:39:37.162079','[AgentCreateDTO(name=string, surname=string, email=emails@emailing.com, contactNumber=0775091262, channel=FEET_ON_STREET, team=NORTH, position=AGENT_EXECUTIVE, managerId=2)]','Agent','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:88.0) Gecko/20100101 Firefox/88.0','anonymousUser'),(9,'View Managers','196.2.73.6','2021-06-01 10:39:49.836735','[0, 100]','Manager','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:88.0) Gecko/20100101 Firefox/88.0','anonymousUser'),(10,'Create Manager','196.2.73.6','2021-06-01 10:42:05.724089','[ManagerCreateDTO(name=tests, surname=tests, email=string@manager.com, branch=string)]','Manager','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:88.0) Gecko/20100101 Firefox/88.0','anonymousUser'),(11,'Create Agent','196.2.73.6','2021-06-01 10:42:22.758829','[AgentCreateDTO(name=string, surname=string, email=emails@emailing.com, contactNumber=0775091262, channel=FEET_ON_STREET, team=NORTH, position=AGENT_EXECUTIVE, managerId=1)]','Agent','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:88.0) Gecko/20100101 Firefox/88.0','anonymousUser'),(12,'View Managers','196.2.73.6','2021-06-01 10:43:25.182760','[]','Manager','Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:88.0) Gecko/20100101 Firefox/88.0','anonymousUser'),(13,'Get All Accident Plan','62.171.154.168','2021-06-01 10:43:56.948579','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(14,'Get All Accident Policies','127.0.0.1','2021-06-01 10:46:30.364228','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(15,'Get All Accident Plan','62.171.154.168','2021-06-01 11:53:55.823371','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(16,'Get All Accident Plan','62.171.154.168','2021-06-01 11:54:20.726885','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(17,'Get All Accident Plan','62.171.154.168','2021-06-01 11:54:26.087973','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(18,'Get All Accident Plan','62.171.154.168','2021-06-01 11:54:34.106125','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(19,'Get All Accident Plan','197.221.253.203','2021-06-02 07:28:44.094866','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(20,'Allocate Accident Plan','197.221.253.203','2021-06-02 07:29:26.150002','[PolicyAccidentCreateDto(policyHolderId=12, accidentProductId=4, paymentMethod=null, paymentFrequency=null, formLocation=222aeee3-d81f-4b0b-ac7a-5133abda151c.pdf, agentId=9, commencementDate=2021-07-01, bankDetailsCreateDto=BankDetailsCreateDto(bankName=null, branch=null, accountNumber=null, debitDate=null))]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(21,'Get All Accident Plan','62.171.154.168','2021-06-02 10:52:55.128262','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(22,'Get All Accident Plan','62.171.154.168','2021-06-02 10:54:58.282913','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(23,'Allocate Accident Plan','62.171.154.168','2021-06-02 10:55:22.277126','[PolicyAccidentCreateDto(policyHolderId=15, accidentProductId=4, paymentMethod=CASH, paymentFrequency=MONTHLY, formLocation=649dda80-6afa-4f7b-a7e6-678b1372377f.pdf, agentId=9, commencementDate=2021-07-01, bankDetailsCreateDto=BankDetailsCreateDto(bankName=null, branch=null, accountNumber=null, debitDate=null))]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(24,'Get All Accident Plan','77.246.52.176','2021-06-02 12:26:08.381066','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(25,'Get All Accident Plan','77.246.52.176','2021-06-02 12:27:58.274150','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(26,'Allocate Accident Plan','77.246.52.176','2021-06-02 12:29:00.596297','[PolicyAccidentCreateDto(policyHolderId=12, accidentProductId=4, paymentMethod=null, paymentFrequency=null, formLocation=e21df327-73f1-46ad-ad7d-4d4a1e6f5500.pdf, agentId=9, commencementDate=2021-07-01, bankDetailsCreateDto=BankDetailsCreateDto(bankName=null, branch=null, accountNumber=null, debitDate=null))]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(27,'Get All Accident Plan','62.171.154.168','2021-06-02 12:54:07.972019','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','highforce'),(28,'Allocate Accident Plan','62.171.154.168','2021-06-02 12:54:26.930953','[PolicyAccidentCreateDto(policyHolderId=12, accidentProductId=4, paymentMethod=null, paymentFrequency=null, formLocation=fb207868-69d6-4748-9ffb-a1ecda627317.pdf, agentId=11, commencementDate=2021-07-01, bankDetailsCreateDto=BankDetailsCreateDto(bankName=null, branch=null, accountNumber=null, debitDate=null))]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','highforce'),(29,'Get All Accident Plan','77.246.52.176','2021-06-02 13:24:23.288763','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(30,'Allocate Accident Plan','77.246.52.176','2021-06-02 13:24:42.852299','[PolicyAccidentCreateDto(policyHolderId=2, accidentProductId=4, paymentMethod=null, paymentFrequency=null, formLocation=b4a7ee9e-9a65-4bbe-826b-31589f2d78fd.pdf, agentId=11, commencementDate=2021-07-01, bankDetailsCreateDto=BankDetailsCreateDto(bankName=null, branch=null, accountNumber=null, debitDate=null))]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(31,'Get All Accident Plan','77.246.52.176','2021-06-02 13:31:19.649573','[]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(32,'Allocate Accident Plan','77.246.52.176','2021-06-02 13:31:34.671868','[PolicyAccidentCreateDto(policyHolderId=2, accidentProductId=4, paymentMethod=null, paymentFrequency=null, formLocation=7458eda5-4432-4d60-90b4-2d7d6ca10a8b.pdf, agentId=9, commencementDate=2021-07-01, bankDetailsCreateDto=BankDetailsCreateDto(bankName=null, branch=null, accountNumber=null, debitDate=null))]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(33,'Allocate Savings Policy','62.171.154.168','2021-06-02 13:51:02.706955','[PolicySavingsCreateDto(policyHolderId=12, savingsProductId=3, paymentMethod=CASH, paymentFrequency=MONTHLY, formLocation=93d55d33-4eb1-4172-8d15-19515aebc1d9.pdf, agentId=9, commencementDate=2021-07-01, bankDetailsCreateDto=BankDetailsCreateDto(bankName=null, branch=null, accountNumber=null, debitDate=null))]','Savings Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(34,'Allocate Standard Funeral Policy','62.171.154.168','2021-06-02 13:52:12.110697','[PolicyFuneralCreateDto(policyHolderId=12, funeralProductId=5, paymentMethod=CASH, paymentFrequency=MONTHLY, formLocation=522b6718-63b0-4690-a2bc-493b761b2839.pdf, agentId=11, commencementDate=2021-07-01, bankDetailsCreateDto=BankDetailsCreateDto(bankName=null, branch=null, accountNumber=null, debitDate=null))]','Standard Funeral Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(35,'Allocate Comprehensive Plan','62.171.154.168','2021-06-02 13:53:12.795848','[PolicyComprehensiveCreateDto(policyHolderId=12, comprehensiveProductId=3, paymentMethod=CASH, paymentFrequency=MONTHLY, formLocation=4782e013-fb7d-462c-a97a-f210388e9b99.pdf, agentId=9, commencementDate=2021-07-01, bankDetailsCreateDto=BankDetailsCreateDto(bankName=null, branch=null, accountNumber=null, debitDate=null))]','Comprehensive Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(36,'Get All Accident Policies','127.0.0.1','2021-06-02 13:58:15.004834','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(37,'Get All Accident Policies','127.0.0.1','2021-06-02 13:58:19.198535','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(38,'Get All Standard Funeral Policies','127.0.0.1','2021-06-02 13:58:30.531399','[0, 50]','Standard Funeral Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(39,'Get All Accident Policies','127.0.0.1','2021-06-02 13:58:34.564412','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(40,'Get All Standard Funeral Policies','127.0.0.1','2021-06-02 13:58:37.485655','[0, 50]','Standard Funeral Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(41,'Get All Comprehensive Policies','127.0.0.1','2021-06-02 13:58:38.510676','[0, 50]','Comprehensive Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(42,'Get All Savings policies','127.0.0.1','2021-06-02 13:58:42.609951','[0, 50]','Savings Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(43,'Get All Standard Funeral Policies','127.0.0.1','2021-06-02 13:58:45.515698','[0, 50]','Standard Funeral Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(44,'Get All Accident Policies','127.0.0.1','2021-06-02 13:58:47.083465','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(45,'Get All Standard Funeral Policies','127.0.0.1','2021-06-02 13:58:48.026817','[0, 50]','Standard Funeral Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(46,'Get All Comprehensive Policies','127.0.0.1','2021-06-02 13:58:48.643173','[0, 50]','Comprehensive Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(47,'Get All Accident Policies','127.0.0.1','2021-06-02 13:58:49.716582','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(48,'Get All Standard Funeral Policies','127.0.0.1','2021-06-02 13:58:51.335874','[0, 50]','Standard Funeral Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(49,'Get All Accident Policies','127.0.0.1','2021-06-02 13:58:52.107970','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(50,'Get All Standard Funeral Policies','127.0.0.1','2021-06-02 13:58:52.778796','[0, 50]','Standard Funeral Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(51,'Get All Comprehensive Policies','127.0.0.1','2021-06-02 13:58:53.279144','[0, 50]','Comprehensive Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(52,'Get All Savings policies','127.0.0.1','2021-06-02 13:58:59.788945','[0, 50]','Savings Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(53,'Get All Comprehensive Policies','127.0.0.1','2021-06-02 13:59:09.279283','[0, 50]','Comprehensive Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(54,'Get All Standard Funeral Policies','127.0.0.1','2021-06-02 13:59:17.811039','[0, 50]','Standard Funeral Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(55,'Get All Comprehensive Policies','127.0.0.1','2021-06-02 13:59:18.074104','[0, 50]','Comprehensive Policy','GuzzleHttp/6.5.5 curl/7.58.0 PHP/7.3.27-9+ubuntu18.04.1+deb.sury.org+1','takaiteyi@gmail.com'),(56,'Get All Comprehensive Policies','77.246.52.176','2021-06-02 15:25:03.608347','[0, 10]','Comprehensive Policy','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36','anonymousUser'),(57,'Get All Standard Funeral Policies','77.246.52.176','2021-06-02 15:25:34.710339','[0, 10]','Standard Funeral Policy','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36','anonymousUser'),(58,'Get All Savings policies','77.246.52.176','2021-06-02 15:26:16.546094','[0, 10]','Savings Policy','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36','anonymousUser'),(59,'Get All Accident Policies','77.246.52.176','2021-06-02 15:26:30.921651','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(60,'Get All Standard Funeral Policies','77.246.52.176','2021-06-02 15:26:32.941116','[0, 50]','Standard Funeral Policy','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(61,'Get All Accident Policies','77.246.52.176','2021-06-02 15:26:53.835713','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(62,'Get All Accident Policies','77.246.52.176','2021-06-02 15:31:48.747307','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(63,'Get All Standard Funeral Policies','77.246.52.176','2021-06-02 15:32:08.768877','[0, 10]','Standard Funeral Policy','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36','anonymousUser'),(64,'Get All Accident Policies','77.246.52.176','2021-06-02 15:42:14.805507','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(65,'Get All Accident Policies','77.246.52.176','2021-06-02 15:44:40.822082','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(66,'Get All Accident Policies','77.246.52.176','2021-06-02 15:44:41.651758','[0, 50]','Accident Plan','GuzzleHttp/6.5.5 curl/7.76.1 PHP/7.4.18','highforce'),(67,'Get All Accident Policies','77.246.52.176','2021-06-02 15:45:06.511124','[0, 10]','Accident Plan','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36','anonymousUser'),(68,'Get All Accident Policies','77.246.52.176','2021-06-02 15:45:12.579494','[0, 10]','Accident Plan','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36','anonymousUser');
/*!40000 ALTER TABLE `audit_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `bin` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `bank_code` varchar(255) DEFAULT NULL,
  `agent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7qct241djo57xa9b8tofgxc6p` (`agent_id`),
  CONSTRAINT `FK7qct241djo57xa9b8tofgxc6p` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficiary`
--

DROP TABLE IF EXISTS `beneficiary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beneficiary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `percentage_share` bigint(20) DEFAULT NULL,
  `person_type` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `surname` varchar(255) NOT NULL,
  `policy_id` bigint(20) DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKto6ooi5gyv6om30b7nhdnjsr6` (`policy_id`),
  CONSTRAINT `FKto6ooi5gyv6om30b7nhdnjsr6` FOREIGN KEY (`policy_id`) REFERENCES `policy` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiary`
--

LOCK TABLES `beneficiary` WRITE;
/*!40000 ALTER TABLE `beneficiary` DISABLE KEYS */;
INSERT INTO `beneficiary` VALUES (1,'1991-05-19','MALE','67-1234565F-15','Sean',100,'SPOUSE','string','Huvaya',2,NULL),(2,'2021-05-20','MALE','string','string',0,'SPOUSE','string','string',2,NULL),(8,'1980-01-29','FEMALE','09-6756456Y-78','Thelma',0,'SPOUSE','string','Thomas',18,NULL),(10,'1984-08-21','FEMALE','12-4545676K-90','Christine ',0,'SPOUSE','string','Chimupererwa',40,NULL),(12,'2021-06-01','MALE','string','string',0,'SPOUSE','string','string',40,NULL),(13,'2021-06-01','MALE','string','string',0,'BENEFICIARY','string','string',40,NULL),(14,'2000-03-16','MALE','123456789j56','FREEMAN',100,'BENEFICIARY','child','Highforce',7,NULL),(15,'2000-03-16','MALE','123456789j67','FREEMAN',100,'BENEFICIARY','child','Musana',2,NULL),(16,'2000-03-16','MALE','123456789O90','Brian',100,'BENEFICIARY','father','hyfos',42,NULL),(17,'2000-03-16','MALE','12-3456789j-67','FREEMAN',100,'BENEFICIARY','mother','Highforce',39,NULL),(18,'2000-03-16','MALE','123456789y89','Sdsadsdasd',100,'BENEFICIARY','son','Asdasda',16,NULL),(20,'1980-05-14','FEMALE','89-0987656K--89','Thelma',0,'SPOUSE','string','Thomas',46,NULL),(22,'2001-06-02','MALE','string','tinashe',100,'SPOUSE','father','thomas',47,NULL),(24,'2000-03-16','MALE','qweqweqwe','Wqeqwe',100,'BENEFICIARY','qweqwe','Qweqwe',5,NULL);
/*!40000 ALTER TABLE `beneficiary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficiary_accident`
--

DROP TABLE IF EXISTS `beneficiary_accident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beneficiary_accident` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `percentage_share` bigint(20) NOT NULL,
  `person_type` varchar(255) DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `surname` varchar(255) NOT NULL,
  `accident_id` bigint(20) DEFAULT NULL,
  `policy_accident_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK711ngl7wm8dt1dl8rvirl0s40` (`accident_id`),
  KEY `FKpxi15a6o05svkamvpinq79m9f` (`policy_accident_id`),
  CONSTRAINT `FK711ngl7wm8dt1dl8rvirl0s40` FOREIGN KEY (`accident_id`) REFERENCES `accident_product` (`id`),
  CONSTRAINT `FKpxi15a6o05svkamvpinq79m9f` FOREIGN KEY (`policy_accident_id`) REFERENCES `policy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiary_accident`
--

LOCK TABLES `beneficiary_accident` WRITE;
/*!40000 ALTER TABLE `beneficiary_accident` DISABLE KEYS */;
/*!40000 ALTER TABLE `beneficiary_accident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficiary_comprehensive`
--

DROP TABLE IF EXISTS `beneficiary_comprehensive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beneficiary_comprehensive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `percentage_share` bigint(20) NOT NULL,
  `person_type` varchar(255) DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `surname` varchar(255) NOT NULL,
  `comprehensive_funeral_id` bigint(20) DEFAULT NULL,
  `policy_comprehensive_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5l6afyf8vyc4gy056vms1suis` (`comprehensive_funeral_id`),
  KEY `FKs3csg77xte1lijse95qidh7cp` (`policy_comprehensive_id`),
  CONSTRAINT `FK5l6afyf8vyc4gy056vms1suis` FOREIGN KEY (`comprehensive_funeral_id`) REFERENCES `comprehensive_funeral_product` (`id`),
  CONSTRAINT `FKs3csg77xte1lijse95qidh7cp` FOREIGN KEY (`policy_comprehensive_id`) REFERENCES `policy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiary_comprehensive`
--

LOCK TABLES `beneficiary_comprehensive` WRITE;
/*!40000 ALTER TABLE `beneficiary_comprehensive` DISABLE KEYS */;
/*!40000 ALTER TABLE `beneficiary_comprehensive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficiary_funeral`
--

DROP TABLE IF EXISTS `beneficiary_funeral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beneficiary_funeral` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `percentage_share` bigint(20) NOT NULL,
  `person_type` varchar(255) DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `surname` varchar(255) NOT NULL,
  `funeral_id` bigint(20) DEFAULT NULL,
  `policy_funeral_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ci9806koh51ujvaxwvv8u8mc` (`funeral_id`),
  KEY `FKirrl605arcr1b3mlp3n4k4i8k` (`policy_funeral_id`),
  CONSTRAINT `FK2ci9806koh51ujvaxwvv8u8mc` FOREIGN KEY (`funeral_id`) REFERENCES `funeral_product` (`id`),
  CONSTRAINT `FKirrl605arcr1b3mlp3n4k4i8k` FOREIGN KEY (`policy_funeral_id`) REFERENCES `policy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiary_funeral`
--

LOCK TABLES `beneficiary_funeral` WRITE;
/*!40000 ALTER TABLE `beneficiary_funeral` DISABLE KEYS */;
/*!40000 ALTER TABLE `beneficiary_funeral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficiary_savings`
--

DROP TABLE IF EXISTS `beneficiary_savings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beneficiary_savings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `percentage_share` bigint(20) NOT NULL,
  `person_type` varchar(255) DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `surname` varchar(255) NOT NULL,
  `policy_savings_id` bigint(20) DEFAULT NULL,
  `savings_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3g8oocdpv54rs25tb47trd10x` (`policy_savings_id`),
  KEY `FK8wuty16mkbk2aqd2ry0pfxrj6` (`savings_id`),
  CONSTRAINT `FK3g8oocdpv54rs25tb47trd10x` FOREIGN KEY (`policy_savings_id`) REFERENCES `policy` (`id`),
  CONSTRAINT `FK8wuty16mkbk2aqd2ry0pfxrj6` FOREIGN KEY (`savings_id`) REFERENCES `savings_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiary_savings`
--

LOCK TABLES `beneficiary_savings` WRITE;
/*!40000 ALTER TABLE `beneficiary_savings` DISABLE KEYS */;
/*!40000 ALTER TABLE `beneficiary_savings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biller`
--

DROP TABLE IF EXISTS `biller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biller` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biller`
--

LOCK TABLES `biller` WRITE;
/*!40000 ALTER TABLE `biller` DISABLE KEYS */;
/*!40000 ALTER TABLE `biller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `biller_account`
--

DROP TABLE IF EXISTS `biller_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `biller_account` (
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `available_balance` decimal(19,2) DEFAULT NULL,
  `commission_balance` decimal(19,2) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `ledger_balance` decimal(19,2) DEFAULT NULL,
  `reserved_balance` decimal(19,2) DEFAULT NULL,
  `biller_id` bigint(20) NOT NULL,
  PRIMARY KEY (`biller_id`),
  CONSTRAINT `FK8ws90mm7732jaminngnqh7cyq` FOREIGN KEY (`biller_id`) REFERENCES `biller` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biller_account`
--

LOCK TABLES `biller_account` WRITE;
/*!40000 ALTER TABLE `biller_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `biller_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancel_policy`
--

DROP TABLE IF EXISTS `cancel_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancel_policy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cancel_policy_status` int(11) DEFAULT NULL,
  `effective_date` date DEFAULT NULL,
  `more_information` varchar(255) DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `reason` int(11) DEFAULT NULL,
  `submission_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancel_policy`
--

LOCK TABLES `cancel_policy` WRITE;
/*!40000 ALTER TABLE `cancel_policy` DISABLE KEYS */;
/*!40000 ALTER TABLE `cancel_policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `card` (
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `subscriber_id` bigint(20) NOT NULL,
  PRIMARY KEY (`subscriber_id`),
  CONSTRAINT `FKcxr6vpmnmuaf02f7ypjilh5b2` FOREIGN KEY (`subscriber_id`) REFERENCES `subscriber` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `claim_accident`
--

DROP TABLE IF EXISTS `claim_accident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `claim_accident` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `accident_claim_reason` int(11) DEFAULT NULL,
  `accident_location` int(11) DEFAULT NULL,
  `admission` date DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `claim_status` varchar(255) DEFAULT NULL,
  `claimant_name` varchar(255) DEFAULT NULL,
  `claimant_type` int(11) DEFAULT NULL,
  `date_claim_paid` datetime(6) DEFAULT NULL,
  `dateof_injury` date DEFAULT NULL,
  `description_accident` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `hospital_admission` bit(1) DEFAULT NULL,
  `hospitalisation_date` date DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `primary_physician_address_city` varchar(255) DEFAULT NULL,
  `primary_physician_address_street` varchar(255) DEFAULT NULL,
  `primary_physician_address_suburb` varchar(255) DEFAULT NULL,
  `primary_physician_email` varchar(255) DEFAULT NULL,
  `primary_physician_fax` varchar(255) DEFAULT NULL,
  `primary_physician_name` varchar(255) DEFAULT NULL,
  `primary_physician_telephone` varchar(255) DEFAULT NULL,
  `referring_physician_address_city` varchar(255) DEFAULT NULL,
  `referring_physician_address_street` varchar(255) DEFAULT NULL,
  `referring_physician_address_suburb` varchar(255) DEFAULT NULL,
  `referring_physician_email` varchar(255) DEFAULT NULL,
  `referring_physician_fax` varchar(255) DEFAULT NULL,
  `referring_physician_name` varchar(255) DEFAULT NULL,
  `referring_physician_telephone` varchar(255) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `released` date DEFAULT NULL,
  `similar_condition` bit(1) DEFAULT NULL,
  `policy_accident_id` bigint(20) DEFAULT NULL,
  `policy_holder_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjg7u1sg1btu43jnab5ndg67ys` (`policy_accident_id`),
  KEY `FKpkji3tqgwgt9viadu951gwlwv` (`policy_holder_id`),
  CONSTRAINT `FKjg7u1sg1btu43jnab5ndg67ys` FOREIGN KEY (`policy_accident_id`) REFERENCES `policy` (`id`),
  CONSTRAINT `FKpkji3tqgwgt9viadu951gwlwv` FOREIGN KEY (`policy_holder_id`) REFERENCES `policy_holder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claim_accident`
--

LOCK TABLES `claim_accident` WRITE;
/*!40000 ALTER TABLE `claim_accident` DISABLE KEYS */;
/*!40000 ALTER TABLE `claim_accident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `claim_comprehensive`
--

DROP TABLE IF EXISTS `claim_comprehensive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `claim_comprehensive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `address_of_death_city` varchar(255) DEFAULT NULL,
  `address_of_death_street` varchar(255) DEFAULT NULL,
  `address_of_death_suburb` varchar(255) DEFAULT NULL,
  `administration_letter` varchar(255) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `birth_certificate` varchar(255) DEFAULT NULL,
  `burial_order` varchar(255) DEFAULT NULL,
  `cause_of_death` varchar(30) DEFAULT NULL,
  `certificate_medical_attendant` varchar(255) DEFAULT NULL,
  `certification_authority` varchar(255) DEFAULT NULL,
  `claim_status` varchar(255) DEFAULT NULL,
  `claimant_certificate` varchar(255) DEFAULT NULL,
  `claimant_email` varchar(50) DEFAULT NULL,
  `claimant_first_name` varchar(30) DEFAULT NULL,
  `claimant_id_number` varchar(15) DEFAULT NULL,
  `claimant_last_name` varchar(30) DEFAULT NULL,
  `claimant_postal_address_city` varchar(255) DEFAULT NULL,
  `claimant_postal_address_street` varchar(255) DEFAULT NULL,
  `claimant_postal_address_suburb` varchar(255) DEFAULT NULL,
  `claimant_telephone_number` varchar(15) DEFAULT NULL,
  `contact_place_of_death` varchar(50) DEFAULT NULL,
  `date_claim_paid` datetime(6) DEFAULT NULL,
  `date_of_death` date DEFAULT NULL,
  `death_certificate` varchar(255) DEFAULT NULL,
  `deceased_employer` varchar(30) DEFAULT NULL,
  `deceased_first_name` varchar(30) DEFAULT NULL,
  `deceased_id_number` varchar(15) DEFAULT NULL,
  `deceased_last_name` varchar(30) DEFAULT NULL,
  `deceased_occupation` varchar(30) DEFAULT NULL,
  `deceased_relationship` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `id_number` varchar(15) DEFAULT NULL,
  `marriage_certificate` varchar(255) DEFAULT NULL,
  `masters_release` varchar(255) DEFAULT NULL,
  `medical_report_document` varchar(255) DEFAULT NULL,
  `name_of_insured` varchar(50) DEFAULT NULL,
  `nationalid` varchar(255) DEFAULT NULL,
  `palour_address_city` varchar(255) DEFAULT NULL,
  `palour_address_street` varchar(255) DEFAULT NULL,
  `palour_address_suburb` varchar(255) DEFAULT NULL,
  `palour_contact_number` varchar(15) DEFAULT NULL,
  `palour_name` varchar(50) DEFAULT NULL,
  `place_of_burial` varchar(50) DEFAULT NULL,
  `place_of_death` varchar(50) DEFAULT NULL,
  `police_report` varchar(255) DEFAULT NULL,
  `policy_document` varchar(255) DEFAULT NULL,
  `policy_number` varchar(30) DEFAULT NULL,
  `telephone_number` varchar(15) DEFAULT NULL,
  `venue_of_death` varchar(50) DEFAULT NULL,
  `policy_comprehensive_id` bigint(20) DEFAULT NULL,
  `policy_holder_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr3fi124ayt3t2gsmt6ghe74i` (`policy_comprehensive_id`),
  KEY `FKif2cp21w76woc4kj38s3nor7q` (`policy_holder_id`),
  CONSTRAINT `FKif2cp21w76woc4kj38s3nor7q` FOREIGN KEY (`policy_holder_id`) REFERENCES `policy_holder` (`id`),
  CONSTRAINT `FKr3fi124ayt3t2gsmt6ghe74i` FOREIGN KEY (`policy_comprehensive_id`) REFERENCES `policy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claim_comprehensive`
--

LOCK TABLES `claim_comprehensive` WRITE;
/*!40000 ALTER TABLE `claim_comprehensive` DISABLE KEYS */;
/*!40000 ALTER TABLE `claim_comprehensive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `claim_funeral`
--

DROP TABLE IF EXISTS `claim_funeral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `claim_funeral` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `address_of_death_city` varchar(255) DEFAULT NULL,
  `address_of_death_street` varchar(255) DEFAULT NULL,
  `address_of_death_suburb` varchar(255) DEFAULT NULL,
  `administration_letter` varchar(255) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `birth_certificate` varchar(255) DEFAULT NULL,
  `burial_order` varchar(255) DEFAULT NULL,
  `cause_of_death` varchar(30) DEFAULT NULL,
  `certificate_medical_attendant` varchar(255) DEFAULT NULL,
  `certification_authority` varchar(255) DEFAULT NULL,
  `claim_status` varchar(255) DEFAULT NULL,
  `claimant_certificate` varchar(255) DEFAULT NULL,
  `claimant_email` varchar(50) DEFAULT NULL,
  `claimant_first_name` varchar(30) DEFAULT NULL,
  `claimant_id_number` varchar(15) DEFAULT NULL,
  `claimant_last_name` varchar(30) DEFAULT NULL,
  `claimant_postal_address_city` varchar(255) DEFAULT NULL,
  `claimant_postal_address_street` varchar(255) DEFAULT NULL,
  `claimant_postal_address_suburb` varchar(255) DEFAULT NULL,
  `claimant_telephone_number` varchar(15) DEFAULT NULL,
  `contact_place_of_death` varchar(50) DEFAULT NULL,
  `date_claim_paid` datetime(6) DEFAULT NULL,
  `date_of_death` date DEFAULT NULL,
  `death_certificate` varchar(255) DEFAULT NULL,
  `deceased_employer` varchar(30) DEFAULT NULL,
  `deceased_first_name` varchar(30) DEFAULT NULL,
  `deceased_id_number` varchar(15) DEFAULT NULL,
  `deceased_last_name` varchar(30) DEFAULT NULL,
  `deceased_occupation` varchar(30) DEFAULT NULL,
  `deceased_relationship` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `id_number` varchar(15) DEFAULT NULL,
  `marriage_certificate` varchar(255) DEFAULT NULL,
  `masters_release` varchar(255) DEFAULT NULL,
  `medical_report_document` varchar(255) DEFAULT NULL,
  `name_of_insured` varchar(50) DEFAULT NULL,
  `nationalid` varchar(255) DEFAULT NULL,
  `palour_address_city` varchar(255) DEFAULT NULL,
  `palour_address_street` varchar(255) DEFAULT NULL,
  `palour_address_suburb` varchar(255) DEFAULT NULL,
  `palour_contact_number` varchar(15) DEFAULT NULL,
  `palour_name` varchar(50) DEFAULT NULL,
  `place_of_burial` varchar(50) DEFAULT NULL,
  `place_of_death` varchar(50) DEFAULT NULL,
  `police_report` varchar(255) DEFAULT NULL,
  `policy_document` varchar(255) DEFAULT NULL,
  `policy_number` varchar(30) DEFAULT NULL,
  `telephone_number` varchar(15) DEFAULT NULL,
  `venue_of_death` varchar(50) DEFAULT NULL,
  `policy_funeral_id` bigint(20) DEFAULT NULL,
  `policy_holder_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs4ray6bqv3c83uvryro86vw92` (`policy_funeral_id`),
  KEY `FK7yj6kisepyw3856qa3113yigb` (`policy_holder_id`),
  CONSTRAINT `FK7yj6kisepyw3856qa3113yigb` FOREIGN KEY (`policy_holder_id`) REFERENCES `policy_holder` (`id`),
  CONSTRAINT `FKs4ray6bqv3c83uvryro86vw92` FOREIGN KEY (`policy_funeral_id`) REFERENCES `policy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claim_funeral`
--

LOCK TABLES `claim_funeral` WRITE;
/*!40000 ALTER TABLE `claim_funeral` DISABLE KEYS */;
/*!40000 ALTER TABLE `claim_funeral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `claim_savings`
--

DROP TABLE IF EXISTS `claim_savings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `claim_savings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claim_savings`
--

LOCK TABLES `claim_savings` WRITE;
/*!40000 ALTER TABLE `claim_savings` DISABLE KEYS */;
/*!40000 ALTER TABLE `claim_savings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commission`
--

DROP TABLE IF EXISTS `commission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `executive_agent_commission_rate` decimal(19,2) DEFAULT NULL,
  `executive_unit_leader_commission_rate` decimal(19,2) DEFAULT NULL,
  `month` int(11) NOT NULL,
  `parent_agent_commission_rate` decimal(19,2) DEFAULT NULL,
  `parent_unit_leader_commission_rate` decimal(19,2) DEFAULT NULL,
  `policy_type` varchar(255) DEFAULT NULL,
  `tied_agent_commission_rate` decimal(19,2) DEFAULT NULL,
  `tied_unit_leader_commission_rate` decimal(19,2) DEFAULT NULL,
  `upsell_agent` decimal(19,2) DEFAULT NULL,
  `upsell_manager` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission`
--

LOCK TABLES `commission` WRITE;
/*!40000 ALTER TABLE `commission` DISABLE KEYS */;
/*!40000 ALTER TABLE `commission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commission_payment`
--

DROP TABLE IF EXISTS `commission_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commission_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `payment_channel` varchar(255) DEFAULT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  `payment_reference` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `policy_type` varchar(255) DEFAULT NULL,
  `agent_id` bigint(20) DEFAULT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  `policy_holder_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrb8a24y78qbw5ga5e5q9g4vbf` (`agent_id`),
  KEY `FKkn94feiuuwewylyio94fs4sff` (`manager_id`),
  KEY `FKtqbdb3smcphdhpwmnd2ategwp` (`policy_holder_id`),
  CONSTRAINT `FKkn94feiuuwewylyio94fs4sff` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`),
  CONSTRAINT `FKrb8a24y78qbw5ga5e5q9g4vbf` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`),
  CONSTRAINT `FKtqbdb3smcphdhpwmnd2ategwp` FOREIGN KEY (`policy_holder_id`) REFERENCES `policy_holder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission_payment`
--

LOCK TABLES `commission_payment` WRITE;
/*!40000 ALTER TABLE `commission_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `commission_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commission_report`
--

DROP TABLE IF EXISTS `commission_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commission_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `agent_id` bigint(20) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `payment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqnhfoxp4f1td308flnl4carsf` (`payment_id`),
  CONSTRAINT `FKqnhfoxp4f1td308flnl4carsf` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission_report`
--

LOCK TABLES `commission_report` WRITE;
/*!40000 ALTER TABLE `commission_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `commission_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comprehensive_funeral_product`
--

DROP TABLE IF EXISTS `comprehensive_funeral_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comprehensive_funeral_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clawback_period` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `person_type` varchar(255) DEFAULT NULL,
  `premium` decimal(19,2) DEFAULT NULL,
  `sum_assured` decimal(19,2) DEFAULT NULL,
  `term` varchar(255) DEFAULT NULL,
  `comprehensive_funeral_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpydh6j55ywrfxk32ww51lo7cn` (`comprehensive_funeral_id`),
  CONSTRAINT `FKpydh6j55ywrfxk32ww51lo7cn` FOREIGN KEY (`comprehensive_funeral_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprehensive_funeral_product`
--

LOCK TABLES `comprehensive_funeral_product` WRITE;
/*!40000 ALTER TABLE `comprehensive_funeral_product` DISABLE KEYS */;
INSERT INTO `comprehensive_funeral_product` VALUES (3,4,'comprehensive_34000','PRINCIPAL',20.40,34000.00,'TEN_YEAR',4);
/*!40000 ALTER TABLE `comprehensive_funeral_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_person`
--

DROP TABLE IF EXISTS `contact_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_person` (
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `landline` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `agent_id` bigint(20) NOT NULL,
  PRIMARY KEY (`agent_id`),
  CONSTRAINT `FK79kn3vhg2pms4g1obovtf31b0` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_person`
--

LOCK TABLES `contact_person` WRITE;
/*!40000 ALTER TABLE `contact_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `imei` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `agent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK47fg7thc70hmc19umewdrgbrs` (`agent_id`),
  CONSTRAINT `FK47fg7thc70hmc19umewdrgbrs` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employer`
--

DROP TABLE IF EXISTS `employer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `suburb` varchar(255) DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `representative` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employer`
--

LOCK TABLES `employer` WRITE;
/*!40000 ALTER TABLE `employer` DISABLE KEYS */;
INSERT INTO `employer` VALUES (6,'system','2021-05-24 14:45:42.254816',NULL,'2021-05-24 14:45:42.254817',1,NULL,NULL,NULL,0.00,'0779258741','chakon@gmail.com','Chakon','Tendai Gatsi');
/*!40000 ALTER TABLE `employer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exception_report`
--

DROP TABLE IF EXISTS `exception_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exception_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `biller_name` varchar(255) DEFAULT NULL,
  `failure_reason` varchar(255) DEFAULT NULL,
  `merchant_mobile` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `receiver_mobile` varchar(255) DEFAULT NULL,
  `subscriber_mobile` varchar(255) DEFAULT NULL,
  `transaction_date` datetime(6) DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exception_report`
--

LOCK TABLES `exception_report` WRITE;
/*!40000 ALTER TABLE `exception_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `exception_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `failure_report`
--

DROP TABLE IF EXISTS `failure_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `failure_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `biller_name` varchar(255) DEFAULT NULL,
  `failure_reason` varchar(255) DEFAULT NULL,
  `merchant_mobile` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `subscriber_mobile` varchar(255) DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `failure_report`
--

LOCK TABLES `failure_report` WRITE;
/*!40000 ALTER TABLE `failure_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `failure_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_suspence_account`
--

DROP TABLE IF EXISTS `fee_suspence_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fee_suspence_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `currency_code` int(11) DEFAULT NULL,
  `entry_type` varchar(255) DEFAULT NULL,
  `fee` decimal(19,2) DEFAULT NULL,
  `original_transaction_id` bigint(20) DEFAULT NULL,
  `transaction_amount` decimal(19,2) DEFAULT NULL,
  `transaction_date` datetime(6) DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_suspence_account`
--

LOCK TABLES `fee_suspence_account` WRITE;
/*!40000 ALTER TABLE `fee_suspence_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `fee_suspence_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `financial_advisor`
--

DROP TABLE IF EXISTS `financial_advisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `financial_advisor` (
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `policy_holder_id` bigint(20) NOT NULL,
  PRIMARY KEY (`policy_holder_id`),
  CONSTRAINT `FKkonjpv03msdm4aarhn3guh51j` FOREIGN KEY (`policy_holder_id`) REFERENCES `policy_holder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `financial_advisor`
--

LOCK TABLES `financial_advisor` WRITE;
/*!40000 ALTER TABLE `financial_advisor` DISABLE KEYS */;
/*!40000 ALTER TABLE `financial_advisor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funeral_product`
--

DROP TABLE IF EXISTS `funeral_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funeral_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clawback_period` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `person_type` varchar(255) DEFAULT NULL,
  `premium` decimal(19,2) DEFAULT NULL,
  `sum_assured` decimal(19,2) DEFAULT NULL,
  `funeral_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn35ev3kdq7cperlgc3w7wu9td` (`funeral_id`),
  CONSTRAINT `FKn35ev3kdq7cperlgc3w7wu9td` FOREIGN KEY (`funeral_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funeral_product`
--

LOCK TABLES `funeral_product` WRITE;
/*!40000 ALTER TABLE `funeral_product` DISABLE KEYS */;
INSERT INTO `funeral_product` VALUES (3,4,'funeral_100000','PRINCIPAL',60.00,100000.00,3),(5,4,'funeral_12000','PRINCIPAL',7.20,12000.00,3);
/*!40000 ALTER TABLE `funeral_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imt_tax_suspense_account`
--

DROP TABLE IF EXISTS `imt_tax_suspense_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imt_tax_suspense_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `currency_code` int(11) DEFAULT NULL,
  `entry_type` varchar(255) DEFAULT NULL,
  `original_transaction_id` bigint(20) DEFAULT NULL,
  `tax` decimal(19,2) DEFAULT NULL,
  `transaction_amount` decimal(19,2) DEFAULT NULL,
  `transaction_date` datetime(6) DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imt_tax_suspense_account`
--

LOCK TABLES `imt_tax_suspense_account` WRITE;
/*!40000 ALTER TABLE `imt_tax_suspense_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `imt_tax_suspense_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interest`
--

DROP TABLE IF EXISTS `interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `month` int(11) DEFAULT NULL,
  `rate` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_plvjvru190beutx7o0tn3q5lr` (`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interest`
--

LOCK TABLES `interest` WRITE;
/*!40000 ALTER TABLE `interest` DISABLE KEYS */;
/*!40000 ALTER TABLE `interest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `application_form_url` varchar(255) DEFAULT NULL,
  `closing_balance` decimal(19,2) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `invoice_status` varchar(32) NOT NULL,
  `invoicing_date` date DEFAULT NULL,
  `opening_balance` decimal(19,2) DEFAULT NULL,
  `employer_id` bigint(20) DEFAULT NULL,
  `policy_holder_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdv29l1u94flhv5v2iuh7a1khb` (`employer_id`),
  KEY `FKpf4vreeeb8u5arhqccydham7a` (`policy_holder_id`),
  CONSTRAINT `FKdv29l1u94flhv5v2iuh7a1khb` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`),
  CONSTRAINT `FKpf4vreeeb8u5arhqccydham7a` FOREIGN KEY (`policy_holder_id`) REFERENCES `policy_holder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_item`
--

DROP TABLE IF EXISTS `invoice_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `beneficiary` varchar(64) DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `policy_type` varchar(255) DEFAULT NULL,
  `policyholder` varchar(64) DEFAULT NULL,
  `price` decimal(19,2) NOT NULL,
  `product` varchar(32) NOT NULL,
  `invoice_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbu6tmpd0mtgu9wrw5bj5uv09v` (`invoice_id`),
  CONSTRAINT `FKbu6tmpd0mtgu9wrw5bj5uv09v` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_item`
--

LOCK TABLES `invoice_item` WRITE;
/*!40000 ALTER TABLE `invoice_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legal_guardian`
--

DROP TABLE IF EXISTS `legal_guardian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legal_guardian` (
  `contact_number` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `policy_holder_id` bigint(20) NOT NULL,
  PRIMARY KEY (`policy_holder_id`),
  CONSTRAINT `FKel7higrmv48tl73vqahd09puo` FOREIGN KEY (`policy_holder_id`) REFERENCES `policy_holder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legal_guardian`
--

LOCK TABLES `legal_guardian` WRITE;
/*!40000 ALTER TABLE `legal_guardian` DISABLE KEYS */;
/*!40000 ALTER TABLE `legal_guardian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (2,'system','2021-05-14 13:18:50.515005',NULL,'2021-05-14 13:18:50.515006',2,NULL,'musana@gmail.com','Tinashe','Masango'),(13,'system','2021-05-19 10:02:07.695882',NULL,'2021-05-19 10:02:07.695883',0,'Harare Central','smatsa@gmail.com','Sarah','Matsa'),(14,'system','2021-06-01 07:34:05.996531',NULL,'2021-06-01 07:34:05.996532',0,'Harare','email@manager.com','Captain Manager','Another');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_bank_account`
--

DROP TABLE IF EXISTS `my_bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_bank_account` (
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `available_balance` decimal(19,2) DEFAULT NULL,
  `commission_balance` decimal(19,2) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `ledger_balance` decimal(19,2) DEFAULT NULL,
  `reserved_balance` decimal(19,2) DEFAULT NULL,
  `bank_id` bigint(20) NOT NULL,
  PRIMARY KEY (`bank_id`),
  CONSTRAINT `FKmlvv6qj4getr0elidxyadr23s` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_bank_account`
--

LOCK TABLES `my_bank_account` WRITE;
/*!40000 ALTER TABLE `my_bank_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `my_bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operator`
--

DROP TABLE IF EXISTS `operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `pin_block` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `agent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj5kc9jervgobwc7qbq3pi5idh` (`agent_id`),
  CONSTRAINT `FKj5kc9jervgobwc7qbq3pi5idh` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operator`
--

LOCK TABLES `operator` WRITE;
/*!40000 ALTER TABLE `operator` DISABLE KEYS */;
/*!40000 ALTER TABLE `operator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `payment_channel` varchar(255) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_reference` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `invoice_id` bigint(20) DEFAULT NULL,
  `policy_holder_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsb24p8f52refbb80qwp4gem9n` (`invoice_id`),
  KEY `FKpvm7u9qus1cf5olj2o1c4qcie` (`policy_holder_id`),
  CONSTRAINT `FKpvm7u9qus1cf5olj2o1c4qcie` FOREIGN KEY (`policy_holder_id`) REFERENCES `policy_holder` (`id`),
  CONSTRAINT `FKsb24p8f52refbb80qwp4gem9n` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penalty`
--

DROP TABLE IF EXISTS `penalty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `penalty` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `month` int(11) DEFAULT NULL,
  `rate` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_js6l0ftu0vdfnghiv54fb7825` (`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penalty`
--

LOCK TABLES `penalty` WRITE;
/*!40000 ALTER TABLE `penalty` DISABLE KEYS */;
/*!40000 ALTER TABLE `penalty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platform_account`
--

DROP TABLE IF EXISTS `platform_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `platform_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `original_transaction_id` varchar(255) DEFAULT NULL,
  `transaction_date` datetime(6) DEFAULT NULL,
  `transaction_id` bigint(20) DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platform_account`
--

LOCK TABLES `platform_account` WRITE;
/*!40000 ALTER TABLE `platform_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `platform_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy`
--

DROP TABLE IF EXISTS `policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `application_form_url` varchar(255) DEFAULT NULL,
  `cancel_date` date DEFAULT NULL,
  `commencement_date` date DEFAULT NULL,
  `first_payment_date` date DEFAULT NULL,
  `next_invoicing_date` date DEFAULT NULL,
  `payment_frequency` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `policy_number` varchar(20) DEFAULT NULL,
  `policy_state` varchar(255) DEFAULT NULL,
  `policy_status` varchar(255) DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `agent_id` bigint(20) DEFAULT NULL,
  `policy_holder_id` bigint(20) DEFAULT NULL,
  `savings_product_id` bigint(20) DEFAULT NULL,
  `accident_product_id` bigint(20) DEFAULT NULL,
  `comprehensive_funeral_product_id` bigint(20) DEFAULT NULL,
  `funeral_product_id` bigint(20) DEFAULT NULL,
  `policy_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l7q2fb9fv4pqw1m40p4nfi7ir` (`policy_number`),
  UNIQUE KEY `UK_6bk47o33btcw7d12vksoeqht8` (`policy_number`),
  KEY `FK639vo7fd0ma0tvyr4l13698eu` (`agent_id`),
  KEY `FKm3lkqscm28bjujct48pvolpw0` (`policy_holder_id`),
  KEY `FKmgvga8u4itba3s9gqbh72l1w1` (`savings_product_id`),
  KEY `FK7ef1upyi875ocxa5ii4x0w5yn` (`accident_product_id`),
  KEY `FK1twjhomarlbrljyyfro0i3cfe` (`comprehensive_funeral_product_id`),
  KEY `FKa0mqerkgfuv2ba2ggif2mmysl` (`funeral_product_id`),
  CONSTRAINT `FK1twjhomarlbrljyyfro0i3cfe` FOREIGN KEY (`comprehensive_funeral_product_id`) REFERENCES `comprehensive_funeral_product` (`id`),
  CONSTRAINT `FK639vo7fd0ma0tvyr4l13698eu` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`),
  CONSTRAINT `FK7ef1upyi875ocxa5ii4x0w5yn` FOREIGN KEY (`accident_product_id`) REFERENCES `accident_product` (`id`),
  CONSTRAINT `FKa0mqerkgfuv2ba2ggif2mmysl` FOREIGN KEY (`funeral_product_id`) REFERENCES `funeral_product` (`id`),
  CONSTRAINT `FKm3lkqscm28bjujct48pvolpw0` FOREIGN KEY (`policy_holder_id`) REFERENCES `policy_holder` (`id`),
  CONSTRAINT `FKmgvga8u4itba3s9gqbh72l1w1` FOREIGN KEY (`savings_product_id`) REFERENCES `savings_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy`
--

LOCK TABLES `policy` WRITE;
/*!40000 ALTER TABLE `policy` DISABLE KEYS */;
INSERT INTO `policy` VALUES ('PolicyAccident',2,'system','2021-05-19 07:55:06.286324',NULL,'2021-05-19 07:55:06.286325',0,'hardcoded_sample_file.pdf',NULL,NULL,NULL,'2021-06-01','QUARTERLY','CASH','960794P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,2,NULL,4,NULL,NULL,NULL),('PolicySavings',3,'system','2021-05-26 08:36:41.752045',NULL,'2021-05-26 08:36:41.752046',0,'string',NULL,'2021-05-26',NULL,'2021-06-01','MONTHLY','CASH','539617P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',0.00,9,1,3,NULL,NULL,NULL,NULL),('PolicyAccident',5,'system','2021-05-27 12:35:28.946787',NULL,'2021-05-27 12:35:28.946788',0,'string',NULL,NULL,NULL,'2021-06-01','MONTHLY','CASH','050527P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,2,NULL,4,NULL,NULL,NULL),('PolicySavings',6,'system','2021-05-31 09:59:55.475081',NULL,'2021-05-31 09:59:55.475082',0,'63a0812f-b614-4056-9f84-332f02f5b926.pdf',NULL,'2021-08-01',NULL,'2021-09-01','MONTHLY','CASH','045358P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',0.00,9,11,3,NULL,NULL,NULL,NULL),('PolicyAccident',7,'system','2021-05-31 12:31:12.452972',NULL,'2021-05-31 12:31:12.452973',0,'5758b950-506d-403b-90d7-6e7f644b07c7.pdf',NULL,NULL,NULL,'2021-06-01','QUARTERLY','CASH','705071P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',8,'system','2021-05-31 12:33:00.732527',NULL,'2021-05-31 12:33:00.732528',0,'64285b2d-1fa2-4f15-954d-c1e706848cf4.pdf',NULL,NULL,NULL,'2021-06-01','QUARTERLY','CASH','072475P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',9,'system','2021-05-31 12:33:29.959312',NULL,'2021-05-31 12:33:29.959312',0,'64285b2d-1fa2-4f15-954d-c1e706848cf4.pdf',NULL,NULL,NULL,'2021-06-01','QUARTERLY','CASH','494383P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',10,'system','2021-05-31 12:38:03.513582',NULL,'2021-05-31 12:38:03.513582',0,'08cb834e-7f6e-4953-a418-13f64142c85d.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'222714P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',11,'system','2021-05-31 12:45:12.563516',NULL,'2021-05-31 12:45:12.563517',0,'08cb834e-7f6e-4953-a418-13f64142c85d.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'749016P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',12,'system','2021-05-31 12:46:04.376036',NULL,'2021-05-31 12:46:04.376036',0,'4a5b7aa5-e030-495b-a78b-eaad6306ee46.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'141090P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',13,'system','2021-05-31 12:46:44.926780',NULL,'2021-05-31 12:46:44.926781',0,'4a5b7aa5-e030-495b-a78b-eaad6306ee46.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'067910P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',14,'system','2021-05-31 12:56:29.364071',NULL,'2021-05-31 12:56:29.364072',0,'4a5b7aa5-e030-495b-a78b-eaad6306ee46.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'240215P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',15,'system','2021-05-31 13:04:27.431854',NULL,'2021-05-31 13:04:27.431855',0,'d470de0a-c151-4f0d-b463-1c88186f4914.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'983022P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',16,'system','2021-05-31 13:09:56.555570',NULL,'2021-05-31 13:09:56.555570',0,'d470de0a-c151-4f0d-b463-1c88186f4914.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'027555P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',17,'system','2021-05-31 13:13:07.022349',NULL,'2021-05-31 13:13:07.022350',0,'22e7bdac-cda7-49f1-85dd-0547acad500a.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'269932P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',18,'system','2021-05-31 13:14:04.156207',NULL,'2021-05-31 13:14:04.156208',0,'22e7bdac-cda7-49f1-85dd-0547acad500a.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'581334P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyFuneral',19,'system','2021-05-31 13:21:36.984743',NULL,'2021-05-31 13:21:36.984743',0,'16a53591-5916-4ec6-b8c6-2b65558b9f94.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'662594P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,3,NULL),('PolicyFuneral',20,'system','2021-05-31 13:23:18.962294',NULL,'2021-05-31 13:23:18.962294',0,'16a53591-5916-4ec6-b8c6-2b65558b9f94.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'421820P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,3,NULL),('PolicyFuneral',21,'system','2021-05-31 13:24:03.605616',NULL,'2021-05-31 13:24:03.605616',0,'16a53591-5916-4ec6-b8c6-2b65558b9f94.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'139732P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,3,NULL),('PolicyFuneral',22,'system','2021-05-31 13:30:04.798992',NULL,'2021-05-31 13:30:04.798992',0,'16a53591-5916-4ec6-b8c6-2b65558b9f94.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'497266P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,3,NULL),('PolicyFuneral',23,'system','2021-05-31 13:30:43.982871',NULL,'2021-05-31 13:30:43.982871',0,'16a53591-5916-4ec6-b8c6-2b65558b9f94.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'773484P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,3,NULL),('PolicyFuneral',24,'system','2021-05-31 13:31:09.374769',NULL,'2021-05-31 13:31:09.374769',0,'16a53591-5916-4ec6-b8c6-2b65558b9f94.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'176524P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,3,NULL),('PolicyFuneral',25,'system','2021-05-31 13:34:02.798071',NULL,'2021-05-31 13:34:02.798072',0,'16a53591-5916-4ec6-b8c6-2b65558b9f94.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'530650P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,3,NULL),('PolicyFuneral',26,'system','2021-05-31 13:36:52.035655',NULL,'2021-05-31 13:36:52.035655',0,'1ff8a6cd-d47c-429d-b133-ed6ec11b9911.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'632874P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,3,NULL),('PolicyFuneral',27,'system','2021-05-31 13:38:54.282229',NULL,'2021-05-31 13:38:54.282229',0,'1ff8a6cd-d47c-429d-b133-ed6ec11b9911.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'786385P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,3,NULL),('PolicyFuneral',28,'system','2021-05-31 13:39:48.425551',NULL,'2021-05-31 13:39:48.425551',0,'2cc21cc2-3704-46ed-9639-835fe79ddd81.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'776852P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,3,NULL),('PolicyFuneral',29,'system','2021-05-31 15:10:26.210567',NULL,'2021-05-31 15:10:26.210567',0,'acb9c3b6-b0ae-48c6-8cba-010bf2e85477.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'591707P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,5,NULL),('PolicyFuneral',30,'system','2021-05-31 15:23:25.939224',NULL,'2021-05-31 15:23:25.939224',0,'24bc6e81-e09c-4b02-8988-3ab02ae6f612.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'088720P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,5,NULL),('PolicyFuneral',31,'system','2021-05-31 15:26:03.168011',NULL,'2021-05-31 15:26:03.168011',0,'24bc6e81-e09c-4b02-8988-3ab02ae6f612.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'787383P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,5,NULL),('PolicyFuneral',32,'system','2021-05-31 15:41:02.593516',NULL,'2021-05-31 15:41:02.593516',0,'19e42a96-052a-48cb-b6b2-a82e488518be.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'008662P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,5,NULL),('PolicyFuneral',33,'system','2021-05-31 15:42:06.851235',NULL,'2021-05-31 15:42:06.851236',0,'19e42a96-052a-48cb-b6b2-a82e488518be.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'535498P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,5,NULL),('PolicyFuneral',34,'system','2021-05-31 15:44:16.792487',NULL,'2021-05-31 15:44:16.792487',0,'a56be1c8-134e-49f9-a0cf-891cdc59926e.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'440453P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,5,NULL),('PolicyFuneral',35,'system','2021-05-31 15:59:38.975448',NULL,'2021-05-31 15:59:38.975448',0,'string',NULL,NULL,NULL,'2021-06-01','MONTHLY','CASH','409601P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,5,NULL),('PolicyFuneral',36,'system','2021-05-31 16:00:31.233654',NULL,'2021-05-31 16:00:31.233655',0,'65331f0f-c9ed-4ed7-8bb7-182e727c6740.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'515698P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,5,NULL),('PolicyFuneral',37,'system','2021-05-31 16:02:50.676816',NULL,'2021-05-31 16:02:50.676817',0,'65331f0f-c9ed-4ed7-8bb7-182e727c6740.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'335014P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,5,NULL),('PolicyFuneral',38,'system','2021-05-31 16:03:11.565620',NULL,'2021-05-31 16:03:11.565620',0,'65331f0f-c9ed-4ed7-8bb7-182e727c6740.pdf',NULL,NULL,NULL,'2021-06-01',NULL,NULL,'191219P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,NULL,5,NULL),('PolicyAccident',39,'system','2021-06-01 06:41:51.228399',NULL,'2021-06-01 06:41:51.228400',0,'4b24bf0d-2ddb-49e6-8409-60f541988fc0.pdf',NULL,'2021-07-01',NULL,'2021-07-01',NULL,NULL,'677825P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,14,NULL,4,NULL,NULL,NULL),('PolicyAccident',40,'system','2021-06-01 06:49:34.575338',NULL,'2021-06-01 06:49:34.575339',0,'e5a27942-5f71-4c51-b1ed-0def1e53d939.pdf',NULL,'2021-08-01',NULL,'2021-07-01','QUARTERLY','CASH','905338P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,14,NULL,6,NULL,NULL,NULL),('PolicyAccident',41,'system','2021-06-01 07:02:51.308375',NULL,'2021-06-01 07:02:51.308375',0,'8bbd0d47-3a70-42b6-8af2-095da2223793.pdf',NULL,'2021-08-01',NULL,'2021-07-01',NULL,NULL,'814527P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,14,NULL,4,NULL,NULL,NULL),('PolicyAccident',42,'system','2021-06-01 07:04:39.639938',NULL,'2021-06-01 07:04:39.639938',0,'5d592531-74e7-49e1-a54f-7381b5c57217.pdf',NULL,'2021-07-01',NULL,'2021-07-01',NULL,NULL,'686059P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,14,NULL,7,NULL,NULL,NULL),('PolicyAccident',43,'system','2021-06-01 07:09:44.106883',NULL,'2021-06-01 07:09:44.106884',0,'ff86d3d3-44c0-4262-96f5-bca389632e34.pdf',NULL,'2021-08-01',NULL,'2021-07-01','QUARTERLY','CASH','723138P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,14,NULL,4,NULL,NULL,NULL),('PolicyAccident',44,'system','2021-06-02 07:29:24.537616',NULL,'2021-06-02 07:29:24.537616',0,'222aeee3-d81f-4b0b-ac7a-5133abda151c.pdf',NULL,'2021-07-01',NULL,'2021-07-01',NULL,NULL,'138473P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',45,'system','2021-06-02 10:55:20.608702',NULL,'2021-06-02 10:55:20.608702',0,'649dda80-6afa-4f7b-a7e6-678b1372377f.pdf',NULL,'2021-07-01',NULL,'2021-07-01','MONTHLY','CASH','182395P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,15,NULL,4,NULL,NULL,NULL),('PolicyAccident',46,'system','2021-06-02 12:29:00.414485',NULL,'2021-06-02 12:29:00.414485',0,'e21df327-73f1-46ad-ad7d-4d4a1e6f5500.pdf',NULL,'2021-07-01',NULL,'2021-07-01',NULL,NULL,'522028P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',47,'system','2021-06-02 12:54:26.765721',NULL,'2021-06-02 12:54:26.765721',0,'fb207868-69d6-4748-9ffb-a1ecda627317.pdf',NULL,'2021-07-01',NULL,'2021-07-01',NULL,NULL,'269197P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,11,12,NULL,4,NULL,NULL,NULL),('PolicyAccident',48,'system','2021-06-02 13:24:41.507838',NULL,'2021-06-02 13:24:41.507839',0,'b4a7ee9e-9a65-4bbe-826b-31589f2d78fd.pdf',NULL,'2021-07-01',NULL,'2021-07-01',NULL,NULL,'420057P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,11,2,NULL,4,NULL,NULL,NULL),('PolicyAccident',49,'system','2021-06-02 13:31:34.591280',NULL,'2021-06-02 13:31:34.591280',0,'7458eda5-4432-4d60-90b4-2d7d6ca10a8b.pdf',NULL,'2021-07-01',NULL,'2021-07-01',NULL,NULL,'769972P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,2,NULL,4,NULL,NULL,NULL),('PolicySavings',50,'system','2021-06-02 13:51:02.559612',NULL,'2021-06-02 13:51:02.559613',0,'93d55d33-4eb1-4172-8d15-19515aebc1d9.pdf',NULL,'2021-07-01',NULL,'2021-08-01','MONTHLY','CASH','467923P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',0.00,9,12,3,NULL,NULL,NULL,NULL),('PolicyFuneral',51,'system','2021-06-02 13:52:11.980281',NULL,'2021-06-02 13:52:11.980285',0,'522b6718-63b0-4690-a2bc-493b761b2839.pdf',NULL,NULL,NULL,'2021-07-01','MONTHLY','CASH','954989P01','ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,11,12,NULL,NULL,NULL,5,NULL),('PolicyComprehensive',52,'system','2021-06-02 13:53:11.342255',NULL,'2021-06-02 13:53:11.342255',0,'4782e013-fb7d-462c-a97a-f210388e9b99.pdf',NULL,'2021-07-01',NULL,'2021-08-01','MONTHLY','CASH',NULL,'ACTIVE','WAITING_FOR_FIRST_PAYMENT',NULL,9,12,NULL,NULL,3,NULL,NULL);
/*!40000 ALTER TABLE `policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy_holder`
--

DROP TABLE IF EXISTS `policy_holder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy_holder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `employee_number` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `id_number` varchar(12) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `mobile` varchar(255) NOT NULL,
  `occupation` varchar(255) NOT NULL,
  `person_type` varchar(255) DEFAULT NULL,
  `physical_address_city` varchar(255) DEFAULT NULL,
  `physical_address_street` varchar(255) DEFAULT NULL,
  `physical_address_suburb` varchar(255) DEFAULT NULL,
  `postal_address_city` varchar(255) DEFAULT NULL,
  `postal_address_street` varchar(255) DEFAULT NULL,
  `postal_address_suburb` varchar(255) DEFAULT NULL,
  `premium_payer` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `work_telephone` varchar(255) NOT NULL,
  `employer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_73nkdk5dcctfwvj1s55oxh3j9` (`id_number`),
  KEY `FKs177viov2ervjt5gwo83ulr2f` (`employer_id`),
  CONSTRAINT `FKs177viov2ervjt5gwo83ulr2f` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_holder`
--

LOCK TABLES `policy_holder` WRITE;
/*!40000 ALTER TABLE `policy_holder` DISABLE KEYS */;
INSERT INTO `policy_holder` VALUES (1,'system','2021-05-12 17:57:08.787943',NULL,'2021-05-12 17:57:08.787944',0,0.00,'1998-05-12','sean@gmail.com','','Sean','MALE','632031979F15','Huvaya','0773806130','Software Engineer','PRINCIPAL','Harare','3 14th Ave','Warren Park','Harare','3 14th Ave','Warren Park','MAIN_LIFE_ASSURED','ACTIVE','MR','0773806130',NULL),(2,'system','2021-05-19 07:48:23.151269',NULL,'2021-05-19 07:48:23.151269',0,0.00,'1972-02-02','mutasa@gmail.com',NULL,'George','FEMALE','236425171U89','Mutasa','0778227522','','PRINCIPAL','HARARE','882 CHAMINUKA STREET','HARARE','HARARE','882 CHAMINUKA STREET','HARARE','PREMIUM_PAYER','ACTIVE','MISS','0987654321',NULL),(11,'system','2021-05-31 09:52:44.612222',NULL,'2021-05-31 09:52:44.612223',0,0.00,'1992-07-07','Pmufa@gmail.com',NULL,'Patience','FEMALE','637894562E27','Mufanebadza','0774125896','','PRINCIPAL','Harare','212 mabvuku','Harare','Harare','212 mabvuku','Harare','MAIN_LIFE_ASSURED','ACTIVE','MISS','0778124639',NULL),(12,'system','2021-05-31 10:21:16.327855',NULL,'2021-05-31 10:21:16.327855',0,0.00,'1990-10-02','sekai@gmail.com',NULL,'SEKAI','FEMALE','638956741E27','Chaora','0779458123','','PRINCIPAL','Harare','21 Chawora Street, Mabvuku','Harare','Harare','21 Chawora Street, Mabvuku','Harare','MAIN_LIFE_ASSURED','ACTIVE','MR','0772125478',NULL),(13,'system','2021-05-31 10:43:31.755075',NULL,'2021-05-31 10:43:31.755075',0,0.00,'1985-11-05','lovemore@gmail.com',NULL,'Lovemore','MALE','637851239E27','Dewure','0779125789','','PRINCIPAL','Harare','23 murehwa','Harare','Harare','23 murehwa','Harare','MAIN_LIFE_ASSURED_AND_PREMIUM_PAYER','ACTIVE','PROF','0772452369',NULL),(14,'system','2021-05-31 19:50:37.221765',NULL,'2021-05-31 19:50:37.221766',0,0.00,'1986-11-03','takaiteyi@gmail.com',NULL,'Venon','MALE','592001300Y75','Mapfunde','0775091262','','PRINCIPAL','Harare','1 Orange Groove','Highlands','Harare','1 Orange Groove','Highlands','MAIN_LIFE_ASSURED_AND_PREMIUM_PAYER','ACTIVE','MR','0775091262',NULL),(15,'system','2021-06-02 10:52:39.344365',NULL,'2021-06-02 10:52:39.344365',0,0.00,'1993-06-04','takaiteyi@takaiteyi.com',NULL,'Captain','MALE','592001788K56','Morass','0775091262','','PRINCIPAL','Fgdg','Teghdfsg','Dfgdgdgg','Fgdg','Teghdfsg','Dfgdgdgg','MAIN_LIFE_ASSURED_AND_PREMIUM_PAYER','ACTIVE','MR','0775091262',NULL);
/*!40000 ALTER TABLE `policy_holder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `premium_payment`
--

DROP TABLE IF EXISTS `premium_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `premium_payment` (
  `id` bigint(20) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `payment_channel` varchar(255) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_reference` varchar(255) DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `policy_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `premium_payment`
--

LOCK TABLES `premium_payment` WRITE;
/*!40000 ALTER TABLE `premium_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `premium_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `product_type` varchar(255) DEFAULT NULL,
  `admin_fee` decimal(19,2) DEFAULT NULL,
  `bill_format` varchar(255) DEFAULT NULL,
  `bill_name` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `post_payment_url` varchar(255) DEFAULT NULL,
  `preauth_url` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `biller_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnjxxj5unxcy2yisksri8ek1oe` (`biller_id`),
  CONSTRAINT `FKnjxxj5unxcy2yisksri8ek1oe` FOREIGN KEY (`biller_id`) REFERENCES `biller` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('Savings',1,'system','2021-05-12 17:47:22.745086',NULL,'2021-05-12 17:47:22.745088',0,'Savings','SAVINGS',2.50,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Accident',2,'system','2021-05-12 17:47:39.804313',NULL,'2021-05-12 17:47:39.804315',0,'Accident','ACCIDENT',2.50,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Funeral',3,'system','2021-05-12 17:47:58.196258',NULL,'2021-05-12 17:47:58.196260',0,'Funeral','FUNERAL',2.50,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('ComprehensiveFuneral',4,'system','2021-05-12 17:48:34.837428',NULL,'2021-05-12 17:48:34.837430',0,'Comprehensive Funeral','COMPREHENSIVE_FUNERAL',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `representative`
--

DROP TABLE IF EXISTS `representative`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `representative` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `employer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmodnxunrf3km0d2346fjaela3` (`employer_id`),
  CONSTRAINT `FKmodnxunrf3km0d2346fjaela3` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `representative`
--

LOCK TABLES `representative` WRITE;
/*!40000 ALTER TABLE `representative` DISABLE KEYS */;
INSERT INTO `representative` VALUES (42,'HR_OFFICER','0779458123','tendaigatsi@gmail.com','tendai','gatsi',6);
/*!40000 ALTER TABLE `representative` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savings_partial_withdrawal`
--

DROP TABLE IF EXISTS `savings_partial_withdrawal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `savings_partial_withdrawal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `identity_document` varchar(255) DEFAULT NULL,
  `inception_date` date DEFAULT NULL,
  `life_assured` varchar(255) DEFAULT NULL,
  `mobile_account_name` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `policy_number` varchar(255) NOT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `withdrawal_status` int(11) DEFAULT NULL,
  `policy_savings_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrkevslb21pjijw23f8ivajtoq` (`policy_savings_id`),
  CONSTRAINT `FKrkevslb21pjijw23f8ivajtoq` FOREIGN KEY (`policy_savings_id`) REFERENCES `policy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savings_partial_withdrawal`
--

LOCK TABLES `savings_partial_withdrawal` WRITE;
/*!40000 ALTER TABLE `savings_partial_withdrawal` DISABLE KEYS */;
/*!40000 ALTER TABLE `savings_partial_withdrawal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savings_product`
--

DROP TABLE IF EXISTS `savings_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `savings_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `clawback_period` int(11) NOT NULL,
  `monthly_investment_premium` decimal(19,2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `person_type` varchar(255) DEFAULT NULL,
  `premium_waiver_rate` decimal(19,2) DEFAULT NULL,
  `savings_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5yvjw639tfglshsri71nssljs` (`savings_id`),
  CONSTRAINT `FK5yvjw639tfglshsri71nssljs` FOREIGN KEY (`savings_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savings_product`
--

LOCK TABLES `savings_product` WRITE;
/*!40000 ALTER TABLE `savings_product` DISABLE KEYS */;
INSERT INTO `savings_product` VALUES (3,'system','2021-05-26 08:36:18.704794',NULL,'2021-05-26 08:36:18.704795',0,9,8900.00,'savings_8900','PRINCIPAL',569.60,1);
/*!40000 ALTER TABLE `savings_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `savings_surrender`
--

DROP TABLE IF EXISTS `savings_surrender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `savings_surrender` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `effective_date` date DEFAULT NULL,
  `identity_document` varchar(255) DEFAULT NULL,
  `surrender_status` int(11) DEFAULT NULL,
  `surrender_value` decimal(19,2) DEFAULT NULL,
  `policy_savings_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlch6wvwpcnlm4qvp167oan7vs` (`policy_savings_id`),
  CONSTRAINT `FKlch6wvwpcnlm4qvp167oan7vs` FOREIGN KEY (`policy_savings_id`) REFERENCES `policy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `savings_surrender`
--

LOCK TABLES `savings_surrender` WRITE;
/*!40000 ALTER TABLE `savings_surrender` DISABLE KEYS */;
/*!40000 ALTER TABLE `savings_surrender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriber`
--

DROP TABLE IF EXISTS `subscriber`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriber` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `id_photo` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) NOT NULL,
  `next_of_kin_address` varchar(255) DEFAULT NULL,
  `next_of_kin_cell` varchar(255) DEFAULT NULL,
  `next_of_kin_first_name` varchar(255) DEFAULT NULL,
  `next_of_kin_landline` varchar(255) DEFAULT NULL,
  `next_of_kin_last_name` varchar(255) DEFAULT NULL,
  `next_of_kin_national_id` varchar(255) DEFAULT NULL,
  `next_of_kin_relationship` varchar(255) DEFAULT NULL,
  `registration_date` date NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `subscriber_photo` varchar(255) DEFAULT NULL,
  `suburb` varchar(255) DEFAULT NULL,
  `type_of_id` varchar(255) DEFAULT NULL,
  `agent_id` bigint(20) DEFAULT NULL,
  `subscriber_profile_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK60kgeqy40cp1lkq7mgbfh32sy` (`agent_id`),
  KEY `FKr0gi1k8wc2ap06f485b1cdbud` (`subscriber_profile_id`),
  CONSTRAINT `FK60kgeqy40cp1lkq7mgbfh32sy` FOREIGN KEY (`agent_id`) REFERENCES `agent` (`id`),
  CONSTRAINT `FKr0gi1k8wc2ap06f485b1cdbud` FOREIGN KEY (`subscriber_profile_id`) REFERENCES `subscriber_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriber`
--

LOCK TABLES `subscriber` WRITE;
/*!40000 ALTER TABLE `subscriber` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriber` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriber_account`
--

DROP TABLE IF EXISTS `subscriber_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriber_account` (
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `available_balance` decimal(19,2) DEFAULT NULL,
  `currency` varchar(255) DEFAULT NULL,
  `ledger_balance` decimal(19,2) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL,
  `reserved_balance` decimal(19,2) DEFAULT NULL,
  `subscriber_id` bigint(20) NOT NULL,
  PRIMARY KEY (`subscriber_id`),
  CONSTRAINT `FKnfcy0fj4xnln9cpe6ijr69p3c` FOREIGN KEY (`subscriber_id`) REFERENCES `subscriber` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriber_account`
--

LOCK TABLES `subscriber_account` WRITE;
/*!40000 ALTER TABLE `subscriber_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriber_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriber_bank_account`
--

DROP TABLE IF EXISTS `subscriber_bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriber_bank_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `account_name` varchar(255) DEFAULT NULL,
  `account_number` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `bank_code` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `subscriber_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdkjok1ouyc1u5y2828e27hnsd` (`subscriber_id`),
  CONSTRAINT `FKdkjok1ouyc1u5y2828e27hnsd` FOREIGN KEY (`subscriber_id`) REFERENCES `subscriber` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriber_bank_account`
--

LOCK TABLES `subscriber_bank_account` WRITE;
/*!40000 ALTER TABLE `subscriber_bank_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriber_bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriber_profile`
--

DROP TABLE IF EXISTS `subscriber_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriber_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriber_profile`
--

LOCK TABLES `subscriber_profile` WRITE;
/*!40000 ALTER TABLE `subscriber_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriber_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriber_profile_transaction_type`
--

DROP TABLE IF EXISTS `subscriber_profile_transaction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriber_profile_transaction_type` (
  `subscriber_profile_id` bigint(20) NOT NULL,
  `transaction_type_id` bigint(20) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `max_credit` decimal(19,2) DEFAULT NULL,
  `max_debit` decimal(19,2) DEFAULT NULL,
  `min_credit` decimal(19,2) DEFAULT NULL,
  `min_debit` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`subscriber_profile_id`,`transaction_type_id`),
  KEY `FK7idbok8tjeqg8sk82ykcrb6px` (`transaction_type_id`),
  CONSTRAINT `FK3y0llrrvtltysuhfsqx21yi1o` FOREIGN KEY (`subscriber_profile_id`) REFERENCES `subscriber_profile` (`id`),
  CONSTRAINT `FK7idbok8tjeqg8sk82ykcrb6px` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriber_profile_transaction_type`
--

LOCK TABLES `subscriber_profile_transaction_type` WRITE;
/*!40000 ALTER TABLE `subscriber_profile_transaction_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriber_profile_transaction_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriber_velocity`
--

DROP TABLE IF EXISTS `subscriber_velocity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriber_velocity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `max_daily_credit` decimal(19,2) DEFAULT NULL,
  `max_daily_debit` decimal(19,2) DEFAULT NULL,
  `max_monthly_credit` decimal(19,2) DEFAULT NULL,
  `max_monthly_debit` decimal(19,2) DEFAULT NULL,
  `max_weekly_credit` decimal(19,2) DEFAULT NULL,
  `max_weekly_debit` decimal(19,2) DEFAULT NULL,
  `min_daily_credit` decimal(19,2) DEFAULT NULL,
  `min_daily_debit` decimal(19,2) DEFAULT NULL,
  `min_monthly_credit` decimal(19,2) DEFAULT NULL,
  `min_monthly_debit` decimal(19,2) DEFAULT NULL,
  `min_weekly_credit` decimal(19,2) DEFAULT NULL,
  `min_weekly_debit` decimal(19,2) DEFAULT NULL,
  `subscriber_profile_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9oj5iqi6425vh3p6o6uv0makf` (`subscriber_profile_id`),
  CONSTRAINT `FK9oj5iqi6425vh3p6o6uv0makf` FOREIGN KEY (`subscriber_profile_id`) REFERENCES `subscriber_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriber_velocity`
--

LOCK TABLES `subscriber_velocity` WRITE;
/*!40000 ALTER TABLE `subscriber_velocity` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriber_velocity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summary_evalue`
--

DROP TABLE IF EXISTS `summary_evalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `summary_evalue` (
  `transaction_date` date NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `fees_balance` decimal(19,2) DEFAULT NULL,
  `merchant_balance` decimal(19,2) DEFAULT NULL,
  `subscriber_balance` decimal(19,2) DEFAULT NULL,
  `tax_balance` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`transaction_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summary_evalue`
--

LOCK TABLES `summary_evalue` WRITE;
/*!40000 ALTER TABLE `summary_evalue` DISABLE KEYS */;
/*!40000 ALTER TABLE `summary_evalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `policy_status_id` varchar(255) DEFAULT NULL,
  `agent_id` bigint(20) DEFAULT NULL,
  `agent_name` varchar(255) DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `bank_account` varchar(255) DEFAULT NULL,
  `bank_id` bigint(20) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `biller_id` bigint(20) DEFAULT NULL,
  `biller_name` varchar(255) DEFAULT NULL,
  `channel` varchar(255) DEFAULT NULL,
  `commission` decimal(19,2) DEFAULT NULL,
  `credit_amount` decimal(19,2) DEFAULT NULL,
  `debit_amount` decimal(19,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `fee` decimal(19,2) DEFAULT NULL,
  `imei` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `operator_name` varchar(255) DEFAULT NULL,
  `original_reference` varchar(255) DEFAULT NULL,
  `platform_account_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `subscriber_id` bigint(20) DEFAULT NULL,
  `tax` decimal(19,2) DEFAULT NULL,
  `transaction_amount` decimal(19,2) DEFAULT NULL,
  `transaction_date` datetime(6) DEFAULT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  `transactor_type` varchar(255) DEFAULT NULL,
  `type_of_entry` varchar(255) DEFAULT NULL,
  `wallet_commission` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_commission`
--

DROP TABLE IF EXISTS `transaction_commission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_commission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `fixed` decimal(19,2) DEFAULT NULL,
  `max` decimal(19,2) DEFAULT NULL,
  `min` decimal(19,2) DEFAULT NULL,
  `percentage` decimal(19,2) DEFAULT NULL,
  `agent_profile_id` bigint(20) DEFAULT NULL,
  `transaction_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKob6ieiacecftgyjes77dw348r` (`agent_profile_id`),
  KEY `FK76i189mpvpgv29gtqhc11e5c5` (`transaction_type_id`),
  CONSTRAINT `FK76i189mpvpgv29gtqhc11e5c5` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`id`),
  CONSTRAINT `FKob6ieiacecftgyjes77dw348r` FOREIGN KEY (`agent_profile_id`) REFERENCES `agent_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_commission`
--

LOCK TABLES `transaction_commission` WRITE;
/*!40000 ALTER TABLE `transaction_commission` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_commission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_fee`
--

DROP TABLE IF EXISTS `transaction_fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_fee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `fixed` decimal(19,2) DEFAULT NULL,
  `imt_tax_fixed` decimal(19,2) DEFAULT NULL,
  `imt_tax_percentage` decimal(19,2) DEFAULT NULL,
  `max` decimal(19,2) DEFAULT NULL,
  `min` decimal(19,2) DEFAULT NULL,
  `percentage` decimal(19,2) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `subscriber_profile_id` bigint(20) DEFAULT NULL,
  `transaction_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8t31vh8couy9qpn9jyba89pyd` (`subscriber_profile_id`),
  KEY `FK1dmkhqcdakrsqq7hhlqb0mcw1` (`transaction_type_id`),
  CONSTRAINT `FK1dmkhqcdakrsqq7hhlqb0mcw1` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`id`),
  CONSTRAINT `FK8t31vh8couy9qpn9jyba89pyd` FOREIGN KEY (`subscriber_profile_id`) REFERENCES `subscriber_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_fee`
--

LOCK TABLES `transaction_fee` WRITE;
/*!40000 ALTER TABLE `transaction_fee` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_type`
--

DROP TABLE IF EXISTS `transaction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_type`
--

LOCK TABLES `transaction_type` WRITE;
/*!40000 ALTER TABLE `transaction_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_attempts`
--

DROP TABLE IF EXISTS `user_attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_attempts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `version` bigint(20) NOT NULL,
  `attempts` int(11) NOT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_attempts`
--

LOCK TABLES `user_attempts` WRITE;
/*!40000 ALTER TABLE `user_attempts` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_attempts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-02 20:02:08
