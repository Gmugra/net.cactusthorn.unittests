package net.cactusthorn.unittests;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

import java.util.*;

public class SimpleSpyWithRuleForInitTest {

    // alternative to @RunWith(MockitoJUnitRunner.class)
    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 

    @Spy
    List<String> spyList = new ArrayList<String>();

    @Test
    public void testSpy() {
        spyList.add("A");
        spyList.add("B");

        verify(spyList, times(2)).add(anyString());
        assertEquals(2, spyList.size());
    }

}
