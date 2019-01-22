CREATE DATABASE `inventory_managment`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `price` float NOT NULL,
  `add_date` varchar(20) NOT NULL,
  `image` longblob NOT NULL
);


