DROP TABLE IF EXISTS tournaments CASCADE;
DROP TABLE IF EXISTS matches CASCADE;
DROP TABLE IF EXISTS tournaments_matches CASCADE;
DROP TABLE IF EXISTS users_matches CASCADE;

CREATE TABLE tournaments
    (
    tourney_id          SERIAL
    , tourney_name      CHARACTER VARYING(20)
    , tourney_desc      CHARACTER VARYING(100)
    , tourney_host      INTEGER
    , start_date        DATE
    , end_date          DATE
    , tourney_is_active BOOLEAN
    , open_for_reg      BOOLEAN
    , particpant_num    INTEGER
    , CONSTRAINT pk_tournaments_tourney_id
        PRIMARY KEY (tourney_id)
    )
;

--determine extras "nice to haves" vs requirements of projects
--need location integation for google maps finding tournament
--team_rank needs assigned to a table for ranking
CREATE TABLE matches
        (
        match_id         SERIAL
        , start_time     TIME
        , start_date     DATE
        , CONSTRAINT pk_matches_match_id
              PRIMARY KEY (match_id)
    );

CREATE TABLE tournaments_matches
    (
    tourney_id INTEGER
    , match_id INTEGER
    , CONSTRAINT pk_tournaments_matches_tourney_id_match_id
        PRIMARY KEY (tourney_id, match_id)
    )
;

CREATE TABLE users_matches
    (
    user_id INTEGER
    , match_id INTEGER
    , CONSTRAINT pk_users_matches_user_id_match_id
        PRIMARY KEY (user_id, match_id)
    )
;

-- Setting foreign keys
ALTER TABLE tournaments
    ADD CONSTRAINT fk_users_user_id
    FOREIGN KEY (tourney_host)
    REFERENCES users(user_id)
;

ALTER TABLE tournaments_matches
    ADD CONSTRAINT fk_tournaments_tourney_id
    FOREIGN KEY (tourney_id)
    REFERENCES tournaments(tourney_id)
;

ALTER TABLE tournaments_matches
    ADD CONSTRAINT fk_tournaments_match_id
    FOREIGN KEY (match_id)
    REFERENCES matches(match_id)
;

ALTER TABLE users_matches
    ADD CONSTRAINT fk_users_user_id
    FOREIGN KEY (user_id)
    REFERENCES users(user_id)
;

ALTER TABLE users_matches
    ADD CONSTRAINT fk_matches_match_id
    FOREIGN KEY (match_id)
    REFERENCES matches(match_id)
;