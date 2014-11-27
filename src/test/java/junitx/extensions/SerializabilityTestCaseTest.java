package junitx.extensions;

import java.io.Serializable;

/**
 * Unit tests for the serializability test class.
 *
 * @version  $Revision: 1.1 $ $Date: 2003/02/05 10:27:29 $
 * @author  <a href="mailto:pholser@yahoo.com">Paul Holser</a>
 */
public class SerializabilityTestCaseTest
        extends SerializabilityTestCase {

    public SerializabilityTestCaseTest(String name) {
        super(name);
    }

    protected Serializable createInstance() {
        return new Foo(1);
    }

}
