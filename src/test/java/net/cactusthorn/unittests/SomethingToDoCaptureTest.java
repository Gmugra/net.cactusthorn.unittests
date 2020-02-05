package net.cactusthorn.unittests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class SomethingToDoCaptureTest {

    @Mock
    HttpServletRequest request;

    @InjectMocks
    SomethingToDo std;

    @Captor
    ArgumentCaptor<String> stringCaptor;

    @Test
    public void testStringCaptor() {

        std.doIt();

        verify(request).getAttribute(stringCaptor.capture());
        assertEquals("XYZ", stringCaptor.getValue());
    }
}
