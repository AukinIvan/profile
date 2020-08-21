INSERT INTO errors (msg, created)
VALUES ('Not Found', '2020-08-17 03:21:53'),
       ('Fail', '2020-08-15 03:21:53');

INSERT INTO profiles (name, email, age, created)
VALUES ('Victor', 'victor@ya.ru', 25, '2020-08-14 03:21:53'),
       ('Max', 'max@ya.ru', 28, '2020-08-10 03:21:53');

INSERT INTO users (name, password)
VALUES ('User', '$2a$10$P58xXLwg8cW9m5TvegBWUeni6g6jYcjdCdZciTOrP7Ejom550eUwy');

INSERT INTO user_token (user_id, token)
VALUES (1, 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTk5MTY2ODAwfQ.iLIrO-Osc3WIbREbQGjQroxW1u1YramNh3-yQ61lD_wUmFZY8xH2TnIl-aclUyopdykb01BsyQdXNqAUqKIUzQ');