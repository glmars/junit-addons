package junitx.util;

import junit.framework.TestCase;

/**
 * @version $Revision: 1.1 $ $Date: 2003/03/21 06:13:50 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ResourceManagerTest extends TestCase {

    static int value = 1;

    public ResourceManagerTest(String name) {
        super(name);
    }

    public void setUp() {
        if (!ResourceManager.containsResource("test")) {
            ResourceManager.addResource("test", new Integer(value));
            value++;
        }
    }

    public void test1() throws Exception {
        assertEquals(new Integer(1), ResourceManager.getResource("test"));
    }

    public void test2() throws Exception {
        assertEquals(new Integer(1), ResourceManager.getResource("test"));
    }

    public void test3() throws Exception {
        assertEquals(new Integer(1), ResourceManager.getResource("test"));
    }

    public void test4() throws Exception {
        assertEquals(new Integer(1), ResourceManager.getResource("test"));
    }

    public void test5() throws Exception {
        assertEquals(new Integer(1), ResourceManager.getResource("test"));
    }

}
