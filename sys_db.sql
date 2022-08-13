/*
SQLyog Community v12.4.0 (64 bit)
MySQL - 5.7.19 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `test`;

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`name`,`type`,`value`) values 
('user','icon','el-icon-user'),
('custom','icon','el-icon-s-custom'),
('menu','icon','el-icon-menu'),
('file','icon','el-icon-document'),
('home','icon','el-icon-house'),
('manage','icon','el-icon-s-grid');

/*Table structure for table `sys_file` */

DROP TABLE IF EXISTS `sys_file`;

CREATE TABLE `sys_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `size` bigint(20) DEFAULT NULL COMMENT '大小',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '链接',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `enable` tinyint(1) DEFAULT '1' COMMENT '是否禁用',
  `md5` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'md5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=910032910 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sys_file` */

insert  into `sys_file`(`id`,`name`,`type`,`size`,`url`,`is_delete`,`enable`,`md5`) values 
(1,'火山.png','png',6,'http://localhost:9090/file/b3cb7ac21fef4880acd9a8b1c81720ae.png',1,1,'94c372ce3d1be2f0b8f2b50ebe9a03c0'),
(2,'火山.png','png',6,'http://localhost:9090/file/b3cb7ac21fef4880acd9a8b1c81720ae.png',1,1,'94c372ce3d1be2f0b8f2b50ebe9a03c0'),
(3,'3.1我的 (1).png','png',7,'http://localhost:9090/file/3eb48e08203d4119b9d64201fc901c94.png',0,1,'beaedc46f6fa48beb65f29619162498c'),
(4,'_文化艺术业.png','png',10,'http://localhost:9090/file/fb51f8e7582c405397489655e4b46256.png',0,1,'79acb288cb32f58a4c47a8c8a069548c'),
(5,'3.1我的 (1).png','png',7,'http://localhost:9090/file/3eb48e08203d4119b9d64201fc901c94.png',0,1,'beaedc46f6fa48beb65f29619162498c'),
(6,'FAmn83VUcAIs8P4.jpg','jpg',56,'http://localhost:9090/file/7582b0e024fe4e7f895b880e5dffdbc0.jpg',0,1,'89495db6c18745eafa32badc45bda320'),
(7,'FAmn83RVUAYULHc.jpg','jpg',44,'http://localhost:9090/file/342f2759a3bf45d09e63006a3a41c1aa.jpg',0,1,'9b4f0078c3805a6580ec55aa724b7806'),
(8,'FAmn83VUcAIs8P4.jpg','jpg',56,'http://localhost:9090/file/7582b0e024fe4e7f895b880e5dffdbc0.jpg',0,1,'89495db6c18745eafa32badc45bda320'),
(9,'FAmn83RUUAIiH0T.jpg','jpg',73,'http://localhost:9090/file/db644f6f52144437b22f53689e37ce8f.jpg',0,1,'e30d7ac7faca71fa6fda21b15a1c74a4'),
(10,'dog.jpeg','jpeg',53,'http://localhost:9090/file/42762f4e75e346828b82b5bbbfeaf01d.jpeg',0,1,'78c94f73c0c40783fb34feaa1858f7dc'),
(910032908,'FAmn83VUcAIs8P4.jpg','jpg',56,'http://localhost:9090/file/7582b0e024fe4e7f895b880e5dffdbc0.jpg',0,1,'89495db6c18745eafa32badc45bda320'),
(910032909,'运动.png','png',9,'http://localhost:9090/file/4e878965b25c44cd8936d0f549522e0b.png',0,1,'0e05f0ec8b0f5a38e63e8f740a84cf65');

/*Table structure for table `sys_goods` */

DROP TABLE IF EXISTS `sys_goods`;

CREATE TABLE `sys_goods` (
  `lost_id` int(11) NOT NULL AUTO_INCREMENT,
  `lost_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lost_position` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lost_description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `claim_position` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '领取位置',
  `lost_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_returned` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`lost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sys_goods` */

insert  into `sys_goods`(`lost_id`,`lost_name`,`lost_position`,`lost_description`,`claim_position`,`lost_date`,`is_returned`) values 
(3,'钥匙','太极操场','22','二教失物招领处','2022-08-09 13:05:55',NULL),
(4,'一卡通','二教','待补充','二教','2022-08-10 12:40:11',NULL);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pid` int(11) DEFAULT NULL COMMENT '父级id',
  `page_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`path`,`icon`,`description`,`pid`,`page_path`) values 
(0,'Home','/home','el-icon-house','首页',NULL,'Home'),
(1,'Menu','/menu','el-icon-menu',NULL,2,'Menu'),
(2,'Manage','','el-icon-s-grid','局部视图',NULL,NULL),
(3,'Developer','/user','el-icon-user','',2,'Developer'),
(5,'Role','/role','el-icon-s-custom',NULL,2,'Role'),
(6,'Files','/file','el-icon-document',NULL,2,'Files'),
(8,'Lost & Found',NULL,'el-icon-box','失物招领',NULL,NULL),
(10,'Student','/student','el-icon-user-solid','失主',8,'Student'),
(11,'Goods','/goods','el-icon-takeaway-box',NULL,8,'Goods'),
(12,'Me','/me','el-icon-coffee-cup','Welcome to sponsor me ?',NULL,'Me');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `flag` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`description`,`flag`) values 
(1,'administrator','管理员','ROLE_ADMIN'),
(2,'user','用户','ROLE_USER');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values 
(1,0),
(1,1),
(1,2),
(1,3),
(1,5),
(1,6),
(1,8),
(1,10),
(1,11),
(1,12),
(2,0),
(2,2),
(2,6),
(2,8),
(2,10),
(2,11);

/*Table structure for table `sys_student` */

DROP TABLE IF EXISTS `sys_student`;

CREATE TABLE `sys_student` (
  `student_no` int(11) NOT NULL AUTO_INCREMENT,
  `institute` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `grade` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `class` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`student_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sys_student` */

insert  into `sys_student`(`student_no`,`institute`,`grade`,`class`,`name`) values 
(1,'计算机','2021','04122113','oliver'),
(2,'马克思主义','2021','04022113','张三');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `avatar_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `role` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`nickname`,`email`,`phone`,`address`,`create_time`,`avatar_url`,`role`) values 
(1,'admin','admin','管理员','admin@qq.com','1234567','里斯本','2022-01-25 08:47:16','http://localhost:9090/file/7582b0e024fe4e7f895b880e5dffdbc0.jpg','ROLE_ADMIN'),
(2,'oliver','oliver','CEO','123@qq.com','123456','冰岛','2022-02-25 09:32:39','http://avatars.githubusercontent.com/u/92531864?v=4','ROLE_USER'),
(3,'naya','naya','CTO ','naya@qq.com','1415926535','挪威','2022-04-25 09:46:05','https://tse3-mm.cn.bing.net/th/id/OIP-C.5jZiyu1J4Hfjej8rxWqrkwAAAA?w=175&h=180&c=7&r=0&o=5&dpr=1.25&pid=1.7','ROLE_USER'),
(4,'olivia','olivia','secretary','olivia@qq.com','1234567','南京','2022-05-28 12:04:23','https://tse3-mm.cn.bing.net/th/id/OIP-C.W4nhGNE8nEhvh2sRR9Po8AAAAA?w=192&h=192&c=7&r=0&o=5&dpr=1.25&pid=1.7','ROLE_USER'),
(5,'monica','monica','mon','mon@google.com','12345678','iceland','2022-06-28 12:28:08','https://ts1.cn.mm.bing.net/th?id=OIP-C.zuSiNGBrUKRyk1FAyNSvqQAAAA&w=100&h=106&c=8&rs=1&qlt=90&o=6&dpr=1.25&pid=3.1&rm=2','ROLE_USER'),
(14,'berlin','berlin','lin','berlin@qq.com','123456789','德国','2023-08-16 00:00:00','https://tse2-mm.cn.bing.net/th/id/OIP-C.HfLKGWOavYM5heVEy_HebAHaEo?w=264&h=180&c=7&r=0&o=5&dpr=1.25&pid=1.7','ROLE_USER'),
(15,'naruto','naruto','na','naruto@google.com','123221','tokyo','2022-07-30 22:06:56','https://tse4-mm.cn.bing.net/th/id/OIP-C.7bT8oGHcc6D2M5LESPUS1AHaFo?w=221&h=180&c=7&r=0&o=5&dpr=1.25&pid=1.7','ROLE_USER'),
(16,'sakura','sakura','ra','sakura@google.com','1231','tokyo','2022-11-30 22:07:24','https://tse1-mm.cn.bing.net/th/id/OIP-C.oy3L8he5zXNUI91ClnuDQAHaKg?w=128&h=181&c=7&r=0&o=5&dpr=1.25&pid=1.7','ROLE_USER'),
(17,'sasuke','sasuke','sa','sasuke@google.com','12312','tokyo','2022-12-30 22:29:45','https://tse2-mm.cn.bing.net/th/id/OIP-C.T934A1ItR6Cx2qgUJp1_rAHaEo?w=270&h=180&c=7&r=0&o=5&dpr=1.25&pid=1.7','ROLE_USER');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
