CREATE TABLE author (
                        created_at  DATETIME(6),
                        id          BIGINT       NOT NULL AUTO_INCREMENT,
                        modified_at DATETIME(6),
                        email       VARCHAR(255) NOT NULL UNIQUE,
                        name        VARCHAR(255) NOT NULL,
                        password    VARCHAR(255) NOT NULL,
                        PRIMARY KEY (id)
)