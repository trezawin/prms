CREATE TABLE `program-slot` (
  `id` int(11) not null auto_increment,
  `duration` time NOT NULL,
  `dateOfProgram` datetime NOT NULL,
  `program-name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dateOfProgram_UNIQUE` (`dateOfProgram`),
  KEY `name_program_slot` (`program-name`),
  CONSTRAINT `name` FOREIGN KEY (`program-name`) REFERENCES `radio-program` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;