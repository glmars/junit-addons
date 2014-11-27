package junitx.util;

import junit.framework.TestCase;

/**
 * @version $Revision$, $Date$
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir Ritz Bossicard</a>
 */
public class BasicTestFilterTest extends TestCase {

    private BasicTestFilter filter;

    public BasicTestFilterTest(String name) {
        super(name);
    }

    public void setUp() {
        filter = new BasicTestFilter();
        filter.setRoot("/home/vladimir/projects/junit-addons/build/classes");
    }
    
    public void testGetClassName() {
        String result = filter.getClassName("/home/vladimir/projects/junit-addons/build/classes/junitx/test/MyClassTest.class");
        assertEquals("MyClassTest", result);
    }

    public void testGetClassNameEmptyPackage() {
        String result = filter.getClassName("/home/vladimir/projects/junit-addons/build/classes/MyClassTest.class");
        assertEquals("MyClassTest", result);
    }

    public void testGetPackageName() {
        String result = filter.getPackageName("/home/vladimir/projects/junit-addons/build/classes/junitx/test/MyClassTest.class");
        assertEquals("junitx.test", result);
    }

    public void testGetPackageNameEmptyPackage() {
        String result = filter.getPackageName("/home/vladimir/projects/junit-addons/build/classes/MyClassTest.class");
        assertEquals("", result);
    }

}
