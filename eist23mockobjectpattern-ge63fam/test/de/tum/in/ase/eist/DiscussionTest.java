package de.tum.in.ase.eist;

import org.easymock.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyMockExtension.class)
class DiscussionTest {

    // TODO implement the tests
    @TestSubject
    private Discussion discussion = new Discussion();

    @Mock
    private Comment commentMock;

    @Mock
    private Course courseMock;

    @Test
    void testComment() {
        expect(commentMock.save()).andReturn(true);

        replay(commentMock);

        int expect = discussion.getNumberOfComments() + 1;

        discussion.addComment(commentMock);

        assertEquals(expect, discussion.getNumberOfComments());

        verify(commentMock);

    }

    @Test
    void testCommentIfSavingFails() {
        expect(commentMock.save()).andReturn(false);

        replay(commentMock);

        int expect = discussion.getNumberOfComments();

        discussion.addComment(commentMock);

        assertEquals(expect, discussion.getNumberOfComments());

    }

    @Test
    void testStartCourseDiscussion(){
        String expectTopic = "EIST";
        Person person = new Student("Yingnan", "Liu", LocalDate.now(),"WI","WI");
        expect(courseMock.isDiscussionAllowed(person)).andReturn(true);

        replay(courseMock);

        boolean result = discussion.startCourseDiscussion(courseMock,person,expectTopic);

        assertEquals(expectTopic, discussion.getTopic());
        assertEquals(true,result);
        assertEquals(expectTopic, discussion.getTopic());

    }


}
