package junitx.framework;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.6 $ $Date: 2003/02/10 20:26:42 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir Bossicard</a>
 */
public class AssertTest
        extends TestCase {

    public AssertTest(String name) {
        super(name);
    }

    public void testSuccessAssertFalse() {
        Assert.assertFalse(false);
    }

    public void testFailAssertFalse()
            throws Exception {
        try {
            Assert.assertFalse(true);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testSucceedAssertNotEquals()
            throws Exception {
        Assert.assertNotEquals(new Integer(1), new Integer(2));
    }

    public void testSucceedAssertNotEqualsNullExpected()
            throws Exception {
        Assert.assertNotEquals(null, new Integer(2));
    }

    public void testSucceedAssertNotEqualsNullActual()
            throws Exception {
        Assert.assertNotEquals(new Integer(1), null);
    }

    public void testFailAssertNotEquals()
            throws Exception {
        try {
            Assert.assertNotEquals(new Integer(2), new Integer(2));
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailAssertNotEqualsNulls()
            throws Exception {
        try {
            Assert.assertNotEquals(null, null);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testSucceedAssertNotEqualsFloat() {
        Assert.assertNotEquals(1.0d, 2.0d, 0.5d);
    }

    public void testFailAssertNotEqualsFloat() {
        try {
            Assert.assertNotEquals(1.0d, 1.5d, 1.0d);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testSucceedNotSame() {
        Assert.assertNotSame(new Integer(1), new Integer(1));
    }

    public void testFailNotSame() {
        try {
            Integer integer = new Integer(1);
            Assert.assertNotSame(integer, integer);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailThrowable() {
        try {
            Assert.fail(new NullPointerException("dummy"));
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

}
