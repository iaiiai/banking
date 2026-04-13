BEGIN;
INSERT INTO roles(name) VALUES ('USER');
INSERT INTO roles(name) VALUES ('ADMIN');
INSERT INTO roles(name) VALUES ('SYSTEM');

WITH sys_user AS (
INSERT INTO users(username, password, role_id, created_at, updated_at)
VALUES (
    'system',
    '$2a$12$EpcnnCB4fs/aTkOA71/ZtOpWPQ.pGuO1X7OkWyjQ3UlxoSLKgg/gC',
    3,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
)
    RETURNING id)
INSERT INTO wallets(balance, user_id, type, created_at, updated_at)
SELECT 1000000000, id, 'SYSTEM', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
FROM sys_user;
COMMIT;