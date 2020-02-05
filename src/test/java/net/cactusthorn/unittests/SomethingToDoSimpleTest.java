package net.cactusthorn.unittests;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

public class SomethingToDoSimpleTest {

    @Test
    public void testWhen() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute("XYZ")).thenReturn("TEST");

        SomethingToDo std = new SomethingToDo(null);
        assertEquals("TEST", std.doIt(request));
    }

    @Test
    public void testVerify() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getAttribute("XYZ")).thenReturn("TEST");

        SomethingToDo std = new SomethingToDo(null);
        std.doIt(request);

        verify(request).getAttribute("XYZ");
    }

}
