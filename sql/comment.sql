CREATE TABLE comment (
                         author_id   BIGINT,
                         created_at  DATETIME(6),
                         id          BIGINT       NOT NULL AUTO_INCREMENT,
                         modified_at DATETIME(6),
                         schedule_id BIGINT,
                         contents    VARCHAR(255) NOT NULL,
                         PRIMARY KEY (id),
                             FOREIGN KEY (author_id)
                                 REFERENCES author (id),
                             FOREIGN KEY (schedule_id)
                                 REFERENCES schedule (id)
)