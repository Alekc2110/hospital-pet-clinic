INSERT INTO doctors (d_id, name, position, sur_name) VALUES
(1, 'Ivan', 'dentist','Mihailov'),
(2, 'John', 'optometrist','Smith');


INSERT INTO patients (p_id, age, name, sur_name) VALUES
(1, 25, 'Michael','Jackson'),
(2, 31, 'John','Travolta');


INSERT INTO patient_doctor (p_id, d_id) VALUES
(1, 1),
(2, 1),
(2, 2);

