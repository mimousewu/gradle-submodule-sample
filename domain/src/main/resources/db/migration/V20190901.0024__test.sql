CREATE TABLE user (
  id VARCHAR(64) NOT NULL,
  username VARCHAR(64),
  encryptedPassword VARCHAR(255),
  type integer,
  operated_at TIMESTAMP,
  created_at TIMESTAMP,
  last_modified_at TIMESTAMP,
  primary key (id)
);
