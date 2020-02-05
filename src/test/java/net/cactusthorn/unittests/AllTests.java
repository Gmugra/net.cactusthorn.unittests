package net.cactusthorn.unittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ SimpleSpyWithRuleForInitTest.class, SomethingToDoCaptureTest.class,
        SomethingToDoLogsWithBeforeInitTest.class, SomethingToDoMockitoAnnotationsTest.class,
        SomethingToDoSimpleTest.class })
public class AllTests {

}
