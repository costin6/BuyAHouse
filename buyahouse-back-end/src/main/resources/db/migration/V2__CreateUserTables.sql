CREATE TABLE user
(
    id int NOT NULL AUTO_INCREMENT,
    username varchar(20)  NOT NULL,
    password varchar(100) NOT NULL,
    phone_number varchar(20),
    first_name varchar(50),
    last_name varchar(50),
    PRIMARY KEY (id),
    UNIQUE (username)
);