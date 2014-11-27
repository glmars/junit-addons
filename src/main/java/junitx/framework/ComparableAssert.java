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
 * A set of assert methods specially targetted to comparable objects.
 *
 * @version $Revision: 1.6 $ $Date: 2003/03/23 01:25:24 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ComparableAssert {

    /**
     * Don't let anyone have access to this constructor.
     */
    private ComparableAssert() {
    }

    /**
     * Asserts that the <tt>actual</tt> object is lesser than the
     * <tt>limit</tt> object.
     * Throws an <tt>AssertionFailedError</tt> if it is greater or equal.
     */
    static public void assertLesser(String message,
                                    Comparable limit,
                                    Comparable actual) {
        Assert.assertNotNull(message, limit);
        Assert.assertNotNull(message, actual);
        if (limit.compareTo(actual) <= 0) {
            failLesser(message, limit, actual);
        }
    }

    /**
     * Asserts that the <tt>actual</tt> object is lesser than the
     * <tt>limit</tt> object.
     * Throws an <tt>AssertionFailedError</tt> if it is greater or equal.
     */
    static public void assertLesser(Comparable limit,
                                    Comparable actual) {
        assertLesser(null, limit, actual);
    }

    /**
     * Asserts that the <tt>actual</tt> object is not lesser than the
     * <tt>limit</tt> object.
     * Throws an <tt>AssertionFailedError</tt> if it is lesser.
     */
    static public void assertNotLesser(String message,
                                       Comparable limit,
                                       Comparable actual) {
        Assert.assertNotNull(message, limit);
        Assert.assertNotNull(message, actual);
        if (limit.compareTo(actual) > 0) {
            failNotLesser(message, limit, actual);
        }
    }

    /**
     * Asserts that the <tt>actual</tt> object is not lesser than the
     * <tt>limit</tt> object.
     * Throws an <tt>AssertionFailedError</tt> if it is lesser.
     */
    static public void assertNotLesser(Comparable limit,
                                       Comparable actual) {
        assertNotLesser(null, limit, actual);
    }

    /**
     * Asserts that the <tt>expected</tt> and <tt>actual</tt> are equals
     * (comparables).
     * Throws an <tt>AssertionFailedError</tt> if it is lesser or equal.
     */
    static public void assertEquals(String message,
                                    Comparable expected,
                                    Comparable actual) {
        Assert.assertNotNull(message, expected);
        Assert.assertNotNull(message, actual);
        if (expected.compareTo(actual) != 0) {
            failNotEquals(message, expected, actual);
        }
    }

    /**
     * Asserts that the <tt>expected</tt> and <tt>actual</tt> are equals
     * (comparables).
     * Throws an <tt>AssertionFailedError</tt> if it is lesser or equal.
     */
    static public void assertEquals(Comparable expected,
                                    Comparable actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that the <tt>expected</tt> and <tt>actual</tt> are not equals
     * (comparables). Throws an <tt>AssertionFailedError</tt> if it is lesser or
     * equal.
     */
    static public void assertNotEquals(String message,
                                       Comparable expected,
                                       Comparable actual) {
        Assert.assertNotNull(message, expected);
        Assert.assertNotNull(message, actual);
        if (expected.compareTo(actual) == 0) {
            failEquals(message, expected);
        }
    }

    /**
     * Asserts that the <tt>expected</tt> and <tt>actual</tt> are not equals
     * (comparables). Throws an <tt>AssertionFailedError</tt> if it is lesser or
     * equal.
     */
    static public void assertNotEquals(Comparable expected,
                                       Comparable actual) {
        assertNotEquals(null, expected, actual);
    }

    /**
     * Asserts that the <tt>actual</tt> object is greater than the
     * <tt>limit</tt> object.
     * Throws an <tt>AssertionFailedError</tt> if it is lesser or equal.
     */
    static public void assertGreater(String message,
                                     Comparable limit,
                                     Comparable actual) {
        Assert.assertNotNull(message, limit);
        Assert.assertNotNull(message, actual);
        if (limit.compareTo(actual) >= 0) {
            failGreater(message, limit, actual);
        }
    }

    /**
     * Asserts that the <tt>actual</tt> object is greater than the
     * <tt>limit</tt> object.
     * Throws an <tt>AssertionFailedError</tt> if it is lesser or equal.
     */
    static public void assertGreater(Comparable limit,
                                     Comparable actual) {
        assertGreater(null, limit, actual);
    }

    /**
     * Asserts that the <tt>actual</tt> object is not greater than the
     * <tt>limit</tt> object.
     * Throws an <tt>AssertionFailedError</tt> if it is greater.
     */
    static public void assertNotGreater(String message,
                                        Comparable limit,
                                        Comparable actual) {
        Assert.assertNotNull(message, limit);
        Assert.assertNotNull(message, actual);
        if (limit.compareTo(actual) < 0) {
            failNotGreater(message, limit, actual);
        }
    }

    /**
     * Asserts that the <tt>actual</tt> object is not greater than the
     * <tt>limit</tt> object.
     * Throws an <tt>AssertionFailedError</tt> if it is greater.
     */
    static public void assertNotGreater(Comparable limit,
                                        Comparable actual) {
        assertNotGreater(null, limit, actual);
    }

    static private void failGreater(String message,
                                      Object limit,
                                      Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected greater than:<" +
                limit + "> but was:<" + actual + ">");
    }

    static private void failNotGreater(String message,
                                         Object limit,
                                         Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected not greater than:<" +
                limit + "> but was:<" + actual + ">");
    }

    static private void failLesser(String message,
                                     Object limit,
                                     Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected lesser than:<" +
                limit + "> but was:<" + actual + ">");
    }

    static private void failNotLesser(String message,
                                        Object limit,
                                        Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected not lesser than:<" +
                limit + "> but was:<" + actual + ">");
    }

    static private void failNotEquals(String message,
                                        Object expected,
                                        Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected equals to:<" +
                expected + "> but was:<" + actual + ">");
    }

    static private void failEquals(String message,
                                     Object expected) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected not equals to:<" +
                expected + ">");
    }

}
