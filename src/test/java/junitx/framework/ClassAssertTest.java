package junitx.framework;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * @version $Revision$, $Date$
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir Ritz Bossicard</a>
 */
public class ClassAssertTest extends TestCase {

    public ClassAssertTest(String name) {
        super(name);
    }

    public void testSuccessContains() {
        ClassAssert.assertContains(DummyClass.class, "testMethod", new Class[] { int.class });
    }

    public void testFailContains() {
        try {
            ClassAssert.assertContains(DummyClass.class, "testMethod", new Class[] { boolean.class });
        } catch (AssertionFailedError e) {
            return;
        }
        fail();
    }

    private class DummyClass {
        public void testMethod(int param) {
            param++;
        }
    }

}
