drop table `weekly-schedule`

CREATE TABLE `weekly-schedule` (
  `id` bigint(11) NOT NULL,
  `startDate` datetime NOT NULL,
  `assignedBy` varchar(45) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `startDate_UNIQUE` (`startDate`),
  KEY `id_assigned_by` (`assignedBy`),
  CONSTRAINT `id_ws` FOREIGN KEY (`assignedBy`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;