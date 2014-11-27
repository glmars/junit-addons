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

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import junit.framework.Assert;
import junitx.util.NamingUtil;

/**
 * A set of assert methods specially targetted to asserting objects found in 
 * the <tt>javax.naming</tt> package.
 * 
 * @version $Revision: 1.2 $ $Date: 2003/05/03 03:50:36 $
 * @author <a href="mailto:vbossica@users.sourceforge.net">Vladimir R. Bossicard</a>
 */
public class NamingAssert {

    /**
     * Don't let anyone have access to this constructor.
     */
    private NamingAssert() {
    }

    /**
     * Asserts that two <tt>Attributes</tt> objects are equal. Throws an
     * <tt>AssertionFailedError</tt> if not.
     */
    public static void assertEquals(Attributes expected, Attributes actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two <tt>Attributes</tt> objects are equal. Throws an
     * <tt>AssertionFailedError</tt> if not.
     */
    public static void assertEquals(String message, Attributes expected, Attributes actual) {
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "[expected] expected not <null>", expected);
        Assert.assertNotNull(formatted + "[actual] expected not <null>", actual);

        try {
            NamingEnumeration ids = expected.getIDs();
            while (ids.hasMore()) {
                String key = ids.next().toString();
                assertEquals(message, expected.get(key), actual.get(key));
            }
        } catch (NamingException e) {
            throw new ExecutionError(e);
        }
    }

    /**
     * Asserts that two attributes are equal. Throws an
     * <tt>AssertionFailedError</tt> if not.
     */
    public static void assertEquals(Attribute expected, Attribute actual) {
        assertEquals(null, expected, actual);
    }
    
    /**
     * Asserts that two attributes are equal. Throws an
     * <tt>AssertionFailedError</tt> if not.
     */
    public static void assertEquals(String message, Attribute expected, Attribute actual) {
        if ((expected == null && actual == null) || (expected != null && expected.equals(actual))) {
            return;
        }

        String formatted = "";
        if (message != null) {
            formatted = message + " ";
        }

        Assert.assertNotNull(formatted + "[expected] expected not <null>", expected);
        Assert.assertNotNull(formatted + "[actual] expected not <null>", actual);

        try {
            assertEquals(message, expected.getAll(), actual.getAll());
        } catch (NamingException e) {
            throw new ExecutionError(e);
        }
    }

    /**
     * Asserts that two <tt>NamingEnumeration</tt>s are equal. Throws an
     * <tt>AssertionFailedError</tt> if not.
     */
    public static void assertEquals(NamingEnumeration expected, NamingEnumeration actual) {
        assertEquals(null, expected, actual);
    }

    /**
     * Asserts that two <tt>NamingEnumeration</tt>s are equal. Throws an
     * <tt>AssertionFailedError</tt> if not.
     */
    public static void assertEquals(String message, NamingEnumeration expected, NamingEnumeration actual) {
        try {
            ListAssert.assertEquals(message, NamingUtil.toList(expected), NamingUtil.toList(actual));
        } catch (NamingException e) {
            throw new ExecutionError(e);
        }
    }

}
