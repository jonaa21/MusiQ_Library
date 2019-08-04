-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
    'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema musiq_library
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `musiq_library`;

-- -----------------------------------------------------
-- Schema musiq_library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `musiq_library` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `musiq_library`;

-- -----------------------------------------------------
-- Table `musiq_library`.`artist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `musiq_library`.`artist`;

CREATE TABLE IF NOT EXISTS `musiq_library`.`artist`
(
  `id`          BIGINT(4)   NOT NULL AUTO_INCREMENT,
  `artist_name` VARCHAR(45) NOT NULL UNIQUE,
  `artist_type` VARCHAR(45) NOT NULL,
  `is_followed` INT(1)      NULL DEFAULT 0,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `musiq_library`.`album`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `musiq_library`.`album`;

CREATE TABLE IF NOT EXISTS `musiq_library`.`album`
(
  `id`           BIGINT(4)   NOT NULL AUTO_INCREMENT,
  `album_title`  VARCHAR(45) NOT NULL,
  `artist_id`    BIGINT(4)   NOT NULL,
  `release_year` INT(4)      NOT NULL DEFAULT 1980,
  PRIMARY KEY (`id`),
  INDEX `artist_id_idx` (`artist_id` ASC) VISIBLE,
  CONSTRAINT `artist_id`
    FOREIGN KEY (`artist_id`)
      REFERENCES `musiq_library`.`artist` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `musiq_library`.`song`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `musiq_library`.`song`;

CREATE TABLE IF NOT EXISTS `musiq_library`.`song`
(
  `id`           BIGINT(4)   NOT NULL AUTO_INCREMENT,
  `title`        VARCHAR(45) NOT NULL,
  `release_year` INT(4)      NULL,
  `album_id`     BIGINT(4)   NULL,
  `is_favorite`  INT(1)      NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `album_id_idx` (`album_id` ASC) VISIBLE,
  CONSTRAINT `album_id`
    FOREIGN KEY (`album_id`)
      REFERENCES `musiq_library`.`album` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `musiq_library`.`artist_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `musiq_library`.artist_info;
CREATE TABLE IF NOT EXISTS `musiq_library`.`artist_info`
(
  `id`        BIGINT(4)     NOT NULL AUTO_INCREMENT,
  `artist_id` BIGINT(4)     NOT NULL,
  `info`      VARCHAR(1500) NULL,
  PRIMARY KEY (`id`),
  INDEX `artist_id_idx` (`artist_id` ASC) VISIBLE,
  CONSTRAINT `artist_id_fk`
    FOREIGN KEY (`artist_id`)
      REFERENCES `musiq_library`.`artist` (`id`)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `musiq_library`.`artist_type_code`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `musiq_library`.`artist_type_code`;

CREATE TABLE IF NOT EXISTS `musiq_library`.`artist_type_code`
(
  `id`          BIGINT(4)   NOT NULL AUTO_INCREMENT,
  `artist_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- Data for table `musiq_library`.`artist_type_code`
-- -----------------------------------------------------
START TRANSACTION;
USE `musiq_library`;
INSERT INTO `musiq_library`.`artist_type_code` (`id`, `artist_type`)
VALUES ('1', 'Solo');
INSERT INTO `musiq_library`.`artist_type_code` (`id`, `artist_type`)
VALUES ('2', 'Duo');
INSERT INTO `musiq_library`.`artist_type_code` (`id`, `artist_type`)
VALUES ('3', 'Group');
INSERT INTO `musiq_library`.`artist_type_code` (`id`, `artist_type`)
VALUES ('4', 'Band');

COMMIT;


-- -----------------------------------------------------
-- Data for table `musiq_library`.`artist`
-- -----------------------------------------------------
START TRANSACTION;
USE `musiq_library`;
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Queen', 'BAND', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Bon Jovi', 'BAND', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Eminem', 'SOLO', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Michael Jackson', 'SOLO', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('\'N Sync', 'BAND', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('NWA', 'GROUP', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Linkin Park', 'BAND', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Marroon 5', 'BAND', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Bruno Mars', 'SOLO', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Daft Punk', 'DUO', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Alicia Keys', 'SOLO', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Jay-Z', 'SOLO', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Beyonce', 'SOLO', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Destiny\'s Child', 'GROUP', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('Boyz II Men', 'GROUP', '0');
INSERT INTO `musiq_library`.`artist` (`artist_name`, `artist_type`, `is_followed`)
VALUES ('The Temptations', 'GROUP', '0');


COMMIT;


-- -----------------------------------------------------
-- Data for table `musiq_library`.`album`
-- -----------------------------------------------------
START TRANSACTION;
USE `musiq_library`;
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('Bohemian Rhapsody', '1', '1975');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('Slippery When Wet', '2', '1986');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('The Marshall Mathers LP', '3', '2000');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('Thriller', '4', '1982');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('No Strings Attached', '5', '2000');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('Straight Outta Compton', '6', '1988');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('Meteora', '7', '2003');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('Songs About Jane', '8', '2003');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('24K Magic', '9', '2016');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('Random Access Memories', '10', '2010');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('Songs in a Minor', '11', '2001');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('The Blueprint 3', '12', '2009');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('I Am... Sasha Fierce', '13', '2008');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('The Writing\'s on the Wall', '14', '1999');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('Boomerang Soundtrack', '15', '1992');
INSERT INTO `musiq_library`.`album` (`album_title`, `artist_id`, `release_year`)
VALUES ('The Temptations Sing Smokey', '16', '1965');


COMMIT;


-- -----------------------------------------------------
-- Data for table `musiq_library`.`song`
-- -----------------------------------------------------
START TRANSACTION;
USE `musiq_library`;
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('Living on a Prayer', '1986', '2', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('Bohemian Rhapsody', '1975', '1', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('The Real Slim Shady', '2000', '3', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('Thriller', '1982', '4', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('Bye Bye Bye', '2000', '5', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('F*ck the Police', '1988', '6', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('Numb', '2003', '7', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('This Love', '2003', '8', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('Finesse', '2016', '9', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('Get Lucky', '2010', '10', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('A Woman\'s Worth', '2001', '11', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('Empire State of Mind', '2009', '12', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('Single Ladies', '2008', '13', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('Say My Name', '1999', '14', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('End of the Road', '1992', '15', '0');
INSERT INTO `musiq_library`.`song` (`title`, `release_year`, `album_id`, `is_favorite`)
VALUES ('My Girl', '1964', '16', '0');


COMMIT;


-- -----------------------------------------------------
-- Data for table `musiq_library`.`info`
-- -----------------------------------------------------
START TRANSACTION;
USE `musiq_library`;
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('1',
        'Queen are a British rock band formed in London in 1970. Their classic line-up was Freddie Mercury (lead vocals and piano), Brian May (lead guitar and vocals), Roger Taylor (drums and vocals), and John Deacon (bass guitar). Their earliest works were influenced by progressive rock, hard rock and heavy metal, but the band gradually ventured into more conventional and radio-friendly works by incorporating further styles, such as arena rock and pop rock.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('2',
        'Bon Jovi is an American rock band formed in 1983 in Sayreville, New Jersey. It consists of singer Jon Bon Jovi, keyboardist David Bryan, drummer Tico Torres, guitarist Phil X, and bassist Hugh McDonald. Previous bassist Alec John Such was dismissed in 1994, and longtime guitarist and co-songwriter Richie Sambora left in 2013.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('3',
        'Marshall Bruce Mathers III (born October 17, 1972), known professionally as Eminem, is an American rapper, songwriter, record producer, record executive, film producer, and actor. He is consistently cited as one of the greatest and most influential artists of all time in hip hop, with Rolling Stone placing him in its list of the 100 Greatest Artists of All Time and labeling him the \"King of Hip Hop\".');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('4',
        'Michael Joseph Jackson (August 29, 1958 – June 25, 2009) was an American singer, songwriter, and dancer. Dubbed the \"King of Pop\", he is widely regarded as one of the most significant cultural figures of the 20th century and one of the greatest entertainers of all time. He was also known for his philanthropy, charitable fundraising, and lifestyle, residing in a private amusement park he called Neverland Ranch and often becoming the focus of tabloid scrutiny. Jackson\'s contributions to music, dance, and fashion, along with his publicized personal life, made him a global figure in popular culture for over four decades.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('5',
        'NSYNC was an American boy band formed in Orlando, Florida, in 1995 and launched in Germany by BMG Ariola Munich. NSYNC consisted of Justin Timberlake, JC Chasez, Chris Kirkpatrick, Joey Fatone, and Lance Bass. After heavily publicized legal battles with their former manager Lou Pearlman and former record label Bertelsmann Music Group, the group\'s second album, No Strings Attached (2000), sold over one million copies in one day and 2.42 million copies in one week, which was a record for over fifteen years.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('6',
        'N.W.A (an abbreviation for Niggaz Wit Attitudes) was an American hip hop group from Los Angeles, California. They were among the earliest and most significant popularizes and controversial figures of the gangsta rap subgenre, and are widely considered one of the greatest and most influential groups in the history of hip hop music.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('7',
        'Linkin Park is an American rock band from Agoura Hills, California. The band\'s current lineup comprises vocalist/rhythm guitarist Mike Shinoda, lead guitarist Brad Delson, bassist Dave Farrell, turntablist/keyboardist Joe Hahn and drummer Rob Bourdon, all of whom are founding members. Vocalists Mark Wakefield and Chester Bennington and bassist Kyle Christner are former members of the band.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('8',
        'Maroon 5 is an American pop rock band from Los Angeles, California. It currently consists of lead vocalist Adam Levine, keyboardist and rhythm guitarist Jesse Carmichael, bassist Mickey Madden, lead guitarist James Valentine, drummer Matt Flynn, keyboardist PJ Morton, and multi-instrumentalist Sam Farrar. Original members Levine, Carmichael, Madden, and drummer Ryan Dusick first came together as Kara\'s Flowers in 1994, while they were still in high school.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('9',
        'Peter Gene Hernandez (born October 8, 1985), known professionally as Bruno Mars, is an American singer, songwriter, multi-instrumentalist, record producer, and dancer. He is known for his stage performances, retro showmanship and for performing in a wide range of musical styles including R&B, funk, pop, soul, reggae, hip hop, and rock. Mars is accompanied by his band, The Hooligans, who play a variety of instruments such as electric guitar, bass, piano, keyboards, drums and horns, and also serve as backup singers and dancers.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('10',
        'Daft Punk is a French electronic music duo formed in Paris in 1993 by Guy-Manuel de Homem-Christo and Thomas Bangalter.[5][6][7][8] They achieved popularity in the late 1990s as part of the French house movement, and had success in the years following, combining elements of house music with funk, techno, disco, rock, and synthpop.[2][6][7][9] They have worn ornate helmets and gloves to assume robot personas in most public appearances since 1999[10] and rarely grant interviews or appear on television. The duo were managed from 1996 to 2008 by Pedro Winter (also known as Busy P), the head of Ed Banger Records.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('11',
        'Alicia Augello Cook (born January 25, 1981), known professionally as Alicia Keys, is an American singer-songwriter, musician, record producer, actress and philanthropist. A classically-trained pianist, Keys was composing songs by age 12 and was signed at 15 years old by Columbia Records.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('12',
        'Shawn Corey Carter (born December 4, 1969), known professionally as Jay-Z (stylized as JAY-Z), is an American rapper, songwriter, record producer, entrepreneur, and record executive. Considered one of the best rappers of all time, he is regarded as one of the world\'s most significant cultural icons and has been a global figure in popular culture for over two decades.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('13',
        'Beyoncé Giselle Knowles-Carter born September 4, 1981) is an American singer, songwriter, actress, record producer and dancer. Born and raised in Houston, Texas, Beyoncé performed in various singing and dancing competitions as a child. She rose to fame in the late 1990s as lead singer of the R&B girl-group Destiny\'s Child. Managed by her father, Mathew Knowles, the group became one of the best-selling girl groups in history.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('14',
        'Destiny\'s Child was an American girl group whose final and best-known line-up comprised Beyoncé Knowles, Kelly Rowland, and Michelle Williams. Formed in 1997 in Houston, Texas, Destiny\'s Child members began their musical career as Girl\'s Tyme, formed in 1990, comprising Knowles, Rowland, LaTavia Roberson, and LeToya Luckett among others.');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('15',
        'Boyz II Men is an American R&B vocal group from Philadelphia, Pennsylvania, best known for emotional ballads and a cappella harmonies. They are currently a trio composed of baritone Nathan Morris alongside tenors Wanya Morris and Shawn Stockman. During the 1990s, Boyz II Men found fame on Motown Records as a quartet including bass Michael McCary, who left the group in 2003 due to health issues');
INSERT INTO `musiq_library`.`artist_info` (`artist_id`, info)
VALUES ('16',
        'The Temptations is an American vocal group who released a series of successful singles and album with Motown Records during the 1960s and 1970s. The group’s work with producer Norman Whitfield, beginning with the Top 10 hit single "Cloud Nine" in October 1968, pioneered psychedelic soul, and was significant in the evolution of R&B and soul music.[1] The band members are known for their choreography, distinct harmonies, and dress style. Having sold tens of millions of album, the Temptations is among the most successful groups in popular music.');


COMMIT;
