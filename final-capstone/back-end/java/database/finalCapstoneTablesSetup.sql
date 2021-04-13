DROP TABLE IF EXISTS tournaments         CASCADE;
DROP TABLE IF EXISTS matches             CASCADE;
DROP TABLE IF EXISTS users_tournaments   CASCADE;
DROP TABLE IF EXISTS tournaments_matches CASCADE;
DROP TABLE IF EXISTS users_matches       CASCADE;

CREATE TABLE tournaments
    ( tourney_id        SERIAL
    , tourney_name      CHARACTER VARYING(50)
    , tourney_desc      CHARACTER VARYING(200)
    , tourney_host      INTEGER
    , participant_max   INTEGER
    , participant_num   INTEGER
    , tourney_is_active BOOLEAN
    , open_for_reg      BOOLEAN
    , start_date        DATE
    , end_date          DATE
    , CONSTRAINT pk_tournaments_tourney_id
        PRIMARY KEY (tourney_id));

-- Determine "nice to haves" vs requirements of overall project
-- Need location integation for Google maps for tournament finding
-- Team_Rank needs assigned to a table for ranking

CREATE TABLE matches
  ( match_id   SERIAL
  , start_time TIME
  , start_date DATE
  , round_level INTEGER
  , CONSTRAINT pk_matches_match_id
       PRIMARY KEY (match_id));
       
CREATE TABLE users_tournaments
  ( user_id      INTEGER
  , tourney_id   INTEGER
  , status       CHARACTER VARYING(50)
  , CONSTRAINT pk_users_tournaments_user_id_tourney_id
       PRIMARY KEY (user_id, tourney_id));

CREATE TABLE tournaments_matches
  ( tourney_id INTEGER
  , match_id   INTEGER
  , CONSTRAINT pk_tournaments_matches_tourney_id_match_id
       PRIMARY KEY (tourney_id, match_id));

CREATE TABLE users_matches
  ( user_id    INTEGER
  , match_id   INTEGER
  , win_status BOOLEAN
  , CONSTRAINT pk_users_matches_user_id_match_id
       PRIMARY KEY (user_id, match_id));

-- Setting foreign keys
ALTER TABLE      tournaments
  ADD CONSTRAINT fk_users_user_id
  FOREIGN KEY    (tourney_host)
  REFERENCES     users(user_id);

ALTER TABLE      tournaments_matches
  ADD CONSTRAINT fk_tournaments_tourney_id
  FOREIGN KEY    (tourney_id)
  REFERENCES     tournaments(tourney_id);

ALTER TABLE      tournaments_matches
  ADD CONSTRAINT fk_tournaments_match_id
  FOREIGN KEY    (match_id)
  REFERENCES     matches(match_id);

ALTER TABLE      users_matches
  ADD CONSTRAINT fk_users_user_id
  FOREIGN KEY    (user_id)
  REFERENCES     users(user_id);

ALTER TABLE      users_matches
  ADD CONSTRAINT fk_matches_match_id
  FOREIGN KEY    (match_id)
  REFERENCES     matches(match_id);
  
ALTER TABLE      users_tournaments
  ADD CONSTRAINT fk_users_user_id
  FOREIGN KEY    (user_id)
  REFERENCES     users(user_id);

ALTER TABLE      users_tournaments
  ADD CONSTRAINT fk_tournaments_tournament_id
  FOREIGN KEY    (tourney_id)
  REFERENCES     tournaments(tourney_id);