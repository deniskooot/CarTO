
-- Здесь текущее (актуальное) состояние таблиц через CREATE TABLE

CREATE TABLE car_users (user_id SERIAL PRIMARY KEY, name VARCHAR(50));

CREATE TABLE cars (car_id SERIAL PRIMARY KEY, name VARCHAR(50), user_id INT, mileage INT, notes VARCHAR(1000, start_date TIMESTAMPTZ(0), start_mileage INT, yearly_mileage INT),
CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES car_users (user_id);

CREATE TABLE service_schedules (service_schedule_id SERIAL PRIMARY KEY, name VARCHAR(100), is_required BOOLEAN, default_period_km INT, default_period_time_days INTERVAL DAY);

CREATE TABLE car_to_service_schedules (car_to_service_schedule_id SERIAL PRIMARY KEY, service_schedule_id INT, car_id INT, periodicity_km INT, periodicity_time_days INTERVAL DAY, notes VARCHAR(200),
CONSTRAINT service_schedule_id_fk FOREIGN KEY (service_schedule_id) REFERENCES service_schedules (service_schedule_id),
CONSTRAINT car_id_fk FOREIGN KEY (car_id) REFERENCES cars (car_id);

CREATE TABLE service_operations (service_operation_id SERIAL PRIMARY KEY, car_to_service_schedule_id INT, mileage_service_operation INT, date_service_operation DATE, notes VARCHAR(1000),
CONSTRAINT car_to_service_schedule_id_fk FOREIGN KEY (car_to_service_schedule_id) REFERENCES car_to_service_schedules (car_to_service_schedule_id);

CREATE TABLE parts (part_id SERIAL PRIMARY KEY, name VARCHAR(200), car_to_service_schedule_id INT, part_number_original VARCHAR(50), part_number_analogs VARCHAR(400), notes VARCHAR(1000),
CONSTRAINT car_to_service_schedule_id_fk FOREIGN KEY (car_to_service_schedule_id) REFERENCES car_to_service_schedules (car_to_service_schedule_id);

