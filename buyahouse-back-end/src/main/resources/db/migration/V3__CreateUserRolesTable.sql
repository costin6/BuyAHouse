CREATE TABLE user_role
(
    id int NOT NULL AUTO_INCREMENT,
    role varchar(20) NOT NULL ,
    PRIMARY KEY (id),
    UNIQUE (role)
);