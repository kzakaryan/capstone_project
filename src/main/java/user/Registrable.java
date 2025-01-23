package user;

import course.Course;

public interface Registrable {
    void register(Course course);

    void withdraw(Course course);
}
