DROP TABLE IF EXISTS tournaments CASCADE;

CREATE TABLE tournaments
    (
    tourney_id SERIAL
    , tourney_name CHARACTER VARYING(20)
    , tourney_desc CHARACTER VARYING(100)
    , tourney_host INTEGER
    , scheduled_time TIMESTAMP
    , tourney_is_active BOOLEAN
    , particpant_num INTEGER
    , CONSTRAINT pk_tournaments_tourney_id
        PRIMARY KEY (tourney_id)
    , CONSTRAINT fk_users_user_id
        FOREIGN KEY (tourney_host) REFERENCES
        users(user_id)
    )
;