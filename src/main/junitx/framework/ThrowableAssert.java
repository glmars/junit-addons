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
 * A set of assert methods specially targetted to asserting throwable objects.
 *
 * @version $Revision: 1.6 $ $Date: 2003/03/21 06:13:48 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class ThrowableAssert {

    /**
     * Don't let anyone have access to this constructor.
     */
    private ThrowableAssert() {
    }

    /**
     * Asserts that two throwable objects are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.<p>
     *
     * Two throwable objects are considered equals if:
     * <ul>
     * <li>their respective classes are the same</li>
     * <li>their respective messages are equal</li>
     * <li>their respective localized messages are equal</li>
     * </ul>
     */
    public static void assertEquals(String message,
                                    Throwable expected,
                                    Throwable actual) {
        ObjectAssert.assertInstanceOf(message, expected.getClass(), actual);
        if (message == null) {
            Assert.assertEquals("[message]", expected.getMessage(), actual.getMessage());
            Assert.assertEquals("[localized message]", expected.getLocalizedMessage(), actual.getLocalizedMessage());
        } else {
            Assert.assertEquals(message + " [message]", expected.getMessage(), actual.getMessage());
            Assert.assertEquals(message + " [localized message]", expected.getLocalizedMessage(), actual.getLocalizedMessage());
        }
    }

    /**
     * Asserts that two throwable objects are equal. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     *
     * @see #assertEquals(String, Throwable, Throwable)
     */
    public static void assertEquals(Throwable expected,
                                    Throwable actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Assert that two throwable objects are similar. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     *
     * Two throwable objects are considered similar if:
     * <ul>
     * <li>their respective classes are the same</li>
     * <li>the actual object's message or localized message contains the
     * expected object's message</li>
     * </ul>
     */
    public static void assertSimilar(String message,
                                     Throwable expected,
                                     Throwable actual) {
        ObjectAssert.assertInstanceOf(message, expected.getClass(), actual);
        if (expected.getMessage() == null) {
            throw new IllegalArgumentException("String to search cannot be <null>");
        }
        if (actual.getMessage() != null) {
            if (actual.getMessage().indexOf(expected.getMessage()) >= 0) {
                return;
            }
        }
        if (actual.getLocalizedMessage() != null) {
            if (actual.getLocalizedMessage().indexOf(expected.getMessage()) >= 0) {
                return;
            }
        }
        failContainsMessage(message, expected, actual);
    }

    /**
     * Assert that two throwable objects are similar. Throws an
     * <tt>AssertionFailedError</tt> if they are equal.
     *
     * @see #assertSimilar(String, Throwable, Throwable)
     */
    public static void assertSimilar(Throwable expected,
                                     Throwable actual) {
        assertSimilar(null, expected, actual);
    }

    private static void failContainsMessage(String message,
                                            Throwable expected,
                                            Throwable actual) {
        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }
        if (actual.getMessage().equals(actual.getLocalizedMessage())) {
            Assert.fail(formatted + "Expected containing <" + expected.getMessage() + "> in <" + actual.getMessage() + ">");
        } else {
            Assert.fail(formatted + "Expected containing <" + expected.getMessage() + "> in <" + actual.getMessage() + "> or <" + actual.getLocalizedMessage() + "> (localized)");
        }
    }

}
