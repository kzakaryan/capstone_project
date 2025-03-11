-- Create large amount of data
INSERT INTO Course (course_id, course_name, course_credits, instructor_id, capacity)
SELECT uuid_generate_v4(), 'Course ' || i, 3, uuid_generate_v4(), 100
FROM generate_series(1, 2000000) AS s(i);

-- Create index on `course_id` to optimize lookup
CREATE INDEX idx_course_id ON Course(course_id);

-- Create index on `student_id` for faster enrollments
CREATE INDEX idx_student_id ON Enrollment(student_id);

-- Run a query and measure the execution time
EXPLAIN ANALYZE SELECT * FROM Enrollment WHERE student_id = 'some-valid-student-uuid';