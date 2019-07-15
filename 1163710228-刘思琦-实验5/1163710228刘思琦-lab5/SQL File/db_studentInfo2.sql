CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_studentInfo2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_studentInfo2`;

/*Table structure for table `t_faculty` */

DROP TABLE IF EXISTS `t_faculty`;

CREATE TABLE `t_faculty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `facultyName` varchar(20) NOT NULL,
  `facultyDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_faculty` */

insert  into `t_faculty`(`id`,`facultyName`,`facultyDesc`) values (1,'计算机科学与技术学院','暂无');
insert  into `t_faculty`(`id`,`facultyName`,`facultyDesc`) values (2,'航天学院','暂无');
insert  into `t_faculty`(`id`,`facultyName`,`facultyDesc`) values (3,'电信学院','暂无');
insert  into `t_faculty`(`id`,`facultyName`,`facultyDesc`) values (4,'人文学院','暂无');
insert  into `t_faculty`(`id`,`facultyName`,`facultyDesc`) values (5,'材料学院','暂无');


/*Table structure for table `t_grade` */

DROP TABLE IF EXISTS `t_grade`;

CREATE TABLE `t_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gradeName` varchar(20) NOT NULL,
  `gradeDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_grade` */

insert  into `t_grade`(`id`,`gradeName`,`gradeDesc`) values (1,'软件一班','暂无');
insert  into `t_grade`(`id`,`gradeName`,`gradeDesc`) values (2,'软件二班','暂无');
insert  into `t_grade`(`id`,`gradeName`,`gradeDesc`) values (3,'航天一班','暂无');
insert  into `t_grade`(`id`,`gradeName`,`gradeDesc`) values (4,'电信一班','暂无');
insert  into `t_grade`(`id`,`gradeName`,`gradeDesc`) values (5,'材料一班','暂无');

/*Table structure for table `t_dormitory` */

DROP TABLE IF EXISTS `t_dormitory`;

CREATE TABLE `t_dormitory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dormitoryName` varchar(20) NOT NULL,
  `dormitoryDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_dormitory` */

insert  into `t_dormitory`(`id`,`dormitoryName`,`dormitoryDesc`) values (1,'二公寓','学生二公寓');
insert  into `t_dormitory`(`id`,`dormitoryName`,`dormitoryDesc`) values (2,'三公寓','学生三公寓');
insert  into `t_dormitory`(`id`,`dormitoryName`,`dormitoryDesc`) values (3,'四公寓','学生四公寓');


/*Table structure for table `t_student` */

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `stuId` int(11) NOT NULL AUTO_INCREMENT,
  `stuNo` varchar(20) NOT NULL,
  `stuName` varchar(10) NOT NULL,
  `sex` varchar(5) NOT NULL,
  `birthday` date NOT NULL,
  `facultyId` int(11) NOT NULL,
  `gradeId` int(11) NOT NULL,
  `dormitoryId` int(11) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `stuDesc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`stuId`),
  FOREIGN KEY (`facultyId`) REFERENCES `t_faculty` (`id`),
  FOREIGN KEY (`gradeId`) REFERENCES `t_grade` (`id`),
  FOREIGN KEY (`dormitoryId`) REFERENCES `t_dormitory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Data for the table `t_student` */

/*insert  into `t_student`(`stuId`,`stuNo`,`stuName`,`sex`,`birthday`,`facultyId`,`gradeId`,`dormitoryId`,`email`,`stuDesc`) values (1,'1163710228','刘思琦','男','1999-01-28',1,1,1,'1031901796@qq.com','Good'); */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userName`,`password`) values (1,'hitlsq','lsq19990128');