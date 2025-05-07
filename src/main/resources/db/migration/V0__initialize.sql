-- create tables
CREATE TABLE IF NOT EXISTS geography
(
    id   INT GENERATED ALWAYS AS IDENTITY
        CONSTRAINT pk_geography PRIMARY KEY,
    name VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS gamers
(
    id           UUID
        CONSTRAINT pk_gamers PRIMARY KEY,
    username     VARCHAR(64)
        CONSTRAINT gamers_uq_nn_username NOT NULL UNIQUE,
    email        VARCHAR(256)
        CONSTRAINT gamers_uq_nn_email NOT NULL UNIQUE,
    geography_id INT REFERENCES geography (id)
        CONSTRAINT gamers_nn_geography_id NOT NULL,
    created_at   TIMESTAMP DEFAULT NOW(),
    updated_at   TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS games
(
    id       UUID
        CONSTRAINT pk_games PRIMARY KEY,
    name     VARCHAR(256) NOT NULL UNIQUE,
    released DATE DEFAULT NOW()
);

CREATE TYPE level_type AS ENUM ('NOOB', 'PRO', 'INVINCIBLE');

CREATE TABLE IF NOT EXISTS gamers_games
(
    game_id    UUID
        CONSTRAINT fk_games_id REFERENCES games (id) ON DELETE CASCADE                    NOT NULL,
    gamer_id   UUID
        CONSTRAINT fk_gamers_games_info_gamer_id REFERENCES gamers (id) ON DELETE CASCADE NOT NULL,
    level      level_type
        CONSTRAINT gamers_games_info_nn_level NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    CONSTRAINT pk_gamers_games PRIMARY KEY (game_id, gamer_id)
);


-- fill with data
BEGIN TRANSACTION;

INSERT INTO geography (name)
VALUES ('North America'),
       ('South America'),
       ('Europe'),
       ('Asia'),
       ('Africa'),
       ('Oceania');

INSERT INTO gamers (id, username, email, geography_id)
VALUES (gen_random_uuid(), 'RND', 'rnd@gmail.com', 1),
       (gen_random_uuid(), 'beastx', 'beastx@gmail.com', 3),
       (gen_random_uuid(), 'essence', 'essence@gmail.com', 4),
       (gen_random_uuid(), 'racer', 'racer@gmail.com', 6),
       (gen_random_uuid(), 'destructor', 'destructor@gmail.com', 2),
       (gen_random_uuid(), 'puzlex', 'puzlex@gmail.com', 2),
       (gen_random_uuid(), 'kronos', 'kronos@gmail.com', 3),
       (gen_random_uuid(), 'flicks', 'flicks@gmail.com', 2),
       (gen_random_uuid(), 'superalpha', 'super-alpha@gmail.com', 5),
       (gen_random_uuid(), 'superbeta', 'super-beta@gmail.com', 6),
       (gen_random_uuid(), 'vipers', 'vipers@gmail.com', 1),
       (gen_random_uuid(), 'axtonite', 'axtonite@gmail.com', 2),
       (gen_random_uuid(), 'rush', 'rush@gmail.com', 3),
       (gen_random_uuid(), 'knight', 'knight@gmail.com', 4),
       (gen_random_uuid(), 'chapo', 'chapo@gmail.com', 2),
       (gen_random_uuid(), 'mccoym', 'mccoym@gmail.com', 6),
       (gen_random_uuid(), 'hun', 'hun@gmail.com', 2),
       (gen_random_uuid(), 'broken', 'broken@gmail.com', 4),
       (gen_random_uuid(), 'outlaw', 'outlaw@gmail.com', 3),
       (gen_random_uuid(), 'heaven', 'heaven@gmail.com', 5),
       (gen_random_uuid(), 'dark_knight', 'dark-knight@gmail.com', 2),
       (gen_random_uuid(), 'ninja', 'ninja@gmail.com', 2);

INSERT INTO games
VALUES (gen_random_uuid(), 'The Witcher 3', '2015-05-19'),
       (gen_random_uuid(), 'Red Dead Redemption 2', '2018-10-26'),
       (gen_random_uuid(), 'Elden Ring', '2022-02-25'),
       (gen_random_uuid(), 'Half-Life', '1998-11-19'),
       (gen_random_uuid(), 'Doom', '1993-12-10'),
       (gen_random_uuid(), 'Minecraft', '2011-11-18'),
       (gen_random_uuid(), 'Counter-Strike 2', '2023-09-23');

INSERT INTO gamers_games (game_id, gamer_id, level)
VALUES ((SELECT id FROM games WHERE name = 'The Witcher 3'),
        (SELECT id FROM gamers WHERE username = 'beastx'),
        'NOOB'),
       ((SELECT id FROM games WHERE name = 'Minecraft'),
        (SELECT id FROM gamers WHERE username = 'vipers'),
        'PRO'),
       ((SELECT id FROM games WHERE name = 'Elden Ring'),
        (SELECT id FROM gamers WHERE username = 'broken'),
        'INVINCIBLE'),
       ((SELECT id FROM games WHERE name = 'Doom'),
        (SELECT id FROM gamers WHERE username = 'dark_knight'),
        'PRO'),
       ((SELECT id FROM games WHERE name = 'Counter-Strike 2'),
        (SELECT id FROM gamers WHERE username = 'ninja'),
        'INVINCIBLE'),
       ((SELECT id FROM games WHERE name = 'Red Dead Redemption 2'),
        (SELECT id FROM gamers WHERE username = 'superalpha'),
        'NOOB'),
       ((SELECT id FROM games WHERE name = 'Half-Life'),
        (SELECT id FROM gamers WHERE username = 'mccoym'),
        'INVINCIBLE'),
       ((SELECT id FROM games WHERE name = 'The Witcher 3'),
        (SELECT id FROM gamers WHERE username = 'rush'),
        'PRO'),
       ((SELECT id FROM games WHERE name = 'Minecraft'),
        (SELECT id FROM gamers WHERE username = 'essence'),
        'PRO'),
       ((SELECT id FROM games WHERE name = 'Elden Ring'),
        (SELECT id FROM gamers WHERE username = 'outlaw'),
        'NOOB'),
       ((SELECT id FROM games WHERE name = 'Doom'),
        (SELECT id FROM gamers WHERE username = 'racer'),
        'PRO'),

       ((SELECT id FROM games WHERE name = 'Red Dead Redemption 2'),
        (SELECT id FROM gamers WHERE username = 'knight'),
        'NOOB'),

       ((SELECT id FROM games WHERE name = 'Counter-Strike 2'),
        (SELECT id FROM gamers WHERE username = 'superbeta'),
        'INVINCIBLE'),

       ((SELECT id FROM games WHERE name = 'The Witcher 3'),
        (SELECT id FROM gamers WHERE username = 'axtonite'),
        'PRO'),

       ((SELECT id FROM games WHERE name = 'Minecraft'),
        (SELECT id FROM gamers WHERE username = 'chapo'),
        'PRO'),

       ((SELECT id FROM games WHERE name = 'Half-Life'),
        (SELECT id FROM gamers WHERE username = 'hun'),
        'INVINCIBLE'),

       ((SELECT id FROM games WHERE name = 'Doom'),
        (SELECT id FROM gamers WHERE username = 'superbeta'),
        'NOOB'),

       ((SELECT id FROM games WHERE name = 'Red Dead Redemption 2'),
        (SELECT id FROM gamers WHERE username = 'ninja'),
        'INVINCIBLE'),

       ((SELECT id FROM games WHERE name = 'Half-Life'),
        (SELECT id FROM gamers WHERE username = 'destructor'),
        'PRO'),

       ((SELECT id FROM games WHERE name = 'Counter-Strike 2'),
        (SELECT id FROM gamers WHERE username = 'essence'),
        'INVINCIBLE'),

       ((SELECT id FROM games WHERE name = 'Minecraft'),
        (SELECT id FROM gamers WHERE username = 'heaven'),
        'INVINCIBLE'),

       ((SELECT id FROM games WHERE name = 'Elden Ring'),
        (SELECT id FROM gamers WHERE username = 'kronos'),
        'NOOB'),

       ((SELECT id FROM games WHERE name = 'Red Dead Redemption 2'),
        (SELECT id FROM gamers WHERE username = 'flicks'),
        'PRO'),

       ((SELECT id FROM games WHERE name = 'The Witcher 3'),
        (SELECT id FROM gamers WHERE username = 'dark_knight'),
        'NOOB');
COMMIT;