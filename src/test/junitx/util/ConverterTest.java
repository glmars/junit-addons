package junitx.util;

import junit.framework.TestCase;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * @version $Revision: 1.3 $ $Date: 2003/03/21 06:13:50 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ConverterTest
        extends TestCase {

    public ConverterTest(String name) {
        super(name);
    }

    public void testIteratorToList() {
        List vector = new Vector();
        vector.add("3");
        vector.add("2");
        vector.add("1");
        Iterator iter = vector.iterator();

        List conv = Converter.asList(iter);
        assertNotNull(conv);
        assertEquals(3, conv.size());
        assertTrue(conv.contains("1"));
        assertTrue(conv.contains("2"));
        assertTrue(conv.contains("3"));
    }

}
