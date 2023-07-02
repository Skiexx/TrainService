ALTER TABLE train
    ADD CONSTRAINT uc_train_entity UNIQUE (number, year, train_type_id, company_id);