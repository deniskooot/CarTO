--liquibase formatted sql
--changeset TestUsers_sql:1

CREATE TABLE car_user (user_id SERIAL PRIMARY KEY, name VARCHAR(50));

CREATE TABLE car (car_id SERIAL PRIMARY KEY, name VARCHAR(50), user_id INT, mileage INT, notes VARCHAR(1000), FOREIGN KEY (user_id) REFERENCES  car_user(user_id));

ALTER TABLE car_user RENAME TO car_users;
ALTER TABLE car RENAME TO cars;

--(Внешний ключ корректировка?)

CREATE TABLE service_shedule (service_shedule_id SERIAL PRIMARY KEY, name VARCHAR(100), is_required BOOLEAN, default_period_km INT, default_period_time_days INTERVAL DAY);

CREATE TABLE car_to_service_schedule (car_to_service_schedule_id SERIAL PRIMARY KEY, service_shedule_id INT, car_id INT, periodicity_km INT, periodicity_time_days INTERVAL DAY, notes VARCHAR(200),FOREIGN KEY (service_shedule_id) REFERENCES service_shedule (service_shedule_id), FOREIGN KEY (car_id) REFERENCES cars (car_id));

CREATE TABLE service_operations (service_operation_id SERIAL PRIMARY KEY, car_to_service_schedule_id INT, mileage_service_operation INT, date_service_operation DATE, notes VARCHAR(1000), FOREIGN KEY (car_to_service_schedule_id) REFERENCES car_to_service_schedule (car_to_service_schedule_id));

CREATE TABLE parts (part_id SERIAL PRIMARY KEY, name VARCHAR(200), car_to_service_shedule_id INT, part_number_original VARCHAR(50), part_number_analogs VARCHAR(400), notes VARCHAR(1000), FOREIGN KEY (car_to_service_shedule_id) REFERENCES  car_to_service_schedule (car_to_service_schedule_id));


--rollback DROP TABLE car_user;
--rollback DROP TABLE car;
--rollback DROP TABLE service_shedule;
--rollback DROP TABLE car_to_service_shedule;
--rollback DROP TABLE service_operations;
--rollback DROP TABLE parts;

--changeset TestUsers_sql:2

ALTER TABLE service_shedule RENAME to service_schedule;
ALTER TABLE service_schedule RENAME COLUMN service_shedule_id TO service_schedule_id;
ALTER TABLE parts RENAME COLUMN car_to_service_shedule_id TO car_to_service_schedule_id;



--rollback ALTER TABLE parts RENAME COLUMN car_to_service_schedule_id TO car_to_service_shedule_id;
--rollback ALTER TABLE service_schedule RENAME COLUMN service_schedule_id TO service_shedule_id;
--rollback ALTER TABLE service_schedule RENAME to service_shedule;

--changeset TestUsers_sql:3 context:"change table names"

ALTER TABLE service_schedule RENAME TO service_schedules;
ALTER TABLE car_to_service_schedule RENAME TO car_to_service_schedules;

--rollback ALTER TABLE service_schedules RENAME TO service_schedule;
--rollback ALTER TABLE car_to_service_schedules RENAME TO car_to_service_schedule;


--changeset TestUsers_sql:4 context:"Rename shedule and change wrong Foreign key which didn't renamed while rename columns"

ALTER TABLE car_to_service_schedules RENAME COLUMN service_shedule_id TO service_schedule_id;
ALTER TABLE car_to_service_schedules DROP CONSTRAINT car_to_service_schedule_service_shedule_id_fkey;
ALTER TABLE car_to_service_schedules ADD CONSTRAINT service_schedule_id_fk FOREIGN KEY (service_schedule_id) REFERENCES service_schedules (service_schedule_id);

ALTER TABLE parts DROP CONSTRAINT parts_car_to_service_shedule_id_fkey;
ALTER TABLE parts ADD CONSTRAINT car_to_service_schedule_id_fk FOREIGN KEY (car_to_service_schedule_id) REFERENCES car_to_service_schedules (car_to_service_schedule_id);

ALTER TABLE service_operations DROP CONSTRAINT service_operations_car_to_service_schedule_id_fkey;
ALTER TABLE service_operations ADD CONSTRAINT car_to_service_schedule_id_fk FOREIGN KEY (car_to_service_schedule_id) REFERENCES car_to_service_schedules (car_to_service_schedule_id);

--rollback ALTER TABLE car_to_service_schedules RENAME COLUMN service_schedule_id TO service_shedule_id;
--rollback ALTER TABLE car_to_service_schedules DROP CONSTRAINT car_to_service_schedule_service_shedule_id_fkey;
--rollback ALTER TABLE car_to_service_schedules ADD FOREIGN KEY (service_shedule_id) REFERENCES service_shedule (service_shedule_id);

--rollback ALTER TABLE parts DROP CONSTRAINT car_to_service_schedule_id_fk;
--rollback ALTER TABLE parts ADD FOREIGN KEY (car_to_service_shedule_id) REFERENCES car_to_service_schedule (car_to_service_schedule_id);

--rollback ALTER TABLE service_operations DROP CONSTRAINT car_to_service_schedule_id_fk;
--rollback ALTER TABLE service_operations ADD FOREIGN KEY (car_to_service_schedule_id) REFERENCES car_to_service_schedule (car_to_service_schedule_id));


--changeset TestUsers_sql:5 context:"Rename car_to_service_schedules, cars Foreign key for custom names"

ALTER TABLE car_to_service_schedules DROP CONSTRAINT car_to_service_schedule_car_id_fkey;
ALTER TABLE car_to_service_schedules ADD CONSTRAINT car_id_fk FOREIGN KEY (car_id) REFERENCES cars (car_id);

ALTER TABLE cars DROP CONSTRAINT car_user_id_fkey;
ALTER TABLE cars ADD CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES car_users (user_id);

--rollback ALTER TABLE cars DROP CONSTRAINT user_id_fk;
--rollback ALTER TABLE cars ADD FOREIGN KEY (user_id) REFERENCES car_users (user_id);

--rollback ALTER TABLE car_to_service_schedules DROP CONSTRAINT car_id_fk;
--rollback ALTER TABLE car_to_service_schedules ADD FOREIGN KEY (car_id) REFERENCES cars (car_id);
