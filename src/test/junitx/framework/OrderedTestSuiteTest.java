package junitx.framework;

import junit.framework.TestCase;

public class OrderedTestSuiteTest
        extends TestCase {

    public OrderedTestSuiteTest(String name) {
        super(name);
    }

    public void testClassConstructor1() {
        OrderedTestSuite suite = new OrderedTestSuite(ClassDefinition1.class);
        assertEquals(3, suite.countTestCases());
        assertEquals("testA(junitx.framework.OrderedTestSuiteTest$ClassDefinition1)", suite.testAt(0).toString());
        assertEquals("testB(junitx.framework.OrderedTestSuiteTest$ClassDefinition1)", suite.testAt(1).toString());
        assertEquals("testC(junitx.framework.OrderedTestSuiteTest$ClassDefinition1)", suite.testAt(2).toString());
    }

    public void testClassConstructor2() {
        OrderedTestSuite suite = new OrderedTestSuite(ClassDefinition2.class);
        assertEquals(3, suite.countTestCases());
        assertEquals("testA(junitx.framework.OrderedTestSuiteTest$ClassDefinition2)", suite.testAt(0).toString());
        assertEquals("testB(junitx.framework.OrderedTestSuiteTest$ClassDefinition2)", suite.testAt(1).toString());
        assertEquals("testC(junitx.framework.OrderedTestSuiteTest$ClassDefinition2)", suite.testAt(2).toString());
    }

    public void testClassConstructor3() {
        OrderedTestSuite suite = new OrderedTestSuite(ClassDefinition3.class);
        assertEquals(3, suite.countTestCases());
        assertEquals("testA(junitx.framework.OrderedTestSuiteTest$ClassDefinition3)", suite.testAt(0).toString());
        assertEquals("testB(junitx.framework.OrderedTestSuiteTest$ClassDefinition3)", suite.testAt(1).toString());
        assertEquals("testC(junitx.framework.OrderedTestSuiteTest$ClassDefinition3)", suite.testAt(2).toString());
    }

    public static class ClassDefinition1
            extends TestCase {

        public ClassDefinition1(String name) {
            super(name);
        }

        public void testC() {
        }

        public void testA() {
        }

        public void testB() {
        }
    }

    public static class ClassDefinition2
            extends TestCase {

        public ClassDefinition2(String name) {
            super(name);
        }

        public void testC() {
        }

        public void testB() {
        }

        public void testA() {
        }
    }

    public static class ClassDefinition3
            extends TestCase {

        public ClassDefinition3(String name) {
            super(name);
        }

        public void testB() {
        }

        public void testC() {
        }

        public void testA() {
        }

    }

}
