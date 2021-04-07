BEGIN TRANSACTION;

INSERT INTO tournaments
VALUES
    (
    100
    , 'user hosted name'
    , 'user desc'
    , 1
    , 20
    , 0
    , TRUE
    , TRUE
    , '04/06/2021'
    , '04/07/2021'
    ),
    (
    105
    , 'admin hosted name'
    , 'admin desc'
    , 2
    , 35
    , 0
    , TRUE
    , TRUE
    , '04/10/2021'
    , '04/12/2021'
    )
;

SELECT *
FROM tournaments
;

SELECT *
FROM tournaments
JOIN users ON users.user_id = tournaments.tourney_host
WHERE username ILIKE '%us%'
;

SELECT users.user_id
FROM users
JOIN tournaments ON tournaments.tourney_host = users.user_id
WHERE tournaments.tourney_host = 1
;

UPDATE tournaments
SET tourney_name = 'Hello',
    tourney_desc = 'wow'
WHERE tourney_id = 100
;

SELECT *
FROM tournaments
;

ROLLBACK;