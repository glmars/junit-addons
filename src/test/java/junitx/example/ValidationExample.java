package junitx.example;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.3 $ $Date: 2003/01/14 08:58:42 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ValidationExample
        extends TestCase {

    public ValidationExample(String name) {
        super(name);
    }

    public Test suite() {
        return null;
    }

    public void setup() {
    }

    public void tearDown() {
    }

    public void atestDummy() {
        assertTrue(true);
    }

}
