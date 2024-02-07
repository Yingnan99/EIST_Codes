package de.tum.in.ase.eist;

import org.junit.jupiter.api.*;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class OnlineCourseTest {

    // TODO 3: Test setOnlineCourseUrl()
    @Test
    void testSetOnlineCourseUrlWithValidUrl() throws MalformedURLException {
        OnlineCourse onlineCourse = new OnlineCourse("EIST");
        onlineCourse.setUrl("https://artemis.ase.in.tum.de/courses/241/exercises/9427");

        assertEquals(onlineCourse.getUrl().toString(), "https://artemis.ase.in.tum.de/courses/241/exercises/9427");
    }

    @Test
    void testSetOnlineCourseUrlWithInvalidUrl() throws MalformedURLException {
        OnlineCourse onlineCourse = new OnlineCourse("EIST");

        assertThrows(MalformedURLException.class, () -> onlineCourse.setUrl("invalid url"));

    }

}
