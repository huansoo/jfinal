/*
Navicat SQL Server Data Transfer

Source Server         : SQLSERVER-SVR2
Source Server Version : 105000
Source Host           : SVR2:1433
Source Database       : jfsnpm
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 105000
File Encoding         : 65001

Date: 2016-01-07 14:23:10
*/


-- ----------------------------
-- Table structure for form_concert
-- ----------------------------
DROP TABLE [dbo].[form_concert]
GO
CREATE TABLE [dbo].[form_concert] (
[id] nvarchar(32) NOT NULL ,
[pm_creator] nvarchar(32) NULL ,
[pm_create_time] datetime NULL ,
[concert_need_date] datetime NULL ,
[concert_title] nvarchar(MAX) NULL ,
[dousers] nvarchar(MAX) NULL ,
[dousersname] nvarchar(MAX) NULL ,
[concert_send] nvarchar(MAX) NULL ,
[douser] nvarchar(100) NULL ,
[dousername] nvarchar(100) NULL ,
[concert_get] nvarchar(MAX) NULL ,
[concert_complete] nvarchar(MAX) NULL ,
[concert_close] nvarchar(MAX) NULL 
)


GO

-- ----------------------------
-- Table structure for form_concert_detail
-- ----------------------------
DROP TABLE [dbo].[form_concert_detail]
GO
CREATE TABLE [dbo].[form_concert_detail] (
[id] nvarchar(32) NOT NULL ,
[parentid] nvarchar(32) NOT NULL ,
[itemname] nvarchar(100) NULL ,
[itemdesc] nvarchar(MAX) NULL 
)


GO

-- ----------------------------
-- Table structure for form_leave
-- ----------------------------
DROP TABLE [dbo].[form_leave]
GO
CREATE TABLE [dbo].[form_leave] (
[id] nvarchar(32) NOT NULL ,
[pm_creator] nvarchar(32) NULL ,
[pm_create_time] datetime NULL ,
[leave_title] nvarchar(200) NULL ,
[day] int NULL ,
[approveDept] nvarchar(200) NULL ,
[approveBoss] nvarchar(200) NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpm_demo
-- ----------------------------
DROP TABLE [dbo].[jfsnpm_demo]
GO
CREATE TABLE [dbo].[jfsnpm_demo] (
[id] nvarchar(32) NOT NULL ,
[name] nvarchar(100) NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpm_ext_wf_order
-- ----------------------------
DROP TABLE [dbo].[jfsnpm_ext_wf_order]
GO
CREATE TABLE [dbo].[jfsnpm_ext_wf_order] (
[orderId] nvarchar(32) NOT NULL ,
[orderTitle] nvarchar(MAX) NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpm_file
-- ----------------------------
DROP TABLE [dbo].[jfsnpm_file]
GO
CREATE TABLE [dbo].[jfsnpm_file] (
[id] nvarchar(32) NOT NULL ,
[fileName] nvarchar(200) NOT NULL ,
[fileDesc] nvarchar(MAX) NULL ,
[filePath] nvarchar(MAX) NOT NULL ,
[fileType] nvarchar(50) NOT NULL ,
[releKey] nvarchar(50) NULL ,
[modifier] nvarchar(50) NULL ,
[modifyDate] datetime NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpm_form_d
-- ----------------------------
DROP TABLE [dbo].[jfsnpm_form_d]
GO
CREATE TABLE [dbo].[jfsnpm_form_d] (
[id] nvarchar(32) NOT NULL ,
[processName] nvarchar(100) NOT NULL ,
[formName] nvarchar(100) NOT NULL ,
[sortNo] nvarchar(10) NULL ,
[formColumn] nvarchar(100) NULL ,
[formLabel] nvarchar(100) NULL ,
[rowYn] nvarchar(10) NULL ,
[updateYN] nvarchar(10) NULL ,
[editYN] nvarchar(10) NULL ,
[requiredYN] nvarchar(10) NULL ,
[argsYN] nvarchar(10) NULL ,
[defaultValue] nvarchar(100) NULL ,
[type] nvarchar(50) NULL ,
[otherArgs] nvarchar(MAX) NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpm_form_g
-- ----------------------------
DROP TABLE [dbo].[jfsnpm_form_g]
GO
CREATE TABLE [dbo].[jfsnpm_form_g] (
[id] nvarchar(32) NOT NULL ,
[processName] nvarchar(100) NOT NULL ,
[formName] nvarchar(100) NOT NULL ,
[sortNo] nvarchar(10) NULL ,
[formCname] nvarchar(100) NULL ,
[formClabel] nvarchar(100) NULL ,
[formCwidth] nvarchar(100) NULL ,
[formCalign] nvarchar(100) NULL ,
[formCtype] nvarchar(100) NULL ,
[formCadd] nvarchar(100) NULL ,
[formCedit] nvarchar(100) NULL ,
[formCattrs] nvarchar(MAX) NULL ,
[formCrule] nvarchar(MAX) NULL ,
[formCitems] nvarchar(MAX) NULL ,
[formCrender] nvarchar(MAX) NULL ,
[formCpattern] nvarchar(100) NULL ,
[formCcalc] nvarchar(100) NULL ,
[formCcalcTit] nvarchar(100) NULL ,
[formCcalcDecimal] nvarchar(100) NULL ,
[formChide] nvarchar(100) NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpm_form_h
-- ----------------------------
DROP TABLE [dbo].[jfsnpm_form_h]
GO
CREATE TABLE [dbo].[jfsnpm_form_h] (
[id] nvarchar(32) NOT NULL ,
[processName] nvarchar(100) NOT NULL ,
[formName] nvarchar(100) NOT NULL ,
[formRejTo] nvarchar(100) NULL ,
[formDisplayName] nvarchar(100) NULL ,
[formUpdateTitle] nvarchar(100) NULL ,
[formUpdateDesc] nvarchar(100) NULL ,
[formOperaType1] nvarchar(100) NULL ,
[formOperaType2] nvarchar(100) NULL ,
[formOperaType3] nvarchar(100) NULL ,
[formGridDbName] nvarchar(100) NULL ,
[formGridDbKey] nvarchar(100) NULL ,
[formGridAuth] nvarchar(100) NULL ,
[formAttAuth] nvarchar(100) NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpm_logs
-- ----------------------------
DROP TABLE [dbo].[jfsnpm_logs]
GO
CREATE TABLE [dbo].[jfsnpm_logs] (
[id] nvarchar(50) NOT NULL ,
[userId] nvarchar(32) NULL ,
[url] nvarchar(100) NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpm_menu
-- ----------------------------
DROP TABLE [dbo].[jfsnpm_menu]
GO
CREATE TABLE [dbo].[jfsnpm_menu] (
[id] nvarchar(32) NOT NULL ,
[pId] nvarchar(32) NULL ,
[url] nvarchar(100) NULL ,
[text] nvarchar(100) NOT NULL ,
[sortNo] nvarchar(100) NOT NULL ,
[status] nvarchar(20) NOT NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpm_org
-- ----------------------------
DROP TABLE [dbo].[jfsnpm_org]
GO
CREATE TABLE [dbo].[jfsnpm_org] (
[id] nvarchar(32) NOT NULL ,
[pId] nvarchar(32) NULL ,
[text] nvarchar(100) NULL ,
[sortNo] nvarchar(100) NULL ,
[status] nvarchar(20) NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpm_role
-- ----------------------------
DROP TABLE [dbo].[jfsnpm_role]
GO
CREATE TABLE [dbo].[jfsnpm_role] (
[id] nvarchar(32) NOT NULL ,
[name] nvarchar(100) NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpm_user
-- ----------------------------
DROP TABLE [dbo].[jfsnpm_user]
GO
CREATE TABLE [dbo].[jfsnpm_user] (
[id] nvarchar(32) NOT NULL ,
[userNo] nvarchar(100) NULL ,
[userName] nvarchar(100) NULL ,
[userMail] nvarchar(100) NULL ,
[password] nvarchar(100) NULL ,
[status] nvarchar(20) NULL ,
[expirytime] datetime NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpmr_menu_org
-- ----------------------------
DROP TABLE [dbo].[jfsnpmr_menu_org]
GO
CREATE TABLE [dbo].[jfsnpmr_menu_org] (
[menuId] nvarchar(32) NOT NULL ,
[orgId] nvarchar(32) NOT NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpmr_menu_role
-- ----------------------------
DROP TABLE [dbo].[jfsnpmr_menu_role]
GO
CREATE TABLE [dbo].[jfsnpmr_menu_role] (
[menuId] nvarchar(32) NOT NULL ,
[roleId] nvarchar(32) NOT NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpmr_org_user
-- ----------------------------
DROP TABLE [dbo].[jfsnpmr_org_user]
GO
CREATE TABLE [dbo].[jfsnpmr_org_user] (
[orgId] nvarchar(32) NOT NULL ,
[userId] nvarchar(32) NOT NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpmr_process_role
-- ----------------------------
DROP TABLE [dbo].[jfsnpmr_process_role]
GO
CREATE TABLE [dbo].[jfsnpmr_process_role] (
[processId] nvarchar(32) NOT NULL ,
[roleId] nvarchar(32) NOT NULL 
)


GO

-- ----------------------------
-- Table structure for jfsnpmr_role_user
-- ----------------------------
DROP TABLE [dbo].[jfsnpmr_role_user]
GO
CREATE TABLE [dbo].[jfsnpmr_role_user] (
[roleId] nvarchar(32) NOT NULL ,
[userId] nvarchar(32) NOT NULL 
)


GO

-- ----------------------------
-- Indexes structure for table form_concert
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table form_concert
-- ----------------------------
ALTER TABLE [dbo].[form_concert] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table form_concert_detail
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table form_concert_detail
-- ----------------------------
ALTER TABLE [dbo].[form_concert_detail] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table form_leave
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table form_leave
-- ----------------------------
ALTER TABLE [dbo].[form_leave] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table jfsnpm_demo
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpm_demo
-- ----------------------------
ALTER TABLE [dbo].[jfsnpm_demo] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table jfsnpm_ext_wf_order
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpm_ext_wf_order
-- ----------------------------
ALTER TABLE [dbo].[jfsnpm_ext_wf_order] ADD PRIMARY KEY ([orderId])
GO

-- ----------------------------
-- Indexes structure for table jfsnpm_file
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpm_file
-- ----------------------------
ALTER TABLE [dbo].[jfsnpm_file] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table jfsnpm_form_d
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpm_form_d
-- ----------------------------
ALTER TABLE [dbo].[jfsnpm_form_d] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table jfsnpm_form_g
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpm_form_g
-- ----------------------------
ALTER TABLE [dbo].[jfsnpm_form_g] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table jfsnpm_form_h
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpm_form_h
-- ----------------------------
ALTER TABLE [dbo].[jfsnpm_form_h] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table jfsnpm_logs
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpm_logs
-- ----------------------------
ALTER TABLE [dbo].[jfsnpm_logs] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table jfsnpm_menu
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpm_menu
-- ----------------------------
ALTER TABLE [dbo].[jfsnpm_menu] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table jfsnpm_org
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpm_org
-- ----------------------------
ALTER TABLE [dbo].[jfsnpm_org] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table jfsnpm_role
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpm_role
-- ----------------------------
ALTER TABLE [dbo].[jfsnpm_role] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table jfsnpm_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpm_user
-- ----------------------------
ALTER TABLE [dbo].[jfsnpm_user] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table jfsnpmr_menu_org
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpmr_menu_org
-- ----------------------------
ALTER TABLE [dbo].[jfsnpmr_menu_org] ADD PRIMARY KEY ([menuId], [orgId])
GO

-- ----------------------------
-- Indexes structure for table jfsnpmr_menu_role
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpmr_menu_role
-- ----------------------------
ALTER TABLE [dbo].[jfsnpmr_menu_role] ADD PRIMARY KEY ([menuId], [roleId])
GO

-- ----------------------------
-- Indexes structure for table jfsnpmr_org_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpmr_org_user
-- ----------------------------
ALTER TABLE [dbo].[jfsnpmr_org_user] ADD PRIMARY KEY ([orgId], [userId])
GO

-- ----------------------------
-- Indexes structure for table jfsnpmr_process_role
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpmr_process_role
-- ----------------------------
ALTER TABLE [dbo].[jfsnpmr_process_role] ADD PRIMARY KEY ([processId], [roleId])
GO

-- ----------------------------
-- Indexes structure for table jfsnpmr_role_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table jfsnpmr_role_user
-- ----------------------------
ALTER TABLE [dbo].[jfsnpmr_role_user] ADD PRIMARY KEY ([roleId], [userId])
GO
