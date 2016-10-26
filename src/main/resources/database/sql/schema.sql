DROP TABLE IF EXISTS `dealer`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `dealer_product`;
DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `dealer` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `line` varchar(255) DEFAULT NULL,
  `post_code` varchar(255) DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL,
  `associate` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`email`,`name`,`phone`)
);


CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`type`,`name`)
);


CREATE TABLE `dealer_product` (
  `dealer_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`dealer_id`,`product_id`),
  FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  FOREIGN KEY (`dealer_id`) REFERENCES `dealer` (`id`)
);

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
);
