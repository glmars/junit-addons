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

package junitx.framework;

/**
 * A set of assert methods (primarly testing the negative assertion of the
 * correspondent methods found in the <tt>junit.framework.Assert</tt> class).
 *
 * <h4>Usage</h4>
 * 
 * <pre>
 *    import junitx.framework.Assert;
 *
 *    Assert.assertNotEquals(expected, actual);
 * </pre>
 *
 * @version $Revision: 1.15 $ $Date: 2004/07/06 03:31:33 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class Assert
        extends junit.framework.Assert {

    /**
     * Don't let anyone have access to this constructor.
     */
    private Assert() {
    }

	/**
	 * This method does absolutely nothing but can be used to explicitely
	 * mention that a test has passed (if you caught some exception for example.
	 */
	public static void pass() {
	}

	/**
	 * This method does absolutely nothing but can be used to explicitely
	 * mention that a test has passed (if you caught some exception for example.
	 */
	public static void pass(String message) {
	}

    /**
     * Asserts that a condition is false. Throws a
     * <tt>junitx.framework.ComparisonFailure</tt> if not.
     * 
     * @see junitx.framework.ComparisonFailure
     */
    public static void assertFalse(String message,
                                   boolean condition) {
        if (condition) {
            failFalse(message);
        }
    }

    /**
     * Asserts that a condition is false. Throws a
     * <tt>junitx.framework.ComparisonFailure</tt> if not.
     * 
     * @see junitx.framework.ComparisonFailure
     */
    public static void assertFalse(boolean condition) {
        assertFalse(null, condition);
    }

    /**
     * Asserts that two strings are equal. Throws an
     * <tt>AssertionFailedError</tt> if not.
     */
    public static void assertEquals(String expected, String actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two strings are equal. Throws an
     * <tt>AssertionFailedError</tt> if not.
     */
    public static void assertEquals(String message, String expected, String actual) {
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            return;
        }
        throw new ComparisonFailure(message, expected, actual);
    }

    /**
     * Asserts that two objects are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(String message,
                                       Object expected,
                                       Object actual) {
        if ((expected == null && actual == null) ||
                (expected != null && expected.equals(actual))) {
            failNotEquals(message, expected);
        }
    }

    /**
     * Asserts that two objects are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(Object expected,
                                       Object actual) {
        assertNotEquals(null, expected, actual);
    }

    /**
     * Asserts that two bytes are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(String message,
                                       byte expected,
                                       byte actual) {
        assertNotEquals(message, new Byte(expected), new Byte(actual));
    }

    /**
     * Asserts that two bytes are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(byte expected,
                                       byte actual) {
        assertNotEquals(null, expected, actual);
    }

    /**
     * Asserts that two chars are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(String message,
                                       char expected,
                                       char actual) {
        assertNotEquals(message, new Character(expected), new Character(actual));
    }

    /**
     * Asserts that two chars are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(char expected,
                                       char actual) {
        assertNotEquals(null, expected, actual);
    }

    /**
     * Asserts that two doubles are not equal concerning a delta. If the
     * expected value is infinity then the delta value is ignored. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(String message,
                                       double expected,
                                       double actual,
                                       double delta) {
        if (Double.isInfinite(expected)) {
            if (expected == actual) {
                failNotEquals(message, new Double(expected));
            }
        } else if (Math.abs(expected - actual) <= delta) {
            failNotEquals(message, new Double(expected));
        }
    }

    /**
     * Asserts that two doubles are not equal concerning a delta. If the
     * expected value is infinity then the delta value is ignored. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(double expected,
                                       double actual,
                                       double delta) {
        assertNotEquals(null, expected, actual, delta);
    }

    /**
     * Asserts that two floats are not equal concerning a delta. If the
     * expected value is infinity then the delta value is ignored. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(String message,
                                       float expected,
                                       float actual,
                                       float delta) {
        if (Float.isInfinite(expected)) {
            if (expected == actual) {
                failNotEquals(message, new Float(expected));
            }
        } else if (Math.abs(expected - actual) <= delta) {
            failNotEquals(message, new Float(expected));
        }
    }

    /**
     * Asserts that two floats are not equal concerning a delta. If the
     * expected value is infinity then the delta value is ignored. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(float expected,
                                       float actual,
                                       float delta) {
        assertNotEquals(null, expected, actual, delta);
    }

    /**
     * Asserts that two ints are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(String message,
                                       int expected,
                                       int actual) {
        assertNotEquals(message, new Integer(expected), new Integer(actual));
    }

    /**
     * Asserts that two ints are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(int expected,
                                       int actual) {
        assertNotEquals(null, expected, actual);
    }

    /**
     * Asserts that longs objects are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(String message,
                                       long expected,
                                       long actual) {
        assertNotEquals(message, new Long(expected), new Long(actual));
    }

    /**
     * Asserts that two longs are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(long expected,
                                       long actual) {
        assertNotEquals(null, expected, actual);
    }

    /**
     * Asserts that two shorts are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(String message,
                                       short expected,
                                       short actual) {
        assertNotEquals(message, new Short(expected), new Short(actual));
    }

    /**
     * Asserts that two shorts are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(short expected,
                                       short actual) {
        assertNotEquals(null, expected, actual);
    }

    /**
     * Asserts that two boolean are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(String message,
                                       boolean expected,
                                       boolean actual) {
        assertNotEquals(message, new Boolean(expected), new Boolean(actual));
    }

    /**
     * Asserts that two booleans are not equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotEquals(boolean expected,
                                       boolean actual) {
        assertNotEquals(null, expected, actual);
    }

    /**
     * Asserts that two objects do not refer to the same object. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotSame(String message,
                                     Object expected,
                                     Object actual) {
        if (expected == actual) {
            failSame(message, expected);
        }
    }

    /**
     * Asserts that two objects do not refer to the same object. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     */
    public static void assertNotSame(Object expected,
                                     Object actual) {
        assertNotSame(null, expected, actual);
    }

    static private void failFalse(String message) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        fail(formatted + "expected <false>");
    }

    static private void failNotEquals(String message,
                                      Object expected) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        fail(formatted + "expected not equals to: <" + expected + ">");
    }

    static private void failSame(String message,
                                 Object expected) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        fail(formatted + "expected not same as: <" + expected + ">");
    }

    /**
     * Fails a test with the given <tt>Throwable</tt> object causing the
     * failure.
     */
    static public void fail(Throwable cause) {
        fail(null, cause);
    }

    /**
     * Fails a test with the given message and the <tt>Throwable</tt> object
     * causing the failure.
     */
    static public void fail(String message, Throwable cause) {
        throw new AssertionFailedError(message, cause);
    }

}
