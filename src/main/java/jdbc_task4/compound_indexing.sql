-- Create a compound index on both student_id and course_id
CREATE INDEX idx_student_course ON Enrollment(student_id, course_id);

-- Run a query using both columns in the WHERE clause (this should be optimized by the compound index)
EXPLAIN ANALYZE SELECT * FROM Enrollment WHERE student_id = 'some-student-id' AND course_id = 'some-course-id';

-- Run a query using only one column (course_id), which won't fully benefit from the compound index
EXPLAIN ANALYZE SELECT * FROM Enrollment WHERE course_id = 'some-course-id';
