package net.cactusthorn.unittests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class SomethingToDoMockitoAnnotationsTest {

    @Mock
    HttpServletRequest request;

    @InjectMocks
    SomethingToDo std;

    @Before
    public void setUp() {
        when(request.getAttribute("XYZ")).thenReturn("TEST");
    }

    @Test
    public void testWhen() {
        assertEquals("TEST", std.doIt(request));
    }

    @Test
    public void testVerify() {
        std.doIt(request);
        verify(request).getAttribute("XYZ");
    }
}
