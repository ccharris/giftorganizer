CREATE SCHEMA IF NOT EXISTS giftOrganizer;

CREATE TABLE IF NOT EXISTS giftOrganizer.users (
  id SERIAL NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  email VARCHAR(128) NOT NULL,
  active boolean NOT NULL DEFAULT true,
  role VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));
  
  CREATE TABLE IF NOT EXISTS giftOrganizer.recipients (
  id SERIAL NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NULL,
  email VARCHAR(128) NULL,
  birthday VARCHAR(100) NULL,
  anniversary VARCHAR(100) NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (id));
  
  CREATE TABLE IF NOT EXISTS giftOrganizer.events (
  id SERIAL NOT NULL,
  name VARCHAR(45) NOT NULL,
  budget DECIMAL(8,2) NULL,
  user_id INT NOT NULL,
  PRIMARY KEY (id));
  
  CREATE TABLE IF NOT EXISTS giftOrganizer.event_recipients (
  id SERIAL NOT NULL,
  recipient_id INT NOT NULL,
  event_id INT NOT NULL,
  PRIMARY KEY (id));
  
  CREATE TABLE IF NOT EXISTS giftOrganizer.gifts (
  id SERIAL NOT NULL,
  name VARCHAR(200) NOT NULL,
  recipient_id INT NOT NULL,
  price DECIMAL(6,2) NULL,
  bought boolean NOT NULL DEFAULT false,
  link_one VARCHAR(500) NULL,
  link_two VARCHAR(500) NULL,
  PRIMARY KEY (id));
