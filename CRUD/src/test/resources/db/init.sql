CREATE TABLE springtest.person
(
    id     BIGINT       NOT NULL AUTO_INCREMENT,
    name   VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NULL,
    email VARCHAR(100) NULL,
    password VARCHAR(100) NULL,
    PRIMARY KEY (ID)
) ENGINE = InnoDB;
