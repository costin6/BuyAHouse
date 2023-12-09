CREATE TABLE property_type
(
    id         int NOT NULL AUTO_INCREMENT,
    name       varchar(50),
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE property
(
    id   int     NOT NULL AUTO_INCREMENT,
    name varchar(50),
    description varchar(500),
    area int NOT NULL,
    rooms int NOT NULL,
    price decimal NOT NULL,
    city varchar(50),
    parking_included bool not null,
    furnished bool,
    max_occupancy int NOT NULL,
    outside_area bool,
    property_type_id int NOT NULL,
    address varchar(200),
    construction_year int NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name, address),
    FOREIGN KEY (property_type_id) REFERENCES property_type (id)
);