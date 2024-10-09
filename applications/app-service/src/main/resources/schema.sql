CREATE TABLE manuscripts (
    id SERIAL PRIMARY KEY,
    manuscript TEXT,
    clue_found boolean
);

CREATE TABLE stats (
    id SERIAL PRIMARY KEY,
    countClueFound numeric,
    countClueNoFound numeric
);

INSERT INTO stats (id, countClueFound, countClueNoFound)
VALUES (1, 0, 0);