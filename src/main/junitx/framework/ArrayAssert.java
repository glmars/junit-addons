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
 * 4. The names "JUnit-addons" must not be used to endorse or promote
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

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A set of assert methods specially targetted to asserting arrays.
 *
 * @version $Revision: 1.11 $ $Date: 2003/04/14 01:06:32 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ArrayAssert {

    /**
     * Don't let anyone have access to this constructor.
     */
    private ArrayAssert() {
    }

    /**
     * Asserts that two arrays are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.
     *
     * Two arrays are considered equals if:
     * <ul>
     * <li>their respective lengths are the same</li>
     * <li>the respective messages must be equal</li>
     * <li>the respective localized messages must be equal</li>
     * </ul>
     */
    static public void assertEquals(String message,
                                    Object[] expected,
                                    Object[] actual) {
        if (Arrays.equals(expected, actual)) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "expected array: <not null> but was <null>", expected);
        Assert.assertNotNull(formatted + "expected array: <null> but was <not null>", actual);
        Assert.assertEquals(formatted + "[array length] ", expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(formatted + "[position " + i + "]", expected[i], actual[i]);
        }
    }

    /**
     * Asserts that two arrays are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are not.
     */
    static public void assertEquals(Object[] expected,
                                    Object[] actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal (within the delta range)
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param expected a double array of expected values
     * @param actual a double array of actual values
     * @param delta tolerated delta
     */
    public static void assertEquals(double[] expected,
                                    double[] actual,
                                    double delta) {
        assertEquals(null, expected, actual, delta);
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal (within the delta range)
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param message message to display when arrays are not equal
     * @param expected a double array of expected values
     * @param actual a double array of actual values
     * @param delta tolerated delta
     */
    public static void assertEquals(String message,
                                    double[] expected,
                                    double[] actual,
                                    double delta) {
        if (Arrays.equals(expected, actual)) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "expected array: <not null> but was <null>", expected);
        Assert.assertNotNull(formatted + "expected array: <null> but was <not null>", actual);
        Assert.assertEquals(formatted + "[array length] ", expected.length, actual.length);
        for (int i = 0; i < actual.length; i++) {
            Assert.assertEquals(formatted + "[position " + i + "]", expected[i], actual[i], delta);
        }
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal (within the delta range)
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param message message to display when arrays are not equal
     * @param expected a float array of expected values
     * @param actual a float array of actual values
     * @param delta tolerated delta
     */
    public static void assertEquals(String message,
                                    float[] expected,
                                    float[] actual,
                                    float delta) {
        if (Arrays.equals(expected, actual)) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "expected array: <not null> but was <null>", expected);
        Assert.assertNotNull(formatted + "expected array: <null> but was <not null>", actual);
        Assert.assertEquals(formatted + "[array length] ", expected.length, actual.length);
        for (int i = 0; i < actual.length; i++) {
            Assert.assertEquals(formatted + "[position " + i + "]", expected[i], actual[i], delta);
        }
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal (within the delta range)
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param expected a float array of expected values
     * @param actual a float array of actual values
     * @param delta tolerated delta
     */
    public static void assertEquals(float[] expected,
                                    float[] actual,
                                    float delta) {
        assertEquals(null, expected, actual, delta);
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param message message to display when arrays are not equal
     * @param expected a long array of expected values
     * @param actual a long array of actual values
     */
    public static void assertEquals(String message,
                                    long[] expected,
                                    long[] actual) {
        if (Arrays.equals(expected, actual)) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "expected array: <not null> but was <null>", expected);
        Assert.assertNotNull(formatted + "expected array: <null> but was <not null>", actual);
        Assert.assertEquals(formatted + "[array length] ", expected.length, actual.length);
        for (int i = 0; i < actual.length; i++) {
            Assert.assertEquals(formatted + "[position " + i + "]", expected[i], actual[i]);
        }
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param expected a long array of expected values
     * @param actual a long array of actual values
     */
    public static void assertEquals(long[] expected,
                                    long[] actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param message message to display when arrays are not equal
     * @param expected an int array of expected values
     * @param actual an int array of actual values
     */
    public static void assertEquals(String message,
                                    int[] expected,
                                    int[] actual) {
        if (Arrays.equals(expected, actual)) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "expected array: <not null> but was <null>", expected);
        Assert.assertNotNull(formatted + "expected array: <null> but was <not null>", actual);
        Assert.assertEquals(formatted + "[array length] ", expected.length, actual.length);

        for (int i = 0; i < actual.length; i++) {
            Assert.assertEquals(formatted + "[position " + i + "]", expected[i], actual[i]);
        }
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param expected an int array of expected values
     * @param actual an int array of actual values
     */
    public static void assertEquals(int[] expected,
                                    int[] actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param message message to display when arrays are not equal
     * @param expected a short array of expected values
     * @param actual a short array of actual values
     */
    public static void assertEquals(String message,
                                    short[] expected,
                                    short[] actual) {
        if (Arrays.equals(expected, actual)) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "expected array: <not null> but was <null>", expected);
        Assert.assertNotNull(formatted + "expected array: <null> but was <not null>", actual);
        Assert.assertEquals(formatted + "[array length] ", expected.length, actual.length);

        for (int i = 0; i < actual.length; i++) {
            Assert.assertEquals(formatted + "[position " + i + "]", expected[i], actual[i]);
        }
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param expected a short array of expected values
     * @param actual a short array of actual values
     */
    public static void assertEquals(short[] expected,
                                    short[] actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param message message to display when arrays are not equal
     * @param expected a short array of expected values
     * @param actual a short array of actual values
     */
    public static void assertEquals(String message,
                                    char[] expected,
                                    char[] actual) {
        if (Arrays.equals(expected, actual)) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "expected array: <not null> but was <null>", expected);
        Assert.assertNotNull(formatted + "expected array: <null> but was <not null>", actual);
        Assert.assertEquals(formatted + "[array length] ", expected.length, actual.length);
        for (int i = 0; i < actual.length; i++) {
            Assert.assertEquals(formatted + "[position " + i + "]", expected[i], actual[i]);
        }
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param expected a short array of expected values
     * @param actual a short array of actual values
     */
    public static void assertEquals(char[] expected,
                                    char[] actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param message message to display when arrays are not equal
     * @param expected a short array of expected values
     * @param actual a short array of actual values
     */
    public static void assertEquals(String message,
                                    boolean[] expected,
                                    boolean[] actual) {
        if (Arrays.equals(expected, actual)) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "expected array: <not null> but was <null>", expected);
        Assert.assertNotNull(formatted + "expected array: <null> but was <not null>", actual);
        Assert.assertEquals(formatted + "[array length] ", expected.length, actual.length);
        for (int i = 0; i < actual.length; i++) {
            Assert.assertEquals(formatted + "[position " + i + "]", expected[i], actual[i]);
        }
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param expected a short array of expected values
     * @param actual a short array of actual values
     */
    public static void assertEquals(boolean[] expected,
                                    boolean[] actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param message message to display when arrays are not equal
     * @param expected a byte array of expected values
     * @param actual a byte array of actual values
     */
    public static void assertEquals(String message,
                                    byte[] expected,
                                    byte[] actual) {
        if (Arrays.equals(expected, actual)) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "expected array: <not null> but was <null>", expected);
        Assert.assertNotNull(formatted + "expected array: <null> but was <not null>", actual);
        Assert.assertEquals(formatted + "[array length] ", expected.length, actual.length);
        for (int i = 0; i < actual.length; i++) {
            Assert.assertEquals(formatted + "[position " + i + "]", expected[i], actual[i]);
        }
    }

    /**
     * Asserts that two arrays are equal. Two arrays are considered equal if:
     * <ul>
     * <li>their respective lengths are the same</i>
     * <li>all corresponding pairs of elements are equal
     * <li>both array references are <code>null</code>
     * </ul>
     *
     * @param expected a byte array of expected values
     * @param actual a byte array of actual values
     */
    public static void assertEquals(byte[] expected,
                                    byte[] actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two arrays have the same elements, but not necessarily in
     * the same order.  If order is important then use assertEquals().  If a
     * difference is found, the object is listed by calling its toString() method.
     * Arrays are equal if both are <i>null</i>.
     *
     * Equality of objects uses the equals method.
     *
     * @see #assertEquals(String message, Object[] expected, Object[] actual)
     *
     * @param message message to display when arrays are not equal
     * @param expected an Object array of expected values
     * @param actual an Object array of actual values
     */
    public static void assertEquivalenceArrays(String message,
                                               Object[] expected,
                                               Object[] actual) {
        if (Arrays.equals(expected, actual)) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "expected array: <not null> but was <null>", expected);
        Assert.assertNotNull(formatted + "expected array: <null> but was <not null>", actual);

        ArrayList missing = new ArrayList();
        for (int i = 0; i < expected.length; i++) {
            missing.add(expected[i]);
        }

        ArrayList extra = new ArrayList();
        for (int i = 0; i < actual.length; i++) {
            extra.add(actual[i]);
        }

        ArrayList missingClone = (ArrayList) missing.clone();
        missing.removeAll(extra);
        extra.removeAll(missingClone);

        Assert.assertTrue(formatted + "[Missing elements: " + missing + "]", missing.size() == 0);
        Assert.assertTrue(formatted + "[Extra elements: " + extra + "]", extra.size() == 0);
    }

    /**
     * Assertion that both arrays has the same elements, but not necessarily in
     * the same order.  If order is important then use assertEquals().  If a
     * difference is found, the object is listed by calling its toString() method.
     * Arrays are equal if both are <i>null</i>.
     *
     * Equality of objects uses the equals method.
     *
     * @see #assertEquals(String message, Object[] expected, Object[] actual)
     *
     * @param expected an Object array of expected values
     * @param actual an Object array of actual values
     */
    public static void assertEquivalenceArrays(Object[] expected,
                                               Object[] actual) {
        assertEquivalenceArrays(null, expected, actual);
    }

}
