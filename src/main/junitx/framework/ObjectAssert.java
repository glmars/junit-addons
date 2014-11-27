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
 * A set of assert methods specially targetted to asserting objects.
 *
 * @version $Revision: 1.7 $ $Date: 2003/04/27 02:14:33 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ObjectAssert {

    /**
     * Don't let anyone have access to this constructor.
     */
    private ObjectAssert() {
    }

    /**
     * Asserts that an object is an instance of a class.  Throws an
     * <tt>AssertionFailedError</tt> if it is not an instance of that class.
     */
    public static void assertInstanceOf(String message,
                                        Class expected,
                                        Object actual) {
        if (expected.isInstance(actual)) {
            return;
        }
        failInstanceOf(message, expected, actual);
    }

    /**
     * Asserts that an object is an instance of a class.  Throws an
     * <tt>AssertionFailedError</tt> if it is not an instance of that class.
     */
    public static void assertInstanceOf(Class expected,
                                        Object actual) {
        assertInstanceOf(null, expected, actual);
    }

    /**
     * Asserts that an object is not an instance of a class.  Throws an
     * <tt>AssertionFailedError</tt> if it is an instance of that class.
     */
    public static void assertNotInstanceOf(String message,
                                           Class expected,
                                           Object actual) {
        if (!expected.isInstance(actual)) {
            return;
        }
        failNotInstanceOf(message, expected);
    }

    /**
     * Asserts that an object is not an instance of a class.  Throws an
     * <tt>AssertionFailedError</tt> if it is an instance of that class.
     */
    public static void assertNotInstanceOf(Class expected,
                                           Object actual) {
        assertNotInstanceOf(null, expected, actual);
    }

    /**
     * Asserts that an object are the same.  Throws an
     * <tt>AssertionFailedError</tt> if they are not (including the objects' 
     * values).
     */
    public static void assertSame(Object expected,
                                    Object actual) {
        assertSame(null, expected, actual);
    }

    /**
     * Asserts that an object are the same.  Throws an
     * <tt>AssertionFailedError</tt> if they are not  (including the objects' 
     * values).
     */
    public static void assertSame(String message, 
                                    Object expected,
                                    Object actual) {
        if (expected == actual) {
            return;
        }
        failSame(message, expected, actual);
    }

    /**
     * Asserts that an object are not the same.  Throws an
     * <tt>AssertionFailedError</tt> if they are.
     */
    public static void assertNotSame(Object expected,
                                       Object actual) {
        assertNotSame(null, expected, actual);
    }

    /**
     * Asserts that an object are not the same.  Throws an
     * <tt>AssertionFailedError</tt> if they are.
     */
    public static void assertNotSame(String message, 
                                       Object expected,
                                       Object actual) {
        if (expected != actual) {
            return;
        }
        failNotSame(message, expected);
    }

    static private void failInstanceOf(String message,
                                       Class expected,
                                       Object actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected instance of class: <" + expected.getName() + "> but was of class: <" + actual.getClass().getName() + ">");
    }

    static private void failNotInstanceOf(String message,
                                          Class expected) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.fail(formatted + "expected not instance of class: <" + expected.getName() + ">");
    }

    static private void failSame(String message,
                                   Object expected,
                                   Object actual) {
       String formatted = "";
       if (message != null) {
           formatted = message + " ";
       }

       Assert.fail(formatted + "expected same as : <" + expected + "> but was <" + actual + ">");
    }
    
    static private void failNotSame(String message,
                                      Object expected) {
       String formatted = "";
       if (message != null) {
           formatted = message + " ";
       }

       Assert.fail(formatted + "expected not same as : <" + expected + ">");
    }
    
}
