package junitx.util;

import junit.framework.TestCase;

/**
 * @version $Revision: 1.1 $, $Date: 2003/04/02 04:50:43 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class SimpleTestFilterTest extends TestCase {

    public SimpleTestFilterTest(String name) {
        super(name);
    }

    public void testGetClassName() {
        String result = SimpleTestFilter.getClassName("junitx/test/MyClassTest.class");
        assertEquals("MyClassTest", result);
    }

    public void testGetClassNameEmptyPackage() {
        String result = SimpleTestFilter.getClassName("MyClassTest.class");
        assertEquals("MyClassTest", result);
    }

    public void testGetPackageName() {
        String result = SimpleTestFilter.getPackageName("junitx/test/MyClassTest.class");
        assertEquals("junitx.test", result);
    }

    public void testGetPackageNameEmptyPackage() {
        String result = SimpleTestFilter.getPackageName("MyClassTest.class");
        assertEquals("", result);
    }

}
