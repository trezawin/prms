CREATE TABLE `program-slot` (
  `duration` time NOT NULL,
  `dateOfProgram` datetime NOT NULL,
  `program-name` varchar(45) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`duration`,`dateOfProgram`,`id`),
  UNIQUE KEY `dateOfProgram_UNIQUE` (`dateOfProgram`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `name_program_slot` (`program-name`),
  CONSTRAINT `name` FOREIGN KEY (`program-name`) REFERENCES `radio-program` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
