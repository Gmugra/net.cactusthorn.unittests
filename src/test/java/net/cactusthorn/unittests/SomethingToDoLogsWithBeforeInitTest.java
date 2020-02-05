package net.cactusthorn.unittests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.read.ListAppender;

public class SomethingToDoLogsWithBeforeInitTest {

    @Mock
    Appender<ILoggingEvent> logAppender;

    ListAppender<ILoggingEvent> logListAppender = new ListAppender<>();

    @Mock
    HttpServletRequest request;

    @InjectMocks
    SomethingToDo std;

    @Before
    public void setUp() {

        // alternative to
        // @RunWith(MockitoJUnitRunner.class)
        // OR to
        // @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
        MockitoAnnotations.initMocks(this);

        Logger logger = (Logger) LoggerFactory.getLogger(std.getClass());
        logger.addAppender(logAppender);

        logListAppender.start();
        logger.addAppender(logListAppender);

        when(request.getAttribute("XYZ")).thenReturn("TEST");
    }

    @Captor
    ArgumentCaptor<ILoggingEvent> loggingEventCaptor;

    @Test
    public void testLogAppender() {

        std.doIt();

        verify(logAppender).doAppend(loggingEventCaptor.capture());

        ILoggingEvent value = loggingEventCaptor.getValue();
        assertEquals(Level.INFO, value.getLevel());
        assertEquals("Test logging: TEST", value.getFormattedMessage());
    }

    @Test
    public void testLogListAppender() {

        std.doItMultiLog();

        List<ILoggingEvent> logEvents = logListAppender.list;

        assertEquals(Level.INFO, logEvents.get(0).getLevel());
        assertEquals("Test logging: TEST", logEvents.get(0).getFormattedMessage());

        assertEquals(Level.ERROR, logEvents.get(1).getLevel());
        assertEquals("TEST ERROR logging", logEvents.get(1).getFormattedMessage());
    }
}
