CREATE SCHEMA IF NOT EXISTS giftOrganizer;
use giftOrganizer;

CREATE TABLE IF NOT EXISTS giftOrganizer.users (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  email VARCHAR(128) NOT NULL,
  active tinyint(1) NOT NULL DEFAULT 1,
  role VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));
  
  CREATE TABLE IF NOT EXISTS giftOrganizer.recipients (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NULL,
  email VARCHAR(128) NULL,
  birthday VARCHAR(100) NULL,
  notes VARCHAR(100) NULL,
  user_id INT NOT NULL,
  group_tag VARCHAR(100) NULL,
  PRIMARY KEY (id));
  
  CREATE TABLE IF NOT EXISTS giftOrganizer.events (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  budget DECIMAL(8,2) NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (id));
  
  CREATE TABLE IF NOT EXISTS giftOrganizer.event_recipients (
  id INT NOT NULL AUTO_INCREMENT,
  recipient_id INT NOT NULL,
  event_id INT NOT NULL,
  PRIMARY KEY (id));
  
  CREATE TABLE IF NOT EXISTS giftOrganizer.gifts (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL,
  recipient_id INT NOT NULL,
  price DECIMAL(6,2) NULL,
  bought tinyint(1) NOT NULL DEFAULT 0,
  link_one VARCHAR(500) NULL,
  link_two VARCHAR(500) NULL,
  PRIMARY KEY (id));
