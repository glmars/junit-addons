package junitx.extensions;

/**
 * @version  $Revision: 1.1 $ $Date: 2003/02/05 10:27:29 $
 * @author  <a href="mailto:pholser@yahoo.com">Paul Holser</a>
 */
public class EqualsHashCodeTestCaseTest
        extends EqualsHashCodeTestCase {

    public EqualsHashCodeTestCaseTest(String name) {
        super(name);
    }

    protected Object createInstance() {
        return new Foo(1);
    }

    protected Object createNotEqualInstance() {
        return new Foo(2);
    }
}
