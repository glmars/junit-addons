package junitx.framework;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * This class is copy-pasted from <tt>junit.framework.TestSuite</tt>.  This was
 * only done to have access to the <tt>fTests</tt> field in order to sort it.
 */
class TestSuite implements Test {

    protected List fTests = new Vector(10);
    private String fName;

    /**
     * Constructs an empty TestSuite.
     */
    public TestSuite() {
    }

    /**
     * Constructs a TestSuite from the given class. Adds all the methods
     * starting with "test" as test cases to the suite.
     * Parts of this method was written at 2337 meters in the Hüffihütte,
     * Kanton Uri
     */
    public TestSuite(final Class theClass) {
        fName = theClass.getName();
        Constructor constructor = null;
        try {
            constructor = getConstructor(theClass);
        } catch (NoSuchMethodException e) {
            addTest(warning("Class " + theClass.getName() + " has no public constructor TestCase(String name)"));
            return;
        }

        if (!Modifier.isPublic(theClass.getModifiers())) {
            addTest(warning("Class " + theClass.getName() + " is not public"));
            return;
        }

        Class superClass = theClass;
        Vector names = new Vector();
        while (Test.class.isAssignableFrom(superClass)) {
            Method[] methods = superClass.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                addTestMethod(methods[i], names, constructor);
            }
            superClass = superClass.getSuperclass();
        }
        if (fTests.size() == 0) {
            addTest(warning("No tests found in " + theClass.getName()));
        }
    }

    /**
     * Constructs an empty TestSuite.
     */
    public TestSuite(String name) {
        fName = name;
    }

    /**
     * Adds a test to the suite.
     */
    public void addTest(Test test) {
        fTests.add(test);
    }

    /**
     * Adds the tests from the given class to the suite
     */
    public void addTestSuite(Class testClass) {
        addTest(new TestSuite(testClass));
    }

    private void addTestMethod(Method m, List names, Constructor constructor) {
        String name = m.getName();
        if (names.contains(name)) {
            return;
        }

        if (isPublicTestMethod(m)) {
            names.add(name);

            Object[] args = new Object[]{name};
            try {
                addTest((Test) constructor.newInstance(args));
            } catch (InstantiationException e) {
                addTest(warning("Cannot instantiate test case: " + name + " (" + exceptionToString(e) + ")"));
            } catch (InvocationTargetException e) {
                addTest(warning("Exception in constructor: " + name + " (" + exceptionToString(e.getTargetException()) + ")"));
            } catch (IllegalAccessException e) {
                addTest(warning("Cannot access test case: " + name + " (" + exceptionToString(e) + ")"));
            }

        } else { // almost a test method
            if (isTestMethod(m)) {
                addTest(warning("Test method isn't public: " + m.getName()));
            }
        }
    }

    /**
     * Converts the stack trace into a string
     */
    private String exceptionToString(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        return stringWriter.toString();

    }

    /**
     * Counts the number of test cases that will be run by this test.
     */
    public int countTestCases() {
        int count = 0;
        for (Enumeration e = tests(); e.hasMoreElements();) {
            Test test = (Test) e.nextElement();
            count = count + test.countTestCases();
        }
        return count;
    }

    /**
     * Gets a constructor which takes a single String as
     * its argument.
     */
    private Constructor getConstructor(Class theClass) throws NoSuchMethodException {
        Class[] args = {String.class};
        return theClass.getConstructor(args);
    }

    /**
     */
    private boolean isPublicTestMethod(Method m) {
        return isTestMethod(m) && Modifier.isPublic(m.getModifiers());
    }

    /**
     */
    private boolean isTestMethod(Method m) {
        String name = m.getName();
        Class[] parameters = m.getParameterTypes();
        Class returnType = m.getReturnType();
        return parameters.length == 0 && name.startsWith("test") && returnType.equals(Void.TYPE);
    }

    /**
     * Runs the tests and collects their result in a TestResult.
     */
    public void run(TestResult result) {
        for (Enumeration e = tests(); e.hasMoreElements();) {
            if (result.shouldStop()) {
                break;
            }
            Test test = (Test) e.nextElement();
            runTest(test, result);
        }
    }

    public void runTest(Test test, TestResult result) {
        test.run(result);
    }

    /**
     * Returns the test at the given index
     */
    public Test testAt(int index) {
        return (Test) fTests.get(index);
    }

    /**
     * Returns the number of tests in this suite
     */
    public int testCount() {
        return fTests.size();
    }

    /**
     * Returns the tests as an enumeration
     */
    public Enumeration tests() {
        // todo fix this
        return new Vector(fTests).elements();
    }

    /**
     */
    public String toString() {
        if (getName() != null) {
            return getName();
        }
        return super.toString();
    }

    /**
     * Sets the name of the suite.
     * @param name The name to set
     */
    public void setName(String name) {
        fName = name;
    }

    /**
     * Returns the name of the suite. Not all
     * test suites have a name and this method
     * can return null.
     */
    public String getName() {
        return fName;
    }

    /**
     * Returns a test which will fail and log a warning message.
     */
    private Test warning(final String message) {
        return new TestCase("warning") {
            protected void runTest() {
                fail(message);
            }
        };
    }

}

