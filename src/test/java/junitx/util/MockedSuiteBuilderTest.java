package junitx.util;

import junit.framework.TestCase;
import junitx.framework.Assert;

/**
 * @version $Revision: 1.4 $ $Date: 2003/05/22 02:24:15 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class MockedSuiteBuilderTest extends TestCase {

    public MockedSuiteBuilderTest(String name) {
        super(name);
    }

    public void testIsGifTestClass() {
        MockedSuiteBuilder builder = new MockedSuiteBuilder();

        Assert.assertFalse(builder.isTestClass("logo.gif"));
        Assert.assertFalse(builder.isTestClass("junit/awtui/logo.gif"));
    }

    public void testIsSubclassTestClass() {
        MockedSuiteBuilder builder = new MockedSuiteBuilder();

        Assert.assertFalse(builder.isTestClass("TestRunner$1.class"));
        Assert.assertFalse(builder.isTestClass("junit/awtui/TestRunner$1.class"));
    }

    public void testIsTestClass() {
        MockedSuiteBuilder builder = new MockedSuiteBuilder();

        assertTrue(builder.isTestClass("SuiteTest.class"));
        assertTrue(builder.isTestClass("junit/tests/framework/SuiteTest.class"));
    }

    public void testIsTestClassPrefix() {
        MockedSuiteBuilder builder = new MockedSuiteBuilder();
        TestFilter filter = new SimpleTestFilter() {
            public boolean include(String classpath) {
                return getClassName(classpath).startsWith("Test");
            }
        };
        builder.setFilter(filter);

        assertTrue(builder.isTestClass("TestSuite.class"));
        assertTrue(builder.isTestClass("junit/tests/framework/TestSuite.class"));

        Assert.assertFalse(builder.isTestClass("TestRunner$1.class"));
        Assert.assertFalse(builder.isTestClass("junit/awtui/TestRunner$1.class"));
    }

    public void testIsTestClassPrefix_BasicTestFilter() {
        MockedSuiteBuilder builder = new MockedSuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return getClassName(classpath).startsWith("Test");
            }
        };
        builder.setFilter(filter);

        assertTrue(builder.isTestClass("TestSuite.class"));
        assertTrue(builder.isTestClass("junit/tests/framework/TestSuite.class"));

        Assert.assertFalse(builder.isTestClass("TestRunner$1.class"));
        Assert.assertFalse(builder.isTestClass("junit/awtui/TestRunner$1.class"));
    }

    public void testIsTestClassNullPrefix() {
        MockedSuiteBuilder builder = new MockedSuiteBuilder();

        assertTrue(builder.isTestClass("SuiteTest.class"));
        assertTrue(builder.isTestClass("junit/tests/framework/SuiteTest.class"));

        Assert.assertFalse(builder.isTestClass("TestRunner$1.class"));
        Assert.assertFalse(builder.isTestClass("junit/awtui/TestRunner$1.class"));
    }

    public void testIsTestClassSuffix() {
        MockedSuiteBuilder builder = new MockedSuiteBuilder();
        TestFilter filter = new SimpleTestFilter() {
            public boolean include(String classpath) {
                return getClassName(classpath).endsWith("Tests");
            }
        };
        builder.setFilter(filter);

        assertTrue(builder.isTestClass("AllTests.class"));
        assertTrue(builder.isTestClass("junit/tests/framework/AllTests.class"));

        Assert.assertFalse(builder.isTestClass("AllTests$1.class"));
        Assert.assertFalse(builder.isTestClass("junit/awtui/AllTests$1.class"));
    }

    public void testIsTestClassSuffix_BasicTestFilter() {
        MockedSuiteBuilder builder = new MockedSuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return getClassName(classpath).endsWith("Tests");
            }
        };
        builder.setFilter(filter);

        assertTrue(builder.isTestClass("AllTests.class"));
        assertTrue(builder.isTestClass("junit/tests/framework/AllTests.class"));

        Assert.assertFalse(builder.isTestClass("AllTests$1.class"));
        Assert.assertFalse(builder.isTestClass("junit/awtui/AllTests$1.class"));
    }

    public void testIsTestClassNullSuffix() {
        MockedSuiteBuilder builder = new MockedSuiteBuilder();

        assertTrue(builder.isTestClass("SuiteTest.class"));
        assertTrue(builder.isTestClass("junit/tests/framework/SuiteTest.class"));

        Assert.assertFalse(builder.isTestClass("TestRunner$1.class"));
        Assert.assertFalse(builder.isTestClass("junit/awtui/TestRunner$1.class"));
    }

    public void testIsTestClassPackage() {
        MockedSuiteBuilder builder = new MockedSuiteBuilder();
        TestFilter filter = new SimpleTestFilter() {
            public boolean include(String classpath) {
                return getPackageName(classpath).startsWith("junit.tests.framework");
            }
        };
        builder.setFilter(filter);

        Assert.assertFalse(builder.isTestClass("SuiteTest.class"));
        Assert.assertFalse(builder.isTestClass("junit/tests/SuiteTest.class"));
        assertTrue(builder.isTestClass("junit/tests/framework/SuiteTest.class"));

        Assert.assertFalse(builder.isTestClass("SuiteTest$1.class"));
        Assert.assertFalse(builder.isTestClass("junit/tests/framework/SuiteTest$1.class"));
    }

    public void testIsTestClassPackage_BasicTestFilter() {
        MockedSuiteBuilder builder = new MockedSuiteBuilder();
        TestFilter filter = new BasicTestFilter() {
            public boolean include(String classpath) {
                return getPackageName(classpath).startsWith("junit.tests.framework");
            }
        };
        builder.setFilter(filter);

        Assert.assertFalse(builder.isTestClass("SuiteTest.class"));
        Assert.assertFalse(builder.isTestClass("junit/tests/SuiteTest.class"));
        assertTrue(builder.isTestClass("junit/tests/framework/SuiteTest.class"));

        Assert.assertFalse(builder.isTestClass("SuiteTest$1.class"));
        Assert.assertFalse(builder.isTestClass("junit/tests/framework/SuiteTest$1.class"));
    }

    private static class MockedSuiteBuilder extends AbstractSuiteBuilder {
        private MockedSuiteBuilder() {
            super(null);
        }
    }

}