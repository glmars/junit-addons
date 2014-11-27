package junitx.util;

import junit.framework.TestCase;
import junitx.example.PrivateAccessorExample;

/**
 * @version $Revision: 1.3 $ $Date: 2003/05/04 20:35:58 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class PrivateAccessorTest
        extends TestCase {

    private PrivateAccessorExample example;
    private ExampleSubclass example2;

    public PrivateAccessorTest(String name) {
        super(name);
    }

    public void setUp() {
        this.example = new PrivateAccessorExample();
        this.example2 = new ExampleSubclass();
    }

    public void testGetSetIntViaMethod()
            throws Throwable {
        Object res = PrivateAccessor.invoke(this.example, "setIntValue", new Class[]{int.class}, new Object[]{new Integer(1)});
        assertNull(res);

        res = PrivateAccessor.invoke(this.example, "getIntValue", new Class[0], new Object[0]);
        assertNotNull(res);
        assertEquals(new Integer(1), res);
    }

    public void testGetSetIntDirect()
            throws Exception {
        PrivateAccessor.setField(this.example, "intvalue", new Integer(1));

        Object res = PrivateAccessor.getField(this.example, "intvalue");
        assertNotNull(res);
        assertEquals(new Integer(1), res);
    }

    public void testGetSetDoubleViaMethod()
            throws Throwable {
        Object res = PrivateAccessor.invoke(PrivateAccessorExample.class, "setDoubleValue", new Class[]{double.class}, new Object[]{new Double(1234.567)});
        assertNull(res);

        res = PrivateAccessor.invoke(PrivateAccessorExample.class, "getDoubleValue", new Class[0], new Object[0]);
        assertNotNull(res);
        assertEquals(1234.567, ((Double) res).doubleValue(), 0);
    }

    public void testGetSetDoubleDirect()
            throws Exception {
        PrivateAccessor.setField(PrivateAccessorExample.class, "doublevalue", new Double(1234.567));

        Object res = PrivateAccessor.getField(PrivateAccessorExample.class, "doublevalue");
        assertNotNull(res);
        assertEquals(1234.567, ((Double) res).doubleValue(), 0);
    }

    public void testGetSetBoolViaMethod()
            throws Throwable {
        Object res = PrivateAccessor.invoke(this.example, "setBoolValue", new Class[]{Boolean.class}, new Object[]{new Boolean(false)});
        assertNull(res);

        res = PrivateAccessor.invoke(this.example, "getBoolValue", new Class[0], new Object[0]);
        assertNotNull(res);
        assertEquals(new Boolean(false), res);
    }

    public void testGetSetBoolDirect()
            throws Exception {
        PrivateAccessor.setField(this.example, "boolvalue", new Boolean(true));

        Object res = PrivateAccessor.getField(this.example, "boolvalue");
        assertNotNull(res);
        assertEquals(new Boolean(true), res);
    }

    public void testSubclassGetSetIntViaMethod()
            throws Throwable {
        Object res = PrivateAccessor.invoke(this.example2, "setIntValue", new Class[]{int.class}, new Object[]{new Integer(8)});
        assertNull(res);

        res = PrivateAccessor.invoke(this.example2, "getIntValue", new Class[0], new Object[0]);
        assertNotNull(res);
        assertEquals(new Integer(10), res);
    }

    public void testSubclassGetSetIntDirect()
            throws Exception {
        PrivateAccessor.setField(this.example2, "intvalue", new Integer(8));

        Object res = PrivateAccessor.getField(this.example2, "intvalue");
        assertNotNull(res);
        assertEquals(new Integer(8), res);
    }

    public void testSubclassGetSetBoolViaMethod()
            throws Throwable {
        Object res = PrivateAccessor.invoke(this.example2, "setBoolValue", new Class[]{Boolean.class}, new Object[]{new Boolean(false)});
        assertNull(res);

        res = PrivateAccessor.invoke(this.example2, "getBoolValue", new Class[0], new Object[0]);
        assertNotNull(res);
        assertEquals(new Boolean(false), res);
    }

    public void testSubclassGetSetBoolDirect()
            throws Exception {
        PrivateAccessor.setField(this.example2, "boolvalue", new Boolean(true));

        Object res = PrivateAccessor.getField(this.example2, "boolvalue");
        assertNotNull(res);
        assertEquals(new Boolean(true), res);
    }

    public void testSubclassThrowingException()
            throws Throwable {
        try {
            PrivateAccessor.invoke(this.example2, "throwingException", new Class[0], new Object[0]);
        } catch (NullPointerException e) {
            return;
        }
        fail();
    }

    public class ExampleSubclass
            extends PrivateAccessorExample {

        private int intvalue;

        private int getIntValue() {
            return this.intvalue + 2;
        }

        private void setIntValue(int intvalue) {
            this.intvalue = intvalue;
        }

        private void throwingException() {
            throw new NullPointerException();
        }

        public void usePrivateMethodsSoThatPmdDontComplainAnymore() {
            getIntValue();
            setIntValue(1);
            throwingException();
        }        
    }

}
