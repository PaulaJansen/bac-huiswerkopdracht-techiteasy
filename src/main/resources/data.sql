-- Authorities
INSERT INTO authorities (username) VALUES ('READ_TV');
INSERT INTO authorities (username) VALUES ('WRITE_TV');
INSERT INTO authorities (username) VALUES ('DELETE_TV');

-- Roles
INSERT INTO roles (rolename) VALUES ('ROLE_ADMIN');
INSERT INTO roles (rolename) VALUES ('ROLE_USER');

-- Role → Authority
INSERT INTO role_authorities (role_id, authority_id)
SELECT r.id, a.id FROM roles r, authorities a
WHERE r.name = 'ROLE_ADMIN' AND a.name IN ('READ_TV', 'WRITE_TV', 'DELETE_TV');

INSERT INTO role_authorities (role_id, authority_id)
SELECT r.id, a.id FROM roles r, authorities a
WHERE r.name = 'ROLE_USER' AND a.name = 'READ_TV';

-- Users
INSERT INTO users (username, password, enabled) VALUES
                                                        ('admin', '$2a$10$8c8vVXtPR4MSb0SvU.T5fuj1T6.LkUbeVj01/R4CHVsuOjY6LKNpK', true),
                                                        ('user', '$2a$10$ZJZrSr/JqOCp12p9wh4J4uj7lUHrIdzS5PS8ytxOGB0LE7xT8hFrC', true);

-- User → Role
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'admin' AND r.name = 'ROLE_ADMIN';

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'user' AND r.name = 'ROLE_USER';