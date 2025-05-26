CREATE TABLE schedule (
                          author_id   BIGINT,
                          created_at  DATETIME(6),
                          id          BIGINT       NOT NULL AUTO_INCREMENT,
                          modified_at DATETIME(6),
                          contents    LONGTEXT,
                          title       VARCHAR(255) NOT NULL,
                          PRIMARY KEY (id),
                              FOREIGN KEY (author_id)
                                  REFERENCES author (id)
)