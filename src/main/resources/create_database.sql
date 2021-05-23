DROP DATABASE IF EXISTS imnotdb;
CREATE DATABASE imnotdb;
USE imnotdb;

-- TODO: decide the len of each varchar

CREATE TABLE title_akas(
    tconst VARCHAR(20),
    ordering INTEGER,
    title TEXT,
    region VARCHAR(200),
    language VARCHAR(200),
    types VARCHAR(200),
    attributes VARCHAR(200),
    isOriginalTitle BOOLEAN,
    FULLTEXT (title),
    INDEX akasIndexByTconst (tconst)
);

CREATE TABLE title_basics(
    tconst VARCHAR(20) NOT NULL ,
    titleType VARCHAR(50),
    primaryTitle VARCHAR(200),
    originalTitle VARCHAR(200),
    isAdult BOOLEAN,
    startYear YEAR,
    endYear YEAR,
    runtimeMinutes INTEGER,
    genres TEXT,
    primary key (tconst),
    FULLTEXT (genres)
);
CREATE TABLE title_crew(
    tconst VARCHAR(20) NOT NULL ,
    directors VARCHAR (200),
    writers VARCHAR(200),
    primary key(tconst)
);

CREATE TABLE title_principals(
    tconst VARCHAR(20) NOT NULL ,
    nconst VARCHAR(200) NOT NULL ,
    ordering INTEGER,
    category VARCHAR (200),
    job VARCHAR (200),
    characters VARCHAR(200),
    primary key (tconst,nconst)
);

CREATE TABLE ratings(
    tconst VARCHAR(20) NOT NULL ,
    averageRating FLOAT,
    numVotes BIGINT,
    primary key (tconst)
);

CREATE TABLE name(
    nconst VARCHAR(20) NOT NULL ,
    primaryName TEXT,
    birthYear YEAR,
    deathYear YEAR,
    primaryProfession VARCHAR(200),
    knownForTitles VARCHAR (100),
    primary key(nconst),
    FULLTEXT (primaryName)
);