package junitx.tool;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.2 $ $Date: 2003/01/14 08:58:44 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir Bossicard</a>
 */
public class ValidSuite
        extends TestCase {

    public ValidSuite(String name) {
        super(name);
    }

    public static Test suite() {
        return null;
    }
}
