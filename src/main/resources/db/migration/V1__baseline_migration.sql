CREATE TABLE company
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title       VARCHAR(50),
    small_title VARCHAR(10),
    CONSTRAINT pk_company PRIMARY KEY (id)
);

CREATE TABLE train
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    number        VARCHAR(5),
    year          INTEGER,
    train_type_id BIGINT,
    company_id    BIGINT,
    CONSTRAINT pk_train PRIMARY KEY (id)
);

CREATE TABLE train_type
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title VARCHAR(30),
    CONSTRAINT pk_train_type PRIMARY KEY (id)
);

ALTER TABLE train
    ADD CONSTRAINT FK_TRAIN_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (id);

ALTER TABLE train
    ADD CONSTRAINT FK_TRAIN_ON_TRAIN_TYPE FOREIGN KEY (train_type_id) REFERENCES train_type (id);