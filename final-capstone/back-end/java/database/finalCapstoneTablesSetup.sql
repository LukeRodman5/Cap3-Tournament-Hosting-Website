DROP TABLE IF EXISTS tournaments CASCADE;
DROP TABLE IF EXISTS matches CASCADE;

CREATE TABLE tournaments
    (
    tourney_id SERIAL
    , tourney_name CHARACTER VARYING(20)
    , tourney_desc CHARACTER VARYING(100)
    , tourney_host INTEGER
    , start_date DATE
    , end_date DATE
    , tourney_is_active BOOLEAN
    , particpant_num INTEGER
    , CONSTRAINT pk_tournaments_tourney_id
        PRIMARY KEY (tourney_id)
    , CONSTRAINT fk_users_user_id
        FOREIGN KEY (tourney_host) REFERENCES
        users(user_id)
    )
;
CREATE TABLE matches
        (
          match_id SERIAL
        , competitor_one CHARACTER VARYING (50)
        , competitor_two CHARACTER VARYING (50)
        , start_time     TIME
        , start_date     DATE
        , CONSTRAINT pk_matches_match_id
              PRIMARY KEY (match_id)
     --  , CONSTRAINT fk_users_username
     --         FOREIGN KEY (competitor_one) REFERENCES
     --                 users(username)        
     --   , CONSTRAINT fk_users_username2
     --         FOREIGN KEY (competitor_two) REFERENCES
     --                  users(username)
    );       
        
    
    
    --determine extras "nice to haves" vs requirements of projects
    --need location integation for google maps finding tournament
    --, team_rank **needs assigned to a table for ranking