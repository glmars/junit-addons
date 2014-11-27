package junitx.util;

import junit.framework.TestCase;

/**
 * @version $Revision: 1.1 $ $Date: 2002/08/26 04:13:02 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class PropertyManagerTest
        extends TestCase {

    public PropertyManagerTest(String name) {
        super(name);
    }

    public void testSuccessGetProperty() {
        assertEquals("value1", PropertyManager.getProperty("test1"));
    }

    public void testSuccessGetProperty2() {
        assertEquals("value2", PropertyManager.getProperty("test2"));
    }

    public void testSuccessDefaultProperty2() {
        assertEquals("value2", PropertyManager.getProperty("test2", "defaultvalue2"));
    }

    public void testFailureMissingProperty() {
        assertEquals(null, PropertyManager.getProperty("test3"));
    }

    public void testSuccessMissingProperty() {
        assertEquals("defaultvalue", PropertyManager.getProperty("test3", "defaultvalue"));
    }

}