package junitx.tool;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.3 $ $Date: 2003/03/21 06:13:47 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir Bossicard</a>
 */
public class NonEmptyArgSuite
        extends TestCase {

    public NonEmptyArgSuite(String name) {
        super(name);
    }

    public static Test suite(boolean val) {
        if (val) {
            ;
        }
        return null;
    }
}

