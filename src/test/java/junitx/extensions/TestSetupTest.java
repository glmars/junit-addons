package junitx.extensions;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;

/**
 * @version $Revision: 1.1 $ $Date: 2002/09/22 23:25:34 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class TestSetupTest
        extends TestCase {

    public TestSetupTest(String name) {
        super(name);
    }

    public void testFailSetUp() {
        TestCase testcase = new TestCase("testDummy") {
            public void testDummy() {
                System.out.println("h");
            }
        };

        MockSetUp wrapper = new MockSetUp(testcase);
        TestResult result = new TestResult();

        wrapper.run(result);
        assertTrue(wrapper.tearDownExecuted);
    }

    public void testFailRun() {
        TestCase testcase = new TestCase("testDummy") {
            public void testDummy() {
                throw new NullPointerException();
            }
        };

        MockSetUp wrapper = new MockSetUp(testcase);
        TestResult result = new TestResult();

        wrapper.run(result);
        assertTrue(wrapper.tearDownExecuted);
    }

    public static class MockSetUp
            extends TestSetup {
        public boolean tearDownExecuted = false;

        public MockSetUp(Test test) {
            super(test);
        }

        protected void setUp()
                throws Exception {
            throw new NullPointerException();
        }

        protected void tearDown()
                throws Exception {
            tearDownExecuted = true;
        }
    }

}
