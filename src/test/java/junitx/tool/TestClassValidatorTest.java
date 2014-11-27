package junitx.tool;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * @version $Revision: 1.4 $ $Date: 2003/05/04 20:35:58 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir Bossicard</a>
 * @author <a href="mailto:benoitx@users.sourceforge.net">Benoit Xhenseval</a>
 */
public class TestClassValidatorTest
        extends TestCase {

    private MockListener listener;
    private TestClassValidator.ClassValidator validator;

    public TestClassValidatorTest(String name) {
        super(name);
    }

    public void setUp() {
        this.listener = new MockListener();
        this.validator = new TestClassValidator.DefaultClassValidator();

        this.validator.setListener(this.listener);
    }

    public void testValidClass() {
        Class cls = new TestCase("") {

            public void setUp() {
            }

            public void tearDown() {
            }

            public void testDummy() {
            }
        }.getClass();
        this.validator.validate(cls);
        assertNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNull(this.listener.error);
    }

    /* SUITE METHOD */

    public void testValidSuite() {
        this.validator.validate(ValidSuite.class);
        assertNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNull(this.listener.error);
    }

    public void testMisspelledSuite() {
        this.validator.validate(MisspelledSuite.class);
        assertNull(this.listener.info);
        assertNotNull(this.listener.warning);
        assertNull(this.listener.error);
    }

    public void testNonPublicSuite() {
        this.validator.validate(NonPublicSuite.class);
        assertNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNotNull(this.listener.error);
    }

    public void testNonStaticSuite() {
        Class cls = (new TestCase("") {
            public Test suite() {
                return null;
            }
        }).getClass();
        this.validator.validate(cls);
        assertNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNotNull(this.listener.error);
    }

    public void testNonTestReturnSuite() {
        this.validator.validate(NonTestReturnSuite.class);
        assertNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNotNull(this.listener.error);
    }

    public void testNonEmptyArgSuite() {
        this.validator.validate(NonEmptyArgSuite.class);
        assertNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNotNull(this.listener.error);
    }

    /* SETUP METHOD */

    public void testMisspelledSetUp() {
        Class cls = (new TestCase("") {

            public void setup() {
            }
        }).getClass();
        this.validator.validate(cls);
        assertNull(this.listener.info);
        assertNotNull(this.listener.warning);
        assertNull(this.listener.error);
    }

    public void testNonEmptyArgSetUp() {
        Class cls = (new TestCase("") {

            public void setUp(boolean val) {
                if (val) {
                    ;
                }
            }
        }).getClass();
        this.validator.validate(cls);
        assertNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNotNull(this.listener.error);
    }

    /* TEARDOWN METHOD */

    public void testMisspelledTearDown() {
        Class cls = (new TestCase("") {

            public void teardown() {
            }
        }).getClass();
        this.validator.validate(cls);
        assertNull(this.listener.info);
        assertNotNull(this.listener.warning);
        assertNull(this.listener.error);
    }

    public void testNonEmptyArgTearDown() {
        Class cls = (new TestCase("") {

            public void tearDown(boolean val) {
                if (val) {
                    ;
                }
            }
        }).getClass();
        this.validator.validate(cls);
        assertNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNotNull(this.listener.error);
    }

    /* TEST METHOD */

    public void testInvalidNameTest() {
        Class cls = (new TestCase("") {

            public void atestDummy() {
            }
        }).getClass();
        this.validator.validate(cls);
        assertNotNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNull(this.listener.error);
    }

    public void testPrivateAccessorTest() {
        Class cls = (new TestCase("") {

            private void testDummy() {
            }
            
            public void usePrivateMethodsSoThatPmdDontComplainAnymore() {
                testDummy();
            }            
        }).getClass();
        this.validator.validate(cls);
        assertNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNotNull(this.listener.error);
    }

    public void testNotNullArgsTest() {
        Class cls = (new TestCase("") {

            public void testPrivate(boolean val) {
                if (val) {
                    ;
                }
            }
        }).getClass();
        this.validator.validate(cls);
        assertNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNotNull(this.listener.error);
    }

    public void testProtectedAccessorTest() {
        Class cls = (new TestCase("") {

            protected void testProtected() {
            }
        }).getClass();
        this.validator.validate(cls);
        assertNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNotNull(this.listener.error);
    }

    public void testTestCaseInheritance() {
        this.validator.validate(ChildTestCase.class);
        assertNotNull(this.listener.info);
        assertNull(this.listener.warning);
        assertNull(this.listener.error);
    }

    public class MockListener
            implements TestClassValidator.ClassValidatorListener {

        public String info, error, warning;

        public void info(String message) {
            this.info = message;
        }

        public void warning(String message) {
            this.warning = message;
        }

        public void error(String message) {
            this.error = message;
        }
    }

}
