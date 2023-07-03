ALTER TABLE train_station_visit
    ALTER COLUMN leave_timestamp DROP NOT NULL;

ALTER TABLE route
    ADD final_station_id BIGINT;

ALTER TABLE route
    ALTER COLUMN final_station_id SET NOT NULL;

ALTER TABLE route
    ADD CONSTRAINT FK_ROUTE_ON_FINAL_STATION FOREIGN KEY (final_station_id) REFERENCES station (id);

ALTER TABLE route
    DROP CONSTRAINT fk_route_on_final_route;

ALTER TABLE route
    DROP COLUMN final_route_id;