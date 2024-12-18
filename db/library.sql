/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.6.2-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	11.6.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES
(1,44,'F. Scott Fitzgerald'),
(2,46,'George Orwell'),
(3,89,'Harper Lee'),
(4,41,'Jane Austen'),
(5,91,'J.D. Salinger'),
(6,81,'J.R.R. Tolkien'),
(7,91,'Ray Bradbury'),
(8,72,'Herman Melville'),
(9,82,'Leo Tolstoy'),
(10,NULL,'Homer'),
(11,59,'Fyodor Dostoevsky'),
(12,69,'Aldous Huxley'),
(13,38,'Charlotte Brontë'),
(14,30,'Emily Brontë'),
(15,56,'Dante Alighieri'),
(16,58,'Charles Dickens'),
(17,83,'Victor Hugo'),
(18,66,'John Steinbeck'),
(19,76,'Joseph Heller'),
(20,58,'Khaled Hosseini'),
(21,84,'Kurt Vonnegut'),
(22,89,'Cormac McCarthy'),
(23,76,'Paulo Coelho'),
(24,64,'Bram Stoker'),
(25,53,'Mary Shelley'),
(26,46,'Oscar Wilde'),
(27,76,'Stephen King'),
(28,59,'Dan Brown'),
(29,50,'Stieg Larsson'),
(30,52,'Gillian Flynn'),
(31,46,'John Green'),
(32,60,'Yann Martel'),
(33,48,'Markus Zusak'),
(34,67,'Arthur Golden'),
(35,55,'Kathryn Stockett'),
(36,61,'Suzanne Collins'),
(37,35,'Veronica Roth'),
(38,52,'James Dashner'),
(39,87,'Lois Lowry'),
(40,76,'S.E. Hinton'),
(41,70,'Aldous Huxley');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `in_stock` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfjixh2vym2cvfj3ufxj91jem7` (`author_id`),
  CONSTRAINT `FKfjixh2vym2cvfj3ufxj91jem7` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES
(1,'A novel about the American Dream and the roaring twenties.','https://m.media-amazon.com/images/I/81h5-y42iGL._SL1500_.jpg',12,'The Great Gatsby',1),
(2,'A dystopian novel set in a totalitarian society.','https://m.media-amazon.com/images/I/71z8EdFNwxL._SL1360_.jpg',0,'1984',2),
(3,'To Kill a Mockingbird','https://m.media-amazon.com/images/I/81aY1lxk+9L._SL1500_.jpg',13,'To Kill a Mockingbird',3),
(4,'A classic romantic novel about manners and matrimonial machinations.','https://m.media-amazon.com/images/I/31AxQViSTGL._SY445_SX342_.jpg',8,'Pride and Prejudice',4),
(5,'A coming-of-age story about teenage rebellion.','https://m.media-amazon.com/images/I/71nXPGovoTL._SY466_.jpg',0,'The Catcher in the Rye',5),
(6,'A fantasy adventure about a hobbit on a quest.','https://m.media-amazon.com/images/I/91Q00mYqCFL._SX342_.jpg',17,'The Hobbit',6),
(7,'A dystopian novel about a future where books are banned.','https://m.media-amazon.com/images/I/71UCyTAt4hL._AC_UY327_FMwebp_QL65_.jpg',8,'Fahrenheit 451',7),
(8,'A novel about the obsessive quest for a giant whale.','https://m.media-amazon.com/images/I/81XS2mY6qfL._AC_UL480_FMwebp_QL65_.jpg',7,'Moby Dick',8),
(9,'An epic novel set during the Napoleonic Wars.','https://m.media-amazon.com/images/I/71eK3ri8ROL._AC_UL480_FMwebp_QL65_.jpg',4,'War and Peace',9),
(10,'An ancient Greek epic poem about the hero Odysseus.','https://m.media-amazon.com/images/I/61cudQwiS3L._AC_UL480_FMwebp_QL65_.jpg',11,'The Odyssey',10),
(11,'A psychological novel about crime and morality.','https://m.media-amazon.com/images/I/81vTpOYpwOL._AC_UL480_FMwebp_QL65_.jpg',6,'Crime and Punishment',11),
(12,'A philosophical novel about faith, doubt, and free will.','https://m.media-amazon.com/images/I/71ohpQBLDyL._AC_UL480_FMwebp_QL65_.jpg',5,'The Brothers Karamazov',11),
(13,'A dystopian novel about a technologically advanced future.','https://m.media-amazon.com/images/I/71c4HiVmNrL._AC_UY327_FMwebp_QL65_.jpg',12,'Brave New World',12),
(14,'A gothic romance about an orphaned governess.','https://m.media-amazon.com/images/I/81t73Rsp6wL._AC_UY327_FMwebp_QL65_.jpg',7,'Jane Eyre',13),
(15,'A tale of passion and revenge on the Yorkshire moors.','https://m.media-amazon.com/images/I/61CM-bYfT4S._AC_UL480_FMwebp_QL65_.jpg',8,'Wuthering Heights',14),
(16,'An epic poem about the journey through the afterlife.','https://m.media-amazon.com/images/I/817Lm8PHG9L._AC_UY327_FMwebp_QL65_.jpg',3,'The Divine Comedy',15),
(17,'A coming-of-age story about wealth and poverty in Victorian England.','https://m.media-amazon.com/images/I/91m4aHqEL3L._AC_UY327_FMwebp_QL65_.jpg',10,'Great Expectations',16),
(18,'A novel about love and redemption in post-revolutionary France.','https://m.media-amazon.com/images/I/81e1NPAQJmL._AC_UY327_FMwebp_QL65_.jpg',6,'Les Misérables',17),
(19,'A novel about love and betrayal in Russian high society.','https://m.media-amazon.com/images/I/71O3PTUA3vL._AC_UY327_FMwebp_QL65_.jpg',9,'Anna Karenina',9),
(20,'A novel about the struggles of a family during the Great Depression.','https://m.media-amazon.com/images/I/51MNQ-0bL+S._AC_UY327_FMwebp_QL65_.jpg',12,'The Grapes of Wrath',18),
(21,'An epic poem about the Trojan War.','https://m.media-amazon.com/images/I/91Bfhi-K2SL._AC_UL480_FMwebp_QL65_.jpg',10,'The Iliad',10),
(22,'A satirical novel about the absurdities of war.','https://m.media-amazon.com/images/I/71PJ3u6gO2L._AC_UY327_FMwebp_QL65_.jpg',7,'Catch-22',19),
(23,'A story of friendship and redemption set in Afghanistan.','https://m.media-amazon.com/images/I/81LVEH25iJL._AC_UY327_FMwebp_QL65_.jpg',14,'The Kite Runner',20),
(24,'A science fiction-infused anti-war novel.','https://m.media-amazon.com/images/I/81NgzG3kU-L._AC_UY327_FMwebp_QL65_.jpg',8,'Slaughterhouse-Five',21),
(25,'A post-apocalyptic novel about survival and father-son bond.','https://m.media-amazon.com/images/I/711WYzePJeL._AC_UY327_FMwebp_QL65_.jpg',3,'The Road',22),
(26,'A philosophical novel about following one’s dreams.','https://m.media-amazon.com/images/I/71zHDXu1TaL._AC_UY327_FMwebp_QL65_.jpg',11,'The Alchemist',23),
(27,'A gothic novel introducing the iconic vampire Count Dracula.','https://m.media-amazon.com/images/I/61TqVMQ6jKL._AC_UY327_FMwebp_QL65_.jpg',9,'Dracula',24),
(28,'A novel about the consequences of playing God.','https://m.media-amazon.com/images/I/81z7E0uWdtL._AC_UY327_FMwebp_QL65_.jpg',7,'Frankenstein',25),
(29,'A novel about vanity and moral corruption.','https://m.media-amazon.com/images/I/61vSNJFuq5S._AC_UL480_FMwebp_QL65_.jpg',6,'The Picture of Dorian Gray',26),
(30,'A horror novel set in a haunted hotel.','https://m.media-amazon.com/images/I/91G7TjLTU3L._AC_UY327_FMwebp_QL65_.jpg',10,'The Shining',27),
(31,'A post-apocalyptic horror/fantasy novel.','https://m.media-amazon.com/images/I/81n5Qg246YL._AC_UY327_FMwebp_QL65_.jpg',4,'The Stand',27),
(32,'A mystery thriller involving secret societies and religious history.','https://m.media-amazon.com/images/I/71wngh3UDSL._AC_UY327_FMwebp_QL65_.jpg',15,'The Da Vinci Code',28),
(33,'A thriller involving a conspiracy within the Catholic Church.','https://m.media-amazon.com/images/I/91eSpp085nL._AC_UY327_FMwebp_QL65_.jpg',13,'Angels & Demons',28),
(34,'A mystery thriller about a journalist and a hacker.','https://m.media-amazon.com/images/I/71K-euhWB5L._AC_UY327_FMwebp_QL65_.jpg',11,'The Girl with the Dragon Tattoo',29),
(35,'A psychological thriller about a missing woman.','https://m.media-amazon.com/images/I/61XJBJDl6PL._AC_UY327_FMwebp_QL65_.jpg',9,'Gone Girl',30),
(36,'A novel about two teenagers dealing with cancer.','https://m.media-amazon.com/images/I/918av7yayqL._AC_UY327_FMwebp_QL65_.jpg',14,'The Fault in Our Stars',31),
(37,'A philosophical novel about a boy stranded at sea with a tiger.','https://m.media-amazon.com/images/I/81A8M75eOuL._AC_UY327_FMwebp_QL65_.jpg',11,'Life of Pi',32),
(38,'A novel narrated by Death, set in Nazi Germany.','https://m.media-amazon.com/images/I/914cHl9v7oL._AC_UY327_FMwebp_QL65_.jpg',10,'The Book Thief',33),
(39,'A historical novel about the life of a geisha in Japan.','https://m.media-amazon.com/images/I/91Ckqk1TY7L._AC_UY327_FMwebp_QL65_.jpg',8,'Memoirs of a Geisha',34),
(40,'A novel about African American maids in the 1960s South.','https://m.media-amazon.com/images/I/81rWPtpxHbL._AC_UY327_FMwebp_QL65_.jpg',7,'The Help',35),
(41,'A dystopian novel about a televised fight to the death.','https://m.media-amazon.com/images/I/91IMwiZCOHL._AC_UY327_FMwebp_QL65_.jpg',16,'The Hunger Games',36),
(42,'The second book in the Hunger Games series.','https://m.media-amazon.com/images/I/81SKNqIndVL._AC_UY327_FMwebp_QL65_.jpg',14,'Catching Fire',36),
(43,'The final book in the Hunger Games series.','https://m.media-amazon.com/images/I/81sjORNm-fL._AC_UY327_FMwebp_QL65_.jpg',13,'Mockingjay',36),
(44,'A dystopian novel about a society divided into factions.','https://m.media-amazon.com/images/I/91OeMEuXPzL._AC_UY327_FMwebp_QL65_.jpg',12,'Divergent',37),
(45,'The second book in the Divergent series.','https://m.media-amazon.com/images/I/81R3-Ay6o8L._AC_UY327_FMwebp_QL65_.jpg',11,'Insurgent',37),
(46,'The final book in the Divergent series.','https://m.media-amazon.com/images/I/51crKNKl6zL._AC_UY327_FMwebp_QL65_.jpg',10,'Allegiant',37),
(47,'A dystopian novel about a group of teens in a maze.','https://m.media-amazon.com/images/I/91Jra1QAMPL._AC_UY327_FMwebp_QL65_.jpg',9,'The Maze Runner',38),
(48,'The second book in the Maze Runner series.','https://m.media-amazon.com/images/I/812eEL8dzeL._AC_UY327_FMwebp_QL65_.jpg',8,'The Scorch Trials',38),
(49,'The final book in the Maze Runner series.','https://m.media-amazon.com/images/I/91vaqmbaF4L._AC_UY327_FMwebp_QL65_.jpg',7,'The Death Cure',38),
(50,'A dystopian novel about a society without memory.','https://m.media-amazon.com/images/I/81Yq5WKWfSL._AC_UY327_FMwebp_QL65_.jpg',6,'The Giver',39),
(51,'A coming-of-age novel about teenage gangs.','https://m.media-amazon.com/images/I/71Bg39CmhoL._AC_UY327_FMwebp_QL65_.jpg',5,'The Outsiders',40);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher_books`
--

DROP TABLE IF EXISTS `publisher_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publisher_books` (
  `publisher_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  UNIQUE KEY `UKq1kbhl4pm7wivubbhfjke3ya4` (`book_id`),
  KEY `FKf52mb9gil8gyjoho84ofm9yb8` (`publisher_id`),
  CONSTRAINT `FKf52mb9gil8gyjoho84ofm9yb8` FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`id`),
  CONSTRAINT `FKl1bd96d6c73657644ctile7yq` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher_books`
--

LOCK TABLES `publisher_books` WRITE;
/*!40000 ALTER TABLE `publisher_books` DISABLE KEYS */;
INSERT INTO `publisher_books` VALUES
(1,1),
(1,4),
(1,6),
(1,8),
(1,10),
(1,14),
(1,17),
(1,19),
(1,23),
(1,26),
(1,29),
(1,32),
(1,35),
(1,38),
(1,41),
(1,44),
(1,47),
(1,50),
(2,2),
(2,3),
(2,7),
(2,9),
(2,11),
(2,13),
(2,15),
(2,18),
(2,21),
(2,24),
(2,27),
(2,30),
(2,33),
(2,36),
(2,39),
(2,42),
(2,45),
(2,48),
(2,51),
(3,5),
(3,12),
(3,16),
(3,20),
(3,22),
(3,25),
(3,28),
(3,31),
(3,34),
(3,37),
(3,40),
(3,43),
(3,46),
(3,49);
/*!40000 ALTER TABLE `publisher_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishers`
--

DROP TABLE IF EXISTS `publishers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publishers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6hfeubb8cqc6wuj84uu5k3u4v` (`address`),
  UNIQUE KEY `UKan1ucpx8sw2qm194mlok8e5us` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishers`
--

LOCK TABLES `publishers` WRITE;
/*!40000 ALTER TABLE `publishers` DISABLE KEYS */;
INSERT INTO `publishers` VALUES
(1,'375 Hudson St, New York, NY','Penguin Books'),
(2,'195 Broadway, New York, NY','HarperCollins'),
(3,'1230 Avenue of the Americas, New York, NY','Simon & Schuster');
/*!40000 ALTER TABLE `publishers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `records`
--

DROP TABLE IF EXISTS `records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exceed_date` datetime(6) NOT NULL,
  `rent_date` datetime(6) NOT NULL,
  `status` varchar(255) NOT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `subscription_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnke55n81hhwtj7p25hswydt52` (`book_id`),
  KEY `FKi8l5qr4lyjrbumllf1177xe45` (`subscription_id`),
  CONSTRAINT `FKi8l5qr4lyjrbumllf1177xe45` FOREIGN KEY (`subscription_id`) REFERENCES `subscriptions` (`id`),
  CONSTRAINT `FKnke55n81hhwtj7p25hswydt52` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `records`
--

LOCK TABLES `records` WRITE;
/*!40000 ALTER TABLE `records` DISABLE KEYS */;
INSERT INTO `records` VALUES
(1,'2025-03-13 13:07:14.666000','2024-12-13 13:07:14.666000','renting',2,1),
(2,'2025-03-13 13:07:37.847000','2024-12-13 13:07:37.847000','renting',7,1),
(3,'2025-03-13 13:09:57.824000','2024-12-13 13:09:57.824000','renting',25,1),
(4,'2025-03-13 13:13:47.808000','2024-12-13 13:13:47.808000','renting',3,1),
(5,'2025-03-13 13:15:25.561000','2024-12-13 13:15:25.561000','renting',2,1),
(6,'2025-03-13 13:16:15.490000','2024-12-13 13:16:15.490000','renting',2,1),
(7,'2025-03-13 13:16:32.155000','2024-12-13 13:16:32.155000','renting',2,1),
(8,'2025-03-13 13:16:34.840000','2024-12-13 13:16:34.840000','renting',2,1),
(9,'2025-03-13 13:17:01.968000','2024-12-13 13:17:01.968000','renting',2,1),
(10,'2025-03-13 13:17:30.528000','2024-12-13 13:17:30.528000','renting',2,1);
/*!40000 ALTER TABLE `records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriptions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_date` datetime(6) NOT NULL,
  `period` int(11) NOT NULL,
  `start_date` datetime(6) NOT NULL,
  `status` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `rent_limit` int(11) NOT NULL,
  `late_fee` float NOT NULL,
  `late_fee_percent` float NOT NULL,
  `remaining_fee` float NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKl3ommhd1n0tu0k2va0cbp87qe` (`user_id`),
  CONSTRAINT `FKhro52ohfqfbay9774bev0qinr` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptions`
--

LOCK TABLES `subscriptions` WRITE;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
INSERT INTO `subscriptions` VALUES
(1,'2025-03-01 12:21:05.155000',3,'2024-12-01 12:21:05.155000','active',1,500,15000,0.25,0);
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `subscription_id` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UKns8vi4ouq0uoo25pse5gos0bn` (`subscription_id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKdu5v5sr43g5bfnji4vb8hg5s3` (`phone`),
  CONSTRAINT `FKfwx079xww5uyfbpi9u8gwam34` FOREIGN KEY (`subscription_id`) REFERENCES `subscriptions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'$2a$10$LDVQIxRV4wSVfMQKnUKFZeLSZz4tMEkMkM45MD9nVKL0R7gdkt2b2','ADMIN','admin',1,'admin@gmail.com','0123456789');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2024-12-18 11:59:28
