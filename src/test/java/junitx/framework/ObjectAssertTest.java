package junitx.framework;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import junitx.example.PrivateAccessorExample;

/**
 * @version $Revision: 1.3 $ $Date: 2003/04/27 02:14:59 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ObjectAssertTest
        extends TestCase {

    public ObjectAssertTest(String name) {
        super(name);
    }

    public void testSuccessInstanceOf() {
        ObjectAssert.assertInstanceOf(PrivateAccessorExample.class, new PrivateAccessorExample());
    }

    public void testFailInstanceOf() {
        try {
            ObjectAssert.assertInstanceOf(PrivateAccessorExample.class, new Integer(1));
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testSuccessNotInstanceOf() {
        ObjectAssert.assertNotInstanceOf(PrivateAccessorExample.class, new Integer(1));
    }

    public void testFailNotInstanceOf() {
        try {
            ObjectAssert.assertNotInstanceOf(PrivateAccessorExample.class, new PrivateAccessorExample());
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }

    public void testSuccessSame() {
        Object obj = new Integer(1);
        ObjectAssert.assertSame(obj, obj);        
    }
    
    public void testFailSame() {
        try {
            ObjectAssert.assertSame(new Integer(1), new Integer(1));        
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }
    
    public void testSuccessNotSame() {
        ObjectAssert.assertNotSame(new Integer(1), new Integer(1));        
    }
    
    public void testFailNotSame() {
        try {
            Object obj = new Integer(1);
            ObjectAssert.assertNotSame(obj, obj);        
        } catch (AssertionFailedError e) {
            return;
        }
        fail("Should have thrown exception");
    }
    
}
