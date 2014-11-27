package junitx.tool;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.3 $ $Date: 2003/05/04 20:35:58 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir Bossicard</a>
 */
public class NonPublicSuite
        extends TestCase {

    public NonPublicSuite(String name) {
        super(name);
    }

    private static Test suite() {
        return null;
    }

    public static void usePrivateMethodsSoThatPmdDontComplainAnymore() {
        suite();
    }

}
