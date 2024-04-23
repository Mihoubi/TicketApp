CREATE DATABASE IF NOT EXISTS book;
USE book;
CREATE TABLE IF NOT EXISTS bus (
                                   id bigint AUTO_INCREMENT,
                                   line_price double,
                                   name varchar(255),
    PRIMARY KEY (id)
    );
--CREATE TABLE IF NOT EXISTS programinglanguage (
--    name varchar(255),
--    rating bigint,
--    PRIMARY KEY (name)
--    );
CREATE TABLE IF NOT EXISTS passenger (
                                         id bigint AUTO_INCREMENT,
                                         last_bus varchar(255),
    name varchar(255),
    price double,
    user_id bigint,
    PRIMARY KEY (id)
    );
CREATE TABLE IF NOT EXISTS user (
                                    id bigint AUTO_INCREMENT,
                                    name varchar(255),
    password varchar(255),
    PRIMARY KEY (id)
    );
INSERT INTO
    bus(line_price, name)
VALUES
    (5.5, "11us"),
    (5.5, "18d"),
    (5.5, "um73"),
    (5.5, "132p"),
    (11, "522st");