package junitx.framework;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.2 $ $Date: 2002/09/13 08:19:14 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir Bossicard</a>
 */
public class ThrowableAssertTest
        extends TestCase {

    public ThrowableAssertTest(String name) {
        super(name);
    }

    public void testSuccessEquals() {
        Throwable expected = new TestThrowable("This is a message");
        Throwable actual = new TestThrowable("This is a message");

        ThrowableAssert.assertEquals(expected, actual);
    }

    public void testSuccessEqualsLocal() {
        Throwable expected = new TestThrowable("This is a message", "This is another message");
        Throwable actual = new TestThrowable("This is a message", "This is another message");

        ThrowableAssert.assertEquals(expected, actual);
    }

    public void testFailClass()
            throws Exception {
        try {
            Throwable expected = new TestThrowable("This is a message");
            Throwable actual = new NullPointerException("This is a message");

            ThrowableAssert.assertEquals(expected, actual);
        } catch (AssertionFailedError e) {
            Assert.assertEquals("expected instance of class: <junitx.framework.ThrowableAssertTest$TestThrowable> but was of class: <java.lang.NullPointerException>", e.getMessage());
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailMessage()
            throws Exception {
        try {
            Throwable expected = new TestThrowable("This is a message");
            Throwable actual = new TestThrowable("This is a wrong message");

            ThrowableAssert.assertEquals(expected, actual);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailLocalized()
            throws Exception {
        try {
            Throwable expected = new TestThrowable("This is a message", "This is another message");
            Throwable actual = new TestThrowable("This is a message", "This is another wrong message");

            ThrowableAssert.assertEquals(expected, actual);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testSuccessSimilar() {
        Throwable expected = new TestThrowable("is a");
        Throwable actual = new TestThrowable("This is a message");

        ThrowableAssert.assertSimilar(expected, actual);
    }

    public void testSuccessSimilarLocal() {
        Throwable expected = new TestThrowable("is another");
        Throwable actual = new TestThrowable("This is a message", "This is another message");

        ThrowableAssert.assertSimilar(expected, actual);
    }

    public void testFailSimilar()
            throws Exception {
        try {
            Throwable expected = new TestThrowable("is another");
            Throwable actual = new TestThrowable("This is a message");

            ThrowableAssert.assertSimilar(expected, actual);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testFailSimilarLocal()
            throws Exception {
        try {
            Throwable expected = new TestThrowable("has");
            Throwable actual = new TestThrowable("This is a message", "This is another message");

            ThrowableAssert.assertSimilar(expected, actual);
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public static class TestThrowable
            extends Throwable {

        private String local;

        public TestThrowable(String message) {
            super(message);
            this.local = message;
        }

        public TestThrowable(String message, String local) {
            super(message);
            this.local = local;
        }

        public String getLocalizedMessage() {
            return local;
        }

    }

}