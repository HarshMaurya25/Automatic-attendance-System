--------------------------------------COLLEGE---------------------------------------------------------------------------
INSERT INTO colleges (id, name, college_code) VALUES
('87cbc269-1f39-4b9e-b628-c0dbadf5f09a', 'Xavier College', 'XC001');

--------------------------------------DEPARTMENT---------------------------------------------------------------------------
INSERT INTO departments (id, name, college, hod) VALUES
('75e63750-0f44-41ac-a75c-fc5e4e732476', 'Computer Science', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a', 'Dr. Sushama Khanvilkar');

--------------------------------------COURSE---------------------------------------------------------------------------
INSERT INTO courses (id, name, course_code, college, semester, department) VALUES
('c2f456a1-9d24-4c7e-85f3-9a5f4c1e7b3d', 'Java Full Stack', '10302', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a', 3, '75e63750-0f44-41ac-a75c-fc5e4e732476');

INSERT INTO courses (id, name, course_code, college, semester, department) VALUES
('d3a7b5c2-8f91-4e7e-b1f2-6c4d5e2f0a7c', 'Algorithms', '10303', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a', 3, '75e63750-0f44-41ac-a75c-fc5e4e732476');

--------------------------------------STAFF--------------------------------------------------------------------------
-- HOD
--password : sushama
INSERT INTO users (email, authority, password) VALUES
('sushama@hod.xavier.in', 'HOD', '$2a$12$a2r.nHGl7yhW3bnV4oClU.WNAX3oL4KXUmQB3Jt3EXfosTnODSuRK');

INSERT INTO staffs (id, name, date_of_birth, gender, department, college, position, authority, phone_number, email, address, city, state) VALUES
('a1f3b6c7-d2e5-4f8a-9c1b-1234567890ab', 'Sushma Khanvilkar', '1980-05-12', 'FEMALE', '75e63750-0f44-41ac-a75c-fc5e4e732476', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a', 'HOD', 'HOD', '9876543210', 'sushama@hod.xavier.in', '123 Mahim Road', 'Mumbai', 'Maharashtra');

INSERT INTO teacher_course (teachers, courses) VALUES
('a1f3b6c7-d2e5-4f8a-9c1b-1234567890ab', 'd3a7b5c2-8f91-4e7e-b1f2-6c4d5e2f0a7c');

-- Teacher Nilambari
--password : nilambari
INSERT INTO users (email, authority, password) VALUES
('nilambari@staff.xavier.in', 'TEACHER', '$2a$12$2rZGq8lRGdMVzcZyyjpCFuISe9OEL6Q0BWcl9cQjcFaCZS6EimZXq');

INSERT INTO staffs (id, name, date_of_birth, gender, department, college, position, authority, phone_number, email, address, city, state) VALUES
('b2d4e7f8-9a1b-4c6d-8f3e-234567890abc', 'Nilambari G. Narkar', '1985-08-20', 'FEMALE', '75e63750-0f44-41ac-a75c-fc5e4e732476', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a', 'Professor', 'TEACHER', '9123456780', 'nilambari@staff.xavier.in', '456 College Lane', 'Mumbai', 'Maharashtra');

INSERT INTO teacher_course (teachers, courses) VALUES
('b2d4e7f8-9a1b-4c6d-8f3e-234567890abc', 'c2f456a1-9d24-4c7e-85f3-9a5f4c1e7b3d');

--------------------------------------DIVISION---------------------------------------------------------------------------
-- Division A
INSERT INTO divisions (id, name, department, college, class_teacher) VALUES
('d1a2b3c4-e5f6-7890-abcd-1234567890ef', 'SE - A', '75e63750-0f44-41ac-a75c-fc5e4e732476', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a', 'Nilambari G. Narkar');

-- Division B
INSERT INTO divisions (id, name, department, college, class_teacher) VALUES
('e2b3c4d5-f6a7-8901-bcde-234567890abc', 'SE - B', '75e63750-0f44-41ac-a75c-fc5e4e732476', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a', 'Sayali Mane');

-- Division Courses
INSERT INTO division_course (divisions, courses) VALUES
('d1a2b3c4-e5f6-7890-abcd-1234567890ef', 'c2f456a1-9d24-4c7e-85f3-9a5f4c1e7b3d'),
('d1a2b3c4-e5f6-7890-abcd-1234567890ef', 'd3a7b5c2-8f91-4e7e-b1f2-6c4d5e2f0a7c'),
('e2b3c4d5-f6a7-8901-bcde-234567890abc', 'c2f456a1-9d24-4c7e-85f3-9a5f4c1e7b3d');

--------------------------------------LOGIN_SESSIONS---------------------------------------------------------------------------
INSERT INTO LOGIN_SESSIONS(id, PLACE_IDENTIFIER, SESSION_TYPE, COLLEGE) VALUES
('d04ba2f9-c93c-4faa-bea4-f3a3e0b08009', 'd1a2b3c4-e5f6-7890-abcd-1234567890ef', 'STUDENT', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a');

--------------------------------------STUDENTS---------------------------------------------------------------------------
-- Harsh Maurya
--password : harsh
INSERT INTO users (email, authority, password) VALUES
('harsh.maurya@student.xavier.in', 'STUDENT', '$2a$12$Lj1WOFj8xIJCyrCO5vZJYu9U7dIpy.nTjwhBWQqqJyQLTGCHML4Ka');

INSERT INTO students (id, first_name, surname, father_name, roll_number, gender, date_of_birth, academic_year, division, departments, college, phone_number, email, address, city, State)
VALUES ('9c69357e-0f3a-4aad-8131-558dda5e8426', 'Harsh', 'Maurya', 'Ramesh Maurya', 1, 'MALE', '2005-05-25', '2', 'd1a2b3c4-e5f6-7890-abcd-1234567890ef', '75e63750-0f44-41ac-a75c-fc5e4e732476', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a', '9876543212', 'harsh.maurya@student.xavier.in', 'S-Ward , Bhandup', 'Mumbai', 'Maharashtra');

-- Harsh Narodey
--password : narodey
INSERT INTO users (email, authority, password) VALUES
('harsh.narodey@student.xavier.in', 'STUDENT', '$2a$12$zOhTKcQyfJiCs7E0vvl1Y.Nz0yQpygIQ4MOe5wdhB8STIGl5cHu1u');

INSERT INTO students (id, first_name, surname, father_name, roll_number, gender, date_of_birth, academic_year, division, departments, college, phone_number, email, address, city, State)
VALUES ('a7b3c2d1-4e5f-6789-abcd-112233445566', 'Harsh', 'Narodey', 'Sunil Narodey', 3, 'MALE', '2005-06-15', '2', 'd1a2b3c4-e5f6-7890-abcd-1234567890ef', '75e63750-0f44-41ac-a75c-fc5e4e732476', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a', '9876543213', 'harsh.narodey@student.xavier.in', 'B-Ward , Bhandup', 'Mumbai', 'Maharashtra');

-- Nikhil Bogati
--password : bogati
INSERT INTO users (email, authority, password) VALUES
('nikhil.bogati@student.xavier.in', 'STUDENT', '$2a$12$zztJgAF7CWb1eeL.MkIcGONH8um9UTyEiuxOQ2vUtxjkNTgyMdtZS');

INSERT INTO students (id, first_name, surname, father_name, roll_number, gender, date_of_birth, academic_year, division, departments, college, phone_number, email, address, city, State)
VALUES ('b8c9d0e1-2f3a-4b5c-8d6e-223344556677', 'Nikhil', 'Bogati', 'Ramesh Bogati', 1, 'MALE', '2005-07-10', '2', 'e2b3c4d5-f6a7-8901-bcde-234567890abc', '75e63750-0f44-41ac-a75c-fc5e4e732476', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a', '9876543214', 'nikhil.bogati@student.xavier.in', 'C-Ward , Bhandup', 'Mumbai', 'Maharashtra');

-- Jayamati Patel
--password : jaya
INSERT INTO users (email, authority, password) VALUES
('jayamati.patel@student.xavier.in', 'STUDENT', '$2a$12$nMUB4jjnobXqsEaOvTPY3OJ9UEGpdpsCiiwr.xOQ6nZx2a3YFX3Vq');

INSERT INTO students (id, first_name, surname, father_name, roll_number, gender, date_of_birth, academic_year, division, departments, college, phone_number, email, address, city, State)
VALUES ('c9d0e1f2-3a4b-5c6d-7e8f-334455667788', 'Jayamati', 'Patel', 'Ramesh Patel', 2, 'FEMALE', '2005-08-20', '2', 'd1a2b3c4-e5f6-7890-abcd-1234567890ef', '75e63750-0f44-41ac-a75c-fc5e4e732476', '87cbc269-1f39-4b9e-b628-c0dbadf5f09a', '9876543215', 'jayamati.patel@student.xavier.in', 'D-Ward , Bhandup', 'Mumbai', 'Maharashtra');
