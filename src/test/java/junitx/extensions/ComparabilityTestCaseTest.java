package junitx.extensions;

/**
 * @version  $Revision: 1.1 $ $Date: 2003/02/05 10:27:29 $
 * @author  <a href="mailto:pholser@yahoo.com">Paul Holser</a>
 */
public class ComparabilityTestCaseTest
        extends ComparabilityTestCase {

    public ComparabilityTestCaseTest(String name) {
        super(name);
    }

    protected Comparable createLessInstance() {
        return new Foo(1);
    }

    protected Comparable createEqualInstance() {
        return new Foo(2);
    }

    protected Comparable createGreaterInstance() {
        return new Foo(3);
    }

}
