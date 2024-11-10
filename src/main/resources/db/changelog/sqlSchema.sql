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

--ALTER TABLE service_shedule RENAME to service_schedule;
--rollback ALTER TABLE service_schedule RENAME to service_shedule;