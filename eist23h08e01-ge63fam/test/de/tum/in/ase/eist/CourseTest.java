package de.tum.in.ase.eist;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {


    // TODO 1: Test getCourseTitle()
    @Test
    void testGetCourseTitle() {
        Course expected = new Course("EIST");
        assertEquals(expected.getTitle(), "EIST");
    }

    // TODO 2: Test getNumberOfAttendees()

    @Test
    void testNoAttendees() {
        Course expected = new Course("EIST");
        assertEquals(expected.getNumberOfAttendees(), 0);


    }

    @Test
    void testThreeAttendees() {
        Course expected = new Course("EIST");

        Student student1 = new Student("Alex", "Brown", "03041996", "EI", "AI");
        Student student2 = new Student("Alexandra", "Brown", "03051996", "EI", "AI");
        Student student3 = new Student("Agnes", "Brown", "03061996", "EI", "AI");

        expected.addAttendee(student1);
        expected.addAttendee(student2);
        expected.addAttendee(student3);

        assertEquals(expected.getNumberOfAttendees(), 3);
    }

}
