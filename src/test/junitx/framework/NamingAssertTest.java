package junitx.framework;

import javax.naming.directory.Attribute;
import javax.naming.directory.*;
import javax.naming.directory.BasicAttributes;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.1 $ $Date: 2003/04/28 03:11:20 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class NamingAssertTest extends TestCase {

    public NamingAssertTest(String name) {
        super(name);
    }

    public void testEqualsAttributesSingleValue() {
        Attributes expected = new BasicAttributes();
        expected.put(new BasicAttribute("attrID", "value1"));

        Attributes actual = new BasicAttributes();
        actual.put(new BasicAttribute("attrID", "value1"));

        NamingAssert.assertEquals(expected, actual);
    }

    public void testFailAttributesNullActual() {
        Attributes expected = new BasicAttributes();
        expected.put(new BasicAttribute("attrID", "value1"));
        try {
            NamingAssert.assertEquals(expected, null);
        } catch (AssertionFailedError e) {
            assertEquals("[actual] expected not <null>", e.getMessage());
            return;
        }
        fail();
    }

    public void testFailAttributesNullExpected() {
        Attributes actual = new BasicAttributes();
        actual.put(new BasicAttribute("attrID", "value1"));
        try {
            NamingAssert.assertEquals(null, actual);
        } catch (AssertionFailedError e) {
            assertEquals("[expected] expected not <null>", e.getMessage());
            return;
        }
        fail();
    }

    public void testFailEqualsAttributesSingleValue() {
        Attributes expected = new BasicAttributes();
        expected.put(new BasicAttribute("attrID", "value1"));

        Attributes actual = new BasicAttributes();
        actual.put(new BasicAttribute("attrID", "value2"));
        try {
            NamingAssert.assertEquals(expected, actual);
        } catch (AssertionFailedError e) {
            assertEquals("expecting <value1> in <value2>", e.getMessage());
            return;
        }
        fail();
    }

    public void testEqualsAttributeSingleValue() {
        Attribute expected = new BasicAttribute("attrID", "value1");
        Attribute actual = new BasicAttribute("attrID", "value1");

        NamingAssert.assertEquals(expected, actual);
    }

    public void testFailAttributeNullActual() {
        Attribute expected = new BasicAttribute("attrID", "value1");
        try {
            NamingAssert.assertEquals(expected, null);
        } catch (AssertionFailedError e) {
            assertEquals("[actual] expected not <null>", e.getMessage());
            return;
        }
        fail();
    }

    public void testFailAttributeNullExpected() {
        Attribute actual = new BasicAttribute("attrID", "value1");
        try {
            NamingAssert.assertEquals(null, actual);
        } catch (AssertionFailedError e) {
            assertEquals("[expected] expected not <null>", e.getMessage());
            return;
        }
        fail();
    }

    public void testFailEqualsAttributeSingleValue() {
        Attribute expected = new BasicAttribute("attrID", "value1");
        Attribute actual = new BasicAttribute("attrID", "value2");
        try {
            NamingAssert.assertEquals(expected, actual);
        } catch (AssertionFailedError e) {
            assertEquals("expecting <value1> in <value2>", e.getMessage());
            return;
        }
        fail();
    }

}
