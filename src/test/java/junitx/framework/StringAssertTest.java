package junitx.framework;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.3 $ $Date: 2003/04/09 04:39:40 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class StringAssertTest
        extends TestCase {

    public StringAssertTest(String name) {
        super(name);
    }

    public void testSuccessContains() {
        StringAssert.assertContains("is a", "This is a message");
    }

    public void testFailNullExpectedContains() {
        try {
            StringAssert.assertContains(null, "This is a message");
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailNullActualContains() {
        try {
            StringAssert.assertContains("is another", null);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailNullBothContains() {
        try {
            StringAssert.assertContains(null, null);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailContains() {
        try {
            StringAssert.assertContains("is another", "This is a message");
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testSuccessNotContains() {
        StringAssert.assertNotContains("is another", "This is a message");
    }

    public void testSuccessNullActualNotContains() {
        StringAssert.assertNotContains("is another", null);
    }

    public void testSuccessNullExpectedNotContains() {
        StringAssert.assertNotContains(null, "This is a message");
    }

    public void testFailureNullBothNotContains() {
        try {
            StringAssert.assertNotContains(null, null);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailNotContains() {
        try {
            StringAssert.assertNotContains("is a", "This is a message");
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    // tests for startsWith
    
    public void testSuccessStartsWith() {
        StringAssert.assertStartsWith("This is a", "This is a message");
    }

    public void testFailNullExpectedStartsWith() {
        try {
            StringAssert.assertStartsWith(null, "This is a message");
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailNullActualStartsWith() {
        try {
            StringAssert.assertStartsWith("is another", null);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailNullBothStartsWith() {
        try {
            StringAssert.assertStartsWith(null, null);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailStartsWith() {
        try {
            StringAssert.assertStartsWith("is another", "This is a message");
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testSuccessNotStartsWith() {
        StringAssert.assertNotStartsWith("is another", "This is a message");
    }

    public void testSuccessNullActualNotStartsWith() {
        StringAssert.assertNotStartsWith("is another", null);
    }

    public void testSuccessNullExpectedNotStartsWith() {
        StringAssert.assertNotStartsWith(null, "This is a message");
    }

    public void testFailureNullBothNotStartsWith() {
        try {
            StringAssert.assertNotStartsWith(null, null);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailNotStartsWith() {
        try {
            StringAssert.assertNotStartsWith("This is a", "This is a message");
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }
    
    // tests for EndsWith

    public void testSuccessEndsWith() {
        StringAssert.assertEndsWith("a message", "This is a message");
    }

    public void testFailNullExpectedEndsWith() {
        try {
            StringAssert.assertEndsWith(null, "This is a message");
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailNullActualEndsWith() {
        try {
            StringAssert.assertEndsWith("is another", null);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailNullBothEndsWith() {
        try {
            StringAssert.assertEndsWith(null, null);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailEndsWith() {
        try {
            StringAssert.assertEndsWith("another message", "This is a message");
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testSuccessNotEndsWith() {
        StringAssert.assertNotEndsWith("another message", "This is a message");
    }

    public void testSuccessNullActualNotEndsWith() {
        StringAssert.assertNotEndsWith("is another", null);
    }

    public void testSuccessNullExpectedNotEndsWith() {
        StringAssert.assertNotEndsWith(null, "This is a message");
    }

    public void testFailureNullBothNotEndsWith() {
        try {
            StringAssert.assertNotEndsWith(null, null);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailNotEndsWith() {
        try {
            StringAssert.assertNotEndsWith("a message", "This is a message");
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }
    
}
