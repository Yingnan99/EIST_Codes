package de.tum.in.ase.eist;

import org.easymock.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.Period;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(EasyMockExtension.class)
class DiscussionTest {
    /* 总的来说，verify(courseMock)确保你在mock对象上设置的所有预期方法调用都已经执行，而断言则用来检查方法的结果是否符合预期。
    在这个例子中，将verify(courseMock)放在断言之前有助于先确认预期的方法调用是否已经执行，然后再检查结果。这有助于在断言失败时，
    提供更详细的信息，因为你已经知道预期的方法调用都已经执行。
    */

    // TODO implement the tests

    @TestSubject
    private Discussion discussion = new Discussion();
    @Mock
    private Course courseMock;
    @Mock
    private Comment commentMock;


    @Test
    void testComment() {
        //设置期望的行为和结果
        expect(commentMock.save()).andReturn(true);
        int expectedNumberOfComments = discussion.getNumberOfComments() + 1;

        //已经完成了模拟对象的设置阶段，并且准备开始执行测试
        replay(commentMock);

        //调用方法
        discussion.addComment(commentMock);

        //验证结果是否符合预期
        assertEquals(expectedNumberOfComments, discussion.getNumberOfComments());

        //验证模拟对象的行为
        verify(commentMock);

    }

    @Test
    void testCommentIfSavingFails() {
        //设置期望的行为和结果
        expect(commentMock.save()).andReturn(false);
        int expectedNumberOfComments = discussion.getNumberOfComments();

        //已经完成了模拟对象的设置阶段，并且准备开始执行测试
        replay(commentMock);

        //测试addComment()方法是否按指定工作
        discussion.addComment(commentMock);

        //验证结果是否符合预期
        assertEquals(expectedNumberOfComments, discussion.getNumberOfComments());

        //验证模拟对象的行为
        verify(commentMock);


    }

    @Test
    void testStartCourseDiscussion() {
        //设置期望的行为和结果
        Student student = new Student("Alex", "Lee", LocalDate.now(), "Info", "WI");
        String topic = "EIST";
        expect(courseMock.isDiscussionAllowed(student)).andReturn(true);

        //已经完成了模拟对象的设置阶段，并且准备开始执行测试
        replay(courseMock);

        //测试startCourseDiscussion()方法是否按指定工作
        boolean result = discussion.startCourseDiscussion(courseMock, student, topic);

        //验证结果是否符合预期即，是否正确设置了讨论的课程和主题
        assertEquals(true, result);
        assertEquals(courseMock, discussion.getCourse());
        assertEquals(topic, discussion.getTopic());

        //验证模拟对象的行为
        verify(courseMock);

    }

}
