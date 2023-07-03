CREATE TABLE route
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    train_id         BIGINT                                  NOT NULL,
    start_station_id BIGINT                                  NOT NULL,
    final_route_id   BIGINT                                  NOT NULL,
    start_timestamp  TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    end_timestamp    TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    CONSTRAINT pk_route PRIMARY KEY (id)
);

CREATE TABLE station
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    city      VARCHAR(50)                             NOT NULL,
    title     VARCHAR(50)                             NOT NULL,
    time_zone VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_station PRIMARY KEY (id)
);

CREATE TABLE train_station_visit
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    route_id        BIGINT                                  NOT NULL,
    station_id      BIGINT                                  NOT NULL,
    visit_timestamp TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    leave_timestamp TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    CONSTRAINT pk_train_station_visit PRIMARY KEY (id)
);

ALTER TABLE train
    ADD circulation_stopped TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE route
    ADD CONSTRAINT FK_ROUTE_ON_FINAL_ROUTE FOREIGN KEY (final_route_id) REFERENCES station (id);

ALTER TABLE route
    ADD CONSTRAINT FK_ROUTE_ON_START_STATION FOREIGN KEY (start_station_id) REFERENCES station (id);

ALTER TABLE route
    ADD CONSTRAINT FK_ROUTE_ON_TRAIN FOREIGN KEY (train_id) REFERENCES train (id);

ALTER TABLE train_station_visit
    ADD CONSTRAINT FK_TRAIN_STATION_VISIT_ON_ROUTE FOREIGN KEY (route_id) REFERENCES route (id);

ALTER TABLE train_station_visit
    ADD CONSTRAINT FK_TRAIN_STATION_VISIT_ON_STATION FOREIGN KEY (station_id) REFERENCES station (id);

ALTER TABLE train
    ALTER COLUMN company_id SET NOT NULL;

ALTER TABLE train
    ALTER COLUMN number SET NOT NULL;

ALTER TABLE company
    ALTER COLUMN small_title SET NOT NULL;

ALTER TABLE company
    ALTER COLUMN title SET NOT NULL;

ALTER TABLE train_type
    ALTER COLUMN title SET NOT NULL;

ALTER TABLE train
    ALTER COLUMN train_type_id SET NOT NULL;

ALTER TABLE train
    ALTER COLUMN year SET NOT NULL;