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
 * A set of assert methods specially targetted at string objects.
 *
 * @version $Revision: 1.8 $ $Date: 2003/04/27 02:13:47 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class StringAssert {

    /**
     * Don't let anyone have access to this constructor.
     */
    private StringAssert() {
    }

    /**
     * Asserts that a string contains a substring. Throws an
     * <tt>AssertionFailedError</tt> if it doesn't contain the substring.
     */
    public static void assertContains(String substring,
                                      String actual) {
        assertContains(null, substring, actual);
    }

    /**
     * Asserts that a string contains a substring. Throws an
     * <tt>AssertionFailedError</tt> if it doesn't contain the substring.
     */
    public static void assertContains(String message,
                                      String substring,
                                      String actual) {
        Assert.assertNotNull(substring);
        if (actual != null && actual.indexOf(substring) >= 0) {
            return;
        }
        failContains(message, substring, actual);
    }

    /**
     * Asserts that a string does not contain a substring. Throws an
     * <tt>AssertionFailedError</tt> if it contains the substring.
     */
    public static void assertNotContains(String substring,
                                         String actual) {
        assertNotContains(null, substring, actual);
    }

    /**
     * Asserts that a string does not contain a substring. Throws an
     * <tt>AssertionFailedError</tt> if it contains the substring.
     */
    public static void assertNotContains(String message,
                                         String substring,
                                         String actual) {
        if ((actual == null && substring != null) ||
                (actual != null && substring == null) ||
                (actual != null && actual.indexOf(substring) < 0)) {
            return;
        }
        failNotContains(message, substring, actual);
    }

    /**
     * Asserts that a string starts with a substring. Throws an
     * <tt>AssertionFailedError</tt> if it contains the substring.
     */
    public static void assertStartsWith(String substring, String actual) {
        assertStartsWith(null, substring, actual);
    }
    
    /**
     * Asserts that a string starts with a substring. Throws an
     * <tt>AssertionFailedError</tt> if it contains the substring.
     */
    public static void assertStartsWith(String message, String substring, String actual) {
        Assert.assertNotNull(substring);
        if (actual != null && actual.startsWith(substring)) {
            return;
        }
        failStartsWith(message, substring, actual);
    }
    
    /**
     * Asserts that a string doesn't start with a substring. Throws an
     * <tt>AssertionFailedError</tt> if it contains the substring.
     */
    public static void assertNotStartsWith(String substring, String actual) {
        assertNotStartsWith(null, substring, actual);
    }
    
    /**
     * Asserts that a string doesn't start with a substring. Throws an
     * <tt>AssertionFailedError</tt> if it contains the substring.
     */
    public static void assertNotStartsWith(String message, String substring, String actual) {
        if ((actual == null && substring != null) ||
                (actual != null && substring == null) ||
                (actual != null && !actual.startsWith(substring))) {
            return;
        }
        failNotStartsWith(message, substring);
    }
    
    /**
     * Asserts that a string ends with a substring. Throws an
     * <tt>AssertionFailedError</tt> if it contains the substring.
     */
    public static void assertEndsWith(String substring, String actual) {
        assertEndsWith(null, substring, actual);
    }
    
    /**
     * Asserts that a string ends with a substring. Throws an
     * <tt>AssertionFailedError</tt> if it contains the substring.
     */
    public static void assertEndsWith(String message, String substring, String actual) {
        Assert.assertNotNull(substring);
        if (actual != null && actual.endsWith(substring)) {
            return;
        }
        failEndsWith(message, substring, actual);
    }
    
    /**
     * Asserts that a string doesn't end with a substring. Throws an
     * <tt>AssertionFailedError</tt> if it contains the substring.
     */
    public static void assertNotEndsWith(String substring, String actual) {
        assertNotEndsWith(null, substring, actual);
    }
    
    /**
     * Asserts that a string doesn't end with a substring. Throws an
     * <tt>AssertionFailedError</tt> if it contains the substring.
     */
    public static void assertNotEndsWith(String message, String substring, String actual) {
        if ((actual == null && substring != null) ||
                (actual != null && substring == null) ||
                (actual != null && !actual.endsWith(substring))) {
            return;
        }
        failNotEndsWith(message, substring);
    }
    
    private static void failContains(String message,
                                     String substring,
                                     String actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected containing: <" + substring + "> but was: <" + actual + ">");
    }

    private static void failNotContains(String message,
                                        String substring,
                                        String actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected not containing: <" + substring + "> but was: <" + actual + ">");
    }

    private static void failStartsWith(String message, String substring, String actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected starting with: <" + substring + "> but was: <" + actual + ">");
    }

    private static void failNotStartsWith(String message, String substring) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected not starting with: <" + substring + ">");
    }

    private static void failEndsWith(String message, String substring, String actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected ending with: <" + substring + "> but was: <" + actual + ">");
    }
    
    private static void failNotEndsWith(String message, String substring) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected not ending with: <" + substring + ">");
    }
    
}
