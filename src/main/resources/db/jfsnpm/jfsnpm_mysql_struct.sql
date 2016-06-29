/*
Navicat MySQL Data Transfer

Source Server         : svr-mdt
Source Server Version : 50621
Source Host           : svr-mdt:3306
Source Database       : jfsnpm

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-01-07 14:41:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for form_concert
-- ----------------------------
DROP TABLE IF EXISTS `form_concert`;
CREATE TABLE `form_concert` (
  `id` varchar(32) NOT NULL,
  `pm_creator` varchar(32) DEFAULT NULL,
  `pm_create_time` datetime DEFAULT NULL,
  `concert_need_date` datetime DEFAULT NULL,
  `concert_title` longtext,
  `dousers` longtext,
  `dousersname` longtext,
  `concert_send` longtext,
  `douser` varchar(100) DEFAULT NULL,
  `dousername` varchar(100) DEFAULT NULL,
  `concert_get` longtext,
  `concert_complete` longtext,
  `concert_close` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for form_concert_detail
-- ----------------------------
DROP TABLE IF EXISTS `form_concert_detail`;
CREATE TABLE `form_concert_detail` (
  `id` varchar(32) NOT NULL,
  `parentid` varchar(32) NOT NULL,
  `itemname` varchar(100) DEFAULT NULL,
  `itemdesc` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for form_leave
-- ----------------------------
DROP TABLE IF EXISTS `form_leave`;
CREATE TABLE `form_leave` (
  `id` varchar(32) NOT NULL,
  `pm_creator` varchar(32) DEFAULT NULL,
  `pm_create_time` datetime DEFAULT NULL,
  `leave_title` varchar(200) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `approveDept` varchar(200) DEFAULT NULL,
  `approveBoss` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpm_demo
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpm_demo`;
CREATE TABLE `jfsnpm_demo` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpm_ext_wf_order
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpm_ext_wf_order`;
CREATE TABLE `jfsnpm_ext_wf_order` (
  `orderId` varchar(32) NOT NULL,
  `orderTitle` longtext,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpm_file
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpm_file`;
CREATE TABLE `jfsnpm_file` (
  `id` varchar(32) NOT NULL,
  `fileName` varchar(200) NOT NULL,
  `fileDesc` longtext,
  `filePath` longtext NOT NULL,
  `fileType` varchar(50) NOT NULL,
  `releKey` varchar(50) DEFAULT NULL,
  `modifier` varchar(50) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpm_form_d
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpm_form_d`;
CREATE TABLE `jfsnpm_form_d` (
  `id` varchar(32) NOT NULL,
  `processName` varchar(100) NOT NULL,
  `formName` varchar(100) NOT NULL,
  `sortNo` varchar(10) DEFAULT NULL,
  `formColumn` varchar(100) DEFAULT NULL,
  `formLabel` varchar(100) DEFAULT NULL,
  `rowYn` varchar(10) DEFAULT NULL,
  `updateYN` varchar(10) DEFAULT NULL,
  `editYN` varchar(10) DEFAULT NULL,
  `requiredYN` varchar(10) DEFAULT NULL,
  `argsYN` varchar(10) DEFAULT NULL,
  `defaultValue` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `otherArgs` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpm_form_g
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpm_form_g`;
CREATE TABLE `jfsnpm_form_g` (
  `id` varchar(32) NOT NULL,
  `processName` varchar(100) NOT NULL,
  `formName` varchar(100) NOT NULL,
  `sortNo` varchar(10) DEFAULT NULL,
  `formCname` varchar(100) DEFAULT NULL,
  `formClabel` varchar(100) DEFAULT NULL,
  `formCwidth` varchar(100) DEFAULT NULL,
  `formCalign` varchar(100) DEFAULT NULL,
  `formCtype` varchar(100) DEFAULT NULL,
  `formCadd` varchar(100) DEFAULT NULL,
  `formCedit` varchar(100) DEFAULT NULL,
  `formCattrs` longtext,
  `formCrule` longtext,
  `formCitems` longtext,
  `formCrender` longtext,
  `formCpattern` varchar(100) DEFAULT NULL,
  `formCcalc` varchar(100) DEFAULT NULL,
  `formCcalcTit` varchar(100) DEFAULT NULL,
  `formCcalcDecimal` varchar(100) DEFAULT NULL,
  `formChide` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpm_form_h
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpm_form_h`;
CREATE TABLE `jfsnpm_form_h` (
  `id` varchar(32) NOT NULL,
  `processName` varchar(100) NOT NULL,
  `formName` varchar(100) NOT NULL,
  `formRejTo` varchar(100) DEFAULT NULL,
  `formDisplayName` varchar(100) DEFAULT NULL,
  `formUpdateTitle` varchar(100) DEFAULT NULL,
  `formUpdateDesc` varchar(100) DEFAULT NULL,
  `formOperaType1` varchar(100) DEFAULT NULL,
  `formOperaType2` varchar(100) DEFAULT NULL,
  `formOperaType3` varchar(100) DEFAULT NULL,
  `formGridDbName` varchar(100) DEFAULT NULL,
  `formGridDbKey` varchar(100) DEFAULT NULL,
  `formGridAuth` varchar(100) DEFAULT NULL,
  `formAttAuth` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpm_logs
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpm_logs`;
CREATE TABLE `jfsnpm_logs` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(32) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpm_menu
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpm_menu`;
CREATE TABLE `jfsnpm_menu` (
  `id` varchar(32) NOT NULL,
  `pId` varchar(32) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `text` varchar(100) NOT NULL,
  `sortNo` varchar(100) NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpm_org
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpm_org`;
CREATE TABLE `jfsnpm_org` (
  `id` varchar(32) NOT NULL,
  `pId` varchar(32) DEFAULT NULL,
  `text` varchar(100) DEFAULT NULL,
  `sortNo` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpm_role
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpm_role`;
CREATE TABLE `jfsnpm_role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpm_user
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpm_user`;
CREATE TABLE `jfsnpm_user` (
  `id` varchar(32) NOT NULL,
  `userNo` varchar(100) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `userMail` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `expirytime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpmr_menu_org
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpmr_menu_org`;
CREATE TABLE `jfsnpmr_menu_org` (
  `menuId` varchar(32) NOT NULL,
  `orgId` varchar(32) NOT NULL,
  PRIMARY KEY (`menuId`,`orgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpmr_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpmr_menu_role`;
CREATE TABLE `jfsnpmr_menu_role` (
  `menuId` varchar(32) NOT NULL,
  `roleId` varchar(32) NOT NULL,
  PRIMARY KEY (`menuId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpmr_org_user
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpmr_org_user`;
CREATE TABLE `jfsnpmr_org_user` (
  `orgId` varchar(32) NOT NULL,
  `userId` varchar(32) NOT NULL,
  PRIMARY KEY (`orgId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpmr_process_role
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpmr_process_role`;
CREATE TABLE `jfsnpmr_process_role` (
  `processId` varchar(32) NOT NULL,
  `roleId` varchar(32) NOT NULL,
  PRIMARY KEY (`processId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for jfsnpmr_role_user
-- ----------------------------
DROP TABLE IF EXISTS `jfsnpmr_role_user`;
CREATE TABLE `jfsnpmr_role_user` (
  `roleId` varchar(32) NOT NULL,
  `userId` varchar(32) NOT NULL,
  PRIMARY KEY (`roleId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
