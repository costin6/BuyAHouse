ALTER TABLE property
ADD user_id int AFTER id,
ADD FOREIGN KEY (user_id) REFERENCES user (id);