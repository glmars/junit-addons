/*
 * The JUnit-addons Software License, Version 1.0
 *     (based on the Apache Software License, Version 1.1)
 *
 * Copyright (c) 2002-2003 Vladimir R. Bossicard.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by Vladimir R.
 *        Bossicard as well as other contributors
 *        (http://junit-addons.sourceforge.net/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The name "JUnit-addons" must not be used to endorse or promote
 *    products derived from this software without prior written
 *    permission. For written permission, please contact
 *    vbossica@users.sourceforge.net.
 *
 * 5. Products derived from this software may not be called "JUnit-addons"
 *    nor may "JUnit-addons" appear in their names without prior written
 *    permission of the project managers.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ======================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals.  For more information on the JUnit-addons Project, please
 * see <http://junit-addons.sourceforge.net/>.
 */

package junitx.tool;

import junit.framework.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Checks if a test class can be recognized by the JUnit framework.
 *
 * <h4>Usage</h4>
 *
 * <pre>
 *    java TestCaseValidator [-listener classname] [-validator classname] classname
 * </pre>
 *
 * <h4>Example</h4>
 *
 * <pre>
 *    java TestCaseValidator ValidationExample
 * </pre>
 *
 * <pre>
 *    public class ValidationExample
 *            extends TestCase {
 *
 *        public ValidationExample( String name ) {
 *            super( name );
 *        }
 *
 *        public Test suite() {
 *            return null;
 *        }
 *
 *        public void setup() {
 *        }
 *
 *        public void tearDown() {
 *        }
 *
 *        public void atestDummy() {
 *            assertTrue( true );
 *        }
 *    }
 * </pre>
 *
 * prints the following report:
 *
 * <pre>
 *    TestClassValidator, by Vladimir R. Bossicard
 *    WARN > junitx.example.ValidationExample: method potentially misspelled <setup>
 *    ERROR> junitx.example.ValidationExample: method 'suite' must be static
 *    INFO > junitx.example.ValidationExample: method seems to be a test <atestDummy>
 * </pre>
 *
 * @version $Revision: 1.9 $ $Date: 2003/02/06 20:43:54 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 * @author <a href="mailto:benoitx@users.sourceforge.net">Benoit Xhenseval</a>
 */
public class TestClassValidator {

    /** Advertising message */
    public static final String BANNER = "TestClassValidator, by Vladimir R. Bossicard";

    private ClassValidator validator = null;
    private ClassValidatorListener listener = null;

    public void setValidator(ClassValidator validator) {
        this.validator = validator;
    }

    public void setValidatorListener(ClassValidatorListener listener) {
        this.listener = listener;
    }

    private void init(String[] args)
            throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {
        this.validator = createValidator(args);
        this.listener = createValidatorListener(args);
        go(args);
    }

    private ClassValidator createValidator(String[] args)
            throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {
        ClassValidator res = null;
        if (args[0].equals("-validator")) {
            res = (ClassValidator) Class.forName(args[1]).newInstance();
        } else if ((args.length >= 3) &&
                args[2].equals("-validator")) {
            if (res == null) {
                throw new IllegalStateException("Two validators are defined");
            } else {
                res = (ClassValidator) Class.forName(args[3]).newInstance();
            }
        }
        return res;
    }

    private ClassValidatorListener createValidatorListener(String[] args)
            throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {
        ClassValidatorListener res = null;
        if (args[0].equals("-listener")) {
            res = (ClassValidatorListener) Class.forName(args[1]).newInstance();
        } else if ((args.length >= 3) &&
                args[2].equals("-listener")) {
            if (res == null) {
                throw new IllegalStateException("Two listeners are defined");
            } else {
                res = (ClassValidatorListener) Class.forName(args[3]).newInstance();
            }
        }
        return res;
    }

    private void go(String[] args)
            throws ClassNotFoundException {
        if (this.validator == null) {
            this.validator = new DefaultClassValidator();
        }

        if (this.listener == null) {
            this.listener = new ClassValidatorListener() {

                public void info(String message) {
                    System.out.println("INFO > " + message);
                }

                public void warning(String message) {
                    System.out.println("WARN > " + message);
                }

                public void error(String message) {
                    System.out.println("ERROR> " + message);
                }
            };
        }

        this.validator.setListener(this.listener);
        this.validator.validate(Class.forName(args[args.length - 1]));
    }

    private static void man() {
        System.out.println("usage: junitx.tool.TestClassValidator [-validator classname] [-listener classname] classname");
    }

    public static void main(String[] args) {
        try {
            System.out.println(BANNER);
            new TestClassValidator().init(args);
        } catch (Exception e) {
            man();
            System.out.println();
            e.printStackTrace(System.err);
        }
    }

    /**
     * Class reponsible for validating a given class. Users willing to
     * program their own validator can implement this interface or extend the
     * <tt>DefaultClassValidator</tt> class.<p>
     *
     * The information can be of the following types:
     * <ul>
     * <li><i>info</i>: just an information.</li>
     * <li><i>warning</i>: a minor problem has been found. The tests can still
     * be executed.</li>
     * <li><i>error</i>: a major problem that will prevent the tests from
     * beeing correctly executed has been found.</li>
     * </ul>
     *
     * @version $Revision: 1.9 $ $Date: 2003/02/06 20:43:54 $
     * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
     * @see TestClassValidator
     * @see TestClassValidator.ClassValidatorListener
     */
    public interface ClassValidator {

        /**
         * Sets the listener that will report to the user. As a general rule
         * All information must be handled by the listener and not by the
         * <tt>ClassValidator</> object itselfs.
         */
        public void setListener(ClassValidatorListener listener);

        /**
         * Validates a given class and report infos, warnings and errors via
         * the <tt>ClassValidatorListener</tt> object (if any was set).
         */
        public void validate(Class cls);

    }

    /**
     * Listener that will receive update from the <tt>ClassValidator</tt>
     * object.
     *
     * @version $Revision: 1.9 $ $Date: 2003/02/06 20:43:54 $
     * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
     * @see TestClassValidator.ClassValidator
     * @see TestClassValidator.ClassValidator#setListener
     */
    public interface ClassValidatorListener {

        /**
         * Fired when the <tt>ClassValidator</tt> has something to say.
         */
        void info(String message);

        /**
         * Fired when the <tt>ClassValidator</tt> has something important to
         * say.
         */
        void warning(String message);

        /**
         * Fired when the <tt>ClassValidator</tt> has something very important
         * to say.
         */
        void error(String message);

    }

    /**
     * Provides a default implementation for the <code>ClassValidator</code>
     * interface. This class is meant to become the base class for other, more
     * specific, validators.
     *
     * @version $Revision: 1.9 $ $Date: 2003/02/06 20:43:54 $
     * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
     * @see TestClassValidator
     */
    public static class DefaultClassValidator
            implements ClassValidator {

        private ClassValidatorListener listener = null;

        public void setListener(ClassValidatorListener listener) {
            this.listener = listener;
        }

        public void validate(Class cls) {
            Class base = cls;
            while (base != null) {
                Method mtds[] = base.getDeclaredMethods();
                for (int ii = 0; ii < mtds.length; ii++) {
                    Method mtd = mtds[ii];
                    String name = mtd.getName();
                    if (name.equals("suite")) {
                        validateSuiteMethod(mtd);
                    } else if (name.equals("setUp")) {
                        validateSetUpMethod(mtd);
                    } else if (name.equals("tearDown")) {
                        validateTearDownMethod(mtd);
                    } else if (name.startsWith("test")) {
                        validateTestMethod(mtd);
                    } else {
                        validateOtherMethod(mtd);
                    }
                }
                base = base.getSuperclass();
            }
        }

        /**
         * Verifies that the 'suite' method is recognized by the JUnit
         * framework.<p>
         *
         * The method must:
         * <ul>
         * <li>be static</li>
         * <li>be public</li>
         * <li>return Test</li>
         * <li>have no argument</li>
         * </ul>
         */
        protected void validateSuiteMethod(Method method) {
            int modifier = method.getModifiers();
            if (!Modifier.isPublic(modifier)) {
                fireError(method.getDeclaringClass().getName() + ": method 'suite' must be public");
            }
            if (!Modifier.isStatic(modifier)) {
                fireError(method.getDeclaringClass().getName() + ": method 'suite' must be static");
            }
            if (method.getReturnType() != Test.class) {
                fireError(method.getDeclaringClass().getName() + ": method 'suite' must return Test");
            }
            if (method.getParameterTypes().length != 0) {
                fireError(method.getDeclaringClass().getName() + ": method 'suite' must have no argument");
            }
        }

        /**
         * Verifies that the 'setUp' method is recognized by the JUnit
         * framework.<p>
         *
         * The method must:
         * <ul>
         * <li>have no argument</li>
         * </ul>
         */
        protected void validateSetUpMethod(Method method) {
            if (method.getParameterTypes().length != 0) {
                fireError(method.getDeclaringClass().getName() + ".setUp: method must have no argument");
            }
        }

        /**
         * Verifies that the 'tearDown' method is recognized by the JUnit
         * framework.<p>
         *
         * The method must:
         * <ul>
         * <li>have no argument</li>
         * </ul>
         */
        protected void validateTearDownMethod(Method method) {
            if (method.getParameterTypes().length != 0) {
                fireError(method.getDeclaringClass().getName() + ".tearDown: method must have no argument");
            }
        }

        /**
         * Verifies that the 'test' method is recognized by the JUnit
         * framework.<p>
         *
         * The method must:
         * <ul>
         * <li>be public</li>
         * <li>have no argument</li>
         * </ul>
         */
        protected void validateTestMethod(Method method) {
            if (method.getParameterTypes().length != 0) {
                fireError(method.getDeclaringClass().getName() + ": test method must have no argument");
            }
            if (!Modifier.isPublic(method.getModifiers())) {
                fireError(method.getDeclaringClass().getName() + ": test method must be public");
            }
        }

        /**
         * Validates all other methods whose name did not match either 'suite',
         * 'setUp' or 'tearDown'.
         */
        protected void validateOtherMethod(Method method) {
            String name = method.getName();
            if (name.toLowerCase().equals("setup")) {
                fireWarning(method.getDeclaringClass().getName() + ": method potentially misspelled <" + name + ">");
            } else if (name.toLowerCase().equals("teardown")) {
                fireWarning(method.getDeclaringClass().getName() + ": method potentially misspelled <" + name + ">");
            } else if (name.toLowerCase().equals("suite")) {
                fireWarning(method.getDeclaringClass().getName() + ": method potentially misspelled <" + name + ">");
            } else if (name.indexOf("test") > 0) {
                fireInfo(method.getDeclaringClass().getName() + ": method seems to be a test <" + name + ">");
            }
        }

        private void fireInfo(String message) {
            if (this.listener != null) {
                this.listener.info(message);
            }
        }

        private void fireWarning(String message) {
            if (this.listener != null) {
                this.listener.warning(message);
            }
        }

        private void fireError(String message) {
            if (this.listener != null) {
                this.listener.error(message);
            }
        }

    }

}
