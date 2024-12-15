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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
(40,76,'S.E. Hinton');
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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES
(1,'A novel about the American Dream and the roaring twenties.','https://upload.wikimedia.org/wikipedia/en/f/f7/TheGreatGatsby_1925jacket.gif',12,'The Great Gatsby',1),
(2,'A dystopian novel set in a totalitarian society.','https://upload.wikimedia.org/wikipedia/en/c/c3/1984first.jpg',0,'1984',2),
(3,'A story of racial injustice in the Deep South.','https://upload.wikimedia.org/wikipedia/en/7/79/To_Kill_a_Mockingbird.JPG',13,'To Kill a Mockingbird',3),
(4,'A classic romantic novel about manners and matrimonial machinations.','https://upload.wikimedia.org/wikipedia/en/0/0b/PrideAndPrejudiceTitlePage.jpg',8,'Pride and Prejudice',4),
(5,'A coming-of-age story about teenage rebellion.','https://upload.wikimedia.org/wikipedia/en/3/32/Rye_catcher.jpg',0,'The Catcher in the Rye',5),
(6,'A fantasy adventure about a hobbit on a quest.','https://upload.wikimedia.org/wikipedia/en/4/4a/TheHobbit_FirstEdition.jpg',17,'The Hobbit',6),
(7,'A dystopian novel about a future where books are banned.','https://upload.wikimedia.org/wikipedia/en/d/dc/Fahrenheit_451_1st_ed_cover.jpg',8,'Fahrenheit 451',7),
(8,'A novel about the obsessive quest for a giant whale.','https://upload.wikimedia.org/wikipedia/commons/4/41/Moby-Dick_FE_title_page.jpg',7,'Moby Dick',8),
(9,'An epic novel set during the Napoleonic Wars.','https://upload.wikimedia.org/wikipedia/commons/a/af/War-and-peace-book-cover.jpg',4,'War and Peace',9),
(10,'An ancient Greek epic poem about the hero Odysseus.','https://upload.wikimedia.org/wikipedia/commons/9/9c/Odyssey-circe.jpg',11,'The Odyssey',10),
(11,'A psychological novel about crime and morality.','https://upload.wikimedia.org/wikipedia/en/8/86/Crimeandpunishmentcover.png',6,'Crime and Punishment',11),
(12,'A philosophical novel about faith, doubt, and free will.','https://upload.wikimedia.org/wikipedia/en/8/8d/TheBrothersKaramazov.jpg',5,'The Brothers Karamazov',11),
(13,'A dystopian novel about a technologically advanced future.','https://upload.wikimedia.org/wikipedia/en/6/62/BraveNewWorld_FirstEdition.jpg',12,'Brave New World',12),
(14,'A gothic romance about an orphaned governess.','https://upload.wikimedia.org/wikipedia/en/9/9f/Jane_Eyre_title_page.jpg',7,'Jane Eyre',13),
(15,'A tale of passion and revenge on the Yorkshire moors.','https://upload.wikimedia.org/wikipedia/en/1/11/Wuthering_Heights.jpg',8,'Wuthering Heights',14),
(16,'An epic poem about the journey through the afterlife.','https://upload.wikimedia.org/wikipedia/commons/5/55/Divine_Comedy%2C_Cary%27s_translation%2C_The_Vision_of_Hell%2C_title_page.jpg',3,'The Divine Comedy',15),
(17,'A coming-of-age story about wealth and poverty in Victorian England.','https://upload.wikimedia.org/wikipedia/en/3/32/GreatExpectations_FC.jpg',10,'Great Expectations',16),
(18,'A novel about love and redemption in post-revolutionary France.','https://upload.wikimedia.org/wikipedia/en/6/6e/Les_Miserables_1862_-_Title_page.jpg',6,'Les Misérables',17),
(19,'A novel about love and betrayal in Russian high society.','https://upload.wikimedia.org/wikipedia/en/7/7e/AnnaKareninaTitlePage.jpg',9,'Anna Karenina',9),
(20,'A novel about the struggles of a family during the Great Depression.','https://upload.wikimedia.org/wikipedia/en/3/3d/TheGrapesOfWrath.jpg',12,'The Grapes of Wrath',18),
(21,'An epic poem about the Trojan War.','https://upload.wikimedia.org/wikipedia/commons/3/3b/The_Iliad_of_Homer_-_Buckley_-_title_page.jpg',10,'The Iliad',10),
(22,'A satirical novel about the absurdities of war.','https://upload.wikimedia.org/wikipedia/en/6/67/Catch22.jpg',7,'Catch-22',19),
(23,'A story of friendship and redemption set in Afghanistan.','https://upload.wikimedia.org/wikipedia/en/6/6a/The_Kite_Runner.jpg',14,'The Kite Runner',20),
(24,'A science fiction-infused anti-war novel.','https://upload.wikimedia.org/wikipedia/en/e/e6/Slaughterhouse-Five_1st_ed_cover.jpg',8,'Slaughterhouse-Five',21),
(25,'A post-apocalyptic novel about survival and father-son bond.','https://upload.wikimedia.org/wikipedia/en/6/65/The-Road.jpg',3,'The Road',22),
(26,'A philosophical novel about following one’s dreams.','https://upload.wikimedia.org/wikipedia/en/c/c4/TheAlchemist.jpg',11,'The Alchemist',23),
(27,'A gothic novel introducing the iconic vampire Count Dracula.','https://upload.wikimedia.org/wikipedia/en/e/ea/Dracula1st.jpeg',9,'Dracula',24),
(28,'A novel about the consequences of playing God.','https://upload.wikimedia.org/wikipedia/en/9/9b/Frankenstein_1818_edition_title_page.jpg',7,'Frankenstein',25),
(29,'A novel about vanity and moral corruption.','https://upload.wikimedia.org/wikipedia/en/8/86/Thepictureofdoriangray.jpg',6,'The Picture of Dorian Gray',26),
(30,'A horror novel set in a haunted hotel.','https://upload.wikimedia.org/wikipedia/en/4/4c/Shiningnovel.jpg',10,'The Shining',27),
(31,'A post-apocalyptic horror/fantasy novel.','https://upload.wikimedia.org/wikipedia/en/5/5b/The_Stand_cover.jpg',4,'The Stand',27),
(32,'A mystery thriller involving secret societies and religious history.','https://upload.wikimedia.org/wikipedia/en/6/6b/DaVinciCode.jpg',15,'The Da Vinci Code',28),
(33,'A thriller involving a conspiracy within the Catholic Church.','https://upload.wikimedia.org/wikipedia/en/8/8f/AngelsAndDemons.jpg',13,'Angels & Demons',28),
(34,'A mystery thriller about a journalist and a hacker.','https://upload.wikimedia.org/wikipedia/en/5/52/The_Girl_with_the_Dragon_Tattoo.jpg',11,'The Girl with the Dragon Tattoo',29),
(35,'A psychological thriller about a missing woman.','https://upload.wikimedia.org/wikipedia/en/0/05/Gone_Girl_%28Flynn_novel%29.jpg',9,'Gone Girl',30),
(36,'A novel about two teenagers dealing with cancer.','https://upload.wikimedia.org/wikipedia/en/4/44/The_Fault_in_Our_Stars.jpg',14,'The Fault in Our Stars',31),
(37,'A philosophical novel about a boy stranded at sea with a tiger.','https://upload.wikimedia.org/wikipedia/en/1/1b/Life_of_Pi_cover.png',11,'Life of Pi',32),
(38,'A novel narrated by Death, set in Nazi Germany.','https://upload.wikimedia.org/wikipedia/en/8/8e/The_Book_Thief_by_Markus_Zusak_book_cover.jpg',10,'The Book Thief',33),
(39,'A historical novel about the life of a geisha in Japan.','https://upload.wikimedia.org/wikipedia/en/0/0f/MemoirsOfAGeisha.jpg',8,'Memoirs of a Geisha',34),
(40,'A novel about African American maids in the 1960s South.','https://upload.wikimedia.org/wikipedia/en/8/8f/Thehelpbookcover.jpg',7,'The Help',35),
(41,'A dystopian novel about a televised fight to the death.','https://upload.wikimedia.org/wikipedia/en/d/dc/The_Hunger_Games.jpg',16,'The Hunger Games',36),
(42,'The second book in the Hunger Games series.','https://upload.wikimedia.org/wikipedia/en/4/42/Catching_Fire.jpg',14,'Catching Fire',36),
(43,'The final book in the Hunger Games series.','https://upload.wikimedia.org/wikipedia/en/6/60/Mockingjay.JPG',13,'Mockingjay',36),
(44,'A dystopian novel about a society divided into factions.','https://upload.wikimedia.org/wikipedia/en/d/d4/Divergent.jpg',12,'Divergent',37),
(45,'The second book in the Divergent series.','https://upload.wikimedia.org/wikipedia/en/0/08/Insurgent_cover.jpg',11,'Insurgent',37),
(46,'The final book in the Divergent series.','https://upload.wikimedia.org/wikipedia/en/1/16/Allegiant.jpg',10,'Allegiant',37),
(47,'A dystopian novel about a group of teens in a maze.','https://upload.wikimedia.org/wikipedia/en/d/db/The_Maze_Runner_cover.png',9,'The Maze Runner',38),
(48,'The second book in the Maze Runner series.','https://upload.wikimedia.org/wikipedia/en/9/92/The_Scorch_Trials_cover.jpg',8,'The Scorch Trials',38),
(49,'The final book in the Maze Runner series.','https://upload.wikimedia.org/wikipedia/en/d/d5/The_Death_Cure.jpg',7,'The Death Cure',38),
(50,'A dystopian novel about a society without memory.','https://upload.wikimedia.org/wikipedia/en/7/7b/The_Giver_first_edition_1993.jpg',6,'The Giver',39),
(51,'A coming-of-age novel about teenage gangs.','https://upload.wikimedia.org/wikipedia/en/9/9e/TheOutsiders.jpg',5,'The Outsiders',40);
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
(10,'2025-03-13 13:17:30.528000','2024-12-13 13:17:30.528000','renting',2,1),
(11,'2025-03-13 13:17:45.439000','2024-12-13 13:17:45.439000','renting',2,1),
(12,'2025-03-13 13:17:54.062000','2024-12-13 13:17:54.062000','renting',3,1),
(13,'2025-03-13 13:21:08.146000','2024-12-13 13:21:08.146000','renting',25,1),
(14,'2025-03-13 13:21:20.998000','2024-12-13 13:21:20.998000','renting',13,1),
(15,'2025-03-15 13:08:31.589000','2024-12-15 13:08:31.589000','renting',6,1),
(16,'2025-03-15 13:08:54.906000','2024-12-15 13:08:54.906000','renting',4,1),
(17,'2025-03-15 13:09:08.673000','2024-12-15 13:09:08.673000','renting',4,1),
(18,'2025-03-15 13:09:32.687000','2024-12-15 13:09:32.687000','renting',34,1),
(19,'2025-03-15 13:11:32.626000','2024-12-15 13:11:32.626000','renting',6,1),
(20,'2025-03-15 13:11:36.496000','2024-12-15 13:11:36.496000','renting',6,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
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
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suppliers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES
(1,'123-456-7890'),
(2,'987-654-3210');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers_books`
--

DROP TABLE IF EXISTS `suppliers_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suppliers_books` (
  `supplier_id` bigint(20) NOT NULL,
  `book_id` bigint(20) NOT NULL,
  KEY `FK7j8kbfytrvtjw7kb7syl8huv4` (`book_id`),
  KEY `FKowr7f71w9glkavpkoy6i5hgct` (`supplier_id`),
  CONSTRAINT `FK7j8kbfytrvtjw7kb7syl8huv4` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `FKowr7f71w9glkavpkoy6i5hgct` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers_books`
--

LOCK TABLES `suppliers_books` WRITE;
/*!40000 ALTER TABLE `suppliers_books` DISABLE KEYS */;
INSERT INTO `suppliers_books` VALUES
(1,1),
(2,1),
(1,2),
(2,2),
(2,1),
(2,2),
(2,1),
(1,2),
(1,1),
(2,2);
/*!40000 ALTER TABLE `suppliers_books` ENABLE KEYS */;
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UKns8vi4ouq0uoo25pse5gos0bn` (`subscription_id`),
  CONSTRAINT `FKfwx079xww5uyfbpi9u8gwam34` FOREIGN KEY (`subscription_id`) REFERENCES `subscriptions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'$2a$10$LDVQIxRV4wSVfMQKnUKFZeLSZz4tMEkMkM45MD9nVKL0R7gdkt2b2','ADMIN','admin',1);
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

-- Dump completed on 2024-12-15 17:38:31
