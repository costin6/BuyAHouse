ALTER TABLE user
ADD user_role_id int,
ADD FOREIGN KEY (user_role_id) REFERENCES user_role (user_role_id);