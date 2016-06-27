




-- -----------------------------------------------------------
-- Entity Designer DDL Script for MySQL Server 4.1 and higher
-- -----------------------------------------------------------
-- Date Created: 06/28/2016 00:01:21
-- Generated from EDMX file: C:\Users\Jozef\Documents\visual studio 2013\Projects\Ftn.Android.backend\Ftn.Android.backend\FTNBackend.edmx
-- Target version: 3.0.0.0
-- --------------------------------------------------

DROP DATABASE IF EXISTS `FtnBackEnd`;
CREATE DATABASE `FtnBackEnd`;
USE `FtnBackEnd`;

-- --------------------------------------------------
-- Dropping existing FOREIGN KEY constraints
-- NOTE: if the constraint does not exist, an ignorable error will be reported.
-- --------------------------------------------------


-- --------------------------------------------------
-- Dropping existing tables
-- --------------------------------------------------
SET foreign_key_checks = 0;
SET foreign_key_checks = 1;

-- --------------------------------------------------
-- Creating all tables
-- --------------------------------------------------

CREATE TABLE `LocatableDTOes`(
	`Id` int NOT NULL AUTO_INCREMENT UNIQUE, 
	`Lattitude` double NOT NULL, 
	`Longittude` double NOT NULL, 
	`Name` longtext NOT NULL, 
	`Description` longtext NOT NULL, 
	`Date` datetime NOT NULL, 
	`Type` bool NOT NULL, 
	`Active` bool NOT NULL, 
	`Author_Id` int NOT NULL);

ALTER TABLE `LocatableDTOes` ADD PRIMARY KEY (Id);




CREATE TABLE `Images`(
	`Id` int NOT NULL AUTO_INCREMENT UNIQUE, 
	`Image` longtext NOT NULL, 
	`LocatableDTO_Id` int NOT NULL);

ALTER TABLE `Images` ADD PRIMARY KEY (Id);




CREATE TABLE `UserDtoes`(
	`Email` longtext NOT NULL, 
	`UserName` longtext NOT NULL, 
	`Password` longtext NOT NULL, 
	`Longittude` double NOT NULL, 
	`Lattitude` double NOT NULL, 
	`Id` int NOT NULL AUTO_INCREMENT UNIQUE);

ALTER TABLE `UserDtoes` ADD PRIMARY KEY (Id);




CREATE TABLE `ConfirmedFromUser`(
	`ConfirmedLocatable_Id` int NOT NULL, 
	`ConfirmedFrom_Id` int NOT NULL);

ALTER TABLE `ConfirmedFromUser` ADD PRIMARY KEY (ConfirmedLocatable_Id, ConfirmedFrom_Id);






-- --------------------------------------------------
-- Creating all FOREIGN KEY constraints
-- --------------------------------------------------

-- Creating foreign key on `LocatableDTO_Id` in table 'Images'

ALTER TABLE `Images`
ADD CONSTRAINT `FK_LocatableDTOImages`
    FOREIGN KEY (`LocatableDTO_Id`)
    REFERENCES `LocatableDTOes`
        (`Id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_LocatableDTOImages'

CREATE INDEX `IX_FK_LocatableDTOImages` 
    ON `Images`
    (`LocatableDTO_Id`);

-- Creating foreign key on `Author_Id` in table 'LocatableDTOes'

ALTER TABLE `LocatableDTOes`
ADD CONSTRAINT `FK_AuthorOfLocatableDTO`
    FOREIGN KEY (`Author_Id`)
    REFERENCES `UserDtoes`
        (`Id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_AuthorOfLocatableDTO'

CREATE INDEX `IX_FK_AuthorOfLocatableDTO` 
    ON `LocatableDTOes`
    (`Author_Id`);

-- Creating foreign key on `ConfirmedLocatable_Id` in table 'LocatableDTOUserDto1'

ALTER TABLE `LocatableDTOUserDto1`
ADD CONSTRAINT `FK_ConfirmedFromUser_LocatableDTO`
    FOREIGN KEY (`ConfirmedLocatable_Id`)
    REFERENCES `LocatableDTOes`
        (`Id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating foreign key on `ConfirmedFrom_Id` in table 'LocatableDTOUserDto1'

ALTER TABLE `LocatableDTOUserDto1`
ADD CONSTRAINT `FK_ConfirmedFromUser_UserDto`
    FOREIGN KEY (`ConfirmedFrom_Id`)
    REFERENCES `UserDtoes`
        (`Id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_ConfirmedFromUser_UserDto'

CREATE INDEX `IX_FK_ConfirmedFromUser_UserDto` 
    ON `LocatableDTOUserDto1`
    (`ConfirmedFrom_Id`);

-- --------------------------------------------------
-- Script has ended
-- --------------------------------------------------
