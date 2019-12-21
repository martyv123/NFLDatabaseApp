CREATE DATABASE  IF NOT EXISTS `nflprostats` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `nflprostats`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: nflprostats
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `head_coach`
--

DROP TABLE IF EXISTS `head_coach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `head_coach` (
  `coach_id` int(11) NOT NULL AUTO_INCREMENT,
  `coach_name` varchar(50) NOT NULL,
  `years_experience` int(11) NOT NULL,
  `current_team` varchar(50) NOT NULL,
  `win_loss_ratio` double DEFAULT NULL,
  `championships_won` int(11) DEFAULT NULL,
  `superbowls_won` int(11) DEFAULT NULL,
  PRIMARY KEY (`coach_id`),
  KEY `coach_fk_team` (`current_team`),
  CONSTRAINT `coach_fk_team` FOREIGN KEY (`current_team`) REFERENCES `team` (`team_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `head_coach`
--

LOCK TABLES `head_coach` WRITE;
/*!40000 ALTER TABLE `head_coach` DISABLE KEYS */;
INSERT INTO `head_coach` VALUES (1,'Bill Belichick',20,'Patriots',0.684,9,6),(2,'Vic Fangio',1,'Broncos',0.333,0,0),(3,'Kyle Shanahan',3,'49ers',0.455,0,0),(4,'Sean Payton',14,'Saints',0.596,1,1),(5,'Dan Quinn',5,'Falcons',0.513,1,0),(6,'Matt Lafleur',1,'Packers',0.8,0,0);
/*!40000 ALTER TABLE `head_coach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_player_stats`
--

DROP TABLE IF EXISTS `match_player_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `match_player_stats` (
  `player_id` int(11) NOT NULL,
  `team` varchar(50) NOT NULL,
  `match_number` int(11) NOT NULL,
  `passing_yards` int(11) NOT NULL,
  `rushing_yards` int(11) NOT NULL,
  `receiving_yards` int(11) NOT NULL,
  `tackles` int(11) NOT NULL,
  `interceptions` int(11) NOT NULL,
  `sacks` double NOT NULL,
  PRIMARY KEY (`player_id`,`match_number`),
  KEY `match_number` (`match_number`),
  CONSTRAINT `match_player_stats_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `match_player_stats_ibfk_2` FOREIGN KEY (`match_number`) REFERENCES `nfl_match` (`match_number`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_player_stats`
--

LOCK TABLES `match_player_stats` WRITE;
/*!40000 ALTER TABLE `match_player_stats` DISABLE KEYS */;
INSERT INTO `match_player_stats` VALUES (1,'Patriots',2,0,0,83,0,0,0),(1,'Patriots',11,0,0,51,0,0,0),(1,'Patriots',18,0,0,62,0,0,0),(2,'Saints',3,0,0,123,0,0,0),(2,'Saints',12,0,0,89,0,0,0),(2,'Saints',13,0,0,54,0,0,0),(3,'Broncos',1,0,0,120,0,0,0),(3,'Broncos',10,0,0,40,0,0,0),(3,'Broncos',15,0,0,87,0,0,0),(4,'Falcons',4,0,0,31,0,0,0),(4,'Falcons',9,0,0,106,0,0,0),(4,'Falcons',17,0,0,128,0,0,0),(5,'Packers',6,0,0,36,0,0,0),(5,'Packers',7,0,0,106,0,0,0),(5,'Packers',15,0,0,56,0,0,0),(6,'49ers',5,0,0,7,0,0,0),(6,'49ers',8,0,0,77,0,0,0),(6,'49ers',14,0,0,41,0,0,0),(7,'Packers',6,203,0,0,0,0,0),(7,'Packers',7,209,0,0,0,0,0),(7,'Packers',15,235,0,0,0,0,0),(8,'Patriots',2,341,0,0,0,0,0),(8,'Patriots',11,264,0,0,0,0,0),(8,'Patriots',18,306,0,0,0,0,0),(9,'Saints',3,370,0,0,0,0,0),(9,'Saints',12,38,0,0,0,0,0),(9,'Saints',13,0,0,0,0,0,0),(10,'49ers',5,166,0,0,0,0,0),(10,'49ers',8,296,0,0,0,0,0),(10,'49ers',14,277,0,0,0,0,0),(11,'Broncos',1,268,0,0,0,0,0),(11,'Broncos',10,292,0,0,0,0,0),(11,'Broncos',15,213,0,0,0,0,0),(12,'Falcons',4,304,0,0,0,0,0),(12,'Falcons',9,320,0,0,0,0,0),(12,'Falcons',17,304,0,0,0,0,0),(13,'Saints',3,0,97,72,0,0,0),(13,'Saints',12,0,45,15,0,0,0),(13,'Saints',13,0,69,92,0,0,0),(14,'Broncos',1,0,43,23,0,0,0),(14,'Broncos',10,0,36,30,0,0,0),(14,'Broncos',15,0,81,49,0,0,0),(15,'Patriots',2,0,26,56,0,0,0),(15,'Patriots',11,0,10,19,0,0,0),(15,'Patriots',18,0,1,57,0,0,0),(16,'Falcons',4,0,19,12,0,0,0),(16,'Falcons',9,0,22,42,0,0,0),(16,'Falcons',17,0,88,7,0,0,0),(17,'Packers',6,0,36,0,0,0,0),(17,'Packers',7,0,116,34,0,0,0),(17,'Packers',15,0,19,4,0,0,0),(18,'49ers',5,0,23,33,0,0,0),(18,'49ers',8,0,97,0,0,0,0),(18,'49ers',14,0,45,16,0,0,0),(23,'Broncos',1,0,0,0,5,0,0),(23,'Broncos',10,0,0,0,2,0,0),(23,'Broncos',15,0,0,0,3,0,0),(24,'Packers',6,0,0,0,7,0,1),(24,'Packers',7,0,0,0,13,0,0),(24,'Packers',15,0,0,0,12,0,0),(25,'Falcons',4,0,0,0,3,0,0),(25,'Falcons',9,0,0,0,1,0,1),(25,'Falcons',17,0,0,0,3,0,0.5),(26,'Saints',3,0,0,0,4,0,0),(26,'Saints',12,0,0,0,5,0,0),(26,'Saints',13,0,0,0,12,1,0),(27,'Patriots',2,0,0,0,13,0,0),(27,'Patriots',11,0,0,0,14,1,0),(27,'Patriots',18,0,0,0,12,0,0),(28,'49ers',5,0,0,0,5,1,0),(28,'49ers',8,0,0,0,4,0,0),(28,'49ers',14,0,0,2,0,0,0);
/*!40000 ALTER TABLE `match_player_stats` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_player_stats` AFTER INSERT ON `match_player_stats` FOR EACH ROW BEGIN   
        DECLARE pl_id INT;
        DECLARE match_num INT;
        DECLARE pass_yds INT;
        DECLARE run_yds INT;
        DECLARE rec_yds INT;
        DECLARE tacks INT;
        DECLARE ints INT;
        DECLARE scks DOUBLE;
        
        SET pl_id = NEW.player_id;
        SET match_num = NEW.match_number;
        SET pass_yds = NEW.passing_yards;
        SET run_yds = NEW.rushing_yards;
        SET rec_yds = NEW.receiving_yards;
        SET tacks = NEW.tackles;
        SET ints = NEW.interceptions;
        SET scks = NEW.sacks;
        
        -- update games_so_far field
        UPDATE player
        SET games_so_far = games_so_far + 1
        WHERE pl_id = id;
        
        -- update offensive stats
        UPDATE player_stats_off
        SET 
			passing_yards = passing_yards + pass_yds,
            rushing_yards = rushing_yards + run_yds,
            receiving_yards = receiving_yards + rec_yds
        WHERE pl_id = player_id;
        
        -- update defensive stats
        UPDATE player_stats_def
        SET 
			tackles = tackles + tacks,
            interceptions = interceptions + ints,
            sacks = sacks + scks
        WHERE pl_id = player_id;

	END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `nfl_match`
--

DROP TABLE IF EXISTS `nfl_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nfl_match` (
  `match_number` int(11) NOT NULL,
  `week` int(11) NOT NULL,
  `home_team` varchar(50) NOT NULL,
  `away_team` varchar(50) NOT NULL,
  `home_points` int(11) NOT NULL,
  `away_points` int(11) NOT NULL,
  `home_pass_yards` int(11) NOT NULL,
  `away_pass_yards` int(11) NOT NULL,
  `home_run_yards` int(11) NOT NULL,
  `away_run_yards` int(11) NOT NULL,
  PRIMARY KEY (`match_number`),
  KEY `match_fk_home` (`home_team`),
  KEY `match_fk_away` (`away_team`),
  CONSTRAINT `match_fk_away` FOREIGN KEY (`away_team`) REFERENCES `team` (`team_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `match_fk_home` FOREIGN KEY (`home_team`) REFERENCES `team` (`team_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nfl_match`
--

LOCK TABLES `nfl_match` WRITE;
/*!40000 ALTER TABLE `nfl_match` DISABLE KEYS */;
INSERT INTO `nfl_match` VALUES (1,1,'Raiders','Broncos',24,16,259,268,98,95),(2,1,'Patriots','Steelers',33,3,373,276,99,32),(3,1,'Saints','Texans',30,28,370,268,148,180),(4,1,'Vikings','Falcons',28,12,98,304,172,73),(5,1,'Buccaneers','49ers',17,31,194,166,121,98),(6,1,'Bears','Packers',3,10,228,203,46,47),(7,2,'Packers','Vikings',21,16,209,230,144,198),(8,2,'Bengals','49ers',17,41,311,314,25,259),(9,2,'Falcons','Eagles',24,20,320,255,57,39),(10,2,'Broncos','Bears',14,16,292,120,90,153),(11,2,'Dolphins','Patriots',0,43,196,264,42,124),(12,2,'Rams','Saints',27,9,283,203,117,57),(13,3,'Seahawks','Saints',27,33,406,177,108,86),(14,3,'49ers','Steelers',24,20,277,174,168,79),(15,3,'Packers','Broncos',27,16,235,213,77,149),(17,3,'Colts','Falcons',27,24,310,304,76,93),(18,3,'Patriots','Jets',30,14,320,98,68,36);
/*!40000 ALTER TABLE `nfl_match` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `update_team_stats` AFTER INSERT ON `nfl_match` FOR EACH ROW BEGIN
		DECLARE hm_wins INT;
        DECLARE hm_losses INT;
        DECLARE aw_wins INT;
        DECLARE aw_losses INT;
        DECLARE hm_team VARCHAR(50);
        DECLARE aw_team VARCHAR(50);
        DECLARE hm_points INT;
        DECLARE aw_points INT;
        DECLARE hm_pass INT;
        DECLARE hm_run INT;
        DECLARE aw_pass INT;
        DECLARE aw_run INT;
        
        SET hm_team = NEW.home_team;
        SET aw_team = NEW.away_team;
		SET hm_points = NEW.home_points;
        SET aw_points = NEW.away_points;
        SET hm_pass = NEW.home_pass_yards;
        SET hm_run = NEW.home_run_yards;
        SET aw_pass = NEW.away_pass_yards;
        SET aw_run = NEW.away_run_yards;
        
		SELECT COUNT(*) INTO hm_losses FROM nfl_match as ma WHERE ma.home_team = hm_team AND ma.home_points < ma.away_points;
        SELECT COUNT(*) INTO hm_wins FROM nfl_match as ma WHERE ma.home_team = hm_team AND ma.home_points > ma.away_points;
        SELECT COUNT(*) INTO aw_losses FROM nfl_match as ma WHERE ma.away_team = aw_team AND ma.home_points > ma.away_points;
        SELECT COUNT(*) INTO aw_wins FROM nfl_match as ma WHERE ma.away_team = aw_team AND ma.home_points < ma.away_points;
		
        -- updates wins and losses
        UPDATE team
		SET wins = hm_wins, losses = hm_losses
		WHERE hm_team = team_name;
		UPDATE team
 		SET wins = aw_wins, losses = aw_losses
 		WHERE aw_team = team_name;
        
        -- updates points scored
        UPDATE team 
        SET tot_points_scored = tot_points_scored + hm_points
        WHERE hm_team = team_name;
        UPDATE team 
        SET tot_points_scored = tot_points_scored + aw_points
        WHERE aw_team = team_name;
        
        -- updates points allowed
        UPDATE team 
        SET tot_points_allowed = tot_points_allowed + aw_points
        WHERE hm_team = team_name;
        UPDATE team 
        SET tot_points_allowed = tot_points_allowed + hm_points
        WHERE aw_team = team_name;
        
        -- updates pass yards (both including earned and conceded)
        UPDATE team 
        SET tot_pass_yards = tot_pass_yards + hm_pass, tot_pass_yards_conceded = tot_pass_yards_conceded + aw_pass
        WHERE hm_team = team_name;
        UPDATE team 
        SET tot_pass_yards = tot_pass_yards + aw_pass, tot_pass_yards_conceded = tot_pass_yards_conceded + hm_pass
        WHERE aw_team = team_name;
        
        -- updates run yards (both including earned and conceded)
        UPDATE team 
        SET tot_run_yards = tot_run_yards + hm_run, tot_run_yards_conceded = tot_run_yards_conceded + aw_run
        WHERE hm_team = team_name;
        UPDATE team 
        SET tot_run_yards = tot_run_yards + aw_run, tot_run_yards_conceded = tot_run_yards_conceded + hm_run
        WHERE aw_team = team_name;
	END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player` (
  `id` int(11) NOT NULL,
  `player_number` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `position` varchar(50) NOT NULL,
  `current_team` varchar(50) DEFAULT NULL,
  `games_so_far` int(11) DEFAULT '0',
  `years_experience` int(11) NOT NULL,
  `rating` double NOT NULL,
  `championships_won` int(11) DEFAULT NULL,
  `superbowls_won` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `player_fk_team` (`current_team`),
  CONSTRAINT `player_fk_team` FOREIGN KEY (`current_team`) REFERENCES `team` (`team_name`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,11,'Julian','Edelman','WR','Patriots',3,10,92,4,3),(2,13,'Michael','Thomas','WR','Saints',3,3,98,0,0),(3,14,'Courtland','Sutton','WR','Broncos',3,2,90,0,0),(4,11,'Julio','Jones','WR','Falcons',3,8,95,1,0),(5,17,'Davante','Adams','WR','Packers',3,5,93,0,0),(6,11,'Marquise','Goodwin','WR','49ers',3,7,82,0,0),(7,12,'Aaron','Rodgers','QB','Packers',3,7,97,1,1),(8,12,'Tom','Brady','QB','Patriots',3,7,94,9,6),(9,9,'Drew','Brees','QB','Saints',3,7,95,1,1),(10,10,'Jimmy','Garoppolo ','QB','49ers',3,5,80,2,2),(11,5,'Joe','Flacco','QB','Broncos',3,1,84,1,1),(12,2,'Matt','Ryan','QB','Falcons',3,12,90,1,0),(13,41,'Alvin','Kamara','RB','Saints',3,3,96,0,0),(14,30,'Phillip','Lindsay','RB','Broncos',3,2,93,0,0),(15,28,'James','White','RB','Patriots',3,6,85,4,2),(16,24,'Devonta','Freeman','RB','Falcons',3,6,88,1,0),(17,33,'Aaron','Jones','RB','Packers',3,3,96,0,0),(18,26,'Tevin','Coleman','RB','49ers',3,5,85,1,0),(23,58,'Von','Miller','ILB','Broncos',3,8,99,2,1),(24,50,'Blake','Martinez','ILB','Packers',3,6,97,0,0),(25,44,'Vic','Beasley','DE','Falcons',3,8,94,1,0),(26,23,'Marshon','Lattimore','CB','Saints',3,3,94,0,0),(27,24,'Stephon','Gilmore','CB','Patriots',3,8,97,2,1),(28,25,'Richard','Sherman','CB','49ers',3,9,91,0,0);
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `create_player_stats` AFTER INSERT ON `player` FOR EACH ROW BEGIN  

		INSERT INTO player_stats_def(player_id)
        VALUES(NEW.id);
        INSERT INTO player_stats_off(player_id)
        VALUES(NEW.id);
        
	END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `player_stats_def`
--

DROP TABLE IF EXISTS `player_stats_def`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_stats_def` (
  `player_id` int(11) NOT NULL,
  `tackles` int(11) DEFAULT '0',
  `interceptions` int(11) DEFAULT '0',
  `sacks` double DEFAULT '0',
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_stats_def`
--

LOCK TABLES `player_stats_def` WRITE;
/*!40000 ALTER TABLE `player_stats_def` DISABLE KEYS */;
INSERT INTO `player_stats_def` VALUES (1,0,0,0),(2,0,0,0),(3,0,0,0),(4,0,0,0),(5,0,0,0),(6,0,0,0),(7,0,0,0),(8,0,0,0),(9,0,0,0),(10,0,0,0),(11,0,0,0),(12,0,0,0),(13,0,0,0),(14,0,0,0),(15,0,0,0),(16,0,0,0),(17,0,0,0),(18,0,0,0),(23,10,0,0),(24,32,0,1),(25,7,0,1.5),(26,21,1,0),(27,39,1,0),(28,9,1,0);
/*!40000 ALTER TABLE `player_stats_def` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_stats_off`
--

DROP TABLE IF EXISTS `player_stats_off`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_stats_off` (
  `player_id` int(11) NOT NULL,
  `passing_yards` int(11) DEFAULT '0',
  `rushing_yards` int(11) DEFAULT '0',
  `receiving_yards` int(11) DEFAULT '0',
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_stats_off`
--

LOCK TABLES `player_stats_off` WRITE;
/*!40000 ALTER TABLE `player_stats_off` DISABLE KEYS */;
INSERT INTO `player_stats_off` VALUES (1,0,0,196),(2,0,0,266),(3,0,0,247),(4,0,0,265),(5,0,0,198),(6,0,0,125),(7,647,0,0),(8,911,0,0),(9,408,0,0),(10,739,0,0),(11,773,0,0),(12,928,0,0),(13,0,211,179),(14,0,160,102),(15,0,37,132),(16,0,129,61),(17,0,171,38),(18,0,165,49),(23,0,0,0),(24,0,0,0),(25,0,0,0),(26,0,0,0),(27,0,0,0),(28,0,0,2);
/*!40000 ALTER TABLE `player_stats_off` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `team_name` varchar(50) NOT NULL,
  `stadium` varchar(50) NOT NULL,
  `wins` int(11) DEFAULT '0',
  `losses` int(11) DEFAULT '0',
  `tot_points_scored` double DEFAULT '0',
  `tot_points_allowed` double DEFAULT '0',
  `tot_pass_yards` double DEFAULT '0',
  `tot_run_yards` double DEFAULT '0',
  `tot_pass_yards_conceded` double DEFAULT '0',
  `tot_run_yards_conceded` double DEFAULT '0',
  `championships_won` int(11) DEFAULT NULL,
  `superbowls_won` int(11) DEFAULT NULL,
  PRIMARY KEY (`team_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES ('49ers','Levi\'s Stadium',1,0,96,54,757,525,679,225,NULL,NULL),('Bears','Soldier Field',1,0,19,24,348,199,495,137,NULL,NULL),('Bengals','Paul Brown Stadium',0,1,17,41,311,25,314,259,NULL,NULL),('Bills','New era',0,0,0,0,0,0,0,0,NULL,NULL),('Broncos','Milehigh',0,2,46,67,773,334,614,328,NULL,NULL),('Browns','FirstEnergy Stadium',0,0,0,0,0,0,0,0,NULL,NULL),('Buccaneers','Raymond James Stadium',0,1,17,31,194,121,166,98,NULL,NULL),('Cardinals','Lincoln Financial',0,0,0,0,0,0,0,0,NULL,NULL),('Chargers','Stub Hub',0,0,0,0,0,0,0,0,NULL,NULL),('Chiefs','Arrowhead',0,0,0,0,0,0,0,0,NULL,NULL),('Colts','Lucas Oil Stadium',1,0,27,24,310,76,304,93,NULL,NULL),('Cowboys','AT&T Stadium',0,0,0,0,0,0,0,0,NULL,NULL),('Dolphins','Hard Rock',0,1,0,43,196,42,264,124,NULL,NULL),('Eagles','Lincoln Financial Field',0,1,20,24,255,39,320,57,NULL,NULL),('Falcons','Mercedes-Benz Stadium',0,2,60,75,928,223,663,287,NULL,NULL),('Giants','MetLife Stadium',0,0,0,0,0,0,0,0,NULL,NULL),('Jaguars','TIAA Bank Stadium',0,0,0,0,0,0,0,0,NULL,NULL),('Jets','MetLife',0,1,14,30,98,36,320,68,NULL,NULL),('Lions','Ford Field',0,0,0,0,0,0,0,0,NULL,NULL),('Packers','Lambeau Field',2,0,58,35,647,268,671,393,NULL,NULL),('Panthers','Bank of America Stadium',0,0,0,0,0,0,0,0,NULL,NULL),('Patriots','Gillette',2,0,106,17,957,291,570,110,NULL,NULL),('Raiders','Colliseum',1,0,24,16,259,98,268,95,NULL,NULL),('Rams','LA Coliseum',1,0,27,9,283,117,203,57,NULL,NULL),('Ravens','M&T Bank Stadium',0,0,0,0,0,0,0,0,NULL,NULL),('Redskins','FedExField',0,0,0,0,0,0,0,0,NULL,NULL),('Saints','Mercedes-Benz Superdome',1,1,72,82,750,291,957,405,NULL,NULL),('Seahawks','CenturyLink Field',0,1,27,33,406,108,177,86,NULL,NULL),('Steelers','Heinz Field',0,2,23,57,450,111,650,267,NULL,NULL),('Texans','NRG Stadium',0,1,28,30,268,180,370,148,NULL,NULL),('Titans','Nissan Stadium',0,0,0,0,0,0,0,0,NULL,NULL),('Vikings','U.S. Bank Stadium',0,1,44,33,328,370,513,217,NULL,NULL);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'nflprostats'
--

--
-- Dumping routines for database 'nflprostats'
--
/*!50003 DROP PROCEDURE IF EXISTS `avg_interceptions_player` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_interceptions_player`(fname VARCHAR(50), lname VARCHAR(50), num INT, team VARCHAR(50))
BEGIN
   SELECT first_name, last_name, interceptions / games_so_far as IntsPerGame 
   FROM player as p JOIN player_stats_def as psd ON p.id = psd.player_id 
   WHERE p.first_name = fname AND p.last_name = lname AND p.player_number = num AND p.current_team = team;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `avg_pass_conceded_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_pass_conceded_team`(team_name VARCHAR(50))
BEGIN
	SELECT t.team_name, tot_pass_yards_conceded / (wins + losses) as AvgPassYardsConcededPerGame FROM team as t WHERE team_name = t.team_name;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `avg_pass_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_pass_team`(team_name VARCHAR(50))
BEGIN
	SELECT t.team_name, tot_pass_yards / (wins + losses) as AvgPassYardsPerGame FROM team as t WHERE team_name = t.team_name;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `avg_pass_yards_player` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_pass_yards_player`(fname VARCHAR(50), lname VARCHAR(50), num INT, team VARCHAR(50))
BEGIN
   SELECT first_name, last_name, passing_yards / games_so_far as YardsPerGame 
   FROM player as p JOIN player_stats_off as pso ON p.id = pso.player_id 
   WHERE p.first_name = fname AND p.last_name = lname AND p.player_number = num AND p.current_team = team;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `avg_points_conceded_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_points_conceded_team`(team_name VARCHAR(50))
BEGIN
	SELECT t.team_name, tot_points_allowed / (wins + losses) as AvgPointsConcededPerGame FROM team as t WHERE team_name = t.team_name;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `avg_points_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_points_team`(team_name VARCHAR(50))
BEGIN
	SELECT t.team_name, tot_points_scored / (wins + losses) as AvgPointsPerGame FROM team as t WHERE team_name = t.team_name;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `avg_rec_yards_player` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_rec_yards_player`(fname VARCHAR(50), lname VARCHAR(50), num INT, team VARCHAR(50))
BEGIN
   SELECT first_name, last_name, receiving_yards / games_so_far as YardsPerGame 
   FROM player as p JOIN player_stats_off as pso ON p.id = pso.player_id 
   WHERE p.first_name = fname AND p.last_name = lname AND p.player_number = num AND p.current_team = team;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `avg_run_conceded_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_run_conceded_team`(team_name VARCHAR(50))
BEGIN
	SELECT t.team_name, tot_run_yards_conceded / (wins + losses) as AvgRunYardsConcededPerGame FROM team as t WHERE team_name = t.team_name;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `avg_run_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_run_team`(team_name VARCHAR(50))
BEGIN
	SELECT t.team_name, tot_run_yards / (wins + losses) as AvgRunYardsPerGame FROM team as t WHERE team_name = t.team_name;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `avg_run_yards_player` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_run_yards_player`(fname VARCHAR(50), lname VARCHAR(50), num INT, team VARCHAR(50))
BEGIN
   SELECT first_name, last_name, rushing_yards / games_so_far as YardsPerGame 
   FROM player as p JOIN player_stats_off as pso ON p.id = pso.player_id 
   WHERE p.first_name = fname AND p.last_name = lname AND p.player_number = num AND p.current_team = team;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `avg_sacks_player` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_sacks_player`(fname VARCHAR(50), lname VARCHAR(50), num INT, team VARCHAR(50))
BEGIN
   SELECT first_name, last_name, sacks / games_so_far as SacksPerGame 
   FROM player as p JOIN player_stats_def as psd ON p.id = psd.player_id 
   WHERE p.first_name = fname AND p.last_name = lname AND p.player_number = num AND p.current_team = team;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `avg_tackles_player` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_tackles_player`(fname VARCHAR(50), lname VARCHAR(50), num INT, team VARCHAR(50))
BEGIN
   SELECT first_name, last_name, tackles / games_so_far as TacklesPerGame 
   FROM player as p JOIN player_stats_def as psd ON p.id = psd.player_id 
   WHERE p.first_name = fname AND p.last_name = lname AND p.player_number = num AND p.current_team = team;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_losses_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_losses_team`()
BEGIN
	SELECT team_name, losses as Losses FROM team 
    WHERE wins + losses > 0
    ORDER BY losses DESC LIMIT 5;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_pass_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_pass_team`()
BEGIN
	SELECT team_name, tot_pass_yards as TotalPassScored FROM team 
    WHERE wins + losses > 0
    ORDER BY tot_pass_yards DESC LIMIT 5;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_pass_yards_conceded_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_pass_yards_conceded_team`()
BEGIN
	SELECT team_name, tot_pass_yards_conceded as TotalPassAllowed FROM team 
    WHERE wins + losses > 0
    ORDER BY tot_pass_yards_conceded ASC LIMIT 5;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_points_allowed_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_points_allowed_team`()
BEGIN
	SELECT team_name, tot_points_allowed as TotalPointsAllowed FROM team 
    WHERE wins + losses > 0
    ORDER BY tot_points_allowed ASC LIMIT 5;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_points_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_points_team`()
BEGIN
	SELECT team_name, tot_points_scored as TotalPointsScored FROM team 
    WHERE wins + losses > 0
    ORDER BY tot_points_scored DESC LIMIT 5;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_quarterbacks` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_quarterbacks`()
BEGIN
   SELECT first_name, last_name, passing_yards / games_so_far as PassYardsPerGame 
   FROM player as p JOIN player_stats_off as pso ON p.id = pso.player_id 
   WHERE pso.passing_yards > 0
   ORDER BY pso.passing_yards DESC LIMIT 5;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_receivers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_receivers`()
BEGIN
   SELECT first_name, last_name, receiving_yards / games_so_far as RecYardsPerGame 
   FROM player as p JOIN player_stats_off as pso ON p.id = pso.player_id 
   WHERE pso.receiving_yards > 0
   ORDER BY pso.receiving_yards DESC LIMIT 5;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_runningbacks` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_runningbacks`()
BEGIN
   SELECT first_name, last_name, rushing_yards / games_so_far as RunYardsPerGame 
   FROM player as p JOIN player_stats_off as pso ON p.id = pso.player_id 
   WHERE pso.rushing_yards > 0
   ORDER BY pso.rushing_yards DESC LIMIT 5;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_run_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_run_team`()
BEGIN
	SELECT team_name, tot_run_yards as TotalRunScored FROM team 
    WHERE wins + losses > 0
    ORDER BY tot_run_yards DESC LIMIT 5;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_run_yards_conceded_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_run_yards_conceded_team`()
BEGIN
	SELECT team_name, tot_run_yards_conceded as TotalRunAllowed FROM team 
    WHERE wins + losses > 0
    ORDER BY tot_run_yards_conceded ASC LIMIT 5;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_winlosspct_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_winlosspct_team`()
BEGIN
	SELECT team_name, wins / (wins + losses) as WinPercentage FROM team 
    WHERE wins / (wins + losses) > 0
    ORDER BY wins DESC LIMIT 5;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `top_5_wins_team` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `top_5_wins_team`()
BEGIN
	SELECT team_name, wins as Wins FROM team 
    WHERE wins + losses > 0
    ORDER BY wins DESC LIMIT 5;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-04 21:17:22
