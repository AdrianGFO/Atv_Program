ALTER TABLE address
    ADD CONSTRAINT fk_user_create_id
        FOREIGN KEY (user_create_id)
            REFERENCES user_create(id)
            ON DELETE CASCADE;