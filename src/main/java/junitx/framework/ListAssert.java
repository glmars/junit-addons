/*
 * The JUnit-addons Software License, Version 1.0
 *     (based on the Apache Software License, Version 1.1)
 *
 * Copyright (c) 2003 Vladimir R. Bossicard.  All rights reserved.
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

import java.util.List;

/**
 * A set of assert methods specially targetted to asserting Lists.
 * 
 * @version $Revision: 1.1 $ $Date: 2003/04/28 03:08:17 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ListAssert {

    /**
     * Don't let anyone have access to this constructor.
     */
    private ListAssert() {
    }

    /**
     * Asserts that two lists are equal (the order is not relevant). Throws an
     * <tt>AssertionFailedError</tt> if not.
     */
    public static void assertEquals(List expected, List actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two lists are equal (the order is not relevant). Throws an
     * <tt>AssertionFailedError</tt> if not.
     */
    public static void assertEquals(String message, List expected, List actual) {
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            return;
        }

        for (int ii = 0; ii < expected.size(); ii++) {
            assertContains(message, actual, expected.get(ii));
        }
        String formatted = "[length]";
        if (message != null) {
            formatted = message + " " + formatted;
        }

        Assert.assertEquals(formatted, expected.size(), actual.size());
    }

    /**
     * Asserts that a list contains a given object. Throws an
     * <tt>AssertionFailedError</tt> if it doesn't contain the object.
     */
    public static void assertContains(List actual, Object value) {
        assertContains(null, actual, value);
    }

    /**
     * Asserts that a list contains a given object. Throws an
     * <tt>AssertionFailedError</tt> if it doesn't contain the object.
     */
    public static void assertContains(String message, List actual, Object value) {
        Assert.assertNotNull(message, actual);
        if (actual.contains(value)) {
            return;
        }
        failNotContains(message, actual, value);
    }

    private static void failNotContains(String message, List actual, Object value) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        StringBuffer listcontent = new StringBuffer();
        for (int ii = 0; ii < actual.size() - 1; ii++) {
            listcontent.append(actual.get(ii)).append(", ");
        }
        listcontent.append(actual.get(actual.size() - 1));

        throw new junit.framework.AssertionFailedError(formatted + "expecting <" + value + "> in <" + listcontent.toString() + ">");
    }

}
